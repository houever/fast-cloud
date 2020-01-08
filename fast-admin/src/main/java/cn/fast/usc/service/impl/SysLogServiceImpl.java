package cn.fast.usc.service.impl;
import javax.annotation.Resource;
import cn.fast.web.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import cn.fast.usc.repository.SysLogRepository;
import cn.fast.usc.entity.SysLog;
import cn.fast.usc.service.ISysLogService;
import cn.fast.usc.mapper.ISysLogMapper;
/**
 * <p>
 * 系统日志表 服务实现类
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
@Service
public class SysLogServiceImpl extends BaseServiceImpl<ISysLogMapper,SysLogRepository,SysLog> implements ISysLogService{

    @Resource
    private ISysLogMapper SysLogMapper;

}
