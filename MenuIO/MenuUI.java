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
			System.out.println("ȯ���մϴ�, �����ڴ�. K ���θ� ������ �ý����Դϴ�.");
			
			System.out.println("�Ʒ��� �޴��� ���� �����ϰ��� �ϴ� �޴� ��ȣ�� �Է����ּ���");
			System.out.println("1. ����� ��� �Է�");
			System.out.println("2. ����� ��� ����");
			System.out.println("3. ��ǰ ��� ���� �� �˻�");
			System.out.println("4. ������ ����");
			System.out.println("5. ��й�ȣ ����");
			System.out.println("9. �ý��� ����");
			menu = (int) System.in.read();
			do {
				temp = (int) System.in.read();
			}while(temp != '\n');
			
			switch(menu) {
			case 1:
				System.out.println("�Է��� ����� ������ �����Ͻʽÿ�(1: �԰�, 2: ���)");
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
				System.out.println("������ ����� ������ �����Ͻʽÿ�(1: �԰�, 2: ���)");
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
				System.out.println("������ ����� �����Ͻʽÿ�(1: ����, 2: �˻�)");
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
				System.out.println("���α׷��� �����մϴ�.");
				break;
			default:
			}
		}
		System.exit(0);
	}
}
