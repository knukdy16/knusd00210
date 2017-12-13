package ProductManagement;

import java.util.Comparator;
public class ComparatorStore implements Comparator<Store>{
	public int compare(Store o1, Store o2) {
		String t1, t2;
		int returnValue = 0;
		t1 = o1.getStoreDate();
		t2 = o2.getStoreDate();
		if(t1.compareTo(t2) == 0) { // 같은 문자열
			returnValue = 0;
		} else if(t1.compareTo(t2) < 0) { // 사전순으로 앞
			returnValue = -1;
		} else if(t1.compareTo(t2) > 0) { // 사전순으로 뒤
			returnValue = 1;
		}
		return returnValue;
	}
}
