package xyz.huanxicloud.findjob;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.huanxicloud.findjob.common.jwt.JwtTokenUtil;
import xyz.huanxicloud.findjob.pojo.User;
import xyz.huanxicloud.findjob.service.positionservice.PositionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FindjobApplicationTests {

    @Autowired
    PositionService positionService;
    @Test
    public void testJwt(){
        User user=new User();
        user.setType("1");
        user.setUserId(0);
        user.setOpenId("0");
        user.setStatus("1");
        user.setCreateTime(23434L);
        String token=JwtTokenUtil.generateToken(user);
        User users=JwtTokenUtil.getUserFormToken("fdasss");
        System.out.println(users);
    }

}
