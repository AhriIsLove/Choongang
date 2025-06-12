-- Backup Dir 생성
CREATE OR REPLACE DIRECTORY mdBackup2 AS 'C:\oraclexe\backup'; -- 가상의 폴더 생성(mdBackup2라는 이름으로 정의함)
GRANT read, write ON DIRECTORY mdBackup2 TO scott; -- 해당 폴더를 scott에게 권한 부여함

-- export : cmd에서 export 할 경로로 이동 후 export 코드 실행
-- import : cmd에서 export 된 dmp파일이 있는 경로로 이동 후 import 코드 실행
-- import시 대상 객체와 이름이 동일한 객체가 있으면 반영되지 않음

-- Oracle 전체 Backup  (scott)
EXPDP scott/tiger DIRECTORY=mdBackup2 DUMPFILE=scott.dmp; -- 덤프파일 Export
-- Oracle 전체 복구
IMPDP scott/tiger DIRECTORY=mdBackup2 DUMPFILE=scott.dmp; -- 덤프파일 Import

-- Oracle 부분 Backup후  부분 Restore
-- bat파일에 이것만 넣고 실행해도 잘됨
exp scott/tiger file=dept_second.dmp tables=dept_second
exp scott/tiger file=address.dmp tables=address
imp scott/tiger file=dept_second.dmp tables=dept_second
imp scott/tiger file=address.dmp tables=address

----------------------------------------------------------------------------------------
-------                     Trigger 
--  1. 정의 : 어떤 사건이 발생했을 때 내부적으로 실행되도록 데이터베이스에 저장된 프로시저
--           트리거가 실행되어야 할 이벤트 발생시 자동으로 실행되는 프로시저 
--           트리거링 사건(Triggering Event), 즉 오라클 DML 문인 INSERT, DELETE, UPDATE이 
--           실행되면 자동으로 실행
--  2. 오라클 트리거 사용 범위
--    1) 데이터베이스 테이블 생성하는 과정에서 참조 무결성과 데이터 무결성 등의 복잡한 제약 조건 생성하는 경우 
--    2) 데이터베이스 테이블의 데이터에 생기는 작업의 감시, 보완 
--    3) 데이터베이스 테이블에 생기는 변화에 따라 필요한 다른 프로그램을 실행하는 경우 
--    4) 불필요한 트랜잭션을 금지하기 위해 
--    5) 컬럼의 값을 자동으로 생성되도록 하는 경우 
----------------------------------------------------------------------------------------
--트리거 생성
CREATE OR REPLACE TRIGGER triger_test
BEFORE 
UPDATE ON dept -- 테이블을 업데이트 하기 전에
FOR EACH ROW -- old,new 사용 하기 위해
BEGIN -- BEGIN~END 코드 작성시 ctrl+enter주의!
    DBMS_OUTPUT.ENABLE;
    DBMS_OUTPUT.PUT_LINE('변경 전 칼럼 값 : ' || :old.dname);
    DBMS_OUTPUT.PUT_LINE('변경 후 칼럼 값 : ' || :new.dname);
END;

-- 상단메뉴 - 보기 - DBMS출력 - scott연결 - 확인
UPDATE dept SET dname='회계5팀' WHERE deptno = 71;
UPDATE dept SET loc='여기' WHERE deptno = 71; -- 이때도 트리거 발생함
COMMIT;

----------------------------------------------------------
--현장Work2 ) emp Table의 급여가 변화시
--       화면에 출력하는 Trigger 작성( emp_sal_change)
--       emp Table 수정전
--      조건 : 입력시는 empno가 0보다 커야함

--출력결과 예시
--     이전급여  : 10000
--     신  급여  : 15000
 --    급여 차액 :  5000
----------------------------------------------------------
CREATE OR REPLACE TRIGGER emp_sal_change
BEFORE DELETE OR INSERT OR UPDATE ON emp
FOR EACH ROW
    WHEN(new.empno > 0) -- 트리거문법 : 여기선 ':'을 사용하면 안됨
    DECLARE
        sal_diff NUMBER; -- 변수선언
BEGIN
    sal_diff := :new.sal - :old.sal; -- 트리거문법 : 변수에 값을 부여할때 ':='문법 주의
    SELECT :new.sal - :old.sal INTO sal_diff
    DBMS_OUTPUT.ENABLE;
    DBMS_OUTPUT.PUT_LINE('이전급여 : ' || :old.sal);
    DBMS_OUTPUT.PUT_LINE('신 급여 : ' || :new.sal);
    DBMS_OUTPUT.PUT_LINE('급여차액 : ' || sal_diff);
END;

UPDATE emp SET sal = 1001 WHERE empno = 7369;

-------------------------------------------------------------------------------
--  EMP 테이블에 INSERT,UPDATE,DELETE문장이 하루에 몇 건의 ROW가 발생되는지 조사
--  조사 내용은 EMP_ROW_AUDIT에 
--  ID ,사용자 이름, 작업 구분,작업 일자시간을 저장하는 
--  트리거를 작성
-------------------------------------------------------------------------------
-- 1. 시퀀스 생성
CREATE SEQUENCE emp_row_seq;
SELECT emp_row_seq.NEXTVAL FROM dual;--시퀀스 활성화

-- 2. Audit 테이블 생성 : 기록, 감시, 추적용 테이블
CREATE TABLE emp_row_audit(
    e_id    NUMBER(6)       CONSTRAINT emp_row_pk PRIMARY KEY,
    e_name  VARCHAR2(30),
    e_gubun VARCHAR2(10),
    e_date  DATE
);
-- 3. 트리거 생성
-- DDL관련도 시스템의 테이블을 트리거 시키면 가능할듯
CREATE OR REPLACE TRIGGER emp_row_aud
AFTER INSERT OR UPDATE OR DELETE ON emp -- emp 테이블에서 변경이 일어난 후에
FOR EACH ROW
BEGIN
    --IF문 문법 주의
    --IF THEN
    --ELSIF
    --END IF;
    IF INSERTING THEN -- 삽입이라면
        INSERT INTO emp_row_audit
            VALUES(emp_row_seq.NEXTVAL, :new.ename, 'inserting', sysdate);
    ELSIF UPDATING THEN -- 변경이라면
        INSERT INTO emp_row_audit
            VALUES(emp_row_seq.NEXTVAL, :new.ename, 'updating', sysdate);
    ELSIF DELETING THEN -- 삭제라면
        INSERT INTO emp_row_audit
            VALUES(emp_row_seq.NEXTVAL, :new.ename, 'deleting', sysdate);
    END IF;
END;

--integrity constraint (SCOTT.FK_DEPTNO) violated - parent key not found
INSERT INTO emp(empno, ename, sal, deptno) VALUES (3000, '유상진', 3500, 51); -- 오류 : emp.deptno가 외래키인데 dept.deptno가 51인 값이 없음
INSERT INTO emp(empno, ename, sal, deptno) VALUES (3000, '유상진', 3500, 52);
INSERT INTO emp(empno, ename, sal, deptno) VALUES (3100, '안도건', 3500, 52);
UPDATE emp SET ename = '노경민' WHERE empno = 1601;

------------------------------------------------------------------------
---    PL/SQL의 개념
---   1. Oracle에서 지원하는 프로그래밍 언어의 특성을 수용한 SQL의 확장
---   2. PL/SQL Block내에서 SQL의 DML(데이터 조작어)문과 Query(검색어)문, 
---      그리고 절차형 언어(IF, LOOP) 등을 사용하여 절차적으로 프로그래밍을 가능하게 
---      한 강력한  트랜잭션 언어
---
---   1) 장점 
---      프로그램 개발의 모듈화 : 복잡한 프로그램을 의미있고 잘 정의된 작은 Block 분해
---      변수선언  : 테이블과 칼럼의 데이터 타입을 기반으로 하는 유동적인 변수를 선언
---      에러처리  : Exception 처리 루틴을 사용하여 Oracle 서버 에러를 처리
---      이식성    : Oracle과 PL/SQL을 지원하는어떤 호스트로도 프로그램 이동 가능
---      성능 향상 : 응용 프로그램의 성능을 향상
--------------------------------------------------------------------------
-----------------------------  PL / SQL ---------------------------------
--------------------------------------------------------------------------
--[예제1] 특정한 수에 세금을 7%로 계산하는 Function tax를 작성
CREATE OR REPLACE FUNCTION tax(p_num in number)
RETURN number
IS
    v_tax   number;
BEGIN
    v_tax := p_num * 0.07;
    RETURN(v_tax);
END;

SELECT tax(100) FROM dual;

----------------------------------------------------------------------------------------
-- 현장 Work02
-- 1) Procedure Insert_emp
-- 2) parameter(IN) -> p_empno, p_ename, p_job, p_mgr, p_sal, p_deptno
-- 3) 변수명 : v_comm
-- 4) 로직
--        1) p_job MANAGER -> v_comm(1000)
--        2) p_job ELSE         -> v_comm(150)
--        3) emp TBL Insert(hiredate -> 현재일자)
----------------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE Insert_emp(
p_empno in emp.empno%type, 
p_ename in emp.ename%type, 
p_job in emp.job%type, 
p_mgr in emp.mgr%type, 
p_sal in emp.sal%type, 
p_deptno in emp.deptno%type)
IS
    v_comm emp.comm%type;
--    DECLARE v_comm emp.comm%type;
BEGIN
    SELECT job INTO v_job 
    FROM emp 
    WHERE empno = p_empno;

    IF v_job LIKE 'SALE%' THEN
        v_up := 10;
    ELSIF v_job LIKE 'MAN%' THEN
        v_up := 7;
    ELSE 
        v_up := 5;
    END IF;

    UPDATE emp SET sal = sal + (sal*v_up/100) WHERE empno = p_empno;
    COMMIT;
END;

-----------------------------------------------------
--  Procedure up_emp 실행 결과
-- SQL> EXECUTE up_emp(1200);  -- parameter 사번 p_empno 
-- 결과       : 급여 인상 저장
--               시작문자
--   변수     :   v_job(업무)
--                  v_up세율)

-- 조건 1) job = SALE포함         v_up : 10
--           IF              v_job LIKE 'SALE%' THEN
--     2)            MAN              v_up : 7  
--     3)                                v_up : 5
--   job에 따른 급여 인상을 수행  sal = sal+sal*v_up/100
-- 확인 : DB -> TBL
-----------------------------------------------------
CREATE OR REPLACE PROCEDURE up_emp(
p_empno IN emp.empno%type
)
IS
    v_job emp.job%type;
    v_up number(3);
BEGIN
    SELECT job INTO v_job FROM emp WHERE empno = p_empno;

    IF v_job LIKE 'SALE%' THEN
        v_up := 10;
    ELSIF v_job LIKE 'MAN%' THEN
        v_up := 7;
    ELSE 
        v_up := 5;
    END IF;
    
    UPDATE emp SET sal = sal + (sal*v_up/100) WHERE empno = p_empno;
    COMMIT;
END;

----------------------------------------------------------
-- HW01
-- PROCEDURE Delete_emp
-- SQL> EXECUTE Delete_emp(5555);
-- 사원번호 : 5555
-- 사원이름 : 55
-- 입 사 일 : 81/12/03
-- 데이터 삭제 성공
--  1. Parameter : 사번 입력
--  2. 사번 이용해 사원번호 ,사원이름 , 입 사 일 출력
--  3. 사번 해당하는 데이터 삭제 
----------------------------------------------------------
CREATE OR REPLACE PROCEDURE delete_emp(
    p_empno emp.empno%type
)
IS
    v_empno emp.empno%type;
    v_ename emp.ename%type;
    v_hiredate emp.hiredate%type;
BEGIN
    SELECT empno, ename, hiredate 
    INTO v_empno, v_ename, v_hiredate
    FROM emp WHERE empno = p_empno;

    DBMS_OUTPUT.ENABLE;
    DBMS_OUTPUT.PUT_LINE('사원번호 : ' || v_empno);
    DBMS_OUTPUT.PUT_LINE('사원이름 : ' || v_ename);
    DBMS_OUTPUT.PUT_LINE('입 사 일 : ' || v_hiredate);
    
    DELETE FROM emp WHERE empno = p_empno;
    DBMS_OUTPUT.PUT_LINE('데이터 삭제 성공');
    COMMIT;
END;

------------------------------------------------------------
--  EMP 테이블에서 사번을 입력받아 해당 사원의 급여에 따른 세금을 구함.
-- 급여가 2000 미만이면 급여의 6%, 
-- 급여가 3000 미만이면 8%, 
-- 5000 미만이면 10%, 
-- 그 이상은 15%로 세금
--- FUNCTION  emp_tax3
-- 1) Parameter : 사번 p_empno
--      변수     :   v_sal(급여)
--                     v_pct(세율)
-- 2) 사번을 가지고 급여를 구함
-- 3) 급여를 가지고 세율 계산 
-- 4) 계산 된 값 Return   number
-------------------------------------------------------------
CREATE OR REPLACE FUNCTION emp_tax3(p_empno in emp.empno%type)
RETURN number
IS
    v_sal   emp.sal%type;
    v_pct   NUMBER(5,2);
BEGIN
    SELECT sal INTO v_sal FROM emp WHERE empno = p_empno;
    
-- 3) 급여를 가지고 세율 계산
    IF              v_sal < 2000     THEN
                     v_pct := (v_sal*0.06);
    ELSIF          v_sal < 3000     THEN
                     v_pct := (v_sal*0.08);
    ELSIF          v_sal < 5000     THEN
                     v_pct := (v_sal*0.10);
    ELSE           
                     v_pct := (v_sal*0.15);
    END IF;
    
    RETURN(v_pct);
END emp_tax3;

SELECT ename, sal, EMP_TAX3(empno) emp_rate FROM emp;
