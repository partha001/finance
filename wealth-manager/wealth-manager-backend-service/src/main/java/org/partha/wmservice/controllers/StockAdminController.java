package org.partha.wmservice.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.partha.wmservice.service.domain.StockService;

@RestController
@RequestMapping("/admin")
public class StockAdminController {

    //
//	@Autowired
//	GoogleSheetsService googleSheetService;
//
    @Autowired
    StockService stockService;


//	@GetMapping("/googleSheet/readPrices")
//	public ResponseEntity<List<Stock>> getFromGoogleSpreadSheet() throws IOException, GeneralSecurityException{
//		return new ResponseEntity<List<Stock>>(googleSheetService.getCurrentStockDetails(),  HttpStatus.OK);
//	}

//	@GetMapping("/googleSheet/recordReadingTime")
//	public ResponseEntity<String> writeToGoogleSpreadSheet() throws IOException, GeneralSecurityException{
//		TimeZone tz = TimeZone.getTimeZone("IST");
//		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_IS08601);
//		df.setTimeZone(tz);
//		String nowAsISO = df.format(new Date());
//		googleSheetService.recordReadingTime(nowAsISO);
//		return new ResponseEntity<String>("Updated",  HttpStatus.OK);
//	}


//    @GetMapping("/googleSheet/upsertPriceToDB")
//    public ResponseEntity<String> upsertPriceToDBFromGoogleSheet() throws IOException, GeneralSecurityException, ParseException {
//        stockService.upsertPriceToDBFromGoogleSheet();
//        return new ResponseEntity<String>("Updated", HttpStatus.OK);
//    }


}
