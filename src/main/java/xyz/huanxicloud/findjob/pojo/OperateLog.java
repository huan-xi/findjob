package xyz.huanxicloud.findjob.pojo;

public class OperateLog {
    private Long opId;

    private String type;

    private String lValue;

    private Long createTime;

    private String operator;

    private String operatorType;

    public Long getOpId() {
        return opId;
    }

    public void setOpId(Long opId) {
        this.opId = opId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getlValue() {
        return lValue;
    }

    public void setlValue(String lValue) {
        this.lValue = lValue == null ? null : lValue.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType == null ? null : operatorType.trim();
    }
}