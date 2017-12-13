package ProductManagement; // �ѱ۷� ��ǰ����

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import DataManagement.StoreDeliverFile;

public class Store { // �ѱ�� �԰�
	
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
		// ���ڵ� ��ȣ�� �Է¹޾Ƽ� ��ǰ��ȣ�� ��ȯ��Ų��.
		// �̸� ���ؼ�, ��ǰ����� �о ���� ������ ���ڸ� �ο��Ѵ�.
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
		// Eclipse �ܼ� â���� �ǽ��ϸ� �ѱ� �Է� �� ������ �ֽ��ϴ�. �Է��� �� curser�� �������� �����ؾ���� �ؿ�(...)
		// UI �������� ��, �Է°��� �޾Ƽ� ��ȯ�޴� ����� ����ϴ°� ���� ��. Switch ������ ���� ��������.
		Product MadeProduct = new Product(ExpirationDate, Container, OriginalPrice, Inadequate); // �����ڸ� ���� ��ǰ ���� ���� �� ����ȴ�.
		MadeProduct.updateProductList(this, -1, -1); // ���� ������Ʈ
		saveStore(this); // �� ������ �κп� �ǽ�
	}
	
	public int getTotalStoreAmount(String Barcode) throws IOException {
		// file�κ��� data�� load�ؼ� Array�� ����.
		// �� �� �Է¹��� Barcode�� �������� �� �԰��� ����ؼ� ��ȯ
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
	
	private void saveStore(Store Input) throws IOException { // ������ class�� file�� ����
		StoreDeliverFile Saver = new StoreDeliverFile();
		Saver.saveStoreFile(Input);
	}
}
