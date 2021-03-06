ALTER DATABASE GADB SET ALLOW_SNAPSHOT_ISOLATION ON;

USE GADB;
SET TRANSACTION ISOLATION LEVEL SNAPSHOT;

BEGIN TRANSACTION
    UPDATE manager_table
    SET nameOfManager = 'Ion'
    WHERE  anuallyIncome = 2378;
    WAITFOR DELAY '00:00:05'
COMMIT TRANSACTION