package cn.fast.usc.service.impl;
import javax.annotation.Resource;
import cn.fast.web.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import cn.fast.usc.repository.SysRolesDeptRepository;
import cn.fast.usc.entity.SysRolesDept;
import cn.fast.usc.service.ISysRolesDeptService;
import cn.fast.usc.mapper.ISysRolesDeptMapper;
/**
 * <p>
 * 部门数据权限表 服务实现类
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
@Service
public class SysRolesDeptServiceImpl extends BaseServiceImpl<ISysRolesDeptMapper,SysRolesDeptRepository,SysRolesDept> implements ISysRolesDeptService{

    @Resource
    private ISysRolesDeptMapper SysRolesDeptMapper;

}
