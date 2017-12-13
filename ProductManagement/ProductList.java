package ProductManagement; // �ѱ۷� ��ǰ����

import java.util.Arrays;

public class ProductList { // �ѱ۷� ��ǰ���
	/*
		���� status
	- ��ǰ�˻� method �ۼ��ʿ�.
	*/
	
	private Product[] List;
	// ��ǰ�� ���� Array�� ��� �ִٰ� ��������.
	// �����ؼ� ����ϰ��� �� ���, Array ��ü�� ���� ���� ��, �ű⼭ ���ϴ� ������ data�� �ش��ϴ��� Ȯ�� �� �ش��ϴ� ������ ���
	
	static public void main(String args[]) {
		ProductList test = new ProductList();
		Product[] list;
		list = test._getProductList();
		for(int i = 0; i < 6; i++) {
			System.out.println("Name: " + list[i].getName());
			System.out.println("Amount: " + list[i].getAmount());
			System.out.println("BarCode: " + list[i].getBarCode());
			System.out.println("ProductCode: " + list[i].getProductCode());
			System.out.println("----------------------------------------");
		}
	}
	
	public ProductList() {
		List = null;
	}
	
	public void setProductList() { // Product Object's Array�� ����
		
	}
	
	public void _setProductList() { // Product Object's Array�� ����
		
	}
	
	public Product[] getProductList() { // Product Object's Array�� ��ȯ
		Arrays.sort(List, new CompatatorProduct());
		return List;
		// ��ȯ���� Array�� ��� � Ȱ��
	}
	
	public Product[] _getProductList() { // Product Object's Array�� ��ȯ
		int index = 6;
		List = new Product[index];
		for(int i = 0; i < 6; i++) {
			List[i] = new Product(i);
		}
		
		Arrays.sort(List, new CompatatorProduct());
		
		return List;
		// ��ȯ���� Array�� ��� � Ȱ��
	}
	
	public Product searchProduct(String ProductCode) { // ��ǰ�˻�(). ������ �Է¹޾Ƽ� Product Object's Array���� �ش��ϴ� ������ ã�Ƽ� Product ��ü�� ��ȯ.
		Product Search = null;
		this.setProductList();
		// Array�κ��� Data �ִ��� Ȯ��
		
		
		return Search;
	}
	
	public Product _searchProduct() { // ��ǰ�˻�(). ������ �Է¹޾Ƽ� Product ��ü�� ��ȯ. ��ȯ���ø� ���� example��, ���� ���� ���� ���.
		Product Search = new Product();
		Search.setClient("Samsung Electronics");
		Search.setName("Samsung Galaxy Note 8");
		Search.setBarCode("1234567890123");
		Search.setProductCode("12345678901231");
		Search.setExpirationDate("0000-00-00");
		Search.setContainer("�뱸������ �ϱ� ���3�� ���з� 30");
		Search.setNormalPrice(1100000);
		Search.setDiscountRate(0);
		Search.setBuyingPrice();
		Search.setOriginalPrice(1000000);
		Search.setAmount(100);
		return new Product(Search);
	}
	
	
}
