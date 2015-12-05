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



public class preprocessing {
	//형태소 분석한뒤 필요한 정보만 추출
	public void morphemeAnalyze(ArrayList<evalInfo> inputData){
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
				int uselessCount =0;
				int usefullCount =0;
				workflow.analyze(inputData.get(i).eval_content);
				tmp=workflow.getResultOfDocument();
				//원래 문자열의 형태소분석 된 것중 1번째 줄을 regex를 통해 가져온다.
				Pattern pattern  =  Pattern.compile("[가-힣]+\n\\s+[ㄱ-ㅎㅏ-ㅣ가-힣]+.*[A-Z]\n",Pattern.MULTILINE);
				Matcher match = pattern.matcher(tmp);
				while(match.find()) {
					//원래문자열을 제외하고 형태소분석된 1개의 정보만 tmp에 입력
					tmp=match.group().substring(match.group().indexOf('\n')+1).trim();
					//형태소분석된 정보중에 맨처음 정보와(stem) 그 정보의 형태(맨마지막에 위치)를 저장한다.
					tmp2=tmp.split("/");
					tmp=tmp2[0]+'/'+tmp2[1].substring(0, 1);
					if(tmp2[1].charAt(0)!='U'){
						usefullCount++;
					}else{
						uselessCount++;
					}						
					inputData.get(i).analyzed_content.add(tmp);
				}
				inputData.get(i).usefullPercent=(double)usefullCount/usefullCount+uselessCount;
			}
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
	public void morphemeAnalyzeExceptU(ArrayList<evalInfo> inputData){
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
				int uselessCount =0;
				int usefullCount =0;
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
					tmp=tmp2[0]+'/'+tmp2[1].substring(0, 1);
			
					if(tmp2[1].charAt(0)!='U'){
						inputData.get(i).analyzed_content.add(tmp);
						usefullCount++;
					}else{
						uselessCount++;
					}
				}
				inputData.get(i).usefullPercent=usefullCount/usefullCount+uselessCount;
			}
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
	public void morphemeAnalyzePN(ArrayList<evalInfo> inputData){
		String tmp = "";
		String[] tmp2;
		Workflow workflow = new Workflow();
		try {
			//어떤 형식으로 형태소를 분석할껀지에 대한 옵션
			workflow.appendPlainTextProcessor(new SentenceSegmentor(), null);
			//workflow.appendPlainTextProcessor(new InformalSentenceFilter(), null);
			workflow.setMorphAnalyzer(new ChartMorphAnalyzer(), "./conf/plugin/MajorPlugin/MorphAnalyzer/ChartMorphAnalyzer.json");
			//workflow.appendMorphemeProcessor(new UnknownProcessor(), null);
			workflow.appendMorphemeProcessor(new SimpleMAResult09(), null);
			workflow.activateWorkflow(true);
			
			for(int i=0;i<inputData.size();i++){
				int uselessCount =0;
				int usefullCount =0;
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
					tmp=tmp2[0]+'/'+tmp2[1].substring(0, 1);
										
					if(tmp2[1].charAt(0)=='U'){
						uselessCount++;
						inputData.get(i).analyzed_content.add(tmp);
						inputData.get(i).analyzed_content_useless.add(tmp);
					}else if(tmp2[1].charAt(0)=='P' || tmp2[1].charAt(0)=='N'){
						usefullCount++;
						inputData.get(i).analyzed_content.add(tmp);
						inputData.get(i).analyzed_content_usefull.add(tmp);
					}
					else{
						usefullCount++;
						inputData.get(i).analyzed_content.add(tmp);
					}
				}
				inputData.get(i).usefullPercent=(double)usefullCount/(usefullCount+uselessCount);
			}
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
}
