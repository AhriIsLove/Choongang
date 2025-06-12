-- Backup Dir 생성
CREATE OR REPLACE DIRECTORY mdBackup2 AS 'C:\oraclexe\backup'; -- 가상의 폴더 생성(mdBackup2라는 이름으로 정의함)
GRANT read, write ON DIRECTORY mdBackup2 TO scott; -- 해당 폴더를 scott에게 권한 부여함


