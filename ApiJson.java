package ch11_java_api;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ApiJson {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws ParseException {
		// .jar 파일(라이브러리 파일)을 프로젝트 안에 넣기
		// 프로젝트 마우스 우클릭 - build path
		// -Add jar 클릭 후 프로젝트 내 driver 폴더 찾아 해당 .jar파일 추가해준다
		
		// JSON 객체 만들기
		JSONObject jsonObject = new JSONObject();
		
		// JSON 객체는 내부에 이름과 값의 쌍으로 구성된 데이터를 가진다.
		// Key와 Value 쌍으로 데이터를 담는 HashMap과 유사하다.
		// HashMap<String, Object> 라고 봐도 무방
		jsonObject.put("name", "아이유");
		jsonObject.put("나이", 30);
		
		// JSONArray 배열
		JSONArray jsonArray = new JSONArray();
		jsonArray.add("좋은날");
		jsonArray.add("라일락");
		jsonArray.add("밤편지");
		jsonArray.add("마시멜로우");
		jsonArray.add("있잖아");
		
		jsonObject.put("노래리스트", jsonArray);
		
		System.out.println(jsonObject);
		System.out.println(jsonObject.toString());
		System.out.println(jsonObject.toJSONString());
		
		System.out.println("\n=============================\n");
		
		JSONObject taeyeon = new JSONObject();
		taeyeon.put("name", "태연");
		taeyeon.put("age", 34);
		
		JSONArray songArray = new JSONArray();
		songArray.add("사계");
		songArray.add("만약에");
		songArray.add("INVU");
		songArray.add("제주도의 푸른밤");
		
		taeyeon.put("songList", songArray);
		System.out.println(taeyeon.toJSONString());
		
		jsonObject.put("태연", taeyeon);
		System.out.println(jsonObject.toJSONString());
		
		System.out.println("\n=============================\n");
		
		String serverResult = "{\"노래리스트\":[\"좋은날\",\"라일락\",\"밤편지\",\"마시멜로우\",\"있잖아\"],\"name\":\"아이유\",\"나이\":30,\"태연\":{\"name\":\"태연\",\"songList\":[\"사계\",\"만약에\",\"INVU\",\"제주도의 푸른밤\"],\"age\":34}}";
		
		// JSON 파싱
		JSONParser jsonParser = new JSONParser();
		
		JSONObject iuJson = (JSONObject)jsonParser.parse(serverResult);
		
		// 키를 이용해서 값을 꺼내보기
		System.out.println(iuJson.get("name"));
		String name = (String)iuJson.get("name");
		System.out.println(name);
		
		System.out.println(iuJson.get("나이"));
		long age = (long) iuJson.get("나이");
		System.out.println(age);
		
		JSONArray iuSongs = (JSONArray) iuJson.get("노래리스트");
		for(int i = 0; i < iuSongs.size(); i++) {
			System.out.println(iuSongs.get(i));
		}
		
		JSONObject iuTaeyeon = (JSONObject) iuJson.get("태연");
		
		long taeyeonAge = (long) iuTaeyeon.get("age");
		System.out.println(taeyeonAge);
		
		// 유니크 아이디 생성
		// 0.001 초에 동시에 버튼 누르지 않는 한
		// Guest20220519162534143578753 님 환영합니다.
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date today = new Date();
		String id = "Guest";
		String date = sdf.format(today);
		id += date;
		
		String no = "";
		for(int i = 0; i < 6; i++) {
			int num = (int) Math.floor((Math.random() * 10));
			no += num + "";
		}
		id += no;
		
		System.out.println(id);
		
		
		
		
	}

}
