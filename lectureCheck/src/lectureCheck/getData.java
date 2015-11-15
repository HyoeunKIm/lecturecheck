package lectureCheck;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


class getData {
	//평가안한거 : 0 , 안좋은 평가 : 1, 좋은 평가 : 2, 애매한거 3
	public ArrayList<evalInfo> getPositiveData(String filename){
		ArrayList<evalInfo> evalData = new ArrayList();
		JSONParser parser = new JSONParser();
		try {
			JSONArray jsonArr = (JSONArray) parser.parse(new FileReader(filename));

			for (Object o : jsonArr) {
				JSONObject obj = (JSONObject) o;
				evalInfo info = new evalInfo();
				if((int) (long) obj.get("eval_hide") != 2){
					continue;
				}
				//data들이 받아들일때 long형식으로 받아져서 임의로 형변환
				info.no = (int) (long) obj.get("no");
				info.lec_no = (int) (long) obj.get("lec_no");
				info.mb_no = (int) (long) obj.get("mb_no");
				if(obj.get("mb_nick") instanceof Long){
					info.mb_nick = (String) String.valueOf(obj.get("mb_nick"));
				}else{
					info.mb_nick = (String) obj.get("mb_nick");
				}
				if(obj.get("eval_content") instanceof Long){
					info.eval_content = (String) String.valueOf(obj.get("eval_content"));
				}else{
					info.eval_content = (String) obj.get("eval_content");
				}
				info.eval_state = (int) (long) obj.get("eval_hide");

				evalData.add(info);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return evalData;
	}
	public static ArrayList<evalInfo> getNegativeData(String filename){
		ArrayList<evalInfo> evalData = new ArrayList();
		JSONParser parser = new JSONParser();
		try {
			JSONArray jsonArr = (JSONArray) parser.parse(new FileReader(filename));

			for (Object o : jsonArr) {
				JSONObject obj = (JSONObject) o;
				evalInfo info = new evalInfo();
				if((int) (long) obj.get("eval_hide") != 1){
					continue;
				}
				//data들이 받아들일때 long형식으로 받아져서 임의로 형변환
				info.no = (int) (long) obj.get("no");
				info.lec_no = (int) (long) obj.get("lec_no");
				info.mb_no = (int) (long) obj.get("mb_no");
				if(obj.get("mb_nick") instanceof Long){
					info.mb_nick = (String) String.valueOf(obj.get("mb_nick"));
				}else{
					info.mb_nick = (String) obj.get("mb_nick");
				}
				if(obj.get("eval_content") instanceof Long){
					info.eval_content = (String) String.valueOf(obj.get("eval_content"));
				}else{
					info.eval_content = (String) obj.get("eval_content");
				}
				info.eval_state = (int) (long) obj.get("eval_hide");

				evalData.add(info);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return evalData;
	}
	public static ArrayList<evalInfo> getAllData(String filename){
		ArrayList<evalInfo> evalData = new ArrayList();
		JSONParser parser = new JSONParser();
		try {
			JSONArray jsonArr = (JSONArray) parser.parse(new FileReader(filename));

			for (Object o : jsonArr) {
				JSONObject obj = (JSONObject) o;
				evalInfo info = new evalInfo();
				//data들이 받아들일때 long형식으로 받아져서 임의로 형변환
				info.no = (int) (long) obj.get("no");
				info.lec_no = (int) (long) obj.get("lec_no");
				info.mb_no = (int) (long) obj.get("mb_no");
				if(obj.get("mb_nick") instanceof Long){
					info.mb_nick = (String) String.valueOf(obj.get("mb_nick"));
				}else{
					info.mb_nick = (String) obj.get("mb_nick");
				}
				if(obj.get("eval_content") instanceof Long){
					info.eval_content = (String) String.valueOf(obj.get("eval_content"));
				}else{
					info.eval_content = (String) obj.get("eval_content");
				}
				info.eval_state = (int) (long) obj.get("eval_hide");

				evalData.add(info);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return evalData;
	}
	
}
