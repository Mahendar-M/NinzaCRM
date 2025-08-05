package ddt;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ToReadFromJson {

	public static void main(String[] args) throws IOException, ParseException {
		
		FileReader fir = new FileReader(".\\src\\test\\resources\\Commondata1.json");
		JSONParser parser = new JSONParser();
		Object javaobj = parser.parse(fir);
		JSONObject obj = (JSONObject) javaobj;
		String Browser = obj.get("Browser").toString();
		System.out.println(Browser);
		String Username = obj.get("Username").toString();
		System.out.println(Username);
		String Password = obj.get("Password").toString();
		System.out.println(Password);
		
		

	}

}



