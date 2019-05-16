import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static void main(String args[]) {
		System.out.println(Arrays.toString(solution(new int[] {1, 2, 5, 8, 9})));
	}
	
	public static int[] solution(int[] pattern) {
		ArrayList<Line> lines = new ArrayList<>();
		
		for (int i = 0; i < pattern.length - 1; i++) {
			Location l1 = new Location(pattern[i]%3.0, 3.0 - pattern[i]/3);
			Location l2 = new Location(pattern[i+1]%3.0, 3.0 - pattern[i+1]/3);
			Line temp = new Line(l2.y-l1.y, l1.x-l2.x, l2.x*l1.y-l1.x*l2.y);
			temp.l1 = l1;
			temp.l2 = l2;
			lines.add(temp);
		}
		
		int[] result = new int[lines.size()];
		
		for (int i = 0; i < lines.size(); i++) {
			Line li1 = lines.get(i);
			for (int j = 0; j < lines.size(); j++) {
				if (i == j)
					continue;
				Line li2 = lines.get(j);
				//if (li1.a*li2.b - li2.a*li1.b == 0)
					//continue;
				Location cross;
				if (li1.b == 0)
					cross = new Location((li1.b*li2.c-li2.b*li1.c)/(li1.a*li2.b-li2.a*li1.b), (-li2.c+li1.c/li1.a*li2.a)/li2.b);
				else if (li2.b == 0)
					cross = new Location((li1.b*li2.c-li2.b*li1.c)/(li1.a*li2.b-li2.a*li1.b), (-li1.c+li2.c/li2.a*li1.a)/li1.b);
				else
					cross = new Location((li1.b*li2.c-li2.b*li1.c)/(li1.a*li2.b-li2.a*li1.b), -li1.a/li1.b*(li1.b*li2.c-li2.b*li1.c)/(li1.a*li2.b-li2.a*li1.b)-li1.c/li1.b);
				if (((cross.x >= li1.l1.x && cross.x <= li1.l2.x) || (cross.x <= li1.l1.x && cross.x >= li1.l2.x))
						&& ((cross.y >= li1.l1.y && cross.y <= li1.l2.y) || (cross.y <= li1.l1.y && cross.y >= li1.l2.y))
						&& ((cross.x >= li2.l1.x && cross.x <= li2.l2.x) || (cross.x <= li2.l1.x && cross.x >= li2.l2.x))
						&& ((cross.y >= li2.l1.y && cross.y <= li2.l2.y) || (cross.y <= li2.l1.y && cross.y >= li2.l2.y)))
					result[i]++;
			}
		}
		
		
		return result;
	}
	
	public static class Location {
		private double x;
		private double y;
		public Location(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	public static class Line {
		private double a;
		private double b;
		private double c;
		private Location l1;
		private Location l2;
		public Line(double a, double b, double c) {
			super();
			this.a = a;
			this.b = b;
			this.c = c;
		}
	}
}
