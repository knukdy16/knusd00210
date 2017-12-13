package Event;
import java.util.InputMismatchException;
import java.util.Scanner;


public class EventUI {
	private Event event;
	
	public void setEvent(){
		Scanner scan = new Scanner(System.in); 
		String name;
		double sale=1;
		boolean InputCheck;
		
		System.out.println("Product name : ");
		name = scan.nextLine();
		try {
			name = scan.nextLine();
			InputCheck = true;
		} catch(InputMismatchException e) {
			InputCheck = false;
		}
		while(!(InputCheck)) {
			System.out.println("Invalid value for name.");
			scan.nextLine();
			try {
				name = scan.nextLine();
				InputCheck = true;
			} catch(InputMismatchException e) {
				InputCheck = false;
			}
		}
		
		
		
		System.out.println("Product sale : ");
		try {
			sale = scan.nextDouble();
			InputCheck = true;
		} catch(InputMismatchException e) {
			InputCheck = false;
		}
		while(!(InputCheck) || sale< 1 || sale>=0) {
			System.out.println("Invalid value for sale.");
			scan.nextLine();
			try {
				sale = scan.nextDouble();
				InputCheck = true;
			} catch(InputMismatchException e) {
				InputCheck = false;
			}
		}
		
		
		event.settingSale(name, sale);
		
	}
	
}
