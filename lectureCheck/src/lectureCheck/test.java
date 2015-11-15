package lectureCheck;
import java.io.*;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.regex.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.IOException;

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
public class test {
	public static void main(String[] args){
		
		Workflow workflow = new Workflow();
		try {
			/* Setting up the work flow */
			/* Phase1. Supplement Plug-in for analyzing the plain text */
			workflow.appendPlainTextProcessor(new SentenceSegmentor(), null);
			workflow.appendPlainTextProcessor(new InformalSentenceFilter(), null);
			
			/* Phase2. Morphological Analyzer Plug-in and Supplement Plug-in for post processing */
			workflow.setMorphAnalyzer(new ChartMorphAnalyzer(), "./conf/plugin/MajorPlugin/MorphAnalyzer/ChartMorphAnalyzer.json");
			workflow.appendMorphemeProcessor(new UnknownProcessor(), null);
			workflow.appendMorphemeProcessor(new SimpleMAResult09(), null);

			workflow.activateWorkflow(true);
			
			/* Analysis using the work flow */
			String document = "한나눔 형태소 분석기는 KLDP에서 제공하는 공개 소프트웨어 프로젝트 사이트에 등록되어 있다.";
			
			workflow.analyze(document);
			//System.out.println(workflow.getResultOfDocument());
			String a = workflow.getResultOfDocument();
			//String a = "한나눔\n	한나눔/N\n형태소\n		형태소/N+는/N\n제공하는\n 	제공/N+하/X+는/E\n	 제공/N+하/X+어/E+는/J\n	제공/N+하/X+어는/E\n	제/X+공하/N+는/J";
			//a=a.replace("\n","|");
			Pattern pattern  =  Pattern.compile("[가-힣]+\n\\s+[ㄱ-ㅎㅏ-ㅣ가-힣]+.*[A-Z]\n",Pattern.MULTILINE);
			ArrayList<String> x = new ArrayList<String>();
			//String a = workflow.getResultOfSentence();
				
			Matcher match = pattern.matcher(a);
			/*
			while(match.find()) {
				System.out.println(match.group());
			}
			*/
			while(match.find()) {
			    x.add(match.group());
			}
			for(int i=0;i<x.size();i++){
				String tmp = x.get(i);
				tmp=tmp.substring(tmp.indexOf('\n')+1).trim();
				String[] tmp2=tmp.split("/");
				tmp=tmp2[0]+'/'+tmp2[tmp2.length-1];
				System.out.println(i+"번째 : "+tmp);
			}
			
			/* Close the work flow */
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
		
		/* Shutdown the workflow */
		workflow.close();  	
	}
}
	
	/*
	public static void getData(String filename){
		JSONParser parser = new JSONParser();
		try {
			JSONArray jsonArr = (JSONArray) parser.parse(new FileReader(filename));

			for (Object o : jsonArr) {
				JSONObject obj = (JSONObject) o;
				int firstname = (int) (long) obj.get("no");
				System.out.println(firstname);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/

