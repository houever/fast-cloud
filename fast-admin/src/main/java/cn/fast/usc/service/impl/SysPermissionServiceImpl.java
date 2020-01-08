package cn.fast.usc.service.impl;
import javax.annotation.Resource;
import cn.fast.web.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import cn.fast.usc.repository.SysPermissionRepository;
import cn.fast.usc.entity.SysPermission;
import cn.fast.usc.service.ISysPermissionService;
import cn.fast.usc.mapper.ISysPermissionMapper;
/**
 * <p>
 * 权限菜单表 服务实现类
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
@Service
public class SysPermissionServiceImpl extends BaseServiceImpl<ISysPermissionMapper,SysPermissionRepository,SysPermission> implements ISysPermissionService{

    @Resource
    private ISysPermissionMapper SysPermissionMapper;

}
