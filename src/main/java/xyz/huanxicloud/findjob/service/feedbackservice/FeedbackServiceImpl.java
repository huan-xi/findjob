package xyz.huanxicloud.findjob.service.feedbackservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.huanxicloud.findjob.common.Constant;
import xyz.huanxicloud.findjob.common.ReturnMessage;
import xyz.huanxicloud.findjob.mapper.FeedbackMapper;
import xyz.huanxicloud.findjob.pojo.Feedback;

import java.util.Date;

@Service
public class FeedbackServiceImpl implements FeedbackService{
    @Autowired
    FeedbackMapper feedbackMapper;
    @Override
    public ReturnMessage addFeedback(String text, String contact) {
        Feedback feedback=new Feedback();
        feedback.setContact(contact);
        feedback.setCreateTime(new Date().getTime());
        feedback.setText(text);
        feedback.setStatus(Constant.getFeedbackStatusOk());
        if (feedbackMapper.insert(feedback)>0)
        return new ReturnMessage(1,"提交成功");
        return new ReturnMessage(0,"提交失败");
    }
}
