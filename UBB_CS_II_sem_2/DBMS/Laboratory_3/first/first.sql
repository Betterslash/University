USE GADB;
CREATE OR ALTER   PROCEDURE add_to_manager_table(@CNP INT, @managerName VARCHAR(255), @phoneNumber INT, @annuallyIncome INT)
AS
    INSERT INTO manager_table VALUES (@CNP, @managerName, @phoneNumber, @annuallyIncome);
GO
CREATE OR ALTER   PROCEDURE add_to_store_table(@storeID INT, @storeName VARCHAR(255), @staffNumber INT)
AS
    INSERT INTO store_table
    VALUES(@storeID, @storeName, @staffNumber);
GO
CREATE OR ALTER PROCEDURE add_to_store_manager
    (@storeID INT, @storeName VARCHAR(255), @staffNumber INT,
    @CNP INT, @managerName VARCHAR(255), @phoneNumber INT, @annuallyIncome INT)
AS
    BEGIN TRY
        BEGIN TRANSACTION
            EXEC add_to_store_table @storeID, @storeName, @staffNumber;
            EXEC add_to_manager_table @CNP, @managerName, @phoneNumber, @annuallyIncome;
            INSERT INTO store_manager_table VALUES (@CNP, @storeID);
        COMMIT TRAN
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK TRAN
        DECLARE @ErrorMessage NVARCHAR(4000) = ERROR_MESSAGE()
        DECLARE @ErrorSeverity INT = ERROR_SEVERITY()
        DECLARE @ErrorState INT = ERROR_STATE()
        RAISERROR(@ErrorMessage, @ErrorSeverity, @ErrorState);
    END CATCH

GO
EXEC add_to_store_manager 142, 'GameCon', 22, 3, 'Daniel', 19283, 1000;

EXEC add_to_store_manager 144, 'GameCon', 22, 3, 'Daniel', 19283, 1000;

EXEC add_to_store_manager 142, 'GameCon', 22, 8, 'Daniel', 19283, 1000;

SELECT *
FROM store_table;
SELECT *
FROM manager_table;
SELECT *
FROM store_manager_table;