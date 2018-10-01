package xyz.huanxicloud.findjob.common.jwt;


/**
 * 安全用户模型
 *
 * @author hackyo
 * Created on 2017/12/8 9:20.
 */
public class JwtUser{

    private String username;
    private String password;

   public JwtUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
