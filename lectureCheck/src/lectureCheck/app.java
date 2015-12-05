package lectureCheck;
import java.util.ArrayList;

public class app {
	public static void main(String[] args){
		//json정보를 가져와서 arrayList에 저장한다.
		getData data = new getData();
		preprocessing pre = new preprocessing();
		ArrayList<evalInfo> evalData=data.getPositiveData("./data/klue_lecture_tmp_min.json");
		
		
		
		
		//강의평가에 관련된 정보들을 형태소 분석한다.
		pre.morphemeAnalyzePN(evalData);  //analyze P or N만   
		
	    //writeData
		for(int i=0;i<evalData.get(0).analyzed_content.size();i++){
			System.out.println(evalData.get(0).analyzed_content.get(i));
		} 
		System.out.println(evalData.get(0).usefullPercent);
	}
}
