/**
 * 
 */
package com.global.beverage.jpmorgan.app.service;

/**
 * @author INGDGC
 *
 */
public final class SuperSimpleStocksValidatorServiceImpl implements
		SuperSimpleStocksValidatorService {

	/* (non-Javadoc)
	 * @see com.global.beverage.jpmorgan.app.service.SuperSimpleStocksValidatorService#validateDoubleIsZero(java.lang.Double)
	 */
	@Override
	public boolean doubleIsZero(Double value) {
		// TODO Auto-generated method stub
		Double zero = new Double(0.00D);
		if(value!=null && zero.equals(value))
			return true;
		return false;
	}

}
