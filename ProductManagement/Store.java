package ProductManagement; // 한글로 물품관리

import java.util.Scanner;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Store { // 한길로 입고
	/*
		현재 status
	- inputStore method 작성중.
	- searchStore method 작성 필요.
	- saveStore method는 File I/O class 완성 전까지 작성 대기
	*/
	
	private int storePrice;
	private int storeAmount;
	private String storeDate;
	private String storeName;
	private String storeCode;
	private String storeClient;
	
	static public void main(String args[]) throws IOException, InterruptedException {
		Store Temp = new Store();
		Temp.inputStore();
	}
	
	public Store() {
		this.storePrice = 0;
		this.storeAmount = 0;
		// this.storeDate = new String();
		// this.storeName = new String();
		// this.storeCode = new String();
		// this.storeClient = new String();
	}
	
	/*
	public Store() {
		this.storePrice = 10;
		this.storeAmount = 100;
		this.storeDate = "2017-12-25";
		this.storeName = "Samsung Galaxy Note 8";
		this.storeCode = "12345678901231";
		this.storeClient = "Samsung Electronics";
	}
	*/
	public void setPrice(int Price) {
		this.storePrice = Price;
	}
	
	public void setAmount(int Amount) {
		this.storeAmount = Amount;
	}
	
	public void setDate(String Date) {
		this.storeDate = new String(Date);
	}
	
	public void setName(String Name) {
		this.storeName = new String(Name);
	}
	
	public void setCode(String Code) {
		// 바코드 번호를 입력받아서 물품번호로 변환시킨다.
		// 이를 위해서, 물품목록을 읽어서 가장 마지막 숫자를 부여한다.
		this.storeCode = new String(Code);
	}
	
	public void setClient(String Client) {
		this.storeClient = new String(Client);
	}
	
	public int getPrice() {
		return this.storePrice;
	}
	
	public int getAmount() {
		return this.storeAmount;
	}
	
	public String getDate() {
		return new String(this.storeDate);
	}
	
	public String getName() {
		return new String(this.storeName);
	}
	
	public String getCode() {
		return new String(this.storeCode);
	}
	
	public String getClient() {
		return new String(this.storeClient);
	}
	
	public void inputStore() throws IOException, InterruptedException {
		Scanner Input = new Scanner(System.in);
		String StrTemp;
		int IntTemp;
		String ExpirationDate;
		String Container;
		int OriginalPrice;
		
		// Eclipse 콘솔 창에서 실시하면 한글 입력 시 문제가 있습니다. 입력할 때 curser를 수동으로 조정해야줘야 해요(...)
		// UI 디자인할 때, 입력값을 받아서 반환받는 방법을 사용하는게 좋을 듯. Switch 같은걸 쓰면 구현가능.
		System.out.println("상품 이름을 입력해주십시오");
		this.setName(Input.nextLine());
		System.out.println("거래처명을 입력해주십시오");
		this.setClient(Input.nextLine());
		System.out.println("창고이름 또는 위치를 입력해주십시오");
		Container = new String(Input.nextLine());
		
		System.out.println("입고일자를 입력해주십시오(yyyy-MM-dd)");
		StrTemp = Input.nextLine();
		while(checkDate(StrTemp) != true) {
			System.out.println("Invaild Format. yyyy-MM-dd에 맞춰서 다시 입력해주십시오.");
			StrTemp = Input.nextLine();
		}
		this.setDate(StrTemp);
		
		System.out.println("바코드번호를 입력해주십시오");
		StrTemp = Input.nextLine();
		while(StrTemp.length() < 13) {
			System.out.println("Invaild Format. 바코드 번호 길이에 맞춰서 다시 입력해주십시오.");
			StrTemp = Input.nextLine();
		}
		this.setCode(StrTemp);
		
		System.out.println("유통기한을 입력해주십시오(yyyy-MM-dd) (없는 제품이면 '0000-00-00'을 입력해주십시오)");
		StrTemp = Input.nextLine();
		while(checkDate(StrTemp) != true) {
			System.out.println("Invaild Format. yyyy-MM-dd에 맞춰서 다시 입력해주십시오.");
			StrTemp = Input.nextLine();
		}
		ExpirationDate = new String(StrTemp);
		
		System.out.println("판매가격을 원 단위(\\)로 입력해주십시오");
		IntTemp = Input.nextInt();
		while(IntTemp <= 0) {
			System.out.println("Invalid value. 가격을 다시 입력해주세요");
			IntTemp = Input.nextInt();
		}
		this.setPrice(Input.nextInt());
		
		System.out.println("입고 수량을 입력해주십시오");
		IntTemp = Input.nextInt();
		while(IntTemp <= 0) {
			System.out.println("Invalid value. 출고수량을 다시 입력해주세요");
			IntTemp = Input.nextInt();
		}
		this.setAmount(IntTemp);
		
		System.out.println("원가를 원 단위(\\)로 입력해주십시오");
		IntTemp = Input.nextInt();
		while(IntTemp <= 0) {
			System.out.println("Invalid value. 가격을 다시 입력해주세요");
			IntTemp = Input.nextInt();
		}
		OriginalPrice = IntTemp;
		
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		
		System.out.println("Name: " + this.getName());
		System.out.println("Amount: " + this.getAmount());
		System.out.println("Date: " + this.getDate());
		System.out.println("Price: " + this.getPrice());
		System.out.println("Code: " + this.getCode());
		System.out.println("Client: " + this.getClient());
		System.out.println("ExpirationDate: " + ExpirationDate);
		System.out.println("Container: " + Container);
		System.out.println("OriginalPrice: " + OriginalPrice);
		
		Product MadeProduct = new Product(ExpirationDate, Container, OriginalPrice); // 생성자를 통해 제품 정보 생성 후 저장된다.
		MadeProduct.updateProductList(this, -1, -1); // 정보 업데이트
		saveStore(this); // 맨 마지막 부분에 실시
	}
	
	private boolean checkDate(String CheckDate) {
		SimpleDateFormat DateFormat = new SimpleDateFormat();
		Date Date = new Date();
		System.out.println(CheckDate);
		DateFormat.applyPattern("yyyy-MM-dd");
		DateFormat.setLenient(false);
		
		try {
			Date = DateFormat.parse(CheckDate);
		} catch(ParseException e) {
			// System.out.println("false");
			return false;
		}
		// System.out.println("true");
		return true;
	}
	
	public void searchStore() {
		
	}
	
	private void saveStore(Store Input) { // 생성한 class를 file로 저장
		System.out.println("이 method는 입고 정보를 file에 저장시킵니다.");
		System.out.println("Name: " + Input.getName());
		System.out.println("Amount: " + Input.getAmount());
		System.out.println("Date: " + Input.getDate());
		System.out.println("Price: " + Input.getPrice());
		System.out.println("Code: " + Input.getCode());
		System.out.println("Client: " + Input.getClient());
	}
}
