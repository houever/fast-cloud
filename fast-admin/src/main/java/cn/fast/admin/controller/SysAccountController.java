package cn.fast.admin.controller;

import cn.fast.web.aspect.log.annotations.SystemLog;
import cn.fast.web.base.BaseController;
import cn.fast.web.base.BasePage;
import cn.fast.web.common.model.AuthAccount;
import cn.fast.web.common.model.AuthRoles;
import cn.fast.web.common.model.MenuVo;
import cn.fast.web.common.result.Result;
import cn.fast.web.common.result.ResultEnum;
import cn.fast.web.utils.ObjectConvertUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.fast.admin.entity.SysAccount;
import cn.fast.admin.entity.SysAccountRoles;
import cn.fast.admin.entity.SysRoles;
import cn.fast.admin.model.dto.AccountDTO;
import cn.fast.admin.model.param.AccountQueryParam;
import cn.fast.admin.service.ISysAccountRolesService;
import cn.fast.admin.service.ISysAccountService;
import cn.fast.admin.service.ISysPermissionService;
import cn.fast.admin.service.ISysRolesService;
import cn.fast.admin.utils.BCryptUtil;
import cn.fast.admin.utils.MenuVoTreeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

/**
 * <p>
 * 账户表 前端控制器
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
@Slf4j
@Api(tags = {"账户表 前端控制器"})
@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class SysAccountController extends BaseController<ISysAccountService, SysAccount, String> {

    private static final String serviceId = "fast-admin";
    private static final String moduleName = "用户服务";

    private final ISysAccountService sysAccountService;
    private final cn.fast.admin.repository.SysAccountRepository SysAccountRepository;
    private final ISysPermissionService sysMenusService;
    private final ISysAccountRolesService sysAccountRolesService;
    private final ISysRolesService sysRolesService;
    @Autowired
    @Qualifier("taskExecutor")
    private ThreadPoolTaskExecutor taskExecutor;

    /**
     * 用户登录
     *
     * @param authAccount
     * @return
     */
    @ApiOperation(value = "用户登录", httpMethod = "GET")
    @PostMapping(value = "/getUserByUsername")
    public Result<SysAccount> getUserByUsername(AuthAccount authAccount) {
        SysAccount sysAccount = sysAccountService.getOne(new QueryWrapper<SysAccount>().eq("username", authAccount.getUsername()));
        if (null == sysAccount) {
            return Result.fail();
        }
        return SUCCESS(sysAccount);
    }

    @PostMapping(value = "/login")
    public Result<AuthAccount> getUserByUserName(AuthAccount authAccount) {

        SysAccount sysAccount = sysAccountService.getOne(new QueryWrapper<SysAccount>().eq("username", authAccount.getUsername()));
        AuthAccount account = null;
        if (null == sysAccount) {
            return Result.fail(ResultEnum.LOGIN_USER_ERR);
        }
        account = ObjectConvertUtil.convert(sysAccount, AuthAccount.class);
        account.setEnabled(sysAccount.getEnabled() == 1 ? true : false);
        account.setAccountNonLocked(sysAccount.getAccountNonLocked() == 1 ? true : false);
        account.setCredentialsNonExpired(sysAccount.getCredentialsNonExpired() == 1 ? true : false);
        account.setAccountNonExpired(sysAccount.getAccountNonExpired() == 1 ? true : false);
        List<AuthRoles> authRoles = ObjectConvertUtil.convertList(sysRolesService.getRolesByAccountId(sysAccount.getId()), AuthRoles.class);
        account.setRoles(authRoles.stream().map(r -> r.getCode()).collect(Collectors.toList()));
        List<String> btns = sysMenusService.getPermissionByUserId(sysAccount.getId());
        account.setBtns(btns);
        log.debug("登录===>{}", account);
        return SUCCESS(account);
    }

    @GetMapping(value = "/getById/{id}")
    public Result getAccountById(@PathVariable(name = "id") String id) {
        return SUCCESS(sysAccountService.getById(id));
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @PostMapping(value = "/info")
    public Result<AuthAccount> authAccountResult(AuthAccount authAccount) {
        //获取用户id
        SysAccount sysAccount = sysAccountService.getOne(new QueryWrapper<SysAccount>().eq("username", authAccount.getUsername()));

        //根据用户id查询菜单列表
        List<MenuVo> menuVoList = sysMenusService.getMenusListByUserId(sysAccount.getId());
        List<String> btns = sysMenusService.getPermissionBtnsByUserId(sysAccount.getId());

        MenuVoTreeUtil menuVoTreeUtil = new MenuVoTreeUtil();
        List<MenuVo> menus = menuVoTreeUtil.menusList(menuVoList);
        AuthAccount account = ObjectConvertUtil.convert(sysAccount, AuthAccount.class);
        List<SysRoles> roles = sysRolesService.getRolesByAccountId(sysAccount.getId());
        List<String> rs = roles.stream().map(r -> r.getCode()).collect(Collectors.toList());
        account.setRoles(rs);
        account.setMenus(menus);
        account.setBtns(btns);
        return SUCCESS(account);
    }

    /**
     * 用户分页列表
     */
    @SystemLog(serviceId = serviceId, moduleName = moduleName, actionName = "用户分页", value = "用户分页")
    @GetMapping(value = "/pages/{current}/{size}")
    public Result getAccountPageList(@PathVariable(name = "current") Long current, @PathVariable(name = "size") Long size, AccountQueryParam queryParam) {
        BasePage<AccountDTO> basePage = new BasePage(current, size, queryParam);
        IPage<AccountDTO> page = sysAccountService.getAccountPage(basePage);
        return SUCCESS(page);
    }

    @SystemLog(serviceId = serviceId, moduleName = moduleName, actionName = "添加用户", value = "添加用户")
    @PostMapping(value = "/add")
    public WebAsyncTask<Result> addAccount(@RequestBody AccountDTO accountDTO) {
        // 此处模拟开启一个异步任务,超时时间为10s
        WebAsyncTask<Result> task = new WebAsyncTask(10 * 1000L, taskExecutor, () -> {
            SysAccount sysAccount = ObjectConvertUtil.convert(accountDTO, SysAccount.class);
            sysAccount.setPassword(BCryptUtil.encode(accountDTO.getPassword()));
            boolean b = sysAccountService.save(sysAccount);
            String[] rids = accountDTO.getRids();
            List<SysAccountRoles> list = new ArrayList<SysAccountRoles>();
            SysAccountRoles sysAccountRoles = null;
            for (String rid : rids) {
                sysAccountRoles = new SysAccountRoles();
                sysAccountRoles.setAid(sysAccount.getId());
                sysAccountRoles.setRid(rid);
                list.add(sysAccountRoles);
            }
            sysAccountRolesService.saveBatch(list);
            return SUCCESS();
        });
        return task;
    }

    @SystemLog(serviceId = serviceId, moduleName = moduleName, actionName = "修改用户信息", value = "修改用户信息")
    @PostMapping(value = "/edit")
    public WebAsyncTask<Result> editAccount(@RequestBody AccountDTO accountDTO) {
        // 1000 为超时设置
        WebAsyncTask<Result> webAsyncTask = new WebAsyncTask<Result>(10 * 1000L, taskExecutor, () -> {
            //业务逻辑处理
            Integer i = sysAccountService.updateAccount(accountDTO);
            return SUCCESS(i);
        });
        //执行完成处理
        webAsyncTask.onCompletion(new Runnable() {
            @Override
            public void run() {

            }
        });
        //超时处理
        webAsyncTask.onTimeout(new Callable<Result>() {
            @Override
            public Result call() throws Exception {
                return Result.fail(ResultEnum.BUSINESS_ERROR);
            }
        });
        //执行出现异常处理
        webAsyncTask.onError(() -> {
            return Result.fail();
        });
        return webAsyncTask;
    }

    @SystemLog(serviceId = serviceId, moduleName = moduleName, actionName = "删除用户", value = "删除用户")
    @DeleteMapping(value = "/{ids}")
    public Result delAccount(@PathVariable(name = "ids") String ids) {
        UpdateWrapper<SysAccount> sysAccountUpdateWrapper = new UpdateWrapper<>();
        sysAccountUpdateWrapper.set("deleted", 1);
        sysAccountUpdateWrapper.in("id", ids.split(","));
        boolean b = sysAccountService.update(sysAccountUpdateWrapper);
        return SUCCESS(b);
    }

    @SystemLog(serviceId = serviceId, moduleName = moduleName, actionName = "启/禁用用户", value = "启/禁用用户")
    @PostMapping(value = "/disable")
    public Callable<Result> disableAccount(@RequestBody SysAccount account) {
        log.info("主线程开始:{}", Thread.currentThread().getName());
        Callable<Result> callable = new Callable<Result>() {
            @Override
            public Result call() throws Exception {
                log.debug("{}异步新的线程开始", Thread.currentThread().getName());
                UpdateWrapper<SysAccount> sysAccountUpdateWrapper = new UpdateWrapper<>();
                sysAccountUpdateWrapper.set("enabled", account.getEnabled());
                sysAccountUpdateWrapper.eq("id", account.getId());
                return SUCCESS(sysAccountService.update(sysAccountUpdateWrapper));
            }
        };
        log.debug("{}主线程结束", Thread.currentThread().getName());
        return callable;
    }

    @SystemLog(serviceId = serviceId, moduleName = moduleName, actionName = "查询用户列表", value = "查询用户列表")
    @PostMapping(value = "/all")
    public Result getAll(Page<SysAccount> page) {
        return SUCCESS(sysAccountService.page(page));
    }

    @SystemLog(serviceId = serviceId, moduleName = moduleName, actionName = "根据id查询用户", value = "根据id查询用户")
    @PostMapping(value = "/getAccountByIds")
    public Result<List<AuthAccount>> getAccountByIds(String ids) {
        List<String> userIds = Arrays.asList(ids.split(","));
        List<SysAccount> sysAccounts = (List) sysAccountService.listByIds(userIds);
        List<AuthAccount> authAccounts = ObjectConvertUtil.convertList(sysAccounts, AuthAccount.class);
        return SUCCESS(authAccounts);
    }

    @SystemLog(serviceId = serviceId, moduleName = moduleName, actionName = "根据角色查询用户", value = "根据角色查询用户")
    @PostMapping(value = "/findUserByRoleId")
    public Result findUserByRoleId(String id) {
        List<SysAccount> list = sysAccountService.findUserByRoleId(id);
        List<AuthAccount> authAccounts = ObjectConvertUtil.convertList(list, AuthAccount.class);
        return SUCCESS(authAccounts);
    }

}
