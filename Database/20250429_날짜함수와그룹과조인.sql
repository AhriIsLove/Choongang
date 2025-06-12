---------------------------------------------------------
-------- 날자 함수 ***   --------------------------------
---------------------------------------------------------
-- 1) 날짜 + 숫자 = 날짜 (날짜에 일수를 가산)
-- 교수 번호가 9908인 교수의 입사일을 기준으로 입사 30일 후와 60일 후의 날짜를 출력
SELECT name, hiredate, hiredate+30, hiredate+60
FROM professor
WHERE profno = 9908
;
-- 2) SYSDATE 함수
--    SYSDATE 함수는 시스템에 저장된 현재 날짜를 반환하는 함수로서, 초 단위까지 반환
SELECT sysdate FROM dual;
-- 3) 날짜 - 숫자 = 날짜 (날짜에 일수를 감산)
SELECT name, hiredate, hiredate-30, hiredate-60
FROM professor
WHERE profno=9908
;
-- 4) 날짜 - 날짜=  일수 (날짜에 날짜를 감산)
SELECT name, hiredate, sysdate - hiredate
FROM professor
WHERE profno=9908
;
-- 5) MONTHS_BETWEEN : date1과 date2 사이의 개월 수를 계산
--     ADD_MONTHS    : date에 개월 수를 더한 날짜 계산
--     MONTHS_BETWEEN과 ADD_MONTHS 함수는 월 단위로 날짜 연산을 하는 함수 
--    문) 입사한지 120개월 미만인 교수의 교수번호, 입사일, 입사일로 부터 현재일까지의 개월 수,
--        입사일에서 6개월 후의 날짜를 출력하여라
SELECT profno, hiredate, sysdate, 
    MONTHS_BETWEEN(sysdate, hiredate) AS working_day, 
    ADD_MONTHS(hiredate, 6) AS hire_6After
FROM professor
WHERE MONTHS_BETWEEN(sysdate, hiredate) < 120
;
-- TO_CHAR 함수
-- TO_CHAR 함수는 날짜나 숫자를 문자로 변환하기 위해 사용
-- 학생 테이블에서 전인하 학생의 학번과 생년월일 중에서 년월만 출력하여라
-- YYYY/YY/MM/MONTH/MON/DD/DY/DAY
SELECT studno, 
    TO_CHAR(birthdate, 'YY/MM') AS birthdate1,
    TO_CHAR(birthdate, 'yy-mm') AS birthdate2,
    TO_CHAR(birthdate, 'yymm') AS birthdate3,
    TO_CHAR(birthdate, 'yymmdd') AS birthdate4,
    TO_CHAR(birthdate, 'yyyymmdd') AS birthdate5,
    TO_CHAR(birthdate, 'yyyy/yy/mm/month/mon/dd/dy/day') AS birthdate6
FROM student
WHERE name = '전인하'
;
SELECT 
    TO_CHAR(sysdate, 'MONTH') AS month,
    TO_CHAR(sysdate, 'DY') AS dy,
    TO_CHAR(sysdate, 'DAY') As day
FROM dual;
-- LAST_DAY, NEXT_DAY
-- LAST_DAY 함수는 해당 날짜가 속한 달의 마지막 날짜를 반환하는 함수
-- NEXT_DAY 함수는 해당 일을 기준으로 명시된 요일의 다음 날짜를 변환하는 함수
-- 오늘이 속한 달의 마지막 날짜와 다가오는 일요일의 날짜를 출력하여라
SELECT sysdate, LAST_DAY(sysdate), NEXT_DAY(sysdate,'토') FROM dual;

SELECT
    TO_CHAR(sysdate, 'YY/MM/DD HH24:MI:SS') AS NORMAL
FROM dual
;
-- 날짜도 반올림이 가능
SELECT name, 
    TO_CHAR(hiredate, 'YY/MM/DD HH24:MI:SS') AS hiredate,
    TO_CHAR(ROUND(hiredate,'dd'), 'YY/MM/DD HH24:MI:SS') AS round_dd,
    TO_CHAR(ROUND(hiredate,'mm'), 'YY/MM/DD HH24:MI:SS') AS round_mm,-- ~15일 내림, 16일~ 올림 (2월 관계 없음)
    TO_CHAR(ROUND(hiredate,'yy'), 'YY/MM/DD HH24:MI:SS') AS round_yy
FROM professor
;

-- TO_CHAR 함수를 이용한 숫자 출력 형식 변환 --> '9'를 사용한다.
SELECT TO_CHAR(1234567, '9,999,999') FROM dual;

--문1) 보직수당을 받는 교수들의 이름, 급여, 보직수당, 
--그리고 급여와 보직수당을 더한 값에 12를 곱한 결과를 연봉(anual_sal)으로 출력
SELECT name, sal, comm, TO_CHAR((sal+comm)*12, '9,999') AS anual_sal
FROM professor
WHERE comm IS NOT NULL
;
--문2) student Table에서 주민등록번호에서 생년월일을 추출하여 문자 ‘YY/MM/DD’ 형태로 
--별명 BirthDate로 출력하여라
SELECT idnum, 
    SUBSTR(idnum, 1,2)||'/'||SUBSTR(idnum, 3,2)||'/'||SUBSTR(idnum, 5,2) AS BirthDate,
    TO_DATE(SUBSTR(idnum, 1,6), 'YY-MM-DD') AS BirthDate2, -- TO_DATE는 무조건 YY/MM/DD로 출력함
    TO_CHAR(TO_DATE(SUBSTR(idnum, 1,6), 'YY-MM-DD'), 'YY-MM-DD') AS BirthDate3 -- TO_CHAR로 원하는 양식 조절 가능
FROM student
;
--문3) NVL 함수는 NULL을 0 또는 다른 값으로 변환하기 위한 함수
-- 101번 학과 교수의 이름, 직급, 급여, 보직수당, 급여와 보직수당의 합계를 출력하여라. 
-- 단, 보직수당이 NULL인 경우에는 보직수당을 0으로 계산
SELECT name, position, sal, comm, 
    sal+NVL(comm,0),
    NVL(sal+comm, sal)
FROM professor
WHERE deptno = 101
;
------------------------------------------------------------------------
------                              Question                   ---------
------------------------------------------------------------------------
-- 1. salgrade 데이터 전체 보기
SELECT * FROM salgrade;
-- 2. scott에서 사용가능한 테이블 보기
SELECT * FROM tab;
-- 3. emp Table에서 사번 , 이름, 급여, 업무, 입사일
SELECT empno, ename, sal, job, hiredate FROM emp;
-- 4. emp Table에서 급여가 2000미만인 사람 에 대한 사번, 이름, 급여 항목 조회
SELECT empno, ename, sal FROM emp WHERE sal < 2000;
-- 5. emp Table에서 80/02이후에 입사한 사람에 대한  사번,이름,업무,입사일 
SELECT empno, ename, job, hiredate FROM emp WHERE hiredate > '80/02/01';
-- 6. emp Table에서 급여가 1500이상이고 3000이하 사번, 이름, 급여  조회(2가지)
SELECT empno, ename, sal FROM emp WHERE sal >= 1500 AND sal <= 3000;
SELECT empno, ename, sal FROM emp WHERE sal BETWEEN 1500 AND 3000;
-- 7. emp Table에서 사번, 이름, 업무, 급여 출력 [ 급여가 2500이상이고  업무가 MANAGER인 사람]
SELECT empno, ename, job, sal FROM emp WHERE sal >= 2500 AND job = 'MANAGER';
-- 8. emp Table에서 이름, 급여, 연봉 조회 
--    [단 조건은  연봉 = (급여+상여) * 12  , null을 0으로 변경]
SELECT ename, sal, (sal+NVL(comm, 0))*12 AS "연봉" FROM emp;
--9. emp Table에서  81/02 이후에 입사자들중 xxx는 입사일이 xxX
-- CONCAT과 ||를 활용하여 [ 전체 Row 출력 ] --> 2가지 방법 다
SELECT ename||'는 입사일이 '||hiredate FROM emp WHERE hiredate - TO_DATE('81/02/01') >= 0;
SELECT CONCAT(CONCAT(ename,'는 입사일이 '), hiredate) FROM emp WHERE hiredate >= '81/02/01';
--10.emp Table에서 이름속에 T가 있는 사번,이름 출력
SELECT empno, ename FROM emp WHERE ename LIKE '%T%';

-- NVL2 함수
-- NVL2 함수는 첫 번째 인수 값이 NULL이 아니면 두 번째 인수 값을 출력하고, 
--            첫 번째 인수 값이 NULL이면 세 번째 인수 값을 출력하는 함수
-- 예시) 102번 학과 교수중에서 보직수당을 받는 사람은 급여와 보직수당을 더한 값을 급여 총액으로 출력하여라. 
-- 단, 보직수당을 받지 않는 교수는 급여만 급여 총액으로 출력하여라.
SELECT name, position, sal, comm, NVL2(comm, sal+comm, sal) AS total
FROM professor
WHERE deptno = 102;

--NULLIF 함수
--NULLIF 함수는 두 개의 표현식을 비교하여 값이 동일하면 NULL을 반환하고,
--                                        일치하지 않으면 첫 번째 표현식의 값을 반환
-- 예시) 교수 테이블에서 이름의 바이트 수와 사용자 아이디의 바이트 수를 비교해서
--같으면 NULL을 반환하고 
--같지 않으면 이름의 바이트 수를 반환
SELECT name, userid, LENGTHB(name), LENGTHB(userid),
    NULLIF(LENGTHB(name), LENGTHB(userid)) AS nullif
FROM professor;

-- DECODE 함수
-- DECODE 함수는 기존 프로그래밍 언어에서 IF문이나 CASE 문으로 표현되는 복잡한 알고리즘을 
-- 하나의 SQL 명령문으로 간단하게 표현할 수 있는 유용한 기능 
-- DECODE 함수에서 비교 연산자는 ‘=‘만 가능
-- NULL = NULL을 참으로 판단
-- 오라클 전용

-- 교수 테이블에서 교수의 소속 학과 번호를 학과 이름으로 변환하여 출력하여라. 
-- 학과 번호가 101이면 ‘컴퓨터공학과’, 102이면 ‘멀티미디어학과’, 201이면 ‘전자공학과’, 
-- 나머지 학과 번호는 ‘기계공학과’(default)로 변환
-- Java  --> If ElseIf Else
SELECT name, deptno,
    DECODE(deptno, --switch(deptno)
    101, '컴퓨터공학과', --case 101:'컴퓨터공학과'
    102, '멀티미디어학과', --case 102:'멀티미디어학과'
    201, '전자공학과', --case 201:'전자공학과'
    '기계공학과') --default: '기계공학과'
FROM professor
;

-- CASE 함수
-- CASE 함수는 DECODE 함수의 기능을 확장한 함수 
-- DECODE 함수는 표현식 또는 칼럼 값이 ‘=‘ 비교를 통해 조건과 일치하는 경우에만 다른 값으로 대치할 수 있지만
-- , CASE 함수에서는 산술 연산, 관계 연산, 논리 연산과 같은 다양한 비교가 가능
-- 또한 WHEN 절에서 표현식을 다양하게 정의
-- NULL = NULL을 거짓으로 판단
-- NULL IS NULL을 참으로 판단
-- 8.1.7에서부터 지원되었으며, 9i에서 SQL, PL/SQL에서 완벽히 지원 
-- DECODE 함수에 비해 직관적인 문법체계와 다양한 비교 표현식 사용
SELECT name, deptno,
    CASE 
        WHEN deptno = 101 THEN '컴퓨터공학과'--if
        WHEN deptno = 102 THEN '멀티미디어학과'--elseif
        WHEN deptno = 201 THEN '전자공학과'--elseif
        ELSE '기계공학과'--else
    END deptname--AS
FROM professor;

-- Decode / Case
-- 교수 테이블에서 소속 학과에 따라 이름, 학과번호, 급여, 보너스출력하되 보너스는 계산하여 출력하여라. (별명 --> bonus)
-- 학과 번호별로 보너스는 다음과 같이 계산한다.
-- 학과 번호가 101이면 보너스는 급여의 10%, 102이면 20%, 201이면 30%, 나머지 학과는 0%
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
--1. emp Table 의 이름을 대문자, 소문자, 첫글자만 대문자로 출력
SELECT UPPER(ename), LOWER(ename), INITCAP(ename) FROM emp;
--2. emp Table 의  이름, 업무, 업무를 2-5사이 문자 출력
SELECT ename, job, SUBSTR(job, 2,5) FROM emp;
--3. emp Table 의 이름, 이름을 10자리로 하고 왼쪽에 #으로 채우기
SELECT ename, LPAD(ename, 10, '#') FROM emp;
-- 4. emp Table 의  이름, 업무, 업무가 MANAGER면 관리자로 출력
SELECT ename, 
    DECODE(job,
        'MANAGER', '관리자',
        job)
FROM emp;
--5. emp Table 의  이름, 급여/7을 각각 정수, 소숫점 1자리. 10단위로   반올림하여 출력
SELECT ename, ROUND(sal/7), ROUND(sal/7, 1), ROUND(sal/7, -1) FROM emp;
--6.  emp Table 의  이름, 급여/7을 각각 절사하여 출력
SELECT ename, trunc(sal/7), trunc(sal/7, 1), trunc(sal/7, -1) FROM emp;
--7.  emp Table 의  이름, 급여/7한 결과를 반올림,절사,ceil,floor
SELECT ename, ROUND(sal/7), trunc(sal/7), CEIL(sal/7), FLOOR(sal/7) FROM emp;
--8.  emp Table 의  이름, 급여, 급여/7한 나머지
SELECT ename, sal, MOD(sal, 7) FROM emp;
--9.  emp Table 의 이름, 급여, 입사일, 입사기간(각각 날자,월)출력,  소숫점 이하는 반올림
SELECT ename, sal, hiredate, ROUND(sysdate-hiredate), ROUND(MONTHS_BETWEEN(sysdate,hiredate)) FROM emp;
--10. emp Table 의  job 이 'CLERK' 일때 10% ,'ANALYSY' 일때 20% 
--                                 'MANAGER' 일때 30% ,'PRESIDENT' 일때 40%
--                                 'SALESMAN' 일때 50% 
--                                 그외일때 60% 인상 하여 
--   empno, ename, job, sal, 및 각 인상 급여를 출력하세요(CASE/Decode문 사용)
SELECT empno, ename, job, sal, --방법1
    CASE 
        WHEN job = 'CLERK' THEN sal*1.1
        WHEN job = 'ANALYSY' THEN sal*1.2
        WHEN job = 'MANAGER' THEN sal*1.3
        WHEN job = 'PRESIDENT' THEN sal*1.4
        WHEN job = 'SALESMAN' THEN sal*1.5
        ELSE sal*1.6
    END "인상 급여"
FROM emp;
SELECT empno, ename, job, sal, --방법2
    CASE job
        WHEN 'CLERK' THEN sal*1.1
        WHEN 'ANALYSY' THEN sal*1.2
        WHEN 'MANAGER' THEN sal*1.3
        WHEN 'PRESIDENT' THEN sal*1.4
        WHEN 'SALESMAN' THEN sal*1.5
        ELSE sal*1.6
    END "인상 급여"
FROM emp;

---------------------------------------------------------------------
---   8장. 그룹함수  * ~ **
----  테이블의 전체 행을 하나 이상의 컬럼을 기준으로 그룹화하여
---   그룹별로 결과를 출력하는 함수
---   그룹함수는 통계적인 결과를 출력하는데 자주 사용
---------------------------------------------------------------------
-- SELECT  column, group_function(column)
-- FROM  table
-- [WHERE  condition]
-- [GROUP BY  group_by_expression]
-- [HAVING  group_condition]
-- ?GROUP BY : 전체 행을 group_by_expression을 기준으로 그룹화
-- ?HAVING : GROUP BY 절에 의해 생성된 그룹별로 조건 부여
------------------------------------------------------------------------

--종류                   의미
--COUNT             행의 개수 출력
--MAX               NULL을 제외한 모든 행에서 최대 값
--MIN               NULL을 제외한 모든 행에서 최소 값
--SUM               NULL을 제외한 모든 행의 합
--AVG               NULL을 제외한 모든 행의 평균 값
------------------위 필수 아래 G-----------------------------------------
--STDDEV            NULL을 제외한 모든 행의 표준편차
--VARIANCE          NULL을 제외한 모든 행의 분산 값
--GROUPING          해당 칼럼이 그룹에 사용되었는지 여부를 1 또는 0으로 반환
--GROUPING SETS     한 번의 질의로 여러 개의 그룹화 기능

-- 1) COUNT 함수
-- 테이블에서 조건을 만족하는 행의 갯수를 반환하는 함수
-- 문1) 101번 학과 교수중에서 보직수당을 받는 교수의 수를 출력하여라
SELECT COUNT(*), COUNT(comm)
FROM professor
WHERE deptno = 101;
--102번 학과 학생들의 몸무게 평균과 합계를 출력하여라
SELECT AVG(weight), SUM(weight)
FROM student
WHERE deptno = 102;
-- 교수 테이블에서 급여의 표준편차와 분산을 출력
SELECT STDDEV(sal), VARIANCE(sal)
FROM professor;

-- 학과별  학생들의 인원수, 몸무게 평균과 합계를 출력하여라
SELECT deptno, COUNT(*), AVG(weight), SUM(weight)
FROM student
GROUP BY deptno
;
-- 교수 테이블에서 학과별로 교수 수와 보직수당을 받는 교수 수를 출력하여라
SELECT deptno, COUNT(*), COUNT(comm)
FROM professor
GROUP BY deptno
;
-- 교수 테이블에서 학과별로 교수 수와 보직수당을 받는 교수 수를 출력하여라
-- 단 학과별로 교수 수가 2명 이상인 학과만 출력
SELECT deptno, COUNT(*), COUNT(comm)
FROM professor
GROUP BY deptno -- 그룹의 기준
HAVING COUNT(*) >= 2 -- 그룹의 기준의 조건
;
-- 학생 수가 4명이상이고 평균키가 168이상인  학년에 대해서 학년, 학생 수, 평균 키, 평균 몸무게를 출력
-- 단, 평균 키와 평균 몸무게는 소수점 두 번째 자리에서 반올림 하고, 
-- 출력순서는 평균 키가 높은 순부터 내림차순으로 출력하고
-- 그 안에서 평균 몸무게가 높은 순부터 내림차순으로 출력
SELECT grade, COUNT(*), 
    ROUND(AVG(height), 1) AS AVG_HEIGHT, 
    ROUND(AVG(weight), 1) AS AVG_WEIGHT
FROM student
GROUP BY grade
HAVING COUNT(*) >= 4 AND AVG(height) >= 168
ORDER BY AVG(height) DESC, AVG(weight) DESC
;

--  최근 입사 사원과 가장 오래된 사원의 입사일 출력 (emp)
SELECT MAX(hiredate), MIN(hiredate)
FROM emp;
--  부서별 최근 입사 사원과 가장 오래된 사원의 입사일 출력 (emp)
SELECT deptno, MAX(hiredate), MIN(hiredate)
FROM emp
GROUP BY deptno
;
-- 부서별, 직업별 count & sum[급여]    (emp)
SELECT deptno, job, count(deptno), count(job), sum(sal)
FROM emp
GROUP BY deptno, job
;
-- 부서별 급여총액 3000이상 부서번호,부서별 급여최대    (emp)
SELECT deptno, max(sal)
FROM emp
GROUP BY deptno
HAVING SUM(sal) >= 3000
;

-- 전체 학생을 소속 학과별로 나누고, 같은 학과 학생은 다시 학년별로 그룹핑하여, 
-- 학과와 학년별 인원수, 평균 몸무게를 출력, 
-- (단, 평균 몸무게는 소수점 이하 첫번째 자리에서 반올림 )  STUDENT
SELECT deptno, grade, count(grade), ROUND(avg(weight))
FROM student
GROUP BY deptno, grade
;

-- ROLLUP 연산자 : 그룹별 Total Sum을 구하는 연산자
-- GROUP BY 절의 그룹 조건에 따라 전체 행을 그룹화하고 각 그룹에 대해 부분합을 구하는 연산자
-- 문) 소속 학과별로 교수 급여 합계와 모든 학과 교수들의 급여 합계를 출력하여라
SELECT deptno, SUM(sal)
FROM professor
GROUP BY ROLLUP(deptno) --Total Sum을 최하단에 출력
--GROUP BY deptno
;
--문2) ROLLUP 연산자를 이용하여 학과 및 직급별 교수 수, 학과별 교수 수, 전체 교수 수를 출력하여라
SELECT deptno, position, COUNT(*)
FROM professor
GROUP BY ROLLUP(deptno, position)
;
-- CUBE 연산자 : 그룹별 Total Sum을 구하는 연산자(상단에 표시)
-- ROLLUP에 의한 그룹 결과와 GROUP BY 절에 기술된 조건에 따라 그룹 조합을 만드는 연산자
-- 문1) CUBE 연산자를 이용하여 학과 및 직급별 교수 수, 학과별 교수 수, 전체 교수 수를 출력하여라.
SELECT deptno, position, COUNT(*)
FROM professor
GROUP BY CUBE(deptno, position)
;

-------------------------------------------------------------------------------------
----                              DeadLock                                  ---------
-------------------------------------------------------------------------------------
--  Transaction A --->  Developer
UPDATE emp --실행1
SET sal = sal*1.1
WHERE empno = 7369--SMITH
;
UPDATE emp --실행3(무한대기)
SET sal = sal*1.1
WHERE empno = 7839--KING
;

--  Transaction B --->  cmd(Sqlplus)
UPDATE emp --실행2
SET comm = 500
WHERE empno = 7839--KING
;
UPDATE emp --실행4(무한대기,실행3오류발생)
SET comm = 500
WHERE empno = 7369--SMITH
;

----------------------------------------------------------------------
----                    9-1.     JOIN       ***              ---------
----------------------------------------------------------------------
-- 1) 조인의 개념
-- 하나의 SQL 명령문에 의해 여러 테이블에 저장된 데이터를 한번에 조회할수 있는 기능
-- ex1-1) 학번이 10101인 학생의 이름과 소속 학과 이름을 출력하여라
SELECT studno, name, deptno
FROM student
WHERE studno = 10101;
-- ex1-2)학과를 가지고 학과이름
SELECT dname
FROM department
WHERE deptno = 101;
-- ex1-3)  [ex1-1] + [ex1-2] 한방 조회  ---> Join
SELECT studno, name, student.deptno, department.dname
FROM student, department
WHERE student.deptno = department.deptno;
-- ex1-3)  [ex1-1] + [ex1-2] 한방 조회  ---> Join
SELECT s.studno, s.name, s.deptno, d.dname
FROM student s, department d
WHERE s.deptno = d.deptno;

-- 전인하 학생의 학번, 이름, 학과 이름 그리고 학과 위치를 출력
SELECT s.studno, s.name, d.dname, d.loc
FROM student s, department d
WHERE s.deptno = d.deptno AND s.name = '전인하'
;
-- 몸무게가 80kg이상인 학생의 학번, 이름, 체중, 학과 이름, 학과위치를 출력
SELECT s.studno, s.name, s.weight, d.dname, d.loc
FROM student s, department d
WHERE s.deptno = d.deptno AND s.weight >= 80
;

-- 카티션 프로덕트(cartesian product) = CROSS JOIN : 두 개 이상의 테이블에 대해 연결 가능한 행을 모두 결합
-- 1) 개발자 실수
-- 2) 개발초기에 많은Data 생성
SELECT s.studno, s.name, d.dname, d.loc, s.weight, d.deptno
FROM student s, department d
;
-- ANSI SQL : CROSS JOIN
SELECT s.studno, s.name, d.dname, d.loc, s.weight, d.deptno
FROM student s CROSS JOIN department d
;
-- ***
-- 조인 대상 테이블에서 공통 칼럼을 ‘=‘(equal) 비교를 통해 같은 값을 가지는 행을 연결하여
--  결과를 생성하는 조인 방법
--  SQL 명령문에서 가장 많이 사용하는 조인 방법
-- 자연조인을 이용한 EQUI JOIN
-- 오라클 9i 버전부터 Inner(EQUI) JOIN을 자연조인이라 명명
-- WHERE 절을 사용하지 않고  NATURAL JOIN 키워드 사용
-- 오라클에서 자동적으로 테이블의 모든 칼럼을 대상으로 공통 칼럼을 조사 후, 내부적으로 조인문 생성

-- 오라클 INNER Join 표기법
SELECT s.studno, s.name, d.dname, d.loc, d.deptno
FROM student s, department d
WHERE s.deptno = d.deptno;
-- ANSI INNER Join 표기법 : NATURAL JOIN
-- Natural Join Convert Error 해결 
-- NATURAL JOIN시 조인 애트리뷰트에 테이블 별명을 사용하면 오류가 발생
SELECT s.studno, s.name, d.dname, d.loc, d.deptno -- 오류
FROM student s NATURAL JOIN department d;
-- 조인을 위한 공통된 칼럼은 구분하지 않도록 작성해야 오류 해결
SELECT s.studno, s.name, d.dname, d.loc, deptno
FROM student s NATURAL JOIN department d;

-- NATURAL JOIN을 이용하여 교수 번호, 이름, 학과 번호, 학과 이름을 출력하여라
SELECT p.profno, p.name, deptno, d.dname
FROM professor p NATURAL JOIN department d;
-- NATURAL JOIN을 이용하여 4학년 학생의 이름, 학과 번호, 학과이름을 출력하여라
SELECT s.name, deptno, d.dname
FROM student s NATURAL JOIN department d
WHERE s.grade = 4;
-- ANSI(JOIN ~ USING 절을 이용한 Inner JOIN)
-- USING절에 조인 대상 칼럼을 지정
-- 칼럼 이름은 조인 대상 테이블에서 동일한 이름으로 정의되어 있어야함
-- 문1) JOIN ~ USING 절을 이용하여 학번, 이름, 학과번호, 학과이름, 학과위치를
-- 출력하여라
SELECT s.studno, s.name, deptno, d.dname
FROM student s JOIN department d 
    USING(deptno);
SELECT s.studno, s.name, s.deptno, d.dname
FROM student s INNER JOIN department d 
    ON s.deptno = d.deptno;

-- EQUI JOIN의 4가지 방법을 이용하여 성이 ‘김’씨인 학생들의 이름, 학과번호,학과이름을 출력
-- ORACLE : WHERE a.a = b.a
-- ANSI : NATURAL JOIN
-- ANSI : JOIN - USING
-- ANSI : INNER JOIN - ON(ORACLE JOIN이 여기서 파생된듯)
SELECT s.studno, s.name, s.deptno, d.dname --공통 칼럼에 대하여 테이블 지정
FROM student s, department d
WHERE s.deptno = d.deptno
AND s.name LIKE '김%';
--
SELECT s.studno, s.name, deptno, d.dname --공통 칼럼에 대하여 테이블 지정X
FROM student s NATURAL JOIN department d
WHERE s.name LIKE '김%';
--
SELECT s.studno, s.name, deptno, d.dname --공통 칼럼에 대하여 테이블 지정X
FROM student s JOIN department d USING(deptno)
WHERE s.name LIKE '김%';
--
SELECT s.studno, s.name, s.deptno, d.dname --공통 칼럼에 대하여 테이블 지정
FROM student s INNER JOIN department d ON s.deptno = d.deptno
WHERE s.name LIKE '김%';

-- NON-EQUI JOIN **
-- ‘<‘,BETWEEN a AND b 와 같이 ‘=‘ 조건이 아닌 연산자 사용
-- 교수 테이블과 급여 등급 테이블을 NON-EQUI JOIN하여 
-- 교수별로 급여 등급을 출력하여라
CREATE TABLE "SCOTT"."SALGRADE2" 
(	"GRADE" NUMBER(2,0), 
    "LOSAL" NUMBER(5,0), 
    "HISAL" NUMBER(5,0)
);

SELECT p.profno, p.name, p.sal, s.grade
FROM professor p, salgrade2 s
WHERE p.sal BETWEEN s.losal AND s.hisal;
