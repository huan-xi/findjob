package xyz.huanxicloud.findjob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.huanxicloud.findjob.common.ReturnMessage;
import xyz.huanxicloud.findjob.service.positionservice.PositionService;
import xyz.huanxicloud.findjob.service.systemservice.SystemService;
import xyz.huanxicloud.findjob.service.venderservice.VenderService;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    PositionService positionService;
    @Autowired
    SystemService systemService;
    @Autowired
    VenderService venderService;
    /**
     * 获取公司信息
     * @return
     */
    @GetMapping("/getVenderInfo")
    public ReturnMessage getVenderInfo(String id){
        return venderService.getInfo(id);
    }
    @GetMapping("/getPositions")
    public ReturnMessage getPositions(int page, int size){
        return positionService.getPositions(page,size);
    }
    @GetMapping("/getPosition")
    public ReturnMessage getPosition(int id){
        return positionService.getPosition(id);
    }
    @GetMapping("/getTypes")
    public ReturnMessage getTypes(){
        return systemService.getAllWT();
    }
}
