public class Main {

	public static void main(String[] args) {
		System.out.println(solution(5, new int[] {2, 4},  new int[] {3}));
	}

	public static int solution(int n, int[] lost, int[] reserve) {
		int res = n - lost.length;
		for (int i = 0; i < reserve.length; i++)
            for (int j = 0; j < lost.length; j++)
                if (reserve[i] == lost[j]) {
                	lost[j] = -10;
                	reserve[i] = -10;
                	break;
                }
            
		for (int i = 0; i < reserve.length; i++)
			if (reserve[i] != -10)
	            for (int j = 0; j < lost.length; j++)
	                if (lost[j] != -10 && (reserve[i]+1 == lost[j] || reserve[i]-1 == lost[j])) {
	                	lost[j] = -10;
	                	reserve[i] = -10;
	                	res++;
	                	break;
	                }
		return res;
	}
}
