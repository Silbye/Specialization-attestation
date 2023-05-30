/* 7. В подключенном MySQL репозитории создать базу данных “Друзья
человека” */
CREATE DATABASE IF NOT EXISTS Mans_friends;


/* 8. Создать таблицы с иерархией из диаграммы в БД */
USE Mans_friends;
CREATE TABLE IF NOT EXISTS animal_classes
(
	Id INT AUTO_INCREMENT PRIMARY KEY, 
	Class_name VARCHAR(20)
);

INSERT INTO animal_classes (Class_name)
VALUES ('Pack'),
('Domestic');

CREATE TABLE IF NOT EXISTS pack_animals
(
	  Id INT AUTO_INCREMENT PRIMARY KEY,
    Genus_name VARCHAR (20),
    Class_id INT,
    FOREIGN KEY (Class_id) REFERENCES animal_classes (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO pack_animals (Genus_name, Class_id)
VALUES ('Horses', 1),
('Donkeys', 1),  
('Camels', 1); 

CREATE TABLE IF NOT EXISTS domestic_animals
(
	  Id INT AUTO_INCREMENT PRIMARY KEY,
    Genus_name VARCHAR (20),
    Class_id INT,
    FOREIGN KEY (Class_id) REFERENCES animal_classes (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO domestic_animals (Genus_name, Class_id)
VALUES ('Cats', 2),
('Dogs', 2),  
('Hamsters', 2); 



/* 9. Заполнить низкоуровневые таблицы именами(животных), командами
которые они выполняют и датами рождения */
CREATE TABLE IF NOT EXISTS cats 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(50),
    Genus_id int,
    Foreign KEY (Genus_id) REFERENCES domestic_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO cats (Name, Birthday, Commands, Genus_id)
VALUES ('Pupa', '2011-01-01', 'ps-ps-ps', 1),
('Lupa', '2016-01-01', 'ps-ps-ps', 1);

CREATE TABLE IF NOT EXISTS dogs 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(50),
    Genus_id int,
    Foreign KEY (Genus_id) REFERENCES domestic_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO dogs (Name, Birthday, Commands, Genus_id)
VALUES ('Duke', '2020-01-01', 'to me, lay, shake, voice', 2),
('Boss', '2021-06-12', 'sit, lay, shake', 2);

CREATE TABLE IF NOT EXISTS hamsters 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(50),
    Genus_id int,
    Foreign KEY (Genus_id) REFERENCES domestic_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO hamsters (Name, Birthday, Commands, Genus_id)
VALUES ('Little', '2020-10-12', '', 3),
('Brownie', '2022-05-10', NULL, 3);

CREATE TABLE IF NOT EXISTS horses 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(50),
    Genus_id int,
    Foreign KEY (Genus_id) REFERENCES pack_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO horses (Name, Birthday, Commands, Genus_id)
VALUES ('Thunder', '2020-01-12', 'run, walk', 1),
('Sunset', '2017-03-12', "run, walk, hop", 1);

CREATE TABLE IF NOT EXISTS donkeys 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(50),
    Genus_id int,
    Foreign KEY (Genus_id) REFERENCES pack_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO donkeys (Name, Birthday, Commands, Genus_id)
VALUES ('First', '2019-04-10', NULL, 2),
('Second', '2020-03-12', "", 2);

CREATE TABLE IF NOT EXISTS camels 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(50),
    Genus_id int,
    Foreign KEY (Genus_id) REFERENCES pack_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO camels (Name, Birthday, Commands, Genus_id)
VALUES ('Humped', '2022-04-10', 'return', 3),
('Siphon', '2015-07-12', "turn around", 3);


/* 10. Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой
питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу. */
SET SQL_SAFE_UPDATES = 0;
DELETE FROM camels;

SELECT Name, Birthday, Commands FROM horses
UNION SELECT  Name, Birthday, Commands FROM donkeys;


/* 11.Создать новую таблицу “молодые животные” в которую попадут все
животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью
до месяца подсчитать возраст животных в новой таблице */
CREATE TEMPORARY TABLE IF NOT EXISTS animals AS 
SELECT *, 'Horses' as genus FROM horses
UNION SELECT *, 'Donkeys' AS genus FROM donkeys
UNION SELECT *, 'Dogs' AS genus FROM dogs
UNION SELECT *, 'Cats' AS genus FROM cats
UNION SELECT *, 'Hamsters' AS genus FROM hamsters;

CREATE TABLE IF NOT EXISTS young_animals AS
SELECT Name, Birthday, Commands, genus, TIMESTAMPDIFF(MONTH, Birthday, CURDATE()) AS Age_in_month
FROM animals WHERE Birthday BETWEEN ADDDATE(curdate(), INTERVAL -3 YEAR) AND ADDDATE(CURDATE(), INTERVAL -1 YEAR);
 
SELECT * FROM young_animals;


/* 12. Объединить все таблицы в одну, при этом сохраняя поля, указывающие на
прошлую принадлежность к старым таблицам. */
SELECT h.Name, h.Birthday, h.Commands, pa.Genus_name, ya.Age_in_month 
FROM horses h
LEFT JOIN young_animals ya ON ya.Name = h.Name
LEFT JOIN pack_animals pa ON pa.Id = h.Genus_id
UNION 
SELECT d.Name, d.Birthday, d.Commands, pa.Genus_name, ya.Age_in_month 
FROM donkeys d 
LEFT JOIN young_animals ya ON ya.Name = d.Name
LEFT JOIN pack_animals pa ON pa.Id = d.Genus_id
UNION
SELECT c.Name, c.Birthday, c.Commands, da.Genus_name, ya.Age_in_month 
FROM cats c
LEFT JOIN young_animals ya ON ya.Name = c.Name
LEFT JOIN domestic_animals da ON da.Id = c.Genus_id
UNION
SELECT d.Name, d.Birthday, d.Commands, da.Genus_name, ya.Age_in_month 
FROM dogs d
LEFT JOIN young_animals ya ON ya.Name = d.Name
LEFT JOIN domestic_animals da ON da.Id = d.Genus_id
UNION
SELECT hm.Name, hm.Birthday, hm.Commands, da.Genus_name, ya.Age_in_month 
FROM hamsters hm
LEFT JOIN young_animals ya ON ya.Name = hm.Name
LEFT JOIN domestic_animals da ON da.Id = hm.Genus_id;