package cn.fast.usc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.fast.usc.entity.SysAccount;
/**
 * <p>
 *  Repository 接口
 * </p>
 *
 * @author houqj
 * @since 2020-01-03
 */
public interface SysAccountRepository extends JpaRepository<SysAccount, String>{
	 
}