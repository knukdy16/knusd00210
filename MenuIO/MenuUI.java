package MenuIO;

import StockRecordIO.ProductUI;
import StockRecordIO.StoreDeliverIO;
import EventUI.EventUI;
import Login.loginfunction;
import ProductManagement.Deliver;
import ProductManagement.ProductList;
import ProductManagement.Store;

import java.io.IOException;

import Alarm.Alarm;

public class MenuUI {
	private char menu;
	public void PrintMenu() throws Exception {
		char interMenu, temp;
		Alarm arm = new Alarm(new ProductList(), new Store(), new Deliver());
		while(menu != '9') {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			System.out.println("환영합니다, 관리자님. K 쇼핑몰 재고관리 시스템입니다.");
			
			/*
			 * 알람 출력해주는 부분이져~
			 */
			arm.callAlarm();

			System.out.println("아래의 메뉴를 보고 실행하고자 하는 메뉴 번호를 입력해주세요");
			System.out.println("1. 입출고 기록 입력");
			System.out.println("2. 입출고 기록 열람");
			System.out.println("3. 물품 기록 열람 및 검색");
			System.out.println("4. 할인율 설정");
			System.out.println("5. 비밀번호 수정");
			System.out.println("9. 시스템 종료");
			menu = (char)System.in.read();
			do {
				temp = (char)System.in.read();
			} while(temp != '\n');
			
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			switch(menu) {
			case '1':
				System.out.println("입력할 기록의 종류를 선택하십시오(1: 입고, 2: 출고, 이외: 복귀)");
				interMenu = (char)System.in.read();
				do {
					temp = (char)System.in.read();
				} while(temp != '\n');
				StoreDeliverIO RecordInput = new StoreDeliverIO();
				switch(interMenu) {
				case '1':
					RecordInput.StoreInput();
					break;
				case '2':
					RecordInput.DeliverInput();
					break;
				default:
					break;
				}
				break;
			case '2':
				System.out.println("열람할 기록의 종류를 선택하십시오(1: 입고, 2: 출고, 이외: 복귀)");
				interMenu = (char)System.in.read();
				do {
					temp = (char)System.in.read();
				} while(temp != '\n');
				StoreDeliverIO RecordOutput = new StoreDeliverIO();
				switch(interMenu) {
				case '1':
					RecordOutput.StoreOutput();
					break;
				case '2':
					RecordOutput.DeliverOutput();
					break;
				default:
					break;
				}
				break;
			case '3':
				System.out.println("수행할 기능을 선택하십시오(1: 열람, 2: 검색, 이외: 복귀)");
				interMenu = (char)System.in.read();
				do {
					temp = (char)System.in.read();
				} while(temp != '\n');
				ProductUI ProductOutput = new ProductUI();
				switch(interMenu) {
				case '1':
					ProductOutput.ProductListOutput();
					break;
				case '2':
					ProductOutput.ProductSearch();
					break;
				default:
					break;
				}
				break;
			case '4':
				EventUI event = new EventUI();
				event.setEvent();
				break;
			case '5':
				loginfunction Login = new loginfunction();
				Login.setpw();
				break;
			case '9':
				System.out.println("프로그램을 종료합니다.");
				break;
			default:
			}
			System.out.println("계속하려면 엔터 키를 누르십시오...");
			System.in.read();
			do {
				temp = (char)System.in.read();
			} while(temp != '\n');
		}
		System.exit(0);
	}
}
