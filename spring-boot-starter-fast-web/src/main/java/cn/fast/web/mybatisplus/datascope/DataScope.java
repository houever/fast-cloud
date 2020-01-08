package cn.fast.web.mybatisplus.datascope;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author houqijun
 * @date 2018/8/30
 * 数据权限查询参数
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DataScope extends HashMap {
	/**
	 * 限制范围的字段名称
	 */
	private String scopeName = "deptId";

	/**
	 * 具体的数据范围
	 */
	private List<String> deptIds = new ArrayList<String>();

	/**
	 * 是否只查询本部门
	 */
	private Boolean isOnly = false;
}
