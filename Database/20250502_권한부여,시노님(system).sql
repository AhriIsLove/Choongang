-----------------------------------------------------------------------------------------------------------
---   �����ͺ��̽� ����
---  1. ���� ����� ȯ��(multi-user environment)
---     1) ����ڴ� �ڽ��� ������ ��ü�� ���� �������� ������ �����Ϳ� ���� �����̳� ��ȸ ����
---     2) �ٸ� ����ڰ� ������ ��ü�� �����ڷκ��� ���� ������ �ο����� �ʴ� ���� �Ұ�
---     3) ���� ����� ȯ�濡���� �����ͺ��̽� �������� ��ȣ�� ö���ϰ� ����
---  2. �߾� �������� ������ ����
---  3. �ý��� ����
---     1) �����ͺ��̽� �����ڴ� ����� ����, ��ȣ ����, ����ں� ��� ������ ��ũ���� �Ҵ�
---     2) �ý��� ���� �������� �����ͺ��̽� ��ü�� ���� ���� ������ ����
---  4. ������ ����
---     1) ����ں��� ��ü�� �����ϱ� ���� ���� ����
---     2) �����ͺ��̽� ��ü�� ���� ���� ������ ����
-----------------------------------------------------------------------------------------------------------
---  ����(Privilege) �ο�
---    1. ���� : ����ڰ� �����ͺ��̽� �ý����� �����ϰų� ��ü�� �̿��� �� �ִ� �Ǹ�
---    2. ���� 
---         1) �ý��� ���� : �ý��� ������ �ڿ� ������ ����� ��Ű�� ��ü ���� ��� ���� 
---                               �����ͺ��̽� ���� �۾��� �� �� �ִ� ����
---             [1]  �����ͺ��̽� �����ڰ� ������ �ý��� ����
---                   CREATE USER     :  ����ڸ� ������ �� �ִ� ����
---                   DROP    USER     : ����ڸ� ������ �� �ִ� ����
---                   DROP ANY TABLE : ������ ���̺��� ������ �� �ִ� ����
---                   QUERY REWRITE  : �Լ� ��� �ε����� �����ϱ� ���� ����
---             [2]  �Ϲݻ���ڰ� ������ �ý��� ����
---                   CREATE SESSION      : DB�� ������ �� �ִ� ����
---                   CREATE TABLE          : ����� ��Ű������ ���̺��� ������ �� �ִ� ����
---                   CREATE SEQUENCE   : ����� ��Ű������ �������� ������ �� �ִ� ����
---                   CREATE VIEW            : ����� ��Ű������ �並 ������ �� �ִ� ����
---                   CREATE PROCEDURE : ����� ��Ű������ ���ν���, �Լ�, ��Ű���� ������ �� �ִ� ����
---         2) ��ü ����    : ���̺�, ��, ������, �Լ� ��� ���� ��ü�� ������ �� �ִ� ����
---  ��(role)
---  1. ���� : �ټ� ����ڿ� �پ��� ������ ȿ�������� �����ϱ� ���Ͽ� ���� ���õ� ������ �׷�ȭ�� ����
---              �Ϲ� ����ڰ� �����ͺ��̽��� �̿��ϱ� ���� �������� ����(�����ͺ��̽� ���ӱ���, 
---              ���̺� ����, ����, ����, ��ȸ ����, �� ���� ����)�� �׷�ȭ
-- ������ ���ǵ� ��
-- 1. CONNECT ��
--     1) ����ڰ� �����ͺ��̽��� �����Ͽ� ������ ������ �� �ִ� ����
--     2) ���̺� �Ǵ� ��� ���� ��ü�� ������ �� �ִ� ����
-- 2. RESOURCE ��
--     1) ����ڿ��� �ڽ��� ���̺�, ������, ���ν���, Ʈ���� ��ü ���� �� �� �ִ� ����
--     2) ����� ������ : CONNECT �Ѱ� RESOURCE ���� �ο�
-- 3.  DBA ��
--     1) �ý��� �ڿ��� ���������� ����̳� �ý��� ������ �ʿ��� ��� ����
--     2) DBA ������ �ٸ� ������� �ο��� �� ����
--     3) ��� ����� ������ CONNECT, RESOURCE, DBA ������ ������ ��� ������ �ο� �� öȸ ����
-----------------------------------------------------------------------------------------------------------

-----------------------------------------------
---   Admin ����� ���� /���� �ο�
------------------------------------------------
-- 1. ����� ����
--CREATE USER id IDENTIFIED BY password
CREATE USER usertest01 IDENTIFIED BY tiger;
CREATE USER usertest02 IDENTIFIED BY tiger;
CREATE USER usertest03 IDENTIFIED BY tiger;
CREATE USER usertest04 IDENTIFIED BY tiger;

-- ������ ����ڷ� ����
-- ���� - �����ͺ��̽����� - Name, ������̸�, ��й�ȣ �Է� - ����
-- ��� - SQL ������ - id ���� - ����
-- ���ܿ� DB��� �޺��ڽ����� �����Ͽ� ���� ����

-- 2-1. session ���� �ο�  --> ���ӱ��Ѹ� �־���
GRANT CREATE session TO usertest01; -- ���̺� ���� ���� : ���� ���� ����
-- usertest01 START ------------------------------------------------------------
-- insufficient privileges ���� : ������ ���� ���̺� ���� �Ұ���
CREATE TABLE emp_row_audit1(
    e_id    NUMBER(6)       CONSTRAINT emp_row_pk PRIMARY KEY,
    e_name  VARCHAR2(30),
    e_gubun VARCHAR2(10),
    e_date  DATE
);
-- usertest01 END --------------------------------------------------------------

-- 2-1. session ���� �ο�  --> ���ӱ���,�������� �� �־���
GRANT CREATE session, CREATE table, CREATE view TO usertest02; -- ���̺� ���� ���� : ���̺� �����̽� ���� ����
-- usertest02 START ------------------------------------------------------------
-- no privileges on tablespace 'SYSTEM' ���� : ���̺� ������ ���������� ���̺� �����̽��� ���� ������ ���� �Ұ���
CREATE TABLE sampleTBL(
     memo varchar2(50)
 );
-- usertest02 END --------------------------------------------------------------

-- 2-2. ���忡�� DBA�� ������ ���� �ο� : ROLE�ο�(CONNECT, RESOURCE)
GRANT CONNECT, RESOURCE TO usertest03;
-- usertest03 START ------------------------------------------------------------
-- ������ ���� --> CONNECT , RESOURCE 
CREATE TABLE sampleTBL(
     memo varchar2(50)
 );
 
INSERT INTO sampleTBL values('7�� ����');
INSERT INTO sampleTBL values('����� ��������');

COMMIT;

-- X 
SELECT * FROM scott.emp; -- ������(scott)�� ���̺� ���� �Ұ���
-- usertest03 END --------------------------------------------------------------

-- 2-3 ���� �ο��� ȸ��
GRANT DBA TO usertest04;
-- usertest04 START ------------------------------------------------------------
CREATE TABLE sampleTBL(
     memo varchar2(50)
);

INSERT INTO sampleTBL values('7�� ����');
INSERT INTO sampleTBL values('����� ��������');
INSERT INTO sampleTBL values('Ʈ���� �����');

COMMIT; -- ���� ����
-- usertest04 END --------------------------------------------------------------
-- DBA ���� ȸ��
REVOKE DBA FROM usertest04;
-- usertest04 START ------------------------------------------------------------
SELECT * FROM sampleTBL; -- ������ ���ܵ� �ڱ��ڽ��� ���� ���� �� �� �ֵ�.
-- usertest04 END --------------------------------------------------------------
GRANT CONNECT, RESOURCE TO usertest04;

-- �ڽ��� ������ ���̺��� �ƴ� ��쿣 ������ �������� �ۼ��ؾ� ��
SELECT * FROM emp; -- ����
SELECT * FROM scott.emp;

----------------------------------------------------------------------------------------------------
-- ���Ǿ�(synonym)
-- 1. ���� : �ϳ��� ��ü�� ���� �ٸ� �̸��� �����ϴ� ���
--      ���Ǿ�� ����(Alias) ������
--      ���Ǿ�� �����ͺ��̽� ��ü���� ���
--      ������ �ش� SQL ��ɹ������� ���
-- 2. ���Ǿ��� ����
--   1) ���� ���Ǿ�(private synonym) 
--      ��ü�� ���� ���� ������ �ο� ���� ����ڰ� ������ ���Ǿ�� �ش� ����ڸ� ���
--
--   2) ���� ���Ǿ�(public synonym)
--      ������ �ִ� ����ڰ� ������ ���Ǿ�� ������ ���
--      DBA ������ ���� ����ڸ� ���� (�� : ������ ��ųʸ�)
 -------------------------------------------------------------------------------------------------
CREATE TABLE systemTBL(
    memo VARCHAR2(50)
);
INSERT INTO systemTBL VALUES('7�� Ǫ��');
INSERT INTO systemTBL VALUES('��� ��������');
COMMIT;

SELECT * FROM systemTBL;

-- SELECT ���� �ο�
GRANT SELECT ON systemTBL TO usertest04 WITH GRANT OPTION;
-- usertest04 START ------------------------------------------------------------
SELECT * FROM system.systemTBL; -- �ó�� �ο������� ����
INSERT INTO system.systemTBL VALUES('5�� �ƻ��'); -- ���ѿ� ���� �ٸ�
-- usertest04 END --------------------------------------------------------------

-- ���� �ο� ������ ���ŷο�
CREATE PUBLIC SYNONYM pub_system FOR systemTBL; -- ��ο��� systemTBL�� pub_system���� ���� �����ϰ� ���
-- usertest04 START ------------------------------------------------------------
SELECT * FROM pub_system;
-- usertest04 END --------------------------------------------------------------

--�ó�� ����;
DROP PUBLIC SYNONYM pub_system;
-- usertest04 START ------------------------------------------------------------
SELECT * FROM pub_system; -- ����
-- usertest04 END --------------------------------------------------------------

-- �ǹ������� ���� ���̺� ��� �ó�� ���� �����ϰ� ����Ѵ�.
CREATE PUBLIC SYNONYM systemTBL FOR systemTBL;
-- usertest04 START ------------------------------------------------------------
SELECT * FROM systemTBL; -- �ڽ��� ���̺�ó�� ��� ����
-- usertest04 END --------------------------------------------------------------

-- ���� ���Ǿ�(private synonym)  Test ��
CREATE TABLE privateTBL(
    memo varchar2(50)
);
INSERT INTO privateTBL VALUES('6�� Ǫ��');
INSERT INTO privateTBL VALUES('����� ��������...');
GRANT SELECT ON privateTBL TO usertest04 WITH GRANT OPTION;
GRANT SELECT ON privateTBL TO scott WITH GRANT OPTION;
COMMIT;
SELECT * FROM privateTBL;
-- usertest04 START ------------------------------------------------------------
SELECT * FROM privateTBL; -- ����
SELECT * FROM system.privateTBL;

-- ���� ���Ǿ�(private synonym) ����
CREATE SYNONYM privateTBL FOR system.privateTBL; -- ����
-- usertest04 END --------------------------------------------------------------

-- CONNECT, RESOURCE ���� ȸ��
REVOKE CONNECT, RESOURCE FROM usertest04;
-- DBA ���� �ο�
GRANT DBA TO usertest04;
-- usertest04 START ------------------------------------------------------------
-- usertest04 ������ �� ���� ���Ǿ�(private synonym) ���� ��õ�
CREATE SYNONYM privateTBL FOR system.privateTBL;
-- usertest04 END --------------------------------------------------------------

-- scott START -----------------------------------------------------------------
SELECT * FROM system.privateTBL;
SELECT * FROM privateTBL; -- �ٸ� ����ڰ� �������� �ó���Ͽ��⿡ scott���� ���� ����
-- scott END -------------------------------------------------------------------

-- usertest04 START ------------------------------------------------------------
SELECT * FROM scott.emp; -- DBA������ ���� ����ڴ� �ٸ� DBA�� ���� ���̺� ���� ����
-- usertest04 END --------------------------------------------------------------

-- scott START -----------------------------------------------------------------
GRANT SELECT ON emp TO usertest02;
-- scott END -------------------------------------------------------------------
-- usertest02 START ------------------------------------------------------------
--scott�� SELECT ������ �Ҵ��Ͽ��� ������ ����
SELECT * FROM scott.emp;
-- usertest02 END --------------------------------------------------------------

-- scott START -----------------------------------------------------------------
GRANT SELECT ON stud_101 TO usertest02; -- �ܼ��� SELECT�� �����ϵ���
GRANT SELECT ON job3 TO usertest02 WITH GRANT OPTION; -- ���������� SELECT ������ �ο��� �� �ֵ���
-- scott END -------------------------------------------------------------------
-- usertest02 START ------------------------------------------------------------
--scott���� ���� ����.
SELECT * FROM scott.stud_101;
SELECT * FROM scott.job3; -- WITH GRANT OPTION
-- usertest02 END --------------------------------------------------------------

GRANT CREATE session, CREATE table, CREATE view TO usertest01;
-- usertest02 START ------------------------------------------------------------
GRANT SELECT ON scott.stud_101 TO usertest01; -- ���� : ������ �ο��� �� �ִ� ������ ����
GRANT SELECT ON scott.job3 TO usertest01;
-- usertest02 END --------------------------------------------------------------
-- usertest01 START ------------------------------------------------------------
--1. scott�� usertest02���� job3�� WITH GRANT OPTION���� �ο�
--2. usertest02�� usertest01���� job3�� �ο�
SELECT * FROM scott.job3;
-- usertest01 END --------------------------------------------------------------

-- scott START -----------------------------------------------------------------
--usertest02�� �̹� usertest01���� ������ �ο��� ����
--usertest02�� ������ �P����
REVOKE SELECT ON job3 FROM usertest02;
-- scott END -------------------------------------------------------------------
-- usertest02 START ------------------------------------------------------------
SELECT * FROM scott.job3; -- ����
-- usertest02 END --------------------------------------------------------------
-- usertest01 START ------------------------------------------------------------
SELECT * FROM scott.job3; -- ����
-- usertest01 END --------------------------------------------------------------
