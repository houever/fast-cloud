package cn.fast.web.base;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;

/**
 * @Description: 快速创建mybatis-plus 的 wrapper
 * 使用WrapperFactory.queryWrapper()  而不是去new QueryWrapper
 * 封装了之后更加优雅，符合链式操作
 * @author houqj
 * @date 2020-04-07 17:42
 */
public class WrapperFactory {

    public static <T> QueryWrapper<T> queryWrapper(T t) {
        return new QueryWrapper();
    }

    public static <T> LambdaQueryWrapper<T> lambdaQueryWrapper(T t) {
        return new LambdaQueryWrapper();
    }

    public static <T> UpdateWrapper<T> updateWrapper() {
        return new UpdateWrapper();
    }

    public static <T> LambdaUpdateWrapper<T> lambdaUpdateWrapper() {
        return new LambdaUpdateWrapper();
    }

    public static <T> LambdaQueryChainWrapper<T> lambdaQueryChainWrapper(BaseMapper<T> baseMapper){
        return new LambdaQueryChainWrapper<T>(baseMapper);
    }
    public static <T> LambdaUpdateChainWrapper<T> lambdaUpdateChainWrapper(BaseMapper<T> baseMapper){
        return new LambdaUpdateChainWrapper<T>(baseMapper);
    }

}
