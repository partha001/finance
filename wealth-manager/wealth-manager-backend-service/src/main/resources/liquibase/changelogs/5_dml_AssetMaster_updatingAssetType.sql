--liquibase formatted sql
--changeset partha:3_dml_AssetMaster_updating_Assetype

update wealthmanager.assetmaster set assettype = 'equity' where assetname in ('zerodha_equity', 'upstox_equity', 'angelone_equity', 'indmoney_us_stocks');
update wealthmanager.assetmaster set assettype = 'cash' where assetname in ('cash', 'angelone_cash', 'upstox_cash', 'groww_cash','zerodha_cash');
update wealthmanager.assetmaster set assettype = 'mutual-fund' where assetname in ('axis_mf', 'zerodha_mf', 'groww_mf');
update wealthmanager.assetmaster set assettype = 'debt' where assetname in ('goldenpi_bond', 'lic', 'pf');
update wealthmanager.assetmaster set assettype = 'crypto' where assetname in ('wazirx', 'vauld');

