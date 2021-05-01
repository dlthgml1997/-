import java.util.*;
import java.io.*;

class Solution {
    static String[][] ticketsInfo;
    static boolean[] visited;
    static List<String> list = new ArrayList<String>();
    static String[] answer = {};

    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length]; // 총 방문 횟수
        ticketsInfo = tickets;

        Arrays.sort(ticketsInfo, (o1, o2) -> {
           if(o1[0].equals(o2[0])) {
               return o1[1].compareTo(o2[1]);
           } else {
               return o1[0].compareTo(o2[0]);
           }
        });

        list.add("ICN");
        dfs("ICN", 0);
        return answer;
    }

    private void dfs(String start, int cnt) {
        if(answer.length>0) return; // 이미 알파벳이 더 빠른 정답이 나온 경우

        if(cnt == visited.length) {
            answer = new String[list.size()];
            for(int i=0; i<list.size(); i++) {
                answer[i] = list.get(i);
            }
            return;
        }

        for(int i=0 ; i< ticketsInfo.length; i++) {
            if(!visited[i] && ticketsInfo[i][0].equals(start)) {
                visited[i] = true;
                list.add(ticketsInfo[i][1]);
                dfs(ticketsInfo[i][1], cnt +1);
                list.remove(list.size()-1);
                visited[i] = false;
            }
        }
    }
}