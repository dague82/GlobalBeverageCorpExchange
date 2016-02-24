/**
 * 
 */
package com.global.beverage.jpmorgan.app.vo;

import java.util.List;

/**
 * @author INGDGC
 *
 */
public class Stock {
	private String symbol;
	private String type;
	private Double lastDividend;
	private Double fixedDividend;
	private Double parValue;
	private List<Trade> trades;
	private Double tickerPrice;
	private Double dividendYeld;
	private Double pERatio;
	private Double geometricMean;
	private Double stockprice;
	
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getLastDividend() {
		return lastDividend;
	}
	public void setLastDividend(Double lastDividend) {
		this.lastDividend = lastDividend;
	}
	public Double getFixedDividend() {
		return fixedDividend;
	}
	public void setFixedDividend(Double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}
	public Double getParValue() {
		return parValue;
	}
	public void setParValue(Double parValue) {
		this.parValue = parValue;
	}
	public List<Trade> getTrades() {
		return trades;
	}
	public void setTrades(List<Trade> trades) {
		this.trades = trades;
	}
	public Double getTickerPrice() {
		return tickerPrice;
	}
	public void setTickerPrice(Double tickerPrice) {
		this.tickerPrice = tickerPrice;
	}
	public Double getDividendYeld() {
		return dividendYeld;
	}
	public void setDividendYeld(Double dividendYeld) {
		this.dividendYeld = dividendYeld;
	}
	public Double getpERatio() {
		return pERatio;
	}
	public void setpERatio(Double pERatio) {
		this.pERatio = pERatio;
	}
	public Double getGeometricMean() {
		return geometricMean;
	}
	public void setGeometricMean(Double geometricMean) {
		this.geometricMean = geometricMean;
	}
	public Double getStockprice() {
		return stockprice;
	}
	public void setStockprice(Double stockprice) {
		this.stockprice = stockprice;
	}
	
}
