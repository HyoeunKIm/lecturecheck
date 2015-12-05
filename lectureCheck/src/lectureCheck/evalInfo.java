package lectureCheck;

import java.util.ArrayList;

class evalInfo {
	public int no;
	public int lec_no;
	public int mb_no;
	public String mb_nick;
	public String eval_content;
	public int eval_state;  //알고리즘 적용 전 eval state
	public int eval_state_modified;  //알고리즘 적용 후 eval state
	
	public double usefullPercent; //   쓸모없는 정보의 길이 / 전체eval_content의 길이
	
	public int duplicated_words_count=0;
	public ArrayList<String> duplicated_words_list = new ArrayList<String>();
	
	public ArrayList<String> analyzed_content = new ArrayList<String>();   //전체
	public ArrayList<String> analyzed_content_useless = new ArrayList<String>();  //U 만 추출
	public ArrayList<String> analyzed_content_usefull = new ArrayList<String>();  //P N만 추출
	
}
