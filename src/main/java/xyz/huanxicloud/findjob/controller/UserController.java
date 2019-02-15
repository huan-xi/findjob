package xyz.huanxicloud.findjob.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.huanxicloud.findjob.common.ReturnMessage;
import xyz.huanxicloud.findjob.common.jwt.JwtUserTokenUtil;
import xyz.huanxicloud.findjob.pojo.User;
import xyz.huanxicloud.findjob.service.systemservice.SystemService;
import xyz.huanxicloud.findjob.service.userservice.UserService;
import xyz.huanxicloud.findjob.util.AliOSSUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Api(tags = "用户接口", description = "用户登入后可调用")
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    SystemService systemService;

    @GetMapping("test")
    public String test(@RequestHeader("Token") String token) {
        return getUser(token).getUserId();
    }

    /**
     * 获取当前用户信息
     *
     * @param
     * @return
     */
    @ApiOperation(value = "获取当前用户信息")
    @GetMapping("/getInfo")
    public ReturnMessage getInfo(@RequestHeader("Token") String token) {
        String id = JwtUserTokenUtil.getUserIdFromToken(token);
        return userService.getUserInfo(id);
    }

    @ApiOperation(value = "获取当前用户所有订单")
    @GetMapping("/getOrders")
    public ReturnMessage getOrders(@RequestHeader("Token") String token, int page, int size) {
        String id = JwtUserTokenUtil.getUserIdFromToken(token);
        return userService.getOrders(id, page, size);
    }

    //接单
    @ApiOperation(value = "用户接单",notes = "")
    @PostMapping("/orderPosition")
    public ReturnMessage orderPosition(@RequestHeader("Token") String token, int id) throws Exception {
        String userId = JwtUserTokenUtil.getUserIdFromToken(token);
        return userService.orderPosition(userId, id);
    }

    @GetMapping("/cancelOrder")
    public ReturnMessage cancelOrder(@RequestHeader("Token") String token, int orderId) {
        if (StringUtils.isEmpty(orderId)) return new ReturnMessage(100, "请求异常");
        String userId = JwtUserTokenUtil.getUserIdFromToken(token);
        return userService.cancelOrder(userId, orderId);
    }

    @GetMapping("/deleteOrder")
    public ReturnMessage deleteOrder(@RequestHeader("Token") String token, int orderId) {
        if (StringUtils.isEmpty(orderId)) return new ReturnMessage(100, "请求异常");
        String userId = JwtUserTokenUtil.getUserIdFromToken(token);
        return userService.deleteOrders(userId, orderId);
    }

    @GetMapping("/finishOrder")
    public ReturnMessage finishOrder(@RequestHeader("Token") String token, int orderId) {
        if (StringUtils.isEmpty(orderId)) return new ReturnMessage(100, "请求异常");
        String userId = JwtUserTokenUtil.getUserIdFromToken(token);
        return userService.finishOrders(userId, orderId);
    }

    private String getUserId(String token) {
        return JwtUserTokenUtil.getUserIdFromToken(token);
    }

    @GetMapping("/deleteOrders")
    public ReturnMessage deleteOrders(@RequestHeader("Token") String token, int orderId) {
        return userService.deleteOrders(getUserId(token), orderId);
    }

    /**
     * 编辑信息
     *
     * @return
     */
    @PostMapping("/editInfo")
    public ReturnMessage editInfo(@RequestHeader("Token") String token,String imagesrc, String phone, String name, String types) {
        String id = JwtUserTokenUtil.getUserIdFromToken(token);
        return userService.editInfo(id,imagesrc, phone, name, types);
    }

    private User getUser(String token) {
        String id = JwtUserTokenUtil.getUserIdFromToken(token);
        return userService.findUserById(id);
    }
}
