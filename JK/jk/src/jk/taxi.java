package jk;

import java.util.ArrayList;

public class taxi {
	
	public static ArrayList<Integer> arrayList = new ArrayList<>();
	public static int target = 4;
	public static int cnt    =0;
	
	public static void main(String[] args) {

    int[] array = {2,3,4,4,2,1,3,1};
    
    for(int temp : array){
	  arrayList.add(temp);

    }
    
    int array_size = arrayList.size();
    System.out.printf("arrayList�Դϴ� : %s",arrayList);
    System.out.println();
    int length = 0;
    System.out.printf("���� ���̴� %d�Դϴ�.",array_size);
    System.out.println();

    for(int i =0; i<array_size-1; i++) {
    	int cur_var = arrayList.get(i);
    	if(cur_var == target) {
    		cnt++;
    		System.out.println("cnt�� �����Ǿ����ϴ�.");
    		//arrayList.set(i, 0);
    		arrayList.remove(i);
    	}
    }
    
    
    int total =0;
    
    for(int i=0; i<array_size-1; i++) {
    	int cur_value = arrayList.get(i);
    	int cur_total = total;
    	total += cur_value;
    	System.out.printf("���� total �� �Դϴ� : %d ", total);
		System.out.println();
    	
    	if(total >= 4) {
    		cnt++;
    		System.out.println("cnt�� �����Ǿ����ϴ�.");
    		total = total-target;
    		System.out.printf("���� total �� �Դϴ� : %d ", total);
    		System.out.println();
    	}
    	
    	arrayList.set(i, 0);
    }
    
    if(total >0) {
    cnt++;
	System.out.printf("���� total �� �Դϴ� : %d ", total);
	System.out.println();
    }
    
    System.out.printf("���� total �� �Դϴ� : %d ", total);
    System.out.printf("���� cnt �� �Դϴ� : %d ", cnt);
	System.out.println();
	
	}

}
