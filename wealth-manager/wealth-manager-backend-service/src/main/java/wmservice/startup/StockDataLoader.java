package wmservice.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import wmservice.loaders.NSEDataLoader;

@Order(0)
@Component
public class StockDataLoader implements CommandLineRunner{
	
	private static final Logger logger = LoggerFactory.getLogger(StockDataLoader.class);


	
	@Value("${nseEquityListLoadFlag}")
	private Boolean nseEquityListLoadFlag;
	
	@Autowired
	NSEDataLoader nseDataLoader;
	

	@Override
	public void run(String... args) throws Exception {
		if(nseEquityListLoadFlag) {
			logger.info("loading nse data");
			nseDataLoader.run();
		}		
		//stockService.refreshStockMap();
		
	}

}
