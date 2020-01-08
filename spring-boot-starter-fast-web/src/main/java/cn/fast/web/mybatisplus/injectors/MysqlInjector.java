package cn.fast.web.mybatisplus.injectors;

import cn.fast.web.mybatisplus.injectors.methods.FindAll;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.AbstractSqlInjector;
import com.baomidou.mybatisplus.core.injector.methods.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author houqj
 * @date 2019-12-27 16:27
 */
@Component
public class MysqlInjector extends AbstractSqlInjector {
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        return Stream.of(new FindAll(),new Insert(), new Delete(), new DeleteByMap(), new DeleteById(), new DeleteBatchByIds(), new Update(), new UpdateById(), new SelectById(), new SelectBatchByIds(), new SelectByMap(), new SelectOne(), new SelectCount(), new SelectMaps(), new SelectMapsPage(), new SelectObjs(), new SelectList(), new SelectPage()).collect(Collectors.toList());
    }
}
