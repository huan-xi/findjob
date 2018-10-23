package xyz.huanxicloud.findjob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.huanxicloud.findjob.common.ReturnMessage;
import xyz.huanxicloud.findjob.common.jwt.JwtVenderUtil;
import xyz.huanxicloud.findjob.pojo.Position;
import xyz.huanxicloud.findjob.pojo.Vender;
import xyz.huanxicloud.findjob.service.positionservice.PositionService;
import xyz.huanxicloud.findjob.service.venderservice.VenderService;
import xyz.huanxicloud.findjob.util.AliOSSUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/vender")
public class VenderController {
    @Autowired
    PositionService positionService;
    @Autowired
    VenderService venderService;

    @PostMapping("/editVender")
    public ReturnMessage editVender(@RequestHeader("Token") String token, Vender vender) {
        String id = JwtVenderUtil.getVenderIdFromToken(token);
        return venderService.editVender(id, vender);
    }

    @GetMapping("/getInfo")
    public ReturnMessage getInfo(@RequestHeader("Token") String token) {
        String id = JwtVenderUtil.getVenderIdFromToken(token);
        return venderService.getInfo(id);
    }

    @GetMapping("/getOrders")
    public ReturnMessage getOrders(@RequestHeader("Token") String token, int page, int size,int type) {
        String id = JwtVenderUtil.getVenderIdFromToken(token);
        return venderService.getOrders(id, page, size,type);
    }

    /**
     * 获取已发布的职位
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/getPositions")
    public ReturnMessage getPositions(@RequestHeader("Token") String token, int page, int size,int type) {
        String id = JwtVenderUtil.getVenderIdFromToken(token);
        return positionService.getPositionsByVender(id, page, size,type);
    }
    @GetMapping("/deletePosition")
    public ReturnMessage deletePositions(@RequestHeader("Token") String token, int positionId){
        String id = JwtVenderUtil.getVenderIdFromToken(token);
        return positionService.deletePosition(id, positionId);
    }
    @GetMapping("/deleteOrder")
    public ReturnMessage deleteOrder(@RequestHeader("Token") String token, int orderId){
        String id = JwtVenderUtil.getVenderIdFromToken(token);
        return positionService.deleteOrderByVender(id, orderId);
    }
    @PostMapping("/publicPosition")
    public ReturnMessage publicPosition(@RequestHeader("Token") String token, Position position) {
        String id = JwtVenderUtil.getVenderIdFromToken(token);
        return positionService.publicPosition(id, position);
    }

    @PostMapping("/editPosition")
    public ReturnMessage editPosition(@RequestHeader("Token") String token, Position position) {
        String vender = JwtVenderUtil.getVenderIdFromToken(token);
        if (position.getPositionId() == null)
            return new ReturnMessage(0, "提交异常");
        return positionService.editPosition(vender, position);
    }
    @GetMapping("/cancelOrder")
    public ReturnMessage cancelOrder(@RequestHeader("Token") String token,int orderId){
        if (StringUtils.isEmpty(orderId)) return new ReturnMessage(100,"请求异常");
        String venderId= JwtVenderUtil.getVenderIdFromToken(token);
        return venderService.cancelOrder(venderId,orderId);
    }
    /**
     * 上传视频
     *
     * @param video
     * @return
     */
    @PostMapping("/uploadVideo")
    public ReturnMessage uploadVideo(MultipartFile video) {
        // return new ReturnMessage(1, "test");
        String temp = System.getProperty("os.name").contains("indow") ? "f:\\" : "/var/tmp/upload";
        String filename = UUID.randomUUID().toString() + "." + video.getContentType().split("/")[1];
        String ossFile = null;
        try {
            ossFile = AliOSSUtil.uploadLocalFile((FileInputStream) video.getInputStream(), filename, "image/head_img/", temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!StringUtils.isEmpty(ossFile)) {
            return new ReturnMessage(1, ossFile);
        }
        return new ReturnMessage(1000, "上传失败");
    }
}
