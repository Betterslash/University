use test_sem_db;
DROP TABLE Likes
DROP TABLE Pages
DROP TABLE Categories
DROP TABLE Comments
DROP TABLE Posts
DROP TABLE Users


CREATE TABLE Users
(
	UserId INT PRIMARY KEY,
	UserName VARCHAR(100),
	City VARCHAR(50),
	DOB DATE
)

-- many to one with user
CREATE TABLE Posts
(
	PostId INT PRIMARY KEY,
	PostDate DATE,
	PostText VARCHAR(100),
	PostShares INT,
	UserId INT REFERENCES Users(UserId)
)

CREATE TABLE Comments
(
	CommId INT PRIMARY KEY,
	DateOfComm DATE,
	CommText VARCHAR(100),
	TopComm BIT,
	PostId INT REFERENCES Posts(PostId)
)

-- one to many with page
CREATE TABLE Categories
(
	CategoryId INT PRIMARY KEY,
	CategoryName VARCHAR(100),
	CategoryDescription VARCHAR(200)
)

CREATE TABLE Pages
(
	PageId INT PRIMARY KEY,
	PageName VARCHAR(100) ,
	CategoryId INT REFERENCES Categories(CategoryId)
)

-- many to many user and page
CREATE TABLE Likes
(
	UserId INT REFERENCES Users(UserId),
	PageId INT REFERENCES Pages(PageId),
	LikeDate DATE
)