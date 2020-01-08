package cn.fast.admin.service;
import cn.fast.web.base.BaseService;
import cn.fast.admin.entity.SysRolesDept;
/**
 * <p>
 * 部门数据权限表 接口
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
public interface ISysRolesDeptService extends BaseService<SysRolesDept>{
    /**
     * 根据角色id删除 角色部门中间表数据
     * @param id
     * @return
     */
    Integer deleteRolesDeptByRid(String id);

}
