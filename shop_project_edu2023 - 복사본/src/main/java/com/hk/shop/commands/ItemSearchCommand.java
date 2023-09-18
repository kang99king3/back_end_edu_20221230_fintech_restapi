package com.hk.shop.commands;

import com.hk.shop.constant.ItemSellStatus;
import com.hk.shop.constant.PageConfig;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemSearchCommand {

	private String searchDateType;//기간(all, 1d,1w,1m,6m)

    private ItemSellStatus searchSellStatus;//판매상태(판매,품절)

    private String searchBy;//상품명,등록자

    //초기값을 ""으로 설정해준 이유는
    //최초에 메인화면 요청때는 값이 null이지만 페이지번호를 선택하여 요청할때는 /?searchQuery=검색어&pnum=2이다
    //근데 검색어 없이 페이지를 요청하게 되면 /?searchQuery=&pnum=2 이렇게 요청이 된다 
    //그럼 searchQuery에 값이 없으면 "null"을 저장한다. 
    //그럼 문자열"null"이 저장되어 실제 값이 저장된 상태가 된다. 그래서 초기값을 ""로 지정한다. 
    private String searchQuery="";//검색어
    
    private int pnum;//페이지 번호
    
    private int rowRange;//한번에 보여줄 상품목록의 개수
}
