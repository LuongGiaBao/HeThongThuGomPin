<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update đồng hồ</title>
 <!-- <link type="text/css" rel="stylesheet" href="css/styleCRUD.css"> -->
<link type="text/css" rel="stylesheet" href="css/add-dongho-style.css">	 


 <!-- Thêm link đến jQuery UI CSS -->
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    
    <style type="text/css">
    	#wrapper {width: 100%; margin-top: 0px;}
		#header {background: #0775d3; margin-top: 0px; padding:15px 0px 15px 0px; text-align: center;}
		#header h2 {width: 100%; margin:auto; color: #FFFFFF;}
		#container {width: 100%}
		#container h3 {color: #000;}
		#container #content {margin-top: 20px;}
	</style>
<script>
      
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
        
        $(function() {
            $("#datepicker").datepicker({
                dateFormat: 'dd/mm/yy',
            });
        });
    </script>
    
</head>
<body>
	

	<div id="wrapper">
		<div id="header" style="background: #0775d3; margin-top: 0px; padding:15px 0px 15px 0px; text-align: center; height: 160px">
						<a href="PinServlet" style="color: white;"><h3>Trang chủ</h3></a>
		                <!-- <a href="CRUDDatLichControll"><h5 style="color: orange ;"> > Quản Lý Đặt lịch</h5></a> -->
		            	<a href="CRUDServlet?command=UPDATE"><h5 style="color: orange ;"> > Cập nhật pin</h5></a>
			<h2>Cập nhật pin</h2>
		</div>
	</div>
	
	<div id="container">
		<form action="CRUDServlet" method="GET">
		
			<input type="hidden" name="command" value="UPDATEPIN" />

			<input type="hidden" name="maPin" value="${PIN.maPin}" />
			
			<table>
				<tbody>
				
				
				<!-- Ten Pin -->
					<tr>
						<td><label>Tên Pin :</label></td>
						<td><select name="tenPin" style="width:150px">
							<option> Con ó đỏ </option>
							<option> AAA </option>
							<option> Golite 9V </option>
						
						</select> </td>
						 
						<%-- <td><input type="text" name="tenPin" 
								   value="${PIN.tenPin}" /></td>  --%>
					</tr>
					
					<!-- Giá thu  -->
						<tr>
							<td><label>Giá thu:</label></td>
							<td><input type="text" name="giaThu" 
									   value="${PIN.giaThu}" /></td>
						</tr>
						
						
						<!--  Số lượng -->
						<tr>
							<td><label>Số lượng:</label></td>
							<td><input type="text" name="soLuong" 
									   value="${PIN.soLuong}" /></td>
						</tr>
					
					<tr>
						<td><label>Mô tả:</label></td>
						<td><input type="text" name="moTa" 
								   value="${PIN.moTa}" /></td>
					</tr> 
					
					<tr>
						<td><label>Thương hiệu:</label></td>
						<td><input type="text" name="thuongHieuId" 
								   value="${PIN.thuongHieuId}" /></td>
					</tr> 
					
					<tr>
						<td><label>Hình ảnh:</label></td>
						<td><input type="text" name="hinhAnh" 
								   value="${PIN.hinhAnh}" /></td>
					</tr> 
					
						 <tr>	 <!-- Thêm thông tin đặt lịch -->
				     <%--   <td> <label>Thời gian đặt lịch:</label></td>
					        <td><select name="thoiGian" style="width: 150px">
					            <option>8:00</option>
					            <option>9:00</option>
					            <option>10:00</option>
					            <!-- Thêm các giờ còn lại -->
					        </select>
					        <input type="text" id="datepicker" name="ngayHen" placeholder="Chọn ngày" readonly />
					        </td>
					        
				        </tr>	
						
				 	 <tr>
						<td><label>mã Chi Nhanh:</label></td>
						<td>
							<select id="chiNhanhDropdown" onchange="updateInput2()" style="width: 200px; margin-top: 20px">
			    				<option>144 Nguyen Van Bao, Go Vap</option>
			    				<option>Cong Vien Lang Hoa, Go Vap</option>
			    				<option>Phố đi bộ Nguyễn Huệ, Quận 1</option>
							</select>
							<input type="text" id="maChiNhanh" name="maChiNhanh" value="${DATLICH.maChiNhanh}" style="margin-top: 10px" readonly />
						</td>

					</tr>
					
					<tr>
						<td><label>mã Pin:</label></td>
						<td>
							<select id="pinDropdown" onchange="updateInput()" style="width: 200px; margin-top: 20px">
			    				<option>Pin con ó đỏ</option>
			    				<option>AAA</option>
			    				<option>Pin vuông Golite 9v</option>
							</select>
							<input type="text" id="maPin" name="maPin" value="${DATLICH.maPin}" style="margin-top: 10px" readonly />
						<td>
					</tr> 
					 --%>
					
					
					
					
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