/*a. modify the type of a column;*/
CREATE OR ALTER PROCEDURE up_version_1
AS
    ALTER TABLE goodies_table
    ALTER COLUMN nameOfGoodie varchar(255);
    PRINT ('Set to version 1!');
GO;
EXEC up_version_1;
CREATE OR ALTER PROCEDURE down_version_0
AS
    SET NOCOUNT ON;
    ALTER TABLE goodies_table
    ALTER COLUMN nameOfGoodie varchar(50);
    PRINT('Back to version 0')
GO;
EXEC down_version_0;

CREATE OR ALTER PROCEDURE display
AS
    SET NOCOUNT ON;
    SELECT * FROM goodies_table;
GO;
EXEC display;

/*b. add / remove a column;*/
CREATE OR ALTER PROCEDURE down_version_1
AS
    ALTER TABLE country_table
    DROP COLUMN populationNumber;
    PRINT ('Back to version 1!');
GO;
EXEC down_version_1;

CREATE OR ALTER PROCEDURE up_version_2
AS
    ALTER TABLE country_table
    ADD populationNumber int;
    PRINT ('Set to version 2!');
GO;
EXEC up_version_2;

/*c. add / remove a DEFAULT constraint;*/
CREATE OR ALTER PROCEDURE down_version_2
AS
    alter table staff_table drop constraint salary;
    PRINT ('Back to version 2!');
GO;
EXEC down_version_2;

CREATE OR ALTER PROCEDURE up_version_3
AS
      ALTER TABLE staff_table
      ADD CONSTRAINT salary DEFAULT 1000 FOR salary;
      PRINT ('Set to version 3!');
GO;
EXEC up_version_3;

/*d. add / remove a primary key;*/
CREATE OR ALTER PROCEDURE up_version_4
AS
    ALTER TABLE sales_table
    DROP CONSTRAINT PK_Sale;
    PRINT ('Set to version 4!');
GO;
EXEC up_version_4;

CREATE OR ALTER PROCEDURE down_version_3
AS
    ALTER TABLE sales_table
    ADD CONSTRAINT PK_Sale PRIMARY KEY (saleID,productCode);
    PRINT ('Back to version 3!');
GO;
EXEC down_version_3;
/*e. add/remove candidate key*/
CREATE OR ALTER PROCEDURE down_version_4
AS
    ALTER TABLE producer_table
    DROP COLUMN phone_number;
    PRINT ('Back to version 4!');
GO;
EXEC down_version_4;

CREATE OR ALTER PROCEDURE up_version_5
AS
    ALTER TABLE producer_table
    ADD phone_number int;
    PRINT ('Set to version 5!');
GO;
EXEC up_version_5;

/*f. add / remove a foreign key;*/
CREATE OR ALTER PROCEDURE down_version_5
AS
    SET NOCOUNT ON;
    alter table sales_table
    drop constraint fk_customer_CNP;
    PRINT ('Back to version 5');
GO;
EXEC down_version_5;
CREATE OR ALTER PROCEDURE up_version_6
AS
    alter table sales_table
    add constraint fk_customer_CNP FOREIGN KEY (customerCNP) references customers_table(CNP);
    PRINT ('Set to version 5!');
GO;
EXEC up_version_6;

/*g. create / drop a table.*/
CREATE OR ALTER PROCEDURE up_version_7
AS
    create table continents_table(
        cont_id int primary key ,
        cont_countries int NOT NULL
    );
    PRINT ('Set to version 7!');
GO;
EXEC up_version_7;
CREATE OR ALTER PROCEDURE down_version_6
AS
    drop table continents_table;
    PRINT ('Back to version 6!');
GO;
EXEC down_version_6;


