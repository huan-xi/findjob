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

}
