package com.hk.shop.commands;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartOrderCommand {

	private Long cartItemId;

    private List<CartOrderCommand> cartOrderDtoList;
}
