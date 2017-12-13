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
			System.out.println("ȯ���մϴ�, �����ڴ�. K ���θ� ������ �ý����Դϴ�.");
			
			/*
			 * �˶� ������ִ� �κ�����~
			 */
			arm.callAlarm();

			System.out.println("�Ʒ��� �޴��� ���� �����ϰ��� �ϴ� �޴� ��ȣ�� �Է����ּ���");
			System.out.println("1. ����� ��� �Է�");
			System.out.println("2. ����� ��� ����");
			System.out.println("3. ��ǰ ��� ���� �� �˻�");
			System.out.println("4. ������ ����");
			System.out.println("5. ��й�ȣ ����");
			System.out.println("9. �ý��� ����");
			menu = (char)System.in.read();
			do {
				temp = (char)System.in.read();
			} while(temp != '\n');
			
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			switch(menu) {
			case '1':
				System.out.println("�Է��� ����� ������ �����Ͻʽÿ�(1: �԰�, 2: ���, �̿�: ����)");
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
				System.out.println("������ ����� ������ �����Ͻʽÿ�(1: �԰�, 2: ���, �̿�: ����)");
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
				System.out.println("������ ����� �����Ͻʽÿ�(1: ����, 2: �˻�, �̿�: ����)");
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
				System.out.println("���α׷��� �����մϴ�.");
				break;
			default:
			}
			System.out.println("����Ϸ��� ���� Ű�� �����ʽÿ�...");
			System.in.read();
			do {
				temp = (char)System.in.read();
			} while(temp != '\n');
		}
		System.exit(0);
	}
}
