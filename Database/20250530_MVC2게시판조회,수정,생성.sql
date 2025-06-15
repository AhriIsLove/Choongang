--�亯 �� ������ ���� 30�� �۰� �亯�� ��ȸ
SELECT num, writer, ref, re_step, re_level, subject 
FROM board 
WHERE ref = 30 
ORDER BY ref DESC, re_step ASC
;

--��� �۵��� �亯�� �����ϸ� ���ϴ� ������� ��ȸ
--������ 10~���� ��ȸ�� �õ��ϸ� ������ ����
SELECT rownum, a.*
FROM 
    (SELECT * 
    FROM board 
    ORDER BY ref DESC, re_step ASC
    ) a
WHERE rownum BETWEEN 1 AND 10;

--�ذ��ϱ� ���� ���������� �־ �ٽ� ��ȸ
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

--��ȸ�� ���� ����1
UPDATE board SET readcount = (
    SELECT readcount+1
    FROM board
    WHERE num = 1)
WHERE num = 1
;
--��ȸ�� ���� ����2
UPDATE board SET readcount = readcount+1
WHERE num = 1
;

--�� �ۼ��� num�� MAX+1
INSERT INTO 
board(num, writer, subject, content, email, readcount, passwd, ref, re_step, re_level, ip, reg_date) 
VALUES((SELECT MAX(num)+1 FROM board), 'abc', 'abc', 'abc', 'abc@abc.com', 0, 'abc', (SELECT MAX(num)+1 FROM board), 0, 0, 'abc', sysdate);