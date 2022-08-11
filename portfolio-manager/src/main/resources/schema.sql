set mode MySQL;

create table test(
	symbol varchar(100)
);

create table DividendMaster (
	id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	symbol varchar(50),
	name varchar(100),
	dividendYear integer,
	quarter integer,
	dividendAmount double
);