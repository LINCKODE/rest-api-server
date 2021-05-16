package me.linckode.restapiserver.MySQL.Auth.Tables;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Credentials {
    @Id
    private String userId;
    private String privateToken;
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPrivateToken() {
        return privateToken;
    }

    public void setPrivateToken(String privateToken) {
        this.privateToken = privateToken;
    }
}
