package StockRecordIO;

import java.io.IOException;
import java.util.Scanner;
import ProductManagement.Product;
import ProductManagement.ProductList;

public class ProductUI {
	public void ProductListOutput() throws IOException {
		ProductList LoadList = new ProductList();
		Product[] List = LoadList.getProductList();
		for(int i = 0; i < List.length; i++) {
			System.out.println(List[i].toString());
		}
		System.out.println("��¼��� : <<��ǰ�̸�, �ŷ�ó, ���ڵ�, ��ǰ��ȣ, �������, â�� ��ġ, ����, �ǸŰ�, ����, ���, ������, ��������>>");
	}
	
	public void ProductSearch() throws IOException {
		String StrTemp = null;
		boolean InputCheck;
		Scanner Input = new Scanner(System.in);
		ProductList LoadList = new ProductList();
		
		System.out.println("ã���� �ϴ� ��ǰ�� ��ǰ��ȣ�� �Է����ֽʽÿ�");
		StrTemp = Input.nextLine();
		try {
			Long.parseLong(StrTemp);
			InputCheck = true;
		} catch (NumberFormatException e){
			InputCheck = false;
		}
		while(StrTemp.length() <= 13 || !(InputCheck)) {
			System.out.println("Invaild Format. ��ǰ ��ȣ�� ���̿� ���缭 ���ڷ� �ٽ� �Է����ֽʽÿ�.");
			StrTemp = Input.nextLine();
			try {
				Long.parseLong(StrTemp);
				InputCheck = true;
			} catch (NumberFormatException e){
				InputCheck = false;
			}
		}
		Product Result = LoadList.searchProduct(StrTemp);
		if(Result == null) {
			System.out.println("ã���÷��� ��ǰ�� �����ϴ�.");
		} else {
			System.out.println("��ǰ��ȣ '" + StrTemp + "'�� �ش��ϴ� ��ǰ ������ ������ �����ϴ�.");
			System.out.println("��¼��� : <<��ǰ�̸�, �ŷ�ó, ���ڵ�, ��ǰ��ȣ, �������, â�� ��ġ, ����, �ǸŰ�, ����, ���, ������, ��������>>");
			System.out.println(Result.toString());
		}
	}
}
