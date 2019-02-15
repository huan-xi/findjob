package xyz.huanxicloud.findjob.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

import java.util.Date;
@Api(tags = "公开接口", description = "所有角色可调用")
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
    @ApiOperation(value = "获取指定公司信息")
    @GetMapping("/getVenderInfo")
    public ReturnMessage getVenderInfo(String id){
        return venderService.getInfo(id);
    }
    //浏览
    @ApiOperation(value = "获取职位信息",notes = "每次获取不得超过20条")
    @GetMapping("/getPositions")
    public ReturnMessage getPositions(@ApiParam(value = "页码") int page,@ApiParam(value = "没有数量") int size){
        return positionService.getPositions(page,size);
    }
    @ApiOperation(value = "获取指定职位信息")
    @GetMapping("/getPosition")
    public ReturnMessage getPosition(@ApiParam(value = "职位id") int id){
        return positionService.getPosition(id);
    }
    @ApiOperation(value = "获取所有工总")
    @GetMapping("/getTypes")
    public ReturnMessage getTypes(){
        return systemService.getAllWT();
    }
    @ApiOperation(value = "搜索职位信息",notes = "按工种名")
    @GetMapping("/search")
    public ReturnMessage searchPositions(int page, int size,@ApiParam(value = "搜索关键字") String key){
        return positionService.search(page,size, key);
    }
    //获取客服信息
    @ApiOperation(value = "获取客服信息")
    @GetMapping("/getServiceInfo")
    public ReturnMessage getServiceInfo(){
        return systemService.getServiceInfo();
    }
    //反馈
    @ApiOperation(value = "提交反馈") //TODO 加反馈类型
    @PostMapping("/feedback")
    public ReturnMessage feedback(String text,String contact){
        return feedbackService.addFeedback(text,contact);
    }
    //是否存活
    @ApiOperation(value = "运行状态",notes = "正常返回1")
    @GetMapping("/isAlive")
    public String isAlive(){
        return "1";
    }
    //获取公告
    @ApiOperation(value = "获取商家公告")
    @GetMapping("/getVenderNotice")
    public ReturnMessage getVenderNotice() {
        return systemService.getVenderNotice();
    }
    //获取用户公告
    @ApiOperation(value = "获取用户公告")
    @GetMapping("/getUserNotice")
    public ReturnMessage getUserNotice(){
        return systemService.getUserNotice();
    }
    @ApiOperation(value = "取服务器时间戳")
    @GetMapping("/getTime")
    public long getTime(){
        return new Date().getTime();
    }
}
