package cn.fast.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.fast.admin.entity.SysDepartment;
/**
 * <p>
 *  Repository 接口
 * </p>
 *
 * @author houqj
 * @since 2020-01-03
 */
public interface SysDepartmentRepository extends JpaRepository<SysDepartment, String>{
	 
}