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
		System.out.println("출력순서 : <<물품이름, 거래처, 바코드, 물품번호, 유통기한, 창고 위치, 정상가, 판매가, 원가, 재고량, 할인율, 재고부족량>>");
	}
	
	public void ProductSearch() throws IOException {
		String StrTemp = null;
		boolean InputCheck;
		Scanner Input = new Scanner(System.in);
		ProductList LoadList = new ProductList();
		
		System.out.println("찾고자 하는 물품의 물품번호를 입력해주십시오");
		StrTemp = Input.nextLine();
		try {
			Long.parseLong(StrTemp);
			InputCheck = true;
		} catch (NumberFormatException e){
			InputCheck = false;
		}
		while(StrTemp.length() <= 13 || !(InputCheck)) {
			System.out.println("Invaild Format. 물품 번호를 길이에 맞춰서 숫자로 다시 입력해주십시오.");
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
			System.out.println("찾으시려는 물품이 없습니다.");
		} else {
			System.out.println("물품번호 '" + StrTemp + "'에 해당하는 물품 정보는 다음과 같습니다.");
			System.out.println("출력순서 : <<물품이름, 거래처, 바코드, 물품번호, 유통기한, 창고 위치, 정상가, 판매가, 원가, 재고량, 할인율, 재고부족량>>");
			System.out.println(Result.toString());
		}
	}
}
