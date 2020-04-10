package cn.fast.web.mybatisplus;

import cn.fast.web.mybatisplus.injectors.MysqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author houqijun
 * @date 2017/10/29
 */
@Configuration
public class MybatisPlusConfig {

	/**
	 * 分页插件
	 *
	 * @return PaginationInterceptor
	 */
	@Bean
	@ConditionalOnMissingBean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
		return paginationInterceptor;
	}

	@Bean
	public MysqlInjector mysqlInjector(){
		return new MysqlInjector();
	}
}
