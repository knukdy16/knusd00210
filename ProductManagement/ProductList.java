package ProductManagement; // �ѱ۷� ��ǰ����

public class ProductList { // �ѱ۷� ��ǰ���
	/*
		���� status
	- ��ǰ�˻� method �ۼ��ʿ�.
	*/
	
	// ��ǰ�� ���� Array�� ��� �ִٰ� ��������.
	// �����ؼ� ����ϰ��� �� ���, Array ��ü�� ���� ���� ��, �ű⼭ ���ϴ� ������ data�� �ش��ϴ��� Ȯ�� �� �ش��ϴ� ������ ���
	
	public ProductList() {
		
	}
	
	public void setProductList() { // Product Object's Array�� ����
		
	}
	
	public void getProductList() { // Product Object's Array�� ��ȯ
		return;
		// ��ȯ���� Array�� ��� � Ȱ��
	}
	
	static public Product searchProduct(String ProductCode) { // ��ǰ�˻�(). ������ �Է¹޾Ƽ� Product Object's Array���� �ش��ϴ� ������ ã�Ƽ� Product ��ü�� ��ȯ.
		Product Search = null;
		
		// Array�κ��� Data �ִ��� Ȯ��
		
		return Search;
	}
	
	static public Product _searchProduct() { // ��ǰ�˻�(). ������ �Է¹޾Ƽ� Product ��ü�� ��ȯ. ��ȯ���ø� ���� example��, ���� ���� ���� ���.
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
