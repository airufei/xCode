package com.miuzone.model.scatter;
import com.miuzone.base.model.BaseEntitys;
import java.util.Date;
import java.math.BigDecimal;
 /**
 * 新智投宝订单子表Entity
 * @author airufei
 * @version 2018-08-29
 */
public class ScatterOrderSub extends BaseEntitys {
	
	private static final long serialVersionUID = 1L;
	private String orderNo;		// 订单编号
			
	private Date updateTime;		// 更新时间
			
	private Date createTime;		// 创建时间
			
	private String uuid;		// 用户唯一序列号
			
	private String couponNo;		// 卡券编号
			
	private String couponName;		// 卡券名称
			
	private String couponTheme;		// 卡券主题
			
	private BigDecimal couponAmount;		// COUPON_AMOUNT
			
	private BigDecimal couponApr;		// COUPON_APR
			
	private String couponType;		// 卡券类型：UNDISCOUNT 无卡券、PRINCIPAL_COUPONS现金券、PLUS_COUPONS加息券、RED_PACKET_COUPON 现金红包  
			
	
	
	public ScatterOrderSub() {
		
	}


			
         public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
			
         public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
			
         public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
			
         public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
			
         public String getCouponNo() {
		return couponNo;
	}

	public void setCouponNo(String couponNo) {
		this.couponNo = couponNo;
	}
			
         public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
			
         public String getCouponTheme() {
		return couponTheme;
	}

	public void setCouponTheme(String couponTheme) {
		this.couponTheme = couponTheme;
	}
			
         public BigDecimal getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(BigDecimal couponAmount) {
		this.couponAmount = couponAmount;
	}
			
         public BigDecimal getCouponApr() {
		return couponApr;
	}

	public void setCouponApr(BigDecimal couponApr) {
		this.couponApr = couponApr;
	}
			
         public String getCouponType() {
		return couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}
}