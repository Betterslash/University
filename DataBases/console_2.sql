CREATE OR ALTER PROCEDURE delete_elems (@table_name varchar(50), @no_rows int)
AS
    IF @table_name = 'producer_game_table'
    BEGIN
        DELETE TOP (@no_rows)
        FROM producer_game_table;
    END
    IF @table_name = 'games_table'
    BEGIN
        DELETE TOP (@no_rows)
        FROM games_table;
    END
    IF @table_name = 'producer_table'
    BEGIN
        DELETE TOP (@no_rows)
        FROM producer_table;
    END
GO
DECLARE @table_name varchar(50);
SET @table_name = 'producer_game_table';
EXEC delete_elems @table_name, 1000;