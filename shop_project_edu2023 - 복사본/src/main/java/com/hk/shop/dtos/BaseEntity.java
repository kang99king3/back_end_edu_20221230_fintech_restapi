package com.hk.shop.dtos;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BaseEntity {

	private LocalDateTime regTime;
	private LocalDateTime updateTime;
}
