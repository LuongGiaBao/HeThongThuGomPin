Create Database PinTaiChe

Use PinTaiChe
---Khach hang

CREATE TABLE ThuongHieu (
	thuongHieuId int IDENTITY(1, 1) PRIMARY KEY NOT NULL,
	name nvarchar(255) NOT NULL,
)
INSERT INTO ThuongHieu
VALUES
(N'Pin con ó đỏ'),
(N'AAA'),
(N'Pin vuông Golite 9V')

SELECT * FROM ThuongHieu
Select * from Pin
drop table Pin
drop table ThuongHieu
drop table GioHang

CREATE TABLE [dbo].[Pin] (
	[maPin]  [int] IDENTITY(1, 1) PRIMARY KEY NOT NULL,
	[tenPin] [nvarchar](255) NOT NULL,
	[giaThu] [float] NULL,
	[soLuong] [int] NULL,
	[moTa] [nvarchar](255) NULL,
	[thuongHieuId] [int] REFERENCES ThuongHieu(thuongHieuId) NOT NULL,
	[hinhAnh] [varchar](255)
)
drop table Pin

Insert into Pin
Values 
(N'Pin con ó đỏ', 3000 , 5, N'Có thiết kế mặt số vàng, dây đeo Jubilee thép vàng, bộ vỏ chất liệu thép 904L', 1, 'https://cf.shopee.vn/file/ad9e71bdcfd3cf74d58fd09e0ed917b7'),
(N'Pin AAA', 4000 , 1, N'Pin có chữ AAA trên bề mặt pin', 2, 'https://pintrongtin.com/wp-content/uploads/2019/12/pin-aaa-energizer-ultimate-lithium.png'),
(N'Pin Golite 9V', 5000 , 1, N'Pin có hình vuông thương dùng trong micro caraoke', 2, 'https://cf.shopee.vn/file/8f9ba1402c02306e446da55467baacb8')


CREATE TABLE ChiNhanh (
	maChiNhanh int IDENTITY(1, 1) PRIMARY KEY NOT NULL,
	DiaChiChiNhanh nvarchar(255) NOT NULL,
)
drop table ChiNhanh

INSERT INTO ChiNhanh
VALUES
(N'144 Nguyen Van Bao, Go Vap'),
(N'Cong Vien Lang Hoa, Go Vap'),
(N'Phố đi bộ Nguyễn Huệ, Quận 1')

CREATE TABLE [dbo].[ThongTinDatLich] (
	[maDatLich]  [int] IDENTITY(1, 1) PRIMARY KEY NOT NULL,
	[tenKH] [nvarchar](255) NOT NULL,
	[soDT] [nvarchar](255) NOT NULL,
	[diaChi] [nvarchar](255) NOT NULL,
	[thoiGian] [nvarchar](255) NULL,
	[ngayHen] [nvarchar](255) NULL,
	[moTa] [nvarchar](255) NULL,
	[maPin] [int] REFERENCES Pin(maPin) NOT NULL,
	[maChiNhanh] [int] REFERENCES ChiNhanh(maChiNhanh) NOT NULL,
)

INSERT INTO ThongTinDatLich
VALUES
(N'Nguyen Van A',N'012548558',N'Phuong 12', N'8:00', '25/5/2024', N'tôi đến một mình', 1, 1)

drop table ThongTinDatLich
select * from [ThongTinDatLich] 

CREATE TABLE [dbo].[KhachHang] (
	[maKH]  [int] IDENTITY(1, 1),
	[tenKH] [nvarchar](255)PRIMARY KEY NOT NULL,
	[soDT] [nvarchar](255) NULL,
	[diaChi] [nvarchar](255) NULL,
)
INSERT INTO KhachHang
VALUES
(N'Nguyen Van A', '012345678', N'144/5/1525 le van tho')

drop table KhachHang
select * from [KhachHang] 


--AcCount

CREATE TABLE [dbo].[Account](
	[uID] [int] IDENTITY(1,1),
	[user] [varchar](255) NULL,
	[pass] [varchar](255) NULL,
	[isAdmin] [int] NULL,
	

	Constraint [PK_Account] primary key (uID)
)

select * from [Account] 
SET IDENTITY_INSERT [dbo].[Account] ON 

INSERT [dbo].[Account] ([uID], [user], [pass], [isAdmin]) VALUES (1, N'chidai', N'123456', 1)
INSERT [dbo].[Account] ([uID], [user], [pass], [isAdmin]) VALUES (2, N'giabao', N'SNZ6HE', 0)
INSERT [dbo].[Account] ([uID], [user], [pass], [isAdmin]) VALUES (3, N'congdanh', N'RXH3XJ', 0)
SET IDENTITY_INSERT [dbo].[Account] OFF

drop table Account

CREATE TABLE GioHang (
    id INT IDENTITY(1, 1),  
	tenDH Nvarchar(50) not Null,
    soLuong INT ,
   Constraint [PK_GioHang] primary key (id)
);
drop table GioHang
select *from GioHang
Insert into GioHang
Values 
(  N'Đồng hồ Gucci nam GG0397S-006', 1 )






	