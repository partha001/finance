package org.partha.wmservice.importers;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface Importer {

    void importData(MultipartFile multipartFiles,String username) throws IOException;
}
