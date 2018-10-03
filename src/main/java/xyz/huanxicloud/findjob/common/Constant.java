package xyz.huanxicloud.findjob.common;

public class Constant {
    private static final String USER_JET_SERCRET= "micherhimmeCteqdi0";
    private static final String VENDER_JWT_SERCRET= "micherhimVerderqdi0";
    //订单常量
    private static final String ODER_STATUS_WAITE="1";
    private static final String ODER_STATUS_AGREE="2";
    private static final String ODER_STATUS_FINISH="3";
    private static final String ODER_STATUS_CANCEL="4";
    //日志常量
    private static final String Log_USER_USER="1";
    private static final String Log_USER_VENDER="2";
    private static final String Log_USER_ADMIN="3";
    private static final String Log_ORPRATE_CANCEL_ORDER="1";
    public static String getUserJetSercret() {
        return USER_JET_SERCRET;
    }

    public static String getVenderJwtSercret() {
        return VENDER_JWT_SERCRET;
    }

    public static String getOderStatusWaite() {
        return ODER_STATUS_WAITE;
    }

    public static String getOderStatusAgree() {
        return ODER_STATUS_AGREE;
    }

    public static String getOderStatusFinish() {
        return ODER_STATUS_FINISH;
    }

    public static String getLog_USER_USER() {
        return Log_USER_USER;
    }

    public static String getOderStatusCancel() {
        return ODER_STATUS_CANCEL;
    }

    public static String getLog_USER_VENDER() {
        return Log_USER_VENDER;
    }

    public static String getLog_USER_ADMIN() {
        return Log_USER_ADMIN;
    }

    public static String getLog_ORPRATE_CANCEL_ORDER() {
        return Log_ORPRATE_CANCEL_ORDER;
    }
}
