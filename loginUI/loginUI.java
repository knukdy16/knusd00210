package loginUI;

import java.util.Scanner;

import loginFunction.loginfunction;

public class loginUI {
	public void loginui(){
		int cnt=0;
		Scanner input=new Scanner(System.in);
		String pw;
		loginfunction temp = new loginfunction();
		
		System.out.println("비밀번호를 입력해주십시오:");
		pw = input.nextLine();
		if(!temp.mlogin(pw)) {
			do {
				cnt++;
				System.out.println("비밀번호가 틀렸습니다. 다시입력해주십시오:");
				pw = input.nextLine();
				if(cnt%5==0) {
					System.out.println("비밀번호가 "+cnt+"회 틀렸습니다.\n15초 정지합니다.");
					try {
						Thread.sleep(15000);
					}catch(InterruptedException e) {
						System.out.println(e.getMessage());
					}
				}
			}while(!temp.mlogin(pw));
		}
		System.out.println("비밀번호가 맞습니다 환영합니다.");
	}
}
