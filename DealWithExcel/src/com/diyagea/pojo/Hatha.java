package com.diyagea.pojo;

import java.util.Date;

public class Hatha {
	private String name;//��Ʒ����
	private String code;//��Ʒ����
	private Date date;
	private String shop;
	private Double totalPrice;//�ܼ�
	private Double cost;//�ܳɱ�
	private Double amount;//����
	private Double turnover;//�ɽ����
	private Double quotedPrice;//����
	
	public Hatha(){
		
	}
	
	public Hatha(String name, String code, Date date, String shop, Double totalPrice, Double cost, Double amount,
			Double turnover, Double quotedPrice) {
		super();
		this.name = name;
		this.code = code;
		this.date = date;
		this.shop = shop;
		this.totalPrice = totalPrice;
		this.cost = cost;
		this.amount = amount;
		this.turnover = turnover;
		this.quotedPrice = quotedPrice;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getShop() {
		return shop;
	}
	public void setShop(String shop) {
		this.shop = shop;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getTurnover() {
		return turnover;
	}
	public void setTurnover(Double turnover) {
		this.turnover = turnover;
	}
	public Double getQuotedPrice() {
		return quotedPrice;
	}
	public void setQuotedPrice(Double quotedPrice) {
		this.quotedPrice = quotedPrice;
	}
	@Override
	public String toString() {
		return "Hatha [name=" + name + ", code=" + code + ", date=" + date + ", shop=" + shop + ", totalPrice="
				+ totalPrice + ", cost=" + cost + ", amount=" + amount + ", turnover=" + turnover + ", quotedPrice="
				+ quotedPrice + "]";
	}

	
}
