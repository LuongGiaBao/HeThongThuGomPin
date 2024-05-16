<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <!-- Thêm link đến jQuery UI CSS -->
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <title>Đặt lịch</title>
    <style type="text/css">
        /* CSS styles here */
    </style>
    <script>
        // JavaScript validation and functionalities here
    </script>
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>Đặt Lịch</h2>
    </div>
</div>

<div id="container">
  <form action="CRUDDatLichControll" method="POST" onsubmit="return validateForm();">
        <input type="hidden" name="command" value="ADD"/>
        
        <!-- Thêm thông tin khách hàng -->
        <label>Họ và tên:</label>
        <input type="text" name="tenKH" />
        <br/>

        <label>Số điện thoại:</label>
        <input type="text" name="soDT" />
        <br/>

        <label>Địa chỉ:</label>
        <input type="text" name="diaChi" />
        <br/>

        <!-- Thêm thông tin đặt lịch -->
        <label>Thời gian đặt lịch:</label>
        <select name="thoiGian">
            <option>8:00</option>
            <option>9:00</option>
            <option>10:00</option>
            <!-- Thêm các giờ còn lại -->
        </select>
        <input type="text" id="datepicker" name="ngayHen" placeholder="Chọn ngày" readonly />
        <br/>

		<label>Mã pin:</label>
        <input type="text" name="maPin" />
        <br/>
        
        <label>Cửa hàng:</label>
        <select name="maChiNhanh">
            <option>1</option>
            <option>2</option>
            <!-- Thêm các quận khác -->
        </select>
        <br/>

        <label>Ghi chú:</label>
        <textarea name="moTa" placeholder="Nhập ghi chú"></textarea>
        <br/>

        <input type="submit" value="Lưu" />
    </form>
</div>

<!-- Thêm JavaScript để chọn ngày -->
<script>
    $(function() {
        $("#datepicker").datepicker({
            dateFormat: 'dd/mm/yy',
        });
    });
</script>

</body>
</html>