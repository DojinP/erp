package test.json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONParserExam {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		// 1. json parser 를 생성
		JSONParser parser = new JSONParser();
		
		// 2. json 문자열을 파싱
		JSONObject root = (JSONObject) parser.parse(new FileReader("src/main/java/test/json/myjson2.json"));
		
		// 3. json object 에서 데이터 읽기
		String name = (String) root.get("name");
		String age = (String) root.get("age");
		System.out.println("이름 : " + name);
		System.out.println("나이 : " + age);
		
		JSONObject addrInfo = (JSONObject) root.get("addr");
		String zip = (String) addrInfo.get("zip");
		String addrl = (String) addrInfo.get("addrl");
		System.out.println("우편번호 : " + zip);
		System.out.println("주소 : " + addrl);
		
		// 4. JSONArray 에서 데이터 읽기
		JSONArray subjectInfo = (JSONArray) root.get("subject");
		for(int i=0; i<subjectInfo.size(); i++) {
			String subject = (String) subjectInfo.get(i);
			System.out.println("subject_" + (i+1) + ":" + subject);
		}
		System.out.println("=====================================");
		JSONArray historyInfo = (JSONArray) root.get("history");
		for(int i=0; i<historyInfo.size(); i++) {
			JSONObject history = (JSONObject) historyInfo.get(i);
			System.out.println("month:" + history.get("month"));
			System.out.println("subject:" + history.get("subject"));
			System.out.println("=====================================");
		}
	}
}
