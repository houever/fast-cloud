package cn.fast.admin.service;
import cn.fast.web.base.BasePage;
import cn.fast.web.base.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.fast.admin.entity.SysAccount;
import cn.fast.admin.model.dto.AccountDTO;

import java.util.List;

/**
 * <p>
 * 账户表 接口
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
public interface ISysAccountService extends BaseService<SysAccount>{

    /**
     * 用户分页列表
     *
     * @return
     */
    IPage<AccountDTO> getAccountPage(BasePage<AccountDTO> page);

    /**
     * 编辑用户
     * @param accountDTO
     * @return
     */
    Integer updateAccount(AccountDTO accountDTO);

    /**
     * @Author houqijun
     * @Description 根据角色id查询所有用户
     * @Date 16:10 2019/2/26 0026
     * @Param [id]
     * @return java.util.List<cn.fast.usc.model.entity.SysAccount>
     **/
    List<SysAccount> findUserByRoleId(String id);
}
