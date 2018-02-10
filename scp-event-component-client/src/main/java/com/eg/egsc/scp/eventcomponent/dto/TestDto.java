package com.eg.egsc.scp.eventcomponent.dto;

public class TestDto {

    private String appid;
    private String attach;
    private String bank_type;
    private String fee_type;
    private String is_subscribe;
    private String mch_id;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getBank_type() {
        return bank_type;
    }

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public String getIs_subscribe() {
        return is_subscribe;
    }

    public void setIs_subscribe(String is_subscribe) {
        this.is_subscribe = is_subscribe;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    @Override
    public String toString() {
        return "TestDto{" +
                "appid='" + appid + '\'' +
                ", attach='" + attach + '\'' +
                ", bank_type='" + bank_type + '\'' +
                ", fee_type='" + fee_type + '\'' +
                ", is_subscribe='" + is_subscribe + '\'' +
                ", mch_id='" + mch_id + '\'' +
                '}';
    }
}
