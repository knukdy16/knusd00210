package ProductManagement; // 한글로 물품관리

public class Product { // 한글로 물품
	/*
		현재 status
	- get, set method 작성 완료
	- constructor 작성 완료. 테스트 필요
	- 물품목록저장, 물품목록갱신 method 작성중.
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
	// 재고가 제거 필요
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
	
	/*
	public Product() {
		this.Client = "Samsung Electronics";
		this.Name = "Samsung Galaxy Note 8";
		this.BarCode = "1234567890123";
		this.ProductCode = "12345678901231";
		this.ExpirationDate = "0000-00-00";
		this.Container = "대구광역시 북구 산격3동 대학로30";
		this.NormalPrice = 1100000;
		this.BuyingPrice = 1100000;
		this.OriginalPrice = 1000000;
		this.Amount = 10;
		this.DiscountRate = 0;
	}
	*/
	public Product(int i) {
		switch(i) {
		case 0:
			this.Client = "Samsung Electronics";
			this.Name = "Samsung Galaxy Note 8_SKT";
			this.BarCode = "1234567890123";
			this.ProductCode = "12345678901231";
			this.ExpirationDate = "0000-00-00";
			this.Container = "대구광역시 북구 산격3동 대학로30";
			this.NormalPrice = 1100000;
			this.BuyingPrice = 1100000;
			this.OriginalPrice = 1000000;
			this.Amount = 10;
			this.DiscountRate = 0;
			break;
		case 1:
			this.Client = "Samsung Electronics";
			this.Name = "Samsung Galaxy Note 8_KT";
			this.BarCode = "1234567890124";
			this.ProductCode = "12345678901241";
			this.ExpirationDate = "0000-00-00";
			this.Container = "대구광역시 북구 산격3동 대학로30";
			this.NormalPrice = 1100000;
			this.BuyingPrice = 1100000;
			this.OriginalPrice = 1000000;
			this.Amount = 10;
			this.DiscountRate = 0;
			break;
		case 2:
			this.Client = "Samsung Electronics";
			this.Name = "Samsung Galaxy Note 8_UPLUS";
			this.BarCode = "1234567890125";
			this.ProductCode = "12345678901251";
			this.ExpirationDate = "0000-00-00";
			this.Container = "대구광역시 북구 산격3동 대학로30";
			this.NormalPrice = 1100000;
			this.BuyingPrice = 1100000;
			this.OriginalPrice = 1000000;
			this.Amount = 10;
			this.DiscountRate = 0;
			break;
		case 3:
			this.Client = "Samsung Electronics";
			this.Name = "Samsung Galaxy Note 8_KT";
			this.BarCode = "1234567890124";
			this.ProductCode = "12345678901242";
			this.ExpirationDate = "0000-00-00";
			this.Container = "대구광역시 북구 산격3동 대학로30";
			this.NormalPrice = 1100000;
			this.BuyingPrice = 1100000;
			this.OriginalPrice = 1000000;
			this.Amount = 10;
			this.DiscountRate = 0;
			break;
		case 4:
			this.Client = "Samsung Electronics";
			this.Name = "Samsung Galaxy Note 8_SKT";
			this.BarCode = "1234567890123";
			this.ProductCode = "12345678901232";
			this.ExpirationDate = "0000-00-00";
			this.Container = "대구광역시 북구 산격3동 대학로30";
			this.NormalPrice = 1100000;
			this.BuyingPrice = 1100000;
			this.OriginalPrice = 1000000;
			this.Amount = 10;
			this.DiscountRate = 0;
			break;
		case 5:
			this.Client = "Samsung Electronics";
			this.Name = "Samsung Galaxy Note 8_SKT";
			this.BarCode = "1234567890123";
			this.ProductCode = "12345678901233";
			this.ExpirationDate = "0000-00-00";
			this.Container = "대구광역시 북구 산격3동 대학로30";
			this.NormalPrice = 1100000;
			this.BuyingPrice = 1100000;
			this.OriginalPrice = 1000000;
			this.Amount = 10;
			this.DiscountRate = 0;
			break;
		}
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
	
	public void updateProductList(Object StoreData, int amount, double rate) { // 물품목록갱신(). 작성중...
		// update product data from Deliver or rate.
		Store tempStore = new Store();
		if(StoreData != null) { // 새로 생성한 물품 class에 입고 데이터를 저장할 경우
			if(tempStore.getClass() != StoreData.getClass()) {
				System.err.println("Error: Object의 Parameter가 Store class type Object가 아닙니다.");
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
		} else if(rate >= 0) { // 할인율 갱신할 경우
			System.out.println("할인율 갱신 테스트");
			this.setDiscountRate(rate);
			this.setBuyingPrice();
		} else if(amount > 0) { // 출고 후 물품 정보를 갱신할 경우
			System.out.println("출고량 적용 테스트");
			System.out.println("적용 전 재고량: " + this.getAmount());
			System.out.println("적용할 출고량: " + amount);
			this.setAmount(this.getAmount() - amount);
			System.out.println("적용 후 재고량: " + this.getAmount());
		} else { // 입력값 틀리게 쓰면
			System.err.println("Error: Product Class method의 Parameter가 올바르지 않습니다.");
			return;
		}
		
		saveProductList(this); // save class contents to file.
	}
	
	private void saveProductList(Product SaveData) { // 물품목록저장(). 작성 필요!
		// Product Object를 받아와서 FileI/O 관련 Class를 Object로 생성해서 File에 저장
		System.out.println("☆저장 테스트 문구★");
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
		System.out.println("이 method는 물품 정보를 file로 저장하는 역할입니다.");
	}
}
