package DataManagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import ProductManagement.Deliver;
import ProductManagement.Product;
import ProductManagement.Store;

public class ProductFile {
   public void saveProductFile(Product[] SaveData) throws IOException{
      BufferedWriter file = new BufferedWriter(new FileWriter("Product.txt"));

      PrintWriter pw = new PrintWriter(new FileWriter("Product.txt", true));
      for(int i=0; i<SaveData.length; i++ ){
         pw.println(SaveData[i].getName() + "|" + SaveData[i].getAmount()  + "|" + SaveData[i].getDiscountRate() + "|" + SaveData[i].getNormalPrice()
          + "|" + SaveData[i].getBarCode() +"|" + SaveData[i].getProductCode() + "|" + SaveData[i].getClient() 
          + "|" + SaveData[i].getExpirationDate() + "|" + SaveData[i].getContainer() + "|" + SaveData[i].getOriginalPrice() + "|" + SaveData[i].getInadequate());
      }
      pw.close(); 
   }
   
   public Product[] loadProductFile() throws IOException{
      Product[] returnValue = null;
      int ProductNumber = 0;
      int i=0;
      File pfile = new File("Product.txt");
      BufferedWriter bw = null;
      boolean pexist;
      pexist = pfile.exists();
      if(!pexist){
         try{
         bw = new BufferedWriter(new FileWriter("Product.txt"));
          bw.flush();
       } catch (IOException e) {
          e.printStackTrace();
       }finally {
          if(bw != null) try {bw.close(); } catch (IOException e) {}
       }
      }
      BufferedReader br = new BufferedReader(new FileReader("Product.txt"));
      while(br.readLine()!=null) {
         ProductNumber++;
      }
      try{
         br = new BufferedReader(new FileReader("Product.txt"));
      }catch(FileNotFoundException e){
         e.printStackTrace();
      }catch(IOException e){
         e.printStackTrace();
      }
      returnValue = new Product[ProductNumber];
      for(i=0;i<ProductNumber;i++) {
        returnValue[i] = new Product();
        String line = br.readLine();
         if (line==null) break;
         StringTokenizer st = new StringTokenizer(line,"|");
         returnValue[i].setName(st.nextToken());
         returnValue[i].setAmount(Integer.parseInt(st.nextToken()));
         returnValue[i].setDiscountRate(Double.parseDouble(st.nextToken()));
         returnValue[i].setNormalPrice(Integer.parseInt(st.nextToken()));
         returnValue[i].setBuyingPrice();
         returnValue[i].setBarCode(st.nextToken());
         returnValue[i].setProductCode(st.nextToken());
         returnValue[i].setClient(st.nextToken());
         returnValue[i].setExpirationDate(st.nextToken());
         returnValue[i].setContainer(st.nextToken());
         returnValue[i].setOriginalPrice(Integer.parseInt(st.nextToken()));
         returnValue[i].setInadequate(Integer.parseInt(st.nextToken()));
      }
  
      br.close();
      
      return returnValue;
   }
}