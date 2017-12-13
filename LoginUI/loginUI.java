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
		
		System.out.print("비밀번호를 입력해주십시오: ");
		pw = input.nextLine();
		if(!temp.logincheck(pw)) {
			do {
				cnt++;
				System.out.print("비밀번호가 틀렸습니다. 다시입력해주십시오: ");
				pw = input.nextLine();
				if(cnt%5==0) {
					System.out.println("비밀번호를 " + cnt + " 회 틀렸습니다. 15초 정지합니다.");
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
