package ProductManagement; // �ѱ۷� ��ǰ����

import java.text.SimpleDateFormat;
import java.util.Date;

import DataManagement.StoreDeliverFile;

public class Store { // �ѱ�� �԰�
	/*
		���� status
	- inputStore method �ۼ���.
	- searchStore method �ۼ� �ʿ�.
	- saveStore method�� File I/O class �ϼ� ������ �ۼ� ���
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
	
	public Store(int i) {
		switch(i) {
		case 0:
			this.storePrice = 10;
			this.storeAmount = 100;
			this.storeDate = "2017-12-25";
			this.storeName = "Samsung Galaxy Note 8";
			this.storeCode = "12345678901231";
			this.storeClient = "Samsung Electronics";
			break;
		case 1:
			this.storePrice = 10;
			this.storeAmount = 100;
			this.storeDate = "2017-12-26";
			this.storeName = "Samsung Galaxy Note 8";
			this.storeCode = "12345678901231";
			this.storeClient = "Samsung Electronics";
			break;
		case 2:
			this.storePrice = 10;
			this.storeAmount = 100;
			this.storeDate = "2017-11-25";
			this.storeName = "Samsung Galaxy Note 8";
			this.storeCode = "12345678901231";
			this.storeClient = "Samsung Electronics";
			break;
		case 3:
			this.storePrice = 10;
			this.storeAmount = 100;
			this.storeDate = "2016-12-24";
			this.storeName = "Samsung Galaxy Note 8";
			this.storeCode = "12345678901231";
			this.storeClient = "Samsung Electronics";
			break;
		case 4:
			this.storePrice = 10;
			this.storeAmount = 100;
			this.storeDate = "2017-12-25";
			this.storeName = "Samsung Galaxy Note 8";
			this.storeCode = "12345678901231";
			this.storeClient = "Samsung Electronics";
			break;
		case 5:
			this.storePrice = 10;
			this.storeAmount = 100;
			this.storeDate = "2018-12-25";
			this.storeName = "Samsung Galaxy Note 8";
			this.storeCode = "12345678901231";
			this.storeClient = "Samsung Electronics";
			break;
		}
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
	
	public void setCode(String Code) {
		Store[] List = null;
		StoreDeliverFile LoadStore = new StoreDeliverFile();
		List = LoadStore._loadStoreFile();
		// ���ڵ� ��ȣ�� �Է¹޾Ƽ� ��ǰ��ȣ�� ��ȯ��Ų��.
		// �̸� ���ؼ�, ��ǰ����� �о ���� ������ ���ڸ� �ο��Ѵ�.
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
	
	public void inputStore(String ExpirationDate, String Container, int OriginalPrice, int Inadequate) {
		// Eclipse �ܼ� â���� �ǽ��ϸ� �ѱ� �Է� �� ������ �ֽ��ϴ�. �Է��� �� curser�� �������� �����ؾ���� �ؿ�(...)
		// UI �������� ��, �Է°��� �޾Ƽ� ��ȯ�޴� ����� ����ϴ°� ���� ��. Switch ������ ���� ��������.
		Product MadeProduct = new Product(ExpirationDate, Container, OriginalPrice, Inadequate); // �����ڸ� ���� ��ǰ ���� ���� �� ����ȴ�.
		MadeProduct.updateProductList(this, -1, -1); // ���� ������Ʈ
		saveStore(this); // �� ������ �κп� �ǽ�
	}
	
	public Store searchStore() {
		Store result = null;
		return result;
	}
	
	public int getTotalStoreAmount(String BarCode) {
		// file�κ��� data�� load�ؼ� Array�� ����.
		// �� �� �Է¹��� Barcode�� �������� �� �԰��� ����ؼ� ��ȯ
		Store[] StoreList;
		int TotalAmount = 0;
		return TotalAmount;
	}
	
	public int getTotalStoreAmount30(String BarCode) {
		Store[] StoreList;
		String CurrentDate = null;
		long DateTemp = 0;
		int TotalAmount = 0;
		Date DateLoader = new Date();
		//DateTemp = Date.getDate();
		
		return TotalAmount;
	}
	
	private void saveStore(Store Input) { // ������ class�� file�� ����
		System.out.println("�� method�� �԰� ������ file�� �����ŵ�ϴ�.");
		System.out.println("Name: " + Input.getName());
		System.out.println("Amount: " + Input.getAmount());
		System.out.println("Date: " + Input.getStoreDate());
		System.out.println("Price: " + Input.getPrice());
		System.out.println("Code: " + Input.getCode());
		System.out.println("Client: " + Input.getClient());
	}
}
