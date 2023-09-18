package com.hk.board.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

import lombok.Data;

@Data
public class HkDto {

	private int seq;
	private String id;
	private String title;
	private String content;
	@JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yyyy-MM-dd")
	private Date regdate;//Date타입은 json변환할때 포맷을 재정의해야 한다.
}





