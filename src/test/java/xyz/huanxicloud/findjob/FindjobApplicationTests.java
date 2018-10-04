package xyz.huanxicloud.findjob;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.huanxicloud.findjob.mapper.POrderMapper;
import xyz.huanxicloud.findjob.mapper.VenderMapper;
import xyz.huanxicloud.findjob.pojo.Position;
import xyz.huanxicloud.findjob.pojo.Vender;
import xyz.huanxicloud.findjob.service.positionservice.PositionService;
import xyz.huanxicloud.findjob.service.venderservice.VenderService;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FindjobApplicationTests {
    @Autowired
    PositionService positionService;
    @Autowired
    VenderService venderService;
    @Autowired
    VenderMapper venderMapper;
    @Autowired
    POrderMapper pOrderMapper;
    @Test
    public void edit(){
        Vender vender=new Vender() ;
        vender.setName("fa");
        vender.setVenderId("oFKkr5LVQKNVVZoisHmEHyiupcBk");
//        System.out.println(venderMapper.updateByPrimaryKey(vender));
       venderService.editVender("oFKkr5LVQKNVVZoisHmEHyiupcBk", vender);
    }
    @Test
    public void add(){
        for (int i=0;i<5;i++){
            test(i);
        }
    }
    public void test(int i){
        Position position=new Position();
        position.sethCount(i);
        position.setPositionDesc("fdasf"+i);
        position.setType("fdsa"+i);
        position.setTime(new Date().getTime());
        positionService.publicPosition("gdfsdfg","fda",position);
    }
    @Test
    public void  findorder(){
    }
}
