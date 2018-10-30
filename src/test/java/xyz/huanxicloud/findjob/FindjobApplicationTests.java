package xyz.huanxicloud.findjob;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.huanxicloud.findjob.mapper.*;
import xyz.huanxicloud.findjob.service.positionservice.PositionService;
import xyz.huanxicloud.findjob.service.venderservice.VenderService;

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
    @Autowired
    SystemMapper systemMapper;

/*    @Test
    public void contact() {
        SystemExample example = new SystemExample();
        example.createCriteria().andSKeyEqualTo(Constant.getSystemKeyName());
        List<System> systems=systemMapper.selectByExample(example);
        System system=systems.get(0);
        system.setsValue("测试名字");
        systemMapper.updateByPrimaryKey(system);
    }*/
    /*@Test
    public void change() {
        SystemExample example = new SystemExample();
        example.createCriteria().andSKeyEqualTo(Constant.getSystemKeyVenderNotice());
        List<System> es = systemMapper.selectByExample(example);
        System e = es.get(0);
        e.setsValue("工厂通告测试,欢迎使用工厂端");
        systemMapper.updateByPrimaryKey(e);
    }*/
    /*    @Test
    public void add(){
        System system=new System();
        system.setsKey(Constant.getSystemKeyUserNotice());
        system.setsValue("用户公告测试！");
        System system2=new System();
        system2.setsKey(Constant.getSystemKeyVenderNotice());
        system2.setsValue("工厂公告测试！");
        systemMapper.insert(system);
        systemMapper.insert(system2);
    }*/

}
