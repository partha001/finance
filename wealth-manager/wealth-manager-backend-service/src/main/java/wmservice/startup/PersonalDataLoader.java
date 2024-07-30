package wmservice.startup;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import wmservice.loaders.AssetLoader;
import wmservice.loaders.DividendLoader;

@Order(1)
@Component
public class PersonalDataLoader implements CommandLineRunner{
	
	private static final Logger logger = LoggerFactory.getLogger(PersonalDataLoader.class);
	
	@Value("${personalLoadFlag}")
	private Boolean loadPersonalData;
	
	@Autowired
	DividendLoader dividendLoader;
	
	@Autowired
	AssetLoader assetLoader;

	@Override
	public void run(String... args) throws Exception {
		if(loadPersonalData) {
			logger.info("loading dividend details");
			dividendLoader.run();
			logger.info("loading asset details");
			assetLoader.run();
		}
		
	}

}
