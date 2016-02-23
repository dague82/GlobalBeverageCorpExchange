package com.global.beverage.jpmorgan.app.service.imp;

import org.apache.log4j.Logger;

import com.global.beverage.jpmorgan.app.service.SuperSimpleStocksService;
import com.global.beverage.jpmorgan.app.vo.Stock;

public class SuperSimpleStocksServiceImpl implements SuperSimpleStocksService {
	
	private Logger log = Logger.getLogger(SuperSimpleStocksServiceImpl.class);

	@Override
	public Double calculateDividenYield(String type, Double last_dividend,
			Double fix_dividend, Double par_vaule, Double TickerPrice) {
		// TODO Auto-generated method stub
		Double returnValue = new Double(0.00D);
		try{
			
		}catch(Exception ex){
			log.error("Error - " + this.getClass().getName(),ex);
		}
		return returnValue;
	}

	@Override
	public Stock calculateDividenYield(Stock stock) {
		// TODO Auto-generated method stub
		return null;
	}

}
