package alarmUI;

import java.util.*;
import java.io.*;
import java.text.*;
import alarmUI.*;
import store.*;
import product.*;
import productList*;
import Deliver.*;
/*
private Boolean ShortageState;
private Boolean SuddenIncreaseState;
private Boolean ExpirationState;
private Boolean BadSellerState;
	private int DailyDeliverAmount;		// 하루 촐고량: Deliver 참고
	private int TotalAmount;			// 어제 재고량: product 참고
	private int TotalStoreAmount;		// 전체 입고량
	private int MonthlyStoreAmount;
	private int CriteriaOfShortage;	// 재고 부족 기준량: 물품목록에서 불러옴
	private String ExpirationDate;		// 유통기한: product 참고	
	private String Barcode;				// 바코드: product 참고
	private String PrevBarcode;
*/

public class AlarmUI {

	private int TotalStoreAmount;	// 전체 입고량
	private int TotalAmount;		// 재고량
	private String ExpirationDate;	// 유통기한
	private String Barcode;			// 바코드 번호
	private String Name;			// 물품 이름
	private String ProductCode;		// 물품 번호
	private String StoreDate;		// 입고 일자
	private boolean state;
	
	public AlarmUI()
	{
		state = false;
	}

	//ShortageState, Pl.getName(), Barcode, Str.getTotalAmount()
	public void readyToShortage()
	{
		System.out.println("재고 부족 기준량을 만족하는 상품입니다.");
	}
	public void readyToSuddenIncrease()
	{
		System.out.println("판매량 급증을 만족하는 상품입니다.");
	}
	public void readyToExpiration()
	{
		System.out.println("남은 유통기한이 60일 이하인 상품입니다.");
	}
	public void readyToBadSeller()
	{
		System.out.println("제품이 안 팔리는 기준을 만족하는 상품입니다.");
	}
	public void printShortage(boolean state, String Name, String Barcode, int TotalAmount, int TotalStoreAmount)
	{
		if(state)
		{
			System.out.println("상품이름: " + Name);
			System.out.println("바코드 번호 " + Barcode);
			System.out.println("재고 계: " + TotalAmount);
			System.out.println("입고 계: " + TotalStoreAmount);
			System.out.println("");
		}
	}
	public void printSuddenIncrease(boolean state, String Name, String Barcode, int TotalAmount, int TotalStoreAmount)
	{
		if(state)
		{
			System.out.println("상품이름: " + Name);
			System.out.println("바코드 번호 " + Barcode);
			System.out.println("재고 계: " + TotalAmount);
			System.out.println("입고 계: " + TotalStoreAmount);
			System.out.println("");
		}
	}
	//ExpirationState, Pl[i].getName(), Barcode, ProductCode, ExpirationDate
	public void printExpiration(boolean state, String Name, String Barcode, String ProductCode, String ExpirationDate)
	{
		if(state)
		{
			System.out.println("상품이름: " + Name);
			System.out.println("바코드 번호 " + Barcode);
			System.out.println("물품 번호: " + ProductCode);
			System.out.println("유통 기한: " + ExpirationDate);
			System.out.println("");
		}
	}
	//BadSellerState, Pl[i].getName(), Barcode, TotalAmount, Str.getTotalStoreAmount(), Str.getStoreDate(Barcode)
	public void printBadSeller(boolean state, String Name, String Barcode, int TotalAmount, int TotalStoreAmount, String StoreDate)
	{
		if(state)
		{
			System.out.println("상품이름: " + Name);
			System.out.println("바코드 번호 " + Barcode);
			System.out.println("재고 계: " + TotalAmount);
			System.out.println("입고 계: " + TotalStoreAmount);
			System.out.println("입고 일자: " + StoreDate);
			System.out.println("");
		}
	}

	
}
