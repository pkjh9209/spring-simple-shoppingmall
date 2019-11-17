package com.fila.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fila.shop.dto.MemberDTO;
import com.fila.shop.service.MemberService;

@Controller
@RequestMapping(value="/member")
public class MemberController {
	@Autowired
	SqlSession ss;
	
	@Autowired
	MemberService service;
	
//	ȸ������ �������̵�
	@RequestMapping(value = "/insertMember")
	public String insertMember() throws Exception {
		
		return "member/memberInsert";
	}
//	ȸ������
	@RequestMapping(value = "/insertMemberProc", method = RequestMethod.POST)
	public String insertMemberProc(MemberDTO md) throws Exception {
		System.out.println(md);
		service.insertMember(md);
		return "redirect:/";
	}
//  ���̵� üũ
	@ResponseBody
	@RequestMapping(value = "/idCheck",method = RequestMethod.POST)
	public int idCheck(HttpServletRequest req) throws Exception {
		
		 String userId = req.getParameter("userId");
		 MemberDTO idCheck =  service.idCheck(userId);

		 int result = 0;

		 if(idCheck != null) {
			  result = 1;
			 } 

			 return result;
	}
//	�α��� ������
	@RequestMapping(value = "/loginMember")
	public String loginMember() throws Exception {
		
		return "member/memberLogin";
	}
//	�α���
	@RequestMapping(value = "/loginMemberProc", method = RequestMethod.POST)
	public String loginMemberProc(MemberDTO md,HttpServletRequest req, RedirectAttributes rttr) throws Exception {
		
		HttpSession session = req.getSession();
		MemberDTO login = service.memberLogin(md);
		System.out.println(md);
		if(login == null) {
			session.setAttribute("user", null);
			rttr.addFlashAttribute("msg",false);
			System.out.println("�ηο��°�");
		}else{
			session.setAttribute("user", login);
			System.out.println("�����ϴ°�");
		}
		
		return "redirect:/";
	}	
//	�α׾ƿ�
	@RequestMapping(value = "/logout")
	public String logoutMember(HttpSession session) throws Exception {
		session.invalidate();
		return "redirect:/";
	}
}
