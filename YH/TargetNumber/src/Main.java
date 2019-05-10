public class Main {

	public static void main(String args[]) {
		System.out.println(solution(new int[] {1, 2, 3, 4, 5}, 15));
	}
	
	private static int cnt;
	private static int[] numbers;
	private static int target;
	
	public static int solution(int[] numbers, int target) {
		cnt = 0;
		Main.numbers = numbers;
		Main.target = target;
		recur(0, 0);
		
		return cnt;
	}
	
	public static void recur(int cur, int index) {
		if (index == numbers.length) {
			if (cur == target)
				cnt++;
		}
		else {
			recur(cur+numbers[index], index+1);
			recur(cur-numbers[index], index+1);
		}
	}
}
