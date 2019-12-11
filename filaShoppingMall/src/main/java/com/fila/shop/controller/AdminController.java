package com.fila.shop.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fila.shop.dto.CartDTO;
import com.fila.shop.dto.CategoryDTO;
import com.fila.shop.dto.MemberDTO;
import com.fila.shop.dto.OrderDTO;
import com.fila.shop.dto.OrderListDTO;
import com.fila.shop.dto.PdtCmtListDTO;
import com.fila.shop.dto.PdtCommentDTO;
import com.fila.shop.dto.PdtViewDTO;
import com.fila.shop.dto.ProductDTO;
import com.fila.shop.service.AdminService;
import com.fila.shop.utils.UploadFileUtils;
import com.google.gson.JsonObject;

import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	@Autowired
	SqlSession ss;

	@Autowired
	AdminService adService;

	@Autowired
	private String uploadPath;

//	������ ������ ȭ��
	@RequestMapping(value = "/index")
	public String adminMain() throws Exception {

		return "admin/index";
	}

//	��ǰ��� ������
	@RequestMapping(value = "/pdtRegister", method = RequestMethod.GET)
	public String pdtRegister(Model model) throws Exception {
		List<CategoryDTO> category = null;
		category = adService.category();
		System.out.println(category);
		model.addAttribute("category", JSONArray.fromObject(category));
		return "admin/pdtRegister";
	}

//	��ǰ���
	@RequestMapping(value = "/pdtRegisterProc", method = RequestMethod.POST)
	public String pdtRegisterProc(ProductDTO pd, MultipartFile file) throws Exception {

		String imgUploadPath = uploadPath + File.separator + "imgUpload";
		String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
		String fileName = null;

		if (file != null) {
			fileName = UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath);
		} else {
			fileName = uploadPath + File.separator + "images" + File.separator + "none.png";
		}

		pd.setPdtImg(File.separator + "imgUpload" + ymdPath + File.separator + fileName);
		pd.setPdtThumbNail(File.separator + "imgUpload" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);

		adService.pdtRegister(pd);
		return "redirect:/admin/pdtList";
	}

//	��ǰ����Ʈ
	@RequestMapping(value = "/pdtList")
	public String pdtList(Model model) throws Exception {

		List<ProductDTO> list = adService.productList();
		model.addAttribute("list", list);
		return "admin/pdtList";
	}

// 	��ǰ ��ȸ
	@RequestMapping(value = "/pdtView", method = RequestMethod.GET)
	public String pdtView(@RequestParam("pdtNum") int pdtNum, Model model) throws Exception {

		PdtViewDTO viewPd = adService.pdtJoinView(pdtNum);
		model.addAttribute("viewPd", viewPd);
		return "admin/pdtView";
	}

//	��ǰ ���� ȭ��
	@RequestMapping(value = "/pdtUpdate", method = RequestMethod.GET)
	public String pdtUpdate(@RequestParam("pdtNum") int pdtNum, Model model) throws Exception {
		PdtViewDTO viewPd = adService.pdtJoinView(pdtNum);
		model.addAttribute("viewPd", viewPd);

		List<CategoryDTO> category = null;
		category = adService.category();
		model.addAttribute("pdtSubCate", viewPd.getCateCode());
		model.addAttribute("category", JSONArray.fromObject(category));
		return "admin/pdtUpdate";
	}

//	��ǰ����
	@RequestMapping(value = "/pdtUpdateProc", method = RequestMethod.POST)
	public String postGoodsModify(ProductDTO pd, MultipartFile file, HttpServletRequest req) throws Exception {
		// ���ο� ������ ��ϵǾ����� Ȯ��
		if (file.getOriginalFilename() != null && file.getOriginalFilename() != "") {
			// ���� ������ ����
			new File(uploadPath + req.getParameter("pdtImg")).delete();
			new File(uploadPath + req.getParameter("pdtThumbNail")).delete();

			// ���� ÷���� ������ ���
			String imgUploadPath = uploadPath + File.separator + "imgUpload";
			String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
			String fileName = UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(),
					ymdPath);

			pd.setPdtImg(File.separator + "imgUpload" + ymdPath + File.separator + fileName);
			pd.setPdtThumbNail(
					File.separator + "imgUpload" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);
		} else {
			// ���� �̹����� �״�� ���
			pd.setPdtImg(req.getParameter("pdtImg"));
			pd.setPdtThumbNail(req.getParameter("pdtThumbNail"));
		}

		adService.pdtUpdate(pd);
		return "redirect:/admin/pdtList";
	}

//	��ǰ ����
	@RequestMapping(value = "/pdtDelete", method = RequestMethod.POST)
	public String pdtDelete(@RequestParam("pdtNum") String pdtNum) throws Exception {
		System.out.println(pdtNum);
		adService.pdtDelete(Integer.parseInt(pdtNum));

		return "redirect:/admin/pdtList";
	}
	
//  �ֹ� ���
	@RequestMapping(value = "/orderList", method = RequestMethod.GET)
	public String getOrderList(Model model) throws Exception {
	   
		 List<OrderDTO> orderList = adService.orderList();
		 
		 model.addAttribute("orderList", orderList);
		 return "admin/orderList";
	}

//  �ֹ� �� ���
	@RequestMapping(value = "/orderListView", method = RequestMethod.GET)
	public void getOrderList(@RequestParam("orderCode") String orderId, OrderDTO od, Model model) throws Exception {
	 
		 od.setOrderId(orderId);  
		 List<OrderListDTO> orderListView = adService.orderListView(od);
		 System.out.println(orderListView);
		 model.addAttribute("orderListView", orderListView);
	}
//  ��ۻ��� ����
	@RequestMapping(value = "/orderListView", method = RequestMethod.POST)
	public String deliveryStatus(OrderDTO od) throws Exception {
		System.out.println("od="+od);
		adService.deliveryStatus(od);
		
		List<OrderListDTO> orderView = adService.orderListView(od); 
		ProductDTO pdt = new ProductDTO();
		  
		for(OrderListDTO i : orderView) {
			pdt.setPdtNum(i.getPdtNum());
			pdt.setPdtVolume(i.getCartVolume());
			adService.changeStock(pdt);
		}
		return "redirect:/admin/orderListView?orderCode="+od.getOrderId();
	}	
//  ��ǰ�򺸱�
	@RequestMapping(value = "/allCmt", method = RequestMethod.GET)
	public void getAllCmt(Model model) throws Exception {
				
		List<PdtCmtListDTO> allCmt = adService.cmtAll();
		
		model.addAttribute("allCmt", allCmt);
	}

//  ��ǰ�� ����
	@RequestMapping(value = "/allCmt", method = RequestMethod.POST)
	public String postAllCmt(PdtCommentDTO pcd) throws Exception {
		System.out.println("���� pcd = "+pcd);		
		adService.adDeleteCmt(pcd.getCmtPdtNum());
		
		return "redirect:/admin/allCmt";
	}	
	
//  ck ������ ���� ���ε�
	@RequestMapping(value = "/ckUpload", method = RequestMethod.POST)
	public void postCKEditorImgUpload(HttpServletRequest req, HttpServletResponse res,
			@RequestParam MultipartFile upload) throws Exception {

		// ���� ���� ����
		UUID uid = UUID.randomUUID();
		
		JsonObject json = new JsonObject();
		OutputStream out = null;
		PrintWriter printWriter = null;

		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");

		try {

			String fileName = upload.getOriginalFilename(); // ���� �̸� ��������
			byte[] bytes = upload.getBytes();

			// ���ε� ���
			String ckUploadPath = uploadPath + File.separator + "ckUpload" + File.separator + uid + "_" + fileName;
			System.out.println(ckUploadPath);
			out = new FileOutputStream(new File(ckUploadPath));
			out.write(bytes);
			out.flush(); // out�� ����� �����͸� �����ϰ� �ʱ�ȭ

			String callback = req.getParameter("CKEditorFuncNum");
			printWriter = res.getWriter();
			String fileUrl = "/shop/ckUpload/" + uid + "_" + fileName; // �ۼ�ȭ��
			
			json.addProperty("uploaded", 1);
			json.addProperty("fileName", fileName);
			json.addProperty("url", fileUrl);
			json.addProperty("callback", callback);
			
			printWriter.println(json);
			printWriter.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (printWriter != null) {
					printWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return;
	}
}
