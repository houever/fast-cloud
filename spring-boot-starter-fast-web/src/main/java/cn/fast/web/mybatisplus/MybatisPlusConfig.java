package cn.fast.web.mybatisplus;

import cn.fast.web.mybatisplus.datascope.DataScopeInterceptor;
import cn.fast.web.mybatisplus.injectors.MysqlInjector;
import cn.fast.web.mybatisplus.tenant.CustomTenantHandler;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author houqijun
 * @date 2017/10/29
 */
@Configuration
public class MybatisPlusConfig {

	/**
	 * 非法sql拦截
	 * https://github.com/baomidou/mybatis-plus/blob/3.0/mybatis-plus-extension/src/main/java/com/baomidou/mybatisplus/extension/plugins/IllegalSQLInterceptor.java
	 */
//	@Bean
//	public IllegalSQLInterceptor illegalSQLInterceptor() {
//		return new IllegalSQLInterceptor();
//	}

	/**
	 * 分页插件
	 *
	 * @return PaginationInterceptor
	 */
	@Bean
	@ConditionalOnMissingBean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
		List<ISqlParser> sqlParserList = new ArrayList<>();
		TenantSqlParser tenantSqlParser = new TenantSqlParser();
		tenantSqlParser.setTenantHandler(new CustomTenantHandler());
		sqlParserList.add(tenantSqlParser);
		paginationInterceptor.setSqlParserList(sqlParserList);
		return paginationInterceptor;
	}

	/**
	 * 数据权限插件
	 *
	 * @param dataSource 数据源
	 * @return DataScopeInterceptor
	 */
	@Bean
	@ConditionalOnMissingBean
	public DataScopeInterceptor dataScopeInterceptor(DataSource dataSource) {
		return new DataScopeInterceptor(dataSource);
	}

	@Bean
	public MysqlInjector mysqlInjector(){
		return new MysqlInjector();
	}
}
