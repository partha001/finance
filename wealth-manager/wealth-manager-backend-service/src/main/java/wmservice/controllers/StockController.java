package wmservice.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import wmcommon.entities.Stock;
import wmcommon.util.StockUtil;
import wmservice.dao.StockDao;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StockController {
	
	private static final Logger logger = LoggerFactory.getLogger(StockController.class);
	
	@Autowired
	StockDao dao;
	
//	@Autowired
//	GoogleService service;
	
	

	
//	@Autowired
//	StockUtil stockUtil;
	
//	private static String sheetId ="1zI0xbc_wDrECMi9IVX_wPNj3kECiq0NMXYFB0-FFFnU";
	
	
	@GetMapping("/getStockDetails")
	public ResponseEntity<List<Stock>> getStockDetails(){
		//Collection<Stock> values = StockUtil.stockMap.values();
		List<Stock> list = StockUtil.stockMap.values().stream().collect(Collectors.toList());
		//stockUtil.stockMap.keySet().stream().map(item -> )
		return new ResponseEntity<List<Stock>>(list,  HttpStatus.OK);
	}
	
	
	

	
	
//	@GetMapping("/test")
//	public ResponseEntity<String> test(){
//		RestTemplate restTemplate= new RestTemplate();
//		
//		HttpHeaders headers = new HttpHeaders();
//
//		// set `Content-Type` and `Accept` headers
////		headers.setContentType(MediaType.APPLICATION_JSON);
////		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//////		 'X-RapidAPI-Key': '7c8fa2f679msh9a7fbc452551bc3p1cd112jsnb7d48e402e99',
//////		    'X-RapidAPI-Host': 'yh-finance.p.rapidapi.com'
//		headers.set("X-RapidAPI-Key", "7c8fa2f679msh9a7fbc452551bc3p1cd112jsnb7d48e402e99");
//		headers.set("X-RapidAPI-Host", "yh-finance.p.rapidapi.com");
//		HttpEntity request = new HttpEntity(headers);
//	
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("symbol", "ITC.NS");
//		params.put("region", "IN");
//
//		
//		ResponseEntity<String> response = restTemplate.exchange(
//		        "https://yh-finance.p.rapidapi.com/stock/v3/get-historical-data",
//		        HttpMethod.GET,
//		        request,
//		        String.class,
//		        params
//		);
//		
//		
//		Stock stock = YahooFinance.get("INTC");
//
//		BigDecimal price = stock.getQuote().getPrice();
//		BigDecimal change = stock.getQuote().getChangeInPercent();
//		BigDecimal peg = stock.getStats().getPeg();
//		BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();
//
//		stock.print();
//		
//		return response;
//		
//		
//		//stTemplate.exchange("https://yh-finance.p.rapidapi.com/stock/v3/get-historical-data", HttpMethod.GET, requestEntity, String.class);
//	}

}
