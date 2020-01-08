package cn.fast.web.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface BaseMpMapper<T> extends BaseMapper<T> {

    /**
     * 分页查询
     * @param query 分页查询条件
     * @return 分页查询结果
     */
    IPage<T> pageByQuery(IPage<T> query);

    <T> List<T> getAll(T t);

    <T> List<T> findAll();
}
