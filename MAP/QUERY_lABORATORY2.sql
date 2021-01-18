delete from producer_game_table;
delete from sales_table;
delete from staff_table;
delete from store_manager_table;
delete from manager_table;
delete from store_table;
delete from games_table;
delete from goodies_table;
delete from producer_table;
delete from country_table;
delete from genre_table;
delete from customers_table;
DBCC CHECKIDENT ('country_table', RESEED, 0);
DBCC CHECKIDENT ('goodies_table', RESEED, 0);
insert into country_table(
countryName,
populationNumber,
continent) values('Romania',17,'Europe');
insert into country_table(
countryName,
populationNumber,
continent) values('Hungary',23,'Europe');
insert into country_table(
countryName,
populationNumber,
continent) values('USA',224,'America');
insert into country_table(
countryName,
populationNumber,
continent) values('Mexic',171,'America');
insert into country_table(
countryName,
populationNumber,
continent) values('Uganda',11,'Africa');
select * from country_table;


insert into genre_table(id, genreName, allowenceAge) values(1,'Drama', 16);
insert into genre_table(id, genreName, allowenceAge) values(2,'Comedy', 12);
insert into genre_table(id, genreName, allowenceAge) values(3,'Action', 14);
insert into genre_table(id, genreName, allowenceAge) values(4,'Horror', 18);
insert into genre_table(id, genreName, allowenceAge) values(5,'MOBA', 14);
insert into genre_table(id, genreName, allowenceAge) values(6,'Survival', 16);
insert into genre_table(id, genreName, allowenceAge) values(7,'TCG', 10);
insert into genre_table(id, genreName, allowenceAge) values(8,'Arcade', 8);
insert into genre_table(id, genreName, allowenceAge) values(9,'RTS', 15);
select * from genre_table;



insert into producer_table(id,nameOfProducer) values(1,'Blizzard');
insert into producer_table(id,nameOfProducer) values(2,'UbiSoft');
insert into producer_table(id,nameOfProducer) values(3,'Gameloft');
insert into producer_table(id,nameOfProducer) values(4,'ActiVision');
insert into producer_table(id,nameOfProducer) values(5,'Riot');
insert into producer_table(id,nameOfProducer) values(6,'Bungie');
insert into producer_table(id,nameOfProducer) values(7,'Steam');
insert into producer_table(id,nameOfProducer) values(8,'EA');
select * from producer_table;


insert into goodies_table(nameOfGoodie, price, producerId) values('LichKingFigurine',56,1);
insert into goodies_table(nameOfGoodie, price, producerId) values('IllidanFigurine',56,1);
insert into goodies_table(nameOfGoodie, price, producerId) values('EzioWallpaper',10,2);
insert into goodies_table(nameOfGoodie, price, producerId) values('VWCarFig',86,3);
insert into goodies_table(nameOfGoodie, price, producerId) values('LiterallyNoth',1,4);
select * from goodies_table;

insert into games_table(code,gameName,genreID,price) values(1,'WoW',3,79);
insert into games_table(code,gameName,genreID,price) values(2,'HearthStone',3,49);
insert into games_table(code,gameName,genreID,price) values(3,'StarCraft',4,39);
insert into games_table(code,gameName,genreID,price) values(4,'LoL',5,79);
insert into games_table(code,gameName,genreID,price) values(5,'Assasins Creed',3,29);
insert into games_table(code,gameName,genreID,price) values(6,'Diablo',3,39);
insert into games_table(code,gameName,genreID,price) values(7,'Fifa2020',8,129);
insert into games_table(code,gameName,genreID,price) values(8,'LoR',5,0);
insert into games_table(code,gameName,genreID,price) values(9,'Starcraft',2,29);
insert into games_table(code,gameName,genreID,price) values(10,'HALO',1,69);
insert into games_table(code,gameName,genreID,price) values(11,'DnD',6,49);
insert into games_table(code,gameName,genreID,price) values(12,'RocketLeague',7,0);
select * from games_table;

insert into customers_table(CNP, username, email, phoneNumber) values('5000413123841','Betterslash','dory7141@gmail.com',0756511879);
insert into customers_table(CNP, username, email, phoneNumber) values('5000513155841','Tractor','em1@yahoo.com',0756525279);
insert into customers_table(CNP, username, email, phoneNumber) values('5000214125741','Celentan','l_c_c@gmail.com',0756533373);
insert into customers_table(CNP, username, email, phoneNumber) values('5001113124811','Wilderness','mihaita@gmail.com',0756871879);
insert into customers_table(CNP, username, email, phoneNumber) values('5001213125821','Kenzor','delaberceni@scs.com',0752111879);
insert into customers_table(CNP, username, email, phoneNumber) values('5000423123841','DonSergione','delabni@scs.com',0735113879);
insert into customers_table(CNP, username, email, phoneNumber) values('5000433124742','TPericulosu','deceni@cs.com',0751821879);
insert into customers_table(CNP, username, email, phoneNumber) values('5000443124742','Fantoma10','dniad@scs.com',0751331879);
insert into customers_table(CNP, username, email, phoneNumber) values('5000463124742','EUBobo','dasdani@icloud.com',0754511879);
insert into customers_table(CNP, username, email, phoneNumber) values('5000473124742','MTS','rfghni@icloud.com',0753411879);
insert into customers_table(CNP, username, email, phoneNumber) values('5000483124742','ElDubi','denida@gmail.com',0753211879);
insert into customers_table(CNP, username, email, phoneNumber) values('5000493124742','SuntMatei','shany@scs.com',0751211879);
insert into customers_table(CNP, username, email, phoneNumber) values('5000423124742','Guby','j1l4U@hefe.com',0751246879);
select * from customers_table;

insert into staff_table(CNP, roleOfStaff, salary, startDate) values(591315381,'Advisor',3700,'2019-11-28');
insert into staff_table(CNP, roleOfStaff, salary, startDate) values(611316281,'Customer Care',2700,'2009-11-28');
insert into staff_table(CNP, roleOfStaff, salary, startDate) values(721315311,'Assistent',1900,'2015-11-26');
insert into staff_table(CNP, roleOfStaff, salary, startDate) values(313453111,'Vendor',2500,'2019-12-08');
insert into staff_table(CNP, roleOfStaff, salary, startDate) values(410153801,'Vendor',2700,'2009-10-18');
insert into staff_table(CNP, roleOfStaff, salary, startDate) values(519153891,'Security',1500,'2012-09-03');
insert into staff_table(CNP, roleOfStaff, salary, startDate) values(318153881,'Customer Care',2700,'2014-08-11');
insert into staff_table(CNP, roleOfStaff, salary, startDate) values(117133871,'Asvisor',2300,'2013-03-22');
insert into staff_table(CNP, roleOfStaff, salary, startDate) values(216123861,'Assistent',2800,'2011-01-21');
insert into staff_table(CNP, roleOfStaff, salary, startDate) values(315113851,'Service',2540,'2012-02-22');
insert into staff_table(CNP, roleOfStaff, salary, startDate) values(414103841,'Surveillance',2200,'2017-06-21');
insert into staff_table(CNP, roleOfStaff, salary, startDate) values(513153831,'Surveillance',3500,'2013-04-16');
insert into staff_table(CNP, roleOfStaff, salary, startDate) values(712153821,'Tester',4500,'2012-11-18');
insert into staff_table(CNP, roleOfStaff, salary, startDate) values(411153811,'Vendor',1500,'2014-02-21');

insert into store_table(adress, storeName, staffnumber) values(1, 'Gamcop', 2);
insert into store_table(adress, storeName, staffnumber) values(2, 'Join', 2);
insert into store_table(adress, storeName, staffnumber) values(3, 'Gedit', 1);
insert into store_table(adress, storeName, staffnumber) values(4, 'NinGasm', 3);
insert into store_table(adress, storeName, staffnumber) values(5, 'GamTop', 5);

insert into manager_table(CNP, nameOfManager, phoneNumber, anuallyIncome) values(011213841, 'Ricardo',0754526476, 190000);
insert into manager_table(CNP, nameOfManager, phoneNumber, anuallyIncome) values(021213841, 'Luigio',0751526983, 210000);
insert into manager_table(CNP, nameOfManager, phoneNumber, anuallyIncome) values(031213841, 'Giovanni',0725726476, 1000000);
insert into manager_table(CNP, nameOfManager, phoneNumber, anuallyIncome) values(041213841, 'Ion',0751534376, 19030);
insert into manager_table(CNP, nameOfManager, phoneNumber, anuallyIncome) values(051213841, 'Dorel',0755264176, 10000);
select * from manager_table;

insert into store_manager_table(CNP,adress) values(011213841,1);
insert into store_manager_table(CNP,adress) values(021213841,2);
insert into store_manager_table(CNP,adress) values(031213841,3);
insert into store_manager_table(CNP,adress) values(041213841,4);
insert into store_manager_table(CNP,adress) values(051213841,5);
select * from store_manager_table;

UPDATE staff_table
SET storeAdress = 1
WHERE CNP = 591315381;
UPDATE staff_table
SET storeAdress = 2
WHERE CNP = 611316281;
UPDATE staff_table
SET storeAdress = 3
WHERE CNP = 411153811;
UPDATE staff_table
SET storeAdress = 4
WHERE CNP = 712153821;
UPDATE staff_table
SET storeAdress = 3
WHERE CNP = 318153881;
UPDATE staff_table
SET storeAdress = 1
WHERE CNP = 216123861;
UPDATE staff_table
SET storeAdress = 2
WHERE CNP = 519153891;
UPDATE staff_table
SET storeAdress = 4
WHERE CNP = 721315311;
UPDATE staff_table
SET storeAdress = 2
WHERE CNP = 414103841;
UPDATE staff_table
SET storeAdress = 2
WHERE CNP = 410153801;
select * from staff_table;




insert into sales_table(productCode, customerCNP, dateOfSale, incomeAmount, storeAdress) values(1, '5000214125741','2020-03-01',100,1);
insert into sales_table(productCode, customerCNP, dateOfSale, incomeAmount, storeAdress) values(2, '5000513155841','2020-03-02',130,1);
insert into sales_table(productCode, customerCNP, dateOfSale, incomeAmount, storeAdress) values(1, '5001213125821','2020-04-01',900,2);
insert into sales_table(productCode, customerCNP, dateOfSale, incomeAmount, storeAdress) values(3, '5000413123841','2013-06-11',100,3);
insert into sales_table(productCode, customerCNP, dateOfSale, incomeAmount, storeAdress) values(4, '5000423124742','2013-06-11',113,4);
insert into sales_table(productCode, customerCNP, dateOfSale, incomeAmount, storeAdress) values(6, '5000433124742','2013-10-14',310,1);
insert into sales_table(productCode, customerCNP, dateOfSale, incomeAmount, storeAdress) values(2, '5000443124742','2013-11-13',410,4);
insert into sales_table(productCode, customerCNP, dateOfSale, incomeAmount, storeAdress) values(7, '5000443124742','2013-09-12',120,5);
insert into sales_table(productCode, customerCNP, dateOfSale, incomeAmount, storeAdress) values(8, '5000483124742','2014-06-21',160,1);
insert into sales_table(productCode, customerCNP, dateOfSale, incomeAmount, storeAdress) values(5, '5000473124742','2012-08-11',210,3);
insert into sales_table(productCode, customerCNP, dateOfSale, incomeAmount, storeAdress) values(4, '5000473124742','2011-07-24',10,2);
select * from sales_table;

INSERT INTO producer_game_table(id, code) VALUES (1,2);
INSERT INTO producer_game_table(id, code) VALUES (3,4);
INSERT INTO producer_game_table(id, code) VALUES (1,3);
INSERT INTO producer_game_table(id, code) VALUES (1,5);
INSERT INTO producer_game_table(id, code) VALUES (2,5);
INSERT INTO producer_game_table(id, code) VALUES (3,2);
INSERT INTO producer_game_table(id, code)
VALUES (5, 3);
/*The producer got out expansions for games and they need to update the names of games*/
/*------------------------------------------------------------------------------------*/
UPDATE staff_table
SET storeAdress = 1
WHERE CNP = 913153841;
UPDATE staff_table
SET storeAdress = 2
WHERE CNP = 1113162841;
UPDATE staff_table
SET storeAdress = 3
WHERE CNP = 1113153641;
UPDATE staff_table
SET storeAdress = 4
WHERE CNP = 313153841;
select * from staff_table;
update games_table
SET gameName = 'WoW Shadowlans'
Where code = 1;

/*The producer wants a sale for some goodies*/
update goodies_table
SET price = price - 10
Where price > 50 AND producerId = 1;
select * from goodies_table;

/*The producer wants a price increase for everything below a sum to get a bit more profit*/
update goodies_table
SET price = price + 10
Where price <= 20 AND producerId = 2 OR producerId = 4 And producerId <> 1;

/*Producer decied that some goodies would not bring profit anyway so they stop selling them*/
Delete from goodies_table
where price between 1 and 30;
select * from goodies_table;

/*ActiVision Merges with Blizzard*/
delete from producer_table
where nameOfProducer is not null  and nameOfProducer like 'Acti%';
update producer_table
set nameOfProducer = 'ActiBlizz'
where nameOfProducer in (select nameOfProducer
from producer_table
where nameOfProducer like 'Bliz%')
select * from producer_table;

/*We want to see every game from Blizzard or other really relevant producer with the name of some goodies made for them*/
select gameName from games_table
UNION
select nameOfGoodie from goodies_table,producer_table
where goodies_table.price > 80 OR producer_table.id = goodies_table.producerId
order by gameName;

/*Some people want to have a salary check with others between stores 2 and 1*/
SELECT COUNT(*), AVG([staff_table].salary) FROM staff_table
WHERE [staff_table].salary > 1500 AND [staff_table].storeAdress = 2
UNION ALL
SELECT COUNT(*), AVG([staff_table].salary) FROM staff_table
WHERE [staff_table].salary > 1500 AND [staff_table].storeAdress = 1;

/*We wanna see which games that are still available were sold over the time and store sold best*/
SELECT [games_table].code FROM games_table
INTERSECT
SELECT [sales_table].productCode FROM sales_table;

/*We wanna see who did bought at least one product */
SELECT [customers_table].CNP, [customers_table].username FROM [customers_table]
WHERE [customers_table].CNP IN(
    SELECT [sales_table].customerCNP FROM sales_table
    );

/*We wanna see which games that are still available were not sold over the time*/
SELECT [games_table].code FROM games_table
EXCEPT
SELECT [sales_table].productCode FROM sales_table;

/*We wanna see who did not buy at least one product since they are subscribed*/
SELECT [customers_table].CNP, [customers_table].username FROM [customers_table]
WHERE [customers_table].CNP NOT IN(
    SELECT [sales_table].customerCNP FROM sales_table
    );

/*Let's see which genre sold the best */
SELECT COUNT(*) as number, E.genreName
FROM
(SELECT j.genreName
FROM [genre_table] as j
INNER JOIN [games_table] as g ON g.genreID = j.id
INNER JOIN [sales_table] as s ON s.productCode = g.code) as E
GROUP BY E.genreName;

/*Let's see how many producers have no goodies associated with their products*/
SELECT COUNT(*)
FROM (SELECT g.nameOfGoodie
        FROM [goodies_table] as g RIGHT JOIN [producer_table] as p
        ON g.producerId = p.id) as E
WHERE E.nameOfGoodie IS NULL;

/*Here we ll make sure that we have some clients that bought a consitent amount and did not crack the games*/
SELECT c.username, st.incomeAmount
FROM customers_table AS c LEFT JOIN sales_table st on c.CNP = st.customerCNP
WHERE st.saleID IS NOT NULL AND st.incomeAmount > 100
ORDER BY st.incomeAmount;

/*The managers wanna see which country gave them the biggest amount of money*/
SELECT nameOfManager, incomeAmount, countryName
FROM [manager_table] AS mn
FULL JOIN store_manager_table smt on mn.CNP = smt.CNP
FULL JOIN store_table st on smt.adress = st.adress
FULL JOIN sales_table s on st.adress = s.storeAdress
FULL JOIN games_table gt on gt.code = s.productCode
FULL JOIN producer_game_table pgt on gt.code = pgt.code
FULL JOIN producer_table pt on pt.id = pgt.ID
FULL JOIN country_table ct on ct.ID = pgt.ID
WHERE s.incomeAmount > 100 AND countryName IS NOT NULL
GROUP BY incomeAmount, nameOfManager, countryName;

