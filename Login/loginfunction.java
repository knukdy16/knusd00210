package Login;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class loginfunction{
   public boolean logincheck(String pw2) {
      File inFile = new File("pw.txt");
      BufferedWriter bw = null;   
      boolean fexist;
      fexist = inFile.exists();
      if(!fexist) {
         try {
            bw = new BufferedWriter(new FileWriter("pw.txt"));
            bw.write("admin");
            bw.flush();
         } catch (IOException e) {
            e.printStackTrace();
         }finally {
            if(bw != null) try {bw.close(); } catch (IOException e) {}
         }
      }
      String password = "";
      BufferedReader br = null;      
      
      try {
         br = new BufferedReader(new FileReader(inFile));
         password = br.readLine();
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         if(br != null) try {br.close(); } catch (IOException e) {}
      }   
      if(pw2.compareTo(password)==0)
         return true;
      return false;   
   }
   public void setpw(){   //비밀번호 수정
      String password = "";
      String pw2="";
      BufferedWriter bw = null;      
       File outFile = new File("pw.txt");
      Scanner input = new Scanner(System.in);   
      
      do {
         System.out.println("비밀번호를 설정해주십시오");
         password = input.nextLine();
         System.out.println("비밀번호를 다시 입력해주십시오");
         pw2 = input.nextLine();
      }while(password.compareTo(pw2) != 0);
      System.out.println("입력해주신 비밀번호는 다음과 같습니다.\n\n" + password);
      try {
         bw = new BufferedWriter(new FileWriter(outFile));
         bw.write(password);
         bw.flush();
      } catch (IOException e) {
         e.printStackTrace();
      }finally {
         if(bw != null) try {bw.close(); } catch (IOException e) {}
      }
      return;
   }
}
