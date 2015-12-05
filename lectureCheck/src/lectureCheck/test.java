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
		//데이터 가져오기
		getData data = new getData();
		ArrayList<evalInfo> evalData=data.getPositiveData("./data/klue_lecture_tmp_min.json");
		//형태소분석
		preprocessing pre = new preprocessing();
		pre.morphemeAnalyzePN(evalData); 
		//중복검사
		
		/*
		duplicatedWord dupword = new duplicatedWord();
		dupword.duplicatedWord(evalData);
		*/
		
		
		
		System.out.println("//////////////////전체//////////////////");

		for(int i=0;i<evalData.get(0).analyzed_content.size();i++){
			System.out.println(evalData.get(0).analyzed_content.get(i));
		} 
		
		System.out.println("//////////////////usefull//////////////////");

		for(int i=0;i<evalData.get(0).analyzed_content_usefull.size();i++){
			System.out.println(evalData.get(0).analyzed_content_usefull.get(i));
		} 
		
		System.out.println("//////////////////useless//////////////////");
		
		for(int i=0;i<evalData.get(0).analyzed_content_useless.size();i++){
			System.out.println(evalData.get(0).analyzed_content_useless.get(i));
		} 
		
		//asasas
		System.out.println("usefullPercent : "+evalData.get(0).usefullPercent);
		System.out.println("duplicatedWords : "+evalData.get(0).duplicated_words_count);
		for(int i=0;i<evalData.get(0).duplicated_words_list.size();i++){
			System.out.println(evalData.get(0).duplicated_words_list.get(i));
		} 
	}
}
	

