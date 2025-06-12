-- Backup Dir ����
CREATE OR REPLACE DIRECTORY mdBackup2 AS 'C:\oraclexe\backup'; -- ������ ���� ����(mdBackup2��� �̸����� ������)
GRANT read, write ON DIRECTORY mdBackup2 TO scott; -- �ش� ������ scott���� ���� �ο���

-- export : cmd���� export �� ��η� �̵� �� export �ڵ� ����
-- import : cmd���� export �� dmp������ �ִ� ��η� �̵� �� import �ڵ� ����
-- import�� ��� ��ü�� �̸��� ������ ��ü�� ������ �ݿ����� ����

-- Oracle ��ü Backup  (scott)
EXPDP scott/tiger DIRECTORY=mdBackup2 DUMPFILE=scott.dmp; -- �������� Export
-- Oracle ��ü ����
IMPDP scott/tiger DIRECTORY=mdBackup2 DUMPFILE=scott.dmp; -- �������� Import

-- Oracle �κ� Backup��  �κ� Restore
-- bat���Ͽ� �̰͸� �ְ� �����ص� �ߵ�
exp scott/tiger file=dept_second.dmp tables=dept_second
exp scott/tiger file=address.dmp tables=address
imp scott/tiger file=dept_second.dmp tables=dept_second
imp scott/tiger file=address.dmp tables=address

----------------------------------------------------------------------------------------
-------                     Trigger 
--  1. ���� : � ����� �߻����� �� ���������� ����ǵ��� �����ͺ��̽��� ����� ���ν���
--           Ʈ���Ű� ����Ǿ�� �� �̺�Ʈ �߻��� �ڵ����� ����Ǵ� ���ν��� 
--           Ʈ���Ÿ� ���(Triggering Event), �� ����Ŭ DML ���� INSERT, DELETE, UPDATE�� 
--           ����Ǹ� �ڵ����� ����
--  2. ����Ŭ Ʈ���� ��� ����
--    1) �����ͺ��̽� ���̺� �����ϴ� �������� ���� ���Ἲ�� ������ ���Ἲ ���� ������ ���� ���� �����ϴ� ��� 
--    2) �����ͺ��̽� ���̺��� �����Ϳ� ����� �۾��� ����, ���� 
--    3) �����ͺ��̽� ���̺� ����� ��ȭ�� ���� �ʿ��� �ٸ� ���α׷��� �����ϴ� ��� 
--    4) ���ʿ��� Ʈ������� �����ϱ� ���� 
--    5) �÷��� ���� �ڵ����� �����ǵ��� �ϴ� ��� 
----------------------------------------------------------------------------------------
--Ʈ���� ����
CREATE OR REPLACE TRIGGER triger_test
BEFORE 
UPDATE ON dept -- ���̺��� ������Ʈ �ϱ� ����
FOR EACH ROW -- old,new ��� �ϱ� ����
BEGIN -- BEGIN~END �ڵ� �ۼ��� ctrl+enter����!
    DBMS_OUTPUT.ENABLE;
    DBMS_OUTPUT.PUT_LINE('���� �� Į�� �� : ' || :old.dname);
    DBMS_OUTPUT.PUT_LINE('���� �� Į�� �� : ' || :new.dname);
END;

-- ��ܸ޴� - ���� - DBMS��� - scott���� - Ȯ��
UPDATE dept SET dname='ȸ��5��' WHERE deptno = 71;
UPDATE dept SET loc='����' WHERE deptno = 71; -- �̶��� Ʈ���� �߻���
COMMIT;

----------------------------------------------------------
--����Work2 ) emp Table�� �޿��� ��ȭ��
--       ȭ�鿡 ����ϴ� Trigger �ۼ�( emp_sal_change)
--       emp Table ������
--      ���� : �Է½ô� empno�� 0���� Ŀ����

--��°�� ����
--     �����޿�  : 10000
--     ��  �޿�  : 15000
 --    �޿� ���� :  5000
----------------------------------------------------------
CREATE OR REPLACE TRIGGER emp_sal_change
BEFORE DELETE OR INSERT OR UPDATE ON emp
FOR EACH ROW
    WHEN(new.empno > 0) -- Ʈ���Ź��� : ���⼱ ':'�� ����ϸ� �ȵ�
    DECLARE
        sal_diff NUMBER; -- ��������
BEGIN
    sal_diff := :new.sal - :old.sal; -- Ʈ���Ź��� : ������ ���� �ο��Ҷ� ':='���� ����
    SELECT :new.sal - :old.sal INTO sal_diff
    DBMS_OUTPUT.ENABLE;
    DBMS_OUTPUT.PUT_LINE('�����޿� : ' || :old.sal);
    DBMS_OUTPUT.PUT_LINE('�� �޿� : ' || :new.sal);
    DBMS_OUTPUT.PUT_LINE('�޿����� : ' || sal_diff);
END;

UPDATE emp SET sal = 1001 WHERE empno = 7369;

-------------------------------------------------------------------------------
--  EMP ���̺� INSERT,UPDATE,DELETE������ �Ϸ翡 �� ���� ROW�� �߻��Ǵ��� ����
--  ���� ������ EMP_ROW_AUDIT�� 
--  ID ,����� �̸�, �۾� ����,�۾� ���ڽð��� �����ϴ� 
--  Ʈ���Ÿ� �ۼ�
-------------------------------------------------------------------------------
-- 1. ������ ����
CREATE SEQUENCE emp_row_seq;
SELECT emp_row_seq.NEXTVAL FROM dual;--������ Ȱ��ȭ

-- 2. Audit ���̺� ���� : ���, ����, ������ ���̺�
CREATE TABLE emp_row_audit(
    e_id    NUMBER(6)       CONSTRAINT emp_row_pk PRIMARY KEY,
    e_name  VARCHAR2(30),
    e_gubun VARCHAR2(10),
    e_date  DATE
);
-- 3. Ʈ���� ����
-- DDL���õ� �ý����� ���̺��� Ʈ���� ��Ű�� �����ҵ�
CREATE OR REPLACE TRIGGER emp_row_aud
AFTER INSERT OR UPDATE OR DELETE ON emp -- emp ���̺��� ������ �Ͼ �Ŀ�
FOR EACH ROW
BEGIN
    --IF�� ���� ����
    --IF THEN
    --ELSIF
    --END IF;
    IF INSERTING THEN -- �����̶��
        INSERT INTO emp_row_audit
            VALUES(emp_row_seq.NEXTVAL, :new.ename, 'inserting', sysdate);
    ELSIF UPDATING THEN -- �����̶��
        INSERT INTO emp_row_audit
            VALUES(emp_row_seq.NEXTVAL, :new.ename, 'updating', sysdate);
    ELSIF DELETING THEN -- �������
        INSERT INTO emp_row_audit
            VALUES(emp_row_seq.NEXTVAL, :new.ename, 'deleting', sysdate);
    END IF;
END;

--integrity constraint (SCOTT.FK_DEPTNO) violated - parent key not found
INSERT INTO emp(empno, ename, sal, deptno) VALUES (3000, '������', 3500, 51); -- ���� : emp.deptno�� �ܷ�Ű�ε� dept.deptno�� 51�� ���� ����
INSERT INTO emp(empno, ename, sal, deptno) VALUES (3000, '������', 3500, 52);
INSERT INTO emp(empno, ename, sal, deptno) VALUES (3100, '�ȵ���', 3500, 52);
UPDATE emp SET ename = '����' WHERE empno = 1601;

------------------------------------------------------------------------
---    PL/SQL�� ����
---   1. Oracle���� �����ϴ� ���α׷��� ����� Ư���� ������ SQL�� Ȯ��
---   2. PL/SQL Block������ SQL�� DML(������ ���۾�)���� Query(�˻���)��, 
---      �׸��� ������ ���(IF, LOOP) ���� ����Ͽ� ���������� ���α׷����� �����ϰ� 
---      �� ������  Ʈ����� ���
---
---   1) ���� 
---      ���α׷� ������ ���ȭ : ������ ���α׷��� �ǹ��ְ� �� ���ǵ� ���� Block ����
---      ��������  : ���̺�� Į���� ������ Ÿ���� ������� �ϴ� �������� ������ ����
---      ����ó��  : Exception ó�� ��ƾ�� ����Ͽ� Oracle ���� ������ ó��
---      �̽ļ�    : Oracle�� PL/SQL�� �����ϴ¾ ȣ��Ʈ�ε� ���α׷� �̵� ����
---      ���� ��� : ���� ���α׷��� ������ ���
--------------------------------------------------------------------------
-----------------------------  PL / SQL ---------------------------------
--------------------------------------------------------------------------
--[����1] Ư���� ���� ������ 7%�� ����ϴ� Function tax�� �ۼ�
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
-- ���� Work02
-- 1) Procedure Insert_emp
-- 2) parameter(IN) -> p_empno, p_ename, p_job, p_mgr, p_sal, p_deptno
-- 3) ������ : v_comm
-- 4) ����
--        1) p_job MANAGER -> v_comm(1000)
--        2) p_job ELSE         -> v_comm(150)
--        3) emp TBL Insert(hiredate -> ��������)
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
--  Procedure up_emp ���� ���
-- SQL> EXECUTE up_emp(1200);  -- parameter ��� p_empno 
-- ���       : �޿� �λ� ����
--               ���۹���
--   ����     :   v_job(����)
--                  v_up����)

-- ���� 1) job = SALE����         v_up : 10
--           IF              v_job LIKE 'SALE%' THEN
--     2)            MAN              v_up : 7  
--     3)                                v_up : 5
--   job�� ���� �޿� �λ��� ����  sal = sal+sal*v_up/100
-- Ȯ�� : DB -> TBL
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
-- �����ȣ : 5555
-- ����̸� : 55
-- �� �� �� : 81/12/03
-- ������ ���� ����
--  1. Parameter : ��� �Է�
--  2. ��� �̿��� �����ȣ ,����̸� , �� �� �� ���
--  3. ��� �ش��ϴ� ������ ���� 
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
    DBMS_OUTPUT.PUT_LINE('�����ȣ : ' || v_empno);
    DBMS_OUTPUT.PUT_LINE('����̸� : ' || v_ename);
    DBMS_OUTPUT.PUT_LINE('�� �� �� : ' || v_hiredate);
    
    DELETE FROM emp WHERE empno = p_empno;
    DBMS_OUTPUT.PUT_LINE('������ ���� ����');
    COMMIT;
END;

------------------------------------------------------------
--  EMP ���̺��� ����� �Է¹޾� �ش� ����� �޿��� ���� ������ ����.
-- �޿��� 2000 �̸��̸� �޿��� 6%, 
-- �޿��� 3000 �̸��̸� 8%, 
-- 5000 �̸��̸� 10%, 
-- �� �̻��� 15%�� ����
--- FUNCTION  emp_tax3
-- 1) Parameter : ��� p_empno
--      ����     :   v_sal(�޿�)
--                     v_pct(����)
-- 2) ����� ������ �޿��� ����
-- 3) �޿��� ������ ���� ��� 
-- 4) ��� �� �� Return   number
-------------------------------------------------------------
CREATE OR REPLACE FUNCTION emp_tax3(p_empno in emp.empno%type)
RETURN number
IS
    v_sal   emp.sal%type;
    v_pct   NUMBER(5,2);
BEGIN
    SELECT sal INTO v_sal FROM emp WHERE empno = p_empno;
    
-- 3) �޿��� ������ ���� ���
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
