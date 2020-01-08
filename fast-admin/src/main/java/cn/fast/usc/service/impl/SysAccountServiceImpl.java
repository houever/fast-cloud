package cn.fast.usc.service.impl;
import javax.annotation.Resource;
import cn.fast.web.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import cn.fast.usc.repository.SysAccountRepository;
import cn.fast.usc.entity.SysAccount;
import cn.fast.usc.service.ISysAccountService;
import cn.fast.usc.mapper.ISysAccountMapper;
/**
 * <p>
 * 账户表 服务实现类
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
@Service
public class SysAccountServiceImpl extends BaseServiceImpl<ISysAccountMapper,SysAccountRepository,SysAccount> implements ISysAccountService{

    @Resource
    private ISysAccountMapper SysAccountMapper;

}
