package LoginUI;

import java.util.Scanner;

import Login.loginfunction;
import MenuIO.MenuUI;

public class loginUI {
	public static void main(String args[]) throws Exception {
		loginUI startLogin = new loginUI();
		startLogin.loginui();
	}
	
	public void loginui() throws Exception {
		int cnt=1;
		Scanner input=new Scanner(System.in);
		String pw;
		loginfunction temp = new loginfunction();
		
		System.out.print("��й�ȣ�� �Է����ֽʽÿ�: ");
		pw = input.nextLine();
		if(!temp.logincheck(pw)) {
			do {
				cnt++;
				System.out.print("��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ��Է����ֽʽÿ�: ");
				pw = input.nextLine();
				if(cnt%5==0) {
					System.out.println("��й�ȣ�� " + cnt + " ȸ Ʋ�Ƚ��ϴ�. 15�� �����մϴ�.");
					try {
						Thread.sleep(15000);
					}catch(InterruptedException e) {
						System.out.println(e.getMessage());
					}
				}
			}while(!temp.logincheck(pw));
		}

		MenuUI menu = new MenuUI();
		menu.PrintMenu();
	}
}
