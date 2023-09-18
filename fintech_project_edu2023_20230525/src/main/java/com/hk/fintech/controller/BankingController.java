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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hk.fintech.dtos.UserDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/banking")
public class BankingController {

	@GetMapping("/main")
	public String main() {
		return "main";
	}
	
	@ResponseBody
	@GetMapping("/myinfo")
	public JSONObject myInfo(HttpServletRequest request) throws IOException, ParseException {
		System.out.println("나의 정보조회하기");
		
		HttpURLConnection conn=null;
		JSONObject result=null;
		
		HttpSession session=request.getSession();
		UserDto ldto=(UserDto)session.getAttribute("ldto");
		System.out.println(ldto);
		URL url=new URL("https://testapi.openbanking.or.kr/v2.0/user/me?"
					  + "user_seq_no="+ldto.getUserseqno());	
		
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
		System.out.println("result: "+result.get("res_list"));
		
		return result;			
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
	
	//거래내역 조회
	@GetMapping("/transactionList")
	@ResponseBody
	public JSONObject transactionList(
			@RequestParam("fintech_use_num")String fintech_use_num
										,HttpServletRequest request)
								throws IOException, ParseException {
		
		System.out.println("거래내역조회");
		
		HttpURLConnection conn=null;
		JSONObject result=null;
		
		HttpSession session=request.getSession();
		UserDto ldto=(UserDto)session.getAttribute("ldto");
		System.out.println(ldto);
		URL url=new URL("https://testapi.openbanking.or.kr/v2.0/account/transaction_list/fin_num?"
					  + "bank_tran_id=M202201886U"+createNum()
					  + "&fintech_use_num="+fintech_use_num
					  + "&inquiry_type=A"
					  + "&inquiry_base=D"
					  + "&from_date=20230101"
					  + "&to_date=20230525"
					  + "&sort_order=D"
					  + "&tran_dtime="+getDateTime());	
		
		conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");//get으로 요청하라고 해서 설정
		conn.setRequestProperty("Content-Type", "application/json");
//			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
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
//			System.out.println("result(잔액): "+result.get("balance_amt"));
		
		return result;	
	}
	
	//계좌등록(센터인증이용기관용)
	@ResponseBody
	@GetMapping("/addaccount")
	public String addAccount(String code) {
		System.out.println("code:"+code);
		System.out.println("계좌등록 성공!!");
		//팝업창을 오픈해서 인증 및 계좌등록을 실행
		String str="<script type='text/javascript'>"
				 + "    self.close();"
				 + "</script>";
		return str;
	}
	
	//계좌등록(센터인증이용기관용)
		@ResponseBody
		@GetMapping("/cards")
		public String addCard(String code) {
			System.out.println("code:"+code);
			System.out.println("카드등록 성공!!");
			//팝업창을 오픈해서 인증 및 계좌등록을 실행
			String str="<script type='text/javascript'>"
					 + "    self.close();"
					 + "</script>";
			return str;
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







