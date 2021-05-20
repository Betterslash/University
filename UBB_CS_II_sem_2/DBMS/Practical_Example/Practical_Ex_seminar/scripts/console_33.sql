USE practical_ex_seminar;

DROP TABLE comments;
DROP TABLE posts;
DROP TABLE likes;
DROP TABLE pages;
DROP TABLE users;
DROP TABLE categories;

CREATE TABLE categories(
    id int PRIMARY KEY IDENTITY ,
    description varchar(255),
);

CREATE TABLE users(
    id int PRIMARY KEY IDENTITY ,
    current_city varchar(255),
    date_of_birth date
);

CREATE TABLE pages(
    id int PRIMARY KEY IDENTITY ,
    name varchar(255),
    category int FOREIGN KEY REFERENCES categories(id) ON DELETE CASCADE
);

CREATE TABLE likes(
    user_id int FOREIGN KEY REFERENCES users(id),
    page_id int FOREIGN KEY REFERENCES pages(id),
    like_date DATE,
    CONSTRAINT users_pages_PK PRIMARY KEY (user_id, page_id)
);

CREATE TABLE posts(
    id int PRIMARY KEY IDENTITY,
    date DATE,
    text varchar(255),
    number_of_shares int,
    user_id int FOREIGN KEY REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE comments(
    id int PRIMARY KEY IDENTITY ,
    text varchar(255),
    date DATE,
    flag int,
    post_id int FOREIGN KEY REFERENCES posts(id) ON DELETE CASCADE
);


