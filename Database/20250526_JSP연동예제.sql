-- Emp_Info3 ���ν��� ����
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
    --���
    DBMS_OUTPUT.PUT_LINE('�����ȣ:'||v_empno);
    DBMS_OUTPUT.PUT_LINE('����̸�:'||v_ename);
    DBMS_OUTPUT.PUT_LINE('����޿�:'||p_sal);
END;

-- MEMBER1 ���̺� ����
CREATE TABLE MEMBER1
        ( id                      VARCHAR2(10) 
         CONSTRAINT PK_MEMBER1_ID PRIMARY KEY,    -- ID
         password       VARCHAR2(20),    -- ��й�ȣ
         name                VARCHAR2(100),   -- �̸� 
         reg_date         Date                   -- ����
       )  TABLESPACE "SYSTEM" ;

Insert into SCOTT.MEMBER1 (ID,password,NAME,REG_DATE) 
values ('aa','1234','������',to_date('25/05/26' ,'YY/MM/DD'));