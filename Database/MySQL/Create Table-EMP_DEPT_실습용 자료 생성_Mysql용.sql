DROP TABLE EMP;
DROP TABLE DEPT;
DROP TABLE BONUS;
DROP TABLE SALGRADE;
DROP TABLE DIVISION;

CREATE TABLE DEPT
        (DEPTNO int(2)   PRIMARY KEY,
         DNAME VARCHAR(14),
	     LOC   VARCHAR(13) ) ;

CREATE TABLE DIVISION
    (    DNO       int(2)   PRIMARY KEY,
         DNAME  VARCHAR(20),
         phone    VARCHAR(15),
         POSITION VARCHAR(20)
 ) ;

CREATE TABLE EMP
    (EMPNO int(4)  ,
	 ENAME VARCHAR(10),
 	 JOB   VARCHAR(9),
	 MGR   int(4),
	 HIREDATE DATE,
	 SAL   int(7),
	 COMM  int(7),
	 DEPTNO int(2),
     PRIMARY KEY (EMPNO), 
     FOREIGN KEY (DEPTNO) REFERENCES dept (deptno)
 );
CREATE TABLE BONUS
	(ENAME VARCHAR(10),
	 JOB   VARCHAR(9),
	 SAL   int,
	 COMM  int) ;
CREATE TABLE SALGRADE
    (GRADE int,
	 LOSAL int,
	 HISAL int );

INSERT INTO DEPT VALUES (10,'ACCOUNTING','NEW YORK');
INSERT INTO DEPT VALUES (20,'RESEARCH','DALLAS');
INSERT INTO DEPT VALUES (30,'SALES','CHICAGO');
INSERT INTO DEPT VALUES (40,'OPERATIONS','BOSTON');

INSERT INTO DIVISION VALUES (10,'인사','010-2236-7890','과장');
INSERT INTO DIVISION VALUES (20,'회계','010-2536-7890','대리');
INSERT INTO DIVISION VALUES (30,'경영','010-7236-7890','차장');
INSERT INTO DIVISION VALUES (40,'구매','010-9236-7890','부장');


INSERT INTO EMP VALUES
(7369,'SMITH','CLERK',    7902,'1980-12-17',800,NULL,20);


INSERT INTO EMP VALUES
(7499,'ALLEN','SALESMAN', 7698,'1981-02-20',1600,300,30);
INSERT INTO EMP VALUES
(7521,'WARD','SALESMAN',  7698,'1981-02-22',1250,500,30);
INSERT INTO EMP VALUES
(7566,'JONES','MANAGER',  7839, '1981-04-02' ,2975,NULL,20);
INSERT INTO EMP VALUES
(7654,'MARTIN','SALESMAN',7698,'1981-09-28',1250,1400,30);
INSERT INTO EMP VALUES
(7698,'BLAKE','MANAGER',  7839,'1981-05-02', 2850,NULL,30);
INSERT INTO EMP VALUES
(7782,'CLARK','MANAGER',  7839,'1981-06-09' ,2450,NULL,10);
INSERT INTO EMP VALUES
(7788,'SCOTT','ANALYST',  7566,'1981-11-17',3000,NULL,20);
INSERT INTO EMP VALUES
(7839,'KING','PRESIDENT', NULL,'1982-04-02',5000,NULL,10);
INSERT INTO EMP VALUES
(7844,'TURNER','SALESMAN',7698,'1985-06-02',1500,0,30);
INSERT INTO EMP VALUES
(7876,'ADAMS','CLERK',    7788,'1991-03-02',1100,NULL,20);
INSERT INTO EMP VALUES
(7900,'JAMES','CLERK',    7698,'1999-04-02',950,NULL,30);
INSERT INTO EMP VALUES
(7902,'FORD','ANALYST',   7566,'1995-12-02',3000,NULL,20);
INSERT INTO EMP VALUES
(7934,'MILLER','CLERK',   7782,'1996-08-02',1300,NULL,10);

INSERT INTO SALGRADE VALUES (1, 700,1200);
INSERT INTO SALGRADE VALUES (2,1201,1400);
INSERT INTO SALGRADE VALUES (3,1401,2000);
INSERT INTO SALGRADE VALUES (4,2001,3000);
INSERT INTO SALGRADE VALUES (5,3001,9999);

COMMIT;






/*************************************************************************
* 기존에 저장되 있는 STUDENT, PROFESSOR, DEPARTMENT, SALGRADE 테이블 삭제
*************************************************************************/

DROP TABLE STUDENT;
DROP TABLE PROFESSOR;
DROP TABLE DEPARTMENT;
DROP TABLE SALGRADE;


/*************************************************************************
*                         STUDNET 테이블 생성
*************************************************************************/
CREATE TABLE STUDENT
        (STUDNO int(5)    PRIMARY KEY,
         NAME VARCHAR(20),
         USERID VARCHAR(10),
         GRADE VARCHAR(1),
         IDNUM VARCHAR(13),
         BIRTHDATE DATE,
         TEL VARCHAR(13),
         HEIGHT int(5),
         WEIGHT int(5),
         DEPTNO int(4),
         PROFNO int(4));


/*************************************************************************
*                        STUDNET 테이블 데이터 입력
*************************************************************************/
INSERT INTO STUDENT VALUES
        (10101, '전인하', 'jun123', '4', '7907021369824',
        '1979-06-02', '051)781-2158', 176, 72, '101',9903);

INSERT INTO STUDENT VALUES
        (20101, '이동훈', 'Dals', '1', '8312101128467',
         '1983-12-02', '055)426-1752', 172, 64, '201',NULL);

INSERT INTO STUDENT VALUES
        (10102, '박미경', 'ansel414', '1', '8405162123648',
        '1989-10-02', '055)261-8947', 168, 52, '101',NULL);

INSERT INTO STUDENT VALUES
        (10103, '김영균', 'mandu', '3', '8103211063421',
        '1981-03-02', '051)824-9637', 170, 88 ,'101',9906);

INSERT INTO STUDENT VALUES
        (20102, '박동진', 'Ping2', '1', '8511241639826',
        '1989-12-02', '051)742-6384', 182, 70, '201',NULL);

INSERT INTO STUDENT VALUES
        (10201, '김진영', 'simply', '2', '8206062186327',
        '1989-06-02', '055)419-6328', 164, 48, '102',9905);
INSERT INTO STUDENT VALUES
        (10104, '지은경', 'Gomo00', '2', '8004122298371',
        '1990-06-02', '055)418-9627', 161, 42, '101',9907);

INSERT INTO STUDENT VALUES
        (10202, '오유석', 'yousuk', '4', '7709121128379',
        '1995-08-02', '051)724-9618', 177, 92, '102',9905);

INSERT INTO STUDENT VALUES
        (10203, '하나리', 'hanal', '1', '8501092378641',
       '1982-09-02', '055)296-3784', 160, 68, '102',NULL);

INSERT INTO STUDENT VALUES
        (10105, '임유진', 'YouJin12', '2', '8301212196482',
        '1985-03-02', '02)312-9838', 171, 54, '101',9907);

INSERT INTO STUDENT VALUES
        (10106, '서재진', 'seolly', '1', '8511291186273',
        '1985-02-02', '051)239-4861', 186, 72, '101',NULL);

INSERT INTO STUDENT VALUES
        (10204, '윤진욱', 'Samba7', '3', '7904021358671',
        '1995-07-02', '053)487-2698', 171, 70, '102',9905);
 
INSERT INTO STUDENT VALUES
        (10107, '이광훈', 'huriky', '4', '8109131276431',
       '1990-08-02', '055)736-4981', 175, 92, '101',9903);

INSERT INTO STUDENT VALUES
        (20103, '김진경', 'lovely', '2', '8302282169387',
        '1989-09-02', '052)175-3941', 166, 51, '201',9902);

INSERT INTO STUDENT VALUES
        (20104, '조명훈', 'Rader214', '1', '8412141254963',
        '1993-10-02','02)785-6984', 184, 62, '201',NULL);

INSERT INTO STUDENT VALUES
        (10108, '류민정', 'cleanSky', '2', '8108192157498',
        '1985-12-02',  '055)248-3679', 162, 72, '101',9907);

 

/*************************************************************************
*                         PROFESSOR 테이블 생성
*************************************************************************/
CREATE TABLE PROFESSOR
        (PROFNO int(4)    PRIMARY KEY,
         NAME VARCHAR(10),
         USERID VARCHAR(10),
         POSITION VARCHAR(20),
         SAL int(10),
         HIREDATE DATE,
         COMM int(2),
         DEPTNO int(4));


/*************************************************************************
*                        ROFESSOR 테이블 데이터 입력
*************************************************************************/
INSERT INTO PROFESSOR VALUES
        (9901, '김도훈', 'capool', '교수', 500,
       '1982-01-24', 20, 101);

INSERT INTO PROFESSOR VALUES
        (9902, '이재우', 'sweat413', '조교수', 320,
        '1992-04-23', NULL, 201);

INSERT INTO PROFESSOR VALUES
        (9903, '성연희', 'Pascal', '조교수', 360,
        '1982-04-23', 15, 101);

INSERT INTO PROFESSOR VALUES
        (9904, '염일웅', 'Blue77', '전임강사', 240,
        '1980-09-23', NULL, 102);

INSERT INTO PROFESSOR VALUES
        (9905, '권혁일', 'refresh', '교수', 450,
        '1986-08-01', 25, 102);

INSERT INTO PROFESSOR VALUES
        (9906, '이만식', 'Pocari', '부교수', 420,
        '1988-08-01', NULL, 101);

INSERT INTO PROFESSOR VALUES
        (9907, '전은지', 'totoro', '전임강사', 210,
        '2001-10-13', NULL, 101);

INSERT INTO PROFESSOR VALUES
        (9908, '남은혁', 'Bird13', '부교수', 400,
        '1990-12-11', 17, 202);


/*************************************************************************
*                          DEPARTMENT 테이블 생성
*************************************************************************/
CREATE TABLE DEPARTMENT
        (DEPTNO int(4)    PRIMARY KEY,
         DNAME VARCHAR(30),
         COLLEGE int(4),
         LOC VARCHAR(10));

/*************************************************************************
*                        DEPARTMENT 테이블 데이터 입력
*************************************************************************/
INSERT INTO DEPARTMENT VALUES
        (101, '컴퓨터공학과', 100, '1호관');

INSERT INTO DEPARTMENT VALUES
        (102, '멀티미디어학과', 100, '2호관');

INSERT INTO DEPARTMENT VALUES
        (201, '전자공학과', 200, '3호관');

INSERT INTO DEPARTMENT VALUES
        (202, '기계공학과', 200, '4호관');

INSERT INTO DEPARTMENT VALUES
        (100, '정보미디어학부', 10, NULL);

INSERT INTO DEPARTMENT VALUES
        (200, '메카트로닉스학부', 10, NULL);

INSERT INTO DEPARTMENT VALUES
        (10, '공과대학', NULL, NULL);


/*************************************************************************
*                          SALGRADE 테이블 생성
*************************************************************************/
CREATE TABLE SALGRADE
        (GRADE int(2)     PRIMARY KEY,
         LOSAL int(5),
         HISAL int(5));

/*************************************************************************
*                         SALGRADE 테이블 데이터 입력
*************************************************************************/
INSERT INTO SALGRADE VALUES (1, 100, 300);
INSERT INTO SALGRADE VALUES (2, 301, 400);
INSERT INTO SALGRADE VALUES (3, 401, 500);


COMMIT;

