package xyz.huanxicloud.findjob.common;

public class Constant {
    private static final String USER_JET_SERCRET= "micherhimmeCteqdi0";
    private static final String VENDER_JWT_SERCRET= "micherhimVerderqdi0";
    //角色状态
    public final static String ROUE_STATUS_NORMAL="1";
    public final static String ROUE_STATUS_FORBID="2";
    //职位状态
    public final static String POSITION_STATUS_OK="1";
    public final static String POSITION_STATUS_NO="2";
    //验证常量
    public final static String VAILID_WAITE="1";
    public final static String VAILID_PASS="2";
    public final static String VAILID_NOPASS="3";
    //订单常量
    private static final String ODER_STATUS_WAITE="1";
    private static final String ODER_STATUS_AGREE="2";
    private static final String ODER_STATUS_FINISH="3";
    private static final String ODER_STATUS_CANCEL="4";
    private static final String ODER_STATUS_DELETE="5";
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

    public static String getVailidWaite() {
        return VAILID_WAITE;
    }

    public static String getVailidPass() {
        return VAILID_PASS;
    }

    public static String getVailidNopass() {
        return VAILID_NOPASS;
    }

    public static String getPositionStatusOk() {
        return POSITION_STATUS_OK;
    }

    public static String getPositionStatusNo() {
        return POSITION_STATUS_NO;
    }

    public static String getLog_USER_VENDER() {
        return Log_USER_VENDER;
    }

    public static String getLog_USER_ADMIN() {
        return Log_USER_ADMIN;
    }

    public static String getOderStatusDelete() {
        return ODER_STATUS_DELETE;
    }

    public static String getRoueStatusNormal() {
        return ROUE_STATUS_NORMAL;
    }

    public static String getRoueStatusForbid() {
        return ROUE_STATUS_FORBID;
    }

    public static String getLog_ORPRATE_CANCEL_ORDER() {
        return Log_ORPRATE_CANCEL_ORDER;
    }
}
