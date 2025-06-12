-------------------------------------------------------------------------------
-----      INDEX      ***
--  �ε����� SQL ��ɹ��� ó�� �ӵ��� ���(*) ��Ű�� ���� Į���� ���� �����ϴ� ��ü
--  �ε����� ����Ʈ�� �̿��Ͽ� ���̺� ����� �����͸� ���� �׼����ϱ� ���� �������� ���
--  [1]�ε����� ����
--   1)���� �ε��� : ������ ���� ������ Į���� ���� �����ϴ� �ε����� ��� �ε��� Ű��
--           ���̺��� �ϳ��� ��� ����
CREATE UNIQUE INDEX idx_dept_name
ON department(dname);

INSERT INTO department VALUES(300, '�̰�����', 10, 'kkk');
INSERT INTO department(deptno, dname, college, loc) VALUES(301, '�̰�����', 10, 'kkk2'); -- UNIQUE CONSTRAINT violated

-- ����� �ε��� birthdate  --> constraint  X   , ���ɿ��� ���� ��ħ 
-- 2)����� �ε���
-- ��) �л� ���̺��� birthdate Į���� ����� �ε����� �����Ͽ���
CREATE INDEX idx_stud_birthdate
ON student(birthdate);

INSERT INTO student(studno, name, idnum, birthdate) VALUES(30102, '������', '8012301036614', '84/09/16');

SELECT * FROM student WHERE birthdate = '84/09/16'; -- 'F10' -> TABLE ACCESS -> INDEX
SELECT * FROM student WHERE grade = 2; -- 'F10' -> TABLE ACCESS -> �ε��� ����

CREATE INDEX idx_stud_dno_grade
ON student(deptno, grade);

SELECT * FROM student WHERE grade = 2 AND deptno = 101;
SELECT * FROM student WHERE deptno = 101 AND grade = 2;
SELECT * FROM student WHERE deptno = 101 AND tel LIKE '%5%' AND grade = 2;

--- Optimizer
--- 1) RBO  2) CBO
-- RBO ����
-- OPTIMIZER ��� Ȯ��
SHOW PARAMETER OPTIMIZER_MODE;
--SELECT NAME, VALUE, ISDEFAULT, ISMODIFIED, DESCRIPTION
--FROM V$SYSTEM_PARAMETER
--WHERE NAME LIKE '%optimizer_mode%';

--���� �󿡼� ������ ��
ALTER SESSION SET optimizer_mode = rule; -- RBO
ALTER SESSION SET optimizer_mode = CHOOSE; -- CBO
ALTER SESSION SET optimizer_mode = first_rows; -- CBO
ALTER SESSION SET optimizer_mode = ALL_ROWS; -- CBO : DEFAULT

SELECT ename FROM emp WHERE ename LIKE 'S%';

-- [2]�ε����� ȿ������ ��� 
--   1) WHERE ���̳� ���� ���������� ���� ���Ǵ� Į��
--   2) ��ü �������߿��� 10~15%�̳��� �����͸� �˻��ϴ� ���
--   3) �� �� �̻��� Į���� WHERE���̳� ���� ���ǿ��� ���� ���Ǵ� ���
--   4) ���̺� ����� �������� ������ �幮 ���
--   5) ���� �� ���� ���� ���Ե� ���, ���� �������� ���� ���ԵȰ��
---------------------------------------------------------------
-- �л� ���̺� ������ PK_DEPTNO �ε����� �籸��
ALTER INDEX PK_DEPTNO REBUILD; -- �ε��� �籸��

--  1. Index ��ȸ
SELECT index_name, table_name, column_name
FROM user_ind_columns;

-- 2. Index ����  emp(job)
CREATE INDEX idx_emp_job ON emp(job);

-- 3. ��ȸ
SELECT * FROM emp WHERE job = 'MANAGER'; -- job INDEX O
SELECT * FROM emp WHERE job <> 'MANAGER'; -- job INDEX X
SELECT * FROM emp WHERE job LIKE '%NA%'; -- job INDEX X
SELECT * FROM emp WHERE job LIKE 'MA%'; -- job INDEX O
SELECT * FROM emp WHERE UPPER(job) = 'MANAGER'; -- job INDEX X

--   5)�Լ� ��� �ε���(FBI) function based index
--      ����Ŭ 8i �������� �����ϴ� ���ο� ������ �ε����� Į���� ���� �����̳� �Լ��� ��� ����� 
--      �ε����� ���� ����
--      UPPER(column_name) �Ǵ� LOWER(column_name) Ű����� ���ǵ�
--      �Լ� ��� �ε����� ����ϸ� ��ҹ��� ���� ���� �˻�
CREATE INDEX idx_emp_job_upper ON emp(UPPER(job));
SELECT * FROM emp WHERE UPPER(job) = 'MANAGER'; -- job INDEX X -> O

----------------------------------------------------------------------------------------------------------------
-- Ʈ����� ����  ***
-- ������ �����ͺ��̽����� ����Ǵ� ���� ���� SQL��ɹ��� �ϳ��� ���� �۾� ������ ó���ϴ� ����
-- COMMIT : Ʈ������� �������� ����
--          Ʈ����ǳ��� ��� SQL ��ɹ��� ���� ����� �۾� ������ ��ũ�� ���������� �����ϰ� 
--          Ʈ������� ����
--          �ش� Ʈ����ǿ� �Ҵ�� CPU, �޸� ���� �ڿ��� ����
--          ���� �ٸ� Ʈ������� �����ϴ� ����
--          COMMIT ��ɹ� �����ϱ� ���� �ϳ��� Ʈ����� ������ �����
--          �ٸ� Ʈ����ǿ��� ������ �� ������ �����Ͽ� �ϰ��� ����
 
-- ROLLBACK : Ʈ������� ��ü ���
--            Ʈ����ǳ��� ��� SQL ��ɹ��� ���� ����� �۾� ������ ���� ����ϰ� Ʈ������� ����
--            CPU,�޸� ���� �ش� Ʈ����ǿ� �Ҵ�� �ڿ��� ����, Ʈ������� ���� ����
----------------------------------------------------------------------------------------------------------------

----------------------------------
-- SEQUENCE ***
-- ������ �ĺ���
-- �⺻ Ű ���� �ڵ����� �����ϱ� ���Ͽ� �Ϸù�ȣ ���� ��ü
-- ���� ���, �� �Խ��ǿ��� ���� ��ϵǴ� ������� ��ȣ�� �ϳ��� �Ҵ��Ͽ� �⺻Ű�� �����ϰ��� �Ҷ� 
-- �������� ���ϰ� �̿�
-- ���� ���̺��� ���� ����  -- > �Ϲ������δ� ������ ��� 
----------------------------------
-- 1) SEQUENCE ����
--CREATE SEQUENCE sequence
--[INCREMENT BY n]
--[START WITH n]
--[MAXVALUE n | NOMAXVALUE]
--[MINVALUE n | NOMINVALUE]
--[CYCLE | NOCYCLE]
--[CACHE n | NOCACHE];
--INCREMENT BY n : ������ ��ȣ�� ����ġ�� �⺻�� 1,  �Ϲ������� ?1 ���
--START WITH n : ������ ���۹�ȣ, �⺻���� 1
--MAXVALUE n : ���� ������ �������� �ִ밪
--MAXVALUE n : ������ ��ȣ�� ��ȯ������ ����ϴ� cycle�� ������ ���, MAXVALUE�� ������ �� ���� �����ϴ� ��������
--CYCLE | NOCYCLE : MAXVALUE �Ǵ� MINVALUE�� ������ �� �������� ��ȯ���� ������ ��ȣ�� ���� ���� ����
--CACHE n | NOCACHE : ������ ���� �ӵ� ������ ���� �޸𸮿� ĳ���ϴ� ������ ����, �⺻���� 20

-- 2) SEQUENCE sample ����
CREATE SEQUENCE sample_seq
INCREMENT BY 1 -- 1�� ����
START WITH 10000; -- 10000����

-- ������ ���(���� O)
SELECT sample_seq.NEXTVAL FROM dual;
-- ������ Ȯ��(���� X)
SELECT sample_seq.CURRVAL FROM dual;

-- 3) SEQUENCE sample ����2 --> �� ��� ����
CREATE SEQUENCE dept_dno_seq
INCREMENT BY 1
START WITH 76;

-- 4) SEQUENCE dept_dno_seq�� �̿� dept_second �Է� --> �� ��� ����
INSERT INTO dept_second VALUES(dept_dno_seq.NEXTVAL, 'Acounting', 'NEW YORK');
SELECT dept_dno_seq.CURRVAL FROM dual;
INSERT INTO dept_second VALUES(dept_dno_seq.NEXTVAL, 'ȸ��', '�̴�');
SELECT dept_dno_seq.CURRVAL FROM dual;
INSERT INTO dept_second VALUES(dept_dno_seq.NEXTVAL, '�λ���', '���');
SELECT dept_dno_seq.CURRVAL FROM dual;

--MAX ��ȯ(�����)
INSERT INTO dept_second VALUES((SELECT MAX(DEPTNO) + 1 FROM dept_second), '�濵��', '�븲');
SELECT MAX(DEPTNO) FROM dept_second;

INSERT INTO dept_second VALUES(dept_dno_seq.NEXTVAL, '�λ���8', '���8'); -- ����
SELECT dept_dno_seq.CURRVAL FROM dual;
INSERT INTO dept_second VALUES(dept_dno_seq.NEXTVAL, '�λ���8', '���8');

-- 4) SEQUENCE ����
DROP SEQUENCE SAMPLE_SEQ;

--5)  Data ����(�ý��� īŻ�α�)���� ���� ��ȸ
SELECT sequence_name, min_value, max_value, increment_by
FROM user_sequences;

------------------------------------------------------
----                   Table ����                 ----
------------------------------------------------------
-- 1.Table ����
CREATE TABLE address(
    id NUMBER(3),
    name VARCHAR2(50),
    addr VARCHAR2(100),
    phone VARCHAR2(30),
    email VARCHAR2(100)
);

INSERT INTO address VALUES (1, 'HGDONG', 'SEOUL', '123-4567', 'gdhong@naver.com');

---    Homework
-- ��1) address��Ű��/Data �����ϸ�     addr_second Table ����
CREATE TABLE addr_second
AS
SELECT * FROM address;
-- ��2) address��Ű�� �����ϸ�  Data ���� ���� �ʰ�   addr_seven Table ����
CREATE TABLE addr_seven
AS
SELECT * FROM address WHERE 0=1;
-- ��3) address(�ּҷ�) ���̺��� id, name Į���� �����Ͽ� addr_third ���̺��� �����Ͽ���
CREATE TABLE addr_third
AS
SELECT id, name FROM address;

------------------------------------------------------------------
-----     ������ ����
-- 1. ����ڿ� �����ͺ��̽� �ڿ��� ȿ�������� �����ϱ� ���� �پ��� ������ �����ϴ� �ý��� ���̺��� ����
-- 2. ���� ������ ������ ����Ŭ ������ ����
-- 3. ����Ŭ ������ ����Ÿ���̽��� ����, ����, ����� ����, ������ ���� ���� ������ �ݿ��ϱ� ����
--    ������ ���� �� ����
-- 4. ����Ÿ���̽� �����ڳ� �Ϲ� ����ڴ� �б� ���� �信 ���� ������ ������ ������ ��ȸ�� ����
-- 5. �ǹ������� ���̺�, Į��, �� ��� ���� ������ ��ȸ�ϱ� ���� ���

------------------------------------------------------------------

------------------------------------------------------------------
-----     ������ ���� ���� ����
-- 1. �����ͺ��̽��� ������ ������ ��ü�� ���� ����
-- 2. ����Ŭ ����� �̸��� ��Ű�� ��ü �̸�
-- 3. ����ڿ��� �ο��� ���� ���Ѱ� ��
-- 4. ���Ἲ �������ǿ� ���� ����
-- 5. Į������ ������ �⺻��
-- 6. ��Ű�� ��ü�� �Ҵ�� ������ ũ��� ��� ���� ������ ũ�� ����
-- 7. ��ü ���� �� ���ſ� ���� ���� ����
-- 8. �����ͺ��̽� �̸�, ����, ������¥, ���۸��, �ν��Ͻ� �̸�

------------------------------------------------------------------
-------     ������ ���� ����
-- 1. USER_ : ��ü�� �����ڸ� ���� ������ ������ ���� ��
-- user_tables�� ����ڰ� ������ ���̺� ���� ������ ��ȸ�� �� �ִ� ������ ���� ��.
SELECT table_name FROM user_tables;
SELECT * FROM user_catalog;

-- 2. ALL_    : �ڱ� ���� �Ǵ� ������ �ο� ���� ��ü�� ���� ������ ������ ���� ��
SELECT owner, table_name FROM all_tables;

-- 3. DBA_   : �����ͺ��̽� �����ڸ� ���� ������ ������ ���� ��
SELECT owner, table_name FROM dba_tables;

------------------------------------------------------------------------------
--   11. View 
------------------------------------------------------------------------------
-- View : �ϳ� �̻��� �⺻ ���̺��̳� �ٸ� �並 �̿��Ͽ� �����Ǵ� ���� ���̺�
--        ��� �����͵�ųʸ� ���̺� �信 ���� ���Ǹ� ����
--       ���� : 1) ���� 
--             2) ��ޱ���ڰ�  �ʱޱ���� SQL �ɷ��� Cover
--       ���� : Performance(����)�� �� ����
------------------------------------------------------------------------------
CREATE OR REPLACE VIEW view_professor
AS
SELECT profno, name, userid, position, hiredate, deptno FROM professor;

-- ��ȸ�ϴ� ���� professor�� �޾Ƽ� ��ü������ ����
SELECT * FROM view_professor;

--�������ǿ� �ɸ��� �ʴ´ٸ� �並 ���� �Է� ����
INSERT INTO view_professor VALUES (2000, 'view', 'userid', 'position', sysdate, 101);
SELECT profno, name, userid, position, hiredate, deptno FROM professor;

INSERT INTO view_professor VALUES (2001, 'view1', 'userid1', '', sysdate, 101);

-- ����work01 --> VIEW �̸� v_emp_sample  : emp(empno , ename , job, mgr,deptno)
CREATE OR REPLACE VIEW v_emp_sample
AS
SELECT empno, ename, job, mgr, deptno FROM emp;

INSERT INTO v_emp_sample (empno, ename, job, mgr, deptno) VALUES (2001, 'userid2', 'position2', 7839, 10);

--���� ��
--v_emp_complex(emp + dept)
CREATE OR REPLACE VIEW v_emp_complex
AS
SELECT * 
FROM emp NATURAL JOIN dept; --�������� = �̳�����

INSERT INTO v_emp_complex(empno, ename) VALUES(1500, 'ȫ�浿');
INSERT INTO v_emp_complex(deptno, dname) VALUES(87, '����3��'); -- ���� : dept ���� Į�� ����

INSERT INTO v_emp_complex(empno, ename, deptno, dname, loc) VALUES (1600, 'ȫ�浿', 87, '������', '������'); -- ���� : dept ���� Į�� ����
INSERT INTO v_emp_complex(empno, ename, deptno) VALUES (1600, 'ȫ�浿', 86); -- ���� : dept ���� Į�� ����
INSERT INTO v_emp_complex(empno, ename, sal) VALUES (1600, 'ȫ�浿', 1200);-- VIEW���� ��� emp���� Į���� ������ ����

--���̺� ������ �ٲ㼭 ���������� �� ���
CREATE OR REPLACE VIEW v_emp_complex2
AS
SELECT * 
FROM dept NATURAL JOIN emp; --������ �ٲ㵵?(�ܷ�Ű �Ǵ����� emp�� ������ ���� ���̺��ε�)
INSERT INTO v_emp_complex2(deptno, dname) VALUES(87, '����3��'); -- ����

--���̺� ������ �ٲ㼭 �̳������� �� ���
CREATE OR REPLACE VIEW v_emp_complex4
AS
SELECT d.deptno, d.dname, d.loc, e.empno, e.ename, e.job
FROM dept d, emp e
WHERE d.deptno = e.deptno; --������ �ٲ㵵?(�ܷ�Ű �Ǵ����� emp�� ������ ���� ���̺��ε�)
INSERT INTO v_emp_complex4(empno, ename) VALUES(1601, 'ȫ�浿1');
INSERT INTO v_emp_complex4(deptno, dname, loc) VALUES (78, '������', '������'); -- ����

--���̺� ������ �ٲ㼭 �ƿ��������� �� ���
CREATE OR REPLACE VIEW v_emp_complex5
AS
SELECT d.deptno, d.dname, d.loc, e.empno, e.ename, e.job
FROM emp e FULL OUTER JOIN dept d
ON e.deptno = d.deptno;
INSERT INTO v_emp_complex5(empno, ename) VALUES(1602, 'ȫ�浿1');
INSERT INTO v_emp_complex5(deptno, dname, loc) VALUES (78, '������', '������'); -- ����

------------     View  HomeWork     ----------------------------------------------------
---��1)  �л� ���̺��� 101�� �а� �л����� �й�, �̸�, �а� ��ȣ�� ���ǵǴ� �ܼ� �並 ����
---     �� �� :  v_stud_dept101
CREATE OR REPLACE VIEW v_stud_dept101
AS
SELECT studno, name, deptno
FROM student
WHERE deptno = 101;
--��2) �л� ���̺�� �μ� ���̺��� �����Ͽ� 102�� �а� �л����� �й�, �̸�, �г�, �а� �̸����� ���ǵǴ� ���� �並 ����
--      �� �� :   v_stud_dept102
CREATE OR REPLACE VIEW v_stud_dept102
AS
SELECT studno, name, grade, dname
FROM student s, department d
WHERE s.deptno = d.deptno
AND s.deptno = 102;
--��3)  ���� ���̺��� �а��� ��� �޿���     �Ѱ�� ���ǵǴ� �並 ����
--  �� �� :  v_prof_avg_sal       Column �� :   avg_sal      sum_sal
CREATE OR REPLACE VIEW v_prof_avg_sal
AS 
SELECT avg(sal) AS avg_sal, sum(sal) AS sum_sal
FROM professor
GROUP BY deptno;

--------------------------------------------------------------------------
---- ������ ���ǹ�
--------------------------------------------------------------------------
-- 1. ������ ������ ���̽� ���� ������� 2���� ���̺� ����
-- 2. ������ ������ ���̽����� �����Ͱ��� �θ� ���踦 ǥ���� �� �ִ� Į���� �����Ͽ� 
--    �������� ���踦 ǥ��
-- 3. �ϳ��� ���̺��� �������� ������ ǥ���ϴ� ���踦 ��ȯ����(recursive relationship)
-- 4. �������� �����͸� ������ Į�����κ��� �����͸� �˻��Ͽ� ���������� ��� ��� ����
--------------------------------------------------------------------------
-- ����
-- SELECT ��ɹ����� START WITH�� CONNECT BY ���� �̿�
-- ������ ���ǹ������� �������� ��� ���İ� ���� ��ġ ����
-- ��� ������  top-down �Ǵ� bottom-up
-- ����) CONNECT BY PRIOR �� START WITH���� ANSI SQL ǥ���� �ƴ�
--------------------------------------------------------------------------
-- ��1) ������ ���ǹ��� ����Ͽ� �μ� ���̺��� �а�,�к�,�ܰ������� �˻��Ͽ� �ܴ�,�к�
-- �а������� top-down ������ ���� ������ ����Ͽ���. ��, ���� �����ʹ� 10�� �μ�
SELECT level, deptno, dname, college
FROM department
START WITH deptno = 10
CONNECT BY PRIOR deptno = college; -- Top-Down

-- ��2)������ ���ǹ��� ����Ͽ� �μ� ���̺��� �а�,�к�,�ܰ������� �˻��Ͽ� �а�,�к�
-- �ܴ� ������ bottom-up ������ ���� ������ ����Ͽ���. ��, ���� �����ʹ� 102�� �μ��̴�
SELECT level, deptno, dname, college
FROM department
START WITH deptno = 102
CONNECT BY PRIOR college = deptno; -- Bottom-Up

--- ��3) ������ ���ǹ��� ����Ͽ� �μ� ���̺��� �μ� �̸��� �˻��Ͽ� �ܴ�, �к�, �а�����
---         top-down �������� ����Ͽ���. ��, ���� �����ʹ� ���������С��̰�,
---        �� LEVEL(����)���� �������� 2ĭ �̵��Ͽ� ���
SELECT LPAD(' ', (LEVEL-1)*2) || dname AS "������"
FROM department
START WITH dname = '��������'
CONNECT BY PRIOR deptno = college;

--------------------------------------------------------------------------------
---  TableSpace  
---  ����  :�����ͺ��̽� ������Ʈ �� ���� �����͸� �����ϴ� ����
--         �̰��� �����ͺ��̽��� �������� �κ��̸�, ���׸�Ʈ�� �����Ǵ� ��� DBMS�� ���� 
--         �����(���׸�Ʈ)�� �Ҵ�
--------------------------------------------------------------------------------
-- �⺻���� ������ ��δ� 
-- 1. TableSpace ����
CREATE TABLESPACE user1 DATAFILE 'C:\oraclexe\backup\tablespace\user1.ora' SIZE 100M;
CREATE TABLESPACE user2 DATAFILE 'C:\oraclexe\backup\tablespace\user2.ora' SIZE 100M;
CREATE TABLESPACE user3 DATAFILE 'C:\oraclexe\backup\tablespace\user3.ora' SIZE 100M;
CREATE TABLESPACE user4 DATAFILE 'C:\oraclexe\backup\tablespace\user4.ora' SIZE 100M;

-- 2. �ε����� ���̺� �����̽� ����
--    1) ���̺��� INDEX�� ���̺� �����̽� ��ȸ
SELECT index_name, table_name, tablespace_name FROM user_indexes;-- �ε��� ��ȸ
ALTER INDEX pk_religionno3 REBUILD TABLESPACE user1;

-- 3. ���̺��� ���̺� �����̽� ����
--    1) Table�� ���̺� �����̽� ��ȸ
SELECT table_name, tablespace_name FROM user_tables;-- ���̺� ��ȸ
ALTER TABLE job3 MOVE TABLESPACE user2;

CREATE TABLE "SCOTT"."DEPT_THIRD" 
   (	"DEPTNO" NUMBER(2,0) NOT NULL ENABLE, 
	"DNAME" VARCHAR2(14 BYTE), 
	"LOC" VARCHAR2(13 BYTE), 
	 CONSTRAINT "DEPT_THIRD_PK" PRIMARY KEY ("DEPTNO"),
  TABLESPACE "user3");

-- Oracle ��ü Backup  (scott)
EXPDP scott/tiger DIRECTORY=mdBackup2 DUMPFILE=scott.dmp; -- �������� Export

IMPDP scott/tiger DIRECTORY=mdBackup2 DUMPFILE=scott.dmp; -- �������� Import














