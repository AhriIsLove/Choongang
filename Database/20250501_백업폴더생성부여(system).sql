-- Backup Dir ����
CREATE OR REPLACE DIRECTORY mdBackup2 AS 'C:\oraclexe\backup'; -- ������ ���� ����(mdBackup2��� �̸����� ������)
GRANT read, write ON DIRECTORY mdBackup2 TO scott; -- �ش� ������ scott���� ���� �ο���


