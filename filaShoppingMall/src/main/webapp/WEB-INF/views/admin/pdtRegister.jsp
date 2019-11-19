<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FILA-로그인</title>
<%@include file ="../common/head.jsp" %>
<script type="text/javascript">
	//레벨1 함수
	$(document).ready(function(){
		var jsonData = JSON.parse('${category}');
		var cateLv01 = new Array();
		var cateOj01 = new Object();
		var cate1Select = $("#category01");
		console.log(jsonData);
		
		for(i = 0; i < jsonData.length; i++){
			if(jsonData[i].level == "1") {
			  cateOj01 = new Object();
			  cateOj01.cateCode = jsonData[i].cateCode;
			  cateOj01.cateName = jsonData[i].cateName;
			  cateLv01.push(cateOj01);
			}
		}
		for(var i = 0; i < cateLv01.length; i++) {
			cate1Select.append("<option value='" + cateLv01[i].cateCode + "'>" + cateLv01[i].cateName + "</option>");
		}
	});
</script>
<script type="text/javascript">
	//레벨2 함수
	$(document).on("change","#category01",function(){
				var jsonData = JSON.parse('${category}');
				var cateLv02 = new Array();
				var cateOj02 = new Object();
				var cate2Select = $("#category02");
				var selectVal = $(this).val();  
	
		for(i = 0; i < jsonData.length; i++){
			if(jsonData[i].level == "2") {
				cateOj02 = new Object();
				cateOj02.cateCode = jsonData[i].cateCode;
				cateOj02.cateName = jsonData[i].cateName;
				cateOj02.cateCodeRef = jsonData[i].cateCodeRef;
				cateLv02.push(cateOj02);
			}
		}
		cate2Select.children().remove();
		$("option:selected", this).each(function(){
			cate2Select.append("<option value=''>전체</option>");
			for(var i = 0; i < cateLv02.length; i++) {
				if(selectVal == cateLv02[i].cateCodeRef) {
					 cate2Select.append("<option value='" + cateLv02[i].cateCode + "'>"
				   + cateLv02[i].cateName + "</option>");
				}
			}
		});
	});
</script>
<script type="text/javascript">
	//카테고리 대분류 값 저장
	$(document).ready(function(){
	    $("#category01").on("change", function(){
	        var setionVal = $(this).find("option[value='" + $(this).val() + "']").text();
	        $("#pdtSection").val(setionVal);
	    });
	});
</script>
</head>
<body>
	<%@include file ="adminNav.jsp" %>
	<div class="container_ad col-sm-9 float-right">
		<form action="${path}/admin/pdtRegisterProc" method="post">
			<label>1차분류</label>
			<input id="pdtSection" type="hidden" name="pdtSection" value="">
			<select id="category01" class="catecory01">
				<option value="">전체</option>
			</select>
			<label>2차분류</label>
			<select id="category02" class="category02" name="cateCode">
				<option value="">전체</option>
			</select>
			
			<div>
				<p>상품명</p>
				<input type="text" name="pdtName"/>
			</div>
			<div>
				<p>상품 수량</p>
				<input type="text" name="pdtVolume"/>
			</div>
			<div>
				<p>상품 가격</p>
				<input type="text" name="pdtPrice"/>
			</div>
			<div>
				<p>상품 소개</p>
				<textarea rows="30" cols="20" name="pdtDes"></textarea>
			</div>
			<input type="submit" value="상품등록하기">
		</form>
	</div>
	<div class="footer_ad">
	
	</div>
</body>
</html>