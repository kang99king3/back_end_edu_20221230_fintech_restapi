package com.hk.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hk.board.dtos.HkDto;
import com.hk.board.service.IHkService;

@RestController
@RequestMapping("/api/board")
//@CrossOrigin(origins = "http://localhost:8086")
public class HkController {

	@Autowired
	private IHkService hkService;
	//페이징처리X
//	@GetMapping("/boardlist")
////	@CrossOrigin(origins = "http://localhost:8086")
//	public Map<String, List<HkDto>> boardList(){
//		
//		System.out.println("글목록 페이지 요청");
//		Map<String, List<HkDto>> map = new HashMap<>();
//		map.put("list", hkService.getAllList());
//		System.out.println(hkService.getAllList().size());
//		
//		
//		return map;
//	}
	
	@GetMapping("/boardlist/{row}/{page}")
//	@CrossOrigin(origins = "http://localhost:8086")
	public Map<String, Object> boardList(@PathVariable String row,
											  @PathVariable String page){
		Map<String , String> paramMap=new HashMap<>();
		paramMap.put("row", row);
		paramMap.put("page", page);
		
		System.out.println("글목록 페이지 요청");
//		Map<String, List<HkDto>> map = new HashMap<>();
//		map.put("list", hkService.getAllList(paramMap));
//		System.out.println(hkService.getAllList(paramMap).size());
		Map<String, Object> map = new HashMap<>();
		map.put("list", hkService.getAllList(paramMap));
		map.put("count", hkService.getAllListCount());
		return map;		
	}
	
	@PostMapping("/insert")
//	@CrossOrigin(origins = "http://localhost:8086")
	public Map<String, Integer> insert(HkDto dto){
		
		System.out.println("글추가하기(insert)");
		Map<String, Integer> map = new HashMap<>();
		map.put("count", hkService.insertBoard(dto));
	
		return map;
	}
	
	@GetMapping("/detail/{seq}")
//	@CrossOrigin(origins = "http://localhost:8086")
	public Map<String, HkDto> detail(@PathVariable int seq){
		
		System.out.println("글상세보기(detail)");
		Map<String, HkDto> map = new HashMap<>();
		map.put("dto", hkService.getBoard(seq));
	
		return map;
	}
	
	@GetMapping("/updateform/{seq}")
//	@CrossOrigin(origins = "http://localhost:8086")
	public Map<String, HkDto> updateForm(@PathVariable int seq){
		
		System.out.println("수정폼이동(updateForm)");
		Map<String, HkDto> map = new HashMap<>();
		map.put("dto", hkService.getBoard(seq));
	
		return map;
	}
	
	@PutMapping("/update")
//	@CrossOrigin(origins = "http://localhost:8086")
	public Map<String, Integer> updateBoard(HkDto dto){
		
		System.out.println("수정하기(updateBoard)");
		Map<String, Integer> map = new HashMap<>();
		map.put("count", hkService.updateBoard(dto));
	
		return map;
	}
//	@ResponseBody
	@DeleteMapping("/delete/{seq}")
//	@CrossOrigin(origins = "http://localhost:8086")
	public Map<String, Integer> delBoard(@PathVariable int seq){
		
		System.out.println("글삭제하기(delBoard)");
		Map<String, Integer> map = new HashMap<>();
		map.put("count", hkService.deleteBoard(seq));
	
		return map;
	}
	
	//여러글 삭제하기
	@DeleteMapping("/muldel")
//	@CrossOrigin(origins = "http://localhost:8086")
	public Map<String, Integer> muldel(@RequestParam String[] chk){
		Map<String, String[]> paramMap = new HashMap<>();
		paramMap.put("seqs", chk);
		System.out.println("여러글삭제하기(muldel)");
		Map<String, Integer> map = new HashMap<>();
		map.put("count", hkService.muldel(paramMap));
	
		return map;
	}
}









