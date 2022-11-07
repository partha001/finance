set mode MySQL;

create table test(
	symbol varchar(100)
);


CREATE TABLE DividendMaster
  (
     id             INT(11) NOT NULL auto_increment PRIMARY KEY,
     symbol         VARCHAR(50),
     name           VARCHAR(100),
     dividendyear   INTEGER,
     quarter        INTEGER,
     dividendamount DOUBLE
  );  


CREATE TABLE AssetMaster
  (
     id     		INT(11) NOT NULL auto_increment PRIMARY KEY,
     assetName   	VARCHAR(100),
     amount 		DOUBLE,
     recordDate		date
  );
  

CREATE TABLE StockMaster
  (
     id     		INT(11) NOT NULL auto_increment PRIMARY KEY,
     symbol         VARCHAR(50),
     name           VARCHAR(100),
     isinNumber     VARCHAR(50),
     faceValue      DOUBLE,
     listingDate	date  
  );