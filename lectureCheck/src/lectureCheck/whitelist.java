package lectureCheck;

import java.util.*;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Table;


public class whitelist {
	
	public String word;
	public int count=0;
	
	
	static HashMap<String, Integer> whitemap = new HashMap<String , Integer>();
	
	public static void makelist(){

		//json정보를 가져와서 arrayList에 저장한다.
		getData data = new getData();
		preprocessing pre = new preprocessing();
		ArrayList<evalInfo> evalData=data.getPositiveData("./data/klue_lecture_tmp2.json");
		//강의평가에 관련된 정보들을 형태소 분석한다.
		pre.morphemeAnalyze(evalData);
		
		
		for(int i=0;i<evalData.size();i++){
			for(int j=0;j<evalData.get(i).analyzed_content.size();j++){
				if(whitemap.containsKey((String) evalData.get(i).analyzed_content.get(j))){
					int count = whitemap.get((String) evalData.get(i).analyzed_content.get(j));
					count++;
					whitemap.put((String) evalData.get(i).analyzed_content.get(j),count);
				}
				else{
					whitemap.put((String) evalData.get(i).analyzed_content.get(j),1);
				}
				
			}
		}
		Set<Entry<String, Integer>> set = whitemap.entrySet();
		Iterator<Entry<String, Integer>> it = set.iterator();
		
		while (it.hasNext()) {
			Map.Entry<String, Integer> e = (Map.Entry<String, Integer>)it.next();
			if(e.getValue()>30){
				System.out.println("단어 : " + e.getKey() + ", 횟수 : " + e.getValue());
			}
		}
		
	}
	
	
	public static int check_white(ArrayList input_list){
		
		int result = 0;
		
		for(int i=0; i<input_list.size();i++){
			if(whitemap.containsKey(input_list.get(i))){
				if(whitemap.get(input_list.get(i)) > 300){
					result = result+5;
					System.out.println("5: "+input_list.get(i)+", "+whitemap.get(input_list.get(i)));
				}
				else if(whitemap.get(input_list.get(i)) > 250){
					result = result+4;
					System.out.println("4: "+input_list.get(i)+", "+whitemap.get(input_list.get(i)));
				}
				else if(whitemap.get(input_list.get(i)) > 200){
					result = result+3;
					System.out.println("3: "+input_list.get(i)+", "+whitemap.get(input_list.get(i)));
				}
				else if(whitemap.get(input_list.get(i)) > 100){
					result = result+2;
					System.out.println("2: "+input_list.get(i)+", "+whitemap.get(input_list.get(i)));
				}
				else if(whitemap.get(input_list.get(i)) > 50){
					result = result+1;
					System.out.println("1: "+input_list.get(i)+", "+whitemap.get(input_list.get(i)));
				}
				else{
					System.out.println("0: "+input_list.get(i)+", "+whitemap.get(input_list.get(i)));
				}
			}
		}

		System.out.println("제 점수는요? "+result);
		return result;
	}
}
