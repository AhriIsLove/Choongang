--------------------------------------------------------
--  DDL for Table MVC_BOARD
--------------------------------------------------------

  CREATE TABLE "SCOTT"."MVC_BOARD" 
   (	"BID" NUMBER(4,0)  Primary KEY, 
	"BNAME" VARCHAR2(20 BYTE), 
	"BTITLE" VARCHAR2(100 BYTE), 
	"BCONTENT" VARCHAR2(300 BYTE), 
	"BDATE" DATE DEFAULT sysDate, 
	"BHIT" NUMBER(4,0) DEFAULT 0, 
	"BGROUP" NUMBER(4,0), 
	"BSTEP" NUMBER(4,0), 
	"BINDENT" NUMBER(4,0)
   ) ; 

Insert into SCOTT.MVC_BOARD (BID,BNAME,BTITLE,BCONTENT,BDATE,BHIT,BGROUP,BSTEP,BINDENT) values (1,'111','222','333',to_date('25/06/09','RR/MM/DD'),1,1,0,0);
Insert into SCOTT.MVC_BOARD (BID,BNAME,BTITLE,BCONTENT,BDATE,BHIT,BGROUP,BSTEP,BINDENT) values (2,'����','�Խ���','���� ��� ����',to_date('25/06/09','RR/MM/DD'),7,2,0,0);
Insert into SCOTT.MVC_BOARD (BID,BNAME,BTITLE,BCONTENT,BDATE,BHIT,BGROUP,BSTEP,BINDENT) values (3,'�亯_����','�Խ���','���� ��� ����',to_date('25/06/09','RR/MM/DD'),5,2,1,1);
Insert into SCOTT.MVC_BOARD (BID,BNAME,BTITLE,BCONTENT,BDATE,BHIT,BGROUP,BSTEP,BINDENT) values (4,'�����˽�Ÿ','�ȿ���','����������',to_date('25/06/09','RR/MM/DD'),11,4,0,0);
Insert into SCOTT.MVC_BOARD (BID,BNAME,BTITLE,BCONTENT,BDATE,BHIT,BGROUP,BSTEP,BINDENT) values (23,'�����','qqq','�Է�',to_date('25/06/12','RR/MM/DD'),4,23,0,0);
Insert into SCOTT.MVC_BOARD (BID,BNAME,BTITLE,BCONTENT,BDATE,BHIT,BGROUP,BSTEP,BINDENT) values (41,'������1','õ���ǻ��1','���������� 1��',to_date('25/07/07','RR/MM/DD'),8,41,0,0);
Insert into SCOTT.MVC_BOARD (BID,BNAME,BTITLE,BCONTENT,BDATE,BHIT,BGROUP,BSTEP,BINDENT) values (25,'������','�ϳ׸���','�� ���� ���⿡',to_date('25/06/12','RR/MM/DD'),9,25,0,0);
Insert into SCOTT.MVC_BOARD (BID,BNAME,BTITLE,BCONTENT,BDATE,BHIT,BGROUP,BSTEP,BINDENT) values (42,'�ż���','�亯 õ���ǻ��','���������� 1�� ������
�����ؿ�',to_date('25/07/07','RR/MM/DD'),2,41,1,1);
Insert into SCOTT.MVC_BOARD (BID,BNAME,BTITLE,BCONTENT,BDATE,BHIT,BGROUP,BSTEP,BINDENT) values (43,'������','�亯�� �亯','�ż��Ͼ�
���������� 1�� ������
�����ؿ並 ������
������',to_date('25/07/07','RR/MM/DD'),1,41,2,2);
