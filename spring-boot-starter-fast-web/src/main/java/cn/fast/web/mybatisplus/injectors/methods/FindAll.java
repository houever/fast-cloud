package cn.fast.web.mybatisplus.injectors.methods;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * @author houqj
 * @date 2019-12-27 16:28
 */
public class FindAll extends AbstractMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sql = "SELECT * from "+tableInfo.getTableName();
        SqlSource sqlSource = languageDriver.createSqlSource(configuration,sql,modelClass);
        return this.addSelectMappedStatementForTable(mapperClass,"findAll",sqlSource,tableInfo);
    }
}
