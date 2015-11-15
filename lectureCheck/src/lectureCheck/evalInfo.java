package lectureCheck;

import java.util.*;

class evalInfo {
	public int no;
	public int lec_no; // 강의코드 
	public int mb_no; // 글쓴이 코드 
	public String mb_nick; //닉네임 
	public String eval_content; // 강의평 
	public int eval_state; // 0,1,2,3
	public ArrayList<String> analyzed_content = new ArrayList<String>();
	
	/*
	public void preprocessing(){
		String data = this.eval_content;
		ManualWorkflowSetUp a = new ManualWorkflowSetUp();
		a.main(this.eval_content);
		
	}
	*/
}
