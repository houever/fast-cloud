package cn.fast.admin.service.impl;
import javax.annotation.Resource;
import cn.fast.web.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import cn.fast.admin.repository.SysDepartmentRepository;
import cn.fast.admin.entity.SysDepartment;
import cn.fast.admin.service.ISysDepartmentService;
import cn.fast.admin.mapper.ISysDepartmentMapper;
/**
 * <p>
 * 系统部门表 服务实现类
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
@Service
public class SysDepartmentServiceImpl extends BaseServiceImpl<ISysDepartmentMapper,SysDepartment> implements ISysDepartmentService{

    @Resource
    private ISysDepartmentMapper SysDepartmentMapper;

}
