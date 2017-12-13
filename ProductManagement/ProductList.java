package ProductManagement; // �ѱ۷� ��ǰ����

import java.io.IOException;
import java.util.Arrays;
import DataManagement.ProductFile;

public class ProductList { // �ѱ۷� ��ǰ���	
	private Product[] List;
	// ��ǰ�� ���� Array�� ��� �ִٰ� ��������.
	// �����ؼ� ����ϰ��� �� ���, Array ��ü�� ���� ���� ��, �ű⼭ ���ϴ� ������ data�� �ش��ϴ��� Ȯ�� �� �ش��ϴ� ������ ���

	public ProductList() throws IOException {
		List = null;
		setProductList();
	}
	
	public void setProductList() throws IOException { // Product Object's Array�� ����
		ProductFile Loader = new ProductFile();
		List = Loader.loadProductFile();
	}
	
	public Product[] getProductList() { // Product Object's Array�� ��ȯ
		Arrays.sort(List, new CompatatorProduct());
		return List;
		// ��ȯ���� Array�� ��� � Ȱ��
	}

	public Product searchProduct(String ProductCode) throws IOException { // ��ǰ�˻�(). ������ �Է¹޾Ƽ� Product Object's Array���� �ش��ϴ� ������ ã�Ƽ� Product ��ü�� ��ȯ.
		Product Search = null;
		this.setProductList();
		// Array�κ��� Data �ִ��� Ȯ��
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
