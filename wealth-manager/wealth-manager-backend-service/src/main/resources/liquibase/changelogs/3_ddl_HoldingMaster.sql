--liquibase formatted sql
--changeset partha:3_ddl_holdingMaster


CREATE TABLE IF NOT EXISTS WealthManager.HoldingMaster(
            id serial primary key,
            username varchar(100),
            brokername varchar(50),
            brokersymbol VARCHAR(50),
            exchangesymbol VARCHAR(50),
            isin varchar(50),
            quantity INTEGER,
            averagePrice numeric NULL,
            key varchar(255) NULL,
            CONSTRAINT wm_holdingMaster_unique_username_brokersymbol UNIQUE (username,brokername,brokersymbol)
            );