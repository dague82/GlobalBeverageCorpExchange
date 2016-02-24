package com.global.beverage.jpmorgan.app.service.imp;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;




import com.global.beverage.jpmorgan.app.service.SuperSimpleStocksService;
import com.global.beverage.jpmorgan.app.service.SuperSimpleStocksValidatorService;
import com.global.beverage.jpmorgan.app.util.Constants;
import com.global.beverage.jpmorgan.app.vo.Stock;
import com.global.beverage.jpmorgan.app.vo.Trade;

public class SuperSimpleStocksServiceImpl implements SuperSimpleStocksService {
	
	private Logger log = Logger.getLogger(SuperSimpleStocksServiceImpl.class);
	private SuperSimpleStocksValidatorService validatorService;

	@Override
	public Double calculateDividenYield(String type, Double last_dividend,
			Double fix_dividend, Double par_vaule, Double TickerPrice) {
		// TODO Auto-generated method stub
		Double returnValue = new Double(0.00D);
		try{
			if(validatorService.doubleIsZero(TickerPrice)){
				throw new Exception("TicckerPrice is ZERO");
			}
			if(type.equals(Constants.TYPE_COMMOM)){
				returnValue = last_dividend/TickerPrice;
			}else if(type.equals(Constants.TYPE_PREFERRED)){
				returnValue = ((fix_dividend/100)*par_vaule)/TickerPrice;
			}
		}catch(Exception ex){
			log.error("Error - " + this.getClass().getName(),ex);
		}
		log.debug("calculateDividenYield :" + returnValue);
		return returnValue;
	}

	@Override
	public Stock calculateDividenYield(Stock stock) {
		// TODO Auto-generated method stub
		Double returnValue = new Double(0.00D);
		try{
			if(stock!=null){
			if(validatorService.doubleIsZero(stock.getTickerPrice())){
				throw new Exception("TicckerPrice is ZERO");
			}
			if(stock.getType().equals(Constants.TYPE_COMMOM)){
				returnValue = stock.getLastDividend()/stock.getTickerPrice();
			}else if(stock.getType().equals(Constants.TYPE_PREFERRED)){
				returnValue = ((stock.getFixedDividend()/100)*stock.getParValue())/stock.getTickerPrice();
			}
			}else{
				throw new Exception("Stock is Null ... ");
			}
			stock.setDividendYeld(returnValue);		
		}catch(Exception ex){
			log.error("Error - " + this.getClass().getName(),ex);
		}
		log.debug("calculateDividenYield for  :" + stock.getSymbol() + " Value : " + returnValue);
		return stock;
	}

	@Override
	public Double calculatePERatio(Double TickerPrice, Double dividend) {
		// TODO Auto-generated method stub
		Double returnValue = new Double(0.00D);
		try{
			if(validatorService.doubleIsZero(TickerPrice)){
				throw new Exception("TicckerPrice is ZERO ... calculating PERatio");
			}
			returnValue=TickerPrice/dividend;
		}catch(Exception ex){
			log.error("Error - " + this.getClass().getName(),ex);
		}
		log.debug("calculatePERatio :" + returnValue);
		return returnValue;
	}

	@Override
	public Stock calculatePERatio(Stock stock) {
		// TODO Auto-generated method stub
		Double returnValue = new Double(0.00D);
		try{
			if(stock!=null){
			if(validatorService.doubleIsZero(stock.getTickerPrice())){
				throw new Exception("TicckerPrice is ZERO ... calculating PERatio");
			}

			returnValue=stock.getTickerPrice()/stock.getDividendYeld();
			stock.setpERatio(returnValue);	
		}else{
			throw new Exception("Stock is Null ... ");
		}
		}catch(Exception ex){
			log.error("Error - " + this.getClass().getName(),ex);
		}
		log.debug("calculatePERatio for  :" + stock.getSymbol() + " Value : " + returnValue);
		return stock;
	}

	@Override
	public Double calculateStockPrice(List<Trade> listTrade) {
		Double returnValue = new Double(0.00D);
		try{
			if(listTrade!=null){
				for(Trade _trade : listTrade){
					
				}
			}
		}catch(Exception ex){
			log.error("Error - " + this.getClass().getName(),ex);
		}
		log.debug("calculateStockPrice :" + returnValue);
		return returnValue;
	}

	@Override
	public Stock calculateStockPrice(Stock stock) {
		// TODO Auto-generated method stub
		return null;
	}

}
