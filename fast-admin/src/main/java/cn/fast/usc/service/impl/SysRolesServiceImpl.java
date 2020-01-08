package cn.fast.usc.service.impl;
import javax.annotation.Resource;
import cn.fast.web.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import cn.fast.usc.repository.SysRolesRepository;
import cn.fast.usc.entity.SysRoles;
import cn.fast.usc.service.ISysRolesService;
import cn.fast.usc.mapper.ISysRolesMapper;
/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
@Service
public class SysRolesServiceImpl extends BaseServiceImpl<ISysRolesMapper,SysRolesRepository,SysRoles> implements ISysRolesService{

    @Resource
    private ISysRolesMapper SysRolesMapper;

}
