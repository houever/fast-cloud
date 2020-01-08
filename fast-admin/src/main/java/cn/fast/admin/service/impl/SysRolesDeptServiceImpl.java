package cn.fast.admin.service.impl;
import javax.annotation.Resource;
import cn.fast.web.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import cn.fast.admin.repository.SysRolesDeptRepository;
import cn.fast.admin.entity.SysRolesDept;
import cn.fast.admin.service.ISysRolesDeptService;
import cn.fast.admin.mapper.ISysRolesDeptMapper;
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

    /**
     * 根据角色id删除 角色部门中间表数据
     *
     * @param id
     * @return
     */
    @Override
    public Integer deleteRolesDeptByRid(String id) {
        return SysRolesDeptMapper.deleteRolesDeptByRid(id);
    }
}
