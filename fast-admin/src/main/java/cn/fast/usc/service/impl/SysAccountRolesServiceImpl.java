package cn.fast.usc.service.impl;
import javax.annotation.Resource;
import cn.fast.web.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import cn.fast.usc.repository.SysAccountRolesRepository;
import cn.fast.usc.entity.SysAccountRoles;
import cn.fast.usc.service.ISysAccountRolesService;
import cn.fast.usc.mapper.ISysAccountRolesMapper;
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
