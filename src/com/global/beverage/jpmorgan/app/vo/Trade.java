package com.global.beverage.jpmorgan.app.vo;

import java.sql.Timestamp;

public class Trade {

	/**
	 * @author INGDGC
	 *
	 */
	
	private Timestamp timeStamp;
	private Double quantityShares;
	private String typeTrade; //(BUY - SELL)
	private Double price;
	
	
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
	public Double getQuantityShares() {
		return quantityShares;
	}
	public void setQuantityShares(Double quantityShares) {
		this.quantityShares = quantityShares;
	}
	public String getTypeTrade() {
		return typeTrade;
	}
	public void setTypeTrade(String typeTrade) {
		this.typeTrade = typeTrade;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	
	
}
