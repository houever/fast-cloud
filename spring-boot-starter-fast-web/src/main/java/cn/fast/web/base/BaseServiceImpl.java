package cn.fast.web.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class BaseServiceImpl<K extends BaseMpMapper<T>, T> extends ServiceImpl<K , T> implements BaseService<T>{

    @Autowired
    protected K basempMapper;

    /**
     * 分页条件查询
     *
     * @param query
     * @return
     */
    @Override
    public IPage<T> pageByQuery(IPage<T> query) {
        return basempMapper.pageByQuery(query);
    }

    @Override
    public List<T> getAll(T t) {
        return basempMapper.getAll(t);
    }

    @Override
    public List<T> findAll() {
        return basempMapper.findAll();
    }

}
