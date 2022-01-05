select * from product
/* Drop Triggers */

DROP TRIGGER TRI_cart_cseq;
DROP TRIGGER TRI_event_eseq;
DROP TRIGGER TRI_Member_membernum;
DROP TRIGGER TRI_member_mseq;
DROP TRIGGER TRI_order_detail_odseq;
DROP TRIGGER TRI_product_productnum;
DROP TRIGGER TRI_product_pseq;
DROP TRIGGER TRI_qna_qseq;
DROP TRIGGER TRI_sub_product_spseq;



/* Drop Tables */

DROP TABLE address CASCADE CONSTRAINTS;
DROP TABLE admin CASCADE CONSTRAINTS;
DROP TABLE cart CASCADE CONSTRAINTS;
DROP TABLE event CASCADE CONSTRAINTS;
DROP TABLE Myaddress CASCADE CONSTRAINTS;
DROP TABLE qna CASCADE CONSTRAINTS;
DROP TABLE member CASCADE CONSTRAINTS;
DROP TABLE non_member CASCADE CONSTRAINTS;
DROP TABLE order_detail CASCADE CONSTRAINTS;
DROP TABLE orders CASCADE CONSTRAINTS;
DROP TABLE product CASCADE CONSTRAINTS;
DROP TABLE shortproduct CASCADE CONSTRAINTS;
DROP TABLE sub_product CASCADE CONSTRAINTS;

select * from 

/* Drop Sequences */

DROP SEQUENCE cseq;
DROP SEQUENCE eseq;
DROP SEQUENCE mseq;
DROP SEQUENCE oseq;
DROP SEQUENCE pseq;
DROP SEQUENCE qseq;
DROP SEQUENCE spseq;
DROP SEQUENCE aseq;
DROP SEQUENCE gseq;


/* Create Sequences */

create sequence mseq increment by 1 start with 1;
create sequence qseq increment by 1 start with 1;
create sequence gseq increment by 1 start with 1;
create sequence cseq increment by 1 start with 1;
create sequence oseq increment by 1 start with 1;
create sequence pseq increment by 1 start with 1;
create sequence spseq increment by 1 start with 1;
create sequence eseq increment by 1 start with 1;
create sequence odseq increment by 1 start with 1;
create sequence sposeq increment by 1 start with 1;

/* Create Tables */

CREATE TABLE address
(
   zip_num varchar2(7) NOT NULL,
   sido varchar2(30) NOT NULL,
   gugun varchar2(30) NOT NULL,
   dong varchar2(100) NOT NULL,
   zip_code varchar2(30),
   bunji varchar2(30)
);


CREATE TABLE admin
(
   id varchar2(20) NOT NULL,
   pwd varchar2(30) NOT NULL,
   name varchar2(20) NOT NULL,
   phone varchar2(20) NOT NULL,
   PRIMARY KEY (id)
);


CREATE TABLE cart
(
   cseq number(10) NOT NULL,
   id varchar2(50) NOT NULL,
   pseq number(10),
   quantity number(5) DEFAULT 1,
   result varchar2(1) DEFAULT '1',
   indate date DEFAULT sysdate,
   PRIMARY KEY (cseq)
);


CREATE TABLE event
(
   eseq number(10) NOT NULL,
   subject varchar2(100) NOT NULL,
   content varchar2(3000) NOT NULL,
   image varchar2(50),
   startdate date DEFAULT sysdate,
   enddate date,
   state number(1) DEFAULT 1,
   PRIMARY KEY (eseq)
);


CREATE TABLE member
(
   mseq number(10) NOT NULL,
   id varchar2(50) NOT NULL UNIQUE,
   pwd varchar2(20) NOT NULL,
   phone varchar2(13) NOT NULL,
   indate date DEFAULT sysdate,
   lastdate date DEFAULT sysdate,
   name varchar2(15) NOT NULL,
   memberkind number(1) DEFAULT 1,
   PRIMARY KEY (mseq)
);

select * from Myaddress
CREATE TABLE Myaddress
(
   mseq number(10) NOT NULL,
   address varchar2(100) NOT NULL,
   zip_num varchar2(7),
   PRIMARY KEY (mseq)
);



CREATE TABLE non_member
(
	mseq number(10) NOT NULL,
   id varchar2(50) NOT NULL,
   password varchar2(20) NOT NULL,
   phone varchar2(13) NOT NULL,
   memberkind number(1) DEFAULT 2,
   PRIMARY KEY (id, password)
);

select * from member


CREATE TABLE orders
(
   oseq number(10) NOT NULL,
   id varchar2(50) NOT NULL,
   indate  date DEFAULT sysdate,
   memberkind number(1) NOT NULL,
   PRIMARY KEY (oseq)
);


CREATE TABLE order_detail
(
   odseq number(10) NOT NULL,
   oseq number(10),
   pseq number(10),
   quantity number(5) DEFAULT 1,
   result number(1) DEFAULT '1',
   PRIMARY KEY (odseq)
);

CREATE TABLE product
(
	pseq number(10) NOT NULL,
	pname varchar2(100) NOT NULL,
	price1 number(10) NOT NULL,
	price2 number(10) NOT NULL,
	price3 number(10) NOT NULL,
	kind1 varchar2(5) NOT NULL,
	kind2 varchar2(3) NOT NULL,
	kind3 varchar2(3) NOT NULL,
	indate  date DEFAULT sysdate NOT NULL,
	content varchar2(100),
	image varchar2(50),
	useyn number(1) DEFAULT 1 NOT NULL,
	PRIMARY KEY (pseq)
);



CREATE TABLE qna
(
   qseq number(30) NOT NULL,
   subject varchar2(30) NOT NULL,
   content varchar2(50) NOT NULL,
   indate date DEFAULT sysdate,
   id varchar2(50) NOT NULL,
   reply varchar2(30),
   rep char DEFAULT '1',
   readcount number(5),
   pass number(4) NOT NULL,
   PRIMARY KEY (qseq)
);


CREATE TABLE sub_product
(
   spseq number(10) NOT NULL,
   sname varchar2(30) NOT NULL,
   kind1 number(5),
   kind2 varchar2(3) DEFAULT '0-0',
   addprice number(5) NOT NULL,
   image varchar2(50),
   PRIMARY KEY (spseq)
);


create table subproduct_order(
	sposeq number(10) not null,
	cseq number(10) not null,
	spseq number(10) not null,
	mseq number(10) not null,
	oseq number(10) not null,
	odseq number(10) not null,
	sname varchar2(30) not null,
	addprice number(5) not null,
	primary key(sposeq)
);

select * from SUB_PRODUCT;

drop view cart_view
create or replace view cart_view
as
select  c.cseq, c.id, m.name as mname, c.pseq, p.pname as pname, p.image, p.kind1, p.kind3,
	c.quantity, p.price1, c.result,  c.indate 
from cart c, product p, member m   
where  c.pseq = p.pseq and m.id = c.id;

drop view order_view
create or replace view order_view
as
select d.odseq, o.oseq, o.id, o.indate, d.pseq, d.quantity,  d.result, 
m.name as mname, m.phone, p.pname as pname, p.price1
from orders o, order_detail d, member m, product p
where o.oseq = d.oseq and o.id = m.id and d.pseq = p.pseq;


select*from sub_product
drop table sub_product
delete from SUBPRODUCT_order
select*from cart
select*from sub_product
select*from orders
select*from order_detail
select * from cart_view;
select * from order_view;
select * from member
select * from myaddress

/* Create Foreign Keys */

ALTER TABLE cart
   ADD FOREIGN KEY (id)
   REFERENCES member (id)
;


ALTER TABLE Myaddress
   ADD FOREIGN KEY (mseq)
   REFERENCES member (mseq)
;


ALTER TABLE qna
   ADD FOREIGN KEY (id)
   REFERENCES member (id)
;


ALTER TABLE order_detail
   ADD FOREIGN KEY (oseq)
   REFERENCES orders (oseq)
;


ALTER TABLE cart
   ADD FOREIGN KEY (pseq)
   REFERENCES product (pseq)
;


ALTER TABLE order_detail
   ADD FOREIGN KEY (pseq)
   REFERENCES product (pseq)


select * from member;

select * from order_view;
select * from orders;
select * from cart;
select * from cart_view;
select * from ADMIN

delete from SUBPRODUCT_ORDER

