package StockRecordIO;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.InputMismatchException;

import DataManagement.StoreDeliverFile;
import ProductManagement.ComparatorStore;
import ProductManagement.ComparatorDeliver;
import ProductManagement.Store;
import ProductManagement.Deliver;

public class StoreDeliverIO {
	public void StoreOutput() throws IOException { // 출력할 범위를 입력받은 뒤, 범위에 해당하는 물품에 대해 출력
		Scanner Input = new Scanner(System.in);
		String StartDate = null;
		String EndDate = null;
		String StrTemp = null;
		boolean EndCheck = true;
		Store[] List = null;
		
		StoreDeliverFile LoadStore = new StoreDeliverFile();
		List = LoadStore.loadStoreFile();
		
		System.out.println("입고 기록의 출력 범위를 입력해주십시오(시작날짜) (정하지 않을 경우, '-' 입력)");
		StrTemp = Input.nextLine();
		while(checkDate(StrTemp) != true) {
			if(StrTemp.equals("-"))
				break;
			System.out.println("Invaild Format. yyyy-MM-dd에 맞춰서 다시 입력해주십시오.");
			StrTemp = Input.nextLine();
		}
		StartDate = StrTemp;
		
		System.out.println("입고 기록의 출력 범위를 입력해주십시오(마지막날짜) (정하지 않을 경우, '-' 입력)");
		StrTemp = Input.nextLine();
		while(checkDate(StrTemp) != true && EndCheck == true) {
			if(StrTemp.equals("-"))
				break;
			System.out.println("Invaild Format. yyyy-MM-dd에 맞춰서 다시 입력해주십시오.");
			StrTemp = Input.nextLine();
		}
		EndDate = StrTemp;
		
		if(StrTemp.compareTo(StartDate) < 0) {
			System.err.println("범위 오류: 마지막 날짜가 시작 날짜보다 빠릅니다. 다시 출력하려면 처음부터 다시 진행하십시오.");
			return;
		}
		
		Arrays.sort(List, new ComparatorStore());
		
		for(int i = 0; i < List.length; i++) {
			if(StartDate.equals("-") == true) {
				if(EndDate.equals("-") == true) { // 전체 범위
					System.out.println(List[i].toString());
				} else { // 처음부터 특정 날짜 까지
					if(EndDate.compareTo(List[i].getStoreDate()) >= 0) {
						System.out.println(List[i].toString());
					} else {
						continue;
					}
				}
			} else if(EndDate.equals("-")) { // 특정 날짜부터 끝까지
				if(StartDate.compareTo(List[i].getStoreDate()) <= 0) {
					System.out.println(List[i].toString());
				} else {
					continue;
				}
			} else { // 특정 날짜 범위 내
				if(EndDate.compareTo(List[i].getStoreDate()) >= 0 && StartDate.compareTo(List[i].getStoreDate()) <= 0) {
					System.out.println(List[i].toString());
				} else {
					continue;
				}
			}
		}
		System.out.println("출력순서 : <<제품이름, 입고량, 입고일자, 입고가, 물품번호, 거래처>>");
		return;
	}
	
	public void DeliverOutput() throws IOException {
		Scanner Input = new Scanner(System.in);
		String StartDate = null;
		String EndDate = null;
		String StrTemp = null;
		boolean EndCheck = true;
		Deliver[] List = null;
		
		StoreDeliverFile LoadDeliver = new StoreDeliverFile();
		List = LoadDeliver.loadDeliverFile();
		
		System.out.println("출고 기록의 출력 범위를 입력해주십시오(시작날짜) (정하지 않을 경우, '-' 입력)");
		StrTemp = Input.nextLine();
		while(checkDate(StrTemp) != true) {
			if(StrTemp.equals("-"))
				break;
			System.out.println("Invaild Format. yyyy-MM-dd에 맞춰서 다시 입력해주십시오.");
			StrTemp = Input.nextLine();
		}
		StartDate = StrTemp;
		
		System.out.println("출고 기록의 출력 범위를 입력해주십시오(마지막날짜) (정하지 않을 경우, '-' 입력)");
		StrTemp = Input.nextLine();
		while(checkDate(StrTemp) != true && EndCheck == true) {
			if(StrTemp.equals("-"))
				break;
			System.out.println("Invaild Format. yyyy-MM-dd에 맞춰서 다시 입력해주십시오.");
			StrTemp = Input.nextLine();
		}
		EndDate = StrTemp;
		
		if(StrTemp.compareTo(StartDate) < 0) {
			System.err.println("범위 오류: 마지막 날짜가 시작 날짜보다 빠릅니다. 다시 출력하려면 처음부터 다시 진행하십시오.");
			return;
		}
		
		Arrays.sort(List, new ComparatorDeliver());

		for(int i = 0; i < List.length; i++) {
			if(StartDate.equals("-") == true) {
				if(EndDate.equals("-") == true) { // 전체 범위
					System.out.println(List[i].toString());
				} else { // 처음부터 특정 날짜 까지
					if(EndDate.compareTo(List[i].getDeliverDate()) >= 0) {
						System.out.println(List[i].toString());
					} else {
						continue;
					}
				}
			} else if(EndDate.equals("-")) { // 특정 날짜부터 끝까지
				if(StartDate.compareTo(List[i].getDeliverDate()) <= 0) {
					System.out.println(List[i].toString());
				} else {
					continue;
				}
			} else { // 특정 날짜 범위 내
				if(EndDate.compareTo(List[i].getDeliverDate()) >= 0 && StartDate.compareTo(List[i].getDeliverDate()) <= 0) {
					System.out.println(List[i].toString());
				} else {
					continue;
				}
			}
		}
		System.out.println("출력순서 : <<제품이름, 출고량, 출고일자, 출고가, 물품번호, 거래처>>");
		return;
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
		
		System.out.println("상품 이름을 입력해주십시오('|' 입력 불가)");
		StrTemp = Input.nextLine();
		while(StrTemp.length() > 100 || (StrTemp.indexOf("|") > -1)) {
			System.out.println("Invaild Format. 100자 이내로 '|' 없이 다시 입력해주십시오.");
			StrTemp = Input.nextLine();
		}
		InputStore.setName(StrTemp);
		
		System.out.println("거래처명을 입력해주십시오('|' 입력 불가)");
		StrTemp = Input.nextLine();
		while(StrTemp.length() > 100 || (StrTemp.indexOf("|") > -1)) {
			System.out.println("Invaild Format. 100자 이내로 '|' 없이 다시 입력해주십시오.");
			StrTemp = Input.nextLine();
		}
		InputStore.setClient(StrTemp);
		
		System.out.println("창고이름 또는 위치를 입력해주십시오('|' 입력 불가)");
		StrTemp = Input.nextLine();
		while(StrTemp.length() > 100 || (StrTemp.indexOf("|") > -1)) {
			System.out.println("Invaild Format. 100자 이내로 '|' 없이 다시 입력해주십시오.");
			StrTemp = Input.nextLine();
		}
		Container = new String(StrTemp);
		
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
			Long.parseLong(StrTemp);
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
		
		System.out.println("유통기한을 입력해주십시오(yyyy-MM-dd) (없는 제품이면 '-'을 입력해주십시오)");
		StrTemp = Input.nextLine();
		while(checkDate(StrTemp) != true) {
			if(StrTemp.equals("-"))
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
				
				System.out.println("상품 이름을 입력해주십시오('|' 입력 불가)[" + InputStore.getName() + "]");
				StrTemp = Input.nextLine();
				while(StrTemp.length() > 100 || (StrTemp.indexOf("|") > -1)) {
					System.out.println("Invaild Format. 100자 이내로 '|' 없이 다시 입력해주십시오.");
					StrTemp = Input.nextLine();
				}
				InputStore.setName(StrTemp);
				
				System.out.println("거래처명을 입력해주십시오('|' 입력 불가)[" + InputStore.getClient() + "]");
				StrTemp = Input.nextLine();
				while(StrTemp.length() > 100 || (StrTemp.indexOf("|") > -1)) {
					System.out.println("Invaild Format. 100자 이내로 '|' 없이 다시 입력해주십시오.");
					StrTemp = Input.nextLine();
				}
				InputStore.setClient(StrTemp);
				
				System.out.println("창고이름 또는 위치를 입력해주십시오('|' 입력 불가)[" + Container + "]");
				StrTemp = Input.nextLine();
				while(StrTemp.length() > 100 || (StrTemp.indexOf("|") > -1)) {
					System.out.println("Invaild Format. 100자 이내로 '|' 없이 다시 입력해주십시오.");
					StrTemp = Input.nextLine();
				}
				Container = new String(StrTemp);
				
				
				System.out.println("입고일자를 입력해주십시오(yyyy-MM-dd)[" + InputStore.getStoreDate() + "]");
				StrTemp = Input.nextLine();
				while(checkDate(StrTemp) != true) {
					System.out.println("Invaild Format. yyyy-MM-dd에 맞춰서 다시 입력해주십시오.");
					StrTemp = Input.nextLine();
				}
				InputStore.setDate(StrTemp);
				
				System.out.println("바코드번호를 입력해주십시오[" + InputStore.getCode() + "]");
				StrTemp = Input.nextLine();
				try {
					Long.parseLong(StrTemp);
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
					if(StrTemp.equals("-"))
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
		
		
		System.out.println("상품 이름을 입력해주십시오('|' 입력 불가)");
		StrTemp = Input.nextLine();
		while(StrTemp.length() > 100 || (StrTemp.indexOf("|") > -1)) {
			System.out.println("Invaild Format. 100자 이내로 '|' 없이 다시 입력해주십시오.");
			StrTemp = Input.nextLine();
		}
		InputDeliver.setName(StrTemp);
		
		System.out.println("거래처명을 입력해주십시오('|' 입력 불가)");
		StrTemp = Input.nextLine();
		while(StrTemp.length() > 100 || (StrTemp.indexOf("|") > -1)) {
			System.out.println("Invaild Format. 100자 이내로 '|' 없이 다시 입력해주십시오.");
			StrTemp = Input.nextLine();
		}
		InputDeliver.setClient(StrTemp);
		
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
			Long.parseLong(StrTemp);
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

				System.out.println("상품 이름을 입력해주십시오('|' 입력 불가)[" + InputDeliver.getName() + "]");
				StrTemp = Input.nextLine();
				while(StrTemp.length() > 100 || (StrTemp.indexOf("|") > -1)) {
					System.out.println("Invaild Format. 100자 이내로 '|' 없이 다시 입력해주십시오.");
					StrTemp = Input.nextLine();
				}
				InputDeliver.setName(StrTemp);
				
				System.out.println("거래처명을 입력해주십시오('|' 입력 불가)[" + InputDeliver.getClient() + "]");
				StrTemp = Input.nextLine();
				while(StrTemp.length() > 100 || (StrTemp.indexOf("|") > -1)) {
					System.out.println("Invaild Format. 100자 이내로 '|' 없이 다시 입력해주십시오.");
					StrTemp = Input.nextLine();
				}
				InputDeliver.setClient(StrTemp);
				
				System.out.println("출고일자를 입력해주십시오(yyyy-MM-dd)[" + InputDeliver.getDeliverDate() + "]");
				StrTemp = Input.nextLine();
				while(checkDate(StrTemp) != true) {
					System.out.println("Invaild Format. yyyy-MM-dd에 맞춰서 다시 입력해주십시오.");
					StrTemp = Input.nextLine();
				}
				InputDeliver.setDate(StrTemp);
				
				System.out.println("물품 번호를 입력해주십시오[" + InputDeliver.getCode() + "]");
				StrTemp = Input.nextLine();
				try {
					Long.parseLong(StrTemp);
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
		
		if(InputDeliver.inputDeliver() == false) {
			System.out.println("출고기록 확인 에러. 출고 기록 데이터를 적용할 물품 데이터가 존재하지 않습니다. 처음부터 다시 입력해주십시오.");
		} else {
			System.out.println("출고기록 저장 성공. 물품 데이터에 성공적으로 출고 기록 데이터가 적용되었습니다.");
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
		System.out.println("입고일자: " + InputStore.getStoreDate());
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
				System.out.println("입고일자: " + InputStore.getStoreDate());
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

		// System.out.println("temp : " + temp + ", yn : " + yn);
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
		System.out.println("출고일자: " + InputStore.getDeliverDate());
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
				System.out.println("출고일자: " + InputStore.getDeliverDate());
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
