package ProductManagement; // 한글로 물품관리

import java.util.Scanner;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Deliver { // 한글로 출고
	/*
		현재 status
	- get, set method 작성 완료
	- constructor 작성 완료. 테스트 필요
	- 물품목록저장, 물품목록갱신 method 작성중.
	 */
	private int deliverPrice;
	private int deliverAmount;
	private String deliverDate;
	private String deliverName;
	private String deliverCode;
	private String deliverClient;
	
	static public void main(String args[]) throws IOException, InterruptedException {
		Deliver Temp = new Deliver();
		Temp.inputDeliver();
	}
	
	public Deliver() {
		this.deliverPrice = 0;
		this.deliverAmount = 0;
		// this.deliverDate = new String();
		// this.deliverName = new String();
		// this.deliverCode = new String();
		// this.deliverClient = new String();
	}
	
	/*
	public Deliver() {
		this.deliverPrice = 10;
		this.deliverAmount = 100;
		this.deliverDate = "2017-12-25";
		this.deliverName = "Samsung Galaxy Note 8";
		this.deliverCode = "12345678901231";
		this.deliverClient = "Samsung Electronics";
	}
	*/
	public void setPrice(int Price) {
		this.deliverPrice = Price;
	}
	
	public void setAmount(int Amount) {
		this.deliverAmount = Amount;
	}
	
	public void setDate(String Date) {
		this.deliverDate = new String(Date);
	}
	
	public void setName(String Name) {
		this.deliverName = new String(Name);
	}
	
	public void setCode(String Code) {
		this.deliverCode = new String(Code);
	}
	
	public void setClient(String Client) {
		this.deliverClient = new String(Client);
	}
	
	public int getPrice() {
		return this.deliverPrice;
	}
	
	public int getAmount() {
		return this.deliverAmount;
	}
	
	public String getDate() {
		return new String(this.deliverDate);
	}
	
	public String getName() {
		return new String(this.deliverName);
	}
	
	public String getCode() {
		return new String(this.deliverCode);
	}
	
	public String getClient() {
		return new String(this.deliverClient);
	}
	
	public void inputDeliver() throws IOException, InterruptedException {
		Scanner Input = new Scanner(System.in);
		String StrTemp;
		int IntTemp;
		
		// Eclipse 콘솔 창에서 실시하면 한글 입력 시 문제가 있습니다. 입력할 때 curser를 수동으로 조정해야줘야 해요(...)
		// UI 디자인할 때, 입력값을 받아서 반환받는 방법을 사용하는게 좋을 듯. Switch 같은걸 쓰면 구현가능.
		System.out.println("상품 이름을 입력해주십시오");
		this.setName(Input.nextLine());
		System.out.println("거래처명을 입력해주십시오");
		this.setClient(Input.nextLine());
		
		System.out.println("출고일자를 입력해주십시오(yyyy-MM-dd)");
		StrTemp = Input.nextLine();
		while(checkDate(StrTemp) != true) {
			System.out.println("Invaild Format. yyyy-MM-dd에 맞춰서 다시 입력해주십시오.");
			StrTemp = Input.nextLine();
		}
		this.setDate(StrTemp);
		
		System.out.println("물품번호를 입력해주십시오");
		StrTemp = Input.nextLine();
		while(StrTemp.length() < 13) {
			System.out.println("Invaild Format. 물품번호 길이에 맞춰 입력해주십시오.");
			StrTemp = Input.nextLine();
		}
		this.setCode(StrTemp);
		
		System.out.println("판매가격을 원 단위(\\)로 입력해주십시오");
		IntTemp = Input.nextInt();
		while(IntTemp <= 0) {
			System.out.println("Invalid value. 가격을 다시 입력해주세요");
			IntTemp = Input.nextInt();
		}
		this.setPrice(Input.nextInt());
		
		System.out.println("출고 수량을 입력해주십시오");
		IntTemp = Input.nextInt();
		while(IntTemp <= 0) {
			System.out.println("Invalid value. 출고수량을 다시 입력해주세요");
			IntTemp = Input.nextInt();
		}
		this.setAmount(IntTemp);
		
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		
		System.out.println("Name: " + this.getName());
		System.out.println("Amount: " + this.getAmount());
		System.out.println("Date: " + this.getDate());
		System.out.println("Price: " + this.getPrice());
		System.out.println("Code: " + this.getCode());
		System.out.println("Client: " + this.getClient());
		
		Product LoadProduct = ProductList._searchProduct();
		LoadProduct.updateProductList(null, this.getAmount(), -1); // 정보 업데이트
		saveDeliver(this); // 맨 마지막에 실시
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
	
	public void searchDeliver() {
		
	}
	
	private void saveDeliver(Deliver Input) { // 생성한 class를 file로 저장
		System.out.println("이 method는 출고 정보를 file에 저장시킵니다.");
		System.out.println("Name: " + Input.getName());
		System.out.println("Amount: " + Input.getAmount());
		System.out.println("Date: " + Input.getDate());
		System.out.println("Price: " + Input.getPrice());
		System.out.println("Code: " + Input.getCode());
		System.out.println("Client: " + Input.getClient());
	}
}
