CREATE TABLESPACE INVENTORY
DATAFILE '/etc/oracle/oradata/inventory.dbf'
SIZE 100M
AUTOEXTEND ON NEXT 10M;

CREATE USER inventory IDENTIFIED BY inventory
DEFAULT TABLESPACE inventory
PROFILE DEFAULT
QUOTA UNLIMITED ON inventory;

GRANT CONNECT, RESOURCE TO inventory;

UPDATE sys.props$ SET value$ = 'AL32UTF8' WHERE NAME = 'NLS_CHARACTERSET';
UPDATE sys.props$ SET value$ = 'AL16UTF16' WHERE NAME = 'NLS_NCHAR_CHARACTERSET';
COMMIT;

ALTER SYSTEM ENABLE RESTRICTED SESSION;
ALTER SYSTEM SET JOB_QUEUE_PROCESSES = 0;
ALTER DATABASE OPEN;
ALTER DATABASE CHARACTER SET INTERNAL_USE AL32UTF8;
ALTER DATABASE NATIONAL CHARACTER SET INTERNAL_USE AL16UTF16;

SHUTDOWN IMMEDIATE;
STARTUP;

