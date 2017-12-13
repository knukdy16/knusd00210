package ProductManagement; // 한글로 물품관리

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import DataManagement.StoreDeliverFile;

public class Store { // 한길로 입고
	
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
	
	public Store(int Price, int Amount, String Date, String Name, String Code, String Client) {
		this.storePrice = Price;
		this.storeAmount = Amount;
		this.storeDate = new String(Date);
		this.storeName = new String(Name);
		this.storeCode = new String(Code);
		this.storeClient = new String(Client);
	}
	
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
	
	public void setCode(String Code) throws IOException {
		Store[] List = null;
		String TempBarCode;
		int TempNumber = 1;
		int ProductNumber = 1;
		StoreDeliverFile LoadStore = new StoreDeliverFile();
		List = LoadStore.loadStoreFile();
		if(List == null) {
			this.storeCode = new String(Code + ProductNumber);
			return;
		}
		for(int i = 0; i < List.length; i++) {
			TempBarCode = List[i].getCode().substring(0, 13);
			if(TempBarCode.compareTo(Code) == 0) {
				TempNumber = Integer.parseInt(List[i].getCode().substring(13));
				if(ProductNumber == TempNumber) {
					ProductNumber++;
				}
			}
		}
		// 바코드 번호를 입력받아서 물품번호로 변환시킨다.
		// 이를 위해서, 물품목록을 읽어서 가장 마지막 숫자를 부여한다.
		this.storeCode = new String(Code + ProductNumber);
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
	
	public String getStoreDate() {
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
	
	public String toString() {
		return storeName + "\t" + storeAmount + "\t" + storeDate + "\t" + storePrice + "\t" + storeCode + "\t" + storeClient;
	}
	
	public void inputStore(String ExpirationDate, String Container, int OriginalPrice, int Inadequate) throws IOException {
		// Eclipse 콘솔 창에서 실시하면 한글 입력 시 문제가 있습니다. 입력할 때 curser를 수동으로 조정해야줘야 해요(...)
		// UI 디자인할 때, 입력값을 받아서 반환받는 방법을 사용하는게 좋을 듯. Switch 같은걸 쓰면 구현가능.
		Product MadeProduct = new Product(ExpirationDate, Container, OriginalPrice, Inadequate); // 생성자를 통해 제품 정보 생성 후 저장된다.
		MadeProduct.updateProductList(this, -1, -1); // 정보 업데이트
		saveStore(this); // 맨 마지막 부분에 실시
	}
	
	public int getTotalStoreAmount(String Barcode) throws IOException {
		// file로부터 data를 load해서 Array에 저장.
		// 그 후 입력받은 Barcode를 바탕으로 총 입고량을 계산해서 반환
		Store[] StoreList;
		int TotalAmount = 0;
		StoreDeliverFile LoadList = new StoreDeliverFile();
		StoreList = LoadList.loadStoreFile();
		
		for(int i = 0; i < StoreList.length; i++) {
			if(Barcode.compareTo(StoreList[i].getCode().substring(0, 13)) == 0) {
				TotalAmount += StoreList[i].getAmount();
			}
		}
		
		return TotalAmount;
	}
	
	public int getTotalStoreAmount30(String Barcode) throws Exception {
		Store[] StoreList;
		String CurrentDate = null;
		long DateTemp = 0;
		int TotalAmount = 0;
		SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd");
		Date CurrentTime = new Date();
		CurrentDate = Format.format(CurrentTime);
		
		StoreDeliverFile LoadList = new StoreDeliverFile();
		StoreList = LoadList.loadStoreFile();
		
		for(int i = 0; i < StoreList.length; i++) {
			if(Barcode.compareTo(StoreList[i].getCode().substring(0, 13)) == 0) {
				DateTemp = diffOfDate(StoreList[i].getStoreDate(), CurrentDate);
				if(DateTemp >= 30) {
					TotalAmount += StoreList[i].getAmount();
				}
			}
		}
		
		return TotalAmount;
	}
	
	public String getTotalStoreDate(String Barcode) throws IOException {
		String returnValue = null;
		String temp = null;
		Store[] StoreList;
		StoreDeliverFile LoadList = new StoreDeliverFile();
		StoreList = LoadList.loadStoreFile();
		for(int i = 0; i < StoreList.length; i++) {
			if(Barcode.compareTo(StoreList[i].getCode().substring(0, 13)) == 0) {
				temp = new String(StoreList[i].getStoreDate());
				if(returnValue == null || returnValue.compareTo(temp) < 0) {
					returnValue = new String(temp);
				}
			}
		}
		//String
		return returnValue;
	}
	
	private long diffOfDate(String begin, String end) throws Exception {
		SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = Format.parse(begin);
		Date endDate = Format.parse(end);
		long diff = endDate.getTime() - beginDate.getTime();
		long diffDays = diff / (24 * 60 * 60 * 1000);
		
		return diffDays;
	}
	
	private void saveStore(Store Input) throws IOException { // 생성한 class를 file로 저장
		StoreDeliverFile Saver = new StoreDeliverFile();
		Saver.saveStoreFile(Input);
	}
}
