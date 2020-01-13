package cn.fast.admin.service.impl;
import javax.annotation.Resource;
import cn.fast.web.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import cn.fast.admin.repository.SysLogRepository;
import cn.fast.admin.entity.SysLog;
import cn.fast.admin.service.ISysLogService;
import cn.fast.admin.mapper.ISysLogMapper;
/**
 * <p>
 * 系统日志表 服务实现类
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
@Service
public class SysLogServiceImpl extends BaseServiceImpl<ISysLogMapper,SysLog> implements ISysLogService{

    @Resource
    private ISysLogMapper SysLogMapper;

}
