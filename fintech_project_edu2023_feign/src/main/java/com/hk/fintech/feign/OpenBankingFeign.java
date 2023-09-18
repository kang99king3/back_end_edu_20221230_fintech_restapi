package com.hk.fintech.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.hk.fintech.apidto.AccountBalanceDto;
import com.hk.fintech.apidto.AccountListDto;
import com.hk.fintech.apidto.AccountTransactionListDto;
import com.hk.fintech.apidto.TokenResponseDto;
import com.hk.fintech.apidto.UserMeDto;

//restAPI 서버에 요청하고 결과값을 받아 줄 feign 인터페이스
@FeignClient(name="feign", url="https://testapi.openbanking.or.kr")
public interface OpenBankingFeign {
	
  //어노테이션에 설정된 경로로 파라미터 값들과 함께 요청	
  @GetMapping(path = "/v2.0/user/me")
  public UserMeDto requestUserMe(
          @RequestHeader("Authorization") String access_token,
		  @RequestParam("user_seq_no") String user_seq_no);
	
	
  @PostMapping(path = "/oauth/2.0/token", consumes = "application/x-www-form-urlencoded", produces = "application/json")
  public TokenResponseDto requestToken(
		  @RequestParam("code") String code, 
		  @RequestParam("client_id") String client_id, 
		  @RequestParam("client_secret") String client_secret, 
		  @RequestParam("redirect_uri") String redirect_uri, 
		  @RequestParam("grant_type") String grant_type);
  
  @GetMapping(path = "/v2.0/account/list")
  public AccountListDto requestAccountList(
          @RequestHeader("Authorization") String access_token,
		  @RequestParam("user_seq_no") String user_seq_no, 
		  @RequestParam("include_cancel_yn") String include_cancel_yn, 
		  @RequestParam("sort_order") String sort_order);
  
  @GetMapping(path = "/v2.0/account/balance/fin_num")
  public AccountBalanceDto requestAccountBalance(
      @RequestHeader("Authorization") String access_token,
		  @RequestParam("bank_tran_id") String bank_tran_id, 
		  @RequestParam("fintech_use_num") String fintech_use_num, 
		  @RequestParam("tran_dtime") String tran_dtime);

  @GetMapping(path = "/v2.0/account/transaction_list/fin_num")
  public AccountTransactionListDto requestAccountTransactionList(
		  @RequestParam("bank_tran_id") String bank_tran_id, 
		  @RequestParam("fintech_use_num") String fintech_use_num, 
		  @RequestParam("inquiry_type") String inquiry_type, 
		  @RequestParam("inquiry_base") String inquiry_base, 
		  @RequestParam("from_date") String from_date, 
		  @RequestParam("to_date") String to_date, 
		  @RequestParam("sort_order") String sort_order, 
		  @RequestParam("tran_dtime") String tran_dtime);
  
}
