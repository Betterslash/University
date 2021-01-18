CREATE OR ALTER PROCEDURE main (@newVersion int)
AS
BEGIN
	SET NOCOUNT ON;
	DECLARE @nextStep varchar(30)
	DECLARE @currentVersion INT
	SET @currentVersion = (SELECT current_version FROM version_table)
	SET @newVersion = cast(@newVersion as INT)
	while @currentVersion < @newVersion
	begin
		SET @currentVersion = @currentVersion + 1
		SET @nextStep = 'up_version_' + convert(varchar(3), @currentVersion)
		execute @nextStep
	end

	while @currentVersion > @newVersion
	begin
		SET @currentVersion = @currentVersion - 1
		SET @nextStep = 'down_version_' + convert(varchar(3), @currentVersion)
		execute @nextStep
	end

	truncate table version_table
	insert into version_table values(@newVersion)
END;
    EXEC main 7;
    SELECT * FROM version_table;