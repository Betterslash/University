USE GADB;

SET TRANSACTION ISOLATION LEVEL READ COMMITTED;

UPDATE manager_table
    SET nameOfManager = 'Nume'
    WHERE phoneNumber = 2121;
BEGIN TRANSACTION
    WAITFOR DELAY '00:00:05'
    UPDATE manager_table
    SET nameOfManager = 'Bursuc'
    WHERE phoneNumber = 2121;
COMMIT TRANSACTION
