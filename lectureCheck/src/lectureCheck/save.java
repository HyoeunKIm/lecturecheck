package lectureCheck;
import java.io.*;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import kr.ac.kaist.swrc.jhannanum.hannanum.Workflow;
import kr.ac.kaist.swrc.jhannanum.plugin.MajorPlugin.MorphAnalyzer.ChartMorphAnalyzer.ChartMorphAnalyzer;
import kr.ac.kaist.swrc.jhannanum.plugin.MajorPlugin.PosTagger.HmmPosTagger.HMMTagger;
import kr.ac.kaist.swrc.jhannanum.plugin.SupplementPlugin.MorphemeProcessor.SimpleMAResult09.SimpleMAResult09;
import kr.ac.kaist.swrc.jhannanum.plugin.SupplementPlugin.MorphemeProcessor.SimpleMAResult22.SimpleMAResult22;
import kr.ac.kaist.swrc.jhannanum.plugin.SupplementPlugin.MorphemeProcessor.UnknownMorphProcessor.UnknownProcessor;
import kr.ac.kaist.swrc.jhannanum.plugin.SupplementPlugin.PlainTextProcessor.InformalSentenceFilter.InformalSentenceFilter;
import kr.ac.kaist.swrc.jhannanum.plugin.SupplementPlugin.PlainTextProcessor.SentenceSegmentor.SentenceSegmentor;
import kr.ac.kaist.swrc.jhannanum.plugin.SupplementPlugin.PosProcessor.SimplePOSResult09.SimplePOSResult09;
import kr.ac.kaist.swrc.jhannanum.plugin.SupplementPlugin.PosProcessor.SimplePOSResult22.SimplePOSResult22;



public class save {
	public static void main(String[] args){
		//json정보를 가져와서 arrayList에 저장한다.
		ArrayList<evalInfo> evalData=getPositiveData("./data/klue_lecture_tmp_min.json");
        //강의평가에 관련된 정보들을 형태소 분석한다.
		morphemeAnalyze(evalData);

		for(int i=0;i<evalData.get(0).analyzed_content.size();i++){
			System.out.println(evalData.get(0).analyzed_content.get(i));
		} 
	}
	
	//형태소 분석한뒤 필요한 정보만 추출
	public static void morphemeAnalyze(ArrayList<evalInfo> inputData){
		String tmp = "";
		String[] tmp2;
		Workflow workflow = new Workflow();
		try {
			//어떤 형식으로 형태소를 분석할껀지에 대한 옵션
			workflow.appendPlainTextProcessor(new SentenceSegmentor(), null);
			workflow.appendPlainTextProcessor(new InformalSentenceFilter(), null);
			workflow.setMorphAnalyzer(new ChartMorphAnalyzer(), "./conf/plugin/MajorPlugin/MorphAnalyzer/ChartMorphAnalyzer.json");
			workflow.appendMorphemeProcessor(new UnknownProcessor(), null);
			workflow.appendMorphemeProcessor(new SimpleMAResult09(), null);
			workflow.activateWorkflow(true);
			
			for(int i=0;i<inputData.size();i++){
				workflow.analyze(inputData.get(i).eval_content);
				tmp=workflow.getResultOfDocument();
				//원래 문자열의 형태소분석 된 것중 1번째 것 까지를 regex를 통해 가져온다.
				Pattern pattern  =  Pattern.compile("[가-힣]+\n\\s+[ㄱ-ㅎㅏ-ㅣ가-힣]+.*[A-Z]\n",Pattern.MULTILINE);
				Matcher match = pattern.matcher(tmp);
				while(match.find()) {
					//원래문자열을 제외하고 형태소분석된 1개의 정보만 tmp에 입력
					tmp=match.group().substring(match.group().indexOf('\n')+1).trim();
					//형태소분석된 정보중에 맨처음 정보와(stem) 그 정보의 형태(맨마지막에 위치)를 저장한다.
					tmp2=tmp.split("/");
					tmp=tmp2[0]+'/'+tmp2[tmp2.length-1];
				    inputData.get(i).analyzed_content.add(tmp);
				}	
			}
			workflow.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		workflow.close();  	
	}

	
	public static ArrayList<evalInfo> getPositiveData(String filename){
		ArrayList<evalInfo> evalData = new ArrayList();
		JSONParser parser = new JSONParser();
		try {
			JSONArray jsonArr = (JSONArray) parser.parse(new FileReader(filename));

			for (Object o : jsonArr) {
				JSONObject obj = (JSONObject) o;
				evalInfo info = new evalInfo();
				if((int) (long) obj.get("eval_hide") == 1){
					
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
}
