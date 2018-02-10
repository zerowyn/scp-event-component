package com.eg.egsc.scp.eventcomponent.dto;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 *
 * TODO 订单查询返回实体Dto
 *
 * @author fandong
 * @since 2018年2月7日
 */
public class QueryOrderResultDto {
    @NotNull(message = "platform must not null")
    private String platform;// 支付平台 是
    @NotNull(message = "return_code must not null")
    private String return_code;// 返回状态码 是
    private String return_msg;// 返回信息 否

    // 以下字段在return_code为SUCCESS的时候有返回
    @NotNull(message = "appid must not null")
    private String appid;// 应用APPID
    @NotNull(message = "mch_id must not null")
    private String mch_id;// 商户号
    @NotNull(message = "result_code must not null")
    private String result_code;// 业务结果
    private String err_code;// 错误代码
    private String err_code_des;// 错误代码描述

    // 以下字段在return_code 和result_code都为SUCCES时候有返回
    private String device_info;// 设备号 否
    @NotNull(message = "openid must not null")
    private String openid;// 用户标识 是
    private String is_subscribe;// 是否关注公众账号 否
    @NotNull(message = "trade_type must not null")
    private String trade_type;// 交易类型 是
    @NotNull(message = "trade_state must not null")
    private String trade_state;// 交易状态 是
    @NotNull(message = "bank_type must not null")
    private String bank_type;// 付款银行 是
    @NotNull(message = "total_fee must not null")
    private Integer total_fee;// 总金额 是
    private String fee_type;// 货币种类 否
    @NotNull(message = "cash_fee must not null")
    private Integer cash_fee;// 现金支付金额 是
    private String cash_fee_type;// 现金支付货币类型 否
    private Integer settlement_total_fee;// 应结订单金额 否
    private List<Integer> coupon_fee;// 代金券金额 否
    private Integer coupon_count;// 代金券使用数量 否
    private String coupon_id_$n;// 代金券ID 否
    private String coupon_type_$n;// 代金券类型 否
    private Integer coupon_fee_$n;// 单个代金券支付金额 否
    @NotNull(message = "transaction_id must not null")
    private String transaction_id;// 微信支付订单号 是
    @NotNull(message = "out_trade_no must not null")
    private String out_trade_no;// 商户订单号 是
    private String attach;// 附加数据 否
    @NotNull(message = "time_end must not null")
    private String time_end;// 支付完成时间 是
    @NotNull(message = "trade_state_desc must not null")
    private String trade_state_desc;// 交易状态描述 是

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getIs_subscribe() {
        return is_subscribe;
    }

    public void setIs_subscribe(String is_subscribe) {
        this.is_subscribe = is_subscribe;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getTrade_state() {
        return trade_state;
    }

    public void setTrade_state(String trade_state) {
        this.trade_state = trade_state;
    }

    public String getBank_type() {
        return bank_type;
    }

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }

    public Integer getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public Integer getCash_fee() {
        return cash_fee;
    }

    public void setCash_fee(Integer cash_fee) {
        this.cash_fee = cash_fee;
    }

    public String getCash_fee_type() {
        return cash_fee_type;
    }

    public void setCash_fee_type(String cash_fee_type) {
        this.cash_fee_type = cash_fee_type;
    }

    public Integer getSettlement_total_fee() {
        return settlement_total_fee;
    }

    public void setSettlement_total_fee(Integer settlement_total_fee) {
        this.settlement_total_fee = settlement_total_fee;
    }


    public List<Integer> getCoupon_fee() {
        return coupon_fee;
    }

    public void setCoupon_fee(List<Integer> coupon_fee) {
        this.coupon_fee = coupon_fee;
    }

    public Integer getCoupon_count() {
        return coupon_count;
    }

    public void setCoupon_count(Integer coupon_count) {
        this.coupon_count = coupon_count;
    }

    public String getCoupon_id_$n() {
        return coupon_id_$n;
    }

    public void setCoupon_id_$n(String coupon_id_$n) {
        this.coupon_id_$n = coupon_id_$n;
    }

    public String getCoupon_type_$n() {
        return coupon_type_$n;
    }

    public void setCoupon_type_$n(String coupon_type_$n) {
        this.coupon_type_$n = coupon_type_$n;
    }

    public Integer getCoupon_fee_$n() {
        return coupon_fee_$n;
    }

    public void setCoupon_fee_$n(Integer coupon_fee_$n) {
        this.coupon_fee_$n = coupon_fee_$n;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public String getTrade_state_desc() {
        return trade_state_desc;
    }

    public void setTrade_state_desc(String trade_state_desc) {
        this.trade_state_desc = trade_state_desc;
    }

    @Override
    public String toString() {
        return "QueryOrderResultDto{" +
                "platform='" + platform + '\'' +
                ", return_code='" + return_code + '\'' +
                ", return_msg='" + return_msg + '\'' +
                ", appid='" + appid + '\'' +
                ", mch_id='" + mch_id + '\'' +
                ", result_code='" + result_code + '\'' +
                ", err_code='" + err_code + '\'' +
                ", err_code_des='" + err_code_des + '\'' +
                ", device_info='" + device_info + '\'' +
                ", openid='" + openid + '\'' +
                ", is_subscribe='" + is_subscribe + '\'' +
                ", trade_type='" + trade_type + '\'' +
                ", trade_state='" + trade_state + '\'' +
                ", bank_type='" + bank_type + '\'' +
                ", total_fee=" + total_fee +
                ", fee_type='" + fee_type + '\'' +
                ", cash_fee=" + cash_fee +
                ", cash_fee_type='" + cash_fee_type + '\'' +
                ", settlement_total_fee=" + settlement_total_fee +
                ", coupon_fee=" + coupon_fee +
                ", coupon_count=" + coupon_count +
                ", coupon_id_$n='" + coupon_id_$n + '\'' +
                ", coupon_type_$n='" + coupon_type_$n + '\'' +
                ", coupon_fee_$n=" + coupon_fee_$n +
                ", transaction_id='" + transaction_id + '\'' +
                ", out_trade_no='" + out_trade_no + '\'' +
                ", attach='" + attach + '\'' +
                ", time_end='" + time_end + '\'' +
                ", trade_state_desc='" + trade_state_desc + '\'' +
                '}';
    }
}
