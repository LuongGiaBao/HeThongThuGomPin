 <%@page import="entity.GioHang"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.List"%>
<%@page import="entity.GioHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<link rel="stylesheet" href="./css/cart.css" />
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<html>
<head>
  <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="icon" href="img/logo-mini.jpg" type="image/x-icon" />
    <link rel="stylesheet" href="css/style_trangchu.css" type="text/css"/>
    <link rel="stylesheet" href="css/reset.css" />
    
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="fontawesome/css/all.css" />
    
   <!-- Thêm link đến jQuery UI CSS -->
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    
    
    <script src="js/jquery.js"></script>
    <script src="js/style.js"></script>
    <script src="js/bootstrap.min.js"></script>
<title>Đặt lịch</title>
</head>
<body>

  <!-- Thêm link đến thư viện jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- Thêm link đến thư viện jQuery UI -->
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script type="text/javascript">
        $(document).ready(function() {
            $('.list-menu li').hover(function() {
                    $(this).find(".sub-menu").stop().fadeIn(500);
                },
                function() {
                    $(this).find(".sub-menu").stop().fadeOut(300);
                });
        });
        
        function searchDongHo() {
            var searchInput = document.getElementById('searchInput').value;
            window.location.href = 'DongHoServlet?command=SEARCH&tenDH=' + searchInput;
        }
        
        $(function() {
            $("#datepicker").datepicker({
                dateFormat: 'dd/mm/yy', // Corrected option syntax
            });
        });
    </script>
    
    
    
<input type="hidden" name="command" value="TEST">

            
 <div id="main">
    <!-- ============ HEADER ===============-->
    <div id="header">
        
        <div class="banner">
            <div class="login">
              
                <div class="sub-login">
                    <p><a href="tel:0354985272"><i class="fas fa-phone-alt"></i> HOTLINE: 0354985272</a></p>
                    <ul>
                    
                    <!-- Ẩn nút TK sau khi đăng nhập -->
                    <c:if test ="${sessionScope.acc == null}">
                         	<li>
                            <p><a href="PinServlet?command=TAIKHOAN"><i class="fas fa-user"></i>Tài khoản</a></p>
							</li>
                        </c:if>
                       
                    </ul>
                    <p><a href="#"><i class="fas fa-map-marker-alt" ></i>Hệ thống cửa hàng</a></p>
                    <a href="?command=VIEWCART" class="cart-link">
					  <i class="fas fa-shopping-cart" style="margin-left: 1000px;"></i>
					  <span class="cart-counter">1</span>
					  <span class="cart-title">Giỏ hàng</span>
					</a>
                    
                     </div>
            </div>
            <div class="sub-banner">
                <a href="PinServlet"><img class="logo" style="color: red; width: 60%; height:84px;  " src="imgPin/logo.jpeg" alt="" /></a>
                
                <form action>
                    <input type="search" id="searchInput" placeholder="Bạn đang tìm dòng pin nào..." />
                    
                </form>
                <i class="fas fa-search" onclick="searchPin()" ></i>
            </div>
        </div>
    </div>   
</div> 
    <!-- ============END HEADER=============== -->
        <!-- ============MENU=============== -->
        

    <nav style="margin-left: 0px;">
        <ul class="list-menu" style="margin-left: 50px;">
        
        <!--  Hello Người dùng-->
        <c:if test="${not empty sessionScope.acc}">
						    <c:if test="${not empty sessionScope.acc.user}">
						        <li class="f">
						            <a  href="#" style="color: while;">Hello ${sessionScope.acc.user}</a>
						        </li>
						        
						     
						        <li class="f">
						            <a style="color: while;" href="LogoutControll">Logout</a>
						        </li>
						    </c:if>
						    
						    
						</c:if>
    
        
            <li class="f"><a style="color: black; margin-left: 150px;" href="PinServlet">Trang chủ</a></li>
            
            
         
            <li class="dmsp" ><a href="#">THƯƠNG HIỆU<i class="fas fa-caret-down"></i></a>
                <ul class="sub-menu">
               
                <c:forEach var="brand" items="${LIST_BRANDS}">
					<li> value="${brand.thuongHieuId}">${brand.name}</li>
				</c:forEach>
                    <li ><a href="#pincono" >Pin con ó</a></li>
                    <li><a href="#AAA">AAA</a></li>
                    <li><a href="#pinvuong">Pin vuông</a></li>
                </ul>
          </li>

            <li>
	            <a href="#">TIN TỨC<i class="fas fa-caret-down"></i></a>
	             <ul class="sub-menu">
	                
	                <li><a href="PinServlet?command=TINTUC">TIN XỬ LÝ PIN</a></li>
	               
	            </ul>
           </li>
           
           <li><a href="PinServlet?command=THONGTIN">THÔNG TIN</a></li>
           
           <c:if test ="${sessionScope.acc.isAdmin == 1}">
	           
	           <li>
	            <a href="#">QUẢN LÝ<i class="fas fa-caret-down"></i></a>
	             <ul class="sub-menu">
	                
	                <li><a href="CRUDServlet">QUẢN LÝ PIN</a></li>
	                <li><a href="CRUDDatLichControll">QUẢN LÝ ĐẶT LỊCH</a></li>
	               
	            </ul>
           </li>
           
          </c:if>
       </ul>
  </nav>
  
 
      <div class="slider">
  <img src="imgPin/anhpin2.jpg" style="height: 400px;">
 </div>
 <div id="wrapper">
	
  <div class="container mt-5 p-3 rounded cart">
        <div class="row no-gutters">
            <div class="col-md-8">
                <div class="product-details mr-2">
                    <div class="d-flex flex-row align-items-center"><i class="fa fa-long-arrow-left"></i><span class="ml-2">
                    		<a href="DongHoServlet">Tiếp tục mua sắm</a></span>
                    </div>
                    <hr>
                    <h3 class="mb-0">Đặt lịch xử lý pin</h3><br>
                    <div class="d-flex justify-content-between"><h5>Thông tin khách hàng</h5><br>
                        <div class="d-flex flex-row align-items-center"><span class="text-black-50">sắp xếp theo:</span>
                            <div class="price ml-2"><span class="mr-1">giá</span><i class="fa fa-angle-down"></i></div>
                        </div>
                    </div>

                    <form action="CRUDServlet" method="GET" onsubmit="return validateForm();">
		
			<input type="hidden" name="command" value="ADD"/>
			
			<table>
				<tbody>
					<tr>
						<td><label>Họ và tên:</label></td>
						<td><input type="text" name="tenPin" /></td>
					</tr>
					

					<tr>
						<td><label>Số điện thoại:</label></td>
						<td><input type="text" name="soDienThoai" /></td>
					</tr>

					<tr>
						<td><label>Địa chỉ:</label></td>
						<td><input type="text" name="diaChi" /></td>
					</tr>
					
					<tr>
						<td><label>thời gian đặt lịch:</label></td>
						<td><select id="thoiGianDropdown" name="thoiGian" onchange="updateInput()" style="width: 200px; margin-top: 20px">
			    				<option>8:00</option>
			    				<option>9:00</option>
			    				<option>10:00</option>
			    				<option>12:00</option>
			    				<option>13:00</option>
			    				<option>14:00</option>
			    				<option>15:00</option>
			    				<option>16:00</option>
			    				<option>17:00</option>
			    				<option>18:00</option>
			    				<option>19:00</option>
							</select>
						 <input type="text" id="datepicker" name="ngayHen" style="margin-top: 10px" placeholder="Chọn ngày" readonly />
						
						</td>
						
					</tr>
					
					<tr>
						<td><label>cửa hàng:</label></td>
						<td>
							<select id="diaChiDropdown" name="diaChi" onchange="updateInput()" style="width: 200px; margin-top: 20px">
			    				<option>Quận 1</option>
			    				<option>Quận 2</option>
			    				<option>Quận 3</option>
			    				<option>Quận 4</option>
							</select>
						</td>
						<td><label>ma pin:</label></td>
						<td>
						<input type="text" name="maPin" style="margin-top: 10px"  />
						</td>
						
					</tr>
					
					<tr>
						<td><label>Chi chú:</label></td>
						<td><textarea name="ghiChu" placeholder="Nhập ghi chú" style="height: 150px ; width: 400px" ></textarea></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
					
				</tbody>
			</table>
		</form>
	                       
	                        <!-- <span class="d-block ml-5 font-weight-bold">totalPrice</span><i class="fa fa-trash-o ml-3 text-black-50"></i> -->
	                        
	            </div>  
	          </div>    
	          
	
                    
            <div class="col-md-4">
                <div class="payment-info">
                    <div class="d-flex justify-content-between align-items-center">
                    	<span>PIN ĐÃ CHỌN</span>
                    </div>
		<form action="PinServlet" method="get">
        <div class="row">
        	    <input name="command" type="hidden" value="LOADTHONGTIN">
				<input name="maPin" type="hidden" value="${PIN.getMaPin()}">
            <div class="col-5 ml-2">
                <td><img src="${PIN.getHinhAnh()}" alt="Image" width="150" height="150"></td>
            </div>
            <div class="col-6">
                <h3>${PIN.getTenPin()}</h3>
                <p><span class="text-danger">${PIN.getGiaThu()}đ / 20 cái</span></p>
                <p>Số lượng cần xử lý:</p>
				<input type="number" name="soLuong" id="soLuongInput" value="1" min="1" data-soLuong="${PIN.getSoLuong()}"><br>
				<button type="submit" class="btn btn-danger " style="width: 300px; margin-top: 10px;">Đặt lịch xử lý pin</button>
            </div>
        </div>
    </form>

		                 
        </div>
                    <hr class="line">
                    
           
            </div>
        </div>
    </div>

</body>
</html>