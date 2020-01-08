package cn.fast.gateway.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;


@Data
public class LoginParam {

    @NotBlank(message="用户名不能为空")
    String username;
    @NotBlank(message="密码不能为空")
    String password;
}
