package lectureCheck;

import java.util.ArrayList;

public class whitelist {
	
	public String word;
	public int count=0;
	
	public static void makelist(){

		//json정보를 가져와서 arrayList에 저장한다.
		getData data = new getData();
		preprocessing pre = new preprocessing();
		ArrayList<evalInfo> evalData=data.getPositiveData("./data/klue_lecture_tmp_min.json");
		//강의평가에 관련된 정보들을 형태소 분석한다.
		pre.morphemeAnalyze(evalData);
		
		ArrayList<whitelist> whitelist = new ArrayList();
		
		whitelist list = new whitelist();
		
		for(int i=0;i<evalData.size();i++){
			for(int j=0;j<evalData.get(i).analyzed_content.size();j++){
				list.word = (String) evalData.get(i).analyzed_content.get(j);
				list.count++;
				whitelist.add(list);
			}
		}
	
	}
}
