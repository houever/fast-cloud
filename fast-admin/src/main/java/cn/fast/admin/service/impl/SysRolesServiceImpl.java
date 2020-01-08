package cn.fast.admin.service.impl;
import javax.annotation.Resource;
import cn.fast.web.base.BaseServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.fast.admin.model.dto.RoleDTO;
import org.springframework.stereotype.Service;
import cn.fast.admin.repository.SysRolesRepository;
import cn.fast.admin.entity.SysRoles;
import cn.fast.admin.service.ISysRolesService;
import cn.fast.admin.mapper.ISysRolesMapper;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
@Service
public class SysRolesServiceImpl extends BaseServiceImpl<ISysRolesMapper,SysRolesRepository,SysRoles> implements ISysRolesService{

    @Resource
    private ISysRolesMapper SysRolesMapper;

    /**
     * 根据用户id获取角色列表
     *
     * @param id
     * @return
     */
    @Override
    public List<SysRoles> getRolesByAccountId(String id) {
        return SysRolesMapper.getRolesByAccountId(id);
    }

    /**
     * 角色分页列表
     *
     * @param page
     * @return
     */
    @Override
    public IPage<RoleDTO> getPageList(Page<RoleDTO> page) {
        return SysRolesMapper.getPageList(page);
    }

}
