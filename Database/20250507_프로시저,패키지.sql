CREATE OR REPLACE PROCEDURE DeptEmpSearch1(
p_deptno IN emp.deptno%type) -- 20���� ����� �����߻�
IS
    v_empno emp.empno%type;
    v_ename emp.ename%type;
BEGIN
    DBMS_OUTPUT.ENABLE;
    
    SELECT empno, ename
    INTO v_empno, v_ename -- �����߻����� : 2�� �̻��� ����� ��Ÿ�� �� �����߻� ����
    FROM emp
    WHERE deptno = p_deptno;
    
    DBMS_OUTPUT.PUT_LINE('��� : ' || v_empno);
    DBMS_OUTPUT.PUT_LINE('�̸� : ' || v_ename);
END;

-- RowType���� ����
CREATE OR REPLACE PROCEDURE DeptEmpSearch2(
p_deptno IN emp.deptno%type)
IS
--    v_empno emp.empno%type;
--    v_ename emp.ename%type;
    v_emp   emp%ROWTYPE; -- emp ���̺� ������ ������ ���� -- �굵 ��� �ϳ��� ���� ����...
    
BEGIN
    DBMS_OUTPUT.ENABLE;
    
--    SELECT empno, ename
--    INTO v_empno, v_ename -- �����߻����� : 2�� �̻��� ����� ��Ÿ�� �� �����߻� ����
    SELECT *
    INTO v_emp
    FROM emp
    WHERE deptno = p_deptno;
    
    DBMS_OUTPUT.PUT_LINE('��� : ' || v_emp.empno);
    DBMS_OUTPUT.PUT_LINE('�̸� : ' || v_emp.ename);
END;

-- Multi Row �ذ���
-- 1. EXCEPTION ó�� (��)
-- 2. CURSOR�� ���
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
    
    DBMS_OUTPUT.PUT_LINE('��� : ' || v_emp.empno);
    DBMS_OUTPUT.PUT_LINE('�̸� : ' || v_emp.ename);
    
    -- ���� ���� ó�� : Multi Row Error(���� INTO �� �䱸�� �ͺ��� ���� ���� ���� ������
    EXCEPTION WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('ERROR CODE 1 : ' || TO_CHAR(SQLCODE));
        DBMS_OUTPUT.PUT_LINE('ERROR CODE 2 : ' || SQLCODE);
        DBMS_OUTPUT.PUT_LINE('ERROR MESSAGE : ' || SQLERRM);
END;

--------------------------------------------------------------------------------
----  ***    cursor    ***
--- 1.���� : Oracle Server�� SQL���� �����ϰ� ó���� ������ �����ϱ� ����
--          "Private SQL Area" �̶�� �ϴ� �۾������� �̿�
--          �� ������ �̸��� �ο��ϰ� ����� ������ ó���� �� �ְ� ���ִµ� �̸� CURSOR
-- 2. ���� : Implicit(��������) CURSOR -> DML���� SELECT���� ���� ���������� ����
--          Explicit(�������) CURSOR -> ����ڰ� �����ϰ� �̸��� �����ؼ� ���
-- 3.attribute (**)
--   1) SQL%ROWCOUNT : ���� �ֱ��� SQL���� ���� ó���� Row ��
--   2) SQL%FOUND    : ���� �ֱ��� SQL���� ���� ó���� Row�� ������ �� ���̻��̸� True
--   3) SQL%NOTFOUND : ���� �ֱ��� SQL���� ���� ó���� Row�� ������ ������True
-- 4. 4�ܰ� **
--   1) DECLARE �ܰ� : Ŀ���� �̸��� �ο��ϰ� Ŀ�������� ������ SELECT���� ���������ν� CURSOR�� ����
--   2) OPEN �ܰ� : OPEN���� �����Ǵ� ������ �����ϰ�, SELECT���� ����
--   3) FETCH �ܰ� : CURSOR�κ��� Pointer�� �����ϴ� Record�� ���� ������ ����
--   4) CLOSE �ܰ� : Record�� Active Set�� �ݾ� �ְ�, �ٽ� ���ο� Active Set������� OPEN�� �� �ְ� ����
--------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE show_emp3(
p_empno IN emp.empno%type)
IS
    v_ename emp.ename%type;
    v_sal   emp.sal%type;
    v_job   emp.job%type;
    
    -- 1) DECLARE �ܰ� : Ŀ�� ��ü ����
    CURSOR emp_cursor IS -- �� ����
    SELECT ename, job, sal
    FROM emp
    WHERE empno LIKE p_empno || '%';
    
BEGIN
    -- 2) OPEN �ܰ� : Ŀ�� ��ü ����(���,new)
    OPEN emp_cursor;
    DBMS_OUTPUT.PUT_LINE('�̸�' || CHR(9) || '����' || CHR(9) || '�޿�');
    DBMS_OUTPUT.PUT_LINE('---------------------------');
    
    LOOP
        -- 3) FETCH �ܰ� : Ŀ������ �ϳ��� ó��
        FETCH emp_cursor INTO v_ename, v_job, v_sal;
        EXIT WHEN emp_cursor%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(v_ename || CHR(9) || v_job || CHR(9) || v_sal);
    END LOOP;
    DBMS_OUTPUT.PUT_LINE(emp_cursor%ROWCOUNT||'���� �� ����');
    
    -- 4) CLOSE �ܰ� : Ŀ�� ��ü ����(�ݱ�,nulló��)
    CLOSE emp_cursor;
END;
---------------------------------------------------------
-- EXECUTE ���� �̿��� �Լ��� �����մϴ�.
-- SQL>EXECUTE show_emp3(7900);
--EXEC show_emp3(3);
---------------------------------------------------------
-----------------------------------------------------
-- Fetch �� ***
-- SQL> EXECUTE  Cur_sal_Hap (dept_no);
-- CURSOR �� �̿� ����
-- �μ���ŭ �ݺ�
-- 	�μ��� : �λ���
-- 	�ο��� : 5
-- 	�޿��� : 5000
--  Cursor : dept_sum
-----------------------------------------------------
CREATE OR REPLACE PROCEDURE Cur_sal_Hap( -- ���� ���ν���
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
    
    DBMS_OUTPUT.PUT_LINE('�μ��� : ' || v_deptname);
    DBMS_OUTPUT.PUT_LINE('�ο��� : ' || dept_sum%ROWCOUNT);
    DBMS_OUTPUT.PUT_LINE('�޿��� : ' || v_sumSal);
    
    CLOSE dept_sum;
END;
--EXEC Cur_sal_Hap(20);

CREATE OR REPLACE PROCEDURE Cur_sal_Hap2( -- ������ ���ν���
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
        DBMS_OUTPUT.PUT_LINE('�μ��� : ' || v_dname);
        DBMS_OUTPUT.PUT_LINE('�ο��� : ' || v_count);
        DBMS_OUTPUT.PUT_LINE('�޿��� : ' || v_sumSal);
    END LOOP;
    
    CLOSE dept_sum;
END;
--EXEC Cur_sal_Hap2(1);

------------------------------------------------------------------------
-- FOR���� ����ϸ� Ŀ���� OPEN, FETCH, CLOSE�� �ڵ� �߻��ϹǷ�
-- ���� ����� �ʿ䰡 ����, ���ڵ� �̸��� �ڵ�
-- ����ǹǷ� ���� ������ �ʿ䰡 ����.
-----------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE ForCursor_sal_Hap( -- ������ ���ν���
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
    --OPEN, FETCH, CLOSE�� �ڵ��߻�
    -- ����� FOR���� �����
    FOR emp_list IN dept_sum LOOP
        DBMS_OUTPUT.PUT_LINE('�μ��� : ' || emp_list.dname);
        DBMS_OUTPUT.PUT_LINE('�ο��� : ' || emp_list.cnt);
        DBMS_OUTPUT.PUT_LINE('�޿��� : ' || emp_list.sumSal);
    END LOOP;
    
    EXCEPTION WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('���� : ' || SQLERRM);
END;

-------------------------------------------------------------------------------
--����Ŭ PL/SQL�� ���� �Ͼ�� ��� ���ܸ� �̸� ������ ��������, 
--�̷��� ���ܴ� �����ڰ� ���� ������ �ʿ䰡 ����.
--�̸� ���ǵ� ������ ����
-- NO_DATA_FOUND : SELECT���� �ƹ��� ������ ���� ��ȯ���� ���� ��
-- DUP_VAL_ON_INDEX : UNIQUE ������ ���� �÷��� �ߺ��Ǵ� ������ INSERT �� ��
-- ZERO_DIVIDE : 0���� ���� ��
-- INVALID_CURSOR : �߸��� Ŀ�� ����
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
    
    DBMS_OUTPUT.PUT_LINE('��� : ' || v_emp.empno);
    DBMS_OUTPUT.PUT_LINE('�̸� : ' || v_emp.ename);
    DBMS_OUTPUT.PUT_LINE('�μ���ȣ : ' || v_emp.deptno);
    
    EXCEPTION 
        WHEN DUP_VAL_ON_INDEX THEN 
            DBMS_OUTPUT.PUT_LINE('�ߺ� ������ ���� ����');
            DBMS_OUTPUT.PUT_LINE('DUP_VAL_ON_INDEX ����');
        WHEN TOO_MANY_ROWS THEN 
            DBMS_OUTPUT.PUT_LINE('TOO_MANY_ROWS ����');
        WHEN NO_DATA_FOUND THEN 
            DBMS_OUTPUT.PUT_LINE('NO_DATA_FOUND ����');
        WHEN OTHERS THEN 
            DBMS_OUTPUT.PUT_LINE('��Ÿ ����');
END;

--------------------------------------------------------------------------
----   Procedure :  in_emp
----   Action    : emp Insert
----   1. Error ����
---      1) DUP_VAL_ON_INDEX  :  PreDefined --> Oracle ���� Error
---      2) User Defind Error :  lowsal_err (�����޿� ->1500)
--------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE in_emp(
    p_name      IN emp.ename%type,
    p_sal       IN emp.sal%type,
    p_job       IN emp.job%type,
    p_deptno    IN emp.deptno%type)
IS
    v_empno     emp.empno%type;
    
    -- ������ Defind Error
    lowsal_err  EXCEPTION; -- Ŀ���� ���� ����
BEGIN
    DBMS_OUTPUT.ENABLE;
    SELECT MAX(empno)+1
    INTO v_empno
    FROM emp;
    
    IF p_sal >= 2000 THEN
        INSERT INTO emp(empno, ename, sal, job, deptno, hiredate)
        VALUES (v_empno, p_name, p_sal, p_job, 10, SYSDATE);
    ELSE -- 2) ������ Defind Error :  lowsal_err (�����޿� ->1500)
        RAISE lowsal_err; -- Ŀ���� ���� �߻�
    END IF;
    
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('�ߺ� ������ ename ����');
            DBMS_OUTPUT.PUT_LINE('DUP_VAL_ON_INDEX ����');
        WHEN lowsal_err THEN
            DBMS_OUTPUT.PUT_LINE('�޿� ���� ����');
            DBMS_OUTPUT.PUT_LINE('�޿��� 2000 �̻� �Է����ּ���');
END;

-- ��� Ʈ���� Ȱ��ȭ/��Ȱ��ȭ ���ν���
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
----   1. Error ����
---      1) DUP_VAL_ON_INDEX  :  PreDefined --> Oracle ���� Error
---      2) User Defind Error :  highsal_err (�ְ�޿� ->9000 �̻� ���� �߻�)
---   2. ��Ÿ����
---      1) emp.ename�� Unique ���������� �ɷ� �ִٰ� ����
---      2) parameter : p_name, p_sal, p_job
---      3) PK(empno) : Max ��ȣ �Է�
---      3) hiredate     : �ý��� ��¥ �Է�
---      4) emp(empno,ename,sal,job,hiredate)  --> 5 Column�Է��Ѵ� ����
---      5) DUP_VAL_ON_INDEX --> �ߺ� ������ ename ���� �մϴ� / DUP_VAL_ON_INDEX ���� �߻�
---          highsal_err  -->ERROR!!!-������ �޿��� �ʹ� �����ϴ�. 9000�������� �ٽ� �Է��ϼ���
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
            dbms_output.put_line('�ߺ� ������ ename ���� �մϴ�');
        WHEN highsal_err THEN
            dbms_output.put_line('ERROR!!!-������ �޿��� �ʹ� �����ϴ�. 9000�������� �ٽ� �Է��ϼ���');
        WHEN OTHERS THEN
            dbms_output.put_line('��Ÿ ����');
END;

--------------------------------------------------------------
--  20250507 HW01
-- 1.  PROCEDURE update_empno
-- 2.  parameter -> p_empno, p_job
-- 3.  �ش� empno�� ���õǴ� �������(Like) job�� ����� ������ p_job���� ������Ʈ
-- 4. Update -> emp ����
-- 5.               �Ի����� ��������
-- 6.  �⺻�� EXCEPTION ó��
-- 7.  CURSOR emp_list --> For ó��
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
    DBMS_OUTPUT.PUT_LINE('��� : ' || mEmp.empno);
        UPDATE emp SET job = p_job, hiredate = sysdate WHERE empno = mEmp.empno;
    END LOOP;
    
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('����');
END;
--------------------------------------------------------------
--  20250507 HW02
-- 1.  PROCEDURE Delete_Emp3
-- 2.  parameter -> p_empno, p_result('0' , '1')
-- 3.                        p_result -> ������ 1
-- 4.                                    ���н� 0
-- 5. ���
--   �����ȣ : 1600
--   ����̸� : ȫ�浿
--   �� �� �� : 
--   ��   �� : 1
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
    DBMS_OUTPUT.PUT_LINE('�����ȣ : ' || p_empno);
    DBMS_OUTPUT.PUT_LINE('����̸� : ' || v_ename);
    DBMS_OUTPUT.PUT_LINE('�Ի��� : ' || v_hiredate);
    p_result := 1;
    DBMS_OUTPUT.PUT_LINE('��� : ' || p_result);
    
    EXCEPTION 
        WHEN OTHERS THEN
            p_result := 0;
            DBMS_OUTPUT.PUT_LINE('��� : ' || p_result);
END;

---------------------------------------------------------------------------------------
-----    Package
--  ���� ����ϴ� ���α׷��� ������ ���ȭ
--  ���� ���α׷��� ���� ���� �� �� ����
--  ���α׷��� ó�� �帧�� �������� �ʾ� ���� ����� ����
--  ���α׷��� ���� �������� �۾��� ��
--  ���� �̸��� ���ν����� �Լ��� ���� �� ����
----------------------------------------------------------------------------------------
--- 1.Header -->  ���� : ���� (Interface����, cpp���)
--     ���� PROCEDURE ���� ����
CREATE OR REPLACE PACKAGE emp_info AS -- ���� ���ν������� ������ ��Ű��(Ŭ��������)
    PROCEDURE all_emp_info; -- ��� ����� ������ �������� ���ν���
    PROCEDURE all_sal_info; -- �μ��� �޿� ����
    PROCEDURE dept_emp_info(p_deptno IN NUMBER); -- Ư�� �μ��� ��� ����
END;

CREATE OR REPLACE PACKAGE BODY emp_info AS
    -----------------------------------------------------------------
    -- ��� ����� ��� ����(���, �̸�, �Ի���)
    -- 1. CURSOR  : emp_cursor
    -- 2. FOR  IN
    -- 3. DBMS  -> ���� �� �ٲپ� ���,�̸�,�Ի���
    -- 4. �⺻�� EXCEPTION ó��
    -----------------------------------------------------------------
    PROCEDURE all_emp_info
    IS 
        CURSOR emp_cursor IS
        SELECT empno, ename, to_char(hiredate, 'YYYY/MM/DD') AS hiredate
        FROM emp;
    BEGIN
        DBMS_OUTPUT.ENABLE;
        DBMS_OUTPUT.PUT_LINE('���' || CHR(9) || '�̸�' || CHR(9) || '�Ի���');
        
        FOR f_Emp IN emp_cursor LOOP
            DBMS_OUTPUT.PUT_LINE(f_Emp.empno || CHR(9) || f_Emp.ename || CHR(9) || f_Emp.hiredate);
        END LOOP;
        
        EXCEPTION
            WHEN OTHERS THEN
                DBMS_OUTPUT.PUT_LINE(SQLERRM || '����');
    END;
    -----------------------------------------------------------------------
    -- ��� ����� �μ��� �޿� ����
    -- 1. CURSOR  : empdept_cursor
    -- 2. FOR IN
    -- 3. DBMS  -> ���� �� �ٲپ� �μ��� ,��ü�޿���� , �ִ�޿��ݾ� , �ּұ޿��ݾ�
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
        dbms_output.put_line('�μ���' || CHR(9) || '�޿����' || CHR(9) ||  '�ִ�޿�' || CHR(9) || '�ּұ޿�');
        FOR depts IN empdept_cursor LOOP
            dbms_output.put_line(depts.dname || CHR(9) || depts.avg_sal || CHR(9) || depts.max_sal || CHR(9) || depts.min_sal);
        END LOOP;
        
        EXCEPTION
            WHEN OTHERS THEN
                dbms_output.put_line('��������');
    END;
    -----------------------------------------------------------------
    -- Ư�� �μ��� ��� ����
    -- ���, ����, �Ի���
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
        
        DBMS_OUTPUT.PUT_LINE('�μ���ȣ : ' || p_deptno);
        DBMS_OUTPUT.PUT_LINE('���' || CHR(9) || '����' || CHR(9) || '�Ի���');
        FOR f_emp IN emp_cursor LOOP
            DBMS_OUTPUT.PUT_LINE(f_emp.empno || CHR(9) || f_emp.ename || CHR(9) || f_emp.hiredate);
        END LOOP;
    END;
END;
