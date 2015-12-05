package lectureCheck;

import java.util.ArrayList;

public class app {
	public static void main(String[] args){
		//json정보를 가져와서 arrayList에 저장한다.
	/*	getData data = new getData();
		preprocessing pre = new preprocessing();
		ArrayList<evalInfo> evalData=data.getPositiveData("./data/klue_lecture_tmp_min.json");
		
		
		
		
		//강의평가에 관련된 정보들을 형태소 분석한다.
		pre.morphemeAnalyzePN(evalData);  //analyze P or N만   
		
<<<<<<< HEAD
	    //writeData
		for(int i=0;i<evalData.get(0).analyzed_content.size();i++){
			System.out.println(evalData.get(0).analyzed_content.get(i));
		} 
		System.out.println(evalData.get(0).usefullPercent);
=======
		
		for(int i=0;i<evalData.get(0).analyzed_content.size();i++){
			System.out.println(evalData.get(0).analyzed_content.get(i));
		} */
		
	whitelist wl = new whitelist();	
		
	//wl.makelist();
	wl.get_list();
	
	ArrayList<String> input = new ArrayList();
	input.add("교수님/N");
	input.add("워낙/M");
	input.add("유명하시/N");
	input.add("좋/P");
	input.add("기말고사/N");
	input.add("부분이/N");
	input.add("어렵/P");
	
	
	wl.check_white(input);
	}
}
