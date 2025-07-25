package org.partha.wmservice.importers;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;

public interface Importer {

    void importData(MultipartFile multipartFiles,String username,String filePassword) throws IOException, ParseException;
}
