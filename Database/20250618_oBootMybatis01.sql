CREATE OR REPLACE PROCEDURE Dept_Insert3(
    p_ideptno IN dept.deptno%type,
    p_idname IN dept.dname%type,
    p_iloc IN dept.loc%type,
    p_odeptno OUT dept.deptno%type,
    p_odname OUT dept.dname%type,
    p_oloc OUT dept.loc%type)
IS
BEGIN
    INSERT INTO dept VALUES (p_ideptno, p_idname, p_iloc);
    commit;
    
    SELECT deptno, dname, loc
    INTO p_odeptno, p_odname, p_oloc
    FROM dept
    WHERE deptno = p_ideptno;
    
    dbms_output.enable;
    dbms_output.put_line('p_odeptno : ' || p_odeptno);
    dbms_output.put_line('p_odname : ' || p_odname);
    dbms_output.put_line('p_oloc : ' || p_oloc);
END;

CREATE OR REPLACE PROCEDURE Dept_Cursor3(
    sdeptno IN dept.deptno%type,
    edeptno IN dept.deptno%type,
--    커서문결과를 객체처럼 반환한다
    dept_cursor OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN dept_cursor 
    FOR
        SELECT deptno, dname, loc
        FROM dept
        WHERE deptno BETWEEN sdeptno AND edeptno;
END;

