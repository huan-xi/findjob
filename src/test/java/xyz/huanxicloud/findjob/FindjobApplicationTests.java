package xyz.huanxicloud.findjob;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.huanxicloud.findjob.common.jwt.JwtTokenUtil;
import xyz.huanxicloud.findjob.pojo.Company;
import xyz.huanxicloud.findjob.pojo.User;
import xyz.huanxicloud.findjob.service.CompanyService;
import xyz.huanxicloud.findjob.service.positionservice.PositionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FindjobApplicationTests {

    @Autowired
    CompanyService companyService;
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
    @Test
    public void contextLoads() {
        for (int i=0;i<30;i++){
            addPosition(i);
            addPosition(i);
        }
    }

    public void addPosition(int i){

    }

    public void addCompany(int i)
    {
        Company company=new Company();
        company.setName("测试公司"+i);
        company.setScale("100-200人"+i);
        company.setTag("上市"+i);
        company.setComDesc("测试描述"+i);
        company.setImages("fdas|dfas|");
        companyService.addCompany(company);
    }
}
