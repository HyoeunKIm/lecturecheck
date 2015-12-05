package lectureCheck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;


public class duplicatedWord {
	public void duplicatedWord(ArrayList<evalInfo> inputData){
		ArrayList<String> analyzed_content_tmp = new ArrayList<String>();
		for(int i=0;i<inputData.size();i++){
			for(int j=0;j<inputData.get(i).analyzed_content_usefull.size();j++){
				analyzed_content_tmp.add(inputData.get(i).analyzed_content_usefull.get(j));
			}			
			Collections.sort(analyzed_content_tmp, String.CASE_INSENSITIVE_ORDER);
					
			for(int j=0;j<analyzed_content_tmp.size()-1;j++){
				if(analyzed_content_tmp.get(j).equals(analyzed_content_tmp.get(j+1))){
					inputData.get(i).duplicated_words_count++;
					inputData.get(i).duplicated_words_list.add(analyzed_content_tmp.get(j));
				}
			}
			analyzed_content_tmp.clear();
		}
	}
}
