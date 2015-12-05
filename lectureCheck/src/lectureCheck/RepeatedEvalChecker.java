package lectureCheck;
import java.util.*;
import com.google.common.collect.*;

public class RepeatedEvalChecker {
	public static void analyze(List<evalInfo> evalInfos){
		final int DEFAULTSIZE = 1000;		
		Multimap<String, evalInfo> hashedEvals = HashMultimap.create(DEFAULTSIZE, 1);

		//put evalinfos into hashedEvals
		for(evalInfo e: evalInfos){
			hashedEvals.put(getKey(e), e);
		}
		
		//mark repeated
		for(String key : hashedEvals.keySet()){
			Collection<evalInfo> evalsForAKey = hashedEvals.get(key);
			if(evalsForAKey.size() >= 2){
				int minNo = 999999;
				for(evalInfo e: evalsForAKey){
					if(e.no < minNo){
						minNo = e.no;
					}
					e.eval_repetition = true;
					System.out.println("**" + e.eval_content+"**\n");
					System.out.println(e.no + "\t");
					System.out.println(e.mb_no + "\n");
				}
				for(evalInfo e: evalsForAKey){
					if(e.no == minNo){
						e.eval_repetition = false;
						System.out.println("this is original " + e.no + "\n");
						
					}
				}
				System.out.println("repeated evaluations!\n\n");
			}
		}
	}
	
	//Here, Key is first 20 character of evalInfo.eval_content
	//Key is used for checking repetition among documents
	private static String getKey(evalInfo e){
		int stringLength = e.eval_content.length();
		if(stringLength >= 20){
			return e.eval_content.substring(0, 20);
		}
		else{
			return e.eval_content.substring(0, stringLength); 
		}
	}
}
