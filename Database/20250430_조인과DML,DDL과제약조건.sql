-- OUTER JOIN  ***
-- EQUI JOIN���� ���� Į�� ������ �ϳ��� NULL ������ ���� ����� ����� �ʿ䰡 �ִ� ���
-- OUTER JOIN�� ����Ѵ�.
-- OUTER JOIN�� ��� null�� �����͵� ����Ѵ�.
SELECT s.name, s.grade, p.name, p.position
FROM student s, professor p
WHERE s.profno = p.profno; -- INNER JOIN�� ��� null�� �����Ͱ� ������ ������ ������ �߻� �� �� �ִ�.
-- �л� ���̺�� ���� ���̺��� �����Ͽ� �̸�, �г�, ���������� �̸�, ������ ���
-- ��, ���������� �������� ���� �л��̸��� �Բ� ����Ͽ���.
SELECT s.name, s.grade, p.name, p.position
FROM student s, professor p
WHERE s.profno = p.profno(+); -- 'LEFT' OUTER JOIN : '����' ���̺��� �����ʹ� ��� �����ش�.
-- �л� ���̺�� ���� ���̺��� �����Ͽ� �̸�, �г�, �������� �̸�, ������ ���
-- ��, �����л��� �������� ���� ���� �̸��� �Բ� ����Ͽ���
SELECT s.name, s.grade, p.name, p.position
FROM student s, professor p
WHERE s.profno(+) = p.profno; -- RIGHT OUTER JOIN : (+)�� ���� ���̺��� null�� + �ؼ� �����ش�.

--- ANSI OUTER JOIN
-- 1. ANSI LEFT OUTER JOIN
SELECT s.studno, s.name, s.profno, p.name
FROM 
    student s LEFT OUTER JOIN professor p 
    ON s.profno = p.profno;
--- ANSI OUTER JOIN
-- 1. ANSI RIGHT  OUTER JOIN
SELECT s.studno, s.name, s.profno, p.name
FROM 
    student s RIGHT OUTER JOIN professor p 
    ON s.profno = p.profno;

---- <<<   FULL OUTER JOIN  >>> -------------------------------
-- �л� ���̺�� ���� ���̺��� �����Ͽ� �̸�, �г�, �������� �̸�, ������ ���
-- ��, �����л��� �������� ���� ���� �̸� �� 
--  ���������� �������� ���� �л��̸�  �Բ� ����Ͽ���
--  Oracle ���� �� ��
SELECT s.name, s.grade, p.name, p.position
FROM student s, professor p
WHERE s.profno(+) = p.profno(+); -- X
-- UNION Ȱ���Ͽ� FULL OUTER JOIN ��ü ����
SELECT s.name, s.grade, p.name, p.position
FROM student s, professor p
WHERE s.profno(+) = p.profno
UNION
SELECT s.name, s.grade, p.name, p.position
FROM student s, professor p
WHERE s.profno = p.profno(+);

-- 3. ANSI FULL OUTER JOIN
SELECT s.studno, s.name, s.profno, p.name
FROM 
    student s FULL OUTER JOIN professor p 
    ON s.profno = p.profno;

--------------------------------------------------------------------------       
-------                  SELF JOIN   **                   ----------------       
-- �ϳ��� ���̺��� �ִ� Į������ �����ϴ� ������ �ʿ��� ��� ���
-- ���� ��� ���̺��� �ڽ� �ϳ���� �� �ܿ��� EQUI JOIN�� ����
--------------------------------------------------------------------------
SELECT c.deptno, c.dname, c.college, d.dname AS college_name
FROM department c, department d
WHERE c.college = d.deptno
;
-- SELF JOIN --> �μ� ��ȣ�� 201 �̻��� �μ� �̸��� ���� �μ��� �̸��� ���
-- ��� : xxx�Ҽ��� xxx�к�
SELECT c.dname||' �Ҽ��� '||d.dname
FROM department c, department d
WHERE c.college = d.deptno
AND c.deptno >= 201
;

--  HomeWork(Inner)
-- 1. �̸�, �����ڸ�(emp TBL --> Self Join)
SELECT en.ename, em.ename
FROM emp en, emp em
WHERE en.mgr = em.empno;
-- 2. �̸�,�޿�,�μ��ڵ�,�μ���,�ٹ���, ������ ��, ��ü����(emp ,dept TBL)
-- Self Join, Inner Join/Outer Join
SELECT en.ename, en.sal, en.deptno, d.dname, d.loc, em.ename
FROM emp en, emp em, dept d
WHERE en.deptno = d.deptno
AND en.mgr = em.empno(+);
--ANSI���
SELECT en.ename, en.sal, en.deptno, d.dname, d.loc, em.ename
FROM 
    emp en INNER JOIN dept d ON en.deptno = d.deptno
    LEFT OUTER JOIN emp em ON en.mgr = em.empno;
-- 3. �̸�,�޿�,���,�μ���,�����ڸ�, �޿��� 2000�̻��� ���
--    (emp, dept,salgrade TBL)
-- Self Join, Inner Join/Outer Join , ����Join
SELECT en.ename, en.sal, sg.grade, d.dname, em.ename
FROM emp en, dept d, emp em, salgrade sg
WHERE en.mgr = em.empno
AND em.deptno = d.deptno
AND em.sal > sg.losal AND em.sal <= sg.hisal
AND en.sal >= 2000;
-- 4. ���ʽ��� �޴� ����� ���Ͽ� �̸�,�μ���,��ġ�� 
-- ����ϴ� SELECT ������ �ۼ�(emp ,dept TBL)
SELECT e.ename, d.dname, d.loc
FROM emp e, dept d
WHERE e.deptno = d.deptno
--AND e.comm IS NOT NULL;
AND e.comm > 0;
-- 5. ���, �����, �μ��ڵ�, �μ����� �˻��϶�. 
--    ������������ ������������(emp ,dept TBL)
SELECT e.empno, e.ename, e.deptno, d.dname
FROM emp e, dept d
WHERE e.deptno = d.deptno
ORDER BY e.ename;
-----------------------------------------------------------------
----- SUB Query   ***
-- �ϳ��� SQL ��ɹ��� ����� �ٸ� SQL ��ɹ��� �����ϱ� ���� 
-- �� �� �̻��� SQL ��ɹ��� �ϳ��� SQL��ɹ����� �����Ͽ�
-- ó���ϴ� ���
-- ���� 
-- 1) ������ ��������
-- 2) ������ ��������
------------------------------------------------------------------
--  1. ��ǥ : ���� ���̺��� ���������� ������ ������ ������ ��� ������ �̸� �˻�
--    1-1 ���� ���̺��� ���������� ������ ���� �˻� SQL ��ɹ� ����
SELECT position
FROM professor
WHERE name = '������'; -- ���Ӱ���
--    1-2 ���� ���̺��� ���� Į������ 1-1 ���� ���� ��� ���� ������ ������ ���� ���� �˻� ��ɹ� ����
SELECT name, position
FROM professor
WHERE position = '���Ӱ���';
-- 1.��ǥ : ���� ���̺��� ���������� ������ ������ ������ ��� ������ �̸� �˻�--> SUB Query
SELECT name, position
FROM professor
WHERE position = (
    SELECT position
    FROM professor
    WHERE name = '������');

-- ���� 
-- 1) ������ ��������
--  ������������ �� �ϳ��� �ุ�� �˻��Ͽ� ���������� ��ȯ�ϴ� ���ǹ�
--  ���������� WHERE ������ ���������� ����� ���� ��쿡�� �ݵ�� ������ �� ������ �� 
--  �ϳ��� ����ؾ���

--  ��1) ����� ���̵� ��jun123���� �л��� ���� �г��� �л��� �й�, �̸�, �г��� ����Ͽ���
SELECT studno, name, grade
FROM student
WHERE grade = (
    SELECT grade
    FROM student
    WHERE userid = 'jun123');
--  ��2)  101�� �а� �л����� ��� �����Ժ��� �����԰� ���� �л��� �̸�, �г� , �а���ȣ, �����Ը�  ���
--  ���� : �а��� �ø����� ���
SELECT name, grade, deptno, weight
FROM student
WHERE weight < (
    SELECT avg(weight)
    FROM student
    WHERE deptno = 101)
ORDER BY deptno;
--  ��3) 20101�� �л��� �г��� ����, Ű�� 20101�� �л����� ū �л��� 
-- �̸�, �г�, Ű, �а��� �� ����Ͽ���
--  ���� : �а��� �������� ���
SELECT s.name, s.grade, s.height, d.dname
FROM student s, department d
WHERE s.deptno = d.deptno
AND s.grade = (
    SELECT grade
    FROM student
    WHERE studno = '20101')
AND s.height > (
    SELECT height
    FROM student
    WHERE studno = '20101')
ORDER BY s.deptno DESC;

---------------------------------------------------------------------------------------------------------------
-- 2) ������ ��������
-- ������������ ��ȯ�Ǵ� ��� ���� �ϳ� �̻��� �� ����ϴ� ��������
-- ���������� WHERE ������ ���������� ����� ���� ��쿡�� ���� �� �� ������ �� ����Ͽ� ��
-- ���� �� �� ������ : IN, ANY, SOME, ALL, EXISTS
-- 1) IN              : ���� ������ �� ������ ���������� ����߿��� �ϳ��� ��ġ�ϸ� ��, ��=���񱳸� ����
-- 2) ANY, SOME  : ���� ������ �� ������ ���������� ����߿��� �ϳ��� ��ġ�ϸ� ��
-- 3) ALL            : ���� ������ �� ������ ���������� ����߿��� ��簪�� ��ġ�ϸ� ��, 
-- 4) EXISTS        : ���� ������ �� ������ ���������� ����߿��� �����ϴ� ���� �ϳ��� �����ϸ� ��
---------------------------------------------------------------------------------------------------------------
-- 1.  IN �����ڸ� �̿��� ���� �� ��������
SELECT name, grade, deptno
FROM student
WHERE deptno IN ( -- �ϳ��� ��ġ�ϸ� ��
    SELECT deptno -- ���� �� ��ȯ(101, 102)
    FROM department
    WHERE college = 100);
--  2. ANY �����ڸ� �̿��� ���� �� ��������
-- ��)4�г� �л� �߿��� Ű�� ���� ���� �л����� Ű�� ū ��� �л��� �й�, �̸�, Ű�� ����Ͽ���
SELECT studno, name, height
FROM student
WHERE height > ANY( -- �ϳ��� (������)�����ص� �� : SOME�� ����� ����
    SELECT height
    FROM student
    WHERE grade = 4);
--- 3. ALL �����ڸ� �̿��� ���� �� ��������
SELECT studno, name, height
FROM student
WHERE height > ALL( -- ��� �����ؾ� ��
    SELECT height
    FROM student
    WHERE grade = 4);
--- 4. EXISTS �����ڸ� �̿��� ���� �� ��������
SELECT profno, name, sal, comm, position
FROM professor
WHERE EXISTS (
    SELECT position
    FROM professor
    WHERE comm IS NOT NULL);
    
-- ��1)  ���������� �޴� ������ �� ���̶� ������ 
--       ��� ������ ���� ��ȣ, �̸�, �������� �׸��� �޿��� ���������� ��(NULLó��) sal_comm �� ���
SELECT profno, name, comm, sal+NVL(comm, 0) AS sal_comm
FROM professor
WHERE EXISTS(
    SELECT comm
    FROM professor
    WHERE comm IS NOT NULL);

-- ���� �÷� ��������
-- ������������ ���� ���� Į�� ���� �˻��Ͽ� ���������� �������� ���ϴ� ��������
-- ���������� ������������ ���������� Į�� ����ŭ ����
-- ����
-- 1) PAIRWISE : Į���� ������ ��� ���ÿ� ���ϴ� ���
-- 2) UNPAIRWISE : Į������ ����� ���� ��, AND ������ �ϴ� ���

-- 1) PAIRWISE ���� Į�� ��������
-- ��1)    PAIRWISE �� ����� ���� �г⺰�� �����԰� �ּ��� 
--          �л��� �̸�, �г�, �����Ը� ����Ͽ���
SELECT name, grade, weight
FROM student
WHERE (grade, weight) IN(
    SELECT grade, MIN(weight)
    FROM student
    GROUP BY grade);
--  2) UNPAIRWISE : Į������ ����� ���� ��, AND ������ �ϴ� ���
-- UNPAIRWISE �� ����� ���� �г⺰�� �����԰� �ּ��� �л��� �̸�, �г�, �����Ը� ���
SELECT name, grade, weight
FROM student
WHERE grade IN(
    SELECT grade
    FROM student
    GROUP BY grade)
AND weight IN(
    SELECT MIN(weight)
    FROM student
    GROUP BY grade);
-- ��ȣ���� ��������     ***
-- ������������ ������������ �˻� ����� ��ȯ�ϴ� ��������
-- ��1)  �� �а� �л��� ��� Ű���� Ű�� ū �л��� �̸�, �а� ��ȣ, Ű�� ����Ͽ���
SELECT deptno, name, grade, height
FROM student s1
WHERE height > (
    SELECT avg(height) --deptno�� ��պ���
    FROM student s2
    WHERE s2.deptno = s1.deptno)
ORDER BY deptno;

-------------  HW  (emp)-----------------------
-- 1. Blake�� ���� �μ��� �ִ� ��� ����� ���ؼ� ��� �̸��� �Ի����� ���÷����϶�
SELECT ename, hiredate
FROM emp
WHERE deptno = (
    SELECT deptno
    FROM emp
    WHERE INITCAP(ename) = 'Blake' -- ��ҹ��� ���� ��
    );
-- 2. ��� �޿� �̻��� �޴� ��� ����� ���ؼ� ��� ��ȣ�� �̸��� ���÷����ϴ� ���ǹ��� ����. 
--    �� ����� �޿� �������� �����϶�
SELECT empno, ename
FROM emp
WHERE sal >= (
    SELECT avg(sal)
    FROM emp)
ORDER BY sal DESC;
-- 3. ���ʽ��� �޴� ����� �μ� ��ȣ�� �޿��� ��ġ�ϴ� ����� �̸�, �μ� ��ȣ �׸��� �޿��� ���÷����϶�.
SELECT ename, empno, sal
FROM emp e1
WHERE (deptno, sal) IN (
    SELECT deptno, sal
    FROM emp 
    WHERE comm IS NOT NULL);

------------------------------------------------------------------------------------------------
-------              ������ ���۾� (DML:Data Manpulation Language) **                  ----------
-- 1.���� : ���̺� ���ο� �����͸� �Է��ϰų� ���� �����͸� ���� �Ǵ� �����ϱ� ���� ��ɾ�
-- 2. ���� 
--  0) SELECT
--  1) INSERT : ���ο� ������ �Է� ��ɾ�
--  2) UPDATE : ���� ������ ���� ��ɾ�
--  3) DELETE : ���� ������ ���� ��ɾ�
--  4) MERGE : �ΰ��� ���̺��� �ϳ��� ���̺�� �����ϴ� ��ɾ�

-- 1) Insert
INSERT INTO dept VALUES (83, '�λ�'); -- ����
INSERT INTO dept VALUES (83, '�λ�', '�̴�');
INSERT INTO dept (deptno, dname, loc) VALUES (84, 'ȸ����', '������');
INSERT INTO dept (deptno, loc, dname) VALUES (85, '�Ŵ��', '������');
INSERT INTO dept (deptno, loc) VALUES (86, 'ȫ��');

-- 9910	��̼�		���Ӱ���		24/06/28		101
INSERT INTO professor (profno, name, position, hiredate, deptno) VALUES (9910, '��̼�', '���Ӱ���', sysdate, 101);
-- 9920	������		������		06/01/01		102
INSERT INTO professor (profno, name, position, hiredate, deptno) VALUES (9920, '������', '������', TO_DATE('2006/01/01', 'YYYY/MM/DD'), '102');
INSERT INTO professor (profno, name, position, hiredate, deptno) VALUES (9921, '������3', '������', '2006/01/01', '102');

DROP TABLE job3;
CREATE TABLE job3(
    jobno NUMBER(2) PRIMARY KEY,
    jobname VARCHAR(20)
);

INSERT INTO job3 VALUES(10, '�б�');
INSERT INTO job3 VALUES(11, '������');
INSERT INTO job3 VALUES(12, '�����');
INSERT INTO job3 VALUES(13, '����');
INSERT INTO job3 VALUES(14, '�߼ұ��');

CREATE TABLE Religion(
    religion_no NUMBER(2) CONSTRAINT PK_ReligionNo3 PRIMARY KEY,--PK�������� �̸�����(�ε����� PK��� ����Ǿ� ����)
    religion_name VARCHAR(20)
);

-- HW03
--10	�⵶��
--20	ī�縯��
--30	�ұ�
--40	����
INSERT INTO religion VALUES (10, '�⵶��');
INSERT INTO religion VALUES (20, 'ī�縯��');
INSERT INTO religion VALUES (30, '�ұ�');
INSERT INTO religion VALUES (40, '����');
--INSERT INTO religion VALUES (11, '�⵶��'), (21, 'ī�縯��'), (30, '�ұ�'), (40, '����'); -- ����Ŭ������ �������� ����
INSERT ALL 
	INTO religion values (10, '�⵶��')
	INTO religion values (20, 'ī�縯��')
	INTO religion values (30, '�ұ�')
	INTO religion values (40, '����')
select * from dual;

-----   ���� �� �Է�                         ------
--------------------------------------------------
-- 1. ������ TBL�̿� �ű� TBL ����
CREATE TABLE dept_second --dept�� ���̺� ������ ����
AS 
SELECT * FROM dept;

-- 2. TBL ���� ����
CREATE TABLE emp20
AS
SELECT empno, ename, sal*12 AS annsal -- ���ϴ� Į������
FROM emp
WHERE deptno = 20; -- �����ͱ��� ����

-- 3. TBL ������
CREATE TABLE dept30
AS
SELECT deptno, dname
FROM dept
WHERE 0=1;--������ ����

-- 4. Column �߰�
ALTER TABLE dept30
ADD(birth DATE);

INSERT INTO dept30 VALUES (10, '�߾��б�', sysdate);

-- 5. Column ����
ALTER TABLE dept30
--MODIFY dname VARCHAR2(11); -- �̹� �����ϴ� �����Ϳ� Ÿ��(ũ��)�� ���� ����
MODIFY dname VARCHAR2(20);

-- 6. Column ����
ALTER TABLE dept30
DROP Column dname;

-- 7. TBL �� ����
RENAME dept30 TO dept35;

-- 8. TBL ����
DROP TABLE dept35;

-- 9. Truncate
-- DELETE FROM table ���� ����
-- DDL �������� ROLLBACK���� ����
TRUNCATE TABLE dept_second;

-- INSERT ALL(unconditional INSERT ALL) ��ɹ�
-- ���������� ��� ������ ���Ǿ��� ���� ���̺� ���ÿ� �Է�
-- ���������� �÷� �̸��� �����Ͱ� �ԷµǴ� ���̺��� Į���� �ݵ�� �����ؾ� ��
CREATE TABLE height_info(
    stduno NUMBER(5),
    name VARCHAR2(20),
    height NUMBER(5,2)
);
CREATE TABLE weight_info(
    stduno NUMBER(5),
    name VARCHAR2(20),
    weight NUMBER(5,2)
);
INSERT ALL
    INTO height_info VALUES (studno, name, height)
    INTO weight_info VALUES (studno, name, weight)
SELECT studno, name, height, weight, grade
FROM student
WHERE grade >= '2';

DELETE height_info;
DELETE weight_info;

-- INSERT ALL 
-- [WHEN ������1 THEN
-- INTO [table1] VLAUES[(column1, column2,��)]
-- [WHEN ������2 THEN
-- INTO [table2] VLAUES[(column1, column2,��)]
-- [ELSE
-- INTO [table3] VLAUES[(column1, column2,��)]
-- subquery;
-- �л� ���̺��� 2�г� �̻��� �л��� �˻��Ͽ� 
-- height_info ���̺��� Ű�� 170���� ū �л��� �й�, �̸�, Ű�� �Է�
-- weight_info ���̺��� �����԰� 70���� ū �л��� �й�, �̸�, �����Ը� 
-- ���� �Է��Ͽ���
INSERT ALL
    WHEN height > 170 THEN -- 5��
        INTO height_info VALUES (studno, name, height)
    WHEN weight > 75 THEN -- 3��
        INTO weight_info VALUES (studno, name, weight)
SELECT studno, name, height, weight
FROM student
WHERE grade >= '2';

-- ������ ���� ����
-- UPDATE ��ɹ��� ���̺� ����� ������ ������ ���� ���۾�
-- WHERE ���� �����ϸ� ���̺��� ��� ���� ����
--- Update 
-- ��1) ���� ��ȣ�� 9903�� ������ ���� ������ ���α������� �����Ͽ���
UPDATE professor 
SET position = '�α���', userid = 'kkk'
WHERE profno = 9903;
--  ��2) ���������� �̿��Ͽ� �й��� 10201�� �л��� �г�� �а� ��ȣ��
--  10103 �й� �л��� �г�� �а� ��ȣ�� �����ϰ� �����Ͽ���
UPDATE student
SET (grade, deptno) = (
    SELECT grade, deptno
    FROM student
    WHERE studno = 10103
    )
WHERE studno = 10201;

-- ������ ���� ����
-- DELETE ��ɹ��� ���̺� ����� ������ ������ ���� ���۾�
-- WHERE ���� �����ϸ� ���̺��� ��� �� ����
COMMIT;-- ���� �� ���� ������ ����

-- ��1) �л� ���̺��� �й��� 20103�� �л��� �����͸� ����
DELETE FROM student WHERE studno = 20103;
ROLLBACK;

--  ��2) �л� ���̺��� ��ǻ�Ͱ��а��� �Ҽӵ� �л��� ��� �����Ͽ���.
DELETE FROM student 
WHERE deptno = (
    SELECT deptno
    FROM DEPARTMENT
    WHERE dname = '��ǻ�Ͱ��а�');
ROLLBACK;

----------------------------------------------------------------------------------------------------------------
---- MERGE
--  1. MERGE ����
--     ������ ���� �ΰ��� ���̺��� ���Ͽ� �ϳ��� ���̺�� ��ġ�� ���� ������ ���۾�
--     WHEN ���� ���������� ��� ���̺� �ش� ���� �����ϸ� UPDATE ��ɹ��� ���� ���ο� ������ ����,
--     �׷��� ������ INSERT ��ɹ����� ���ο� ���� ����
----------------------------------------------------------------------------------------------------------------
-- 1] MERGE �����۾� 
--  ��Ȳ 
-- 1) ������ �������� 2�� Update
-- 2) �赵�� ���� �ű� Insert
CREATE TABLE professor_temp
AS
SELECT * FROM professor
WHERE position = '����';

UPDATE professor_temp
SET position = '������'
WHERE position = '����';

INSERT INTO professor_temp
VALUES (9999, '�赵��', 'arom21', '���Ӱ���', 200, sysdate, 10, 101);

COMMIT;

-- 2] professor MERGE ���� 
-- ��ǥ : professor_temp�� �ִ� ����   ������ ������ professor Table�� Update
--                         �赵�� ���� �ű� Insert ������ professor Table�� Insert
-- 1) ������ �������� 2�� Update
-- 2) �赵�� ���� �ű� Insert
MERGE INTO professor p -- ���⿡
USING professor_temp pt -- �̰���
ON (p.profno = pt.profno)
WHEN MATCHED THEN -- ON���� ������ 
    UPDATE SET p.position = pt.position
WHEN NOT MATCHED THEN -- ON���� �ٸ���
    INSERT VALUES (pt.profno, pt.name, pt.userid, pt.position, pt.sal, pt.hiredate, pt.comm, pt.deptno)
;

--------------------------------------------------------------------------
------------            ��������(Constraint)        ***        ------------
--  ����  : �������� ��Ȯ���� �ϰ����� ����
-- 1. ���̺� ������ ���Ἲ ���������� ���� ����
-- 2. ���̺� ���� ����, ������ ��ųʸ��� ����ǹǷ� ���� ���α׷����� 
--     �Էµ� ��� �����Ϳ� ���� �����ϰ� ����
-- 3. ���������� Ȱ��ȭ, ��Ȱ��ȭ �� �� �ִ� ���뼺
-------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------
------------            ��������(Constraint)   ����      ***               ------------
-- 1 .NOT NULL  : ���� NULL�� ������ �� ����
-- 2. �⺻Ű(primary key) : UNIQUE +  NOT NULL + �ּҼ�  ���������� ������ ���� ***
--     PK
-- 3. ����Ű(foreign key) :  ���̺� ���� �ܷ� Ű ���踦 ���� ***
-- 4. CHECK : �ش� Į���� ���� ������ ������ ���� ������ ���� ����
-------------------------------------------------------------------------------------
CREATE TABLE subject(
    subno NUMBER(5) CONSTRAINT subject_no_pk PRIMARY KEY,
    subname VARCHAR2(20) CONSTRAINT subject_name_nn NOT NULL,
    term VARCHAR2(1) CONSTRAINT subject_term_ck CHECK(term IN('1','2')),
    typeGubun VARCHAR2(1)
);
COMMENT ON COLUMN subject.subno IS '������ȣ';
COMMENT ON COLUMN subject.subname IS '��������';
COMMENT ON COLUMN subject.term IS '�б�';

INSERT INTO subject(subno , subname , term) Values(10000, '��ǻ�Ͱ���', '1');
INSERT INTO subject(subno , subname , term, typegubun) Values(10001, 'DB����', '2', '1');
INSERT INTO subject(subno , subname , term, typegubun) Values(10002, 'JSP����', '1', '1');

-- PK Constraint --> Unique violated(���ϼ� ����)
INSERT INTO subject(subno , subname , term, typegubun) Values(10001, 'Spring����', '1', '1');
-- PK Constraint --> NOT NULL violated(NOT NULL ����)
INSERT INTO subject(subname, term, typegubun) Values('Spring����2', '1', '1');
-- subname --> NOT NULL violated(NOT NULL ����)
INSERT INTO subject(subno, term, typegubun) Values(10003, '1', '1');
-- term --> CHECK violated(check ����)
INSERT INTO subject(subno, subname, term, typegubun) Values(10003, 'Spring����3', '5', '1');

-- Table ����� ���Ѱ��� ���� ���� ����
-- Student Table �� idnum�� unique�� ����
ALTER TABLE student
ADD CONSTRAINT stud_idnum_uk UNIQUE(idnum);

INSERT INTO student(studno, name, idnum) VALUES(30101, '������','8012301036613');
-- idnum --> unique violated(���ϼ� ����)
INSERT INTO student(studno, name, idnum) VALUES(30102, '������','8012301036613');

-- CONSTRAINT ��ȸ
-- P : PK
-- U : Unique
-- C : Check + NotNull
SELECT constraint_name, constraint_type
FROM user_constraints
WHERE table_name IN('SUBJECT', 'STUDENT');
