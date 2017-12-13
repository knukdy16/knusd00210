package ProductManagement; // �ѱ۷� ��ǰ����

import java.io.IOException;

import DataManagement.ProductFile;

public class Product { // �ѱ۷� ��ǰ
	/*
		���� status
	- get, set method �ۼ� �Ϸ�
	- constructor �ۼ� �Ϸ�. �׽�Ʈ �ʿ�
	- ��ǰ�������, ��ǰ��ϰ��� method �ۼ���.
	*/
	private String Client;
	private String Name;
	private String BarCode;
	private String ProductCode;
	private String ExpirationDate;
	private String Container;
	private int NormalPrice;
	private int BuyingPrice;
	private int OriginalPrice;
	// ��� ���� �ʿ�
	private int Amount;
	private double DiscountRate;
	private int Inadequate;
	
	public Product() {
		this.Client = new String();
		this.Name = new String();
		this.BarCode = new String();
		this.ProductCode = new String();
		this.ExpirationDate = new String();
		this.Container = new String();
		this.NormalPrice = 0;
		this.BuyingPrice = 0;
		this.OriginalPrice = 0;
		this.Amount = 0;
		this.DiscountRate = 0;
		this.Inadequate = 0;
	}
	
	public Product(Product Search) {
		this.Client = new String(Search.getClient());
		this.Name = new String(Search.getName());
		this.BarCode = new String(Search.getBarCode());
		this.ProductCode = new String(Search.getProductCode());
		this.ExpirationDate = new String(Search.getExpirationDate());
		this.Container = new String(Search.getContainer());
		this.NormalPrice = Search.getNormalPrice();
		this.BuyingPrice = Search.getBuyingPrice();
		this.OriginalPrice = Search.getOriginalPrice();
		this.Amount = Search.getAmount();
		this.DiscountRate = Search.getDiscountRate();
		this.Inadequate = Search.getInadequate();
	}
	
	public Product(String ExpirationDate, String Container, int OriginalPrice, int Inadequate) {
		this.ExpirationDate = new String(ExpirationDate);
		this.Container = new String(Container);
		this.DiscountRate = 0;
		this.OriginalPrice = OriginalPrice;
		this.Inadequate = Inadequate;
	}
	
	public void setAmount(int Amount) {
		this.Amount = Amount;
	}
	
	public void setClient(String Client) {
		this.Client = new String(Client);
	}
	
	public void setName(String Name) {
		this.Name = new String(Name);
	}
	
	public void setBarCode(String BarCode) {
		this.BarCode = new String(BarCode);
	}
	
	public void setProductCode(String ProductCode) {
		this.ProductCode = new String(ProductCode);
	}
	
	public void setExpirationDate(String ExpirationDate) {
		this.ExpirationDate = new String(ExpirationDate);
	}
	
	public void setContainer(String Container) {
		this.Container = new String(Container);
	}

	public void setNormalPrice(int NormalPrice) {
		this.NormalPrice = NormalPrice;
	}
	
	public void setBuyingPrice() {
		this.BuyingPrice = (int)((double)this.NormalPrice * (1-this.DiscountRate));
	}
	
	public void setOriginalPrice(int OriginalPrice) {
		this.OriginalPrice = OriginalPrice;
	}
	
	public void setDiscountRate(double DiscountRate) {
		this.DiscountRate = DiscountRate;
	}
	
	public void setInadequate(int Inadequate) {
		this.Inadequate = Inadequate;
	}
	
	public String getClient() {
		return new String(this.Client);
	}
	
	public String getName() {
		return new String(this.Name);
	}
	
	public String getBarCode() {
		return new String(this.BarCode);
	}
	
	public String getProductCode() {
		return new String(this.ProductCode);
	}
	
	public String getExpirationDate() {
		return new String(this.ExpirationDate);
	}
	
	public String getContainer() {
		return new String (this.Container);
	}
	
	public int getNormalPrice() {
		return this.NormalPrice;
	}
	
	public int getBuyingPrice() {
		return this.BuyingPrice;
	}
	
	public int getOriginalPrice() {
		return this.OriginalPrice;
	}
	
	public int getAmount() {
		return this.Amount;
	}
	
	public double getDiscountRate() {
		return this.DiscountRate;
	
	}
	
	public int getInadequate() {
		return this.Inadequate;
	}
	
	public String toString() {
		// return storeName + "\t" + storeAmount + "\t" + storeDate + "\t" + storePrice + "\t" + storeCode + "\t" + storeClient;
		return Name + "\t" + Client + "\t" + BarCode + "\t" + ProductCode + "\t" + ExpirationDate + "\t" + Container + "\t" + NormalPrice + "\t" + BuyingPrice + "\t" + OriginalPrice + "\t" + Amount + "\t" + DiscountRate + "\t" + Inadequate;
	}
	
	public boolean updateProductList(Object StoreData, int amount, double rate) throws IOException { // ��ǰ��ϰ���(). �ۼ���...
		// update product data from Deliver or rate.
		Store tempStore = new Store();
		// boolean returnValue = true;
		if(StoreData != null) { // ���� ������ ��ǰ class�� �԰� �����͸� ������ ���
			if(tempStore.getClass() != StoreData.getClass()) {
				System.err.println("Error: Object�� Parameter�� Store class type Object�� �ƴմϴ�.");
			} else {
				tempStore = (Store)StoreData;
				this.Client = tempStore.getClient();
				this.Name = tempStore.getName();
				this.ProductCode = tempStore.getCode();
				this.BarCode = this.ProductCode.substring(0, 13);
				this.NormalPrice = tempStore.getPrice();
				this.DiscountRate = 0;
				setBuyingPrice();
				this.Amount = tempStore.getAmount();
			}
		} else if(rate >= 0) { // ������ ������ ���
			this.setDiscountRate(rate);
			this.setBuyingPrice();
		} else if(amount > 0) { // ��� �� ��ǰ ������ ������ ���
			System.out.println("���� �� ���: " + this.getAmount());
			System.out.println("������ ���: " + amount);
			if((this.getAmount() - amount) < 0) {
				System.out.println("����� ���� ����. ����� ���� ������� �����ϴ�.");
				return false;
			}
			this.setAmount(this.getAmount() - amount);
			System.out.println("���� �� ���: " + this.getAmount());
		} else { // parameter�� �ùٸ��� ������
			System.err.println("System Error: Product Class method�� Parameter�� �ùٸ��� �ʽ��ϴ�. ����ڿ��� ���ǹٶ��ϴ�.");
			System.exit(1);
		}
		
		saveProductList(this); // save class contents to file.
		return true;
	}
	
	private void saveProductList(Product SaveData) throws IOException {
		// Product Object�� �޾ƿͼ� FileI/O ���� Class�� Object�� �����ؼ� File�� ����
		int i, index;
		ProductFile Saver = new ProductFile();
		ProductList ListContainer = new ProductList();
		Product[] List = ListContainer.getProductList();
		Product[] Temp;
		
		for(i = 0; i < List.length ; i++) {
			if(SaveData.getProductCode().compareTo(List[i].getProductCode()) == 0) {
				List[i] = new Product(SaveData);
				break;
			}
		}
		
		if(!(i < List.length)) { // ������ ��ȸ�� ���
			// ���� ���� �����Ͱ� ���������� �߰��� �迭 ����
			index = List.length;
			Temp = new Product[index];
			for(int j = 0; j < Temp.length; j++) {
				Temp[j] = new Product(List[j]);
			}
			
			index = Temp.length + 1;
			List = new Product[index];
			
			for(int j = 0; j < Temp.length; j++) {
				List[j] = new Product(Temp[j]);
			}
			List[Temp.length] = new Product(SaveData);
		}
		
		Saver.saveProductFile(List);
	}
}
