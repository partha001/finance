set mode MySQL;

DROP TABLE test;
DROP TABLE DividendMaster;
DROP TABLE AssetMaster;
DROP TABLE StockMaster;
DROP TABLE StockPriceMaster;

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
     recordDate		DATE
  );
  


CREATE TABLE StockMaster
  (
     id     		INT(11) NOT NULL auto_increment PRIMARY KEY,
     exchange       VARCHAR(20),
     symbol         VARCHAR(50),
     name           VARCHAR(100),
     isinNumber     VARCHAR(50),
     faceValue      DOUBLE,
     listingDate	DATE,
     price          Double,
     stockKey       VARCHAR(50), 
     CONSTRAINT UC_ExchangeSymbol UNIQUE (exchange,symbol)
  );
  
  
  CREATE TABLE StockPriceMaster
  (
     id     		INT(11) NOT NULL auto_increment PRIMARY KEY,
     stockMasterId  INTEGER,
     stockKey       VARCHAR(50),
     price 			DOUBLE,
     priceTime		DATETIME,
	 timeZoneName   VARCHAR(20) 
  );
  
  