package lectureCheck;

import java.util.*;
import java.util.Map.Entry;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Table;


public class whitelist {
	
	public String word;
	public int count=0;
	
	
	static HashMap<String, Integer> whitemap = new HashMap<String , Integer>();
	static HashMap<String, Integer> whitelist = new HashMap<String , Integer>();
	
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

		
		Writer writer = null;
		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		            new FileOutputStream("whitelist.txt"), "utf-8"));

				while (it.hasNext()) {
					Map.Entry<String, Integer> e = (Map.Entry<String, Integer>)it.next();
					if(e.getValue()>20){
						System.out.println("단어 : " + e.getKey() + ", 횟수 : " + e.getValue());
						writer.write(e.getKey() + " " + e.getValue()+"\n");
					}
				}
		  } catch (IOException ex) {
		    // report
		  } finally {
		     try {writer.close();} catch (Exception ex) {}
		  }
	}
	
	public static void get_list(){

	    try {
	      ////////////////////////////////////////////////////////////////
	      BufferedReader in = new BufferedReader(new FileReader("whitelist.txt"));
	      String s;
			
	      while ((s = in.readLine()) != null) {
	       String temp[];
	       temp = s.split(" ");
	        whitelist.put(temp[0],Integer.parseInt(temp[1]));
	      }
	      in.close();
	      ////////////////////////////////////////////////////////////////
	    } catch (IOException e) {
	        System.err.println(e); // 에러가 있다면 메시지 출력
	        System.exit(1);
	    }
	}
	
	public static int check_white(ArrayList input_list){
		
		int result = 0;
		
		for(int i=0; i<input_list.size();i++){
			if(whitelist.containsKey(input_list.get(i))){
				result++;
				System.out.println(input_list.get(i)+", "+whitelist.get(input_list.get(i)));
			}
		}

		System.out.println("Result "+result);
		return result;
	}
}
