package com.fila.shop.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fila.shop.dto.CartDTO;
import com.fila.shop.dto.CartListDTO;
import com.fila.shop.dto.PdtCmtListDTO;
import com.fila.shop.dto.PdtCommentDTO;
import com.fila.shop.dto.PdtViewDTO;
import com.fila.shop.dto.ProductDTO;

@Repository
public class MallDAO {
	
	@Autowired SqlSession ss;
	
	//��ǰ����Ʈ ��з�
	public List<PdtViewDTO> list(int cateCodeRef) throws Exception{
		return ss.selectList("mall.mallList",cateCodeRef);
	}
	//��ǰ��ȸ
	public ProductDTO mallView(int pdtCode) throws Exception{
		return ss.selectOne("admin.pdtView",pdtCode);
	}
	//��ǰ���
	public void insertCmt(PdtCommentDTO cd) throws Exception{
		ss.insert("mall.insertCmt",cd);
	}
	//��۸���Ʈ
	public List<PdtCmtListDTO> pdtCmtList(int pdtNum) throws Exception{
		return ss.selectList("mall.PdtlistCmt",pdtNum);
	}
	//��ǰ ��� ����
	public void deleteCmt(PdtCommentDTO cd) throws Exception{
		ss.delete("mall.deleteCmt",cd);
	}
	//���̵� üũ
	public String idCheck(int cmtPdtNum) throws Exception{
		return ss.selectOne("mall.pdtCmtIdCheck",cmtPdtNum);
	}
	//īƮ���
	public void insertCart(CartDTO td) throws Exception{
		ss.insert("mall.insertCart",td);
	}
	// īƮ ����Ʈ
	public List<CartListDTO> cartList(String userId)throws Exception{
		return ss.selectList("mall.listCart",userId);
	}
	//īƮ����
	public void deleteCart(CartDTO td) throws Exception{
		ss.delete("mall.deleteCart",td);
	}
}
