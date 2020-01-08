package cn.fast.admin.service.impl;
import javax.annotation.Resource;
import cn.fast.web.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import cn.fast.admin.repository.SysAccountRolesRepository;
import cn.fast.admin.entity.SysAccountRoles;
import cn.fast.admin.service.ISysAccountRolesService;
import cn.fast.admin.mapper.ISysAccountRolesMapper;
/**
 * <p>
 * 账户角色表 服务实现类
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
@Service
public class SysAccountRolesServiceImpl extends BaseServiceImpl<ISysAccountRolesMapper,SysAccountRolesRepository,SysAccountRoles> implements ISysAccountRolesService{

    @Resource
    private ISysAccountRolesMapper SysAccountRolesMapper;

}
