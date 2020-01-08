package cn.fast.admin.model.dto;

import cn.fast.admin.entity.SysRoles;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RoleDTO extends SysRoles {

    private String pids;

    private String depts;
}
