use GADB;

CREATE OR ALTER PROCEDURE add_to_store_manager_2
    (@storeID INT,
     @storeName VARCHAR(255),
     @staffNumber INT,

    @CNP INT,
    @managerName VARCHAR(255),
    @phoneNumber INT,
    @annuallyIncome INT)
AS
    DECLARE @ErrorMessage NVARCHAR(4000) = ERROR_MESSAGE()
    DECLARE @ErrorSeverity INT = ERROR_SEVERITY()
    DECLARE @ErrorState INT = ERROR_STATE()
    RAISERROR(@ErrorMessage, @ErrorSeverity, @ErrorState);
    BEGIN TRY
        BEGIN TRY
            BEGIN TRANSACTION
                EXEC add_to_store_table @storeID, @storeName, @staffNumber;
            COMMIT TRAN
        END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK TRAN
        RAISERROR(@ErrorMessage, @ErrorSeverity, @ErrorState);
    END CATCH
        BEGIN TRY
            BEGIN TRAN
                EXEC add_to_manager_table @CNP, @managerName, @phoneNumber, @annuallyIncome;
            COMMIT TRAN
        END TRY
        BEGIN CATCH
            IF @@TRANCOUNT > 0
                ROLLBACK TRAN
            RAISERROR(@ErrorMessage, @ErrorSeverity, @ErrorState);
        END CATCH
        BEGIN TRANSACTION
            INSERT INTO store_manager_table VALUES (@CNP, @storeID);
        COMMIT TRAN
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK TRAN
    END CATCH
GO

EXEC add_to_store_manager_2 142, 'GameCon', 22, 8, 'Daniel', 19283, 1000;

EXEC add_to_store_manager 142, 'GameCon', 22, 11, 'Daniel', 19283, 1000;

SELECT * FROM store_table;

SELECT * FROM manager_table;