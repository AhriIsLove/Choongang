-------------------------------------------------------------------------------
-----      INDEX      ***
--  인덱스는 SQL 명령문의 처리 속도를 향상(*) 시키기 위해 칼럼에 대해 생성하는 객체
--  인덱스는 포인트를 이용하여 테이블에 저장된 데이터를 랜덤 액세스하기 위한 목적으로 사용
--  [1]인덱스의 종류
--   1)고유 인덱스 : 유일한 값을 가지는 칼럼에 대해 생성하는 인덱스로 모든 인덱스 키는
--           테이블의 하나의 행과 연결
CREATE UNIQUE INDEX idx_dept_name
ON department(dname);

INSERT INTO department VALUES(300, '이과대학', 10, 'kkk');
INSERT INTO department(deptno, dname, college, loc) VALUES(301, '이과대학', 10, 'kkk2'); -- UNIQUE CONSTRAINT violated

-- 비고유 인덱스 birthdate  --> constraint  X   , 성능에만 영향 미침 
-- 2)비고유 인덱스
-- 문) 학생 테이블의 birthdate 칼럼을 비고유 인덱스로 생성하여라
CREATE INDEX idx_stud_birthdate
ON student(birthdate);

INSERT INTO student(studno, name, idnum, birthdate) VALUES(30102, '김유신', '8012301036614', '84/09/16');

SELECT * FROM student WHERE birthdate = '84/09/16'; -- 'F10' -> TABLE ACCESS -> INDEX
SELECT * FROM student WHERE grade = 2; -- 'F10' -> TABLE ACCESS -> 인덱스 없음

CREATE INDEX idx_stud_dno_grade
ON student(deptno, grade);

SELECT * FROM student WHERE grade = 2 AND deptno = 101;
SELECT * FROM student WHERE deptno = 101 AND grade = 2;
SELECT * FROM student WHERE deptno = 101 AND tel LIKE '%5%' AND grade = 2;

--- Optimizer
--- 1) RBO  2) CBO
-- RBO 변경
-- OPTIMIZER 모드 확인
SHOW PARAMETER OPTIMIZER_MODE;
--SELECT NAME, VALUE, ISDEFAULT, ISMODIFIED, DESCRIPTION
--FROM V$SYSTEM_PARAMETER
--WHERE NAME LIKE '%optimizer_mode%';

--세션 상에서 변경할 때
ALTER SESSION SET optimizer_mode = rule; -- RBO
ALTER SESSION SET optimizer_mode = CHOOSE; -- CBO
ALTER SESSION SET optimizer_mode = first_rows; -- CBO
ALTER SESSION SET optimizer_mode = ALL_ROWS; -- CBO : DEFAULT

SELECT ename FROM emp WHERE ename LIKE 'S%';

-- [2]인덱스가 효율적인 경우 
--   1) WHERE 절이나 조인 조건절에서 자주 사용되는 칼럼
--   2) 전체 데이터중에서 10~15%이내의 데이터를 검색하는 경우
--   3) 두 개 이상의 칼럼이 WHERE절이나 조인 조건에서 자주 사용되는 경우
--   4) 테이블에 저장된 데이터의 변경이 드문 경우
--   5) 열에 널 값이 많이 포함된 경우, 열에 광범위한 값이 포함된경우
---------------------------------------------------------------
-- 학생 테이블에 생성된 PK_DEPTNO 인덱스를 재구성
ALTER INDEX PK_DEPTNO REBUILD; -- 인덱스 재구성

--  1. Index 조회
SELECT index_name, table_name, column_name
FROM user_ind_columns;

-- 2. Index 생성  emp(job)
CREATE INDEX idx_emp_job ON emp(job);

-- 3. 조회
SELECT * FROM emp WHERE job = 'MANAGER'; -- job INDEX O
SELECT * FROM emp WHERE job <> 'MANAGER'; -- job INDEX X
SELECT * FROM emp WHERE job LIKE '%NA%'; -- job INDEX X
SELECT * FROM emp WHERE job LIKE 'MA%'; -- job INDEX O
SELECT * FROM emp WHERE UPPER(job) = 'MANAGER'; -- job INDEX X

--   5)함수 기반 인덱스(FBI) function based index
--      오라클 8i 버전부터 지원하는 새로운 형태의 인덱스로 칼럼에 대한 연산이나 함수의 계산 결과를 
--      인덱스로 생성 가능
--      UPPER(column_name) 또는 LOWER(column_name) 키워드로 정의된
--      함수 기반 인덱스를 사용하면 대소문자 구분 없이 검색
CREATE INDEX idx_emp_job_upper ON emp(UPPER(job));
SELECT * FROM emp WHERE UPPER(job) = 'MANAGER'; -- job INDEX X -> O

----------------------------------------------------------------------------------------------------------------
-- 트랜잭션 개요  ***
-- 관계형 데이터베이스에서 실행되는 여러 개의 SQL명령문을 하나의 논리적 작업 단위로 처리하는 개념
-- COMMIT : 트랜잭션의 정상적인 종료
--          트랜잭션내의 모든 SQL 명령문에 의해 변경된 작업 내용을 디스크에 영구적으로 저장하고 
--          트랜잭션을 종료
--          해당 트랜잭션에 할당된 CPU, 메모리 같은 자원이 해제
--          서로 다른 트랜잭션을 구분하는 기준
--          COMMIT 명령문 실행하기 전에 하나의 트랜잭션 변경한 결과를
--          다른 트랜잭션에서 접근할 수 없도록 방지하여 일관성 유지
 
-- ROLLBACK : 트랜잭션의 전체 취소
--            트랜잭션내의 모든 SQL 명령문에 의해 변경된 작업 내용을 전부 취소하고 트랜잭션을 종료
--            CPU,메모리 같은 해당 트랜잭션에 할당된 자원을 해제, 트랜잭션을 강제 종료
----------------------------------------------------------------------------------------------------------------

----------------------------------
-- SEQUENCE ***
-- 유일한 식별자
-- 기본 키 값을 자동으로 생성하기 위하여 일련번호 생성 객체
-- 예를 들면, 웹 게시판에서 글이 등록되는 순서대로 번호를 하나씩 할당하여 기본키로 지정하고자 할때 
-- 시퀀스를 편리하게 이용
-- 여러 테이블에서 공유 가능  -- > 일반적으로는 개별적 사용 
----------------------------------
-- 1) SEQUENCE 형식
--CREATE SEQUENCE sequence
--[INCREMENT BY n]
--[START WITH n]
--[MAXVALUE n | NOMAXVALUE]
--[MINVALUE n | NOMINVALUE]
--[CYCLE | NOCYCLE]
--[CACHE n | NOCACHE];
--INCREMENT BY n : 시퀀스 번호의 증가치로 기본은 1,  일반적으로 ?1 사용
--START WITH n : 시퀀스 시작번호, 기본값은 1
--MAXVALUE n : 생성 가능한 시퀀스의 최대값
--MAXVALUE n : 시퀀스 번호를 순환적으로 사용하는 cycle로 지정한 경우, MAXVALUE에 도달한 후 새로 시작하는 시퀀스값
--CYCLE | NOCYCLE : MAXVALUE 또는 MINVALUE에 도달한 후 시퀀스의 순환적인 시퀀스 번호의 생성 여부 지정
--CACHE n | NOCACHE : 시퀀스 생성 속도 개선을 위해 메모리에 캐쉬하는 시퀀스 개수, 기본값은 20

-- 2) SEQUENCE sample 예시
CREATE SEQUENCE sample_seq
INCREMENT BY 1 -- 1씩 증가
START WITH 10000; -- 10000부터

-- 시퀀스 사용(증가 O)
SELECT sample_seq.NEXTVAL FROM dual;
-- 시퀀스 확인(증가 X)
SELECT sample_seq.CURRVAL FROM dual;

-- 3) SEQUENCE sample 예시2 --> 실 사용 예시
CREATE SEQUENCE dept_dno_seq
INCREMENT BY 1
START WITH 76;

-- 4) SEQUENCE dept_dno_seq를 이용 dept_second 입력 --> 실 사용 예시
INSERT INTO dept_second VALUES(dept_dno_seq.NEXTVAL, 'Acounting', 'NEW YORK');
SELECT dept_dno_seq.CURRVAL FROM dual;
INSERT INTO dept_second VALUES(dept_dno_seq.NEXTVAL, '회계', '이대');
SELECT dept_dno_seq.CURRVAL FROM dual;
INSERT INTO dept_second VALUES(dept_dno_seq.NEXTVAL, '인사팀', '당산');
SELECT dept_dno_seq.CURRVAL FROM dual;

--MAX 전환(비권장)
INSERT INTO dept_second VALUES((SELECT MAX(DEPTNO) + 1 FROM dept_second), '경영팀', '대림');
SELECT MAX(DEPTNO) FROM dept_second;

INSERT INTO dept_second VALUES(dept_dno_seq.NEXTVAL, '인사팀8', '당산8'); -- 오류
SELECT dept_dno_seq.CURRVAL FROM dual;
INSERT INTO dept_second VALUES(dept_dno_seq.NEXTVAL, '인사팀8', '당산8');

-- 4) SEQUENCE 삭제
DROP SEQUENCE SAMPLE_SEQ;

--5)  Data 사전(시스템 카탈로그)에서 정보 조회
SELECT sequence_name, min_value, max_value, increment_by
FROM user_sequences;

------------------------------------------------------
----                   Table 조작                 ----
------------------------------------------------------
-- 1.Table 생성
CREATE TABLE address(
    id NUMBER(3),
    name VARCHAR2(50),
    addr VARCHAR2(100),
    phone VARCHAR2(30),
    email VARCHAR2(100)
);

INSERT INTO address VALUES (1, 'HGDONG', 'SEOUL', '123-4567', 'gdhong@naver.com');

---    Homework
-- 문1) address스키마/Data 유지하며     addr_second Table 생성
CREATE TABLE addr_second
AS
SELECT * FROM address;
-- 문2) address스키마 유지하며  Data 복제 하지 않고   addr_seven Table 생성
CREATE TABLE addr_seven
AS
SELECT * FROM address WHERE 0=1;
-- 문3) address(주소록) 테이블에서 id, name 칼럼만 복사하여 addr_third 테이블을 생성하여라
CREATE TABLE addr_third
AS
SELECT id, name FROM address;

------------------------------------------------------------------
-----     데이터 사전
-- 1. 사용자와 데이터베이스 자원을 효율적으로 관리하기 위한 다양한 정보를 저장하는 시스템 테이블의 집합
-- 2. 사전 내용의 수정은 오라클 서버만 가능
-- 3. 오라클 서버는 데이타베이스의 구조, 감사, 사용자 권한, 데이터 등의 변경 사항을 반영하기 위해
--    지속적 수정 및 관리
-- 4. 데이타베이스 관리자나 일반 사용자는 읽기 전용 뷰에 의해 데이터 사전의 내용을 조회만 가능
-- 5. 실무에서는 테이블, 칼럼, 뷰 등과 같은 정보를 조회하기 위해 사용

------------------------------------------------------------------

------------------------------------------------------------------
-----     데이터 사전 관리 정보
-- 1. 데이터베이스의 물리적 구조와 객체의 논리적 구조
-- 2. 오라클 사용자 이름과 스키마 객체 이름
-- 3. 사용자에게 부여된 접근 권한과 롤
-- 4. 무결성 제약조건에 대한 정보
-- 5. 칼럼별로 지정된 기본값
-- 6. 스키마 객체에 할당된 공간의 크기와 사용 중인 공간의 크기 정보
-- 7. 객체 접근 및 갱신에 대한 감사 정보
-- 8. 데이터베이스 이름, 버전, 생성날짜, 시작모드, 인스턴스 이름

------------------------------------------------------------------
-------     데이터 사전 종류
-- 1. USER_ : 객체의 소유자만 접근 가능한 데이터 사전 뷰
-- user_tables는 사용자가 소유한 테이블에 대한 정보를 조회할 수 있는 데이터 사전 뷰.
SELECT table_name FROM user_tables;
SELECT * FROM user_catalog;

-- 2. ALL_    : 자기 소유 또는 권한을 부여 받은 객체만 접근 가능한 데이터 사전 뷰
SELECT owner, table_name FROM all_tables;

-- 3. DBA_   : 데이터베이스 관리자만 접근 가능한 데이터 사전 뷰
SELECT owner, table_name FROM dba_tables;

------------------------------------------------------------------------------
--   11. View 
------------------------------------------------------------------------------
-- View : 하나 이상의 기본 테이블이나 다른 뷰를 이용하여 생성되는 가상 테이블
--        뷰는 데이터딕셔너리 테이블에 뷰에 대한 정의만 저장
--       장점 : 1) 보안 
--             2) 고급기술자가  초급기술자 SQL 능력을 Cover
--       단점 : Performance(성능)은 더 저하
------------------------------------------------------------------------------
CREATE OR REPLACE VIEW view_professor
AS
SELECT profno, name, userid, position, hiredate, deptno FROM professor;

-- 조회하는 순간 professor가 받아서 전체적으로 실행
SELECT * FROM view_professor;

--제약조건에 걸리지 않는다면 뷰를 통한 입력 가능
INSERT INTO view_professor VALUES (2000, 'view', 'userid', 'position', sysdate, 101);
SELECT profno, name, userid, position, hiredate, deptno FROM professor;

INSERT INTO view_professor VALUES (2001, 'view1', 'userid1', '', sysdate, 101);

-- 현장work01 --> VIEW 이름 v_emp_sample  : emp(empno , ename , job, mgr,deptno)
CREATE OR REPLACE VIEW v_emp_sample
AS
SELECT empno, ename, job, mgr, deptno FROM emp;

INSERT INTO v_emp_sample (empno, ename, job, mgr, deptno) VALUES (2001, 'userid2', 'position2', 7839, 10);

--복합 뷰
--v_emp_complex(emp + dept)
CREATE OR REPLACE VIEW v_emp_complex
AS
SELECT * 
FROM emp NATURAL JOIN dept; --네츄럴조인 = 이너조인

INSERT INTO v_emp_complex(empno, ename) VALUES(1500, '홍길동');
INSERT INTO v_emp_complex(deptno, dname) VALUES(87, '전산3팀'); -- 오류 : dept 관련 칼럼 있음

INSERT INTO v_emp_complex(empno, ename, deptno, dname, loc) VALUES (1600, '홍길동', 87, '공무팀', '낙성대'); -- 오류 : dept 관련 칼럼 있음
INSERT INTO v_emp_complex(empno, ename, deptno) VALUES (1600, '홍길동', 86); -- 오류 : dept 관련 칼럼 있음
INSERT INTO v_emp_complex(empno, ename, sal) VALUES (1600, '홍길동', 1200);-- VIEW에는 없어도 emp관련 칼럼만 있으면 가능

--테이블 순서를 바꿔서 네츄럴조인을 한 경우
CREATE OR REPLACE VIEW v_emp_complex2
AS
SELECT * 
FROM dept NATURAL JOIN emp; --순서를 바꿔도?(외래키 판단으로 emp가 무조건 메인 테이블인듯)
INSERT INTO v_emp_complex2(deptno, dname) VALUES(87, '전산3팀'); -- 오류

--테이블 순서를 바꿔서 이너조인을 한 경우
CREATE OR REPLACE VIEW v_emp_complex4
AS
SELECT d.deptno, d.dname, d.loc, e.empno, e.ename, e.job
FROM dept d, emp e
WHERE d.deptno = e.deptno; --순서를 바꿔도?(외래키 판단으로 emp가 무조건 메인 테이블인듯)
INSERT INTO v_emp_complex4(empno, ename) VALUES(1601, '홍길동1');
INSERT INTO v_emp_complex4(deptno, dname, loc) VALUES (78, '공무팀', '낙성대'); -- 오류

--테이블 순서를 바꿔서 아우터조인을 한 경우
CREATE OR REPLACE VIEW v_emp_complex5
AS
SELECT d.deptno, d.dname, d.loc, e.empno, e.ename, e.job
FROM emp e FULL OUTER JOIN dept d
ON e.deptno = d.deptno;
INSERT INTO v_emp_complex5(empno, ename) VALUES(1602, '홍길동1');
INSERT INTO v_emp_complex5(deptno, dname, loc) VALUES (78, '공무팀', '낙성대'); -- 오류

------------     View  HomeWork     ----------------------------------------------------
---문1)  학생 테이블에서 101번 학과 학생들의 학번, 이름, 학과 번호로 정의되는 단순 뷰를 생성
---     뷰 명 :  v_stud_dept101
CREATE OR REPLACE VIEW v_stud_dept101
AS
SELECT studno, name, deptno
FROM student
WHERE deptno = 101;
--문2) 학생 테이블과 부서 테이블을 조인하여 102번 학과 학생들의 학번, 이름, 학년, 학과 이름으로 정의되는 복합 뷰를 생성
--      뷰 명 :   v_stud_dept102
CREATE OR REPLACE VIEW v_stud_dept102
AS
SELECT studno, name, grade, dname
FROM student s, department d
WHERE s.deptno = d.deptno
AND s.deptno = 102;
--문3)  교수 테이블에서 학과별 평균 급여와     총계로 정의되는 뷰를 생성
--  뷰 명 :  v_prof_avg_sal       Column 명 :   avg_sal      sum_sal
CREATE OR REPLACE VIEW v_prof_avg_sal
AS 
SELECT avg(sal) AS avg_sal, sum(sal) AS sum_sal
FROM professor
GROUP BY deptno;

--------------------------------------------------------------------------
---- 계층적 질의문
--------------------------------------------------------------------------
-- 1. 관계형 데이터 베이스 모델은 평면적인 2차원 테이블 구조
-- 2. 관계형 데이터 베이스에서 데이터간의 부모 관계를 표현할 수 있는 칼럼을 지정하여 
--    계층적인 관계를 표현
-- 3. 하나의 테이블에서 계층적인 구조를 표현하는 관계를 순환관계(recursive relationship)
-- 4. 계층적인 데이터를 저장한 칼럼으로부터 데이터를 검색하여 계층적으로 출력 기능 제공
--------------------------------------------------------------------------
-- 사용법
-- SELECT 명령문에서 START WITH와 CONNECT BY 절을 이용
-- 계층적 질의문에서는 계층적인 출력 형식과 시작 위치 제어
-- 출력 형식은  top-down 또는 bottom-up
-- 참고) CONNECT BY PRIOR 및 START WITH절은 ANSI SQL 표준이 아님
--------------------------------------------------------------------------
-- 문1) 계층적 질의문을 사용하여 부서 테이블에서 학과,학부,단과대학을 검색하여 단대,학부
-- 학과순으로 top-down 형식의 계층 구조로 출력하여라. 단, 시작 데이터는 10번 부서
SELECT level, deptno, dname, college
FROM department
START WITH deptno = 10
CONNECT BY PRIOR deptno = college; -- Top-Down

-- 문2)계층적 질의문을 사용하여 부서 테이블에서 학과,학부,단과대학을 검색하여 학과,학부
-- 단대 순으로 bottom-up 형식의 계층 구조로 출력하여라. 단, 시작 데이터는 102번 부서이다
SELECT level, deptno, dname, college
FROM department
START WITH deptno = 102
CONNECT BY PRIOR college = deptno; -- Bottom-Up

--- 문3) 계층적 질의문을 사용하여 부서 테이블에서 부서 이름을 검색하여 단대, 학부, 학과순의
---         top-down 형식으로 출력하여라. 단, 시작 데이터는 ‘공과대학’이고,
---        각 LEVEL(레벨)별로 우측으로 2칸 이동하여 출력
SELECT LPAD(' ', (LEVEL-1)*2) || dname AS "조직도"
FROM department
START WITH dname = '공과대학'
CONNECT BY PRIOR deptno = college;

--------------------------------------------------------------------------------
---  TableSpace  
---  정의  :데이터베이스 오브젝트 내 실제 데이터를 저장하는 공간
--         이것은 데이터베이스의 물리적인 부분이며, 세그먼트로 관리되는 모든 DBMS에 대해 
--         저장소(세그먼트)를 할당
--------------------------------------------------------------------------------
-- 기본으로 설정된 경로는 
-- 1. TableSpace 생성
CREATE TABLESPACE user1 DATAFILE 'C:\oraclexe\backup\tablespace\user1.ora' SIZE 100M;
CREATE TABLESPACE user2 DATAFILE 'C:\oraclexe\backup\tablespace\user2.ora' SIZE 100M;
CREATE TABLESPACE user3 DATAFILE 'C:\oraclexe\backup\tablespace\user3.ora' SIZE 100M;
CREATE TABLESPACE user4 DATAFILE 'C:\oraclexe\backup\tablespace\user4.ora' SIZE 100M;

-- 2. 인덱스의 테이블 스페이스 변경
--    1) 테이블의 INDEX의 테이블 스페이스 조회
SELECT index_name, table_name, tablespace_name FROM user_indexes;-- 인덱스 조회
ALTER INDEX pk_religionno3 REBUILD TABLESPACE user1;

-- 3. 테이블의 테이블 스페이스 변경
--    1) Table의 테이블 스페이스 조회
SELECT table_name, tablespace_name FROM user_tables;-- 테이블 조회
ALTER TABLE job3 MOVE TABLESPACE user2;

CREATE TABLE "SCOTT"."DEPT_THIRD" 
   (	"DEPTNO" NUMBER(2,0) NOT NULL ENABLE, 
	"DNAME" VARCHAR2(14 BYTE), 
	"LOC" VARCHAR2(13 BYTE), 
	 CONSTRAINT "DEPT_THIRD_PK" PRIMARY KEY ("DEPTNO"),
  TABLESPACE "user3");

-- Oracle 전체 Backup  (scott)
EXPDP scott/tiger DIRECTORY=mdBackup2 DUMPFILE=scott.dmp; -- 덤프파일 Export

IMPDP scott/tiger DIRECTORY=mdBackup2 DUMPFILE=scott.dmp; -- 덤프파일 Import














