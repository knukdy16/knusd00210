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
import ProductManagement.Store;

public class StoreDeliverFile {
   public void saveStoreFile(Store SaveData) throws IOException{
	   PrintWriter pw = new PrintWriter(new FileWriter("Store.txt", true));
     
       pw.println(SaveData.getPrice() + "|" + SaveData.getAmount() + "|" + SaveData.getStoreDate() 
       + "|" + SaveData.getName()  + "|" + SaveData.getCode() +"|" + SaveData.getClient());
           
       //(deliverPrice)|(deliverAmount)|(deliverDate)|(deliverName)|(deliverCode)|(deliverClient)
       pw.close();
   }
   
   public Store[] loadStoreFile() throws IOException{
      Store[] returnValue = null;
      int StoreNumber = 0;
      int i=0;
      File sfile = new File("Store.txt");
      BufferedWriter bw = null;
      boolean sexist;
      sexist = sfile.exists();
      if(!sexist){
    	  try {
              bw = new BufferedWriter(new FileWriter("Store.txt"));
           } catch (IOException e) {
              e.printStackTrace();
           }finally {
              if(bw != null) try {bw.close(); } catch (IOException e) {}
           }
      }
      BufferedReader br = new BufferedReader(new FileReader("Store.txt"));
      while(br.readLine()!=null) {
    	  StoreNumber++;
      }
      try{
    	  br = new BufferedReader(new FileReader("Store.txt"));
      }catch(FileNotFoundException e){
    	  e.printStackTrace();
      }catch(IOException e){
    	  e.printStackTrace();
      }
      returnValue = new Store[StoreNumber];
      for(i=0;i<StoreNumber;i++) {
        
    	 String line = br.readLine();
         if (line==null) break;
         StringTokenizer st = new StringTokenizer(line,"|");
         returnValue[i] = new Store(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken());
      }
  
      br.close();
      return returnValue;
   }
   public void saveDeliverFile(Deliver SaveData) throws IOException{
	   PrintWriter pw = new PrintWriter(new FileWriter("Deliver.txt", true));
     
       pw.println(SaveData.getPrice() + "|" + SaveData.getAmount() + "|" + SaveData.getDeliverDate() 
       + "|" + SaveData.getName()  + "|" + SaveData.getCode() +"|" + SaveData.getClient());
           
       //(deliverPrice)|(deliverAmount)|(deliverDate)|(deliverName)|(deliverCode)|(deliverClient)
       pw.close();
   }

   public Deliver[] loadDeliverFile() throws IOException{
		      Deliver[] returnValue = null;
		      int StoreNumber = 0;
		      int i=0;
		      BufferedWriter bw = null;
		      File dfile = new File("Deliver.txt");
		      boolean dexists;
		      dexists = dfile.exists();
		      if(!dexists){
		    	  try {
		              bw = new BufferedWriter(new FileWriter("Deliver.txt"));
		           } catch (IOException e) {
		              e.printStackTrace();
		           }finally {
		              if(bw != null) try {bw.close(); } catch (IOException e) {}
		           }
		      }
		      BufferedReader br = new BufferedReader(new FileReader("Deliver.txt"));
		      while(br.readLine()!=null) {
		    	  StoreNumber++;
		      }
		      try{
		    	  br = new BufferedReader(new FileReader("Deliver.txt"));
		      }catch(FileNotFoundException e){
		    	  e.printStackTrace();
		      }catch(IOException e){
		    	  e.printStackTrace();
		      }
		      returnValue = new Deliver[StoreNumber];
		      for(i=0;i<StoreNumber;i++) {
		    	 String line = br.readLine();
		         if (line==null) break;
		         StringTokenizer st = new StringTokenizer(line,"|");
		         returnValue[i] = new Deliver(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken());
		      }
		      br.close();
		      return returnValue;
		   }
}