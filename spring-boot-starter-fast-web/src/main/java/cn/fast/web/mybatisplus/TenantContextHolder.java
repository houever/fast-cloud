package cn.fast.web.mybatisplus;

import cn.fast.web.common.model.OAuth2AccessToken;
import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.experimental.UtilityClass;

/**
 * @author houqijun
 * @date 2018/10/4
 * 租户工具类
 */
@UtilityClass
public class TenantContextHolder {

	private final ThreadLocal<Integer> thread_local_tenant = new TransmittableThreadLocal<>();
	private final ThreadLocal<OAuth2AccessToken> thread_lcoal_user = new TransmittableThreadLocal<>();

	public OAuth2AccessToken getUser(){
		return thread_lcoal_user.get();
	}

	public void setUser(OAuth2AccessToken oauth2accessToken){
		thread_lcoal_user.set(oauth2accessToken);
	}

	/**
	 * TTL 设置租户ID
	 *
	 * @param tenantId
	 */
	public void setTenantId(Integer tenantId) {
		thread_local_tenant.set(tenantId);
	}

	/**
	 * 获取TTL中的租户ID
	 *
	 * @return
	 */
	public Integer getTenantId() {
		return thread_local_tenant.get();
	}

	public void clear() {
		thread_local_tenant.remove();
		thread_lcoal_user.remove();
	}
}
