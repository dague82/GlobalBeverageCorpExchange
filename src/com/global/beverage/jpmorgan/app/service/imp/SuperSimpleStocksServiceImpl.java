package com.global.beverage.jpmorgan.app.service.imp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.global.beverage.jpmorgan.app.service.SuperSimpleStocksService;
import com.global.beverage.jpmorgan.app.service.SuperSimpleStocksValidatorServiceImpl;
import com.global.beverage.jpmorgan.app.util.Constants;
import com.global.beverage.jpmorgan.app.vo.Stock;
import com.global.beverage.jpmorgan.app.vo.Trade;

public class SuperSimpleStocksServiceImpl implements SuperSimpleStocksService {
	
	private Logger log = Logger.getLogger(SuperSimpleStocksServiceImpl.class);
	private static SuperSimpleStocksValidatorServiceImpl validatorService = new SuperSimpleStocksValidatorServiceImpl();
	private ArrayList<Trade> dbTrades = new ArrayList<Trade>();
	private List<Stock> dbStocks = new ArrayList<Stock>();

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
			if(validatorService.doubleIsZero(dividend)){
				System.out.println("Dividend is ZERO ... calculating PERatio");
				throw new Exception("Dividen is ZERO ... calculating PERatio");
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
		Double sumTradPriQua = new Double(0.0D);
		Double sumQuantities = new Double(0.0D);
		try{
			if(listTrade!=null){
				for(Trade _trade : listTrade){
					sumTradPriQua = sumTradPriQua + (_trade.getPrice()*_trade.getQuantityShares());
					sumQuantities = sumQuantities+_trade.getQuantityShares();
				}
				if(!validatorService.doubleIsZero(sumQuantities)){
					returnValue = sumTradPriQua / sumQuantities;
				}// if sum quantities = 0 , return 0
				
			}
		}catch(Exception ex){
			log.error("Error - " + this.getClass().getName(),ex);
		}
		log.debug("calculateStockPrice :" + returnValue);
		return returnValue;
	}

	@Override
	public Stock calculateStockPrice(Stock stock) {
		Double returnValue = new Double(0.00D);
		Double sumTradPriQua = new Double(0.0D);
		Double sumQuantities = new Double(0.0D);
		try{
			if(stock!=null){
				if(stock.getTrades()!=null){
				for(Trade _trade : stock.getTrades()){
					sumTradPriQua = sumTradPriQua + (_trade.getPrice()*_trade.getQuantityShares());
					sumQuantities = sumQuantities+_trade.getQuantityShares();
				}
				if(!validatorService.doubleIsZero(sumQuantities)){
					returnValue = sumTradPriQua / sumQuantities;
				}// if sum quantities = 0 , return 0
				}else{
					log.error("Error - " + this.getClass().getName() + " stock " + stock.getSymbol() + " - No trades");
				}
			}else{
				throw new Exception("Stock is null");
			}
			stock.setStockprice(returnValue);
		}catch(Exception ex){
			log.error("Error - " + this.getClass().getName(),ex);
		}
		log.debug("calculateStockPrice for : " + stock.getSymbol() + " value : " + returnValue);
		return stock;
	}

	@Override
	public boolean savingTrade(Trade trade){
		boolean result = true;
		try {
			if(trade==null){
				throw new Exception("In -  Trade is null");
			}
			if(trade.getTimeStamp()==null){
				throw new Exception("Parameter is Mandatory -  TimeStamp");
			}
			if(trade.getQuantityShares()==null){
				throw new Exception("Parameter is Mandatory -  Quantity");
			}
			if(trade.getTypeTrade()==null){
				throw new Exception("Parameter is Mandatory -  Indicator Buy or Sell");
			}
			if(trade.getPrice()==null){
				throw new Exception("Parameter is Mandatory -  Price");
			}
			dbTrades.add(trade);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("An error has occurred saving trade" + e.getMessage());
			result=false;
		}
		return result;
	}

	@Override
	public Double calculateStockPriceByLastSavedTrades() {
		Double returnValue = new Double(0.00D);
		try {
			if(dbTrades!=null&&dbTrades.size()>0){
				List<Trade> lastSavedTrades = new ArrayList<Trade>();
				Calendar now = GregorianCalendar.getInstance();
				now.add(Calendar.MINUTE, -15);
				Date limitDate=now.getTime();
				int count = 0;
				
				for(Trade t : dbTrades){
					if(count>=15)
						break;
					if(t.getTimeStamp().after(limitDate)){
						lastSavedTrades.add(t);
						count++;
					}
				}
				returnValue = this.calculateStockPrice(lastSavedTrades);
				log.debug("Stock Price from last 15  saved trades : "  + returnValue);
				// clean list  trade
				dbTrades = new ArrayList<Trade>();
			}else{
				throw new Exception("There aren't saved trades");
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("An error has occurred calculating stock price by last saved Trades" + e.getMessage());
		}
		return returnValue;
	}

	@Override
	public Stock newStock(String symbol, String type, Double lastDividend,
			Double fixedDividend, Double parValue) {
		// TODO Auto-generated method stub
		Stock stock = new Stock();
		try {
			stock.setSymbol(symbol);
			stock.setType(type);
			stock.setLastDividend(lastDividend!=null?lastDividend:0);
			stock.setFixedDividend(fixedDividend!=null?fixedDividend:0);
			stock.setParValue(parValue!=null?parValue:0);
			//
			
			// add to list of stock to calculate the geometic mean
			dbStocks.add(stock);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("An error has ocurred creating new stock", e);
			return null;
		}
		return stock;
	}

	@Override
	public Double calculateGBCE() {
		Double retunrValue  = new Double(0.00D);
		try {
			if(dbStocks!=null && dbStocks.size()>0){
				Double product = new Double(1);
				for(Stock s : dbStocks){
					product=product * s.getStockprice();
				}
				Integer m = dbStocks.size();
				Double nn = new Double(m.toString());
				nn=1/nn;
				retunrValue = Math.pow(product,nn);
			}else{
				throw new Exception("There aren't saved stocks");
			}
			log.debug("Geometric Mean : " + retunrValue);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("An error has ocurred calculating geometric mean ", e);
		}
		return retunrValue;
	}

	@Override
	public List<Stock> getDBStock() {
		// TODO Auto-generated method stub
		return dbStocks;
	}

	@Override
	public void updateDBStock(List<Stock> updateList) {
		// TODO Auto-generated method stub
		dbStocks = updateList;
	}
	
	

}
