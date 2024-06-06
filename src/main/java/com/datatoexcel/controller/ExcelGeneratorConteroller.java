package com.datatoexcel.controller;




import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datatoexcel.service.ExcelService;

import jakarta.servlet.http.HttpServletResponse;
//--->this class is genaratess excel file
//--->this controller class service layer-->service layer gets data from db and creats excel workbook and write data in on that work book
//--->then service layer write that workbook in the respose obj
//--->controller gets that respose object and genaratates downloadable excel file
@RestController
public class ExcelGeneratorConteroller {
	
	 private final ExcelService empService;
	
	@Autowired 
	public ExcelGeneratorConteroller(ExcelService empService) {
		super();
		this.empService = empService;
	}


	

	  
	
	
	@GetMapping("/excelGenarator")
	public void generateExcelReport(HttpServletResponse response) throws IOException{
		
		//which indicates that the response will be a binary file.
		response.setContentType("application/octet-stream");
		
		//this Content-Disposition mean tells to our application genarate response as attatchment
		String headerKey = "Content-Disposition";
		//Mentioning  file name have to genarate
		String headerValue = "attachment;filename=empDetails.xlsx";

		response.setHeader(headerKey, headerValue);
		
		empService.generateExcel(response);
		
		response.flushBuffer();
	}
	
	//the below is for pdfGenarator
	/*
	 @GetMapping("/pdf")
	    public void createPDF(HttpServletResponse response) throws IOException {
	        response.setContentType("application/pdf");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	 
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=Exportpdf_" + currentDateTime + ".pdf";
	        response.setHeader(headerKey, headerValue);
	 
	        empService.export(response);
	    }
	
	*/
	
	
}
