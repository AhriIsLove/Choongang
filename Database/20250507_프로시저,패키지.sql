CREATE OR REPLACE PROCEDURE DeptEmpSearch1(
p_deptno IN emp.deptno%type) -- 20으로 실행시 오류발생
IS
    v_empno emp.empno%type;
    v_ename emp.ename%type;
BEGIN
    DBMS_OUTPUT.ENABLE;
    
    SELECT empno, ename
    INTO v_empno, v_ename -- 오류발생가능 : 2개 이상의 결과가 나타날 때 오류발생 가능
    FROM emp
    WHERE deptno = p_deptno;
    
    DBMS_OUTPUT.PUT_LINE('사번 : ' || v_empno);
    DBMS_OUTPUT.PUT_LINE('이름 : ' || v_ename);
END;

-- RowType으로 선언
CREATE OR REPLACE PROCEDURE DeptEmpSearch2(
p_deptno IN emp.deptno%type)
IS
--    v_empno emp.empno%type;
--    v_ename emp.ename%type;
    v_emp   emp%ROWTYPE; -- emp 테이블 형태의 변수로 선언 -- 얘도 결과 하나만 저장 가능...
    
BEGIN
    DBMS_OUTPUT.ENABLE;
    
--    SELECT empno, ename
--    INTO v_empno, v_ename -- 오류발생가능 : 2개 이상의 결과가 나타날 때 오류발생 가능
    SELECT *
    INTO v_emp
    FROM emp
    WHERE deptno = p_deptno;
    
    DBMS_OUTPUT.PUT_LINE('사번 : ' || v_emp.empno);
    DBMS_OUTPUT.PUT_LINE('이름 : ' || v_emp.ename);
END;

-- Multi Row 해결방법
-- 1. EXCEPTION 처리 (ㅇ)
-- 2. CURSOR문 사용
CREATE OR REPLACE PROCEDURE DeptEmpSearch3(
p_deptno IN emp.deptno%type)
IS
    v_emp   emp%ROWTYPE;
    
BEGIN
    DBMS_OUTPUT.ENABLE;
    
    SELECT *
    INTO v_emp
    FROM emp
    WHERE deptno = p_deptno;
    
    DBMS_OUTPUT.PUT_LINE('사번 : ' || v_emp.empno);
    DBMS_OUTPUT.PUT_LINE('이름 : ' || v_emp.ename);
    
    -- 에러 예외 처리 : Multi Row Error(실제 INTO 가 요구된 것보다 많은 수의 행을 추출함
    EXCEPTION WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('ERROR CODE 1 : ' || TO_CHAR(SQLCODE));
        DBMS_OUTPUT.PUT_LINE('ERROR CODE 2 : ' || SQLCODE);
        DBMS_OUTPUT.PUT_LINE('ERROR MESSAGE : ' || SQLERRM);
END;

--------------------------------------------------------------------------------
----  ***    cursor    ***
--- 1.정의 : Oracle Server는 SQL문을 실행하고 처리한 정보를 저장하기 위해
--          "Private SQL Area" 이라고 하는 작업영역을 이용
--          이 영역에 이름을 부여하고 저장된 정보를 처리할 수 있게 해주는데 이를 CURSOR
-- 2. 종류 : Implicit(묵시적인) CURSOR -> DML문과 SELECT문에 의해 내부적으로 선언
--          Explicit(명시적인) CURSOR -> 사용자가 선언하고 이름을 정의해서 사용
-- 3.attribute (**)
--   1) SQL%ROWCOUNT : 가장 최근의 SQL문에 의해 처리된 Row 수
--   2) SQL%FOUND    : 가장 최근의 SQL문에 의해 처리된 Row의 개수가 한 개이상이면 True
--   3) SQL%NOTFOUND : 가장 최근의 SQL문에 의해 처리된 Row의 개수가 없으면True
-- 4. 4단계 **
--   1) DECLARE 단계 : 커서에 이름을 부여하고 커서내에서 수행할 SELECT문을 정의함으로써 CURSOR를 선언
--   2) OPEN 단계 : OPEN문은 참조되는 변수를 연결하고, SELECT문을 실행
--   3) FETCH 단계 : CURSOR로부터 Pointer가 존재하는 Record의 값을 변수에 전달
--   4) CLOSE 단계 : Record의 Active Set을 닫아 주고, 다시 새로운 Active Set을만들어 OPEN할 수 있게 해줌
--------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE show_emp3(
p_empno IN emp.empno%type)
IS
    v_ename emp.ename%type;
    v_sal   emp.sal%type;
    v_job   emp.job%type;
    
    -- 1) DECLARE 단계 : 커서 객체 선언
    CURSOR emp_cursor IS -- 뷰 개념
    SELECT ename, job, sal
    FROM emp
    WHERE empno LIKE p_empno || '%';
    
BEGIN
    -- 2) OPEN 단계 : 커서 객체 생성(사용,new)
    OPEN emp_cursor;
    DBMS_OUTPUT.PUT_LINE('이름' || CHR(9) || '업무' || CHR(9) || '급여');
    DBMS_OUTPUT.PUT_LINE('---------------------------');
    
    LOOP
        -- 3) FETCH 단계 : 커서에서 하나씩 처리
        FETCH emp_cursor INTO v_ename, v_job, v_sal;
        EXIT WHEN emp_cursor%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(v_ename || CHR(9) || v_job || CHR(9) || v_sal);
    END LOOP;
    DBMS_OUTPUT.PUT_LINE(emp_cursor%ROWCOUNT||'개의 행 선택');
    
    -- 4) CLOSE 단계 : 커서 객체 삭제(닫기,null처리)
    CLOSE emp_cursor;
END;
---------------------------------------------------------
-- EXECUTE 문을 이용해 함수를 실행합니다.
-- SQL>EXECUTE show_emp3(7900);
--EXEC show_emp3(3);
---------------------------------------------------------
-----------------------------------------------------
-- Fetch 문 ***
-- SQL> EXECUTE  Cur_sal_Hap (dept_no);
-- CURSOR 문 이용 구현
-- 부서만큼 반복
-- 	부서명 : 인사팀
-- 	인원수 : 5
-- 	급여합 : 5000
--  Cursor : dept_sum
-----------------------------------------------------
CREATE OR REPLACE PROCEDURE Cur_sal_Hap( -- 나의 프로시저
p_deptno IN emp.deptno%type)
IS
    v_deptname dept.dname%type;
    v_count NUMBER;
    v_sumSal NUMBER;
    
    v_deptno emp.deptno%type;
    v_sal emp.sal%type;

    CURSOR dept_sum IS
    SELECT sal
    FROM emp
    WHERE deptno = p_deptno;

BEGIN
    OPEN dept_sum;
    
    v_sumSal := 0;
    LOOP
        FETCH dept_sum INTO v_sal;
        EXIT WHEN dept_sum%NOTFOUND;
        v_sumSal := v_sumSal + v_sal;
    END LOOP;
    
    SELECT dname
    INTO v_deptname
    FROM dept
    WHERE deptno = p_deptno;
    
    DBMS_OUTPUT.PUT_LINE('부서명 : ' || v_deptname);
    DBMS_OUTPUT.PUT_LINE('인원수 : ' || dept_sum%ROWCOUNT);
    DBMS_OUTPUT.PUT_LINE('급여합 : ' || v_sumSal);
    
    CLOSE dept_sum;
END;
--EXEC Cur_sal_Hap(20);

CREATE OR REPLACE PROCEDURE Cur_sal_Hap2( -- 강사의 프로시저
p_deptno IN emp.deptno%type)
IS
    CURSOR dept_sum IS
    SELECT d.dname, COUNT(*) AS cnt, SUM(e.sal) AS sumSal
    FROM emp e, dept d
    WHERE e.deptno = d.deptno
    AND e.deptno LIKE p_deptno || '%'
    GROUP BY d.dname;
        
    v_dname dept.dname%type;
    v_count NUMBER;
    v_sumSal NUMBER;
    
BEGIN
    DBMS_OUTPUT.ENABLE;
    
    OPEN dept_sum;
    
    LOOP
        FETCH dept_sum INTO v_dname, v_count, v_sumSal;
        EXIT WHEN dept_sum%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('부서명 : ' || v_dname);
        DBMS_OUTPUT.PUT_LINE('인원수 : ' || v_count);
        DBMS_OUTPUT.PUT_LINE('급여합 : ' || v_sumSal);
    END LOOP;
    
    CLOSE dept_sum;
END;
--EXEC Cur_sal_Hap2(1);

------------------------------------------------------------------------
-- FOR문을 사용하면 커서의 OPEN, FETCH, CLOSE가 자동 발생하므로
-- 따로 기술할 필요가 없고, 레코드 이름도 자동
-- 선언되므로 따로 선언할 필요가 없다.
-----------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE ForCursor_sal_Hap( -- 강사의 프로시저
p_deptno IN emp.deptno%type)
IS
    CURSOR dept_sum IS
    SELECT d.dname, COUNT(*) AS cnt, SUM(e.sal) AS sumSal
    FROM emp e, dept d
    WHERE e.deptno = d.deptno
    AND e.deptno LIKE p_deptno || '%'
    GROUP BY d.dname;
    
BEGIN
    DBMS_OUTPUT.ENABLE;
    
--    LOOP
    --OPEN, FETCH, CLOSE가 자동발생
    -- 향상형 FOR문과 비슷함
    FOR emp_list IN dept_sum LOOP
        DBMS_OUTPUT.PUT_LINE('부서명 : ' || emp_list.dname);
        DBMS_OUTPUT.PUT_LINE('인원수 : ' || emp_list.cnt);
        DBMS_OUTPUT.PUT_LINE('급여합 : ' || emp_list.sumSal);
    END LOOP;
    
    EXCEPTION WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('에러 : ' || SQLERRM);
END;

-------------------------------------------------------------------------------
--오라클 PL/SQL은 자주 일어나는 몇가지 예외를 미리 정의해 놓았으며, 
--이러한 예외는 개발자가 따로 선언할 필요가 없다.
--미리 정의된 예외의 종류
-- NO_DATA_FOUND : SELECT문이 아무런 데이터 행을 반환하지 못할 때
-- DUP_VAL_ON_INDEX : UNIQUE 제약을 갖는 컬럼에 중복되는 데이터 INSERT 될 때
-- ZERO_DIVIDE : 0으로 나눌 때
-- INVALID_CURSOR : 잘못된 커서 연산
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE PreException(
p_deptno IN emp.deptno%type)
IS
    v_emp emp%ROWTYPE;
BEGIN
    DBMS_OUTPUT.ENABLE;
    
    SELECT empno, ename, deptno
    INTO v_emp.empno, v_emp.ename, v_emp.deptno
    FROM emp
    WHERE deptno = p_deptno;
    
    DBMS_OUTPUT.PUT_LINE('사번 : ' || v_emp.empno);
    DBMS_OUTPUT.PUT_LINE('이름 : ' || v_emp.ename);
    DBMS_OUTPUT.PUT_LINE('부서번호 : ' || v_emp.deptno);
    
    EXCEPTION 
        WHEN DUP_VAL_ON_INDEX THEN 
            DBMS_OUTPUT.PUT_LINE('중복 데이터 존재 에러');
            DBMS_OUTPUT.PUT_LINE('DUP_VAL_ON_INDEX 에러');
        WHEN TOO_MANY_ROWS THEN 
            DBMS_OUTPUT.PUT_LINE('TOO_MANY_ROWS 에러');
        WHEN NO_DATA_FOUND THEN 
            DBMS_OUTPUT.PUT_LINE('NO_DATA_FOUND 에러');
        WHEN OTHERS THEN 
            DBMS_OUTPUT.PUT_LINE('기타 에러');
END;

--------------------------------------------------------------------------
----   Procedure :  in_emp
----   Action    : emp Insert
----   1. Error 유형
---      1) DUP_VAL_ON_INDEX  :  PreDefined --> Oracle 선언 Error
---      2) User Defind Error :  lowsal_err (최저급여 ->1500)
--------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE in_emp(
    p_name      IN emp.ename%type,
    p_sal       IN emp.sal%type,
    p_job       IN emp.job%type,
    p_deptno    IN emp.deptno%type)
IS
    v_empno     emp.empno%type;
    
    -- 개발자 Defind Error
    lowsal_err  EXCEPTION; -- 커스텀 에러 선언
BEGIN
    DBMS_OUTPUT.ENABLE;
    SELECT MAX(empno)+1
    INTO v_empno
    FROM emp;
    
    IF p_sal >= 2000 THEN
        INSERT INTO emp(empno, ename, sal, job, deptno, hiredate)
        VALUES (v_empno, p_name, p_sal, p_job, 10, SYSDATE);
    ELSE -- 2) 개발자 Defind Error :  lowsal_err (최저급여 ->1500)
        RAISE lowsal_err; -- 커스텀 에러 발생
    END IF;
    
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('중복 데이터 ename 존재');
            DBMS_OUTPUT.PUT_LINE('DUP_VAL_ON_INDEX 에러');
        WHEN lowsal_err THEN
            DBMS_OUTPUT.PUT_LINE('급여 범위 에러');
            DBMS_OUTPUT.PUT_LINE('급여를 2000 이상 입력해주세요');
END;

-- 모든 트리거 활성화/비활성화 프로시저
CREATE OR REPLACE PROCEDURE ALL_TRIGGER_ENABLE(
p_enable BOOLEAN)
IS
    CURSOR triggerCursor IS
    SELECT TRIGGER_NAME, STATUS
    FROM USER_TRIGGERS;
    
BEGIN
    FOR mTrigger IN triggerCursor LOOP
        IF p_enable = TRUE THEN
            EXECUTE IMMEDIATE 'ALTER TRIGGER ' || mTrigger.TRIGGER_NAME || ' ENABLE';
        ELSE
            EXECUTE IMMEDIATE 'ALTER TRIGGER ' || mTrigger.TRIGGER_NAME || ' DISABLE';
        END IF;
    END LOOP;
END;
EXEC ALL_TRIGGER_ENABLE(TRUE);
EXEC ALL_TRIGGER_ENABLE(FALSE);

-----------------------------------------------------------
----   Procedure :  in_emp3
----   Action    : emp Insert
----   1. Error 유형
---      1) DUP_VAL_ON_INDEX  :  PreDefined --> Oracle 선언 Error
---      2) User Defind Error :  highsal_err (최고급여 ->9000 이상 오류 발생)
---   2. 기타조건
---      1) emp.ename은 Unique 제약조건이 걸려 있다고 가정
---      2) parameter : p_name, p_sal, p_job
---      3) PK(empno) : Max 번호 입력
---      3) hiredate     : 시스템 날짜 입력
---      4) emp(empno,ename,sal,job,hiredate)  --> 5 Column입력한다 가정
---      5) DUP_VAL_ON_INDEX --> 중복 데이터 ename 존재 합니다 / DUP_VAL_ON_INDEX 에러 발생
---          highsal_err  -->ERROR!!!-지정한 급여가 너무 많습니다. 9000이하으로 다시 입력하세요
-----------------------------------------------------------
CREATE OR REPLACE PROCEDURE in_emp3(
p_ename IN emp.ename%type,
p_sal   IN emp.sal%type,
p_job   IN emp.job%type)
IS
    v_empno     emp.empno%type;
    highsal_err EXCEPTION;
BEGIN
    SELECT MAX(empno) + 1
    INTO v_empno
    FROM emp;

    IF p_sal <= 9000 THEN
        INSERT INTO emp(empno, ename, sal, job, hiredate) 
        VALUES (v_empno, p_ename, p_sal, p_job, sysdate);
    ELSE
        RAISE highsal_err;
    END IF;
    
    dbms_output.enable;
    EXCEPTION 
        WHEN DUP_VAL_ON_INDEX THEN
            dbms_output.put_line('중복 데이터 ename 존재 합니다');
        WHEN highsal_err THEN
            dbms_output.put_line('ERROR!!!-지정한 급여가 너무 많습니다. 9000이하으로 다시 입력하세요');
        WHEN OTHERS THEN
            dbms_output.put_line('기타 에러');
END;

--------------------------------------------------------------
--  20250507 HW01
-- 1.  PROCEDURE update_empno
-- 2.  parameter -> p_empno, p_job
-- 3.  해당 empno에 관련되는 사원들을(Like) job을 사람의 직업을 p_job으로 업데이트
-- 4. Update -> emp 직업
-- 5.               입사일은 현재일자
-- 6.  기본적 EXCEPTION 처리
-- 7.  CURSOR emp_list --> For 처리
------------------------------------------------------------
CREATE OR REPLACE PROCEDURE update_empno(
p_empno IN emp.empno%type,
p_job IN emp.job%type)
IS
    CURSOR emp_list IS
    SELECT empno
    FROM emp
    WHERE empno LIKE p_empno || '%';
BEGIN
    dbms_output.enable;

    FOR mEmp IN emp_list LOOP
    DBMS_OUTPUT.PUT_LINE('사원 : ' || mEmp.empno);
        UPDATE emp SET job = p_job, hiredate = sysdate WHERE empno = mEmp.empno;
    END LOOP;
    
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('에러');
END;
--------------------------------------------------------------
--  20250507 HW02
-- 1.  PROCEDURE Delete_Emp3
-- 2.  parameter -> p_empno, p_result('0' , '1')
-- 3.                        p_result -> 성공시 1
-- 4.                                    실패시 0
-- 5. 결과
--   사원번호 : 1600
--   사원이름 : 홍길동
--   입 사 일 : 
--   결   과 : 1
------------------------------------------------------------
CREATE OR REPLACE PROCEDURE Delete_Emp3(
    p_empno IN emp.empno%type,
    p_result OUT NUMBER)
IS
    v_ename emp.ename%type;
    v_hiredate emp.hiredate%type;

BEGIN
    dbms_output.enable;
    
    SELECT ename, hiredate
    INTO v_ename, v_hiredate
    FROM emp
    WHERE empno = p_empno;
    
    DELETE FROM emp WHERE empno = p_empno;
    DBMS_OUTPUT.PUT_LINE('사원번호 : ' || p_empno);
    DBMS_OUTPUT.PUT_LINE('사원이름 : ' || v_ename);
    DBMS_OUTPUT.PUT_LINE('입사일 : ' || v_hiredate);
    p_result := 1;
    DBMS_OUTPUT.PUT_LINE('결과 : ' || p_result);
    
    EXCEPTION 
        WHEN OTHERS THEN
            p_result := 0;
            DBMS_OUTPUT.PUT_LINE('결과 : ' || p_result);
END;

---------------------------------------------------------------------------------------
-----    Package
--  자주 사용하는 프로그램과 로직을 모듈화
--  응용 프로그램을 쉽게 개발 할 수 있음
--  프로그램의 처리 흐름을 노출하지 않아 보안 기능이 좋음
--  프로그램에 대한 유지보수 작업이 편리
--  같은 이름의 프로시저와 함수를 여러 개 생성
----------------------------------------------------------------------------------------
--- 1.Header -->  역할 : 선언 (Interface역할, cpp방식)
--     여러 PROCEDURE 선언 가능
CREATE OR REPLACE PACKAGE emp_info AS -- 하위 프로시저들을 포함한 패키지(클래스개념)
    PROCEDURE all_emp_info; -- 모든 사원의 정보를 가져오는 프로시저
    PROCEDURE all_sal_info; -- 부서별 급여 정보
    PROCEDURE dept_emp_info(p_deptno IN NUMBER); -- 특정 부서의 사원 정보
END;

CREATE OR REPLACE PACKAGE BODY emp_info AS
    -----------------------------------------------------------------
    -- 모든 사원의 사원 정보(사번, 이름, 입사일)
    -- 1. CURSOR  : emp_cursor
    -- 2. FOR  IN
    -- 3. DBMS  -> 각각 줄 바꾸어 사번,이름,입사일
    -- 4. 기본적 EXCEPTION 처리
    -----------------------------------------------------------------
    PROCEDURE all_emp_info
    IS 
        CURSOR emp_cursor IS
        SELECT empno, ename, to_char(hiredate, 'YYYY/MM/DD') AS hiredate
        FROM emp;
    BEGIN
        DBMS_OUTPUT.ENABLE;
        DBMS_OUTPUT.PUT_LINE('사번' || CHR(9) || '이름' || CHR(9) || '입사일');
        
        FOR f_Emp IN emp_cursor LOOP
            DBMS_OUTPUT.PUT_LINE(f_Emp.empno || CHR(9) || f_Emp.ename || CHR(9) || f_Emp.hiredate);
        END LOOP;
        
        EXCEPTION
            WHEN OTHERS THEN
                DBMS_OUTPUT.PUT_LINE(SQLERRM || '에러');
    END;
    -----------------------------------------------------------------------
    -- 모든 사원의 부서별 급여 정보
    -- 1. CURSOR  : empdept_cursor
    -- 2. FOR IN
    -- 3. DBMS  -> 각각 줄 바꾸어 부서명 ,전체급여평균 , 최대급여금액 , 최소급여금액
   -----------------------------------------------------------------------
    PROCEDURE all_sal_info
    IS
        CURSOR empdept_cursor IS
        SELECT d.dname AS dname, ROUND(AVG(e.sal),3) AS avg_sal, MAX(e.sal) AS max_sal, MIN(e.sal) AS min_sal
        FROM emp e, dept d
        WHERE e.deptno = d.deptno
        GROUP BY d.dname;
    
    BEGIN
        dbms_output.enable;
        dbms_output.put_line('부서명' || CHR(9) || '급여평균' || CHR(9) ||  '최대급여' || CHR(9) || '최소급여');
        FOR depts IN empdept_cursor LOOP
            dbms_output.put_line(depts.dname || CHR(9) || depts.avg_sal || CHR(9) || depts.max_sal || CHR(9) || depts.min_sal);
        END LOOP;
        
        EXCEPTION
            WHEN OTHERS THEN
                dbms_output.put_line('에러에러');
    END;
    -----------------------------------------------------------------
    -- 특정 부서의 사원 정보
    -- 사번, 성명, 입사일
    -----------------------------------------------------------------
    PROCEDURE dept_emp_info(
        p_deptno IN NUMBER)
    IS
        CURSOR emp_cursor IS
        SELECT empno, ename, TO_CHAR(hiredate, 'YYYY/MM/DD') AS hiredate
        FROM emp
        WHERE deptno = p_deptno;
    BEGIN
        dbms_output.enable;
        
        DBMS_OUTPUT.PUT_LINE('부서번호 : ' || p_deptno);
        DBMS_OUTPUT.PUT_LINE('사번' || CHR(9) || '성명' || CHR(9) || '입사일');
        FOR f_emp IN emp_cursor LOOP
            DBMS_OUTPUT.PUT_LINE(f_emp.empno || CHR(9) || f_emp.ename || CHR(9) || f_emp.hiredate);
        END LOOP;
    END;
END;
