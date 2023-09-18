package com.hk.shop.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.shop.commands.CartItemCommand;
import com.hk.shop.dtos.CartItemDto;
import com.hk.shop.service.CartService;

import jakarta.validation.Valid;

@Controller
public class CartController {

	@Autowired
	private CartService cartService;
	
	@PostMapping(value="/cart")
	public @ResponseBody ResponseEntity order(@RequestBody @Validated CartItemCommand cartItemCommand,
			                     BindingResult bindingResult, Principal principal) {
		if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            for (FieldError fieldError : fieldErrors) {
                sb.append(fieldError.getDefaultMessage());
            }
            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

		String email = principal.getName();
		Long cartItemId;
		
		try {
			cartItemId = cartService.addCart(cartItemCommand, email);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
			
		}
		
		return new ResponseEntity<Long>((long) 1,HttpStatus.OK);
	}
}







