package ProductManagement; // 한글로 물품관리

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
	
	static public void main(String args[]) {
		Deliver Temp = new Deliver();
		Temp.inputDeliver();
	}
	
	public Deliver() {
		this.deliverPrice = 0;
		this.deliverAmount = 0;
		this.deliverDate = null;
		this.deliverName = null;
		this.deliverCode = null;
		this.deliverClient = null;
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
	
	public Deliver(int i) {
		switch(i) {
		case 0:
			this.deliverPrice = 10;
			this.deliverAmount = 100;
			this.deliverDate = "2017-12-25";
			this.deliverName = "Samsung Galaxy Note 8";
			this.deliverCode = "12345678901231";
			this.deliverClient = "Samsung Electronics";
			break;
		case 1:
			this.deliverPrice = 10;
			this.deliverAmount = 100;
			this.deliverDate = "2017-12-26";
			this.deliverName = "Samsung Galaxy Note 8";
			this.deliverCode = "12345678901231";
			this.deliverClient = "Samsung Electronics";
			break;
		case 2:
			this.deliverPrice = 10;
			this.deliverAmount = 100;
			this.deliverDate = "2017-11-25";
			this.deliverName = "Samsung Galaxy Note 8";
			this.deliverCode = "12345678901231";
			this.deliverClient = "Samsung Electronics";
			break;
		case 3:
			this.deliverPrice = 10;
			this.deliverAmount = 100;
			this.deliverDate = "2016-12-24";
			this.deliverName = "Samsung Galaxy Note 8";
			this.deliverCode = "12345678901231";
			this.deliverClient = "Samsung Electronics";
			break;
		case 4:
			this.deliverPrice = 10;
			this.deliverAmount = 100;
			this.deliverDate = "2017-12-25";
			this.deliverName = "Samsung Galaxy Note 8";
			this.deliverCode = "12345678901231";
			this.deliverClient = "Samsung Electronics";
			break;
		case 5:
			this.deliverPrice = 10;
			this.deliverAmount = 100;
			this.deliverDate = "2018-12-25";
			this.deliverName = "Samsung Galaxy Note 8";
			this.deliverCode = "12345678901231";
			this.deliverClient = "Samsung Electronics";
			break;
		}
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
	
	public boolean inputDeliver() {
		boolean returnValue = true;
		ProductList List = new ProductList();
		
		Product LoadProduct = List._searchProduct();
		// Product LoadProduct = List.searchProduct(this.deliverCode);
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
	
	public Deliver searchDeliver() {
		Deliver result = null;
		return result;
	}
	
	public int returnTotalDeliverAmount(String BarCode) {
		// file로부터 data를 load해서 return받은 Array를 List에 저장.
		// 그 후 입력받은 Barcode를 바탕으로 총 입고량을 계산해서 반환
		Deliver[] DeliverList;
		int TotalAmount = 0;
		return TotalAmount;
	}
	
	private void saveDeliver(Deliver Input) { // 생성한 class를 file로 저장
		System.out.println("이 method는 출고 정보를 file에 저장시킵니다.");
		System.out.println("Name: " + Input.getName());
		System.out.println("Amount: " + Input.getAmount());
		System.out.println("Date: " + Input.getDeliverDate());
		System.out.println("Price: " + Input.getPrice());
		System.out.println("Code: " + Input.getCode());
		System.out.println("Client: " + Input.getClient());
	}
}
