package ProductManagement; // 한글로 물품관리

import java.io.IOException;

import DataManagement.ProductFile;

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
	
	public boolean updateProductList(Object StoreData, int amount, double rate) throws IOException { // 물품목록갱신(). 작성중...
		// update product data from Deliver or rate.
		Store tempStore = new Store();
		// boolean returnValue = true;
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
			this.setDiscountRate(rate);
			this.setBuyingPrice();
		} else if(amount > 0) { // 출고 후 물품 정보를 갱신할 경우
			System.out.println("적용 전 재고량: " + this.getAmount());
			System.out.println("적용할 출고량: " + amount);
			if((this.getAmount() - amount) < 0) {
				System.out.println("출고기록 적용 에러. 출고량이 현재 재고량보다 많습니다.");
				return false;
			}
			this.setAmount(this.getAmount() - amount);
			System.out.println("적용 후 재고량: " + this.getAmount());
		} else { // parameter가 올바르지 않으면
			System.err.println("System Error: Product Class method의 Parameter가 올바르지 않습니다. 담당자에게 문의바랍니다.");
			System.exit(1);
		}
		
		saveProductList(this); // save class contents to file.
		return true;
	}
	
	private void saveProductList(Product SaveData) throws IOException {
		// Product Object를 받아와서 FileI/O 관련 Class를 Object로 생성해서 File에 저장
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
		
		if(!(i < List.length)) { // 끝까지 순회한 경우
			// 새로 들어온 데이터가 마지막으로 추가된 배열 생성
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
