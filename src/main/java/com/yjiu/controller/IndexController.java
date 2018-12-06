package com.yjiu.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yjiu.service.UserInfoService;

@Controller
public class IndexController {
	@Autowired
	private UserInfoService userRepositoy;
}
