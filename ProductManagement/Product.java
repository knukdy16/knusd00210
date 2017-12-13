package ProductManagement; // �ѱ۷� ��ǰ����

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
	}
	
	/*
	public Product() {
		this.Client = "Samsung Electronics";
		this.Name = "Samsung Galaxy Note 8";
		this.BarCode = "1234567890123";
		this.ProductCode = "12345678901231";
		this.ExpirationDate = "0000-00-00";
		this.Container = "�뱸������ �ϱ� ���3�� ���з�30";
		this.NormalPrice = 1100000;
		this.BuyingPrice = 1100000;
		this.OriginalPrice = 1000000;
		this.Amount = 10;
		this.DiscountRate = 0;
	}
	*/
	
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
	}
	
	public Product(String ExpirationDate, String Container, int OriginalPrice) {
		this.ExpirationDate = new String(ExpirationDate);
		this.Container = new String(Container);
		this.DiscountRate = 0;
		this.OriginalPrice = OriginalPrice;
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
	/*
	public boolean equals(Object otherObject)
	{
	    if (otherObject == null)
	        return false;
	    else if (getClass( ) != otherObject.getClass( ))
	        return false;
	    else
	    {
	        Date otherDate =
	                      (Date)otherObject;
	        return ( month.equals(otherDate.month)
	                  && (day == otherDate.day)
	                  && (year == otherDate.year) );
	    }
    } 
	 */
	
	public void updateProductList(Object StoreData, int amount, double rate) { // ��ǰ��ϰ���(). �ۼ���...
		// update product data from Deliver or rate.
		Store tempStore = new Store();
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
			System.out.println("������ ���� �׽�Ʈ");
			this.setDiscountRate(rate);
			this.setBuyingPrice();
		} else if(amount > 0) { // ��� �� ��ǰ ������ ������ ���
			System.out.println("��� ���� �׽�Ʈ");
			System.out.println("���� �� ���: " + this.getAmount());
			System.out.println("������ ���: " + amount);
			this.setAmount(this.getAmount() - amount);
			System.out.println("���� �� ���: " + this.getAmount());
		} else { // �Է°� Ʋ���� ����
			System.err.println("Error: Product Class method�� Parameter�� �ùٸ��� �ʽ��ϴ�.");
			return;
		}
		
		saveProductList(this); // save class contents to file.
	}
	
	private void saveProductList(Product SaveData) { // ��ǰ�������(). �ۼ� �ʿ�!
		System.out.println("������ �׽�Ʈ ������");
		System.out.println("Name: " + this.getName());
		System.out.println("Amount: " + this.getAmount());
		System.out.println("OriginalPrice: " + this.getOriginalPrice());
		System.out.println("BuyingPrice: " + this.getBuyingPrice());
		System.out.println("DiscountRate: " + this.getDiscountRate());
		System.out.println("BarCode: " + this.getBarCode());
		System.out.println("ProductCode: " + this.getProductCode());
		System.out.println("Client: " + this.getClient());
		System.out.println("ExpirationDate: " + this.getExpirationDate());
		System.out.println("Container: " + this.getContainer());
		System.out.println("OriginalPrice: " + this.getOriginalPrice());
		System.out.println("�� method�� ��ǰ ������ file�� �����ϴ� �����Դϴ�.");
	}
}
