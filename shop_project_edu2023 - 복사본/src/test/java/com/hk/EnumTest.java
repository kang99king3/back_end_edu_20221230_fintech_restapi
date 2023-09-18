package com.hk;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hk.shop.constant.ItemSellStatus;
import com.hk.shop.constant.PageConfig;
import com.hk.shop.constant.Role;

class EnumTest {

	@Test
	void test() {
		PageConfig pc1=PageConfig.ROWRANGEMAIN;
		
		System.out.println(PageConfig.ROWRANGEMAIN.label());
		System.out.println(Role.ADMIN.name().getClass().getName());
	}

}
