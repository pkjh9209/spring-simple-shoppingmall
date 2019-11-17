package com.fila.shop.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fila.shop.dto.MemberDTO;

@Repository
public class MemberDAO {
	
	@Autowired SqlSession ss;
//	ȸ������
	public void insertMemeber(MemberDTO md) throws Exception{
		ss.insert("member.memberInsert",md);
	}
//  �α���
	public MemberDTO memberLogin(MemberDTO md) throws Exception{
		return ss.selectOne("member.memberLogin",md);
	}
//  ���̵� üũ dao
	public MemberDTO idCheck(String userId) throws Exception{
		return ss.selectOne("member.idCheck",userId);
	}
}
