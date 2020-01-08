package cn.fast.admin.mapper;
import cn.fast.web.base.BaseMpMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.fast.admin.entity.SysRoles;
import cn.fast.admin.model.dto.RoleDTO;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
public interface ISysRolesMapper extends BaseMpMapper<SysRoles>{

    /**
     * 根据用户id查询角色集合
     * @return
     */
    List<SysRoles> getRolesByAccountId(String id);

    /**
     * 角色分页
     * @param page
     * @return
     */
    IPage<RoleDTO> getPageList(Page<RoleDTO> page);

}