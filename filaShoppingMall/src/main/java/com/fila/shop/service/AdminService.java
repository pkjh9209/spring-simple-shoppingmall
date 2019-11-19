package com.fila.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fila.shop.dao.AdminDAO;
import com.fila.shop.dto.CategoryDTO;
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
}
