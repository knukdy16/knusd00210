package Event;
import java.util.Scanner;

import ProductManagement.Product;
import ProductManagement.ProductList;


public class Event {
	private Product p;
	
	
	public void settingSale(String ProductCode, double sale){
		ProductList p_l = null;
		Scanner scan = new Scanner(System.in); 
		
		
		p = p_l.searchProduct(ProductCode);
		if(p == null){
			System.err.println("no item");
		}
		else{
			renewalSale(sale);
			
		}
		
		
		
	}
	private void renewalSale(double sale) {
		
		p.setDiscountRate(sale);
		p.setBuyingPrice();

		System.out.println("renewal Sale");
	}
}
