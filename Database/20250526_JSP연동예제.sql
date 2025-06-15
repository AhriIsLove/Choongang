-- Emp_Info3 프로시저 생성
create or replace PROCEDURE Emp_Info3
(p_empno IN emp.empno%type
,p_sal OUT emp.sal%type)
IS
    v_empno emp.empno%type;
    v_ename emp.ename%type;
BEGIN
    DBMS_OUTPUT.ENABLE;
    SELECT empno, ename, sal 
    INTO v_empno, v_ename, p_sal
    FROM emp 
    WHERE empno = p_empno;
    --출력
    DBMS_OUTPUT.PUT_LINE('사원번호:'||v_empno);
    DBMS_OUTPUT.PUT_LINE('사원이름:'||v_ename);
    DBMS_OUTPUT.PUT_LINE('사원급여:'||p_sal);
END;

-- MEMBER1 테이블 생성
CREATE TABLE MEMBER1
        ( id                      VARCHAR2(10) 
         CONSTRAINT PK_MEMBER1_ID PRIMARY KEY,    -- ID
         password       VARCHAR2(20),    -- 비밀번호
         name                VARCHAR2(100),   -- 이름 
         reg_date         Date                   -- 일자
       )  TABLESPACE "SYSTEM" ;

Insert into SCOTT.MEMBER1 (ID,password,NAME,REG_DATE) 
values ('aa','1234','김유신',to_date('25/05/26' ,'YY/MM/DD'));