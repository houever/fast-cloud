package cn.fast.web.mybatisplus.handler;

import cn.fast.web.common.exception.BusinessExcpetion;
import cn.fast.web.mybatisplus.tenant.TenantContextHolder;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @author houqj
 * @date 2019-08-06 17:13
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        if(null != TenantContextHolder.getUser()){
            log.debug("添加操作:字段填充当前用户{}",TenantContextHolder.getUser());
            this.setFieldValByName("createBy", TenantContextHolder.getUser().getUsername(), metaObject);
        }else{
            throw new BusinessExcpetion("操作失败，未获取到当前用户信息");
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if(null != TenantContextHolder.getUser()){
            log.debug("修改操作:字段填充当前用户{}",TenantContextHolder.getUser());
            this.setFieldValByName("updateBy", TenantContextHolder.getUser().getUsername(), metaObject);
        }else{
            throw new BusinessExcpetion("操作失败，未获取到当前用户信息");
        }
    }
}
