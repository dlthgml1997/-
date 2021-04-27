class Solution {
    static int[] numberList;
    static int answer =0;
    public int solution(int[] numbers, int target) {
        numberList = numbers;
        dfs(0, target, 0);
        return answer;
    }

    private void dfs(int cnt, int target, int num) {
        if(cnt == numberList.length) {
            if(num == target) {
                answer++;
            }
            return;
        }

        dfs(cnt+1, target, num+numberList[cnt]);
        dfs(cnt+1, target, num-numberList[cnt]);
    }
}