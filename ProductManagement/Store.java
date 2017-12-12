package ProductManagement; // 한글로 물품관리

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
	
	public Store() {
		this.storePrice = 0;
		this.storeAmount = 0;
		this.storeDate = null;
		this.storeName = null;
		this.storeCode = null;
		this.storeClient = null;
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
	
	public void inputStore(String ExpirationDate, String Container, int OriginalPrice, int Inadequate) {
		// Eclipse 콘솔 창에서 실시하면 한글 입력 시 문제가 있습니다. 입력할 때 curser를 수동으로 조정해야줘야 해요(...)
		// UI 디자인할 때, 입력값을 받아서 반환받는 방법을 사용하는게 좋을 듯. Switch 같은걸 쓰면 구현가능.
		Product MadeProduct = new Product(ExpirationDate, Container, OriginalPrice, Inadequate); // 생성자를 통해 제품 정보 생성 후 저장된다.
		MadeProduct.updateProductList(this, -1, -1); // 정보 업데이트
		saveStore(this); // 맨 마지막 부분에 실시
	}
	
	public Store searchStore() {
		Store result = null;
		return result;
	}
	
	public int returnTotalStoreAmount(String BarCode) {
		// file로부터 data를 load해서 Array에 저장.
		// 그 후 입력받은 Barcode를 바탕으로 총 입고량을 계산해서 반환
		Store[] StoreList;
		int TotalAmount = 0;
		return TotalAmount;
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
