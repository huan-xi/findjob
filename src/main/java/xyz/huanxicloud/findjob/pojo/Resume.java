package xyz.huanxicloud.findjob.pojo;

public class Resume {
    private Integer resumeId;

    private Integer userId;

    private String name;

    private String sex;

    private Long birthday;

    private String city;

    private String timeType;

    private String exCareer;

    private String exIndustry;

    private Integer exSalaryMin;

    private Integer exSalaryMax;

    private String phone;

    private String email;

    public Integer getResumeId() {
        return resumeId;
    }

    public void setResumeId(Integer resumeId) {
        this.resumeId = resumeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType == null ? null : timeType.trim();
    }

    public String getExCareer() {
        return exCareer;
    }

    public void setExCareer(String exCareer) {
        this.exCareer = exCareer == null ? null : exCareer.trim();
    }

    public String getExIndustry() {
        return exIndustry;
    }

    public void setExIndustry(String exIndustry) {
        this.exIndustry = exIndustry == null ? null : exIndustry.trim();
    }

    public Integer getExSalaryMin() {
        return exSalaryMin;
    }

    public void setExSalaryMin(Integer exSalaryMin) {
        this.exSalaryMin = exSalaryMin;
    }

    public Integer getExSalaryMax() {
        return exSalaryMax;
    }

    public void setExSalaryMax(Integer exSalaryMax) {
        this.exSalaryMax = exSalaryMax;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
}