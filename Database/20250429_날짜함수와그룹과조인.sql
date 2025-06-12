---------------------------------------------------------
-------- ���� �Լ� ***   --------------------------------
---------------------------------------------------------
-- 1) ��¥ + ���� = ��¥ (��¥�� �ϼ��� ����)
-- ���� ��ȣ�� 9908�� ������ �Ի����� �������� �Ի� 30�� �Ŀ� 60�� ���� ��¥�� ���
SELECT name, hiredate, hiredate+30, hiredate+60
FROM professor
WHERE profno = 9908
;
-- 2) SYSDATE �Լ�
--    SYSDATE �Լ��� �ý��ۿ� ����� ���� ��¥�� ��ȯ�ϴ� �Լ��μ�, �� �������� ��ȯ
SELECT sysdate FROM dual;
-- 3) ��¥ - ���� = ��¥ (��¥�� �ϼ��� ����)
SELECT name, hiredate, hiredate-30, hiredate-60
FROM professor
WHERE profno=9908
;
-- 4) ��¥ - ��¥=  �ϼ� (��¥�� ��¥�� ����)
SELECT name, hiredate, sysdate - hiredate
FROM professor
WHERE profno=9908
;
-- 5) MONTHS_BETWEEN : date1�� date2 ������ ���� ���� ���
--     ADD_MONTHS    : date�� ���� ���� ���� ��¥ ���
--     MONTHS_BETWEEN�� ADD_MONTHS �Լ��� �� ������ ��¥ ������ �ϴ� �Լ� 
--    ��) �Ի����� 120���� �̸��� ������ ������ȣ, �Ի���, �Ի��Ϸ� ���� �����ϱ����� ���� ��,
--        �Ի��Ͽ��� 6���� ���� ��¥�� ����Ͽ���
SELECT profno, hiredate, sysdate, 
    MONTHS_BETWEEN(sysdate, hiredate) AS working_day, 
    ADD_MONTHS(hiredate, 6) AS hire_6After
FROM professor
WHERE MONTHS_BETWEEN(sysdate, hiredate) < 120
;
-- TO_CHAR �Լ�
-- TO_CHAR �Լ��� ��¥�� ���ڸ� ���ڷ� ��ȯ�ϱ� ���� ���
-- �л� ���̺��� ������ �л��� �й��� ������� �߿��� ����� ����Ͽ���
-- YYYY/YY/MM/MONTH/MON/DD/DY/DAY
SELECT studno, 
    TO_CHAR(birthdate, 'YY/MM') AS birthdate1,
    TO_CHAR(birthdate, 'yy-mm') AS birthdate2,
    TO_CHAR(birthdate, 'yymm') AS birthdate3,
    TO_CHAR(birthdate, 'yymmdd') AS birthdate4,
    TO_CHAR(birthdate, 'yyyymmdd') AS birthdate5,
    TO_CHAR(birthdate, 'yyyy/yy/mm/month/mon/dd/dy/day') AS birthdate6
FROM student
WHERE name = '������'
;
SELECT 
    TO_CHAR(sysdate, 'MONTH') AS month,
    TO_CHAR(sysdate, 'DY') AS dy,
    TO_CHAR(sysdate, 'DAY') As day
FROM dual;
-- LAST_DAY, NEXT_DAY
-- LAST_DAY �Լ��� �ش� ��¥�� ���� ���� ������ ��¥�� ��ȯ�ϴ� �Լ�
-- NEXT_DAY �Լ��� �ش� ���� �������� ��õ� ������ ���� ��¥�� ��ȯ�ϴ� �Լ�
-- ������ ���� ���� ������ ��¥�� �ٰ����� �Ͽ����� ��¥�� ����Ͽ���
SELECT sysdate, LAST_DAY(sysdate), NEXT_DAY(sysdate,'��') FROM dual;

SELECT
    TO_CHAR(sysdate, 'YY/MM/DD HH24:MI:SS') AS NORMAL
FROM dual
;
-- ��¥�� �ݿø��� ����
SELECT name, 
    TO_CHAR(hiredate, 'YY/MM/DD HH24:MI:SS') AS hiredate,
    TO_CHAR(ROUND(hiredate,'dd'), 'YY/MM/DD HH24:MI:SS') AS round_dd,
    TO_CHAR(ROUND(hiredate,'mm'), 'YY/MM/DD HH24:MI:SS') AS round_mm,-- ~15�� ����, 16��~ �ø� (2�� ���� ����)
    TO_CHAR(ROUND(hiredate,'yy'), 'YY/MM/DD HH24:MI:SS') AS round_yy
FROM professor
;

-- TO_CHAR �Լ��� �̿��� ���� ��� ���� ��ȯ --> '9'�� ����Ѵ�.
SELECT TO_CHAR(1234567, '9,999,999') FROM dual;

--��1) ���������� �޴� �������� �̸�, �޿�, ��������, 
--�׸��� �޿��� ���������� ���� ���� 12�� ���� ����� ����(anual_sal)���� ���
SELECT name, sal, comm, TO_CHAR((sal+comm)*12, '9,999') AS anual_sal
FROM professor
WHERE comm IS NOT NULL
;
--��2) student Table���� �ֹε�Ϲ�ȣ���� ��������� �����Ͽ� ���� ��YY/MM/DD�� ���·� 
--���� BirthDate�� ����Ͽ���
SELECT idnum, 
    SUBSTR(idnum, 1,2)||'/'||SUBSTR(idnum, 3,2)||'/'||SUBSTR(idnum, 5,2) AS BirthDate,
    TO_DATE(SUBSTR(idnum, 1,6), 'YY-MM-DD') AS BirthDate2, -- TO_DATE�� ������ YY/MM/DD�� �����
    TO_CHAR(TO_DATE(SUBSTR(idnum, 1,6), 'YY-MM-DD'), 'YY-MM-DD') AS BirthDate3 -- TO_CHAR�� ���ϴ� ��� ���� ����
FROM student
;
--��3) NVL �Լ��� NULL�� 0 �Ǵ� �ٸ� ������ ��ȯ�ϱ� ���� �Լ�
-- 101�� �а� ������ �̸�, ����, �޿�, ��������, �޿��� ���������� �հ踦 ����Ͽ���. 
-- ��, ���������� NULL�� ��쿡�� ���������� 0���� ���
SELECT name, position, sal, comm, 
    sal+NVL(comm,0),
    NVL(sal+comm, sal)
FROM professor
WHERE deptno = 101
;
------------------------------------------------------------------------
------                              Question                   ---------
------------------------------------------------------------------------
-- 1. salgrade ������ ��ü ����
SELECT * FROM salgrade;
-- 2. scott���� ��밡���� ���̺� ����
SELECT * FROM tab;
-- 3. emp Table���� ��� , �̸�, �޿�, ����, �Ի���
SELECT empno, ename, sal, job, hiredate FROM emp;
-- 4. emp Table���� �޿��� 2000�̸��� ��� �� ���� ���, �̸�, �޿� �׸� ��ȸ
SELECT empno, ename, sal FROM emp WHERE sal < 2000;
-- 5. emp Table���� 80/02���Ŀ� �Ի��� ����� ����  ���,�̸�,����,�Ի��� 
SELECT empno, ename, job, hiredate FROM emp WHERE hiredate > '80/02/01';
-- 6. emp Table���� �޿��� 1500�̻��̰� 3000���� ���, �̸�, �޿�  ��ȸ(2����)
SELECT empno, ename, sal FROM emp WHERE sal >= 1500 AND sal <= 3000;
SELECT empno, ename, sal FROM emp WHERE sal BETWEEN 1500 AND 3000;
-- 7. emp Table���� ���, �̸�, ����, �޿� ��� [ �޿��� 2500�̻��̰�  ������ MANAGER�� ���]
SELECT empno, ename, job, sal FROM emp WHERE sal >= 2500 AND job = 'MANAGER';
-- 8. emp Table���� �̸�, �޿�, ���� ��ȸ 
--    [�� ������  ���� = (�޿�+��) * 12  , null�� 0���� ����]
SELECT ename, sal, (sal+NVL(comm, 0))*12 AS "����" FROM emp;
--9. emp Table����  81/02 ���Ŀ� �Ի��ڵ��� xxx�� �Ի����� xxX
-- CONCAT�� ||�� Ȱ���Ͽ� [ ��ü Row ��� ] --> 2���� ��� ��
SELECT ename||'�� �Ի����� '||hiredate FROM emp WHERE hiredate - TO_DATE('81/02/01') >= 0;
SELECT CONCAT(CONCAT(ename,'�� �Ի����� '), hiredate) FROM emp WHERE hiredate >= '81/02/01';
--10.emp Table���� �̸��ӿ� T�� �ִ� ���,�̸� ���
SELECT empno, ename FROM emp WHERE ename LIKE '%T%';

-- NVL2 �Լ�
-- NVL2 �Լ��� ù ��° �μ� ���� NULL�� �ƴϸ� �� ��° �μ� ���� ����ϰ�, 
--            ù ��° �μ� ���� NULL�̸� �� ��° �μ� ���� ����ϴ� �Լ�
-- ����) 102�� �а� �����߿��� ���������� �޴� ����� �޿��� ���������� ���� ���� �޿� �Ѿ����� ����Ͽ���. 
-- ��, ���������� ���� �ʴ� ������ �޿��� �޿� �Ѿ����� ����Ͽ���.
SELECT name, position, sal, comm, NVL2(comm, sal+comm, sal) AS total
FROM professor
WHERE deptno = 102;

--NULLIF �Լ�
--NULLIF �Լ��� �� ���� ǥ������ ���Ͽ� ���� �����ϸ� NULL�� ��ȯ�ϰ�,
--                                        ��ġ���� ������ ù ��° ǥ������ ���� ��ȯ
-- ����) ���� ���̺��� �̸��� ����Ʈ ���� ����� ���̵��� ����Ʈ ���� ���ؼ�
--������ NULL�� ��ȯ�ϰ� 
--���� ������ �̸��� ����Ʈ ���� ��ȯ
SELECT name, userid, LENGTHB(name), LENGTHB(userid),
    NULLIF(LENGTHB(name), LENGTHB(userid)) AS nullif
FROM professor;

-- DECODE �Լ�
-- DECODE �Լ��� ���� ���α׷��� ���� IF���̳� CASE ������ ǥ���Ǵ� ������ �˰����� 
-- �ϳ��� SQL ��ɹ����� �����ϰ� ǥ���� �� �ִ� ������ ��� 
-- DECODE �Լ����� �� �����ڴ� ��=���� ����
-- NULL = NULL�� ������ �Ǵ�
-- ����Ŭ ����

-- ���� ���̺��� ������ �Ҽ� �а� ��ȣ�� �а� �̸����� ��ȯ�Ͽ� ����Ͽ���. 
-- �а� ��ȣ�� 101�̸� ����ǻ�Ͱ��а���, 102�̸� ����Ƽ�̵���а���, 201�̸� �����ڰ��а���, 
-- ������ �а� ��ȣ�� �������а���(default)�� ��ȯ
-- Java  --> If ElseIf Else
SELECT name, deptno,
    DECODE(deptno, --switch(deptno)
    101, '��ǻ�Ͱ��а�', --case 101:'��ǻ�Ͱ��а�'
    102, '��Ƽ�̵���а�', --case 102:'��Ƽ�̵���а�'
    201, '���ڰ��а�', --case 201:'���ڰ��а�'
    '�����а�') --default: '�����а�'
FROM professor
;

-- CASE �Լ�
-- CASE �Լ��� DECODE �Լ��� ����� Ȯ���� �Լ� 
-- DECODE �Լ��� ǥ���� �Ǵ� Į�� ���� ��=�� �񱳸� ���� ���ǰ� ��ġ�ϴ� ��쿡�� �ٸ� ������ ��ġ�� �� ������
-- , CASE �Լ������� ��� ����, ���� ����, �� ����� ���� �پ��� �񱳰� ����
-- ���� WHEN ������ ǥ������ �پ��ϰ� ����
-- NULL = NULL�� �������� �Ǵ�
-- NULL IS NULL�� ������ �Ǵ�
-- 8.1.7�������� �����Ǿ�����, 9i���� SQL, PL/SQL���� �Ϻ��� ���� 
-- DECODE �Լ��� ���� �������� ����ü��� �پ��� �� ǥ���� ���
SELECT name, deptno,
    CASE 
        WHEN deptno = 101 THEN '��ǻ�Ͱ��а�'--if
        WHEN deptno = 102 THEN '��Ƽ�̵���а�'--elseif
        WHEN deptno = 201 THEN '���ڰ��а�'--elseif
        ELSE '�����а�'--else
    END deptname--AS
FROM professor;

-- Decode / Case
-- ���� ���̺��� �Ҽ� �а��� ���� �̸�, �а���ȣ, �޿�, ���ʽ�����ϵ� ���ʽ��� ����Ͽ� ����Ͽ���. (���� --> bonus)
-- �а� ��ȣ���� ���ʽ��� ������ ���� ����Ѵ�.
-- �а� ��ȣ�� 101�̸� ���ʽ��� �޿��� 10%, 102�̸� 20%, 201�̸� 30%, ������ �а��� 0%
--DECODE
SELECT name, deptno, sal,
    DECODE(deptno, 
        101, sal*0.1,
        102, sal*0.2,
        201, sal*0.3,
        0) AS bonus
FROM professor;
--CASE
SELECT name, deptno, sal,
    CASE
        WHEN deptno = 101 THEN sal*0.1
        WHEN deptno = 102 THEN sal*0.2
        WHEN deptno = 201 THEN sal*0.3
        ELSE 0
    END AS bonus
FROM professor;

---------------         Home Work           --------------------
--1. emp Table �� �̸��� �빮��, �ҹ���, ù���ڸ� �빮�ڷ� ���
SELECT UPPER(ename), LOWER(ename), INITCAP(ename) FROM emp;
--2. emp Table ��  �̸�, ����, ������ 2-5���� ���� ���
SELECT ename, job, SUBSTR(job, 2,5) FROM emp;
--3. emp Table �� �̸�, �̸��� 10�ڸ��� �ϰ� ���ʿ� #���� ä���
SELECT ename, LPAD(ename, 10, '#') FROM emp;
-- 4. emp Table ��  �̸�, ����, ������ MANAGER�� �����ڷ� ���
SELECT ename, 
    DECODE(job,
        'MANAGER', '������',
        job)
FROM emp;
--5. emp Table ��  �̸�, �޿�/7�� ���� ����, �Ҽ��� 1�ڸ�. 10������   �ݿø��Ͽ� ���
SELECT ename, ROUND(sal/7), ROUND(sal/7, 1), ROUND(sal/7, -1) FROM emp;
--6.  emp Table ��  �̸�, �޿�/7�� ���� �����Ͽ� ���
SELECT ename, trunc(sal/7), trunc(sal/7, 1), trunc(sal/7, -1) FROM emp;
--7.  emp Table ��  �̸�, �޿�/7�� ����� �ݿø�,����,ceil,floor
SELECT ename, ROUND(sal/7), trunc(sal/7), CEIL(sal/7), FLOOR(sal/7) FROM emp;
--8.  emp Table ��  �̸�, �޿�, �޿�/7�� ������
SELECT ename, sal, MOD(sal, 7) FROM emp;
--9.  emp Table �� �̸�, �޿�, �Ի���, �Ի�Ⱓ(���� ����,��)���,  �Ҽ��� ���ϴ� �ݿø�
SELECT ename, sal, hiredate, ROUND(sysdate-hiredate), ROUND(MONTHS_BETWEEN(sysdate,hiredate)) FROM emp;
--10. emp Table ��  job �� 'CLERK' �϶� 10% ,'ANALYSY' �϶� 20% 
--                                 'MANAGER' �϶� 30% ,'PRESIDENT' �϶� 40%
--                                 'SALESMAN' �϶� 50% 
--                                 �׿��϶� 60% �λ� �Ͽ� 
--   empno, ename, job, sal, �� �� �λ� �޿��� ����ϼ���(CASE/Decode�� ���)
SELECT empno, ename, job, sal, --���1
    CASE 
        WHEN job = 'CLERK' THEN sal*1.1
        WHEN job = 'ANALYSY' THEN sal*1.2
        WHEN job = 'MANAGER' THEN sal*1.3
        WHEN job = 'PRESIDENT' THEN sal*1.4
        WHEN job = 'SALESMAN' THEN sal*1.5
        ELSE sal*1.6
    END "�λ� �޿�"
FROM emp;
SELECT empno, ename, job, sal, --���2
    CASE job
        WHEN 'CLERK' THEN sal*1.1
        WHEN 'ANALYSY' THEN sal*1.2
        WHEN 'MANAGER' THEN sal*1.3
        WHEN 'PRESIDENT' THEN sal*1.4
        WHEN 'SALESMAN' THEN sal*1.5
        ELSE sal*1.6
    END "�λ� �޿�"
FROM emp;

---------------------------------------------------------------------
---   8��. �׷��Լ�  * ~ **
----  ���̺��� ��ü ���� �ϳ� �̻��� �÷��� �������� �׷�ȭ�Ͽ�
---   �׷캰�� ����� ����ϴ� �Լ�
---   �׷��Լ��� ������� ����� ����ϴµ� ���� ���
---------------------------------------------------------------------
-- SELECT  column, group_function(column)
-- FROM  table
-- [WHERE  condition]
-- [GROUP BY  group_by_expression]
-- [HAVING  group_condition]
-- ?GROUP BY : ��ü ���� group_by_expression�� �������� �׷�ȭ
-- ?HAVING : GROUP BY ���� ���� ������ �׷캰�� ���� �ο�
------------------------------------------------------------------------

--����                   �ǹ�
--COUNT             ���� ���� ���
--MAX               NULL�� ������ ��� �࿡�� �ִ� ��
--MIN               NULL�� ������ ��� �࿡�� �ּ� ��
--SUM               NULL�� ������ ��� ���� ��
--AVG               NULL�� ������ ��� ���� ��� ��
------------------�� �ʼ� �Ʒ� G-----------------------------------------
--STDDEV            NULL�� ������ ��� ���� ǥ������
--VARIANCE          NULL�� ������ ��� ���� �л� ��
--GROUPING          �ش� Į���� �׷쿡 ���Ǿ����� ���θ� 1 �Ǵ� 0���� ��ȯ
--GROUPING SETS     �� ���� ���Ƿ� ���� ���� �׷�ȭ ���

-- 1) COUNT �Լ�
-- ���̺��� ������ �����ϴ� ���� ������ ��ȯ�ϴ� �Լ�
-- ��1) 101�� �а� �����߿��� ���������� �޴� ������ ���� ����Ͽ���
SELECT COUNT(*), COUNT(comm)
FROM professor
WHERE deptno = 101;
--102�� �а� �л����� ������ ��հ� �հ踦 ����Ͽ���
SELECT AVG(weight), SUM(weight)
FROM student
WHERE deptno = 102;
-- ���� ���̺��� �޿��� ǥ�������� �л��� ���
SELECT STDDEV(sal), VARIANCE(sal)
FROM professor;

-- �а���  �л����� �ο���, ������ ��հ� �հ踦 ����Ͽ���
SELECT deptno, COUNT(*), AVG(weight), SUM(weight)
FROM student
GROUP BY deptno
;
-- ���� ���̺��� �а����� ���� ���� ���������� �޴� ���� ���� ����Ͽ���
SELECT deptno, COUNT(*), COUNT(comm)
FROM professor
GROUP BY deptno
;
-- ���� ���̺��� �а����� ���� ���� ���������� �޴� ���� ���� ����Ͽ���
-- �� �а����� ���� ���� 2�� �̻��� �а��� ���
SELECT deptno, COUNT(*), COUNT(comm)
FROM professor
GROUP BY deptno -- �׷��� ����
HAVING COUNT(*) >= 2 -- �׷��� ������ ����
;
-- �л� ���� 4���̻��̰� ���Ű�� 168�̻���  �г⿡ ���ؼ� �г�, �л� ��, ��� Ű, ��� �����Ը� ���
-- ��, ��� Ű�� ��� �����Դ� �Ҽ��� �� ��° �ڸ����� �ݿø� �ϰ�, 
-- ��¼����� ��� Ű�� ���� ������ ������������ ����ϰ�
-- �� �ȿ��� ��� �����԰� ���� ������ ������������ ���
SELECT grade, COUNT(*), 
    ROUND(AVG(height), 1) AS AVG_HEIGHT, 
    ROUND(AVG(weight), 1) AS AVG_WEIGHT
FROM student
GROUP BY grade
HAVING COUNT(*) >= 4 AND AVG(height) >= 168
ORDER BY AVG(height) DESC, AVG(weight) DESC
;

--  �ֱ� �Ի� ����� ���� ������ ����� �Ի��� ��� (emp)
SELECT MAX(hiredate), MIN(hiredate)
FROM emp;
--  �μ��� �ֱ� �Ի� ����� ���� ������ ����� �Ի��� ��� (emp)
SELECT deptno, MAX(hiredate), MIN(hiredate)
FROM emp
GROUP BY deptno
;
-- �μ���, ������ count & sum[�޿�]    (emp)
SELECT deptno, job, count(deptno), count(job), sum(sal)
FROM emp
GROUP BY deptno, job
;
-- �μ��� �޿��Ѿ� 3000�̻� �μ���ȣ,�μ��� �޿��ִ�    (emp)
SELECT deptno, max(sal)
FROM emp
GROUP BY deptno
HAVING SUM(sal) >= 3000
;

-- ��ü �л��� �Ҽ� �а����� ������, ���� �а� �л��� �ٽ� �г⺰�� �׷����Ͽ�, 
-- �а��� �г⺰ �ο���, ��� �����Ը� ���, 
-- (��, ��� �����Դ� �Ҽ��� ���� ù��° �ڸ����� �ݿø� )  STUDENT
SELECT deptno, grade, count(grade), ROUND(avg(weight))
FROM student
GROUP BY deptno, grade
;

-- ROLLUP ������ : �׷캰 Total Sum�� ���ϴ� ������
-- GROUP BY ���� �׷� ���ǿ� ���� ��ü ���� �׷�ȭ�ϰ� �� �׷쿡 ���� �κ����� ���ϴ� ������
-- ��) �Ҽ� �а����� ���� �޿� �հ�� ��� �а� �������� �޿� �հ踦 ����Ͽ���
SELECT deptno, SUM(sal)
FROM professor
GROUP BY ROLLUP(deptno) --Total Sum�� ���ϴܿ� ���
--GROUP BY deptno
;
--��2) ROLLUP �����ڸ� �̿��Ͽ� �а� �� ���޺� ���� ��, �а��� ���� ��, ��ü ���� ���� ����Ͽ���
SELECT deptno, position, COUNT(*)
FROM professor
GROUP BY ROLLUP(deptno, position)
;
-- CUBE ������ : �׷캰 Total Sum�� ���ϴ� ������(��ܿ� ǥ��)
-- ROLLUP�� ���� �׷� ����� GROUP BY ���� ����� ���ǿ� ���� �׷� ������ ����� ������
-- ��1) CUBE �����ڸ� �̿��Ͽ� �а� �� ���޺� ���� ��, �а��� ���� ��, ��ü ���� ���� ����Ͽ���.
SELECT deptno, position, COUNT(*)
FROM professor
GROUP BY CUBE(deptno, position)
;

-------------------------------------------------------------------------------------
----                              DeadLock                                  ---------
-------------------------------------------------------------------------------------
--  Transaction A --->  Developer
UPDATE emp --����1
SET sal = sal*1.1
WHERE empno = 7369--SMITH
;
UPDATE emp --����3(���Ѵ��)
SET sal = sal*1.1
WHERE empno = 7839--KING
;

--  Transaction B --->  cmd(Sqlplus)
UPDATE emp --����2
SET comm = 500
WHERE empno = 7839--KING
;
UPDATE emp --����4(���Ѵ��,����3�����߻�)
SET comm = 500
WHERE empno = 7369--SMITH
;

----------------------------------------------------------------------
----                    9-1.     JOIN       ***              ---------
----------------------------------------------------------------------
-- 1) ������ ����
-- �ϳ��� SQL ��ɹ��� ���� ���� ���̺� ����� �����͸� �ѹ��� ��ȸ�Ҽ� �ִ� ���
-- ex1-1) �й��� 10101�� �л��� �̸��� �Ҽ� �а� �̸��� ����Ͽ���
SELECT studno, name, deptno
FROM student
WHERE studno = 10101;
-- ex1-2)�а��� ������ �а��̸�
SELECT dname
FROM department
WHERE deptno = 101;
-- ex1-3)  [ex1-1] + [ex1-2] �ѹ� ��ȸ  ---> Join
SELECT studno, name, student.deptno, department.dname
FROM student, department
WHERE student.deptno = department.deptno;
-- ex1-3)  [ex1-1] + [ex1-2] �ѹ� ��ȸ  ---> Join
SELECT s.studno, s.name, s.deptno, d.dname
FROM student s, department d
WHERE s.deptno = d.deptno;

-- ������ �л��� �й�, �̸�, �а� �̸� �׸��� �а� ��ġ�� ���
SELECT s.studno, s.name, d.dname, d.loc
FROM student s, department d
WHERE s.deptno = d.deptno AND s.name = '������'
;
-- �����԰� 80kg�̻��� �л��� �й�, �̸�, ü��, �а� �̸�, �а���ġ�� ���
SELECT s.studno, s.name, s.weight, d.dname, d.loc
FROM student s, department d
WHERE s.deptno = d.deptno AND s.weight >= 80
;

-- īƼ�� ���δ�Ʈ(cartesian product) = CROSS JOIN : �� �� �̻��� ���̺� ���� ���� ������ ���� ��� ����
-- 1) ������ �Ǽ�
-- 2) �����ʱ⿡ ����Data ����
SELECT s.studno, s.name, d.dname, d.loc, s.weight, d.deptno
FROM student s, department d
;
-- ANSI SQL : CROSS JOIN
SELECT s.studno, s.name, d.dname, d.loc, s.weight, d.deptno
FROM student s CROSS JOIN department d
;
-- ***
-- ���� ��� ���̺��� ���� Į���� ��=��(equal) �񱳸� ���� ���� ���� ������ ���� �����Ͽ�
--  ����� �����ϴ� ���� ���
--  SQL ��ɹ����� ���� ���� ����ϴ� ���� ���
-- �ڿ������� �̿��� EQUI JOIN
-- ����Ŭ 9i �������� Inner(EQUI) JOIN�� �ڿ������̶� ���
-- WHERE ���� ������� �ʰ�  NATURAL JOIN Ű���� ���
-- ����Ŭ���� �ڵ������� ���̺��� ��� Į���� ������� ���� Į���� ���� ��, ���������� ���ι� ����

-- ����Ŭ INNER Join ǥ���
SELECT s.studno, s.name, d.dname, d.loc, d.deptno
FROM student s, department d
WHERE s.deptno = d.deptno;
-- ANSI INNER Join ǥ��� : NATURAL JOIN
-- Natural Join Convert Error �ذ� 
-- NATURAL JOIN�� ���� ��Ʈ����Ʈ�� ���̺� ������ ����ϸ� ������ �߻�
SELECT s.studno, s.name, d.dname, d.loc, d.deptno -- ����
FROM student s NATURAL JOIN department d;
-- ������ ���� ����� Į���� �������� �ʵ��� �ۼ��ؾ� ���� �ذ�
SELECT s.studno, s.name, d.dname, d.loc, deptno
FROM student s NATURAL JOIN department d;

-- NATURAL JOIN�� �̿��Ͽ� ���� ��ȣ, �̸�, �а� ��ȣ, �а� �̸��� ����Ͽ���
SELECT p.profno, p.name, deptno, d.dname
FROM professor p NATURAL JOIN department d;
-- NATURAL JOIN�� �̿��Ͽ� 4�г� �л��� �̸�, �а� ��ȣ, �а��̸��� ����Ͽ���
SELECT s.name, deptno, d.dname
FROM student s NATURAL JOIN department d
WHERE s.grade = 4;
-- ANSI(JOIN ~ USING ���� �̿��� Inner JOIN)
-- USING���� ���� ��� Į���� ����
-- Į�� �̸��� ���� ��� ���̺��� ������ �̸����� ���ǵǾ� �־����
-- ��1) JOIN ~ USING ���� �̿��Ͽ� �й�, �̸�, �а���ȣ, �а��̸�, �а���ġ��
-- ����Ͽ���
SELECT s.studno, s.name, deptno, d.dname
FROM student s JOIN department d 
    USING(deptno);
SELECT s.studno, s.name, s.deptno, d.dname
FROM student s INNER JOIN department d 
    ON s.deptno = d.deptno;

-- EQUI JOIN�� 4���� ����� �̿��Ͽ� ���� ���衯���� �л����� �̸�, �а���ȣ,�а��̸��� ���
-- ORACLE : WHERE a.a = b.a
-- ANSI : NATURAL JOIN
-- ANSI : JOIN - USING
-- ANSI : INNER JOIN - ON(ORACLE JOIN�� ���⼭ �Ļ��ȵ�)
SELECT s.studno, s.name, s.deptno, d.dname --���� Į���� ���Ͽ� ���̺� ����
FROM student s, department d
WHERE s.deptno = d.deptno
AND s.name LIKE '��%';
--
SELECT s.studno, s.name, deptno, d.dname --���� Į���� ���Ͽ� ���̺� ����X
FROM student s NATURAL JOIN department d
WHERE s.name LIKE '��%';
--
SELECT s.studno, s.name, deptno, d.dname --���� Į���� ���Ͽ� ���̺� ����X
FROM student s JOIN department d USING(deptno)
WHERE s.name LIKE '��%';
--
SELECT s.studno, s.name, s.deptno, d.dname --���� Į���� ���Ͽ� ���̺� ����
FROM student s INNER JOIN department d ON s.deptno = d.deptno
WHERE s.name LIKE '��%';

-- NON-EQUI JOIN **
-- ��<��,BETWEEN a AND b �� ���� ��=�� ������ �ƴ� ������ ���
-- ���� ���̺�� �޿� ��� ���̺��� NON-EQUI JOIN�Ͽ� 
-- �������� �޿� ����� ����Ͽ���
CREATE TABLE "SCOTT"."SALGRADE2" 
(	"GRADE" NUMBER(2,0), 
    "LOSAL" NUMBER(5,0), 
    "HISAL" NUMBER(5,0)
);

SELECT p.profno, p.name, p.sal, s.grade
FROM professor p, salgrade2 s
WHERE p.sal BETWEEN s.losal AND s.hisal;
