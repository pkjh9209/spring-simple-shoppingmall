package com.fila.shop.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fila.shop.dao.MallDAO;
import com.fila.shop.dto.CartDTO;
import com.fila.shop.dto.PdtCmtListDTO;
import com.fila.shop.dto.PdtCommentDTO;
import com.fila.shop.dto.PdtViewDTO;
import com.fila.shop.dto.ProductDTO;

@Service
public class MallService {
	
	@Autowired
	private MallDAO dao;
	
	//��ǰ����Ʈ ��з�
	public List<PdtViewDTO> list(int cateCodeRef) throws Exception{
		return dao.list(cateCodeRef);
	}
	//��ǰ��ȸ
	public ProductDTO mallView(int pdtCode) throws Exception{
		return dao.mallView(pdtCode);
	}
	//��ǰ���
	public void insertCmt(PdtCommentDTO cd) throws Exception{
		dao.insertCmt(cd);
	}
	//��۸���Ʈ
	public List<PdtCmtListDTO> pdtCmtList(int pdtNum) throws Exception{
		return dao.pdtCmtList(pdtNum);
	}
	//��ǰ ��� ����
	public void deleteCmt(PdtCommentDTO cd) throws Exception{
		dao.deleteCmt(cd);
	}
	//���̵� üũ
	public String idCheck(int cmtPdtNum) throws Exception{
		return dao.idCheck(cmtPdtNum);
	}
	//īƮ���
	public void insertCart(CartDTO td) throws Exception{
		dao.insertCart(td);
	}
}
