package ProductManagement; // 한글로 물품관리

import java.io.IOException;

import DataManagement.StoreDeliverFile;

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
	
	public Deliver() {
		this.deliverPrice = 0;
		this.deliverAmount = 0;
		this.deliverDate = null;
		this.deliverName = null;
		this.deliverCode = null;
		this.deliverClient = null;
	}
	
	public Deliver(int Price, int Amount, String Date, String Name, String Code, String Client) {
		this.deliverPrice = Price;
	    this.deliverAmount = Amount;
	    this.deliverDate = new String(Date);
	    this.deliverName = new String(Name);
	    this.deliverCode = new String(Code);
	    this.deliverClient = new String(Client);
	}
	
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
	
	public String getDeliverDate() {
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
	
	public String toString() {
		return deliverName + "\t" + deliverAmount + "\t" + deliverDate + "\t" + deliverPrice + "\t" + deliverCode + "\t" + deliverClient;
	}
	
	public boolean inputDeliver() throws IOException {
		boolean returnValue = true;
		ProductList List = new ProductList();
		
		Product LoadProduct = List.searchProduct(this.deliverCode);
		if(LoadProduct == null) { // 물품번호를 확인해서 해당 물품이 있는지 확인해서 적용
			returnValue = false;
		} else {
			if(LoadProduct.updateProductList(null, this.getAmount(), -1) == true) { // 정보 업데이트
				saveDeliver(this); // 맨 마지막에 실시
			} else {
				returnValue = false;
			}
		}
		return returnValue;
	}
	
	public int returnTotalDeliverAmount(String Barcode) throws IOException {
		// file로부터 data를 load해서 return받은 Array를 List에 저장.
		// 그 후 입력받은 Barcode를 바탕으로 총 입고량을 계산해서 반환
		Deliver[] DeliverList;		
		int TotalAmount = 0;
		
		StoreDeliverFile LoadList = new StoreDeliverFile();
		DeliverList = LoadList.loadDeliverFile();
		
		for(int i = 0; i < DeliverList.length; i++) {
			if(Barcode.compareTo(DeliverList[i].getCode().substring(0, 12)) == 0) {
				TotalAmount += DeliverList[i].getAmount();
			}
		}
		
		return TotalAmount;
	}
	
	private void saveDeliver(Deliver Input) throws IOException { // 생성한 class를 file로 저장
		StoreDeliverFile Saver = new StoreDeliverFile();
		Saver.saveDeliverFile(Input);
	}
}
