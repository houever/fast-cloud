package cn.fast.admin.model.form;

import cn.fast.web.base.BaseForm;
import cn.fast.admin.entity.SysAccount;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AccountForm extends BaseForm<SysAccount> {

    private String id;
    @NotNull
    private String username;
    private String password;

}
