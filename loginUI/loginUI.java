package loginUI;

import java.util.Scanner;

import loginFunction.loginfunction;

public class loginUI {
	public void loginui(){
		int cnt=0;
		Scanner input=new Scanner(System.in);
		String pw;
		loginfunction temp = new loginfunction();
		
		System.out.println("��й�ȣ�� �Է����ֽʽÿ�:");
		pw = input.nextLine();
		if(!temp.mlogin(pw)) {
			do {
				cnt++;
				System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ��Է����ֽʽÿ�:");
				pw = input.nextLine();
				if(cnt%5==0) {
					System.out.println("��й�ȣ�� "+cnt+"ȸ Ʋ�Ƚ��ϴ�.\n15�� �����մϴ�.");
					try {
						Thread.sleep(15000);
					}catch(InterruptedException e) {
						System.out.println(e.getMessage());
					}
				}
			}while(!temp.mlogin(pw));
		}
		System.out.println("��й�ȣ�� �½��ϴ� ȯ���մϴ�.");
	}
}
