package com.fila.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fila.shop.dao.AdminDAO;
import com.fila.shop.dto.CategoryDTO;
import com.fila.shop.dto.OrderDTO;
import com.fila.shop.dto.OrderListDTO;
import com.fila.shop.dto.PdtCmtListDTO;
import com.fila.shop.dto.PdtViewDTO;
import com.fila.shop.dto.ProductDTO;

@Service
public class AdminService{
	
	@Autowired
	private AdminDAO dao;
	// ī�װ�	
	public List<CategoryDTO> category() throws Exception{
		return dao.category();
	}
	// ��ǰ���
	public void pdtRegister(ProductDTO pd) throws Exception{
		dao.pdtRegister(pd);
	}
	// ��ǰ���
	public List<ProductDTO> productList() throws Exception{
		return dao.productList();
	}
	// ��ǰ��ȸ
	public ProductDTO pdtView(int pdtNum) throws Exception{
		return dao.pdtView(pdtNum);
	}
	// ��ǰ����
	public void pdtUpdate(ProductDTO pd) throws Exception{
		dao.pdtUpdate(pd);
	}
	// ��ǰ����
	public void pdtDelete(int pdtNum) throws Exception{
		dao.pdtDelete(pdtNum);
	}
	// ��ǰ��ȸ �� ī�װ�
	public PdtViewDTO pdtJoinView(int pdtNum) throws Exception{
		return dao.pdtJoinView(pdtNum);
	}
	//	�ֹ���� ��ü����
	public List<OrderDTO> orderList() throws Exception{
		return dao.orderList();
	}
	// Ư�� �ֹ� ���
	public List<OrderListDTO> orderListView(OrderDTO od) throws Exception{
		return dao.orderListView(od);
	}
	//  ��ۻ���
	public void deliveryStatus(OrderDTO od) throws Exception{
		dao.deliveryStatus(od);
	}
	// ��ǰ ���� ����
	public void changeStock(ProductDTO pd) throws Exception{
		dao.changeStock(pd);
	}
	//	��� ��ǰ�� ��ȸ
	public List<PdtCmtListDTO> cmtAll() throws Exception{
		return dao.cmtAll();
	}
	//  ��ǰ�� ����
	public void adDeleteCmt(int cmtPdtNum) throws Exception{
		dao.adDeleteCmt(cmtPdtNum);
	}
}
