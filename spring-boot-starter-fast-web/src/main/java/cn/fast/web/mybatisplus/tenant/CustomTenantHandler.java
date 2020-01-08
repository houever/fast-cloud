package cn.fast.web.mybatisplus.tenant;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;

/**
 * @author houqijun
 * @date 2018-12-26
 * <p>
 * 租户维护处理器
 */
@Slf4j
public class CustomTenantHandler implements TenantHandler {
//	private static final String[] TENANT_TABLES = new String[]{"sys_user", "sys_role", "sys_dept",
//			"sys_log", "sys_social_details", "sys_dict", "sys_log", "oa_leave_bill"};

		private static final String[] TENANT_TABLES = new String[]{"sys_dept"};
	/**
	 * 获取租户值
	 *
	 * @return 租户值
	 */
	@Override
	public Expression getTenantId(boolean where) {
		Integer tenantId = TenantContextHolder.getTenantId();
		log.debug("当前租户为 >> {}", tenantId);
		return new LongValue(tenantId);
	}

	/**
	 * 获取租户字段名
	 *
	 * @return 租户字段名
	 */
	@Override
	public String getTenantIdColumn() {
		return "tenant_id";
	}

	/**
	 * 根据表名判断是否进行过滤
	 *
	 * @param tableName 表名
	 * @return 是否进行过滤
	 */
	@Override
	public boolean doTableFilter(String tableName) {
		return !ArrayUtil.contains(TENANT_TABLES, tableName);
	}
}
