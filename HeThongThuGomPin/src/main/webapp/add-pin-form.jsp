<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	* {
		width: 1280px;
		margin: 0 auto;
	}

	/*
	html, body{
		margin-left:15px; margin-right:15px; 
		padding:0px; 
		font-family:Verdana, Arial, Helvetica, sans-serif;
	}
	*/
	
	table {   
		border-collapse:collapse;
		border-bottom:1px solid gray;
		font-family: Tahoma,Verdana,Segoe,sans-serif;
		width:72%;
	}
	 
	th {
		border-bottom:1px solid gray;
		background:none repeat scroll 0 0 #0775d3;
		padding:10px;
		color: #FFFFFF;
	}
	
	tr {
		border-top:1px solid gray;
		text-align:center;	
	}
	 
	tr:nth-child(even) {background: #FFFFFF}
	tr:nth-child(odd) {background: #BBBBBB}	
	 
	#wrapper {width: 100%; margin-top: 0px;}
	#header {background: #0775d3; margin-top: 0px; padding:15px 0px 15px 0px; text-align: center;}
	#header h2 {width: 100%; margin:auto; color: #FFFFFF;}
	#container {width: 100%}
	#container h3 {color: #000;}
	#container #content {margin-top: 20px;}
	
	.add-button {
		border: 1px solid #666; 
		border-radius: 5px; 
		padding: 4px; 
		font-size: 12px;
		font-weight: bold;
		width: 120px; 
		padding: 5px 10px; 
		margin-bottom: 15px;
		background: #cccccc;
		margin-left: 160px;
	}

	form {
		margin-top: 10px;
	}
	
	label {
		font-size: 16px; 
		width: 100px; 
		display: block; 
		text-align: right;
		margin-right: 10px;
		margin-top: 8px;
		margin-bottom: 8px;
	}
	
	input {
		width: 250px;
		border: 1px solid #666; 
		border-radius: 5px; 
		padding: 4px; 
		font-size: 16px;
	}
	
	.save {
		font-weight: bold;
		width: 130px; 
		padding: 5px 10px; 
		margin-top: 30px;
		background: #cccccc;
	}
	
	table {   
		border-style:none;
		width:50%;
	}
	
	tr:nth-child(even) {background: #FFFFFF}
	tr:nth-child(odd) {background: #FFFFFF}
	
	tr {
		border-style:none;
		text-align:left;	
	}
</style>
<script>
        function validateForm() {
        	var tenDH = document.getElementsByName("tenPin")[0].value;
            var giaBan = document.getElementsByName("giaBan")[0].value;
            var soLuong = document.getElementsByName("soLuong")[0].value;
            var moTa = document.getElementsByName("moTa")[0].value;
            var thuongHieuDropdown = document.getElementById("thuongHieuDropdown");
            var hinhAnh = document.getElementsByName("hinhAnh")[0].value;

            // Kiểm tra không được bỏ trống các trường input
            if (tenDH.trim() === "" || giaBan.trim() === "" || soLuong.trim() === "" || moTa.trim() === "" || thuongHieuDropdown.value.trim() === "" || hinhAnh.trim() === "") {
                alert("Không được bỏ trống các trường input.");
                return false;
            }

            // Kiểm tra nếu giá bán không phải là số
            if (isNaN(giaBan)) {
alert("Giá bán phải là một số.");
                return false;
            }

            // Kiểm tra nếu số lượng không phải là số
            if (isNaN(soLuong)) {
                alert("Số lượng phải là một số.");
                return false;
            }

            // Nếu mọi thứ đều hợp lệ, cho phép gửi form
            return true;
        }
        
        function updateInput() {
            var pinDropdown = document.getElementById('pinDropdown');
            var pinIdInput = document.getElementById('maPin');
            var selectedIndex = pinDropdown.selectedIndex;
            pinIdInput.value = selectedIndex + 1;
        }
        function updateInput2() {
            var chiNhanhDropdown = document.getElementById('chiNhanhDropdown');
            var chiNhanhIdInput = document.getElementById('maChiNhanh');
            var selectedIndex = chiNhanhDropdown.selectedIndex;
            chiNhanhIdInput.value = selectedIndex + 1;
        }
    </script>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Add New Product</h2>
		</div>
	</div>
	
	<div id="container">
		<form action="CRUDServlet" method="POST" onsubmit="return validateForm();">
		
			<input type="hidden" name="command" value="ADDPIN"/>
			
			<table>
				<tbody>
				
				<tr>
						<td><label>Tên Pin: </label> </td>
						<td><input type="text" name="tenPin"/> </td>
					
				 </tr>
				 
				 <tr>
						<td><label>Giá Thu: </label> </td>
						<td><input type="text" name="giaThu"/> </td>
					
				 </tr>
				 
				 <tr>
						<td><label>Số Lượng: </label> </td>
						<td><input type="text" name="soLuong"/> </td>
					
				 </tr>
				 
				 <tr>
						<td><label>Mô Tả: </label> </td>
						<td><input type="text" name="moTa"/> </td>
					
				 </tr>
				 
				  <tr>
						<td><label>Thương Hiệu: </label> </td>
						<td><input type="text" name="thuongHieuId"/> </td>
					
				 </tr>
				
				 <tr>
						<td><label>Hình Ảnh: </label> </td>
						<td><input type="text" name="hìnhAnh"/> </td>
					
				 </tr>
				
					<!-- <tr>
						<td><label>Tên Pin:</label></td>
						<td><input type="text" name="tenPin" /></td>
					</tr>
					
					<tr>
						<td><label>Số điện thoại:</label></td>
						<td><input type="text" name="soDT" /></td>
					</tr>
					
					 <tr>
						<td><label>Giá Thu:</label></td>
						<td><input type="text" name="giaThu" /></td>
					</tr> 
					
					<tr>
						<td><label>Địa chỉ:</label></td>
						<td><input type="text" name="diaChi" /></td>
					</tr>

					<tr>
						<td><label>mã Pin:</label></td>
						<td>
							<select id="pinDropdown" onchange="updateInput()" style="width: 200px; margin-top: 20px">
			    				<option>Pin con ó đỏ</option>
			    				<option>AAA</option>
			    				<option>Pin vuông Golite 9v</option>
							</select>
							<input type="text" id="maPin" name="maPin" style="margin-top: 10px" readonly />
						</td>
					</tr>
					
					<tr>
						<td><label>Số lượng đổi:</label></td>
						<td><input type="text" name="soLuong" /></td>
					</tr>
					
					<tr>
						<td><label>mã Chi Nhanh:</label></td>
						<td>
							<select id="chiNhanhDropdown" onchange="updateInput2()" style="width: 200px; margin-top: 20px">
			    				<option>144 Nguyen Van Bao, Go Vap</option>
			    				<option>Cong Vien Lang Hoa, Go Vap</option>
			    				<option>Phố đi bộ Nguyễn Huệ, Quận 1</option>
							</select>
							<input type="text" id="maChiNhanh" name="maChiNhanh" style="margin-top: 10px" readonly />
						</td>

					</tr> -->

					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
					
				</tbody>
			</table>
		</form>
		
	</div>
</body>
</html>