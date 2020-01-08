package cn.fast.admin.model.dto;

import cn.fast.admin.entity.SysAccount;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class AccountDTO extends SysAccount {

    private String[] rids;

    private String roles;

    private String deptName;
}
