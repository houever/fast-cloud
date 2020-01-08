package cn.fast.usc.service.impl;
import javax.annotation.Resource;
import cn.fast.web.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import cn.fast.usc.repository.SysLoginConfigRepository;
import cn.fast.usc.entity.SysLoginConfig;
import cn.fast.usc.service.ISysLoginConfigService;
import cn.fast.usc.mapper.ISysLoginConfigMapper;
/**
 * <p>
 * 登录背景配置表 服务实现类
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
@Service
public class SysLoginConfigServiceImpl extends BaseServiceImpl<ISysLoginConfigMapper,SysLoginConfigRepository,SysLoginConfig> implements ISysLoginConfigService{

    @Resource
    private ISysLoginConfigMapper SysLoginConfigMapper;

}
