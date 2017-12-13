package ProductManagement; // �ѱ۷� ��ǰ����

import java.util.Scanner;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Deliver { // �ѱ۷� ���
	/*
		���� status
	- get, set method �ۼ� �Ϸ�
	- constructor �ۼ� �Ϸ�. �׽�Ʈ �ʿ�
	- ��ǰ�������, ��ǰ��ϰ��� method �ۼ���.
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
		
		// Eclipse �ܼ� â���� �ǽ��ϸ� �ѱ� �Է� �� ������ �ֽ��ϴ�. �Է��� �� curser�� �������� �����ؾ���� �ؿ�(...)
		// UI �������� ��, �Է°��� �޾Ƽ� ��ȯ�޴� ����� ����ϴ°� ���� ��. Switch ������ ���� ��������.
		System.out.println("��ǰ �̸��� �Է����ֽʽÿ�");
		this.setName(Input.nextLine());
		System.out.println("�ŷ�ó���� �Է����ֽʽÿ�");
		this.setClient(Input.nextLine());
		
		System.out.println("������ڸ� �Է����ֽʽÿ�(yyyy-MM-dd)");
		StrTemp = Input.nextLine();
		while(checkDate(StrTemp) != true) {
			System.out.println("Invaild Format. yyyy-MM-dd�� ���缭 �ٽ� �Է����ֽʽÿ�.");
			StrTemp = Input.nextLine();
		}
		this.setDate(StrTemp);
		
		System.out.println("��ǰ��ȣ�� �Է����ֽʽÿ�");
		StrTemp = Input.nextLine();
		while(StrTemp.length() < 13) {
			System.out.println("Invaild Format. ��ǰ��ȣ ���̿� ���� �Է����ֽʽÿ�.");
			StrTemp = Input.nextLine();
		}
		this.setCode(StrTemp);
		
		System.out.println("�ǸŰ����� �� ����(\\)�� �Է����ֽʽÿ�");
		IntTemp = Input.nextInt();
		while(IntTemp <= 0) {
			System.out.println("Invalid value. ������ �ٽ� �Է����ּ���");
			IntTemp = Input.nextInt();
		}
		this.setPrice(Input.nextInt());
		
		System.out.println("��� ������ �Է����ֽʽÿ�");
		IntTemp = Input.nextInt();
		while(IntTemp <= 0) {
			System.out.println("Invalid value. �������� �ٽ� �Է����ּ���");
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
		LoadProduct.updateProductList(null, this.getAmount(), -1); // ���� ������Ʈ
		saveDeliver(this); // �� �������� �ǽ�
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
	
	private void saveDeliver(Deliver Input) { // ������ class�� file�� ����
		System.out.println("�� method�� ��� ������ file�� �����ŵ�ϴ�.");
		System.out.println("Name: " + Input.getName());
		System.out.println("Amount: " + Input.getAmount());
		System.out.println("Date: " + Input.getDate());
		System.out.println("Price: " + Input.getPrice());
		System.out.println("Code: " + Input.getCode());
		System.out.println("Client: " + Input.getClient());
	}
}
