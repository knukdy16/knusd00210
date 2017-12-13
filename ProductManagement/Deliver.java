package ProductManagement; // �ѱ۷� ��ǰ����

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
	
	public Deliver searchDeliver() {
		Deliver result = null;
		return result;
	}
	
	public int returnTotalDeliverAmount(String BarCode) {
		// file�κ��� data�� load�ؼ� return���� Array�� List�� ����.
		// �� �� �Է¹��� Barcode�� �������� �� �԰��� ����ؼ� ��ȯ
		Deliver[] DeliverList;
		int TotalAmount = 0;
		return TotalAmount;
	}
	
	private void saveDeliver(Deliver Input) { // ������ class�� file�� ����
		System.out.println("�� method�� ��� ������ file�� �����ŵ�ϴ�.");
		System.out.println("Name: " + Input.getName());
		System.out.println("Amount: " + Input.getAmount());
		System.out.println("Date: " + Input.getDeliverDate());
		System.out.println("Price: " + Input.getPrice());
		System.out.println("Code: " + Input.getCode());
		System.out.println("Client: " + Input.getClient());
	}
}
