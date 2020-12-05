CREATE OR ALTER VIEW View_1
AS
    SELECT gameName, price
    FROM games_table;
GO
CREATE OR ALTER VIEW View_2
AS
    SELECT gameName, pgt.ID
    FROM games_table GT INNER JOIN producer_game_table pgt on GT.code = pgt.code;
GO
CREATE OR ALTER VIEW View_3
AS
    SELECT gameName, gt.price
    FROM games_table GT INNER JOIN producer_game_table pgt on GT.code = pgt.code
    GROUP BY gameName, price, pgt.ID;
GO

CREATE OR ALTER PROCEDURE select_view (@view_id int)
AS
    IF @view_id = 1
    BEGIN
        SELECT *
        FROM View_1;
    END
    IF @view_id = 2
    BEGIN
        SELECT *
        FROM View_2;
    END
    IF @view_id = 3
    BEGIN
        SELECT *
        FROM View_3;
    END
GO
EXEC select_view 3;

