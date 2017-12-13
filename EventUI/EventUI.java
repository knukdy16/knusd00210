package EventUI;

import Event.Event;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class EventUI {
   
   public void setEvent() throws IOException{
      Scanner scan = new Scanner(System.in); 
      String StrTemp = null;
      double sale=1;
      boolean InputCheck;
      
      System.out.println("��ǰ ��ȣ�� �Է����ֽʽÿ�");
      StrTemp = scan.nextLine();
      try {
         Long.parseLong(StrTemp);
         InputCheck = true;
      } catch (NumberFormatException e){
         InputCheck = false;
      }
      while(StrTemp.length() <= 13 || !(InputCheck)) {
         System.out.println("�Է� ���̰� ���� �ʽ��ϴ�. ��ǰ ��ȣ�� ����(13)�� ���缭 ���ڷ� �ٽ� �Է����ֽʽÿ�.");
         StrTemp = scan.nextLine();
         try {
            Long.parseLong(StrTemp);
            InputCheck = true;
         } catch (NumberFormatException e){
            InputCheck = false;
         }
      }
      
      
      System.out.println("�������� �Է����ֽʽÿ�(0 ~ 1)");
      try {
         sale = scan.nextDouble();
         InputCheck = true;
      } catch(InputMismatchException e) {
         InputCheck = false;
      }
      while(!(InputCheck) || sale> 1 || sale<0) {
         System.out.println("�ùٸ��� ���� �������Դϴ�. �ٽ� �Է����ֽʽÿ�,");
         scan.nextLine();
         try {
            sale = scan.nextDouble();
            InputCheck = true;
         } catch(InputMismatchException e) {
            InputCheck = false;
         }
      }
      Event event = new Event();
      
      event.setProductCode(StrTemp);
      event.setsale(sale);
      
      event.settingSale();
      
   }
   
}