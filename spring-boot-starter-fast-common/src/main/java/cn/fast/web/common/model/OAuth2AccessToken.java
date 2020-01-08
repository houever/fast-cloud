package cn.fast.web.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OAuth2AccessToken implements Serializable {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private long expires_in;
    private String id;
    private String scope;
    private String tenantId;
    private String license;
    private String deptId;
    private String username;
    private String error;
    private String message;
    private List<String> roles;
    private List<String> btns;

    public OAuth2AccessToken() {
    }

    public OAuth2AccessToken(String access_token, String token_type, String refresh_token, long expires_in, String scope, String tenantId, String license, String deptId, String id, String username, String error, String message, List<String> roles, List<String> btns) {
        this.access_token = access_token;
        this.token_type = token_type;
        this.refresh_token = refresh_token;
        this.expires_in = expires_in;
        this.scope = scope;
        this.tenantId = tenantId;
        this.license = license;
        this.deptId = deptId;
        this.id = id;
        this.username = username;
        this.error = error;
        this.message = message;
        this.roles = roles;
        this.btns = btns;
    }
}
