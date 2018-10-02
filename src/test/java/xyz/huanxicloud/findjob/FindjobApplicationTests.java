package xyz.huanxicloud.findjob;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.huanxicloud.findjob.common.ReturnMessage;
import xyz.huanxicloud.findjob.common.jwt.JwtTokenUtil;
import xyz.huanxicloud.findjob.pojo.User;
import xyz.huanxicloud.findjob.service.positionservice.PositionService;
import xyz.huanxicloud.findjob.service.systemservice.SystemService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FindjobApplicationTests {

    @Autowired
    PositionService positionService;
    @Autowired
    SystemService systemService;
    @Test
    public void testJwt(){
        User user=new User();
        user.setUserId("4545");
        user.setStatus("1");
        user.setCreateTime(23434L);
        String token=JwtTokenUtil.generateToken(user);
        User users=JwtTokenUtil.getUserFormToken(token);
        System.out.println(users);
    }
    @Test
    public void add(){
        systemService.addWT("两个");
        systemService.addWT("三个子");
        systemService.addWT("三个");
        systemService.addWT("三个发生地");
        systemService.addWT("三发送个");
    }
    @Test
    public void all(){
        ReturnMessage returnMessage = systemService.getAllWT();
        return;
    }

}
