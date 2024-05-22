package com.datatoexcel.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datatoexcel.service.PdfService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class PdfGeneratorController {
	
private PdfService pdfService;

//@Autowired	
public PdfGeneratorController(PdfService pdfService) {
		super();
		this.pdfService = pdfService;
	}



	
	@GetMapping("/pdfGenarator")
    public void createPDF(HttpServletResponse response) throws IOException {
		
		//this below line means setting content type is pdf (we can put json etc...also)
        response.setContentType("application/pdf");
        
        //the below two lines for giving pdf name along with date (particular format)
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
 
        //this Content-Disposition means tell to the application give response as attachment(like pdf,excel)
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Exportpdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
 
        pdfService.export(response);
    }
	
}

