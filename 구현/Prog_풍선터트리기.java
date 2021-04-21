public class Prog_풍선터트리기 {
    public int solution(int[] a) {
        int answer = 0;
        int[] leftMin = new int[1000000+1];
        int[] rightMin = new int[1000000+1];
        leftMin[0] = a[0];
        for(int i=1; i< a.length; i++) {
            leftMin[i] = Math.min(leftMin[i-1], a[i]);
        }
        
        rightMin[a.length-1] = a[a.length-1];
        for(int i=a.length-2; i>= 0; i--) {
            rightMin[i] = Math.min(rightMin[i+1], a[i]);
        }
        
        for(int i=0; i<a.length; i++) {
            if(i == 0 || i == a.length-1) {
                answer++; continue;
            }
            if(leftMin[i-1] < a[i] && rightMin[i+1] < a[i]) {
                continue;
            }
            answer++;
        }
        return answer;
    }
}