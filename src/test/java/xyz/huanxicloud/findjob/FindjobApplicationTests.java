package xyz.huanxicloud.findjob;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.huanxicloud.findjob.common.Constant;
import xyz.huanxicloud.findjob.mapper.*;
import xyz.huanxicloud.findjob.pojo.*;
import xyz.huanxicloud.findjob.service.positionservice.PositionService;
import xyz.huanxicloud.findjob.service.venderservice.VenderService;

import java.lang.System;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FindjobApplicationTests {
    @Autowired
    PositionService positionService;
    @Autowired
    VenderService venderService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    VenderMapper venderMapper;
    @Autowired
    POrderMapper pOrderMapper;
    @Autowired
    PositionMapper positionMapper;
    @Autowired
    OperateLogMapper operateLogMapper;
    @Test
    public void testorder(){
     Order order= pOrderMapper.selectOrderByPrimaryKey(74);
        System.out.println("test");
    }
    @Test
    public void deleteLog(){
        operateLogMapper.deleteByExample(new OperateLogExample());
        positionMapper.deleteByExample(new PositionExample());
    }
    @Test
    public void deleteorder(){
        pOrderMapper.deleteByExample(new POrderExample());
        positionMapper.deleteByExample(new PositionExample());
    }
    public void delete(){
        userMapper.deleteByExample(new UserExample());
        venderMapper.deleteByExample(new VenderExample());
    }
    @Test
    public void addOrder(){
        for (int i=0;i<25;i++){
            POrder pOrder=new POrder();
            pOrder.setStatus(Constant.getOderStatusWaite());
            //模拟每小时注册一个
            pOrder.setCreateTime(new Date().getTime()-i*1000*60*60);
            pOrder.setPositionId(3);
            pOrder.setUserId("testuser"+i);
            pOrder.setWorkTime(new Date().getTime()-i*1000*60*60);
            pOrderMapper.insert(pOrder);
        }
    }
    @Test
    public void addVender(){
        for (int i=0;i<50;i++){
            Vender vender=new Vender();
            vender.setCreateTime(new Date().getTime()-i*1000*60*60);
            vender.setVenderId("testvender"+i);
            vender.setName("公司"+i);
            vender.setPhone("12345678900");
            vender.setAddress("地址");
            vender.setStatus(Constant.getRoueStatusNormal());
            vender.setValid(Constant.getVailidWaite());
            vender.setContacts("测试联系人"+i);
            vender.setAddressDesc("地址描述"+i);
            venderMapper.insert(vender);
        }
    }
    @Test
    public void addUser(){
        for (int i=0;i<100;i++){
            User user =new User();
            user.setUserId("testuser"+i);
            user.setName("测试名字"+i);
            //每小时一个
            user.setCreateTime(new Date().getTime()+i*1000*60*60);
            user.setPhone("12345678900");
            user.setValid(Constant.getVailidWaite());
            user.setStatus(Constant.getRoueStatusNormal());
            userMapper.insert(user);
        }
    }

    @Test
    public void edit(){
        Vender vender=new Vender() ;
        vender.setName("fa");
        vender.setVenderId("oFKkr5LVQKNVVZoisHmEHyiupcBk");
//        System.out.println(venderMapper.updateByPrimaryKey(vender));
       venderService.editVender("oFKkr5LVQKNVVZoisHmEHyiupcBk", vender);
    }

    @Test
    public void  findorder(){
    }
}
