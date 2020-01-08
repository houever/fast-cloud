package cn.fast.admin.service;
import cn.fast.web.base.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.fast.admin.entity.SysRoles;
import cn.fast.admin.model.dto.RoleDTO;

import java.util.List;

/**
 * <p>
 * 角色表 接口
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
public interface ISysRolesService extends BaseService<SysRoles>{

    /**
     * 根据用户id获取角色列表
     * @param id
     * @return
     */
    List<SysRoles> getRolesByAccountId(String id);

    /**
     * 角色分页列表
     * @param page
     * @return
     */
    IPage<RoleDTO> getPageList(Page<RoleDTO> page);

}
