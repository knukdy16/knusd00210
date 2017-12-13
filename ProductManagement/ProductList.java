package ProductManagement; // 한글로 물품관리

import java.io.IOException;
import java.util.Arrays;
import DataManagement.ProductFile;

public class ProductList { // 한글로 물품목록	
	private Product[] List;
	// 물품에 대한 Array를 담고 있다고 생각하자.
	// 정렬해서 출력하고자 할 경우, Array 자체를 갖고 정렬 후, 거기서 원하는 범위의 data에 해당하는지 확인 후 해당하는 정보만 출력

	public ProductList() throws IOException {
		List = null;
		setProductList();
	}
	
	public void setProductList() throws IOException { // Product Object's Array를 생성
		ProductFile Loader = new ProductFile();
		List = Loader.loadProductFile();
	}
	
	public Product[] getProductList() { // Product Object's Array를 반환
		Arrays.sort(List, new CompatatorProduct());
		return List;
		// 반환받은 Array로 출력 등에 활용
	}

	public Product searchProduct(String ProductCode) throws IOException { // 물품검색(). 정보를 입력받아서 Product Object's Array에서 해당하는 정보를 찾아서 Product 객체를 반환.
		Product Search = null;
		this.setProductList();
		// Array로부터 Data 있는지 확인
		for(int i = 0; i < List.length; i++) {
			if(ProductCode.compareTo(List[i].getProductCode()) == 0) {
				Search = new Product(List[i]);
			}
		}
		return Search;
	}
	
	public int getTotalProductAmount(String Barcode) {
		int TotalAmount = 0;
		for(int i = 0; i < List.length; i++) {
			if(Barcode.compareTo(List[i].getBarCode().substring(0, 12)) == 0) {
				TotalAmount += List[i].getAmount();
			}
		}
		
		return TotalAmount;
	}
}
