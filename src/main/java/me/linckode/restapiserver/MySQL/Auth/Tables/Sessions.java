package me.linckode.restapiserver.MySQL.Auth.Tables;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Sessions {

    @Id
    private String accessToken;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
