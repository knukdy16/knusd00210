package ProductManagement; // �ѱ۷� ��ǰ����

import java.io.IOException;

import DataManagement.StoreDeliverFile;

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
		if(LoadProduct == null) { // ��ǰ��ȣ�� Ȯ���ؼ� �ش� ��ǰ�� �ִ��� Ȯ���ؼ� ����
			returnValue = false;
		} else {
			if(LoadProduct.updateProductList(null, this.getAmount(), -1) == true) { // ���� ������Ʈ
				saveDeliver(this); // �� �������� �ǽ�
			} else {
				returnValue = false;
			}
		}
		return returnValue;
	}
	
	public int returnTotalDeliverAmount(String Barcode) throws IOException {
		// file�κ��� data�� load�ؼ� return���� Array�� List�� ����.
		// �� �� �Է¹��� Barcode�� �������� �� �԰��� ����ؼ� ��ȯ
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
	
	private void saveDeliver(Deliver Input) throws IOException { // ������ class�� file�� ����
		StoreDeliverFile Saver = new StoreDeliverFile();
		Saver.saveDeliverFile(Input);
	}
}
