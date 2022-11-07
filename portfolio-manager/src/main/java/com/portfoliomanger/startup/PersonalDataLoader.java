package com.portfoliomanger.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.portfoliomanger.loaders.AssetLoader;
import com.portfoliomanger.loaders.DividendLoader;

@Order(0)
@Component
public class PersonalDataLoader implements CommandLineRunner{
	
	private static final Logger logger = LoggerFactory.getLogger(AssetLoader.class);
	
	@Autowired
	DividendLoader dividendLoader;
	
	@Autowired
	AssetLoader assetLoader;

	@Override
	public void run(String... args) throws Exception {
		logger.info("loading dividend details");
		dividendLoader.run();
		logger.info("loading asset details");
		assetLoader.run();
	}

}
