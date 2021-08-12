Create database WebMarshall
go
use WebMarshall
go 

-- Create table
create table ACCOUNTS
(
	UserName   varchar(20) primary key not null,
	Password   varchar(200) not null,
	FullName   nvarchar(100),
	Email	   varchar(100),
	Active     bit not null,
	User_Role  varchar(20) not null
) 
insert into ACCOUNTS values('user','$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu','nguyen van a','',1,'ROLE_USER')
insert into ACCOUNTS values('admin','$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu','nguyen ngoc quy','',1,'ROLE_ADMIN')

select * from ACCOUNTS
---------------------------------------

Create table CATEGORIES(
	CatId    int identity primary key,
	CatName  nvarchar(100)
)
insert into CATEGORIES values (N'Loa Mashall')
insert into CATEGORIES values (N'HeadPhones')
insert into CATEGORIES values (N'Amplifiers')
insert into CATEGORIES values (N'Phụ Kiện Mashall')

select * from CATEGORIES

select COUNT(*) from CATEGORIES

---------------------------------------

Create table PRODUCTTYPE(
	TypeId        int identity primary key,
	CatId         int foreign key references Categories(CatId),
	TypeName      nvarchar(100),
	Descriptions  ntext
)
insert into PRODUCTTYPE values (1,N'Loa Marshall- Dùng điện trực tiếp','')
insert into PRODUCTTYPE values (1,N'Loa Marshall – Portable','')
insert into PRODUCTTYPE values (2,N'Tai nghe','')
insert into PRODUCTTYPE values (3,N'ACOUSTIC AMP','')
insert into PRODUCTTYPE values (3,N'AMP GUITAR ĐIỆN','')
insert into PRODUCTTYPE values (4,N'Áo','')

select * from PRODUCTTYPE

---------------------------------------
  
create table PRODUCTS
(
	ProId		  varchar(20) primary key not null,
	TypeId        int foreign key references ProductType(TypeId),
	ProName       varchar(200) not null,
	Image         nvarchar(200),
	Price         double precision not null,
	CreatDate     datetime ,
	Unit          nvarchar(10),
	Discount      float,
	Descriptions  nvarchar(200),
	Detail        ntext,
	Status        bit,
	Quantity	  int
) 
alter table Products
	add Image nvarchar(200)
insert into PRODUCTS values ('S01',1,N'Marshall Acton II Voice – AMAZON ALEXA','',7490000,'2021-01-01',N'đ',0,'','','true','')
insert into PRODUCTS values ('S02',1,N'Marshall Stanmore 2','',9900000,'2021-01-01',N'đ',0,'','','true','')
insert into PRODUCTS values ('S03',1,N'Marshall Woburn 2','',14900000,'2021-01-01',N'đ',0,'','','true','')
select * from PRODUCTS

---------------------------------------

create table ORDERS
(
	OrderId         int identity primary key not null,
	Amount          float,
	CustomerAdress  varchar(300) not null,
	OrderDate       datetime not null,
	Descriptions	ntext,
	UserName		varchar(20) foreign key references ACCOUNTS(UserName)
)

select * from ORDERS
 
select OrderDetail.OrDeId , PRODUCTS.ProId, ORDERS.UserName as UserName ---lay san pham da mua theo nguoi mua
from ((OrderDetail 
inner join ORDERS on OrderDetail.OrderId = Orders.orderId) 
inner join Products on OrderDetail.ProId = Products.proId) 
------------------
Select  ORDERS.OrderId, ACCOUNTS.UserName						--lay don hang theo nguoi mua
from ORDERS inner join ACCOUNTS on ORDERS.UserName = ACCOUNTS.UserName
---------------------------------------quy
  

----------------------------------------
create table OrderDetail
(
  OrDeId     int identity primary key not null,

  Price      double precision not null,
  Quantity   int not null,
  OrderId    int foreign key references ORDERS(OrderId) not null,
  ProId      varchar(20) foreign key references PRODUCTS(ProId) not null,
  Discount   float
) 

select * from ORDERDETAIL
 
Select distinct OrderDetail.Quantity, PRODUCTS.ProId, ORDERS.OrderId 
from ((OrderDetail inner join PRODUCTS on OrderDetail.ProId = PRODUCTS.ProId)
inner join ORDERS on OrderDetail.OrDeId = ORDERS.OrderId)
order by OrderId desc 



Select ProId,SUM(Quantity) from OrderDetail group by ProId order by SUM(Quantity) desc



select p.ProId,SUM(od.Quantity)
from Products as p  inner join OrderDetail as od on p.ProId = od.ProId 
group by p.ProId order by SUM(od.Quantity) desc 



select * from products where ProId =
(select ProId 
  from 
    (select ProId , sum(Quantity) as total_order,
        max(sum(Quantity)) over() as maxSm   from orderdetail
        group by ProId
    )
where total_order = maxSm)



Select OrderDetail.ProId, SUM(OrderDetail.Quantity) as Quantity
from OrderDetail inner join PRODUCTS on OrderDetail.ProId = PRODUCTS.ProId 
group by OrderDetail.ProId order by SUM(OrderDetail.Quantity) desc
--------------------------------------  

  

--------------------------------------  

Create table NEWS(
	NewsId    int identity primary key,
	Title     nvarchar(200),
	Images    nvarchar(200),
	Content   ntext,
) 
--------------------------------------  

Create table Contact(
	ContactId   int identity primary key,
	ShopName    nvarchar(50),
	Addresss    nvarchar(200),
	HotLine     varchar(50),
	Email       varchar(100),
	Title       nvarchar(200),
	Content     ntext
)
insert into Contact values ('Mashall',N'381 Nguyễn Khang','0123456789','webstiequy@gmail.com',
	'Mở cửa từ 8AM đến 9PM','Chuyên cung cấp những sản phẩm âm thanh loa, headphones, phụ kiện Marshall.')
 