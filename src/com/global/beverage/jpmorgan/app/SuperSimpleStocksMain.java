package com.global.beverage.jpmorgan.app;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import com.global.beverage.jpmorgan.app.service.imp.SuperSimpleStocksServiceImpl;
import com.global.beverage.jpmorgan.app.util.Constants;
import com.global.beverage.jpmorgan.app.vo.Stock;
import com.global.beverage.jpmorgan.app.vo.Trade;

public class SuperSimpleStocksMain {

	
	private static SuperSimpleStocksServiceImpl superSimpleStocksService = new SuperSimpleStocksServiceImpl();
	public SuperSimpleStocksMain() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		superSimpleStocksService.newStock("TEA", "TC",Double.valueOf("0"), null, Double.valueOf("100"));
		superSimpleStocksService.newStock("POP", "TC",Double.valueOf("8"), null, Double.valueOf("100"));
		superSimpleStocksService.newStock("ALE", "TC",Double.valueOf("23"), null, Double.valueOf("60"));
		superSimpleStocksService.newStock("GIN", "TP",Double.valueOf("8"), Double.valueOf("2"), Double.valueOf("100"));
		superSimpleStocksService.newStock("JOE", "TC",Double.valueOf("13"), null, Double.valueOf("250"));
		
		List<Stock> listNewStock = new ArrayList<Stock>();
		
		
		for(Stock newStock : superSimpleStocksService.getDBStock()){
			// save 15 record
			List<Trade> savedTrade = new ArrayList<Trade>();
			System.out.println(" Stock :  "  + newStock.getSymbol()) ;
			for(int i=1;i<=15;i++){
				Trade newt = new Trade();
				Calendar now = Calendar.getInstance();
				now.add(Calendar.SECOND, i);
				Date f = now.getTime();
				newt.setTimeStamp(new Timestamp(f.getTime()));
				newt.setQuantityShares(new Double(i));
				newt.setPrice(newStock.getParValue()*1.25);
				if(i<8){
					newt.setTypeTrade(Constants.INDICATOR_SELL);
				}else
					newt.setTypeTrade(Constants.INDICATOR_BUY);
				newStock.setTickerPrice(newt.getPrice());
				superSimpleStocksService.savingTrade(newt);
				savedTrade.add(newt);
				System.out.println(" Stock :  "  + newStock.getType() + " - Saved Trade : " + newt.getTypeTrade() +" / "
						+  newt.getPrice() +" / "
						+  newt.getQuantityShares()+" / "
						+  newt.getTimeStamp());
			}
			newStock.setTrades(savedTrade);
			newStock.setStockprice(superSimpleStocksService.calculateStockPriceByLastSavedTrades());
			System.out.println(" Stock :  "  + newStock.getType() + "- StockPrice : " + newStock.getStockprice()) ;
			newStock.setDividendYeld(superSimpleStocksService.calculateDividenYield(newStock.getType(), newStock.getLastDividend(), newStock.getFixedDividend(),newStock.getParValue(), newStock.getTickerPrice()));
			System.out.println(" Stock :  "  + newStock.getType() + "- DividendYeld : " + newStock.getDividendYeld()) ;
			newStock.setpERatio(superSimpleStocksService.calculatePERatio(newStock.getTickerPrice(), newStock.getDividendYeld()));
			System.out.println(" Stock :  "  + newStock.getType() + "- PERatio : " + newStock.getpERatio()) ;
			listNewStock.add(newStock);
		}
		superSimpleStocksService.updateDBStock(listNewStock);	
		System.out.println(" Geometric Mean :  "  + superSimpleStocksService.calculateGBCE()) ;

	}

}
