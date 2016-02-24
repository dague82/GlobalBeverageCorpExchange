package com.global.beverage.jpmorgan.app.service;

import java.util.List;

import com.global.beverage.jpmorgan.app.vo.Stock;
import com.global.beverage.jpmorgan.app.vo.Trade;

public interface SuperSimpleStocksService {

	
	Double calculateDividenYield(String type, Double last_dividend, Double fix_dividend, Double par_vaule, Double TickerPrice);
	Stock calculateDividenYield(Stock stock);
	Double calculatePERatio(Double TickerPrice,Double dividend);
	Stock calculatePERatio(Stock stock);
	Double calculateStockPrice(List<Trade> listTrade);
	Stock calculateStockPrice(Stock stock);
	
	
	
}
