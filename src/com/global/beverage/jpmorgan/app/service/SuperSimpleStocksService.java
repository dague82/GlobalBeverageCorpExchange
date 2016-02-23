package com.global.beverage.jpmorgan.app.service;

import com.global.beverage.jpmorgan.app.vo.Stock;

public interface SuperSimpleStocksService {

	
	Double calculateDividenYield(String type, Double last_dividend, Double fix_dividend, Double par_vaule, Double TickerPrice);
	Stock calculateDividenYield(Stock stock);
	
}
