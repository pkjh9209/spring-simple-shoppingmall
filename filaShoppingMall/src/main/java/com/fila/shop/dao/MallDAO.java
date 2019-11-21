package com.fila.shop.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.fila.shop.dto.PdtViewDTO;

@Repository
public class MallDAO {
	@Autowired SqlSession ss;
	
	public List<PdtViewDTO> list(int cateCodeRef) throws Exception{
		return ss.selectList("mall.mallList",cateCodeRef);
	}
}