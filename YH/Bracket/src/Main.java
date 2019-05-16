import java.util.ArrayList;

public class Main {

	public static void main(String args[]) {
		//System.out.println(solution(new int[] {1, 2, 3, 4, 5}, 15));
		System.out.println(solution(3));
	}
	
	private static ArrayList<String> res;
	private static int target;
	
	public static String[] solution(int target) {
		Main.res = new ArrayList<>();
		Main.target = target;
		recur("", 0, 0);
		
		String[] result = new String[res.size()];
		for (int i = 0; i < res.size(); i++)
			result[i] = res.get(i);
		return result;
	}
	
	public static void recur(String cur, int open, int close) {
		if (close == target)
				res.add(cur);
		else {
			if (open < target)
				recur(cur+"(", open+1, close);
			if (open > close)
				recur(cur+")", open, close+1);
		}
	}
}
