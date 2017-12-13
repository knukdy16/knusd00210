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
	public void StoreOutput() throws IOException { // ����� ������ �Է¹��� ��, ������ �ش��ϴ� ��ǰ�� ���� ���
		Scanner Input = new Scanner(System.in);
		String StartDate = null;
		String EndDate = null;
		String StrTemp = null;
		boolean EndCheck = true;
		Store[] List = null;
		
		StoreDeliverFile LoadStore = new StoreDeliverFile();
		List = LoadStore.loadStoreFile();
		
		System.out.println("�԰� ����� ��� ������ �Է����ֽʽÿ�(���۳�¥) (������ ���� ���, '-' �Է�)");
		StrTemp = Input.nextLine();
		while(checkDate(StrTemp) != true) {
			if(StrTemp.equals("-"))
				break;
			System.out.println("Invaild Format. yyyy-MM-dd�� ���缭 �ٽ� �Է����ֽʽÿ�.");
			StrTemp = Input.nextLine();
		}
		StartDate = StrTemp;
		
		System.out.println("�԰� ����� ��� ������ �Է����ֽʽÿ�(��������¥) (������ ���� ���, '-' �Է�)");
		StrTemp = Input.nextLine();
		while(checkDate(StrTemp) != true && EndCheck == true) {
			if(StrTemp.equals("-"))
				break;
			System.out.println("Invaild Format. yyyy-MM-dd�� ���缭 �ٽ� �Է����ֽʽÿ�.");
			StrTemp = Input.nextLine();
		}
		EndDate = StrTemp;
		
		if(StrTemp.compareTo(StartDate) < 0) {
			System.err.println("���� ����: ������ ��¥�� ���� ��¥���� �����ϴ�. �ٽ� ����Ϸ��� ó������ �ٽ� �����Ͻʽÿ�.");
			return;
		}
		
		Arrays.sort(List, new ComparatorStore());
		
		for(int i = 0; i < List.length; i++) {
			if(StartDate.equals("-") == true) {
				if(EndDate.equals("-") == true) { // ��ü ����
					System.out.println(List[i].toString());
				} else { // ó������ Ư�� ��¥ ����
					if(EndDate.compareTo(List[i].getStoreDate()) >= 0) {
						System.out.println(List[i].toString());
					} else {
						continue;
					}
				}
			} else if(EndDate.equals("-")) { // Ư�� ��¥���� ������
				if(StartDate.compareTo(List[i].getStoreDate()) <= 0) {
					System.out.println(List[i].toString());
				} else {
					continue;
				}
			} else { // Ư�� ��¥ ���� ��
				if(EndDate.compareTo(List[i].getStoreDate()) >= 0 && StartDate.compareTo(List[i].getStoreDate()) <= 0) {
					System.out.println(List[i].toString());
				} else {
					continue;
				}
			}
		}
		System.out.println("��¼��� : <<��ǰ�̸�, �԰�, �԰�����, �԰�, ��ǰ��ȣ, �ŷ�ó>>");
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
		
		System.out.println("��� ����� ��� ������ �Է����ֽʽÿ�(���۳�¥) (������ ���� ���, '-' �Է�)");
		StrTemp = Input.nextLine();
		while(checkDate(StrTemp) != true) {
			if(StrTemp.equals("-"))
				break;
			System.out.println("Invaild Format. yyyy-MM-dd�� ���缭 �ٽ� �Է����ֽʽÿ�.");
			StrTemp = Input.nextLine();
		}
		StartDate = StrTemp;
		
		System.out.println("��� ����� ��� ������ �Է����ֽʽÿ�(��������¥) (������ ���� ���, '-' �Է�)");
		StrTemp = Input.nextLine();
		while(checkDate(StrTemp) != true && EndCheck == true) {
			if(StrTemp.equals("-"))
				break;
			System.out.println("Invaild Format. yyyy-MM-dd�� ���缭 �ٽ� �Է����ֽʽÿ�.");
			StrTemp = Input.nextLine();
		}
		EndDate = StrTemp;
		
		if(StrTemp.compareTo(StartDate) < 0) {
			System.err.println("���� ����: ������ ��¥�� ���� ��¥���� �����ϴ�. �ٽ� ����Ϸ��� ó������ �ٽ� �����Ͻʽÿ�.");
			return;
		}
		
		Arrays.sort(List, new ComparatorDeliver());

		for(int i = 0; i < List.length; i++) {
			if(StartDate.equals("-") == true) {
				if(EndDate.equals("-") == true) { // ��ü ����
					System.out.println(List[i].toString());
				} else { // ó������ Ư�� ��¥ ����
					if(EndDate.compareTo(List[i].getDeliverDate()) >= 0) {
						System.out.println(List[i].toString());
					} else {
						continue;
					}
				}
			} else if(EndDate.equals("-")) { // Ư�� ��¥���� ������
				if(StartDate.compareTo(List[i].getDeliverDate()) <= 0) {
					System.out.println(List[i].toString());
				} else {
					continue;
				}
			} else { // Ư�� ��¥ ���� ��
				if(EndDate.compareTo(List[i].getDeliverDate()) >= 0 && StartDate.compareTo(List[i].getDeliverDate()) <= 0) {
					System.out.println(List[i].toString());
				} else {
					continue;
				}
			}
		}
		System.out.println("��¼��� : <<��ǰ�̸�, ���, �������, ���, ��ǰ��ȣ, �ŷ�ó>>");
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
		boolean InputCheck = true; // �Է°� ��ȿ�� ���� �� ���� Ȯ�ο�
		
		System.out.println("��ǰ �̸��� �Է����ֽʽÿ�('|' �Է� �Ұ�)");
		StrTemp = Input.nextLine();
		while(StrTemp.length() > 100 || (StrTemp.indexOf("|") > -1)) {
			System.out.println("Invaild Format. 100�� �̳��� '|' ���� �ٽ� �Է����ֽʽÿ�.");
			StrTemp = Input.nextLine();
		}
		InputStore.setName(StrTemp);
		
		System.out.println("�ŷ�ó���� �Է����ֽʽÿ�('|' �Է� �Ұ�)");
		StrTemp = Input.nextLine();
		while(StrTemp.length() > 100 || (StrTemp.indexOf("|") > -1)) {
			System.out.println("Invaild Format. 100�� �̳��� '|' ���� �ٽ� �Է����ֽʽÿ�.");
			StrTemp = Input.nextLine();
		}
		InputStore.setClient(StrTemp);
		
		System.out.println("â���̸� �Ǵ� ��ġ�� �Է����ֽʽÿ�('|' �Է� �Ұ�)");
		StrTemp = Input.nextLine();
		while(StrTemp.length() > 100 || (StrTemp.indexOf("|") > -1)) {
			System.out.println("Invaild Format. 100�� �̳��� '|' ���� �ٽ� �Է����ֽʽÿ�.");
			StrTemp = Input.nextLine();
		}
		Container = new String(StrTemp);
		
		System.out.println("�԰����ڸ� �Է����ֽʽÿ�(yyyy-MM-dd)");
		StrTemp = Input.nextLine();
		while(checkDate(StrTemp) != true) {
			System.out.println("Invaild Format. yyyy-MM-dd�� ���缭 �ٽ� �Է����ֽʽÿ�.");
			StrTemp = Input.nextLine();
		}
		InputStore.setDate(StrTemp);
		
		System.out.println("���ڵ��ȣ�� �Է����ֽʽÿ�");
		StrTemp = Input.nextLine();
		try {
			Long.parseLong(StrTemp);
			InputCheck = true;
		} catch (NumberFormatException e){
			InputCheck = false;
		}
		while(StrTemp.length() != 13 || !(InputCheck)) {
			System.out.println("Invaild Format. ���ڵ� ��ȣ ���̿� ���缭 ���ڷ� �ٽ� �Է����ֽʽÿ�.");
			StrTemp = Input.nextLine();
			try {
				Long.parseLong(StrTemp);
				InputCheck = true;
			} catch (NumberFormatException e){
				InputCheck = false;
			}
		}
		InputStore.setCode(StrTemp);
		
		System.out.println("��������� �Է����ֽʽÿ�(yyyy-MM-dd) (���� ��ǰ�̸� '-'�� �Է����ֽʽÿ�)");
		StrTemp = Input.nextLine();
		while(checkDate(StrTemp) != true) {
			if(StrTemp.equals("-"))
				break;
			System.out.println("Invaild Format. yyyy-MM-dd�� ���缭 �ٽ� �Է����ֽʽÿ�.");
			StrTemp = Input.nextLine();
		}
		ExpirationDate = new String(StrTemp);
		
		System.out.println("�ǸŰ����� �� ����(\\)�� �Է����ֽʽÿ�");
		try {
			IntTemp = Input.nextInt();
			InputCheck = true;
		} catch(InputMismatchException e) {
			InputCheck = false;
		}
		while(!(InputCheck) || IntTemp <= 0) {
			System.out.println("Invalid value. ������ �ٽ� �Է����ּ���");
			Input.nextLine();
			try {
				IntTemp = Input.nextInt();
				InputCheck = true;
			} catch(InputMismatchException e) {
				InputCheck = false;
			}
		}
		InputStore.setPrice(IntTemp);
		
		System.out.println("�԰� ������ �Է����ֽʽÿ�");
		try {
			IntTemp = Input.nextInt();
			InputCheck = true;
		} catch(InputMismatchException e) {
			InputCheck = false;
		}
		while(!(InputCheck) || IntTemp <= 0) {
			System.out.println("Invalid value. �������� �ٽ� �Է����ּ���");
			Input.nextLine();
			try {
				IntTemp = Input.nextInt();
				InputCheck = true;
			} catch(InputMismatchException e) {
				InputCheck = false;
			}
		}
		InputStore.setAmount(IntTemp);
		
		System.out.println("������ �� ����(\\)�� �Է����ֽʽÿ�");
		try {
			IntTemp = Input.nextInt();
			InputCheck = true;
		} catch(InputMismatchException e) {
			InputCheck = false;
		}
		while(!(InputCheck) || IntTemp <= 0) {
			System.out.println("Invalid value. ������ �ٽ� �Է����ּ���");
			Input.nextLine();
			try {
				IntTemp = Input.nextInt();
				InputCheck = true;
			} catch(InputMismatchException e) {
				InputCheck = false;
			}
		}
		OriginalPrice = IntTemp;
		
		System.out.println("�����˸� ���ط��� �Է����ֽʽÿ�");
		try {
			IntTemp = Input.nextInt();
			InputCheck = true;
		} catch(InputMismatchException e) {
			InputCheck = false;
		}
		while(!(InputCheck) || IntTemp < 0) {
			System.out.println("Invalid value. ���ط��� �ٽ� �Է����ּ���");
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
			// �ٽ� �Է¹޾Ƽ� Ȯ��
			while(!(InputCheck)) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				
				System.out.println("��ǰ �̸��� �Է����ֽʽÿ�('|' �Է� �Ұ�)[" + InputStore.getName() + "]");
				StrTemp = Input.nextLine();
				while(StrTemp.length() > 100 || (StrTemp.indexOf("|") > -1)) {
					System.out.println("Invaild Format. 100�� �̳��� '|' ���� �ٽ� �Է����ֽʽÿ�.");
					StrTemp = Input.nextLine();
				}
				InputStore.setName(StrTemp);
				
				System.out.println("�ŷ�ó���� �Է����ֽʽÿ�('|' �Է� �Ұ�)[" + InputStore.getClient() + "]");
				StrTemp = Input.nextLine();
				while(StrTemp.length() > 100 || (StrTemp.indexOf("|") > -1)) {
					System.out.println("Invaild Format. 100�� �̳��� '|' ���� �ٽ� �Է����ֽʽÿ�.");
					StrTemp = Input.nextLine();
				}
				InputStore.setClient(StrTemp);
				
				System.out.println("â���̸� �Ǵ� ��ġ�� �Է����ֽʽÿ�('|' �Է� �Ұ�)[" + Container + "]");
				StrTemp = Input.nextLine();
				while(StrTemp.length() > 100 || (StrTemp.indexOf("|") > -1)) {
					System.out.println("Invaild Format. 100�� �̳��� '|' ���� �ٽ� �Է����ֽʽÿ�.");
					StrTemp = Input.nextLine();
				}
				Container = new String(StrTemp);
				
				
				System.out.println("�԰����ڸ� �Է����ֽʽÿ�(yyyy-MM-dd)[" + InputStore.getStoreDate() + "]");
				StrTemp = Input.nextLine();
				while(checkDate(StrTemp) != true) {
					System.out.println("Invaild Format. yyyy-MM-dd�� ���缭 �ٽ� �Է����ֽʽÿ�.");
					StrTemp = Input.nextLine();
				}
				InputStore.setDate(StrTemp);
				
				System.out.println("���ڵ��ȣ�� �Է����ֽʽÿ�[" + InputStore.getCode() + "]");
				StrTemp = Input.nextLine();
				try {
					Long.parseLong(StrTemp);
					InputCheck = true;
				} catch (NumberFormatException e){
					InputCheck = false;
				}
				while(StrTemp.length() != 13 || !(InputCheck)) {
					System.out.println("Invaild Format. ���ڵ� ��ȣ ���̿� ���缭 ���ڷ� �ٽ� �Է����ֽʽÿ�.");
					StrTemp = Input.nextLine();
					try {
						Long.parseLong(StrTemp);
						InputCheck = true;
					} catch (NumberFormatException e){
						InputCheck = false;
					}
				}
				InputStore.setCode(StrTemp);
				
				System.out.println("��������� �Է����ֽʽÿ�(yyyy-MM-dd)[" + ExpirationDate + "]");
				StrTemp = Input.nextLine();
				while(checkDate(StrTemp) != true) {
					if(StrTemp.equals("-"))
						break;
					System.out.println("Invaild Format. yyyy-MM-dd�� ���缭 �ٽ� �Է����ֽʽÿ�.");
					StrTemp = Input.nextLine();
				}
				ExpirationDate = new String(StrTemp);
				
				System.out.println("�ǸŰ����� �� ����(\\)�� �Է����ֽʽÿ�[" + InputStore.getPrice() + "]");
				try {
					IntTemp = Input.nextInt();
					InputCheck = true;
				} catch(InputMismatchException e) {
					InputCheck = false;
				}
				while(!(InputCheck) || IntTemp <= 0) {
					System.out.println("Invalid value. ������ �ٽ� �Է����ּ���");
					Input.nextLine();
					try {
						IntTemp = Input.nextInt();
						InputCheck = true;
					} catch(InputMismatchException e) {
						InputCheck = false;
					}
				}
				InputStore.setPrice(IntTemp);
				
				System.out.println("�԰� ������ �Է����ֽʽÿ�[" + InputStore.getAmount() + "]");
				try {
					IntTemp = Input.nextInt();
					InputCheck = true;
				} catch(InputMismatchException e) {
					InputCheck = false;
				}
				while(!(InputCheck) || IntTemp <= 0) {
					System.out.println("Invalid value. �������� �ٽ� �Է����ּ���");
					Input.nextLine();
					try {
						IntTemp = Input.nextInt();
						InputCheck = true;
					} catch(InputMismatchException e) {
						InputCheck = false;
					}
				}
				InputStore.setAmount(IntTemp);
				
				System.out.println("������ �� ����(\\)�� �Է����ֽʽÿ�[" + OriginalPrice + "]");
				try {
					IntTemp = Input.nextInt();
					InputCheck = true;
				} catch(InputMismatchException e) {
					InputCheck = false;
				}
				while(!(InputCheck) || IntTemp <= 0) {
					System.out.println("Invalid value. ������ �ٽ� �Է����ּ���");
					Input.nextLine();
					try {
						IntTemp = Input.nextInt();
						InputCheck = true;
					} catch(InputMismatchException e) {
						InputCheck = false;
					}
				}
				OriginalPrice = IntTemp;
				
				System.out.println("�����˸� ���ط��� �Է����ֽʽÿ�[" + Inadequate + "]");
				try {
					IntTemp = Input.nextInt();
					InputCheck = true;
				} catch(InputMismatchException e) {
					InputCheck = false;
				}
				while(!(InputCheck) || IntTemp < 0) {
					System.out.println("Invalid value. ���ط��� �ٽ� �Է����ּ���");
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
		// ���⿡������ �Է°� ���� �ǽ�
		InputStore.inputStore(ExpirationDate, Container, OriginalPrice, Inadequate);
	}

	
	public void DeliverInput() throws IOException, InterruptedException{
		Deliver InputDeliver = new Deliver();
		Scanner Input = new Scanner(System.in);
		String StrTemp = null;
		int IntTemp = 0;
		boolean InputCheck = true; // �Է°� ��ȿ�� ���� �� ���� Ȯ�ο�
		
		
		System.out.println("��ǰ �̸��� �Է����ֽʽÿ�('|' �Է� �Ұ�)");
		StrTemp = Input.nextLine();
		while(StrTemp.length() > 100 || (StrTemp.indexOf("|") > -1)) {
			System.out.println("Invaild Format. 100�� �̳��� '|' ���� �ٽ� �Է����ֽʽÿ�.");
			StrTemp = Input.nextLine();
		}
		InputDeliver.setName(StrTemp);
		
		System.out.println("�ŷ�ó���� �Է����ֽʽÿ�('|' �Է� �Ұ�)");
		StrTemp = Input.nextLine();
		while(StrTemp.length() > 100 || (StrTemp.indexOf("|") > -1)) {
			System.out.println("Invaild Format. 100�� �̳��� '|' ���� �ٽ� �Է����ֽʽÿ�.");
			StrTemp = Input.nextLine();
		}
		InputDeliver.setClient(StrTemp);
		
		System.out.println("������ڸ� �Է����ֽʽÿ�(yyyy-MM-dd)");
		StrTemp = Input.nextLine();
		while(checkDate(StrTemp) != true) {
			System.out.println("Invaild Format. yyyy-MM-dd�� ���缭 �ٽ� �Է����ֽʽÿ�.");
			StrTemp = Input.nextLine();
		}
		InputDeliver.setDate(StrTemp);
		
		System.out.println("��ǰ ��ȣ�� �Է����ֽʽÿ�");
		StrTemp = Input.nextLine();
		try {
			Long.parseLong(StrTemp);
			InputCheck = true;
		} catch (NumberFormatException e){
			InputCheck = false;
		}
		while(StrTemp.length() <= 13 || !(InputCheck)) {
			System.out.println("Invaild Format. ��ǰ ��ȣ�� ���̿� ���缭 ���ڷ� �ٽ� �Է����ֽʽÿ�.");
			StrTemp = Input.nextLine();
			try {
				Long.parseLong(StrTemp);
				InputCheck = true;
			} catch (NumberFormatException e){
				InputCheck = false;
			}
		}
		InputDeliver.setCode(StrTemp);
		
		System.out.println("����� �� ����(\\)�� �Է����ֽʽÿ�");
		try {
			IntTemp = Input.nextInt();
			InputCheck = true;
		} catch(InputMismatchException e) {
			InputCheck = false;
		}
		while(!(InputCheck) || IntTemp <= 0) {
			System.out.println("Invalid value. ������ �ٽ� �Է����ּ���");
			Input.nextLine();
			try {
				IntTemp = Input.nextInt();
				InputCheck = true;
			} catch(InputMismatchException e) {
				InputCheck = false;
			}
		}
		InputDeliver.setPrice(IntTemp);
		
		System.out.println("��� ������ �Է����ֽʽÿ�");
		try {
			IntTemp = Input.nextInt();
			InputCheck = true;
		} catch(InputMismatchException e) {
			InputCheck = false;
		}
		while(!(InputCheck) || IntTemp <= 0) {
			System.out.println("Invalid value. �������� �ٽ� �Է����ּ���");
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
			// �ٽ� �Է¹޾Ƽ� Ȯ��
			while(!(InputCheck)) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

				System.out.println("��ǰ �̸��� �Է����ֽʽÿ�('|' �Է� �Ұ�)[" + InputDeliver.getName() + "]");
				StrTemp = Input.nextLine();
				while(StrTemp.length() > 100 || (StrTemp.indexOf("|") > -1)) {
					System.out.println("Invaild Format. 100�� �̳��� '|' ���� �ٽ� �Է����ֽʽÿ�.");
					StrTemp = Input.nextLine();
				}
				InputDeliver.setName(StrTemp);
				
				System.out.println("�ŷ�ó���� �Է����ֽʽÿ�('|' �Է� �Ұ�)[" + InputDeliver.getClient() + "]");
				StrTemp = Input.nextLine();
				while(StrTemp.length() > 100 || (StrTemp.indexOf("|") > -1)) {
					System.out.println("Invaild Format. 100�� �̳��� '|' ���� �ٽ� �Է����ֽʽÿ�.");
					StrTemp = Input.nextLine();
				}
				InputDeliver.setClient(StrTemp);
				
				System.out.println("������ڸ� �Է����ֽʽÿ�(yyyy-MM-dd)[" + InputDeliver.getDeliverDate() + "]");
				StrTemp = Input.nextLine();
				while(checkDate(StrTemp) != true) {
					System.out.println("Invaild Format. yyyy-MM-dd�� ���缭 �ٽ� �Է����ֽʽÿ�.");
					StrTemp = Input.nextLine();
				}
				InputDeliver.setDate(StrTemp);
				
				System.out.println("��ǰ ��ȣ�� �Է����ֽʽÿ�[" + InputDeliver.getCode() + "]");
				StrTemp = Input.nextLine();
				try {
					Long.parseLong(StrTemp);
					InputCheck = true;
				} catch (NumberFormatException e){
					InputCheck = false;
				}
				while(StrTemp.length() <= 13 || !(InputCheck)) {
					System.out.println("Invaild Format. ��ǰ ��ȣ�� ���̿� ���缭 ���ڷ� �ٽ� �Է����ֽʽÿ�.");
					StrTemp = Input.nextLine();
					try {
						Long.parseLong(StrTemp);
						InputCheck = true;
					} catch (NumberFormatException e){
						InputCheck = false;
					}
				}
				InputDeliver.setCode(StrTemp);
				
				System.out.println("������� �� ����(\\)�� �Է����ֽʽÿ�[" + InputDeliver.getPrice() + "]");
				try {
					IntTemp = Input.nextInt();
					InputCheck = true;
				} catch(InputMismatchException e) {
					InputCheck = false;
				}
				while(!(InputCheck) || IntTemp <= 0) {
					System.out.println("Invalid value. ������ �ٽ� �Է����ּ���");
					Input.nextLine();
					try {
						IntTemp = Input.nextInt();
						InputCheck = true;
					} catch(InputMismatchException e) {
						InputCheck = false;
					}
				}
				InputDeliver.setPrice(IntTemp);
				
				System.out.println("��� ������ �Է����ֽʽÿ�[" + InputDeliver.getAmount() + "]");
				try {
					IntTemp = Input.nextInt();
					InputCheck = true;
				} catch(InputMismatchException e) {
					InputCheck = false;
				}
				while(!(InputCheck) || IntTemp <= 0) {
					System.out.println("Invalid value. �������� �ٽ� �Է����ּ���");
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
			System.out.println("����� Ȯ�� ����. ��� ��� �����͸� ������ ��ǰ �����Ͱ� �������� �ʽ��ϴ�. ó������ �ٽ� �Է����ֽʽÿ�.");
		} else {
			System.out.println("����� ���� ����. ��ǰ �����Ϳ� ���������� ��� ��� �����Ͱ� ����Ǿ����ϴ�.");
		}
		// ��ǰ��ȣ Ȯ���ؼ� ��ǰ ������ �ݿ����ϰ� ����
		// �ƴϸ� �ݿ�
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
		System.out.println("�Է��Ͻ� �԰� ����Դϴ�.");
		System.out.println("��ǰ�̸�: " + InputStore.getName());
		System.out.println("�԰�: " + InputStore.getAmount());
		System.out.println("�԰�����: " + InputStore.getStoreDate());
		System.out.println("�԰�: " + InputStore.getPrice());
		System.out.println("���ڵ��ȣ: " + InputStore.getCode());
		System.out.println("�ŷ�ó: " + InputStore.getClient());
		System.out.println("�������: " + ExpirationDate);
		System.out.println("â����ġ: " + Container);
		System.out.println("����: " + OriginalPrice);
		System.out.println("�����˸� ���ط�: " + Inadequate);
		System.out.print("���� �Է��� �½��ϱ�(y/n)? ");
		yn = (char) System.in.read();
		do {
			temp = (char) System.in.read();
		}while(temp != '\n');
		
		if(!(yn == 'y' || yn == 'n' || yn == 'Y' || yn == 'N')) {
			while (!(yn == 'y' || yn == 'n' || yn == 'Y' || yn == 'N')) { // y�� n�� �ƴϸ� ���� Ȯ��
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				System.out.println("temp : " + temp + ", yn : " + yn);
				System.out.println("�Է��Ͻ� �԰� ����Դϴ�.");
				System.out.println("��ǰ�̸�: " + InputStore.getName());
				System.out.println("�԰�: " + InputStore.getAmount());
				System.out.println("�԰�����: " + InputStore.getStoreDate());
				System.out.println("�԰�: " + InputStore.getPrice());
				System.out.println("���ڵ��ȣ: " + InputStore.getCode());
				System.out.println("�ŷ�ó: " + InputStore.getClient());
				System.out.println("�������: " + ExpirationDate);
				System.out.println("â����ġ: " + Container);
				System.out.println("����: " + OriginalPrice);
				System.out.println("�����˸� ���ط�: " + Inadequate);
				System.out.print("���� �Է��� �½��ϱ�(y/n)? ");
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
			System.err.println("�԰��� Ȯ�� ����. �ý����� ��������ֽʽÿ�.");
			System.exit(1);
		}
		return returnValue;
	}
	
	private boolean checkDeliverInput(Deliver InputStore) throws IOException, InterruptedException {
		char yn, temp;
		boolean returnValue = true;
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		System.out.println("�Է��Ͻ� ��� ����Դϴ�.");
		System.out.println("��ǰ�̸�: " + InputStore.getName());
		System.out.println("���: " + InputStore.getAmount());
		System.out.println("�������: " + InputStore.getDeliverDate());
		System.out.println("���: " + InputStore.getPrice());
		System.out.println("��ǰ��ȣ: " + InputStore.getCode());
		System.out.println("�ŷ�ó: " + InputStore.getClient());
		System.out.print("���� �Է��� �½��ϱ�(y/n)? ");
		yn = (char)System.in.read();
		do {
			temp = (char) System.in.read();
		}while(temp != '\n');
		
		if(!(yn == 'y' || yn == 'n' || yn == 'Y' || yn == 'N')) {
			while (!(yn == 'y' || yn == 'n' || yn == 'Y' || yn == 'N')) { // y�� n�� �ƴϸ� ���� Ȯ��
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				System.out.println("�Է��Ͻ� ��� ����Դϴ�.");
				System.out.println("��ǰ�̸�: " + InputStore.getName());
				System.out.println("���: " + InputStore.getAmount());
				System.out.println("�������: " + InputStore.getDeliverDate());
				System.out.println("���: " + InputStore.getPrice());
				System.out.println("��ǰ��ȣ: " + InputStore.getCode());
				System.out.println("�ŷ�ó: " + InputStore.getClient());
				System.out.print("���� �Է��� �½��ϱ�(y/n)? ");
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
			System.err.println("����� Ȯ�� ����. �ý����� ��������ֽʽÿ�.");
			System.exit(1);
		}
		return returnValue;
	}
}
