-----------------------------------------------------------------------------------------------------------
---   데이터베이스 보안
---  1. 다중 사용자 환경(multi-user environment)
---     1) 사용자는 자신이 생성한 객체에 대해 소유권을 가지고 데이터에 대한 조작이나 조회 가능
---     2) 다른 사용자가 소유한 객체는 소유자로부터 접근 권한을 부여받지 않는 접근 불가
---     3) 다중 사용자 환경에서는 데이터베이스 관리자의 암호를 철저하게 관리
---  2. 중앙 집중적인 데이터 관리
---  3. 시스템 보안
---     1) 데이터베이스 관리자는 사용자 계정, 암호 관리, 사용자별 허용 가능한 디스크공간 할당
---     2) 시스템 관리 차원에서 데이터베이스 자체에 대한 접근 권한을 관리
---  4. 데이터 보안
---     1) 사용자별로 객체를 조작하기 위한 동작 관리
---     2) 데이터베이스 객체에 대한 접근 권한을 관리
-----------------------------------------------------------------------------------------------------------
---  권한(Privilege) 부여
---    1. 정의 : 사용자가 데이터베이스 시스템을 관리하거나 객체를 이용할 수 있는 권리
---    2. 유형 
---         1) 시스템 권한 : 시스템 차원의 자원 관리나 사용자 스키마 객체 관리 등과 같은 
---                               데이터베이스 관리 작업을 할 수 있는 권한
---             [1]  데이터베이스 관리자가 가지는 시스템 권한
---                   CREATE USER     :  사용자를 생성할 수 있는 권한
---                   DROP    USER     : 사용자를 삭제할 수 있는 권한
---                   DROP ANY TABLE : 임의의 테이블을 삭제할 수 있는 권한
---                   QUERY REWRITE  : 함수 기반 인덱스를 생성하기 위한 권한
---             [2]  일반사용자가 가지는 시스템 권한
---                   CREATE SESSION      : DB에 접속할 수 있는 권한
---                   CREATE TABLE          : 사용자 스키마에서 테이블을 생성할 수 있는 권한
---                   CREATE SEQUENCE   : 사용자 스키마에서 시퀀스를 생성할 수 있는 권한
---                   CREATE VIEW            : 사용자 스키마에서 뷰를 생성할 수 있는 권한
---                   CREATE PROCEDURE : 사용자 스키마에서 프로시저, 함수, 패키지를 생성할 수 있는 권한
---         2) 객체 권한    : 테이블, 뷰, 시퀀스, 함수 등과 같은 객체를 조작할 수 있는 권한
---  롤(role)
---  1. 개념 : 다수 사용자와 다양한 권한을 효과적으로 관리하기 위하여 서로 관련된 권한을 그룹화한 개념
---              일반 사용자가 데이터베이스를 이용하기 위한 공통적인 권한(데이터베이스 접속권한, 
---              테이블 생성, 수정, 삭제, 조회 권한, 뷰 생성 권한)을 그룹화
-- 사전에 정의된 롤
-- 1. CONNECT 롤
--     1) 사용자가 데이터베이스에 접속하여 세션을 생성할 수 있는 권한
--     2) 테이블 또는 뷰와 같은 객체를 생성할 수 있는 권한
-- 2. RESOURCE 롤
--     1) 사용자에게 자신의 테이블, 시퀀스, 프로시져, 트리거 객체 생성 할 수 있는 권한
--     2) 사용자 생성시 : CONNECT 롤과 RESOURCE 롤을 부여
-- 3.  DBA 롤
--     1) 시스템 자원의 무제한적인 사용이나 시스템 관리에 필요한 모든 권한
--     2) DBA 권한을 다른 사람에게 부여할 수 있음
--     3) 모든 사용자 소유의 CONNECT, RESOURCE, DBA 권한을 포함한 모든 권한을 부여 및 철회 가능
-----------------------------------------------------------------------------------------------------------

-----------------------------------------------
---   Admin 사용자 생성 /권한 부여
------------------------------------------------
-- 1. 사용자 생성
--CREATE USER id IDENTIFIED BY password
CREATE USER usertest01 IDENTIFIED BY tiger;
CREATE USER usertest02 IDENTIFIED BY tiger;
CREATE USER usertest03 IDENTIFIED BY tiger;
CREATE USER usertest04 IDENTIFIED BY tiger;

-- 생성한 사용자로 변경
-- 좌측 - 데이터베이스접속 - Name, 사용자이름, 비밀번호 입력 - 접속
-- 상단 - SQL 아이콘 - id 선택 - 접속
-- 우상단에 DB모양 콤보박스에서 선택하여 변경 가능

-- 2-1. session 권한 부여  --> 접속권한만 주어짐
GRANT CREATE session TO usertest01; -- 테이블 생성 오류 : 생성 권한 없음
-- usertest01 START ------------------------------------------------------------
-- insufficient privileges 오류 : 권한이 없어 테이블 생성 불가능
CREATE TABLE emp_row_audit1(
    e_id    NUMBER(6)       CONSTRAINT emp_row_pk PRIMARY KEY,
    e_name  VARCHAR2(30),
    e_gubun VARCHAR2(10),
    e_date  DATE
);
-- usertest01 END --------------------------------------------------------------

-- 2-1. session 권한 부여  --> 접속권한,생성권한 만 주어짐
GRANT CREATE session, CREATE table, CREATE view TO usertest02; -- 테이블 생성 오류 : 테이블 스페이스 권한 없음
-- usertest02 START ------------------------------------------------------------
-- no privileges on tablespace 'SYSTEM' 오류 : 테이블 생성은 가능하지만 테이블 스페이스에 대한 권한이 없어 불가능
CREATE TABLE sampleTBL(
     memo varchar2(50)
 );
-- usertest02 END --------------------------------------------------------------

-- 2-2. 현장에서 DBA가 개발자 권한 부여 : ROLE부여(CONNECT, RESOURCE)
GRANT CONNECT, RESOURCE TO usertest03;
-- usertest03 START ------------------------------------------------------------
-- 개발자 역할 --> CONNECT , RESOURCE 
CREATE TABLE sampleTBL(
     memo varchar2(50)
 );
 
INSERT INTO sampleTBL values('7월 더움');
INSERT INTO sampleTBL values('결실을 맺으리라');

COMMIT;

-- X 
SELECT * FROM scott.emp; -- 관리자(scott)의 테이블에 접근 불가능
-- usertest03 END --------------------------------------------------------------

-- 2-3 권한 부여와 회수
GRANT DBA TO usertest04;
-- usertest04 START ------------------------------------------------------------
CREATE TABLE sampleTBL(
     memo varchar2(50)
);

INSERT INTO sampleTBL values('7월 더움');
INSERT INTO sampleTBL values('결실을 맺으리라');
INSERT INTO sampleTBL values('트럼프 힘들어');

COMMIT; -- 전부 가능
-- usertest04 END --------------------------------------------------------------
-- DBA 권한 회수
REVOKE DBA FROM usertest04;
-- usertest04 START ------------------------------------------------------------
SELECT * FROM sampleTBL; -- 권한을 뺏겨도 자기자신의 것은 접근 할 수 있따.
-- usertest04 END --------------------------------------------------------------
GRANT CONNECT, RESOURCE TO usertest04;

-- 자신이 생성한 테이블이 아닌 경우엔 생성한 계정명을 작성해야 함
SELECT * FROM emp; -- 오류
SELECT * FROM scott.emp;

----------------------------------------------------------------------------------------------------
-- 동의어(synonym)
-- 1. 정의 : 하나의 객체에 대해 다른 이름을 정의하는 방법
--      동의어와 별명(Alias) 차이점
--      동의어는 데이터베이스 전체에서 사용
--      별명은 해당 SQL 명령문에서만 사용
-- 2. 동의어의 종류
--   1) 전용 동의어(private synonym) 
--      객체에 대한 접근 권한을 부여 받은 사용자가 정의한 동의어로 해당 사용자만 사용
--
--   2) 공용 동의어(public synonym)
--      권한을 주는 사용자가 정의한 동의어로 누구나 사용
--      DBA 권한을 가진 사용자만 생성 (예 : 데이터 딕셔너리)
 -------------------------------------------------------------------------------------------------
CREATE TABLE systemTBL(
    memo VARCHAR2(50)
);
INSERT INTO systemTBL VALUES('7월 푸름');
INSERT INTO systemTBL VALUES('결실 맺으리라');
COMMIT;

SELECT * FROM systemTBL;

-- SELECT 권한 부여
GRANT SELECT ON systemTBL TO usertest04 WITH GRANT OPTION;
-- usertest04 START ------------------------------------------------------------
SELECT * FROM system.systemTBL; -- 시노님 부여받으면 가능
INSERT INTO system.systemTBL VALUES('5월 아사사'); -- 권한에 따라 다름
-- usertest04 END --------------------------------------------------------------

-- 권한 부여 했지만 번거로움
CREATE PUBLIC SYNONYM pub_system FOR systemTBL; -- 모두에게 systemTBL을 pub_system으로 접근 가능하게 명명
-- usertest04 START ------------------------------------------------------------
SELECT * FROM pub_system;
-- usertest04 END --------------------------------------------------------------

--시노님 삭제;
DROP PUBLIC SYNONYM pub_system;
-- usertest04 START ------------------------------------------------------------
SELECT * FROM pub_system; -- 오류
-- usertest04 END --------------------------------------------------------------

-- 실무에서는 실제 테이블 명과 시노님 명을 동일하게 사용한다.
CREATE PUBLIC SYNONYM systemTBL FOR systemTBL;
-- usertest04 START ------------------------------------------------------------
SELECT * FROM systemTBL; -- 자신의 테이블처럼 사용 가능
-- usertest04 END --------------------------------------------------------------

-- 전용 동의어(private synonym)  Test 용
CREATE TABLE privateTBL(
    memo varchar2(50)
);
INSERT INTO privateTBL VALUES('6월 푸름');
INSERT INTO privateTBL VALUES('결실을 맺으리라...');
GRANT SELECT ON privateTBL TO usertest04 WITH GRANT OPTION;
GRANT SELECT ON privateTBL TO scott WITH GRANT OPTION;
COMMIT;
SELECT * FROM privateTBL;
-- usertest04 START ------------------------------------------------------------
SELECT * FROM privateTBL; -- 오류
SELECT * FROM system.privateTBL;

-- 전용 동의어(private synonym) 생성
CREATE SYNONYM privateTBL FOR system.privateTBL; -- 오류
-- usertest04 END --------------------------------------------------------------

-- CONNECT, RESOURCE 권한 회수
REVOKE CONNECT, RESOURCE FROM usertest04;
-- DBA 권한 부여
GRANT DBA TO usertest04;
-- usertest04 START ------------------------------------------------------------
-- usertest04 재접속 후 전용 동의어(private synonym) 생성 재시도
CREATE SYNONYM privateTBL FOR system.privateTBL;
-- usertest04 END --------------------------------------------------------------

-- scott START -----------------------------------------------------------------
SELECT * FROM system.privateTBL;
SELECT * FROM privateTBL; -- 다른 사용자가 전용으로 시노님하였기에 scott에서 볼수 없음
-- scott END -------------------------------------------------------------------

-- usertest04 START ------------------------------------------------------------
SELECT * FROM scott.emp; -- DBA권한을 가진 사용자는 다른 DBA가 만든 테이블에 접근 가능
-- usertest04 END --------------------------------------------------------------

-- scott START -----------------------------------------------------------------
GRANT SELECT ON emp TO usertest02;
-- scott END -------------------------------------------------------------------
-- usertest02 START ------------------------------------------------------------
--scott이 SELECT 권한을 할당하였기 때문에 가능
SELECT * FROM scott.emp;
-- usertest02 END --------------------------------------------------------------

-- scott START -----------------------------------------------------------------
GRANT SELECT ON stud_101 TO usertest02; -- 단순히 SELECT만 가능하도록
GRANT SELECT ON job3 TO usertest02 WITH GRANT OPTION; -- 누군가에게 SELECT 권한을 부여할 수 있도록
-- scott END -------------------------------------------------------------------
-- usertest02 START ------------------------------------------------------------
--scott에게 받은 권한.
SELECT * FROM scott.stud_101;
SELECT * FROM scott.job3; -- WITH GRANT OPTION
-- usertest02 END --------------------------------------------------------------

GRANT CREATE session, CREATE table, CREATE view TO usertest01;
-- usertest02 START ------------------------------------------------------------
GRANT SELECT ON scott.stud_101 TO usertest01; -- 오류 : 권한을 부여할 수 있는 권한이 없음
GRANT SELECT ON scott.job3 TO usertest01;
-- usertest02 END --------------------------------------------------------------
-- usertest01 START ------------------------------------------------------------
--1. scott이 usertest02에게 job3을 WITH GRANT OPTION으로 부여
--2. usertest02가 usertest01에게 job3을 부여
SELECT * FROM scott.job3;
-- usertest01 END --------------------------------------------------------------

-- scott START -----------------------------------------------------------------
--usertest02는 이미 usertest01에게 권한을 부여한 상태
--usertest02의 권한을 뻇으면
REVOKE SELECT ON job3 FROM usertest02;
-- scott END -------------------------------------------------------------------
-- usertest02 START ------------------------------------------------------------
SELECT * FROM scott.job3; -- 오류
-- usertest02 END --------------------------------------------------------------
-- usertest01 START ------------------------------------------------------------
SELECT * FROM scott.job3; -- 오류
-- usertest01 END --------------------------------------------------------------
