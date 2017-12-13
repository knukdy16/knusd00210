package ProductManagement; // �ѱ۷� ��ǰ����

import java.util.Scanner;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

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
		
		// Eclipse �ܼ� â���� �ǽ��ϸ� �ѱ� �Է� �� ������ �ֽ��ϴ�. �Է��� �� curser�� �������� �����ؾ���� �ؿ�(...)
		// UI �������� ��, �Է°��� �޾Ƽ� ��ȯ�޴� ����� ����ϴ°� ���� ��. Switch ������ ���� ��������.
		System.out.println("��ǰ �̸��� �Է����ֽʽÿ�");
		this.setName(Input.nextLine());
		System.out.println("�ŷ�ó���� �Է����ֽʽÿ�");
		this.setClient(Input.nextLine());
		System.out.println("â���̸� �Ǵ� ��ġ�� �Է����ֽʽÿ�");
		Container = new String(Input.nextLine());
		
		System.out.println("�԰����ڸ� �Է����ֽʽÿ�(yyyy-MM-dd)");
		StrTemp = Input.nextLine();
		while(checkDate(StrTemp) != true) {
			System.out.println("Invaild Format. yyyy-MM-dd�� ���缭 �ٽ� �Է����ֽʽÿ�.");
			StrTemp = Input.nextLine();
		}
		this.setDate(StrTemp);
		
		System.out.println("���ڵ��ȣ�� �Է����ֽʽÿ�");
		StrTemp = Input.nextLine();
		while(StrTemp.length() < 13) {
			System.out.println("Invaild Format. ���ڵ� ��ȣ ���̿� ���缭 �ٽ� �Է����ֽʽÿ�.");
			StrTemp = Input.nextLine();
		}
		this.setCode(StrTemp);
		
		System.out.println("��������� �Է����ֽʽÿ�(yyyy-MM-dd) (���� ��ǰ�̸� '0000-00-00'�� �Է����ֽʽÿ�)");
		StrTemp = Input.nextLine();
		while(checkDate(StrTemp) != true) {
			System.out.println("Invaild Format. yyyy-MM-dd�� ���缭 �ٽ� �Է����ֽʽÿ�.");
			StrTemp = Input.nextLine();
		}
		ExpirationDate = new String(StrTemp);
		
		System.out.println("�ǸŰ����� �� ����(\\)�� �Է����ֽʽÿ�");
		IntTemp = Input.nextInt();
		while(IntTemp <= 0) {
			System.out.println("Invalid value. ������ �ٽ� �Է����ּ���");
			IntTemp = Input.nextInt();
		}
		this.setPrice(Input.nextInt());
		
		System.out.println("�԰� ������ �Է����ֽʽÿ�");
		IntTemp = Input.nextInt();
		while(IntTemp <= 0) {
			System.out.println("Invalid value. �������� �ٽ� �Է����ּ���");
			IntTemp = Input.nextInt();
		}
		this.setAmount(IntTemp);
		
		System.out.println("������ �� ����(\\)�� �Է����ֽʽÿ�");
		IntTemp = Input.nextInt();
		while(IntTemp <= 0) {
			System.out.println("Invalid value. ������ �ٽ� �Է����ּ���");
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
		
		Product MadeProduct = new Product(ExpirationDate, Container, OriginalPrice); // �����ڸ� ���� ��ǰ ���� ���� �� ����ȴ�.
		MadeProduct.updateProductList(this, -1, -1); // ���� ������Ʈ
		saveStore(this); // �� ������ �κп� �ǽ�
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
	
	private void saveStore(Store Input) { // ������ class�� file�� ����
		System.out.println("�� method�� �԰� ������ file�� �����ŵ�ϴ�.");
		System.out.println("Name: " + Input.getName());
		System.out.println("Amount: " + Input.getAmount());
		System.out.println("Date: " + Input.getDate());
		System.out.println("Price: " + Input.getPrice());
		System.out.println("Code: " + Input.getCode());
		System.out.println("Client: " + Input.getClient());
	}
}
