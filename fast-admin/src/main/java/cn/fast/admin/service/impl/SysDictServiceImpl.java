package cn.fast.admin.service.impl;
import javax.annotation.Resource;
import cn.fast.web.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import cn.fast.admin.repository.SysDictRepository;
import cn.fast.admin.entity.SysDict;
import cn.fast.admin.service.ISysDictService;
import cn.fast.admin.mapper.ISysDictMapper;
/**
 * <p>
 * 系统字典分类表 服务实现类
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
@Service
public class SysDictServiceImpl extends BaseServiceImpl<ISysDictMapper,SysDict> implements ISysDictService{

    @Resource
    private ISysDictMapper SysDictMapper;

}
