package xyz.huanxicloud.findjob.pojo;

import java.io.Serializable;

public class ServiceInfo implements Serializable {
    private String phone;
    private String name;

    public ServiceInfo(String phone, String name) {
        this.phone = phone;
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
