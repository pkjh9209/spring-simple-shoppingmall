package com.fila.shop.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fila.shop.dao.MemberDAO;
import com.fila.shop.dto.MemberDTO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO dao;
	
//	ȸ������
	public void insertMember(MemberDTO md) throws Exception{
		dao.insertMemeber(md);
	}
//  �α���
	public MemberDTO memberLogin(MemberDTO md) throws Exception{
		return dao.memberLogin(md);
	}
//	�α׾ƿ�
	public void memberLogout(HttpSession session) throws Exception{
		session.invalidate();
	}
//  ���̵�üũ
	public MemberDTO idCheck(String userId) throws Exception{
		return dao.idCheck(userId);
	}
}
