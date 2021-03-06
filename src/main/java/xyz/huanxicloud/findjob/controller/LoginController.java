package xyz.huanxicloud.findjob.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.huanxicloud.findjob.common.Constant;
import xyz.huanxicloud.findjob.common.ReturnMessage;
import xyz.huanxicloud.findjob.common.jwt.JwtUserTokenUtil;
import xyz.huanxicloud.findjob.common.jwt.JwtVenderUtil;
import xyz.huanxicloud.findjob.mapper.UserMapper;
import xyz.huanxicloud.findjob.mapper.VenderMapper;
import xyz.huanxicloud.findjob.pojo.User;
import xyz.huanxicloud.findjob.pojo.Vender;
import xyz.huanxicloud.findjob.util.WXInfo;
import xyz.huanxicloud.findjob.util.WXUtils;

import java.util.Date;

@RestController
@Api(tags = "用户商家登入",description = "微信openId登入")
public class LoginController {

    @Autowired
    UserMapper userMapper;
    @Autowired
    VenderMapper venderMapper;
    @PostMapping("/login")
    @ApiOperation(value = "用户登入",notes="没有用户自动注册")
    public ReturnMessage login(@ApiParam(value = "openId",required = true) String code){
        //用户登入
        WXInfo wxInfo=WXUtils.getWxInfo(code,WXUtils.getUserAppid(),WXUtils.getUserSecret());
        if (wxInfo!=null&& !StringUtils.isEmpty(wxInfo.getOpenid()))
        {
            String token=null;
            User user=userMapper.selectByPrimaryKey(wxInfo.getOpenid());
            if (user==null){
                //未注册，自动注册
                user=new User();
                user.setStatus(Constant.getRoueStatusNormal());
                user.setCreateTime(new Date().getTime());
                user.setUserId(wxInfo.getOpenid());
                user.setValid(Constant.getVailidWaite());
                if (userMapper.insert(user)>0){
                    //自动注册成功
                   token= JwtUserTokenUtil.generateToken(user);
                }else {
                    return new ReturnMessage(10,"自动注册失败");
                }
            }else {
                //已经注册
                if (user.getStatus().equals(Constant.getRoueStatusForbid()))
                    return new ReturnMessage(4004,"您的账号已被禁用");
                token= JwtUserTokenUtil.generateToken(user);
            }
            return new ReturnMessage(1,token);
        }
        //如果可用生产Token
        return new ReturnMessage(0,"登入失败");
    }
    @PostMapping("/venderlogin")
    public ReturnMessage venderLogin(String code){
        //用户登入
        WXInfo wxInfo=WXUtils.getWxInfo(code,WXUtils.getVenderAppid(),WXUtils.getVenderSecret());
        if (wxInfo!=null&& !StringUtils.isEmpty(wxInfo.getOpenid()))
        {
            String token=null;
            Vender vender= venderMapper.selectByPrimaryKey(wxInfo.getOpenid());
            if (vender==null){
                //未注册，自动注册
                vender=new Vender();
                vender.setStatus(Constant.getRoueStatusNormal());
                vender.setValid(Constant.getVailidWaite());
                vender.setCreateTime(new Date().getTime());
                vender.setVenderId(wxInfo.getOpenid());
                if (venderMapper.insert(vender)>0){
                    //自动注册成功
                    token= JwtVenderUtil.generateToken(vender);
                }else {
                    return new ReturnMessage(10,"自动注册失败");
                }
            }else {
                //已经注册
                if (vender.getStatus().equals(Constant.getRoueStatusForbid()))
                    return new ReturnMessage(4005,"您的账号已被禁用");
                token= JwtVenderUtil.generateToken(vender);
            }
            return new ReturnMessage(1,token);
        }
        //如果可用生产Token
        return new ReturnMessage(0,"登入失败");
    }
}
