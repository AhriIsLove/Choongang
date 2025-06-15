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
Insert into SCOTT.MVC_BOARD (BID,BNAME,BTITLE,BCONTENT,BDATE,BHIT,BGROUP,BSTEP,BINDENT) values (2,'강공','게시판','인제 들어 가나',to_date('25/06/09','RR/MM/DD'),7,2,0,0);
Insert into SCOTT.MVC_BOARD (BID,BNAME,BTITLE,BCONTENT,BDATE,BHIT,BGROUP,BSTEP,BINDENT) values (3,'답변_강공','게시판','인제 들어 가나',to_date('25/06/09','RR/MM/DD'),5,2,1,1);
Insert into SCOTT.MVC_BOARD (BID,BNAME,BTITLE,BCONTENT,BDATE,BHIT,BGROUP,BSTEP,BINDENT) values (4,'케이팝스타','안예은','연결하지요',to_date('25/06/09','RR/MM/DD'),11,4,0,0);
Insert into SCOTT.MVC_BOARD (BID,BNAME,BTITLE,BCONTENT,BDATE,BHIT,BGROUP,BSTEP,BINDENT) values (23,'김소현','qqq','입력',to_date('25/06/12','RR/MM/DD'),4,23,0,0);
Insert into SCOTT.MVC_BOARD (BID,BNAME,BTITLE,BCONTENT,BDATE,BHIT,BGROUP,BSTEP,BINDENT) values (41,'옥주현1','천년의사랑1','나가수에서 1등',to_date('25/07/07','RR/MM/DD'),8,41,0,0);
Insert into SCOTT.MVC_BOARD (BID,BNAME,BTITLE,BCONTENT,BDATE,BHIT,BGROUP,BSTEP,BINDENT) values (25,'정선아','암네리스','난 지금 여기에',to_date('25/06/12','RR/MM/DD'),9,25,0,0);
Insert into SCOTT.MVC_BOARD (BID,BNAME,BTITLE,BCONTENT,BDATE,BHIT,BGROUP,BSTEP,BINDENT) values (42,'신성록','답변 천년의사랑','나가수에서 1등 했지요
축하해요',to_date('25/07/07','RR/MM/DD'),2,41,1,1);
Insert into SCOTT.MVC_BOARD (BID,BNAME,BTITLE,BCONTENT,BDATE,BHIT,BGROUP,BSTEP,BINDENT) values (43,'엄기준','답변의 답변','신성록씨
나가수에서 1등 했지요
축하해요를 썼지요
ㅎㅎㅎ',to_date('25/07/07','RR/MM/DD'),1,41,2,2);
