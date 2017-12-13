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
      
      System.out.println("물품 번호를 입력해주십시오");
      StrTemp = scan.nextLine();
      try {
         Long.parseLong(StrTemp);
         InputCheck = true;
      } catch (NumberFormatException e){
         InputCheck = false;
      }
      while(StrTemp.length() <= 13 || !(InputCheck)) {
         System.out.println("입력 길이가 맞지 않습니다. 물품 번호를 길이(13)에 맞춰서 숫자로 다시 입력해주십시오.");
         StrTemp = scan.nextLine();
         try {
            Long.parseLong(StrTemp);
            InputCheck = true;
         } catch (NumberFormatException e){
            InputCheck = false;
         }
      }
      
      
      System.out.println("할인율을 입력해주십시오(0 ~ 1)");
      try {
         sale = scan.nextDouble();
         InputCheck = true;
      } catch(InputMismatchException e) {
         InputCheck = false;
      }
      while(!(InputCheck) || sale> 1 || sale<0) {
         System.out.println("올바르지 않은 할인율입니다. 다시 입력해주십시오,");
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