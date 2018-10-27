package xyz.huanxicloud.findjob.service.feedbackservice;

import xyz.huanxicloud.findjob.common.ReturnMessage;

public interface FeedbackService {
    public ReturnMessage addFeedback(String text, String contact);
}
