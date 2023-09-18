package com.hk.shop.exception;

//재고가 없을 경우 exception
public class OutOfStockException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public OutOfStockException(String message) {
		super(message);
	}
}
