package org.partha.wmservice.controllers;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class TestController {

    @PostMapping("/chartTest")
    public ResponseEntity<String> testChar(@RequestBody String body) throws IOException {

        Base64 decoder = new Base64();
        byte[] imgBytes = decoder.decode(body);
        File file = new File("yourImage.png");
        FileOutputStream osf = new FileOutputStream(file);
        System.out.println(file.getAbsolutePath());
        osf.write(imgBytes);
        osf.flush();


        return new ResponseEntity<>("hello",HttpStatus.OK);
    }



}
