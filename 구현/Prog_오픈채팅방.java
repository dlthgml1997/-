import java.util.*;

public class Prog_오픈채팅방 {

	public String[] solution(String[] record) {
		HashMap<String, String> user = new HashMap<>();
		String oper = "";
		String uid = "";
		String nickName = "";
		int recordLen = record.length;

		for (String r : record) {
			oper = r.split(" ")[0];
			uid = r.split(" ")[1];
			if (!oper.equals("Leave"))
				nickName = r.split(" ")[2];
			switch (oper) {
			case "Enter":
				user.put(uid, nickName); // 유저가 들어옴
				break;
			case "Change":
				user.replace(uid, nickName); // 유저 닉네임 변경
				recordLen--;
				break;
			}
		}

		String[] answer = new String[recordLen];
		int i = 0;
		for (String r : record) {
			oper = r.split(" ")[0];
			uid = r.split(" ")[1];
			switch (oper) {
			case "Enter":
				answer[i++] = user.get(uid) + "님이 들어왔습니다.";
				break;
			case "Leave":
				answer[i++] = user.get(uid) + "님이 나갔습니다.";
				break;
			}
		}
		return answer;
	}
}
