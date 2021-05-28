DROP TABLE IF EXISTS blogs;

CREATE TABLE blogs (
                              id INT AUTO_INCREMENT  PRIMARY KEY,
                              title VARCHAR(250) NOT NULL,
                              creation_date DATE NOT NULL,
                              last_updated DATE NOT NULL,
                              content VARCHAR(10000) DEFAULT NULL
);

INSERT INTO blogs (title, creation_date, last_updated, content) VALUES
('Title1', '2020-02-01', '2020-02-01', 'content1'),
('Title2', '2020-02-01', '2020-02-01', 'content2'),
('Title3', '2020-02-01', '2020-02-01', 'content3');