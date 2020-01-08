package cn.fast.admin.service.impl;
import javax.annotation.Resource;

import cn.fast.web.base.BasePage;
import cn.fast.web.base.BaseServiceImpl;
import cn.fast.web.utils.ObjectConvertUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.fast.admin.entity.SysAccountRoles;
import cn.fast.admin.mapper.ISysAccountRolesMapper;
import cn.fast.admin.model.dto.AccountDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import cn.fast.admin.repository.SysAccountRepository;
import cn.fast.admin.entity.SysAccount;
import cn.fast.admin.service.ISysAccountService;
import cn.fast.admin.mapper.ISysAccountMapper;

import java.util.List;

/**
 * <p>
 * 账户表 服务实现类
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
@Slf4j
@Service
@AllArgsConstructor
public class SysAccountServiceImpl extends BaseServiceImpl<ISysAccountMapper,SysAccountRepository,SysAccount> implements ISysAccountService{

    private ISysAccountMapper SysAccountMapper;
    private ISysAccountRolesMapper sysAccountRolesMapper;
    /**
     * 用户列表分页接口
     *
     * @param page
     * @return
     */
    @Override
    public IPage<AccountDTO> getAccountPage(BasePage<AccountDTO> page) {
        log.debug("page==========>{}",page);
        return SysAccountMapper.getAccountPage(page);
    }

    /**
     * 编辑用户
     *
     * @param accountDTO
     * @return
     */
    @Override
    public Integer updateAccount(AccountDTO accountDTO) {
        SysAccount sysAccount = ObjectConvertUtil.convert(accountDTO, SysAccount.class);
        int i = SysAccountMapper.updateById(sysAccount);

        i += sysAccountRolesMapper.deleteByIdAccountId(accountDTO.getId());

        String[] rids = accountDTO.getRids();
        SysAccountRoles sysAccountRoles = null;
        for (String rid : rids) {
            sysAccountRoles = new SysAccountRoles();
            sysAccountRoles.setAid(sysAccount.getId());
            sysAccountRoles.setRid(rid);
            sysAccountRolesMapper.insert(sysAccountRoles);
        }

        return i;
    }

    /**
     * 根据访问路径获取角色列表
     *
     * @param url
     * @return
     */
    @Override
    public List<String> selectRolesByUrl(String url) {
        return SysAccountMapper.selectRolesByUrl(url);
    }

    /**
     * @param id
     * @return java.util.List<cn.fast.usc.model.entity.SysAccount>
     * @Author houqijun
     * @Description 根据角色id查询所有用户
     * @Date 16:10 2019/2/26 0026
     * @Param [id]
     */
    @Override
    public List<SysAccount> findUserByRoleId(String id) {
        return SysAccountMapper.findUserByRoleId(id);
    }

}
