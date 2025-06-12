-- OUTER JOIN  ***
-- EQUI JOIN에서 양측 칼럼 값중의 하나가 NULL 이지만 조인 결과로 출력할 필요가 있는 경우
-- OUTER JOIN을 사용한다.
-- OUTER JOIN의 경우 null인 데이터도 출력한다.
SELECT s.name, s.grade, p.name, p.position
FROM student s, professor p
WHERE s.profno = p.profno; -- INNER JOIN의 경우 null인 데이터가 있을때 데이터 누락이 발생 할 수 있다.
-- 학생 테이블과 교수 테이블을 조인하여 이름, 학년, 지도교수의 이름, 직급을 출력
-- 단, 지도교수가 배정되지 않은 학생이름도 함께 출력하여라.
SELECT s.name, s.grade, p.name, p.position
FROM student s, professor p
WHERE s.profno = p.profno(+); -- 'LEFT' OUTER JOIN : '왼쪽' 테이블의 데이터는 모두 보여준다.
-- 학생 테이블과 교수 테이블을 조인하여 이름, 학년, 지도교수 이름, 직급을 출력
-- 단, 지도학생을 배정받지 않은 교수 이름도 함께 출력하여라
SELECT s.name, s.grade, p.name, p.position
FROM student s, professor p
WHERE s.profno(+) = p.profno; -- RIGHT OUTER JOIN : (+)가 붙은 테이블은 null을 + 해서 보여준다.

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
-- 학생 테이블과 교수 테이블을 조인하여 이름, 학년, 지도교수 이름, 직급을 출력
-- 단, 지도학생을 배정받지 않은 교수 이름 및 
--  지도교수가 배정되지 않은 학생이름  함께 출력하여라
--  Oracle 지원 안 함
SELECT s.name, s.grade, p.name, p.position
FROM student s, professor p
WHERE s.profno(+) = p.profno(+); -- X
-- UNION 활용하여 FULL OUTER JOIN 대체 가능
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
-- 하나의 테이블내에 있는 칼럼끼리 연결하는 조인이 필요한 경우 사용
-- 조인 대상 테이블이 자신 하나라는 것 외에는 EQUI JOIN과 동일
--------------------------------------------------------------------------
SELECT c.deptno, c.dname, c.college, d.dname AS college_name
FROM department c, department d
WHERE c.college = d.deptno
;
-- SELF JOIN --> 부서 번호가 201 이상인 부서 이름과 상위 부서의 이름을 출력
-- 결과 : xxx소속은 xxx학부
SELECT c.dname||' 소속은 '||d.dname
FROM department c, department d
WHERE c.college = d.deptno
AND c.deptno >= 201
;

--  HomeWork(Inner)
-- 1. 이름, 관리자명(emp TBL --> Self Join)
SELECT en.ename, em.ename
FROM emp en, emp em
WHERE en.mgr = em.empno;
-- 2. 이름,급여,부서코드,부서명,근무지, 관리자 명, 전체직원(emp ,dept TBL)
-- Self Join, Inner Join/Outer Join
SELECT en.ename, en.sal, en.deptno, d.dname, d.loc, em.ename
FROM emp en, emp em, dept d
WHERE en.deptno = d.deptno
AND en.mgr = em.empno(+);
--ANSI방식
SELECT en.ename, en.sal, en.deptno, d.dname, d.loc, em.ename
FROM 
    emp en INNER JOIN dept d ON en.deptno = d.deptno
    LEFT OUTER JOIN emp em ON en.mgr = em.empno;
-- 3. 이름,급여,등급,부서명,관리자명, 급여가 2000이상인 사람
--    (emp, dept,salgrade TBL)
-- Self Join, Inner Join/Outer Join , 범위Join
SELECT en.ename, en.sal, sg.grade, d.dname, em.ename
FROM emp en, dept d, emp em, salgrade sg
WHERE en.mgr = em.empno
AND em.deptno = d.deptno
AND em.sal > sg.losal AND em.sal <= sg.hisal
AND en.sal >= 2000;
-- 4. 보너스를 받는 사원에 대하여 이름,부서명,위치를 
-- 출력하는 SELECT 문장을 작성(emp ,dept TBL)
SELECT e.ename, d.dname, d.loc
FROM emp e, dept d
WHERE e.deptno = d.deptno
--AND e.comm IS NOT NULL;
AND e.comm > 0;
-- 5. 사번, 사원명, 부서코드, 부서명을 검색하라. 
--    사원명기준으로 오름차순정열(emp ,dept TBL)
SELECT e.empno, e.ename, e.deptno, d.dname
FROM emp e, dept d
WHERE e.deptno = d.deptno
ORDER BY e.ename;
-----------------------------------------------------------------
----- SUB Query   ***
-- 하나의 SQL 명령문의 결과를 다른 SQL 명령문에 전달하기 위해 
-- 두 개 이상의 SQL 명령문을 하나의 SQL명령문으로 연결하여
-- 처리하는 방법
-- 종류 
-- 1) 단일행 서브쿼리
-- 2) 다중행 서브쿼리
------------------------------------------------------------------
--  1. 목표 : 교수 테이블에서 ‘전은지’ 교수와 직급이 동일한 모든 교수의 이름 검색
--    1-1 교수 테이블에서 ‘전은지’ 교수의 직급 검색 SQL 명령문 실행
SELECT position
FROM professor
WHERE name = '전은지'; -- 전임강사
--    1-2 교수 테이블의 직급 칼럼에서 1-1 에서 얻은 결과 값과 동일한 직급을 가진 교수 검색 명령문 실행
SELECT name, position
FROM professor
WHERE position = '전임강사';
-- 1.목표 : 교수 테이블에서 ‘전은지’ 교수와 직급이 동일한 모든 교수의 이름 검색--> SUB Query
SELECT name, position
FROM professor
WHERE position = (
    SELECT position
    FROM professor
    WHERE name = '전은지');

-- 종류 
-- 1) 단일행 서브쿼리
--  서브쿼리에서 단 하나의 행만을 검색하여 메인쿼리에 반환하는 질의문
--  메인쿼리의 WHERE 절에서 서브쿼리의 결과와 비교할 경우에는 반드시 단일행 비교 연산자 중 
--  하나만 사용해야함

--  문1) 사용자 아이디가 ‘jun123’인 학생과 같은 학년인 학생의 학번, 이름, 학년을 출력하여라
SELECT studno, name, grade
FROM student
WHERE grade = (
    SELECT grade
    FROM student
    WHERE userid = 'jun123');
--  문2)  101번 학과 학생들의 평균 몸무게보다 몸무게가 적은 학생의 이름, 학년 , 학과번호, 몸무게를  출력
--  조건 : 학과별 올림차순 출력
SELECT name, grade, deptno, weight
FROM student
WHERE weight < (
    SELECT avg(weight)
    FROM student
    WHERE deptno = 101)
ORDER BY deptno;
--  문3) 20101번 학생과 학년이 같고, 키는 20101번 학생보다 큰 학생의 
-- 이름, 학년, 키, 학과명 를 출력하여라
--  조건 : 학과명별 내림차순 출력
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
-- 2) 다중행 서브쿼리
-- 서브쿼리에서 반환되는 결과 행이 하나 이상일 때 사용하는 서브쿼리
-- 메인쿼리의 WHERE 절에서 서브쿼리의 결과와 비교할 경우에는 다중 행 비교 연산자 를 사용하여 비교
-- 다중 행 비교 연산자 : IN, ANY, SOME, ALL, EXISTS
-- 1) IN              : 메인 쿼리의 비교 조건이 서브쿼리의 결과중에서 하나라도 일치하면 참, ‘=‘비교만 가능
-- 2) ANY, SOME  : 메인 쿼리의 비교 조건이 서브쿼리의 결과중에서 하나라도 일치하면 참
-- 3) ALL            : 메인 쿼리의 비교 조건이 서브쿼리의 결과중에서 모든값이 일치하면 참, 
-- 4) EXISTS        : 메인 쿼리의 비교 조건이 서브쿼리의 결과중에서 만족하는 값이 하나라도 존재하면 참
---------------------------------------------------------------------------------------------------------------
-- 1.  IN 연산자를 이용한 다중 행 서브쿼리
SELECT name, grade, deptno
FROM student
WHERE deptno IN ( -- 하나라도 일치하면 됨
    SELECT deptno -- 다중 행 반환(101, 102)
    FROM department
    WHERE college = 100);
--  2. ANY 연산자를 이용한 다중 행 서브쿼리
-- 문)4학년 학생 중에서 키가 제일 작은 학생보다 키가 큰 모든 학생의 학번, 이름, 키를 출력하여라
SELECT studno, name, height
FROM student
WHERE height > ANY( -- 하나만 (범위에)만족해도 됨 : SOME과 기능이 동일
    SELECT height
    FROM student
    WHERE grade = 4);
--- 3. ALL 연산자를 이용한 다중 행 서브쿼리
SELECT studno, name, height
FROM student
WHERE height > ALL( -- 모두 만족해야 함
    SELECT height
    FROM student
    WHERE grade = 4);
--- 4. EXISTS 연산자를 이용한 다중 행 서브쿼리
SELECT profno, name, sal, comm, position
FROM professor
WHERE EXISTS (
    SELECT position
    FROM professor
    WHERE comm IS NOT NULL);
    
-- 문1)  보직수당을 받는 교수가 한 명이라도 있으면 
--       모든 교수의 교수 번호, 이름, 보직수당 그리고 급여와 보직수당의 합(NULL처리) sal_comm 을 출력
SELECT profno, name, comm, sal+NVL(comm, 0) AS sal_comm
FROM professor
WHERE EXISTS(
    SELECT comm
    FROM professor
    WHERE comm IS NOT NULL);

-- 다중 컬럼 서브쿼리
-- 서브쿼리에서 여러 개의 칼럼 값을 검색하여 메인쿼리의 조건절과 비교하는 서브쿼리
-- 메인쿼리의 조건절에서도 서브쿼리의 칼럼 수만큼 지정
-- 종류
-- 1) PAIRWISE : 칼럼을 쌍으로 묶어서 동시에 비교하는 방식
-- 2) UNPAIRWISE : 칼럼별로 나누어서 비교한 후, AND 연산을 하는 방식

-- 1) PAIRWISE 다중 칼럼 서브쿼리
-- 문1)    PAIRWISE 비교 방법에 의해 학년별로 몸무게가 최소인 
--          학생의 이름, 학년, 몸무게를 출력하여라
SELECT name, grade, weight
FROM student
WHERE (grade, weight) IN(
    SELECT grade, MIN(weight)
    FROM student
    GROUP BY grade);
--  2) UNPAIRWISE : 칼럼별로 나누어서 비교한 후, AND 연산을 하는 방식
-- UNPAIRWISE 비교 방법에 의해 학년별로 몸무게가 최소인 학생의 이름, 학년, 몸무게를 출력
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
-- 상호연관 서브쿼리     ***
-- 메인쿼리절과 서브쿼리간에 검색 결과를 교환하는 서브쿼리
-- 문1)  각 학과 학생의 평균 키보다 키가 큰 학생의 이름, 학과 번호, 키를 출력하여라
SELECT deptno, name, grade, height
FROM student s1
WHERE height > (
    SELECT avg(height) --deptno별 평균보다
    FROM student s2
    WHERE s2.deptno = s1.deptno)
ORDER BY deptno;

-------------  HW  (emp)-----------------------
-- 1. Blake와 같은 부서에 있는 모든 사원에 대해서 사원 이름과 입사일을 디스플레이하라
SELECT ename, hiredate
FROM emp
WHERE deptno = (
    SELECT deptno
    FROM emp
    WHERE INITCAP(ename) = 'Blake' -- 대소문자 구분 함
    );
-- 2. 평균 급여 이상을 받는 모든 사원에 대해서 사원 번호와 이름을 디스플레이하는 질의문을 생성. 
--    단 출력은 급여 내림차순 정렬하라
SELECT empno, ename
FROM emp
WHERE sal >= (
    SELECT avg(sal)
    FROM emp)
ORDER BY sal DESC;
-- 3. 보너스를 받는 사원의 부서 번호와 급여에 일치하는 사원의 이름, 부서 번호 그리고 급여를 디스플레이하라.
SELECT ename, empno, sal
FROM emp e1
WHERE (deptno, sal) IN (
    SELECT deptno, sal
    FROM emp 
    WHERE comm IS NOT NULL);

------------------------------------------------------------------------------------------------
-------              데이터 조작어 (DML:Data Manpulation Language) **                  ----------
-- 1.정의 : 테이블에 새로운 데이터를 입력하거나 기존 데이터를 수정 또는 삭제하기 위한 명령어
-- 2. 종류 
--  0) SELECT
--  1) INSERT : 새로운 데이터 입력 명령어
--  2) UPDATE : 기존 데이터 수정 명령어
--  3) DELETE : 기존 데이터 삭제 명령어
--  4) MERGE : 두개의 테이블을 하나의 테이블로 병합하는 명령어

-- 1) Insert
INSERT INTO dept VALUES (83, '인사'); -- 오류
INSERT INTO dept VALUES (83, '인사', '이대');
INSERT INTO dept (deptno, dname, loc) VALUES (84, '회계팀', '충정로');
INSERT INTO dept (deptno, loc, dname) VALUES (85, '신대방', '자재팀');
INSERT INTO dept (deptno, loc) VALUES (86, '홍대');

-- 9910	백미선		전임강사		24/06/28		101
INSERT INTO professor (profno, name, position, hiredate, deptno) VALUES (9910, '백미선', '전임강사', sysdate, 101);
-- 9920	최윤식		조교수		06/01/01		102
INSERT INTO professor (profno, name, position, hiredate, deptno) VALUES (9920, '최윤식', '조교수', TO_DATE('2006/01/01', 'YYYY/MM/DD'), '102');
INSERT INTO professor (profno, name, position, hiredate, deptno) VALUES (9921, '최윤식3', '조교수', '2006/01/01', '102');

DROP TABLE job3;
CREATE TABLE job3(
    jobno NUMBER(2) PRIMARY KEY,
    jobname VARCHAR(20)
);

INSERT INTO job3 VALUES(10, '학교');
INSERT INTO job3 VALUES(11, '공무원');
INSERT INTO job3 VALUES(12, '공기업');
INSERT INTO job3 VALUES(13, '대기업');
INSERT INTO job3 VALUES(14, '중소기업');

CREATE TABLE Religion(
    religion_no NUMBER(2) CONSTRAINT PK_ReligionNo3 PRIMARY KEY,--PK제약조건 이름지정(인덱스에 PK목록 저장되어 있음)
    religion_name VARCHAR(20)
);

-- HW03
--10	기독교
--20	카톨릭교
--30	불교
--40	무교
INSERT INTO religion VALUES (10, '기독교');
INSERT INTO religion VALUES (20, '카톨릭교');
INSERT INTO religion VALUES (30, '불교');
INSERT INTO religion VALUES (40, '무교');
--INSERT INTO religion VALUES (11, '기독교'), (21, '카톨릭교'), (30, '불교'), (40, '무교'); -- 오라클에서는 지원하지 않음
INSERT ALL 
	INTO religion values (10, '기독교')
	INTO religion values (20, '카톨릭교')
	INTO religion values (30, '불교')
	INTO religion values (40, '무교')
select * from dual;

-----   다중 행 입력                         ------
--------------------------------------------------
-- 1. 생성된 TBL이용 신규 TBL 생성
CREATE TABLE dept_second --dept의 테이블 구조로 생성
AS 
SELECT * FROM dept;

-- 2. TBL 가공 생성
CREATE TABLE emp20
AS
SELECT empno, ename, sal*12 AS annsal -- 원하는 칼럼으로
FROM emp
WHERE deptno = 20; -- 데이터까지 포함

-- 3. TBL 구조만
CREATE TABLE dept30
AS
SELECT deptno, dname
FROM dept
WHERE 0=1;--데이터 없이

-- 4. Column 추가
ALTER TABLE dept30
ADD(birth DATE);

INSERT INTO dept30 VALUES (10, '중앙학교', sysdate);

-- 5. Column 변경
ALTER TABLE dept30
--MODIFY dname VARCHAR2(11); -- 이미 존재하는 데이터와 타입(크기)이 맞지 않음
MODIFY dname VARCHAR2(20);

-- 6. Column 삭제
ALTER TABLE dept30
DROP Column dname;

-- 7. TBL 명 변경
RENAME dept30 TO dept35;

-- 8. TBL 제거
DROP TABLE dept35;

-- 9. Truncate
-- DELETE FROM table 보다 빠름
-- DDL 판정으로 ROLLBACK되지 않음
TRUNCATE TABLE dept_second;

-- INSERT ALL(unconditional INSERT ALL) 명령문
-- 서브쿼리의 결과 집합을 조건없이 여러 테이블에 동시에 입력
-- 서브쿼리의 컬럼 이름과 데이터가 입력되는 테이블의 칼럼이 반드시 동일해야 함
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
-- [WHEN 조건절1 THEN
-- INTO [table1] VLAUES[(column1, column2,…)]
-- [WHEN 조건절2 THEN
-- INTO [table2] VLAUES[(column1, column2,…)]
-- [ELSE
-- INTO [table3] VLAUES[(column1, column2,…)]
-- subquery;
-- 학생 테이블에서 2학년 이상의 학생을 검색하여 
-- height_info 테이블에는 키가 170보다 큰 학생의 학번, 이름, 키를 입력
-- weight_info 테이블에는 몸무게가 70보다 큰 학생의 학번, 이름, 몸무게를 
-- 각각 입력하여라
INSERT ALL
    WHEN height > 170 THEN -- 5행
        INTO height_info VALUES (studno, name, height)
    WHEN weight > 75 THEN -- 3행
        INTO weight_info VALUES (studno, name, weight)
SELECT studno, name, height, weight
FROM student
WHERE grade >= '2';

-- 데이터 수정 개요
-- UPDATE 명령문은 테이블에 저장된 데이터 수정을 위한 조작어
-- WHERE 절을 생략하면 테이블의 모든 행을 수정
--- Update 
-- 문1) 교수 번호가 9903인 교수의 현재 직급을 ‘부교수’로 수정하여라
UPDATE professor 
SET position = '부교수', userid = 'kkk'
WHERE profno = 9903;
--  문2) 서브쿼리를 이용하여 학번이 10201인 학생의 학년과 학과 번호를
--  10103 학번 학생의 학년과 학과 번호와 동일하게 수정하여라
UPDATE student
SET (grade, deptno) = (
    SELECT grade, deptno
    FROM student
    WHERE studno = 10103
    )
WHERE studno = 10201;

-- 데이터 삭제 개요
-- DELETE 명령문은 테이블에 저장된 데이터 삭제를 위한 조작어
-- WHERE 절을 생략하면 테이블의 모든 행 삭제
COMMIT;-- 삭제 전 이전 데이터 저장

-- 문1) 학생 테이블에서 학번이 20103인 학생의 데이터를 삭제
DELETE FROM student WHERE studno = 20103;
ROLLBACK;

--  문2) 학생 테이블에서 컴퓨터공학과에 소속된 학생을 모두 삭제하여라.
DELETE FROM student 
WHERE deptno = (
    SELECT deptno
    FROM DEPARTMENT
    WHERE dname = '컴퓨터공학과');
ROLLBACK;

----------------------------------------------------------------------------------------------------------------
---- MERGE
--  1. MERGE 개요
--     구조가 같은 두개의 테이블을 비교하여 하나의 테이블로 합치기 위한 데이터 조작어
--     WHEN 절의 조건절에서 결과 테이블에 해당 행이 존재하면 UPDATE 명령문에 의해 새로운 값으로 수정,
--     그렇지 않으면 INSERT 명령문으로 새로운 행을 삽입
----------------------------------------------------------------------------------------------------------------
-- 1] MERGE 예비작업 
--  상황 
-- 1) 교수가 명예교수로 2행 Update
-- 2) 김도경 씨가 신규 Insert
CREATE TABLE professor_temp
AS
SELECT * FROM professor
WHERE position = '교수';

UPDATE professor_temp
SET position = '명예교수'
WHERE position = '교수';

INSERT INTO professor_temp
VALUES (9999, '김도경', 'arom21', '전임강사', 200, sysdate, 10, 101);

COMMIT;

-- 2] professor MERGE 수행 
-- 목표 : professor_temp에 있는 직위   수정된 내용을 professor Table에 Update
--                         김도경 씨가 신규 Insert 내용을 professor Table에 Insert
-- 1) 교수가 명예교수로 2행 Update
-- 2) 김도경 씨가 신규 Insert
MERGE INTO professor p -- 여기에
USING professor_temp pt -- 이것을
ON (p.profno = pt.profno)
WHEN MATCHED THEN -- ON절이 같으면 
    UPDATE SET p.position = pt.position
WHEN NOT MATCHED THEN -- ON절이 다르면
    INSERT VALUES (pt.profno, pt.name, pt.userid, pt.position, pt.sal, pt.hiredate, pt.comm, pt.deptno)
;

--------------------------------------------------------------------------
------------            제약조건(Constraint)        ***        ------------
--  정의  : 데이터의 정확성과 일관성을 보장
-- 1. 테이블 생성시 무결성 제약조건을 정의 가능
-- 2. 테이블에 대해 정의, 데이터 딕셔너리에 저장되므로 응용 프로그램에서 
--     입력된 모든 데이터에 대해 동일하게 적용
-- 3. 제약조건을 활성화, 비활성화 할 수 있는 융통성
-------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------
------------            제약조건(Constraint)   종류      ***               ------------
-- 1 .NOT NULL  : 열이 NULL을 포함할 수 없음
-- 2. 기본키(primary key) : UNIQUE +  NOT NULL + 최소성  제약조건을 결합한 형태 ***
--     PK
-- 3. 참조키(foreign key) :  테이블 간에 외래 키 관계를 설정 ***
-- 4. CHECK : 해당 칼럼에 저장 가능한 데이터 값의 범위나 조건 지정
-------------------------------------------------------------------------------------
CREATE TABLE subject(
    subno NUMBER(5) CONSTRAINT subject_no_pk PRIMARY KEY,
    subname VARCHAR2(20) CONSTRAINT subject_name_nn NOT NULL,
    term VARCHAR2(1) CONSTRAINT subject_term_ck CHECK(term IN('1','2')),
    typeGubun VARCHAR2(1)
);
COMMENT ON COLUMN subject.subno IS '수강번호';
COMMENT ON COLUMN subject.subname IS '수강과목';
COMMENT ON COLUMN subject.term IS '학기';

INSERT INTO subject(subno , subname , term) Values(10000, '컴퓨터개론', '1');
INSERT INTO subject(subno , subname , term, typegubun) Values(10001, 'DB개론', '2', '1');
INSERT INTO subject(subno , subname , term, typegubun) Values(10002, 'JSP개론', '1', '1');

-- PK Constraint --> Unique violated(유일성 위반)
INSERT INTO subject(subno , subname , term, typegubun) Values(10001, 'Spring개론', '1', '1');
-- PK Constraint --> NOT NULL violated(NOT NULL 위반)
INSERT INTO subject(subname, term, typegubun) Values('Spring개론2', '1', '1');
-- subname --> NOT NULL violated(NOT NULL 위반)
INSERT INTO subject(subno, term, typegubun) Values(10003, '1', '1');
-- term --> CHECK violated(check 위반)
INSERT INTO subject(subno, subname, term, typegubun) Values(10003, 'Spring개론3', '5', '1');

-- Table 선언시 못한것을 추후 정의 가능
-- Student Table 의 idnum을 unique로 선언
ALTER TABLE student
ADD CONSTRAINT stud_idnum_uk UNIQUE(idnum);

INSERT INTO student(studno, name, idnum) VALUES(30101, '대조영','8012301036613');
-- idnum --> unique violated(유일성 위반)
INSERT INTO student(studno, name, idnum) VALUES(30102, '강감찬','8012301036613');

-- CONSTRAINT 조회
-- P : PK
-- U : Unique
-- C : Check + NotNull
SELECT constraint_name, constraint_type
FROM user_constraints
WHERE table_name IN('SUBJECT', 'STUDENT');
