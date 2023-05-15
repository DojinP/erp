package test.json;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONMakerExam {

	public static void main(String[] args) throws IOException {
		JSONObject myjson2 = new JSONObject();
		JSONArray history = new JSONArray();
		
		JSONObject subject1 = new JSONObject();
		subject1.put("subject", "java");
		subject1.put("month", "11");
		
		JSONObject subject2 = new JSONObject();
		subject2.put("subject", "servlet");
		subject2.put("month", "12");
		
		history.add(subject1);
		history.add(subject2);
		
		myjson2.put("history", history);
		
		JSONObject addr = new JSONObject();
		addr.put("zip", "111-222");
		addr.put("addrl", "인천시");
		
		myjson2.put("addr", addr);
		
		JSONArray subjectlist = new JSONArray();
		subjectlist.add("자바");
		subjectlist.add("하둡");
		subjectlist.add("시큐어코딩");
		
		myjson2.put("subject", subjectlist);
		
		myjson2.put("age", "25");
		
		myjson2.put("name", "김서연");
	
		FileWriter fw = new FileWriter("src/main/java/test/json/myjson2.json");
		fw.write(myjson2.toJSONString());
		fw.flush();
		fw.close();
		
	}
}
