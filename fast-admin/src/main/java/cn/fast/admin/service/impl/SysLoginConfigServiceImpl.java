package cn.fast.admin.service.impl;
import javax.annotation.Resource;
import cn.fast.web.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import cn.fast.admin.repository.SysLoginConfigRepository;
import cn.fast.admin.entity.SysLoginConfig;
import cn.fast.admin.service.ISysLoginConfigService;
import cn.fast.admin.mapper.ISysLoginConfigMapper;
/**
 * <p>
 * 登录背景配置表 服务实现类
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
@Service
public class SysLoginConfigServiceImpl extends BaseServiceImpl<ISysLoginConfigMapper,SysLoginConfig> implements ISysLoginConfigService{

    @Resource
    private ISysLoginConfigMapper SysLoginConfigMapper;

}
