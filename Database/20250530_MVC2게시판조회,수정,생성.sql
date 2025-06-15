--답변 글 정렬을 위해 30번 글과 답변들 조회
SELECT num, writer, ref, re_step, re_level, subject 
FROM board 
WHERE ref = 30 
ORDER BY ref DESC, re_step ASC
;

--모든 글들을 답변글 정렬하며 원하는 순서대로 조회
--하지만 10~부터 조회를 시도하면 나오지 않음
SELECT rownum, a.*
FROM 
    (SELECT * 
    FROM board 
    ORDER BY ref DESC, re_step ASC
    ) a
WHERE rownum BETWEEN 1 AND 10;

--해결하기 위해 서브쿼리로 넣어서 다시 조회
SELECT * 
FROM 
    (SELECT rownum AS rn, a.*
    FROM 
        (SELECT * 
        FROM board 
        ORDER BY ref DESC, re_step ASC
        ) a
    )
WHERE rn BETWEEN 11 AND 20
;

--조회수 증가 쿼리1
UPDATE board SET readcount = (
    SELECT readcount+1
    FROM board
    WHERE num = 1)
WHERE num = 1
;
--조회수 증가 쿼리2
UPDATE board SET readcount = readcount+1
WHERE num = 1
;

--글 작성시 num에 MAX+1
INSERT INTO 
board(num, writer, subject, content, email, readcount, passwd, ref, re_step, re_level, ip, reg_date) 
VALUES((SELECT MAX(num)+1 FROM board), 'abc', 'abc', 'abc', 'abc@abc.com', 0, 'abc', (SELECT MAX(num)+1 FROM board), 0, 0, 'abc', sysdate);