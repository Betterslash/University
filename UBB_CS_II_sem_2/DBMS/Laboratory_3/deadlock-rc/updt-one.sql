USE GADB;

BEGIN TRANSACTION

    UPDATE manager_table
    SET anuallyIncome = anuallyIncome + 100
    WHERE nameOfManager = 'Marian';

    UPDATE manager_table
    SET anuallyIncome = anuallyIncome - 100
    WHERE nameOfManager = 'Doni';

COMMIT TRANSACTION

