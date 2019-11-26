package com.fila.shop.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fila.shop.dto.CategoryDTO;
import com.fila.shop.dto.OrderDTO;
import com.fila.shop.dto.OrderListDTO;
import com.fila.shop.dto.PdtCmtListDTO;
import com.fila.shop.dto.PdtViewDTO;
import com.fila.shop.dto.ProductDTO;
@Repository
public class AdminDAO {
	
	@Autowired SqlSession ss;
	//	ī�װ�
	public List<CategoryDTO> category() throws Exception{
		return ss.selectList("admin.category");
	}
	//  ��ǰ���	
	public void pdtRegister(ProductDTO pd) throws Exception{
		ss.insert("admin.pdtRegister",pd);
	}
	//  ��ǰ���	
	public List<ProductDTO> productList() throws Exception{
		return ss.selectList("admin.pdtList");
	}
	//  ��ǰ��ȸ	
	public ProductDTO pdtView(int pdtNum) throws Exception{
		return ss.selectOne("admin.pdtView",pdtNum);
	}
	//	��ǰ����
	public void pdtUpdate(ProductDTO pd) throws Exception{
		ss.update("admin.pdtUpdate",pd);
	}
	//  ��ǰ����
	public void pdtDelete(int pdtNum) throws Exception{
		ss.delete("admin.pdtDelete",pdtNum);
	}
	//  ��ǰ��ȸ �� ī�װ�
	public PdtViewDTO pdtJoinView(int pdtNum) throws Exception{
		return ss.selectOne("admin.pdtJoinView",pdtNum);
	}
	//	�ֹ���� ��ü����
	public List<OrderDTO> orderList() throws Exception{
		return ss.selectList("admin.orderList");
	}
	//  Ư�� �ֹ� ���
	public List<OrderListDTO> orderListView(OrderDTO od) throws Exception{
		return ss.selectList("admin.orderListView", od);
	}	
	//  ��ۻ���
	public void deliveryStatus(OrderDTO od) throws Exception{
		ss.update("admin.deliveryStatus",od);
	}
	//  ��ǰ ���� ����
	public void changeStock(ProductDTO pd) throws Exception{
		ss.update("admin.changeStock",pd);
	}
	//	��� ��ǰ�� ��ȸ
	public List<PdtCmtListDTO> cmtAll() throws Exception{
		return ss.selectList("admin.cmtAll");
	}
	//  ��ǰ�� ����
	public void adDeleteCmt(int cmtPdtNum) throws Exception{
		ss.delete("admin.adDeleteCmt",cmtPdtNum);
	}
	
	
}
