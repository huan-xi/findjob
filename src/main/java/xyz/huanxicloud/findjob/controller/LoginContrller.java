package xyz.huanxicloud.findjob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.huanxicloud.findjob.common.ReturnMessage;
import xyz.huanxicloud.findjob.common.jwt.JwtTokenUtil;
import xyz.huanxicloud.findjob.mapper.UserMapper;
import xyz.huanxicloud.findjob.pojo.User;
import xyz.huanxicloud.findjob.pojo.UserExample;
import xyz.huanxicloud.findjob.util.WXInfo;
import xyz.huanxicloud.findjob.util.WXUtils;

import java.util.Date;
import java.util.List;

@RestController
public class LoginContrller {
    public final static String TYPE_USER="1";
    public final static String TYPE_FACTURER="2";
    public final static String TYPE_ADMIN="3";
    public final static String STATUS_NORMAL="1";
    public final static String STATUS_FORBID="2";
    public final static String STATUS_WAITER="3";
    @Autowired
    UserMapper userMapper;
    @GetMapping("/login")
    public ReturnMessage login(String code){
        //用户登入
        WXInfo wxInfo=WXUtils.getWxInfo(code);
        if (wxInfo!=null&& !StringUtils.isEmpty(wxInfo.getOpenid()))
        {
            String token=null;
            UserExample example=new UserExample();
            example.createCriteria().andOpenIdEqualTo(wxInfo.getOpenid()).andTypeEqualTo(TYPE_USER);
            List<User> users=userMapper.selectByExample(example);
            if (users.size()==0){
                //未注册，自动注册
                User user=new User();
                user.setStatus(STATUS_NORMAL);
                user.setCreateTime(new Date().getTime());
                user.setOpenId(wxInfo.getOpenid());
                user.setType(TYPE_USER);
                if (userMapper.insert(user)>0){
                    //自动注册成功
                   token=JwtTokenUtil.generateToken(user);
                }else {
                    return new ReturnMessage(0,"自动注册失败");
                }
            }else {
                //已经登入
                token=JwtTokenUtil.generateToken(users.get(0));
            }
            return new ReturnMessage(1,token);
        }
        //如果可用生产Token
        return new ReturnMessage(0,"登入失败");
    }
    @PostMapping("/adminLogin")
    public ReturnMessage adminLogin(String username,String password,String captcha){
        if (username.equals("admin")&&password.equals("test")){
            User user=new User();
            user.setType(TYPE_ADMIN);
            return new ReturnMessage(1,JwtTokenUtil.generateToken(user));
        }
        return new ReturnMessage(0,"登入失败！");
    }
}
