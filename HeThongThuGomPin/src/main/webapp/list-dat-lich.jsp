<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách đặt lịch</title>
<link type="text/css" rel="stylesheet" href="css/styleCRUD.css">
</head>
<script>
    function sortProductsByPriceAscending() {
        window.location.href = 'CRUDDatLichControll?command=LIST_BY_PRICE_ASCENDING';
    }
    
    function sortProductsByPriceDescending() {
        window.location.href = 'CRUDDatLichControll?command=LIST_BY_PRICE_DESCENDING';
    }
    
    function listDongHo() {
        window.location.href = 'CRUDDatLichControll?command=LIST';
    }
    
    function searchDongHo() {
        var searchInput = document.getElementById('searchInput').value;
        window.location.href = 'CRUDDatLichControll?command=SEARCH&tenDH=' + searchInput;
    }

    function filterByBrand() {
        var selectedBrandId = document.getElementById('brandFilter').value;
        window.location.href = 'CRUDDatLichControll?command=LIST_BY_BRAND&thuongHieuId=' + selectedBrandId;
    }
</script>
<body>
	<div id="wrapper">
	
		<div id="header">
       	
		                <a href="PinServlet" style="color: white;"><h3>Trang chủ</h3></a>
		                <a href="CRUDDatLichControll" style="color: orange ;"><h5> > Quản Lý Đặt Lịch </h5></a>

			<h2>Quản Lý Đặt Lịch</h2>
		</div>
	</div>
	<div id="container">
		<div id="content">
			<input type="button" value="thêm khách hàng" 
				   onclick="window.location.href='add-pin-form.jsp'; return false;"
				   class="add-button"
				   style="margin-left: 0px; margin-right: 20px"/>
		
			<input type="button" value="thêm lịch" 
				   onclick="window.location.href='add-dat-lich-form.jsp'; return false;"
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
					<th>Tên khách hàng</th>
					<th>Thời Gian</th>
					<th>Ngày hẹn</th>
					<th>Mã pin</th>
					<th>Mã chi nhánh</th>
					<th>Mô tả</th>
					<th>Action</th>
				</tr>
				
				<c:forEach var="tempDatLich" items="${LIST_DAT_LICH}">
					
					<c:url var="tempLink" value="CRUDDatLichControll">
						<c:param name="command" value="LOADDATLICH" />
						<c:param name="maDatLich" value="${tempDatLich.maDatLich}" />
					</c:url>

					<c:url var="deleteLink" value="CRUDDatLichControll">
						<c:param name="command" value="DELETE" />
						<c:param name="maDatLich" value="${tempDatLich.maDatLich}" />
					</c:url>																		
					  <tr>
					  	<td>${tempDatLich.tenKH}</td>
				        <td>${tempDatLich.thoiGian}</td>
				        <td>${tempDatLich.ngayHen}</td>   
				        <td>${tempDatLich.pin.maPin}</td>
				        <td>${tempDatLich.chiNhanh.maChiNhanh}</td>
				        <td>${tempDatLich.moTa}</td>
				        <td>
							<a href="${tempLink}">Update</a> 							  
							<a href="${deleteLink}"
							onclick="if (!(confirm('Ban co chac muon xoa thong tin dat lich nay?'))) return false">
							Delete</a>	
						</td>
					</tr>
				
				</c:forEach>
				
			</table>
		
		</div>
	
	</div>
</body>
</html>