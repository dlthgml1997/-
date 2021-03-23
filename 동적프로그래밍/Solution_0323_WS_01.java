public class Solution_0323_WS_01 {
	static int[] yellow, blue;
	public static void main(String[] args) {
		yellow= new int[9];
		blue = new int[9];
		yellow[1] = 1; blue[1] = 1;
		yellow[2] = 2; blue[2] = 1;
		for(int i=3; i<=8; i++) {
			blue[i] = yellow[i-1];
			yellow[i] = yellow[i-1]+ blue[i-1];
		}
		System.out.println(yellow[8]+blue[8]);
	}
}
