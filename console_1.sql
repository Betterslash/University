CREATE OR ALTER FUNCTION create_random_varchar(@rand_value FLOAT)
RETURNS varchar(255)
AS
   BEGIN
    -- get a random varchar ascii char 32 to 128
    DECLARE @videoname VARCHAR(160)
    DECLARE @length INT
    SELECT @videoname = ''
    SET @length = CAST(@rand_value * 60 as INT)
    WHILE @length <> 0
        BEGIN
        SELECT @videoname = @videoname + CHAR(CAST(@rand_value * 96 + 32 as INT))
        SET @length = @length - 1
        END
    RETURN @videoname
    END
GO
EXEC create_random_varchar;

CREATE OR ALTER FUNCTION create_random_int(@rand_value FLOAT)
RETURNS int
AS
    BEGIN
-- get a random integer between 3 and 7 (3 + 5 - 1)
    DECLARE @totalviews INT
    SELECT @totalviews = CAST(@rand_value * 1000 + 3 as INT)
    RETURN @totalviews
    END
GO
EXEC create_random_int;

CREATE OR ALTER FUNCTION create_random_date(@rand_value FLOAT)
RETURNS datetime
AS
    BEGIN
    -- get a random datetime +/- 365 days
    DECLARE @uploadtime DATETIME
    SET @uploadtime = GETDATE() + (1000 * 2 * @rand_value - 365)
    RETURN(@uploadtime)
    END
GO
EXEC create_random_date;


CREATE OR ALTER PROCEDURE insert_elements_producer
AS
    BEGIN
        DECLARE @int_rand FLOAT;
        DECLARE @varchar_rand FLOAT;
        DECLARE @val_int INT;
        DECLARE @val_chr VARCHAR(50);
        SET @int_rand = RAND();
        SET @varchar_rand = RAND();
        EXEC @val_int = create_random_int @int_rand;
        EXEC @val_chr = create_random_varchar @varchar_rand;
        WHILE @val_int IN (
            SELECT id
            FROM producer_table)
        BEGIN
            SET @int_rand = RAND();
            EXEC @val_int = create_random_int @int_rand;
        END
        INSERT INTO producer_table(id, nameOfProducer) VALUES(@val_int, @val_chr);
    END
GO
SELECT *
FROM producer_table;


CREATE OR ALTER PROCEDURE insert_10_elems_producer
AS
    DECLARE @count INT;
    SET @count = 0;
    WHILE @count < 10
    BEGIN
       EXEC insert_elements_producer;
       SET @count = @count + 1;
    END
GO
EXEC insert_10_elems_producer;
CREATE OR ALTER PROCEDURE insert_100_elems_producer
AS
    DECLARE @count INT;
    SET @count = 0;
    WHILE @count < 100
    BEGIN
       EXEC insert_elements_producer;
       SET @count = @count + 1;
    END
GO
EXEC insert_100_elems_producer;
CREATE OR ALTER PROCEDURE insert_1000_elems_producer
AS
    DECLARE @count INT;
    SET @count = 0;
    WHILE @count < 1000
    BEGIN
       EXEC insert_elements_producer;
       SET @count = @count + 1;
    END
GO
EXEC insert_1000_elems_producer;
SELECT *
FROM producer_table;



CREATE OR ALTER PROCEDURE insert_elements_game
AS
    BEGIN
        DECLARE @int_rand FLOAT;
        DECLARE @varchar_rand FLOAT;
        DECLARE @val_code INT;
        DECLARE @val_genreId INT;
        DECLARE @val_price INT;
        DECLARE @val_gameName VARCHAR(50);

        SET @int_rand = RAND();
        SET @varchar_rand = RAND();
        EXEC @val_code = create_random_int @int_rand;
        EXEC @val_price = create_random_int @int_rand;
        EXEC @val_gameName = create_random_varchar @varchar_rand;
        WHILE @val_code IN (
            SELECT code
            FROM games_table)
        BEGIN
            SET @int_rand = RAND();
            EXEC @val_code = create_random_int @int_rand;
        END
        WHILE @val_genreId IN (
            SELECT code
            FROM games_table)
        BEGIN
            SET @int_rand = RAND();
            EXEC @val_genreId = create_random_int @int_rand;
        END
        INSERT INTO games_table(code, gameName, genreID, price) VALUES(@val_code, @val_gameName, @val_genreId, @val_price);
    END
GO

CREATE OR ALTER PROCEDURE insert_10_elems_games
AS
    DECLARE @count INT;
    SET @count = 0;
    WHILE @count < 10
    BEGIN
       EXEC insert_elements_game;
       SET @count = @count + 1;
    END
GO
EXEC insert_10_elems_games;
CREATE OR ALTER PROCEDURE insert_100_elems_games
AS
    DECLARE @count INT;
    SET @count = 0;
    WHILE @count < 100
    BEGIN
       EXEC insert_elements_game;
       SET @count = @count + 1;
    END
GO
EXEC insert_100_elems_games;
CREATE OR ALTER PROCEDURE insert_1000_elems_games
AS
    DECLARE @count INT;
    SET @count = 0;
    WHILE @count < 1000
    BEGIN
       EXEC insert_elements_game;
       SET @count = @count + 1;
    END
GO
EXEC insert_1000_elems_games;
SELECT *
FROM games_table;

CREATE OR ALTER PROCEDURE insert_elems_producer_games
AS
    DECLARE @id INT;
    SET @id = (SELECT top 1 id
    FROM producer_table
    ORDER BY NEWID());
    DECLARE @code INT;
    SET @code = (SELECT top 1 code
    FROM games_table
    ORDER BY NEWID());
    WHILE @id  IN
        (SELECT id
            FROM producer_game_table) AND @code  IN
        (SELECT code
            FROM producer_game_table)
    BEGIN
        SET @id = (SELECT top 1 id
        FROM producer_table
        ORDER BY NEWID());
        SET @code = (SELECT top 1 code
        FROM games_table
        ORDER BY NEWID());
    END
    INSERT INTO producer_game_table(id, code) VALUES(@id, @code);
GO
SELECT * FROM producer_table;
SELECT * FROM games_table;


CREATE OR ALTER PROCEDURE insert_10_elems_pg_table
AS
    DECLARE @count INT;
    SET @count = 0;
    WHILE @count < 10
    BEGIN
       EXEC insert_elems_producer_games;
       SET @count = @count + 1;
    END
GO
CREATE OR ALTER PROCEDURE insert_100_elems_pg_table
AS
    DECLARE @count INT;
    SET @count = 0;
    WHILE @count < 100
    BEGIN
       EXEC insert_elems_producer_games;
       SET @count = @count + 1;
    END
GO
CREATE OR ALTER PROCEDURE insert_1000_elems_pg_table
AS
    DECLARE @count INT;
    SET @count = 0;
    WHILE @count < 1000
    BEGIN
       EXEC insert_elems_producer_games;
       SET @count = @count + 1;
    END
GO
EXEC clear_database;
EXEC insert_100_elems_games;
EXEC insert_100_elems_producer;
EXEC insert_100_elems_pg_table;


SELECT * FROM producer_game_table;
