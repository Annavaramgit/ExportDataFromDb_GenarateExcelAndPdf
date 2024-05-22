package com.datatoexcel.service;

import java.io.IOException;

import java.util.List;


import org.springframework.stereotype.Service;

import com.datatoexcel.model.Employee;
import com.datatoexcel.repository.EmployeeRepo;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;
@Service
public class PdfService {
	
	
	private EmployeeRepo employeeRepo;
	
	
	public PdfService(EmployeeRepo employeeRepo) {
		super();
		this.employeeRepo = employeeRepo;
	}


	public void export(HttpServletResponse response) throws IOException {
		
		//this below 3 lines  means creates new pdfDocument with A4 size
		//and links document with HTTp response's output stream so pdf directly witten in the response
	
		
		Document  document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
      
        //this 4 lines used for adding header content or text 
        //first two lines tells style of text and size
        //3rd line means that text have to add with specifies style and size
        Font fontHeader = FontFactory.getFont(FontFactory.TIMES_BOLD);
        fontHeader.setSize(22);
        Paragraph headerParagraph = new Paragraph("EMPLOYEE DETAILS LIST", fontHeader);
       headerParagraph.setAlignment(Paragraph.ALIGN_CENTER);

        //the below is for adding paragraph(same like adding header)
       //present iam not using that's why i putted in comments
       /*
        Font fontParagraph = FontFactory.getFont(FontFactory.TIMES);
        fontParagraph.setSize(14);
        Paragraph pdfParagraph = new Paragraph("add paragraph text here", fontParagraph);
        pdfParagraph.setAlignment(Paragraph.ALIGN_LEFT);
       */

        //fetching all employees from DB
        List<Employee> listOfEmployees = employeeRepo.findAll();

        // Create a table to add employee data
        PdfPTable table = new PdfPTable(4); // 4 columns
        table.setWidthPercentage(70);
        table.setSpacingBefore(8f);
        table.setSpacingAfter(10f);

        // Define column widths
        float[] columnWidths = {1f, 1f, 1f, 1f};
        table.setWidths(columnWidths);

        // The below lines used for add table headers
        //the immediate 2 lines is for style and size of the headers
        Font fontTableHeader = FontFactory.getFont(FontFactory.TIMES_BOLD);
        fontTableHeader.setSize(12);
        
        PdfPCell cell = new PdfPCell(new Phrase("Employee ID", fontTableHeader));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Employee Name", fontTableHeader));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Employee Salary", fontTableHeader));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Employee Address", fontTableHeader));
        table.addCell(cell);

        // this below lines is for add the data in t he pdf table
        //the immediate 2 lines is for style and size of the data
        Font fontTableData = FontFactory.getFont(FontFactory.TIMES);
        fontTableData.setSize(12);
        
        //Enhanced for loop for iterate data in the list and add it in the cells of table
        for (Employee employee : listOfEmployees) {
            table.addCell(new PdfPCell(new Phrase(String.valueOf(employee.getEmpId()), fontTableData)));
            table.addCell(new PdfPCell(new Phrase(employee.getEmpName(), fontTableData)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(employee.getEmpSalary()), fontTableData)));
            table.addCell(new PdfPCell(new Phrase(employee.getEmpAdress(), fontTableData)));
        }

        // Add headerParagraph as header in the document
        document.add(headerParagraph);
      
        //add that table in the document
        document.add(table);

        //this for close the document
        document.close();	  
        
	}
}
