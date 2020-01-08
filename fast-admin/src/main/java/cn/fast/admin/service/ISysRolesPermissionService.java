package cn.fast.admin.service;
import cn.fast.web.base.BaseService;
import cn.fast.admin.entity.SysRolesPermission;
/**
 * <p>
 * 角色权限表 接口
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
public interface ISysRolesPermissionService extends BaseService<SysRolesPermission>{

    /**
     * 根据rid删除 role_permission 中间表数据
     * @param id
     * @return
     */
    Integer deleteRolesPermByRid(String id);
}
