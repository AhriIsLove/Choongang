package com.oracle.oBootBoardWarGradle.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.oBootBoardWarGradle.command.BExecuteCommand;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BController {
	private static final Logger LOGGER = LoggerFactory.getLogger(BController.class);
	
	private final BExecuteCommand bExecuteCommand;
	@Autowired
	public BController(BExecuteCommand bExecuteCommand) {
		this.bExecuteCommand = bExecuteCommand;
	}
	
	@RequestMapping("list")
	public String list(Model model) {
		LOGGER.info("list start...");
		
		bExecuteCommand.bListCmd(model);
		
		model.addAttribute("count", 50);
		
		//application.yml의 설정에 따라 '/WEB-INF/views/list.jsp'로 이동
		return "list";
	}
	
	@RequestMapping("content_view")
	public String content_view(HttpServletRequest request, Model model) {
		System.out.println("content_view start...");
		
		model.addAttribute("request", request);
		bExecuteCommand.bContentCmd(model);
		
		return "contet_view";
	}
	
	@PostMapping("modify")
	public String modify(HttpServletRequest request, Model model) {
		LOGGER.info("modify start...");
		
		model.addAttribute("request", request);
		bExecuteCommand.bModifyCmd(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("write_view")
	public String write_view(Model model) {
		LOGGER.info("write_view start...");
		
		return "write_view";
	}

	@RequestMapping("delete")
	public String delete(HttpServletRequest request, Model model) {
		LOGGER.info("delete start...");

		model.addAttribute("request", request);
		bExecuteCommand.bDeleteCmd(model);
		
		return "redirect:list";
	}
	
	@PostMapping("write")
	public String write(HttpServletRequest request, Model model) {
		LOGGER.info("write start...");

		model.addAttribute("request", request);
		bExecuteCommand.bWriteCmd(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("reply_view")
	public String reply_view(HttpServletRequest request, Model model) {
		LOGGER.info("reply_view start...");

		model.addAttribute("request", request);
		bExecuteCommand.bReplyViewCmd(model);
		
		return "reply_view";
	}
	@PostMapping("reply")
	public String reply(HttpServletRequest request, Model model) {
		LOGGER.info("reply start...");

		model.addAttribute("request", request);
		bExecuteCommand.bReplyCmd(model);
		
		return "redirect:list";
	}
}
