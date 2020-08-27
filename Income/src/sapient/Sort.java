package sapient;

import java.util.Comparator;

public class Sort implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		String arr1[] = ((String) o1).split(",");
		String arr2[] = ((String) o2).split(",");
		return (int)Double.parseDouble(arr1[2]) - (int)Double.parseDouble(arr2[2]);
		
	}

}
