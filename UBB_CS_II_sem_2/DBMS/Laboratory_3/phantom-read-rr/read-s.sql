USE GADB;

SET TRANSACTION ISOLATION LEVEL serializable ;

BEGIN TRANSACTION
    SELECT *
    FROM manager_table
    WHERE anuallyIncome > 2500

    WAITFOR DELAY '00:00:05'

    SELECT *
    FROM manager_table
    WHERE anuallyIncome > 2500
COMMIT TRANSACTION ;