USE GADB;

SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

DELETE FROM manager_table
WHERE CNP = 4171;

BEGIN TRANSACTION
    /* Set a timer for us to be able to see the read before update */
    WAITFOR DELAY '00:00:04'

    EXEC add_to_manager_table 4171, 'Marian', 756511879, 2600;

COMMIT TRANSACTION