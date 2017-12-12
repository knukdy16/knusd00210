package ProductManagement; // 한글로 물품관리

import java.util.Arrays;

public class ProductList { // 한글로 물품목록
	/*
		현재 status
	- 물품검색 method 작성필요.
	*/
	
	private Product[] List;
	// 물품에 대한 Array를 담고 있다고 생각하자.
	// 정렬해서 출력하고자 할 경우, Array 자체를 갖고 정렬 후, 거기서 원하는 범위의 data에 해당하는지 확인 후 해당하는 정보만 출력
	
	static public void main(String args[]) {
		ProductList test = new ProductList();
		Product[] list;
		list = test._getProductList();
		for(int i = 0; i < 6; i++) {
			System.out.println("Name: " + list[i].getName());
			System.out.println("Amount: " + list[i].getAmount());
			System.out.println("BarCode: " + list[i].getBarCode());
			System.out.println("ProductCode: " + list[i].getProductCode());
			System.out.println("--------------------------------------------------");
		}
	}
	
	public ProductList() {
		List = null;
	}
	
	public void setProductList() { // Product Object's Array를 생성
		
	}
	
	public void _setProductList() { // Product Object's Array를 생성
		
	}
	
	public Product[] getProductList() { // Product Object's Array를 반환
		Arrays.sort(List, new CompatatorProduct());
		return List;
		// 반환받은 Array로 출력 등에 활용
	}
	
	public Product[] _getProductList() { // Product Object's Array를 반환
		int index = 6;
		List = new Product[index];
		for(int i = 0; i < 6; i++) {
			List[i] = new Product(i);
		}
		
		Arrays.sort(List, new CompatatorProduct());
		
		return List;
		// 반환받은 Array로 출력 등에 활용
	}
	
	static public Product searchProduct(String ProductCode) { // 물품검색(). 정보를 입력받아서 Product Object's Array에서 해당하는 정보를 찾아서 Product 객체를 반환.
		Product Search = null;
		
		// Array로부터 Data 있는지 확인
		
		return Search;
	}
	
	static public Product _searchProduct() { // 물품검색(). 정보를 입력받아서 Product 객체를 반환. 반환예시를 위한 example로, 제출 전에 삭제 요망.
		Product Search = new Product();
		Search.setClient("Samsung Electronics");
		Search.setName("Samsung Galaxy Note 8");
		Search.setBarCode("1234567890123");
		Search.setProductCode("12345678901231");
		Search.setExpirationDate("0000-00-00");
		Search.setContainer("대구광역시 북구 산격3동 대학로 30");
		Search.setNormalPrice(1100000);
		Search.setDiscountRate(0);
		Search.setBuyingPrice();
		Search.setOriginalPrice(1000000);
		Search.setAmount(100);
		return new Product(Search);
	}
	
	
}
