USE GADB;

SET TRANSACTION ISOLATION LEVEL READ COMMITTED;

BEGIN TRANSACTION
    SELECT *
    FROM store_table;
    EXEC logg_data 'store_table', '', 'READ_UNCOMMITED_DATA';
    WAITFOR DELAY '00:00:09';
    SELECT *
    FROM store_table;
    EXEC logg_data 'store_table', '', 'READ_COMMITED_DATA';
COMMIT TRANSACTION