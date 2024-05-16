<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách Pin</title>
<link type="text/css" rel="stylesheet" href="css/styleCRUD.css">
</head>
<script>
    function sortProductsByPriceAscending() {
        window.location.href = 'CRUDServlet?command=LIST_BY_PRICE_ASCENDING';
    }
    
    function sortProductsByPriceDescending() {
        window.location.href = 'CRUDServlet?command=LIST_BY_PRICE_DESCENDING';
    }
    
    function listDongHo() {
        window.location.href = 'CRUDServlet?command=LIST';
    }
    
    function searchDongHo() {
        var searchInput = document.getElementById('searchInput').value;
        window.location.href = 'CRUDServlet?command=SEARCH&tenDH=' + searchInput;
    }

    function filterByBrand() {
        var selectedBrandId = document.getElementById('brandFilter').value;
        window.location.href = 'CRUDServlet?command=LIST_BY_BRAND&thuongHieuId=' + selectedBrandId;
    }
</script>
<body>
	<div id="wrapper">
	
		<div id="header">
       	
		                <a href="PinServlet" style="color: white;"><h3>Trang chủ</h3></a>
		                <a href="CRUDServlet" style="color: orange ;"><h5> > Quản Lý Pin </h5></a>

			<h2>Quản Lý Pin</h2>
		</div>
	</div>
	<div id="container">
		<div id="content">
		
			<input type="button" value="thêm Pin" 
				   onclick="window.location.href='add-pin-form.jsp'; return false;"
				   class="add-button"
				   style="margin-left: 0px; margin-right: 20px"/>
				   
			<input type="button" value="Giá: tăng dần" onclick="sortProductsByPriceAscending()"
				class="add-button"
				style="margin-left: 0px; margin-right: 20px"/>
				
			<input type="button" value="Giá: giảm dần" onclick="sortProductsByPriceDescending()"
				class="add-button"
				style="margin-left: 0px; margin-right: 20px">
				
			<input type="button" value="Bỏ lọc" onclick="listDongHo()"
				class="add-button"
				style="margin-left: 0px; margin-right: 20px">
				
			<input type="text" id="searchInput" placeholder="Nhập tên sản phẩm cần tìm..." style="width: 200px">
			<input type="button" value="Tìm kiếm" onclick="searchDongHo()" class="add-button" style="margin-left: 0px; margin-right: 20px">
			
			<select id="brandFilter" onchange="filterByBrand()" style="width: 200px">
			    <option value="">Chọn Thương Hiệu</option>
				<c:forEach var="brand" items="${LIST_BRANDS}">
					<option value="${brand.thuongHieuId}">${brand.name}</option>
				</c:forEach>
			</select>	
			<table>
				<tr>
					<th>Tên Pin</th>
					<th>Giá Thu</th>
					<th>Số lượng</th>
					<th>Mô tả</th>
					<th>Thương hiệu</th>
					<th>Hình ảnh</th>
					<th>Action</th>
				</tr>
				
				<c:forEach var="tempPin" items="${LIST_PIN}">
					
					<c:url var="tempLink" value="CRUDServlet">
						<c:param name="command" value="LOADPIN" />
						<c:param name="maPin" value="${tempPin.maPin}" />
					</c:url>

					<c:url var="deleteLink" value="CRUDServlet">
						<c:param name="command" value="DELETEPIN" />
						<c:param name="maPin" value="${tempPin.maPin}" />
					</c:url>																		
					<tr>
						<td> ${tempPin.tenPin} </td>
						<td> ${tempPin.giaThu} </td>
						<td> ${tempPin.soLuong} </td>
						<td> ${tempPin.moTa} </td>
						<td> ${tempPin.thuongHieuId} </td>
						<td><img src="${tempPin.getHinhAnh()}" alt="Image" style="width: 150px; height: 150px"></td>
						<td> 
							<a href="${tempLink}">Update</a> 							  
							<a href="${deleteLink}"
							onclick="if (!(confirm('Ban co chac muon xoa loai pin nay?'))) return false">
							Delete</a>	
						</td>
					</tr>
				
				</c:forEach>
				
			</table>
		
		</div>
	
	</div>
</body>
</html>