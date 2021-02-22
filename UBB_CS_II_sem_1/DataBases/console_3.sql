DELETE FROM TestViews;
DELETE FROM TestTables;
DELETE FROM Views;
DELETE FROM TestRunTables;
DELETE FROM TestRunViews;
DELETE FROM TestRuns;
DELETE FROM Tests;
DELETE FROM Tables;

DBCC CHECKIDENT(Tables,RESEED, 0);
DBCC CHECKIDENT(Tests,RESEED, 0);
DBCC CHECKIDENT(Views,RESEED, 0);

INSERT INTO Tables(Name) values('producer_table');
INSERT INTO Tables(Name) values('games_table');
INSERT INTO Tables(Name) values('producer_game_table');
SELECT * FROM Tables;

INSERT INTO Views(Name) VALUES ('View_1');
INSERT INTO Views(Name) VALUES ('View_2');
INSERT INTO Views(Name) VALUES ('View_3');
SELECT * FROM Views;

INSERT INTO Tests(Name) VALUES ('delete_table');
INSERT INTO Tests(Name) VALUES ('insert_table');
INSERT INTO Tests(Name) VALUES ('select_view');
SELECT * FROM Tests;

INSERT INTO TestViews(TestID, ViewID) VALUES (3, 1);
INSERT INTO TestViews(TestID, ViewID) VALUES (3, 2);
INSERT INTO TestViews(TestID, ViewID) VALUES (3, 3);
SELECT * FROM TestViews;


DECLARE @all_start DATETIME;
SET @all_start = GETDATE();

DECLARE @delete_pg_start DATETIME;
SET @delete_pg_start = GETDATE();
EXEC delete_elems 'producer_game_table', 100;
DECLARE @delete_pg_end DATETIME;
SET @delete_pg_end = GETDATE();

DECLARE @delete_g_start DATETIME;
SET @delete_g_start = GETDATE();
EXEC delete_elems 'games_table', 100;
DECLARE @delete_g_end DATETIME;
SET @delete_g_end = GETDATE();


DECLARE @delete_p_start DATETIME;
SET @delete_p_start = GETDATE();
EXEC delete_elems 'producer_table', 100;
DECLARE @delete_p_end DATETIME;
SET @delete_p_end = GETDATE();


DECLARE @insert_p_start DATETIME;
SET @insert_p_start = GETDATE();
EXEC insert_100_elems_producer;
DECLARE @insert_p_end DATETIME;
SET @insert_p_end = GETDATE();

DECLARE @insert_g_start DATETIME;
SET @insert_g_start = GETDATE();
EXEC insert_100_elems_games;
DECLARE @insert_g_end DATETIME;
SET @insert_g_end = GETDATE();

DECLARE @insert_pg_start DATETIME;
SET @insert_pg_start = GETDATE();
EXEC insert_100_elems_pg_table;
DECLARE @insert_pg_end DATETIME;
SET @insert_pg_end = GETDATE();

DECLARE @view_1_start DATETIME;
SET @view_1_start = GETDATE();
EXEC select_view 1;
DECLARE @view_1_end DATETIME;
SET @view_1_end = GETDATE();

DECLARE @view_2_start DATETIME;
SET @view_2_start = GETDATE();
EXEC select_view 2;
DECLARE @view_2_end DATETIME;
SET @view_2_end = GETDATE();

DECLARE @view_3_start DATETIME;
SET @view_3_start = GETDATE();
EXEC select_view 3;
DECLARE @view_3_end DATETIME;
SET @view_3_end = GETDATE();

DECLARE @all_stop DATETIME
SET @all_stop = GETDATE();

declare @description varchar(100)
set @description = 'TestRun' + convert(varchar(7), (select max(TestRunID) from TestRuns)) + 'delete, insert 100 rows, select all views'

insert into TestRuns(Description, StartAt, EndAt)
values(@description, @all_start, @all_stop);

declare @lastTestRunID int;
set @lastTestRunID = (select max(TestRunID) from TestRuns);

insert into TestRunTables
values(@lastTestRunID, 1, @delete_pg_start, @insert_pg_end);

insert into TestRunTables
values(@lastTestRunID, 2, @delete_g_start, @insert_g_end);

insert into TestRunTables
values(@lastTestRunID, 3, @delete_p_start, @insert_p_end);

insert into TestRunViews
values(@lastTestRunID, 1, @view_1_start, @view_1_end);

insert into TestRunViews
values(@lastTestRunID, 2, @view_2_start, @view_2_end);

insert into TestRunViews
values(@lastTestRunID,3, @view_3_start, @view_3_end);

SELECT *
FROM TestRunTables;