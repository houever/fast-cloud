package cn.fast.admin.mapper;
import cn.fast.web.base.BaseMpMapper;
import cn.fast.web.common.model.MenuVo;
import cn.fast.admin.entity.SysPermission;

import java.util.List;

/**
 * <p>
 * 权限菜单表 Mapper 接口
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
public interface ISysPermissionMapper extends BaseMpMapper<SysPermission>{

    /**
     * 根据用户id查询菜单列表
     *
     * @param id
     * @return
     */
    List<MenuVo> getMenusListByUserId(String id);

    /**
     * 根据用户id查询所拥有的按钮列表
     *
     * @param id
     * @return
     */
    List<String> getPermissionBtnsByUserId(String id);
    /**
     * 根据用户id查询所有可按钮访问权限列表
     *
     * @param id
     * @return
     */
    List<String> getPermissionByUserId(String id);
}