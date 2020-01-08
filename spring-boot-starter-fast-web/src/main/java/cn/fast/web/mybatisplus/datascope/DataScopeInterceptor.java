package cn.fast.web.mybatisplus.datascope;

import cn.fast.web.common.exception.CheckedException;
import cn.fast.web.common.model.OAuth2AccessToken;
import cn.fast.web.mybatisplus.enums.DataScopeTypeEnum;
import cn.fast.web.mybatisplus.tenant.UserUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author houqijun
 * @date 2018/12/26
 * <p>
 * mybatis 数据权限拦截器
 *
 * intercept 方法是我们拦截到对象后所进行操作的位置，也就是我们之后编写逻辑代码的位置。
 *
 * plugin方法，根据参数可以看出，该方法的作用是拦截我们需要拦截到的对象。
 *
 * setProperties方法，我们可以通过配置文件中进行properties配置,然后在该方法中读取到配置。
 *
 * 这三个方法的执行顺序: setProperties--->plugin--->intercept
 */
@Slf4j
@AllArgsConstructor
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class DataScopeInterceptor extends AbstractSqlParserHandler implements Interceptor {

	private final DataSource dataSource;

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = PluginUtils.realTarget(invocation.getTarget());
		MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
		this.sqlParser(metaObject);
		// 先判断是不是SELECT操作
		MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
		if (!SqlCommandType.SELECT.equals(mappedStatement.getSqlCommandType())) {
			return invocation.proceed();
		}

		BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
		String originalSql = boundSql.getSql();
		Object parameterObject = boundSql.getParameterObject();

		//查找参数中包含DataScope类型的参数
		DataScope dataScope = findDataScopeObject(parameterObject);
		if (dataScope == null) {
			return invocation.proceed();
		}

		String scopeName = dataScope.getScopeName();
		List<String> deptIds = dataScope.getDeptIds();
		// 优先获取赋值数据
		if (CollUtil.isEmpty(deptIds)) {
            OAuth2AccessToken account = UserUtil.getCurrentUser();
            if (account == null) {
				throw new CheckedException("当前登录用户不存在！");
			}
			Entity query = Db.use(dataSource)
					.query("SELECT * FROM sys_role where role_code IN (" + CollUtil.join(account.getRoles(), ",") + ")")
					.stream().min(Comparator.comparingInt(o -> o.getInt("ds_type"))).get();

			Integer dsType = query.getInt("ds_type");
			// 查询全部
			if (DataScopeTypeEnum.ALL.getType() == dsType) {
				return invocation.proceed();
			}
			// 自定义
			if (DataScopeTypeEnum.CUSTOM.getType() == dsType) {
				String dsScope = query.getStr("ds_scope");
				deptIds.addAll(Arrays.stream(dsScope.split(","))
						.map(String::toString).collect(Collectors.toList()));
			}
			// 查询本级及其下级
			if (DataScopeTypeEnum.OWN_CHILD_LEVEL.getType() == dsType) {
				List<String> deptIdList = Db.use(dataSource)
						.findBy("sys_dept_relation", "ancestor", account.getDeptId())
						.stream().map(entity -> entity.getInt("descendant").toString())
						.collect(Collectors.toList());
				deptIds.addAll(deptIdList);
			}
			// 只查询本级
			if (DataScopeTypeEnum.OWN_LEVEL.getType() == dsType) {
				deptIds.add(account.getDeptId());
			}
		}
		String join = CollectionUtil.join(deptIds, ",");
		originalSql = "select * from (" + originalSql + ") temp_data_scope where temp_data_scope." + scopeName + " in (" + join + ")";
		metaObject.setValue("delegate.boundSql.sql", originalSql);
		return invocation.proceed();
	}

	/**
	 * 生成拦截对象的代理
	 *
	 * @param target 目标对象
	 * @return 代理对象
	 */
	@Override
	public Object plugin(Object target) {
		if (target instanceof StatementHandler) {
			return Plugin.wrap(target, this);
		}
		return target;
	}

	/**
	 * mybatis配置的属性
	 *
	 * @param properties mybatis配置的属性
	 */
	@Override
	public void setProperties(Properties properties) {

	}

	/**
	 * 查找参数是否包括DataScope对象
	 *
	 * @param parameterObj 参数列表
	 * @return DataScope
	 */
	private DataScope findDataScopeObject(Object parameterObj) {
		if (parameterObj instanceof DataScope) {
			return (DataScope) parameterObj;
		} else if (parameterObj instanceof Map) {
			for (Object val : ((Map<?, ?>) parameterObj).values()) {
				if (val instanceof DataScope) {
					return (DataScope) val;
				}
			}
		}
		return null;
	}

}
