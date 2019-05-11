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
    System.out.printf("arrayList입니다 : %s",arrayList);
    System.out.println();
    int length = 0;
    System.out.printf("현재 길이는 %d입니다.",array_size);
    System.out.println();

    for(int i =0; i<array_size-1; i++) {
    	int cur_var = arrayList.get(i);
    	if(cur_var == target) {
    		cnt++;
    		System.out.println("cnt가 증가되었습니다.");
    		//arrayList.set(i, 0);
    		arrayList.remove(i);
    	}
    }
    
    
    int total =0;
    
    for(int i=0; i<array_size-1; i++) {
    	int cur_value = arrayList.get(i);
    	int cur_total = total;
    	total += cur_value;
    	System.out.printf("현재 total 값 입니다 : %d ", total);
		System.out.println();
    	
    	if(total >= 4) {
    		cnt++;
    		System.out.println("cnt가 증가되었습니다.");
    		total = total-target;
    		System.out.printf("현재 total 값 입니다 : %d ", total);
    		System.out.println();
    	}
    	
    	arrayList.set(i, 0);
    }
    
    if(total >0) {
    cnt++;
	System.out.printf("현재 total 값 입니다 : %d ", total);
	System.out.println();
    }
    
    System.out.printf("현재 total 값 입니다 : %d ", total);
    System.out.printf("현재 cnt 값 입니다 : %d ", cnt);
	System.out.println();
	
	}

}
