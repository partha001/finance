package com.portfoliomanger.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfoliomanger.dao.StockDao;
import com.portfoliomanger.entities.Stock;
import com.portfoliomanger.util.StockUtil;

@Service
public class StockService {
	
	@Autowired
	StockDao stockDao;
	
	public List<Stock> readAllStockData(){
		return stockDao.readAllStockData();
	}
	
	public void refreshStockMap() {
		List<Stock> stockList = stockDao.readAllStockData();
		StockUtil.stockMap.clear();
		stockList.forEach(item -> {
			StockUtil.stockMap.put(item.getKey(), item);
		});
	}
	
	
	public Map<String,Stock> getStockMapFromDb(){
		Map<String,Stock> map = new HashMap<String, Stock>();
		List<Stock> stockList = stockDao.readAllStockData();
		stockList.forEach(item -> map.put(item.getKey(), item));
		return map;
	}

	public int updateCurrentPrice(List<Stock> stockSaveList) {
		return stockDao.updateCurrentPrice(stockSaveList);
	}
	
	public int loadToStockmaster(List<Stock> list) {
		return stockDao.loadToStockmaster(list);
	}


}
