package com.hk.shop.controllers;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.shop.commands.OrderCommand;
import com.hk.shop.dtos.MemberDto;
import com.hk.shop.dtos.OrderDto;
import com.hk.shop.service.OrderService;

import jakarta.validation.Valid;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping(value = "/order")
    public @ResponseBody ResponseEntity order(@RequestBody @Valid OrderCommand orderCommand
            , BindingResult bindingResult, Principal principal){

        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            for (FieldError fieldError : fieldErrors) {
                sb.append(fieldError.getDefaultMessage());
            }

            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        String email = principal.getName();
        Long orderId;

        try {
            orderId = orderService.order(orderCommand, email);
        } catch(Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    }
	
	@GetMapping(value = {"/orders", "/orders/{page}"})
    public String orderHist(
//    		@PathVariable("page") Optional<Integer> page, 
    		Principal principal, Model model){

//        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 4);
//        Page<OrderHistDto> ordersHistDtoList = orderService.getOrderList(principal.getName(), pageable);
			
//        model.addAttribute("orders", ordersHistDtoList);
//        model.addAttribute("page", pageable.getPageNumber());
//        model.addAttribute("maxPage", 5);
		
		List<OrderDto> orderDtoList= orderService.getOrderList(principal.getName());
		System.out.println(orderDtoList);
//		for (OrderDto orderDto : orderDtoList) {
//			System.out.println(orderDto);
//		}
		System.out.println();
		model.addAttribute("orders", orderDtoList);
        return "order/orderHist";
    }
}
