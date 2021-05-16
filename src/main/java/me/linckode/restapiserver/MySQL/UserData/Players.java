package me.linckode.restapiserver.MySQL.UserData;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Players {

    @Id
    private String uid;
    private String name;
    private double x;
    private double y;
    private double z;
    private String worldName;
    private String lastIp;

    public String getUId() {
        return uid;
    }

    public void setUId(String uId) {
        this.uid = uId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public String getWorldName() {
        return worldName;
    }

    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }
}
