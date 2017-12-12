package StockRecordIO;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.InputMismatchException;
import ProductManagement.Product;
import ProductManagement.Store;
import ProductManagement.Deliver;

public class StoreDeliverIO {
	static public void main(String args[]) throws IOException, InterruptedException {
		StoreDeliverIO io = new StoreDeliverIO();
		io.StoreInput();
		io.DeliverInput();
	}

	public void StoreInput() throws IOException, InterruptedException {
		Store InputStore = new Store();
		Scanner Input = new Scanner(System.in);
		String StrTemp = null;
		int IntTemp = 0;
		String ExpirationDate = null;
		String Container = null;
		int OriginalPrice = 0;
		int Inadequate = 0;
		boolean InputCheck = true; // 입력값 유효성 여부 및 최종 확인용
		
		System.out.println("상품 이름을 입력해주십시오");
		InputStore.setName(Input.nextLine());
		System.out.println("거래처명을 입력해주십시오");
		InputStore.setClient(Input.nextLine());
		System.out.println("창고이름 또는 위치를 입력해주십시오");
		Container = new String(Input.nextLine());
		
		System.out.println("입고일자를 입력해주십시오(yyyy-MM-dd)");
		StrTemp = Input.nextLine();
		while(checkDate(StrTemp) != true) {
			System.out.println("Invaild Format. yyyy-MM-dd에 맞춰서 다시 입력해주십시오.");
			StrTemp = Input.nextLine();
		}
		InputStore.setDate(StrTemp);
		
		System.out.println("바코드번호를 입력해주십시오");
		StrTemp = Input.nextLine();
		try {
			Double.parseDouble(StrTemp);
			InputCheck = true;
		} catch (NumberFormatException e){
			InputCheck = false;
		}
		while(StrTemp.length() != 13 || !(InputCheck)) {
			System.out.println("Invaild Format. 바코드 번호 길이에 맞춰서 숫자로 다시 입력해주십시오.");
			StrTemp = Input.nextLine();
			try {
				Long.parseLong(StrTemp);
				InputCheck = true;
			} catch (NumberFormatException e){
				InputCheck = false;
			}
		}
		InputStore.setCode(StrTemp);
		
		System.out.println("유통기한을 입력해주십시오(yyyy-MM-dd) (없는 제품이면 '0000-00-00'을 입력해주십시오)");
		StrTemp = Input.nextLine();
		while(checkDate(StrTemp) != true) {
			if(StrTemp.equals("0000-00-00"))
				break;
			System.out.println("Invaild Format. yyyy-MM-dd에 맞춰서 다시 입력해주십시오.");
			StrTemp = Input.nextLine();
		}
		ExpirationDate = new String(StrTemp);
		
		System.out.println("판매가격을 원 단위(\\)로 입력해주십시오");
		try {
			IntTemp = Input.nextInt();
			InputCheck = true;
		} catch(InputMismatchException e) {
			InputCheck = false;
		}
		while(!(InputCheck) || IntTemp <= 0) {
			System.out.println("Invalid value. 가격을 다시 입력해주세요");
			Input.nextLine();
			try {
				IntTemp = Input.nextInt();
				InputCheck = true;
			} catch(InputMismatchException e) {
				InputCheck = false;
			}
		}
		InputStore.setPrice(IntTemp);
		
		System.out.println("입고 수량을 입력해주십시오");
		try {
			IntTemp = Input.nextInt();
			InputCheck = true;
		} catch(InputMismatchException e) {
			InputCheck = false;
		}
		while(!(InputCheck) || IntTemp <= 0) {
			System.out.println("Invalid value. 출고수량을 다시 입력해주세요");
			Input.nextLine();
			try {
				IntTemp = Input.nextInt();
				InputCheck = true;
			} catch(InputMismatchException e) {
				InputCheck = false;
			}
		}
		InputStore.setAmount(IntTemp);
		
		System.out.println("원가를 원 단위(\\)로 입력해주십시오");
		try {
			IntTemp = Input.nextInt();
			InputCheck = true;
		} catch(InputMismatchException e) {
			InputCheck = false;
		}
		while(!(InputCheck) || IntTemp <= 0) {
			System.out.println("Invalid value. 원가를 다시 입력해주세요");
			Input.nextLine();
			try {
				IntTemp = Input.nextInt();
				InputCheck = true;
			} catch(InputMismatchException e) {
				InputCheck = false;
			}
		}
		OriginalPrice = IntTemp;
		
		System.out.println("부족알림 기준량을 입력해주십시오");
		try {
			IntTemp = Input.nextInt();
			InputCheck = true;
		} catch(InputMismatchException e) {
			InputCheck = false;
		}
		while(!(InputCheck) || IntTemp < 0) {
			System.out.println("Invalid value. 기준량을 다시 입력해주세요");
			Input.nextLine();
			try {
				IntTemp = Input.nextInt();
				InputCheck = true;
			} catch(InputMismatchException e) {
				InputCheck = false;
			}
		}
		Inadequate = IntTemp;
		Input.nextLine();
		
		InputCheck = checkStoreInput(InputStore, ExpirationDate, Container, OriginalPrice, Inadequate);
		if(!(InputCheck)) {
			// 다시 입력받아서 확인
			while(!(InputCheck)) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				System.out.println("상품 이름을 입력해주십시오[" + InputStore.getName() + "]");
				InputStore.setName(Input.nextLine());
				System.out.println("거래처명을 입력해주십시오[" + InputStore.getClient() + "]");
				InputStore.setClient(Input.nextLine());
				System.out.println("창고이름 또는 위치를 입력해주십시오[" + Container + "]");
				Container = new String(Input.nextLine());
				
				System.out.println("입고일자를 입력해주십시오(yyyy-MM-dd)[" + InputStore.getDate() + "]");
				StrTemp = Input.nextLine();
				while(checkDate(StrTemp) != true) {
					System.out.println("Invaild Format. yyyy-MM-dd에 맞춰서 다시 입력해주십시오.");
					StrTemp = Input.nextLine();
				}
				InputStore.setDate(StrTemp);
				
				System.out.println("바코드번호를 입력해주십시오[" + InputStore.getCode() + "]");
				StrTemp = Input.nextLine();
				try {
					Double.parseDouble(StrTemp);
					InputCheck = true;
				} catch (NumberFormatException e){
					InputCheck = false;
				}
				while(StrTemp.length() != 13 || !(InputCheck)) {
					System.out.println("Invaild Format. 바코드 번호 길이에 맞춰서 숫자로 다시 입력해주십시오.");
					StrTemp = Input.nextLine();
					try {
						Long.parseLong(StrTemp);
						InputCheck = true;
					} catch (NumberFormatException e){
						InputCheck = false;
					}
				}
				InputStore.setCode(StrTemp);
				
				System.out.println("유통기한을 입력해주십시오(yyyy-MM-dd)[" + ExpirationDate + "]");
				StrTemp = Input.nextLine();
				while(checkDate(StrTemp) != true) {
					if(StrTemp.equals("0000-00-00"))
						break;
					System.out.println("Invaild Format. yyyy-MM-dd에 맞춰서 다시 입력해주십시오.");
					StrTemp = Input.nextLine();
				}
				ExpirationDate = new String(StrTemp);
				
				System.out.println("판매가격을 원 단위(\\)로 입력해주십시오[" + InputStore.getPrice() + "]");
				try {
					IntTemp = Input.nextInt();
					InputCheck = true;
				} catch(InputMismatchException e) {
					InputCheck = false;
				}
				while(!(InputCheck) || IntTemp <= 0) {
					System.out.println("Invalid value. 가격을 다시 입력해주세요");
					Input.nextLine();
					try {
						IntTemp = Input.nextInt();
						InputCheck = true;
					} catch(InputMismatchException e) {
						InputCheck = false;
					}
				}
				InputStore.setPrice(IntTemp);
				
				System.out.println("입고 수량을 입력해주십시오[" + InputStore.getAmount() + "]");
				try {
					IntTemp = Input.nextInt();
					InputCheck = true;
				} catch(InputMismatchException e) {
					InputCheck = false;
				}
				while(!(InputCheck) || IntTemp <= 0) {
					System.out.println("Invalid value. 출고수량을 다시 입력해주세요");
					Input.nextLine();
					try {
						IntTemp = Input.nextInt();
						InputCheck = true;
					} catch(InputMismatchException e) {
						InputCheck = false;
					}
				}
				InputStore.setAmount(IntTemp);
				
				System.out.println("원가를 원 단위(\\)로 입력해주십시오[" + OriginalPrice + "]");
				try {
					IntTemp = Input.nextInt();
					InputCheck = true;
				} catch(InputMismatchException e) {
					InputCheck = false;
				}
				while(!(InputCheck) || IntTemp <= 0) {
					System.out.println("Invalid value. 원가를 다시 입력해주세요");
					Input.nextLine();
					try {
						IntTemp = Input.nextInt();
						InputCheck = true;
					} catch(InputMismatchException e) {
						InputCheck = false;
					}
				}
				OriginalPrice = IntTemp;
				
				System.out.println("부족알림 기준량을 입력해주십시오[" + Inadequate + "]");
				try {
					IntTemp = Input.nextInt();
					InputCheck = true;
				} catch(InputMismatchException e) {
					InputCheck = false;
				}
				while(!(InputCheck) || IntTemp < 0) {
					System.out.println("Invalid value. 기준량을 다시 입력해주세요");
					Input.nextLine();
					try {
						IntTemp = Input.nextInt();
						InputCheck = true;
					} catch(InputMismatchException e) {
						InputCheck = false;
					}
				}
				Inadequate = IntTemp;
				Input.nextLine();
				
				InputCheck = checkStoreInput(InputStore, ExpirationDate, Container, OriginalPrice, Inadequate);
			}
		}
		// 여기에서부터 입력값 저장 실시
		InputStore.inputStore(ExpirationDate, Container, OriginalPrice, Inadequate);
	}

	
	public void DeliverInput() throws IOException, InterruptedException{
		Deliver InputDeliver = new Deliver();
		Scanner Input = new Scanner(System.in);
		String StrTemp = null;
		int IntTemp = 0;
		boolean InputCheck = true; // 입력값 유효성 여부 및 최종 확인용
		
		System.out.println("상품 이름을 입력해주십시오");
		InputDeliver.setName(Input.nextLine());
		System.out.println("거래처명을 입력해주십시오");
		InputDeliver.setClient(Input.nextLine());
		
		System.out.println("출고일자를 입력해주십시오(yyyy-MM-dd)");
		StrTemp = Input.nextLine();
		while(checkDate(StrTemp) != true) {
			System.out.println("Invaild Format. yyyy-MM-dd에 맞춰서 다시 입력해주십시오.");
			StrTemp = Input.nextLine();
		}
		InputDeliver.setDate(StrTemp);
		
		System.out.println("물품 번호를 입력해주십시오");
		StrTemp = Input.nextLine();
		try {
			Double.parseDouble(StrTemp);
			InputCheck = true;
		} catch (NumberFormatException e){
			InputCheck = false;
		}
		while(StrTemp.length() <= 13 || !(InputCheck)) {
			System.out.println("Invaild Format. 물품 번호를 길이에 맞춰서 숫자로 다시 입력해주십시오.");
			StrTemp = Input.nextLine();
			try {
				Long.parseLong(StrTemp);
				InputCheck = true;
			} catch (NumberFormatException e){
				InputCheck = false;
			}
		}
		InputDeliver.setCode(StrTemp);
		
		System.out.println("출고가를 원 단위(\\)로 입력해주십시오");
		try {
			IntTemp = Input.nextInt();
			InputCheck = true;
		} catch(InputMismatchException e) {
			InputCheck = false;
		}
		while(!(InputCheck) || IntTemp <= 0) {
			System.out.println("Invalid value. 가격을 다시 입력해주세요");
			Input.nextLine();
			try {
				IntTemp = Input.nextInt();
				InputCheck = true;
			} catch(InputMismatchException e) {
				InputCheck = false;
			}
		}
		InputDeliver.setPrice(IntTemp);
		
		System.out.println("출고 수량을 입력해주십시오");
		try {
			IntTemp = Input.nextInt();
			InputCheck = true;
		} catch(InputMismatchException e) {
			InputCheck = false;
		}
		while(!(InputCheck) || IntTemp <= 0) {
			System.out.println("Invalid value. 출고수량을 다시 입력해주세요");
			Input.nextLine();
			try {
				IntTemp = Input.nextInt();
				InputCheck = true;
			} catch(InputMismatchException e) {
				InputCheck = false;
			}
		}
		InputDeliver.setAmount(IntTemp);
		Input.nextLine();

		InputCheck = checkDeliverInput(InputDeliver);
		if(!(InputCheck)) {
			// 다시 입력받아서 확인
			while(!(InputCheck)) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				System.out.println("상품 이름을 입력해주십시오[" + InputDeliver.getName() + "]");
				InputDeliver.setName(Input.nextLine());
				System.out.println("거래처명을 입력해주십시오[" + InputDeliver.getClient() + "]");
				InputDeliver.setClient(Input.nextLine());
				
				System.out.println("출고일자를 입력해주십시오(yyyy-MM-dd)[" + InputDeliver.getDate() + "]");
				StrTemp = Input.nextLine();
				while(checkDate(StrTemp) != true) {
					System.out.println("Invaild Format. yyyy-MM-dd에 맞춰서 다시 입력해주십시오.");
					StrTemp = Input.nextLine();
				}
				InputDeliver.setDate(StrTemp);
				
				System.out.println("물품 번호를 입력해주십시오[" + InputDeliver.getCode() + "]");
				StrTemp = Input.nextLine();
				try {
					Double.parseDouble(StrTemp);
					InputCheck = true;
				} catch (NumberFormatException e){
					InputCheck = false;
				}
				while(StrTemp.length() <= 13 || !(InputCheck)) {
					System.out.println("Invaild Format. 물품 번호를 길이에 맞춰서 숫자로 다시 입력해주십시오.");
					StrTemp = Input.nextLine();
					try {
						Long.parseLong(StrTemp);
						InputCheck = true;
					} catch (NumberFormatException e){
						InputCheck = false;
					}
				}
				InputDeliver.setCode(StrTemp);
				
				System.out.println("출고가격을 원 단위(\\)로 입력해주십시오[" + InputDeliver.getPrice() + "]");
				try {
					IntTemp = Input.nextInt();
					InputCheck = true;
				} catch(InputMismatchException e) {
					InputCheck = false;
				}
				while(!(InputCheck) || IntTemp <= 0) {
					System.out.println("Invalid value. 가격을 다시 입력해주세요");
					Input.nextLine();
					try {
						IntTemp = Input.nextInt();
						InputCheck = true;
					} catch(InputMismatchException e) {
						InputCheck = false;
					}
				}
				InputDeliver.setPrice(IntTemp);
				
				System.out.println("출고 수량을 입력해주십시오[" + InputDeliver.getAmount() + "]");
				try {
					IntTemp = Input.nextInt();
					InputCheck = true;
				} catch(InputMismatchException e) {
					InputCheck = false;
				}
				while(!(InputCheck) || IntTemp <= 0) {
					System.out.println("Invalid value. 출고수량을 다시 입력해주세요");
					Input.nextLine();
					try {
						IntTemp = Input.nextInt();
						InputCheck = true;
					} catch(InputMismatchException e) {
						InputCheck = false;
					}
				}
				InputDeliver.setAmount(IntTemp);
				Input.nextLine();
				
				InputCheck = checkDeliverInput(InputDeliver);
			}
		}
		// 물품번호 확인해서 물품 없으면 반영안하고 종료
		// 아니면 반영
	}
	
	private boolean checkDate(String CheckDate) {
		SimpleDateFormat DateFormat = new SimpleDateFormat();
		Date Date = new Date();
		// System.out.println(CheckDate);
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
	
	private boolean checkStoreInput(Store InputStore, String ExpirationDate, String Container, int OriginalPrice, int Inadequate) throws IOException, InterruptedException {
		char yn, temp;
		boolean returnValue = true;
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		System.out.println("입력하신 입고 기록입니다.");
		System.out.println("제품이릉: " + InputStore.getName());
		System.out.println("입고량: " + InputStore.getAmount());
		System.out.println("입고일자: " + InputStore.getDate());
		System.out.println("입고가: " + InputStore.getPrice());
		System.out.println("바코드번호: " + InputStore.getCode());
		System.out.println("거래처: " + InputStore.getClient());
		System.out.println("유통기한: " + ExpirationDate);
		System.out.println("창고위치: " + Container);
		System.out.println("원가: " + OriginalPrice);
		System.out.println("부족알림 기준량: " + Inadequate);
		System.out.print("위의 입력이 맞습니까(y/n)? ");
		yn = (char) System.in.read();
		do {
			temp = (char) System.in.read();
		}while(temp != '\n');
		
		if(!(yn == 'y' || yn == 'n' || yn == 'Y' || yn == 'N')) {
			while (!(yn == 'y' || yn == 'n' || yn == 'Y' || yn == 'N')) { // y나 n이 아니면 새로 확인
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				System.out.println("temp : " + temp + ", yn : " + yn);
				System.out.println("입력하신 입고 기록입니다.");
				System.out.println("제품이릉: " + InputStore.getName());
				System.out.println("입고량: " + InputStore.getAmount());
				System.out.println("입고일자: " + InputStore.getDate());
				System.out.println("입고가: " + InputStore.getPrice());
				System.out.println("바코드번호: " + InputStore.getCode());
				System.out.println("거래처: " + InputStore.getClient());
				System.out.println("유통기한: " + ExpirationDate);
				System.out.println("창고위치: " + Container);
				System.out.println("원가: " + OriginalPrice);
				System.out.println("부족알림 기준량: " + Inadequate);
				System.out.print("위의 입력이 맞습니까(y/n)? ");
				yn = (char)System.in.read();
				do {
					temp = (char) System.in.read();
				}while(temp != '\n');
			}
		}

		System.out.println("temp : " + temp + ", yn : " + yn);
		if(yn == 'y' || yn == 'Y')
			returnValue = true;
		else if(yn == 'n' || yn == 'N')
			returnValue = false;
		else {
			System.err.println("입고기록 확인 에러. 시스템을 재시작해주십시오.");
			System.exit(1);
		}
		return returnValue;
	}
	
	private boolean checkDeliverInput(Deliver InputStore) throws IOException, InterruptedException {
		char yn, temp;
		boolean returnValue = true;
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		System.out.println("입력하신 출고 기록입니다.");
		System.out.println("제품이릉: " + InputStore.getName());
		System.out.println("출고량: " + InputStore.getAmount());
		System.out.println("출고일자: " + InputStore.getDate());
		System.out.println("출고가: " + InputStore.getPrice());
		System.out.println("물품번호: " + InputStore.getCode());
		System.out.println("거래처: " + InputStore.getClient());
		System.out.print("위의 입력이 맞습니까(y/n)? ");
		yn = (char)System.in.read();
		do {
			temp = (char) System.in.read();
		}while(temp != '\n');
		
		if(!(yn == 'y' || yn == 'n' || yn == 'Y' || yn == 'N')) {
			while (!(yn == 'y' || yn == 'n' || yn == 'Y' || yn == 'N')) { // y나 n이 아니면 새로 확인
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				System.out.println("입력하신 출고 기록입니다.");
				System.out.println("제품이릉: " + InputStore.getName());
				System.out.println("출고량: " + InputStore.getAmount());
				System.out.println("출고일자: " + InputStore.getDate());
				System.out.println("출고가: " + InputStore.getPrice());
				System.out.println("물품번호: " + InputStore.getCode());
				System.out.println("거래처: " + InputStore.getClient());
				System.out.print("위의 입력이 맞습니까(y/n)? ");
				yn = (char)System.in.read();
				do {
					temp = (char) System.in.read();
				}while(temp != '\n');
			}
		}

		if(yn == 'y' || yn == 'Y')
			returnValue = true;
		else if(yn == 'n' || yn == 'N')
			returnValue = false;
		else {
			System.err.println("출고기록 확인 에러. 시스템을 재시작해주십시오.");
			System.exit(1);
		}
		return returnValue;
	}
}
