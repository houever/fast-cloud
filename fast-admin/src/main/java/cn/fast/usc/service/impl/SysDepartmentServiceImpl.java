package cn.fast.usc.service.impl;
import javax.annotation.Resource;
import cn.fast.web.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import cn.fast.usc.repository.SysDepartmentRepository;
import cn.fast.usc.entity.SysDepartment;
import cn.fast.usc.service.ISysDepartmentService;
import cn.fast.usc.mapper.ISysDepartmentMapper;
/**
 * <p>
 * 系统部门表 服务实现类
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
@Service
public class SysDepartmentServiceImpl extends BaseServiceImpl<ISysDepartmentMapper,SysDepartmentRepository,SysDepartment> implements ISysDepartmentService{

    @Resource
    private ISysDepartmentMapper SysDepartmentMapper;

}
