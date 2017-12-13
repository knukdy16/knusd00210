package MenuIO;

import StockRecordIO.ProductUI;
import StockRecordIO.StoreDeliverIO;
import java.io.IOException;

public class MenuUI {
	private int menu;
	public void PrintMenu() throws IOException, InterruptedException {
		int interMenu, temp;
		
		while(menu != 9) {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			System.out.println("환영합니다, 관리자님. K 쇼핑몰 재고관리 시스템입니다.");
			
			System.out.println("아래의 메뉴를 보고 실행하고자 하는 메뉴 번호를 입력해주세요");
			System.out.println("1. 입출고 기록 입력");
			System.out.println("2. 입출고 기록 열람");
			System.out.println("3. 물품 기록 열람 및 검색");
			System.out.println("4. 할인율 설정");
			System.out.println("5. 비밀번호 수정");
			System.out.println("9. 시스템 종료");
			menu = (int) System.in.read();
			do {
				temp = (int) System.in.read();
			}while(temp != '\n');
			
			switch(menu) {
			case 1:
				System.out.println("입력할 기록의 종류를 선택하십시오(1: 입고, 2: 출고)");
				interMenu = (int) System.in.read();
				do {
					temp = (int) System.in.read();
				}while(temp != '\n');
				StoreDeliverIO RecordInput = new StoreDeliverIO();
				switch(interMenu) {
				case 1:
					RecordInput.StoreInput();
					break;
				case 2:
					RecordInput.DeliverInput();
					break;
				}
				break;
			case 2:
				System.out.println("열람할 기록의 종류를 선택하십시오(1: 입고, 2: 출고)");
				interMenu = (int) System.in.read();
				do {
					temp = (int) System.in.read();
				}while(temp != '\n');
				StoreDeliverIO RecordOutput = new StoreDeliverIO();
				switch(interMenu) {
				case 1:
					RecordOutput.StoreOutput();
					break;
				case 2:
					RecordOutput.DeliverOutput();
					break;
				}
				break;
			case 3:
				System.out.println("수행할 기능을 선택하십시오(1: 열람, 2: 검색)");
				interMenu = (int) System.in.read();
				do {
					temp = (int) System.in.read();
				}while(temp != '\n');
				ProductUI ProductOutput = new ProductUI();
				switch(interMenu) {
				case 1:
					ProductOutput.ProductListOutput();
					break;
				case 2:
					ProductOutput.ProductSearch();
					break;
				}
				break;
			case 4:
				break;
			case 5:
				break;
			case 9:
				System.out.println("프로그램을 종료합니다.");
				break;
			default:
			}
		}
		System.exit(0);
	}
}
