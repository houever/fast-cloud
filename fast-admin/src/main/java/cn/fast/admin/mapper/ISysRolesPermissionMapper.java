package cn.fast.admin.mapper;
import cn.fast.web.base.BaseMpMapper;
import cn.fast.admin.entity.SysRolesPermission;
/**
 * <p>
 * 角色权限表 Mapper 接口
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
public interface ISysRolesPermissionMapper extends BaseMpMapper<SysRolesPermission>{

    /**
     * 根据rid删除 role_permission 中间表数据
     *
     * @param id
     * @return
     */
    Integer deleteRolesPermByRid(String id);
}