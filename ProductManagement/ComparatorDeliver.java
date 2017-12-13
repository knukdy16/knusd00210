package ProductManagement;

import java.util.Comparator;

public class ComparatorDeliver implements Comparator<Deliver>{
	public int compare(Deliver o1, Deliver o2) {
		String t1, t2;
		int returnValue = 0;
		t1 = o1.getDeliverDate();
		t2 = o2.getDeliverDate();
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
