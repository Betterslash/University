USE GADB;

BEGIN TRANSACTION

    UPDATE manager_table
    SET anuallyIncome = anuallyIncome - 100
    WHERE nameOfManager = 'Doni';

    UPDATE manager_table
    SET anuallyIncome = anuallyIncome + 100
    WHERE nameOfManager = 'Marian';

COMMIT TRANSACTION

