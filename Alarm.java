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

	private int DailyDeliverAmount;		// �Ϸ� �Ͱ�: Deliver ����
	private int TotalAmount;			// ���� ���: product ����
	private int TotalStoreAmount;		// ��ü �԰�
	private int MonthlyStoreAmount;
	private int CriteriaOfShortage;		// ��� ���� ���ط�: ��ǰ��Ͽ��� �ҷ���
	private double Discount;			// ������̳�??
	private String ExpirationDate;		// �������: product ����	
	private String Barcode;				// ���ڵ�: product ����
	private String PrevBarcode;
	private String ProductCode;
	private Boolean ShortageState;
	private Boolean SuddenIncreaseState;
	private Boolean ExpirationState;
	private Boolean BadSellerState;
	private Date CurrDate;
	private Date ExDate;
	private ProductList PL;				// ��� ���
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
		this.DailyDeliverAmount = DailyDeliverAmount;		// �Ϸ� �Ͱ�: Deliver ����
		this.TotalAmount = TotalAmount;			// ���� ���: product ����
		this.TotalStoreAmount = TotalStoreAmount;		// ��ü �԰�
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
	public void shortageOfGoods()	// ��� ����, ���ڵ�
	{	/* ��ǰ ��Ͽ��� �ش� ��ǰ�� ��� ���� ���ط��� �޾ƿ�
		 * ������ ����� ��� ���� ���ط� ���� ������ ��
		 */
		PrevBarcode = "";
		for(int i= 0 ; i < Pl.length ; i++)
		{
			if(Pl[i].getDiscount() != 0)
				continue;
			// ��ǰ ��Ͽ��� ���ڵ� ��ȣ �о��
			Barcode = pl[i].getBarcode();
			// ���� ������ �˻� �ߴ� ��ǰ�̶�� continue
			if(!Barcode.equals(PrevBarcode))
				continue;
			PrevBarcode = Pl[i].getBarcode();
			// �ش� ��ǰ�� ��� ���� ���ط��� �޾ƿ�
			CriteriaOfShortage=Pl[i].getCriteriaOfShortage();
			// ���� ����� �޾ƿ�
			TotalAmount = Pl.getTotalAmount(Barcode);
			// ���� ����� ��� ���� ���ط� ���� �۴ٸ� true
			if(TotalAmount < CriteriaOfShortage)
				ShortageState = true;
			// ���� true�� ���
			AUI.printShortage(ShortageState, Pi[i].getName(), Barcode, TotalAmount, Str.getTotalStoreAmount(Barcode));
			// state �ʱ�ȭ
			ShortageState = false;
		}
	}

	public void suddenIncrease()	// �Ǹŷ� ����, ���ڵ�
	{	/* �Ϸ� ����� ����� 20% �̻��� ��
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
			// �Ϸ� ��� �ޱ�(�� ��� ���� ������..)
			DailyDeliverAmount = Dlv.getDailyDeliverAmount(Barcode);
			// ���� ��� �ޱ�
			TotalAmount = PL.getTotalAmount(Barcode);
			// �Ϸ� ����� ����� 20�� �̻��ΰ�?
			if(DailyDeliverAmount >= (TotalAmount * 0.2))
				SuddenIncreaseState = true;
			AUI.printSuddenIncrease(SuddenIncreaseState, Pl[i].getName(), Barcode, TotalAmount, Str.getTotalStoreAmount(Barcode));
			SuddenIncreaseState = false;
		}
	}
	

	public void expiration()	// ������� �ӹ�, ��ǰ ��ȣ
	{	/* 
		 * product list�� �о� ��ǰ�� ��������� ���� ��¥�� ���� 60�� �����̸� �˸��� ���
		 */
		
		// product list ���� �о�� ��¥ ���� ����
		String expectedPattern = "yyyy-MM-dd";
		// ���� ��¥ �������� format �ϰڴ�!
		SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
			
		for(int i = 0 ; i < Pl.length ; i++)
		{
			if(Pl[i].getDiscount() != 0)
				continue;
			// product list �о� ������� �о����
			ExpirationDate = Pl[i].getExpirationDate();
			// ������� �����ϳ�?
			if(ExpirationDate == "-")
				continue;
			try{
				// string �����ͼ� Date �� ��ȯ
				ExDate = formatter.parse(ExpirationDate);
			}
			catch(ParseException e)
			{
				e.printStackTrace();
			}pu
			// ���� ���� - ���� ��¥
			long diffday = (ExDate.getTime() - CurrDate.getTime()) / (24 * 60 * 60 * 1000);
			// 60 �� ���� ������
			if(diffday < 60)
				ExpirationState = true;
			Barcode = Pl[i].getBarcode();
			ProductCode = Pl[i].getProductCode();
			AUI.printExpiration(ExpirationState, Pl[i].getName(), Barcode, ProductCode, ExpirationDate);
			ExpirationState = false;
		}
	}

	public void badSeller()	// ��ǰ�� ���ȸ��� ����, ���ڵ�
	{	/* �԰����ڿ��� 30�� ���� �������� ��� �԰��� 50% �̻��϶�
		 */
		PrevBarcode = "";
		for(int i = 0 ; i < Pl.length ; i++)
		{
			if(Pl[i].getDiscount() != 0)
				continue;
			Barcode = Pl[i].getBarcode();
			// ������ �� ��ǰ�� ���� ��ǰ�ΰ�?
			if(!Barcode.equals(PrevBarcode))
				continue;
			PrevBarcode = Pl[i].getBarcode();
			// ���� ��� �ޱ�
			TotalAmount = Pl.getTotalAmount(Barcode);
			// 30�� ���� �԰�� ��ǰ ����
			MonthlyStoreAmount = Str.getTotalStoreAmount30(Barcode);
			if(TotalAmount >= (MonthlyStoreAmount * 0.5))
				BadSellerState = true;
			AUI.printBadSeller(BadSellerState, Pl[i].getName(), Barcode, TotalAmount, Str.getTotalStoreAmount(), Str.getStoreDate(Barcode));
			BadSellerState = false;
		}
	}
}


