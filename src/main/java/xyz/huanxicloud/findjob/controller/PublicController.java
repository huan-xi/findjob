package xyz.huanxicloud.findjob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.huanxicloud.findjob.common.ReturnMessage;
import xyz.huanxicloud.findjob.service.feedbackservice.FeedbackService;
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
    @Autowired
    FeedbackService feedbackService;
    /**
     * 获取公司信息
     * @return
     */
    @GetMapping("/getVenderInfo")
    public ReturnMessage getVenderInfo(String id){
        return venderService.getInfo(id);
    }
    //浏览
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
    @GetMapping("/search")
    public ReturnMessage searchPositions(int page, int size,String key){
        return positionService.search(page,size,key);
    }
    //获取客服信息
    @GetMapping("/getServiceInfo")
    public ReturnMessage getServiceInfo(){
        return systemService.getServiceInfo();
    }
    //反馈
    @PostMapping("/feedback")
    public ReturnMessage feedback(String text,String contact){
        return feedbackService.addFeedback(text,contact);
    }
    //是否存活
    @GetMapping("/isAlive")
    public String isAlive(){
        return "1";
    }
}
