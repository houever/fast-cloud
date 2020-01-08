package cn.fast.admin.service.impl;
import javax.annotation.Resource;
import cn.fast.web.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import cn.fast.admin.repository.SysDictDataRepository;
import cn.fast.admin.entity.SysDictData;
import cn.fast.admin.service.ISysDictDataService;
import cn.fast.admin.mapper.ISysDictDataMapper;
/**
 * <p>
 * 系统分类数据表 服务实现类
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
@Service
public class SysDictDataServiceImpl extends BaseServiceImpl<ISysDictDataMapper,SysDictDataRepository,SysDictData> implements ISysDictDataService{

    @Resource
    private ISysDictDataMapper SysDictDataMapper;

}
