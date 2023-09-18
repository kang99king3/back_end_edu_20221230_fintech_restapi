package com.hk.fintech.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hk.fintech.apidto.UserMeDto;
import com.hk.fintech.dtos.UserDto;
import com.hk.fintech.feign.OpenBankingFeign;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/banking")
public class BankingController {

	@Autowired
	private OpenBankingFeign openBankingFeign;
	
	@GetMapping("/main")
	public String main() {
		return "main";
	}
	
	
	//나의 정보 조회 기능을 feign을 이용하여 요청해봄 
	//결과값을 자바 객체로 반환해줌(UserMeDto)-->그래서 서버사이트 랜더링도 가능
//	@ResponseBody
	@GetMapping("/myinfo")
	public String myInfo(HttpServletRequest request){
//	public UserMeDto myInfo(HttpServletRequest request){
		System.out.println("나의 정보조회하기");
		
		HttpSession session=request.getSession();
		UserDto ldto=(UserDto)session.getAttribute("ldto");
		System.out.println(ldto);
		
		//feign 인터페이스가 요청해주고 결과값도 받아준다
		UserMeDto userMeDto= openBankingFeign
				.requestUserMe("Bearer "+ldto.getUseraccesstoken(), ldto.getUserseqno()+"");
		
		System.out.println(userMeDto.getUser_name());
		//결과값을 받아서 request 스코프에 담아서 전달할 수 있다.
		request.setAttribute("userMeDto", userMeDto);
		
//		return userMeDto;	
		return "main";
	}
	
	@ResponseBody
	@GetMapping("/balance")
	public JSONObject balance(String fintech_use_num,HttpServletRequest request) 
												throws IOException, ParseException{
		
		System.out.println("잔액조회하기");
		
		HttpURLConnection conn=null;
		JSONObject result=null;
		
		HttpSession session=request.getSession();
		UserDto ldto=(UserDto)session.getAttribute("ldto");
		System.out.println(ldto);
		URL url=new URL("https://testapi.openbanking.or.kr/v2.0/account/balance/fin_num?"
					  + "bank_tran_id=M202201886U"+createNum()
					  + "&fintech_use_num="+fintech_use_num
					  + "&tran_dtime="+getDateTime());	
		
		conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");//get으로 요청하라고 해서 설정
		conn.setRequestProperty("Content-Type", "application/json");
//		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("Authorization", "Bearer "+ldto.getUseraccesstoken());
		conn.setDoOutput(true);// 데이터를 가져오려면 true로 설정
		
		//---
		BufferedReader br = new BufferedReader(
				new InputStreamReader(conn.getInputStream(),"utf-8") );
	
		StringBuilder response=new StringBuilder();
		String responseLine = null;
		while((responseLine = br.readLine())!=null) {
			System.out.println(responseLine.trim());
			response.append(responseLine.trim());
		}
		
		// json 형태의 텍스트 데이터를 json 객체로 정말 변환시키는 작업
		result=(JSONObject) new JSONParser().parse(response.toString());
		//--
		System.out.println("result(잔액): "+result.get("balance_amt"));
		
		return result;	
		
	}
	
	//이용기관 부여번호 9자리 생성하는 메서드
	public String createNum() {
		String createNum="";// "468345554"
		for (int i = 0; i < 9; i++) {
			createNum+=((int)(Math.random()*10))+"";
		}
		System.out.println("이용기관부여번호9자리생성:"+createNum);
		return createNum;
	}
	
	//현재시간 구하는 메서드
	public String getDateTime() {
		LocalDateTime now = LocalDateTime.now();
		
		String formatNow = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		
		return formatNow;
	}
}







