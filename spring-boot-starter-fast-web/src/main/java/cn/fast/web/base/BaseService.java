package cn.fast.web.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface BaseService <T> extends IService<T> {
    /**
     * 分页条件查询
     * @param query
     * @return
     */
    IPage<T> pageByQuery(IPage<T> query);

    List<T> getAll(T t);

    List<T> findAll();
}
