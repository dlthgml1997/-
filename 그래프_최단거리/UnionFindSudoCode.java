
public class UnionFindSudoCode {
	static int size = 6;
	static int parent[];
	public static void main(String[] args) {
		parent = new int[size];
		for(int i=0; i< size; i++) {
			parent[i] = i;
		}
		
		union(2,3);
		union(0,2);
		union(4,5);
		
		for(int i=0; i< size;i++) {
			System.out.println(parent[i]);
		}
		
	}
	
	private static void union(int a, int b) {
		int aP = findSet(a);
		int bP = findSet(b);
		
		if(aP!=bP) {
			if(aP> bP) {
				parent[aP] = bP;
			} else {
				parent[bP] = aP;
			}
		}
	}


	private static int findSet(int x) {
		if(parent[x] == x) {
			return x;
		} else {
			return parent[x] = findSet(parent[x]);
		}
	}

}
