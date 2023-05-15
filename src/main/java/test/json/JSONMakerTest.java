package test.json;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

// JSON 형식의 데이터를 만들어보기
/*
 * {} : JSON 오브젝트 => JSONObject
 * [] : JSON Array =>   
 *  
 */

public class JSONMakerTest {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		JSONObject myjson = new JSONObject();
		myjson.put("name", "방탄소년단");
		myjson.put("age", "30");
		System.out.println(myjson.toJSONString());
		JSONArray subjectlist = new JSONArray();
		subjectlist.add("자바");
		subjectlist.add("스프링");
		subjectlist.add("하둡");
		// 위에서 작성한 JSONArray를 JSONObject에 추가하기
		
		JSONArray commentlist = new JSONArray();
		
		JSONObject comment1 = new JSONObject();
		comment1.put("no", "1");
		comment1.put("content", "json만들기");
		comment1.put("id", "bts1");
		
		JSONObject comment2 = new JSONObject();
		comment2.put("no", "2");
		comment2.put("content", "json만들기2");
		comment2.put("id", "bts2");
		
		commentlist.add(comment1);
		commentlist.add(comment2);
		
		myjson.put("comment", commentlist);
		myjson.put("subject", subjectlist);
		System.out.println(myjson.toJSONString());
		
		// json 파일을 생성 - 파일 io
		FileWriter fw = new FileWriter("src/main/java/test/json/myjson.json");
		fw.write(myjson.toJSONString());
		fw.flush();
		fw.close();
	}
}