package Alarm;

import java.util.*;

import AlarmUI.AlarmUI;

import java.io.*;
import java.text.*;

import ProductManagement.Deliver;
import ProductManagement.Product;
import ProductManagement.ProductList;
import ProductManagement.Store;


public class Alarm {
   private int DailyDeliverAmount;      // �Ϸ� �Ͱ�: Deliver ����
   private int TotalAmount;         // ���� ���: product ����
   private int TotalStoreAmount;      // ��ü �԰�
   private int MonthlyStoreAmount;
   private int CriteriaOfShortage;      // ��� ���� ���ط�: ��ǰ��Ͽ��� �ҷ���
   private String ExpirationDate;      // �������: product ����   
   private String Barcode;            // ���ڵ�: product ����
   private String PrevBarcode;
   private String ProductCode;
   private Boolean ShortageState;
   private Boolean SuddenIncreaseState;
   private Boolean ExpirationState;
   private Boolean BadSellerState;
   private Date CurrDate;
   private Date ExDate;
   private ProductList PL;            // ��� ���
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
      Pl = PL.getProductList();
      if(Pl == null)
      {
         System.out.println("Error: null DELIVER object");
         System.exit(0);
      }
      this.PL = PL;
      this.Str = Str;
      this.Dlv = Dlv;
      setState(false, false, false, false);
      setBarcode("", "");
      this.Pl = PL.getProductList();
      CurrDate = new Date();
      //this.AUI = new AlarmUI();
   }
   
   public void setAmount(int DailyDeliverAmount, int TotalAmount, int TotalStoreAmount, int MonthlyStoreAmount)
   {
      this.DailyDeliverAmount = DailyDeliverAmount;      // �Ϸ� �Ͱ�: Deliver ����
      this.TotalAmount = TotalAmount;         // ���� ���: product ����
      this.TotalStoreAmount = TotalStoreAmount;      // ��ü �԰�
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
   
   public void callAlarm() throws Exception
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
   
   public void shortageOfGoods() throws IOException   // ��� ����, ���ڵ�
   {   /* ��ǰ ��Ͽ��� �ش� ��ǰ�� ��� ���� ���ط��� �޾ƿ�
       * ������ ����� ��� ���� ���ط� ���� ������ ��
       */
      PrevBarcode = "";
      for(int i= 0 ; i < Pl.length ; i++)
      {
         if(Pl[i].getDiscountRate() != 0)
            continue;
         // ��ǰ ��Ͽ��� ���ڵ� ��ȣ �о��
         Barcode = Pl[i].getBarCode();
         // ���� ������ �˻� �ߴ� ��ǰ�̶�� continue
         if(Barcode.compareTo(PrevBarcode) == 0)
            continue;
         PrevBarcode = Pl[i].getBarCode();
         // �ش� ��ǰ�� ��� ���� ���ط��� �޾ƿ�
         CriteriaOfShortage=Pl[i].getInadequate();
         // ���� ����� �޾ƿ�
         TotalAmount = PL.getTotalProductAmount(Barcode);
         // ���� ����� ��� ���� ���ط� ���� �۴ٸ� true
         if(TotalAmount < CriteriaOfShortage)
            ShortageState = true;
         // ���� true�� ���
         System.out.println(ShortageState );
         System.out.println(Pl[i].getName() );
         System.out.println(Barcode);
         System.out.println(TotalAmount);
         System.out.println(Str.getTotalStoreAmount(Barcode));
         AUI.printShortage(ShortageState, Pl[i].getName(), Barcode, TotalAmount, Str.getTotalStoreAmount(Barcode));
         // state �ʱ�ȭ
         ShortageState = false;
      }
   }

   public void suddenIncrease() throws IOException   // �Ǹŷ� ����, ���ڵ�
   {   /* �Ϸ� ����� ����� 20% �̻��� ��
       */
      PrevBarcode = "";
      for(int i = 0 ; i < Pl.length ; i++)
      {
         if(Pl[i].getDiscountRate() != 0)
            continue;
         Barcode = Pl[i].getBarCode();
         if(Barcode.compareTo(PrevBarcode) == 0)   //compare
            continue;
         PrevBarcode = Pl[i].getBarCode();
         // �Ϸ� ��� �ޱ�(�� ��� ���� ������..)
         DailyDeliverAmount = Dlv.returnTotalDeliverAmount(Barcode);
         // ���� ��� �ޱ�
         TotalAmount = PL.getTotalProductAmount(Barcode);
         // �Ϸ� ����� ����� 20�� �̻��ΰ�?
         if(DailyDeliverAmount >= (TotalAmount * 0.2))
            SuddenIncreaseState = true;
         AUI.printSuddenIncrease(SuddenIncreaseState, Pl[i].getName(), Barcode, TotalAmount, Str.getTotalStoreAmount(Barcode));
         SuddenIncreaseState = false;
      }
   }
   

   public void expiration()   // ������� �ӹ�, ��ǰ ��ȣ
   {   /* 
       * product list�� �о� ��ǰ�� ��������� ���� ��¥�� ���� 60�� �����̸� �˸��� ���
       */
      
      // product list ���� �о�� ��¥ ���� ����
      String expectedPattern = "yyyy-MM-dd";
      // ���� ��¥ �������� format �ϰڴ�!
      SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
      for(int i = 0 ; i < Pl.length ; i++)
      {
         
         if(Pl[i].getDiscountRate() != 0)
            continue;
         // product list �о� ������� �о����
         ExpirationDate = Pl[i].getExpirationDate();
         // ������� �����ϳ�?
         if(ExpirationDate.compareTo("-") == 0)
            continue;
         try{
            // string �����ͼ� Date �� ��ȯ
            ExDate = formatter.parse(ExpirationDate);
         }
         catch(ParseException e)
         {
            e.printStackTrace();
         }
         // ���� ���� - ���� ��¥
         long diffday = (ExDate.getTime() - CurrDate.getTime()) / (long)(24 * 60 * 60 * 1000);
         // 60 �� ���� ������
         if(diffday < 60)
            ExpirationState = true;
         Barcode = Pl[i].getBarCode();
         ProductCode = Pl[i].getProductCode();
         AUI.printExpiration(ExpirationState, Pl[i].getName(), Barcode, ProductCode, ExpirationDate);
         ExpirationState = false;
      }
   }

   public void badSeller() throws Exception
   {   /* �԰����ڿ��� 30�� ���� �������� ��� �԰��� 50% �̻��϶�
       */
      PrevBarcode = "";
      for(int i = 0 ; i < Pl.length ; i++)
      {
         if(Pl[i].getDiscountRate() != 0)
            continue;
         Barcode = Pl[i].getBarCode();
         // ������ �� ��ǰ�� ���� ��ǰ�ΰ�?
         if(Barcode.compareTo(PrevBarcode) == 0)
            continue;
         PrevBarcode = Pl[i].getBarCode();
         // ���� ��� �ޱ�
         TotalAmount = PL.getTotalProductAmount(Barcode);
         // 30�� ���� �԰�� ��ǰ ����
         MonthlyStoreAmount = Str.getTotalStoreAmount30(Barcode);
         if(TotalAmount >= (MonthlyStoreAmount * 0.5))
            BadSellerState = true;
         AUI.printBadSeller(BadSellerState, Pl[i].getName(), Barcode, TotalAmount, Str.getTotalStoreAmount(Barcode), Str.getTotalStoreDate(Barcode));
         BadSellerState = false;
      }
   }
}

