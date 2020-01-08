package cn.fast.usc.service.impl;
import javax.annotation.Resource;
import cn.fast.web.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import cn.fast.usc.repository.SysRolesPermissionRepository;
import cn.fast.usc.entity.SysRolesPermission;
import cn.fast.usc.service.ISysRolesPermissionService;
import cn.fast.usc.mapper.ISysRolesPermissionMapper;
/**
 * <p>
 * 角色权限表 服务实现类
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
@Service
public class SysRolesPermissionServiceImpl extends BaseServiceImpl<ISysRolesPermissionMapper,SysRolesPermissionRepository,SysRolesPermission> implements ISysRolesPermissionService{

    @Resource
    private ISysRolesPermissionMapper SysRolesPermissionMapper;

}
