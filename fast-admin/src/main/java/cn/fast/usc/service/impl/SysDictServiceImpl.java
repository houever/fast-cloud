package cn.fast.usc.service.impl;
import javax.annotation.Resource;
import cn.fast.web.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import cn.fast.usc.repository.SysDictRepository;
import cn.fast.usc.entity.SysDict;
import cn.fast.usc.service.ISysDictService;
import cn.fast.usc.mapper.ISysDictMapper;
/**
 * <p>
 * 系统字典分类表 服务实现类
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
@Service
public class SysDictServiceImpl extends BaseServiceImpl<ISysDictMapper,SysDictRepository,SysDict> implements ISysDictService{

    @Resource
    private ISysDictMapper SysDictMapper;

}
