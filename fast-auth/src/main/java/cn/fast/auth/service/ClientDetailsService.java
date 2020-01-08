package cn.fast.auth.service;

import cn.fast.web.common.constant.SecurityConstants;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * @author Houqijun
 * @date 2018/10/22
 * <p>
 * see JdbcClientDetailsService
 */
public class ClientDetailsService extends JdbcClientDetailsService {

	public ClientDetailsService(DataSource dataSource) {
		super(dataSource);
	}

	/**
	 * 重写原生方法支持redis缓存
	 *
	 * @param clientId
	 * @return
	 * @throws InvalidClientException
	 */
	@Override
	@Cacheable(value = SecurityConstants.CLIENT_DETAILS_KEY, key = "#clientId", unless = "#result == null")
	public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
		return super.loadClientByClientId(clientId);
	}
}
