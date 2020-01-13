package cn.fast.admin.mapper;
import cn.fast.web.base.BaseMpMapper;
import cn.fast.web.base.BasePage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.fast.admin.entity.SysAccount;
import cn.fast.admin.model.dto.AccountDTO;

import java.util.List;

/**
 * <p>
 * 账户表 Mapper 接口
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
public interface ISysAccountMapper extends BaseMpMapper<SysAccount>{

    /**
     * 用户列表分页接口
     *
     * @param page
     * @return
     */
    IPage<AccountDTO> getAccountPage(BasePage<AccountDTO> page);

    /**
     * 根据访问路径获取角色列表
     *
     * @param url
     * @return
     */
    List<String> selectRolesByUrl(String url);

    /**
     * @return java.util.List<cn.fast.usc.model.entity.SysAccount>
     * @Author houqijun
     * @Description 根据角色id查询所有用户
     * @Date 16:11 2019/2/26 0026
     * @Param [id]
     **/
    List<SysAccount> findUserByRoleId(String id);

    SysAccount findByUserName();
}