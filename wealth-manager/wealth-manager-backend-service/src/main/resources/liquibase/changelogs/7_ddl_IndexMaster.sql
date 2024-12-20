--liquibase formatted sql
--changeset partha:7_ddl_IndexMaster

CREATE TABLE IF NOT EXISTS WealthManager.IndexMaster (
            id serial4 NOT NULL,
            indexName text,
            indexKey text,
            yahoofinanceTicker text,
			CONSTRAINT uc_im_indexName UNIQUE (indexName),
            primary key (id)
);


INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY 50','IDX:NIFTY_50');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY NEXT 50','IDX:NIFTY_NEXT_50');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY 100','IDX:NIFTY_100');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY 200','IDX:NIFTY_200');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY 500','IDX:NIFTY_500');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY MIDCAP 50','IDX:NIFTY_MIDCAP_50');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY MIDCAP 100','IDX:NIFTY_MIDCAP_100');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY SMALLCAP 100','IDX:NIFTY_SMALLCAP_100');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('INDIA VIX','IDX:INDIA_VIX');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY MIDCAP 150','IDX:NIFTY_MIDCAP_150');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY SMALLCAP 50','IDX:NIFTY_SMALLCAP_50');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY SMALLCAP 250','IDX:NIFTY_SMALLCAP_250');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY MIDSMALLCAP 400','IDX:NIFTY_MIDSMALLCAP_400');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY500 MULTICAP 50:25:25','IDX:NIFTY500_MULTICAP_50:25:25');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY LARGEMIDCAP 250','IDX:NIFTY_LARGEMIDCAP_250');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY MIDCAP SELECT','IDX:NIFTY_MIDCAP_SELECT');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY TOTAL MARKET','IDX:NIFTY_TOTAL_MARKET');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY MICROCAP 250','IDX:NIFTY_MICROCAP_250');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY500 LARGEMIDSMALL EQUAL-CAP WEIGHTED','IDX:NIFTY500_LARGEMIDSMALL_EQUAL-CAP_WEIGHTED');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY BANK','IDX:NIFTY_BANK');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY AUTO','IDX:NIFTY_AUTO');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY FINANCIAL SERVICES','IDX:NIFTY_FINANCIAL_SERVICES');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY FINANCIAL SERVICES 25/50','IDX:NIFTY_FINANCIAL_SERVICES_25/50');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY FMCG','IDX:NIFTY_FMCG');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY IT','IDX:NIFTY_IT');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY MEDIA','IDX:NIFTY_MEDIA');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY METAL','IDX:NIFTY_METAL');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY PHARMA','IDX:NIFTY_PHARMA');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY PSU BANK','IDX:NIFTY_PSU_BANK');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY PRIVATE BANK','IDX:NIFTY_PRIVATE_BANK');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY REALTY','IDX:NIFTY_REALTY');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY HEALTHCARE INDEX','IDX:NIFTY_HEALTHCARE_INDEX');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY CONSUMER DURABLES','IDX:NIFTY_CONSUMER_DURABLES');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY OIL & GAS','IDX:NIFTY_OIL_&_GAS');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY MIDSMALL HEALTHCARE','IDX:NIFTY_MIDSMALL_HEALTHCARE');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY FINANCIAL SERVICES EX-BANK','IDX:NIFTY_FINANCIAL_SERVICES_EX-BANK');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY MIDSMALL FINANCIAL SERVICES','IDX:NIFTY_MIDSMALL_FINANCIAL_SERVICES');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY MIDSMALL IT & TELECOM','IDX:NIFTY_MIDSMALL_IT_&_TELECOM');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY DIVIDEND OPPORTUNITIES 50','IDX:NIFTY_DIVIDEND_OPPORTUNITIES_50');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY GROWTH SECTORS 15','IDX:NIFTY_GROWTH_SECTORS_15');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY100 QUALITY 30','IDX:NIFTY100_QUALITY_30');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY50 VALUE 20','IDX:NIFTY50_VALUE_20');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY50 TR 2X LEVERAGE','IDX:NIFTY50_TR_2X_LEVERAGE');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY50 PR 2X LEVERAGE','IDX:NIFTY50_PR_2X_LEVERAGE');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY50 TR 1X INVERSE','IDX:NIFTY50_TR_1X_INVERSE');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY50 PR 1X INVERSE','IDX:NIFTY50_PR_1X_INVERSE');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY50 DIVIDEND POINTS','IDX:NIFTY50_DIVIDEND_POINTS');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY ALPHA 50','IDX:NIFTY_ALPHA_50');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY50 EQUAL WEIGHT','IDX:NIFTY50_EQUAL_WEIGHT');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY100 EQUAL WEIGHT','IDX:NIFTY100_EQUAL_WEIGHT');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY100 LOW VOLATILITY 30','IDX:NIFTY100_LOW_VOLATILITY_30');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY200 QUALITY 30','IDX:NIFTY200_QUALITY_30');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY ALPHA LOW-VOLATILITY 30','IDX:NIFTY_ALPHA_LOW-VOLATILITY_30');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY200 MOMENTUM 30','IDX:NIFTY200_MOMENTUM_30');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY MIDCAP150 QUALITY 50','IDX:NIFTY_MIDCAP150_QUALITY_50');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY200 ALPHA 30','IDX:NIFTY200_ALPHA_30');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY MIDCAP150 MOMENTUM 50','IDX:NIFTY_MIDCAP150_MOMENTUM_50');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY500 MOMENTUM 50','IDX:NIFTY500_MOMENTUM_50');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY MIDSMALLCAP400 MOMENTUM QUALITY 100','IDX:NIFTY_MIDSMALLCAP400_MOMENTUM_QUALITY_100');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY SMALLCAP250 MOMENTUM QUALITY 100','IDX:NIFTY_SMALLCAP250_MOMENTUM_QUALITY_100');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY TOP 10 EQUAL WEIGHT','IDX:NIFTY_TOP_10_EQUAL_WEIGHT');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY ALPHA QUALITY LOW-VOLATILITY 30','IDX:NIFTY_ALPHA_QUALITY_LOW-VOLATILITY_30');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY ALPHA QUALITY VALUE LOW-VOLATILITY 30','IDX:NIFTY_ALPHA_QUALITY_VALUE_LOW-VOLATILITY_30');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY HIGH BETA 50','IDX:NIFTY_HIGH_BETA_50');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY LOW VOLATILITY 50','IDX:NIFTY_LOW_VOLATILITY_50');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY QUALITY LOW-VOLATILITY 30','IDX:NIFTY_QUALITY_LOW-VOLATILITY_30');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY SMALLCAP250 QUALITY 50','IDX:NIFTY_SMALLCAP250_QUALITY_50');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY TOP 15 EQUAL WEIGHT','IDX:NIFTY_TOP_15_EQUAL_WEIGHT');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY100 ALPHA 30','IDX:NIFTY100_ALPHA_30');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY200 VALUE 30','IDX:NIFTY200_VALUE_30');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY500 EQUAL WEIGHT','IDX:NIFTY500_EQUAL_WEIGHT');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY500 MULTICAP MOMENTUM QUALITY 50','IDX:NIFTY500_MULTICAP_MOMENTUM_QUALITY_50');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY500 VALUE 50','IDX:NIFTY500_VALUE_50');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY TOP 20 EQUAL WEIGHT','IDX:NIFTY_TOP_20_EQUAL_WEIGHT');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY COMMODITIES','IDX:NIFTY_COMMODITIES');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY INDIA CONSUMPTION','IDX:NIFTY_INDIA_CONSUMPTION');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY CPSE','IDX:NIFTY_CPSE');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY ENERGY','IDX:NIFTY_ENERGY');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY INFRASTRUCTURE','IDX:NIFTY_INFRASTRUCTURE');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY100 LIQUID 15','IDX:NIFTY100_LIQUID_15');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY MIDCAP LIQUID 15','IDX:NIFTY_MIDCAP_LIQUID_15');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY MNC','IDX:NIFTY_MNC');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY PSE','IDX:NIFTY_PSE');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY SERVICES SECTOR','IDX:NIFTY_SERVICES_SECTOR');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY100 ESG SECTOR LEADERS','IDX:NIFTY100_ESG_SECTOR_LEADERS');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY INDIA DIGITAL','IDX:NIFTY_INDIA_DIGITAL');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY100 ESG','IDX:NIFTY100_ESG');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY INDIA MANUFACTURING','IDX:NIFTY_INDIA_MANUFACTURING');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY INDIA CORPORATE GROUP INDEX - TATA GROUP 25% CAP','IDX:NIFTY_INDIA_CORPORATE_GROUP_INDEX_-_TATA_GROUP_25%_CAP');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY500 MULTICAP INDIA MANUFACTURING 50:30:20','IDX:NIFTY500_MULTICAP_INDIA_MANUFACTURING_50:30:20');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY500 MULTICAP INFRASTRUCTURE 50:30:20','IDX:NIFTY500_MULTICAP_INFRASTRUCTURE_50:30:20');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY INDIA DEFENCE','IDX:NIFTY_INDIA_DEFENCE');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY INDIA TOURISM','IDX:NIFTY_INDIA_TOURISM');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY CAPITAL MARKETS','IDX:NIFTY_CAPITAL_MARKETS');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY EV & NEW AGE AUTOMOTIVE','IDX:NIFTY_EV_&_NEW_AGE_AUTOMOTIVE');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY INDIA NEW AGE CONSUMPTION','IDX:NIFTY_INDIA_NEW_AGE_CONSUMPTION');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY INDIA SELECT 5 CORPORATE GROUPS (MAATR)','IDX:NIFTY_INDIA_SELECT_5_CORPORATE_GROUPS_(MAATR)');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY MOBILITY','IDX:NIFTY_MOBILITY');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY100 ENHANCED ESG','IDX:NIFTY100_ENHANCED_ESG');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY CORE HOUSING','IDX:NIFTY_CORE_HOUSING');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY HOUSING','IDX:NIFTY_HOUSING');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY IPO','IDX:NIFTY_IPO');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY MIDSMALL INDIA CONSUMPTION','IDX:NIFTY_MIDSMALL_INDIA_CONSUMPTION');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY NON-CYCLICAL CONSUMER','IDX:NIFTY_NON-CYCLICAL_CONSUMER');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY RURAL','IDX:NIFTY_RURAL');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY SHARIAH 25','IDX:NIFTY_SHARIAH_25');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY TRANSPORTATION & LOGISTICS','IDX:NIFTY_TRANSPORTATION_&_LOGISTICS');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY50 SHARIAH','IDX:NIFTY50_SHARIAH');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY500 SHARIAH','IDX:NIFTY500_SHARIAH');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY 8-13 YR G-SEC','IDX:NIFTY_8-13_YR_G-SEC');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY 10 YR BENCHMARK G-SEC','IDX:NIFTY_10_YR_BENCHMARK_G-SEC');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY 10 YR BENCHMARK G-SEC (CLEAN PRICE)','IDX:NIFTY_10_YR_BENCHMARK_G-SEC_(CLEAN_PRICE)');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY 4-8 YR G-SEC INDEX','IDX:NIFTY_4-8_YR_G-SEC_INDEX');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY 11-15 YR G-SEC INDEX','IDX:NIFTY_11-15_YR_G-SEC_INDEX');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY 15 YR AND ABOVE G-SEC INDEX','IDX:NIFTY_15_YR_AND_ABOVE_G-SEC_INDEX');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY COMPOSITE G-SEC INDEX','IDX:NIFTY_COMPOSITE_G-SEC_INDEX');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY BHARAT BOND INDEX - APRIL 2025','IDX:NIFTY_BHARAT_BOND_INDEX_-_APRIL_2025');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY BHARAT BOND INDEX - APRIL 2030','IDX:NIFTY_BHARAT_BOND_INDEX_-_APRIL_2030');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY BHARAT BOND INDEX - APRIL 2031','IDX:NIFTY_BHARAT_BOND_INDEX_-_APRIL_2031');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY BHARAT BOND INDEX - APRIL 2032','IDX:NIFTY_BHARAT_BOND_INDEX_-_APRIL_2032');
INSERT INTO WealthManager.IndexMaster (indexName, indexKey) values ('NIFTY BHARAT BOND INDEX - APRIL 2033','IDX:NIFTY_BHARAT_BOND_INDEX_-_APRIL_2033');

update wealthmanager.indexmaster  set yahoofinanceticker = '^NSEI' where indexkey = 'IDX:NIFTY_50';
update wealthmanager.indexmaster  set yahoofinanceticker = '^NSMIDCP' where indexkey = 'IDX:NIFTY_NEXT_50';

