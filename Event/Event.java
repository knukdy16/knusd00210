package Event;
import java.io.IOException;
import java.util.Scanner;

import DataManagement.ProductFile;
import ProductManagement.Product;
import ProductManagement.ProductList;


public class Event {
   private Product LoadedProduct;
   private String ProductCode;
   private double sale;
   
   public void setProductCode(String name){
      this.ProductCode = name;
   }
   
   public void setsale(double sale){
      this.sale = sale;
   }
   
   
   public void settingSale() throws IOException{
      ProductList p_l = new ProductList();
      Scanner scan = new Scanner(System.in); 
      
      
      LoadedProduct = p_l.searchProduct(ProductCode);
      if(LoadedProduct == null){
         System.err.println("물품이 존재하지 않습니다.");
      }
      else{
         renewalSale(sale);
      }
      
      
      
   }
   private void renewalSale(double sale) throws IOException {
         Product value[];
         int tar =0;
         LoadedProduct.setDiscountRate(sale);
         LoadedProduct.setBuyingPrice();
         Product temp = new Product();
         temp.setDiscountRate(sale);
         temp.setName(ProductCode);
         ProductFile saver = new ProductFile();
         value = saver.loadProductFile();
         for(int i = 0; i < value.length; i++) {
            if(ProductCode.compareTo(value[i].getName()) == 0) {
               tar = i;
               value[i].setDiscountRate(sale);
            }
         }
         saver.saveProductFile(value);
         System.out.println("할인율을 반영합니다.");
      }
}