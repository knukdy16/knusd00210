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
	private int DailyDeliverAmount;		// �Ϸ� �Ͱ�: Deliver ����
	private int TotalAmount;			// ���� ���: product ����
	private int TotalStoreAmount;		// ��ü �԰�
	private int MonthlyStoreAmount;
	private int CriteriaOfShortage;	// ��� ���� ���ط�: ��ǰ��Ͽ��� �ҷ���
	private String ExpirationDate;		// �������: product ����	
	private String Barcode;				// ���ڵ�: product ����
	private String PrevBarcode;
*/

public class AlarmUI {

	private int TotalStoreAmount;	// ��ü �԰�
	private int TotalAmount;		// ���
	private String ExpirationDate;	// �������
	private String Barcode;			// ���ڵ� ��ȣ
	private String Name;			// ��ǰ �̸�
	private String ProductCode;		// ��ǰ ��ȣ
	private String StoreDate;		// �԰� ����
	private boolean state;
	
	public AlarmUI()
	{
		state = false;
	}

	//ShortageState, Pl.getName(), Barcode, Str.getTotalAmount()
	public void readyToShortage()
	{
		System.out.println("��� ���� ���ط��� �����ϴ� ��ǰ�Դϴ�.");
	}
	public void readyToSuddenIncrease()
	{
		System.out.println("�Ǹŷ� ������ �����ϴ� ��ǰ�Դϴ�.");
	}
	public void readyToExpiration()
	{
		System.out.println("���� ��������� 60�� ������ ��ǰ�Դϴ�.");
	}
	public void readyToBadSeller()
	{
		System.out.println("��ǰ�� �� �ȸ��� ������ �����ϴ� ��ǰ�Դϴ�.");
	}
	public void printShortage(boolean state, String Name, String Barcode, int TotalAmount, int TotalStoreAmount)
	{
		if(state)
		{
			System.out.println("��ǰ�̸�: " + Name);
			System.out.println("���ڵ� ��ȣ " + Barcode);
			System.out.println("��� ��: " + TotalAmount);
			System.out.println("�԰� ��: " + TotalStoreAmount);
			System.out.println("");
		}
	}
	public void printSuddenIncrease(boolean state, String Name, String Barcode, int TotalAmount, int TotalStoreAmount)
	{
		if(state)
		{
			System.out.println("��ǰ�̸�: " + Name);
			System.out.println("���ڵ� ��ȣ " + Barcode);
			System.out.println("��� ��: " + TotalAmount);
			System.out.println("�԰� ��: " + TotalStoreAmount);
			System.out.println("");
		}
	}
	//ExpirationState, Pl[i].getName(), Barcode, ProductCode, ExpirationDate
	public void printExpiration(boolean state, String Name, String Barcode, String ProductCode, String ExpirationDate)
	{
		if(state)
		{
			System.out.println("��ǰ�̸�: " + Name);
			System.out.println("���ڵ� ��ȣ " + Barcode);
			System.out.println("��ǰ ��ȣ: " + ProductCode);
			System.out.println("���� ����: " + ExpirationDate);
			System.out.println("");
		}
	}
	//BadSellerState, Pl[i].getName(), Barcode, TotalAmount, Str.getTotalStoreAmount(), Str.getStoreDate(Barcode)
	public void printBadSeller(boolean state, String Name, String Barcode, int TotalAmount, int TotalStoreAmount, String StoreDate)
	{
		if(state)
		{
			System.out.println("��ǰ�̸�: " + Name);
			System.out.println("���ڵ� ��ȣ " + Barcode);
			System.out.println("��� ��: " + TotalAmount);
			System.out.println("�԰� ��: " + TotalStoreAmount);
			System.out.println("�԰� ����: " + StoreDate);
			System.out.println("");
		}
	}

	
}
