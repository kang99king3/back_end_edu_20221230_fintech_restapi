package com.hk.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {

	@GetMapping("/")
	public String index() {
		System.out.println("index페이지이동");
		return "main";
	}
	
	@GetMapping("/board")
	public String board() {
		System.out.println("board[게시판] 페이지이동");
		return "boards/board";
	}
}
