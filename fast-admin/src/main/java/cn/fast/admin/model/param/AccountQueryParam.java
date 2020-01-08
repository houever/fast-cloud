package cn.fast.admin.model.param;

import cn.fast.web.base.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author houqj
 * @date 2016/10/31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountQueryParam extends BaseParam {
    private String username;
    private String phone;
    private String email;
    private Integer gender;
    private String deptName;
}
