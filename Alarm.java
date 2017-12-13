package alarm;

import java.util.*;
import java.io.*;
import java.lang.*;
import java.text.*;
import alarmUI.*;
import store.*;
import product.*;
import productList*;
import Deliver.*;


public class Alarm {

	private int DailyDeliverAmount;		// 하루 촐고량: Deliver 참고
	private int TotalAmount;			// 어제 재고량: product 참고
	private int TotalStoreAmount;		// 전체 입고량
	private int MonthlyStoreAmount;
	private int CriteriaOfShortage;		// 재고 부족 기준량: 물품목록에서 불러옴
	private double Discount;			// 행사중이냐??
	private String ExpirationDate;		// 유통기한: product 참고	
	private String Barcode;				// 바코드: product 참고
	private String PrevBarcode;
	private String ProductCode;
	private Boolean ShortageState;
	private Boolean SuddenIncreaseState;
	private Boolean ExpirationState;
	private Boolean BadSellerState;
	private Date CurrDate;
	private Date ExDate;
	private ProductList PL;				// 재고 목록
	private Product[] Pl;	
	private Store Str;
	private Deliver Dlv;
	private AlarmUI AUI;
	
	public Alarm()
	{
		setAmount(0, 0, 0, 0);
		CriteriaOfShortage = 0;
		ExpirationDate = "0000-00-00";
		setBarcode("", "");
		setState(ShortageState, SuddenIncreaseState, ExpirationState, BadSellerState);
	}
	
	public Alarm(ProductList PL, Store Str, Deliver Dlv)
	{
		if(PL == null)
		{
			System.out.println("Error: null PRODUCT LIST object");
			System.exit(0);
		}
		if(Str == null)
		{
			System.out.println("Error: null STORE object");
			System.exit(0);
		}
		if(Pl == null)
		{
			System.out.println("Error: null DELIVER object");
			System.exit(0);
		}
		this.PL = PL;
		this.Str = Str;
		this.Dlv = Dlv;
		setState(ShortageState, SuddenIncreaseState, ExpirationState, BadSellerState);
		setBarcode("", "");
		this.Pl = PL.getProductList();
		//this.AUI = new AlarmUI();
	}
	
	public void setAmount(int DailyDeliverAmount, int TotalAmount, int TotalStoreAmount, int MonthlyStoreAmount)
	{
		this.DailyDeliverAmount = DailyDeliverAmount;		// 하루 촐고량: Deliver 참고
		this.TotalAmount = TotalAmount;			// 어제 재고량: product 참고
		this.TotalStoreAmount = TotalStoreAmount;		// 전체 입고량
		this.MonthlyStoreAmount = MonthlyStoreAmount;
	}
	public void setState(Boolean ShortageState, Boolean SuddenIncreaseState, Boolean ExpirationState, Boolean BadSellerState)
	{
		this.ShortageState = ShortageState;
		this.SuddenIncreaseState = SuddenIncreaseState;
		this.ExpirationState = ExpirationState;
		this.BadSellerState = BadSellerState;
	}
	public void setBarcode(String Barcode, String PrevBarcode)
	{
		this.Barcode = new String(Barcode);
		this.PrevBarcode = new String(PrevBarcode);
	}
	public void callAlarm()
	{
		AUI = new AlarmUI();
		AUI.readyToShortage();
		shortageOfGoods();
		AUI.readyToSuddenIncrease();
		suddenIncrease();
		AUI.readyToExpiration();
		expiration();
		AUI.readyToBadSeller();
		badSeller();
	}
	public void shortageOfGoods()	// 재고 부족, 바코드
	{	/* 물품 목록에서 해당 물품의 재고 부족 기준량을 받아와
		 * 현재의 재고량이 재고 부족 기준량 보다 작은지 봄
		 */
		PrevBarcode = "";
		for(int i= 0 ; i < Pl.length ; i++)
		{
			if(Pl[i].getDiscount() != 0)
				continue;
			// 물품 목록에서 바코드 번호 읽어옴
			Barcode = pl[i].getBarcode();
			// 만약 이전에 검색 했던 물품이라면 continue
			if(!Barcode.equals(PrevBarcode))
				continue;
			PrevBarcode = Pl[i].getBarcode();
			// 해당 물품의 재고 부족 기준량을 받아옴
			CriteriaOfShortage=Pl[i].getCriteriaOfShortage();
			// 현재 재고량을 받아옴
			TotalAmount = Pl.getTotalAmount(Barcode);
			// 현재 재고량이 재고 부족 기준량 보다 작다면 true
			if(TotalAmount < CriteriaOfShortage)
				ShortageState = true;
			// 만약 true면 출력
			AUI.printShortage(ShortageState, Pi[i].getName(), Barcode, TotalAmount, Str.getTotalStoreAmount(Barcode));
			// state 초기화
			ShortageState = false;
		}
	}

	public void suddenIncrease()	// 판매량 급증, 바코드
	{	/* 하루 출고량이 재고량의 20% 이상일 때
		 */
		PrevBarcode = "";
		for(int i = 0 ; i < Pl.length ; i++)
		{
			if(Pl[i].getDiscount() != 0)
				continue;
			Barcode = Pl[i].getBarcode();
			if(!Barcode.equals(PrevBarcode))	//compare
				continue;
			PrevBarcode = Pl[i].getBarcode();
			// 하루 출고량 받기(는 사실 어제 꺼게찌..)
			DailyDeliverAmount = Dlv.getDailyDeliverAmount(Barcode);
			// 어제 재고량 받기
			TotalAmount = PL.getTotalAmount(Barcode);
			// 하루 출고량이 재고량의 20퍼 이상인가?
			if(DailyDeliverAmount >= (TotalAmount * 0.2))
				SuddenIncreaseState = true;
			AUI.printSuddenIncrease(SuddenIncreaseState, Pl[i].getName(), Barcode, TotalAmount, Str.getTotalStoreAmount(Barcode));
			SuddenIncreaseState = false;
		}
	}
	

	public void expiration()	// 유통기한 임박, 물품 번호
	{	/* 
		 * product list를 읽어 제품의 유통기한이 현재 날짜로 부터 60일 이하이면 알림을 띄움
		 */
		
		// product list 에서 읽어올 날짜 형식 선언
		String expectedPattern = "yyyy-MM-dd";
		// 위의 날짜 형식으로 format 하겠다!
		SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
			
		for(int i = 0 ; i < Pl.length ; i++)
		{
			if(Pl[i].getDiscount() != 0)
				continue;
			// product list 읽어 유통기한 읽어오기
			ExpirationDate = Pl[i].getExpirationDate();
			// 유통기한 존재하나?
			if(ExpirationDate == "-")
				continue;
			try{
				// string 가져와서 Date 로 변환
				ExDate = formatter.parse(ExpirationDate);
			}
			catch(ParseException e)
			{
				e.printStackTrace();
			}pu
			// 유통 기한 - 현재 날짜
			long diffday = (ExDate.getTime() - CurrDate.getTime()) / (24 * 60 * 60 * 1000);
			// 60 일 이하 남으면
			if(diffday < 60)
				ExpirationState = true;
			Barcode = Pl[i].getBarcode();
			ProductCode = Pl[i].getProductCode();
			AUI.printExpiration(ExpirationState, Pl[i].getName(), Barcode, ProductCode, ExpirationDate);
			ExpirationState = false;
		}
	}

	public void badSeller()	// 제품이 안팔리는 기준, 바코드
	{	/* 입고일자에서 30일 지난 시점에서 재고가 입고의 50% 이상일때
		 */
		PrevBarcode = "";
		for(int i = 0 ; i < Pl.length ; i++)
		{
			if(Pl[i].getDiscount() != 0)
				continue;
			Barcode = Pl[i].getBarcode();
			// 이전에 한 물품과 같은 물품인가?
			if(!Barcode.equals(PrevBarcode))
				continue;
			PrevBarcode = Pl[i].getBarcode();
			// 어제 재고량 받기
			TotalAmount = Pl.getTotalAmount(Barcode);
			// 30일 동안 입고된 물품 갯수
			MonthlyStoreAmount = Str.getTotalStoreAmount30(Barcode);
			if(TotalAmount >= (MonthlyStoreAmount * 0.5))
				BadSellerState = true;
			AUI.printBadSeller(BadSellerState, Pl[i].getName(), Barcode, TotalAmount, Str.getTotalStoreAmount(), Str.getStoreDate(Barcode));
			BadSellerState = false;
		}
	}
}


