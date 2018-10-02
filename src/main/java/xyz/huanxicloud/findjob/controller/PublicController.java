package xyz.huanxicloud.findjob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.huanxicloud.findjob.common.ReturnMessage;
import xyz.huanxicloud.findjob.service.positionservice.PositionService;
import xyz.huanxicloud.findjob.service.systemservice.SystemService;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    PositionService positionService;
    @Autowired
    SystemService systemService;
    @GetMapping("/getPositions")
    public ReturnMessage getPositions(int page, int size){
        return positionService.getPositions(page,size);
    }
    @GetMapping("/getPosition")
    public ReturnMessage getPositions(int id){
        return positionService.getPosition(id);
    }
    @GetMapping("/getTypes")
    public ReturnMessage getTypes(){
        return systemService.getAllWT();
    }
}
