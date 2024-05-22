package com.datatoexcel.service;



import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.datatoexcel.model.Employee;
import com.datatoexcel.repository.EmployeeRepo;


import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ExcelService {
	
	
	private EmployeeRepo employeeRepo;
	
	@Autowired
	public ExcelService(EmployeeRepo employeeRepo) {
		super();
		this.employeeRepo = employeeRepo;
	}


	//this method is for create excel sheet and add data which exports from DB
	public void generateExcel(HttpServletResponse response) throws IOException {
	
	//fetching all the data in the database	
	java.util.List<Employee> listOfEmployees =employeeRepo.findAll();
	
	//creates workbook and sheet and row
	HSSFWorkbook workBook = new HSSFWorkbook();
	HSSFSheet sheet=  workBook.createSheet();
	HSSFRow row =    sheet.createRow(0);
	
	//add column headers
	row.createCell(0).setCellValue("EmpId");
	row.createCell(1).setCellValue("EmpName");
	row.createCell(2).setCellValue("EmpSalary");
	row.createCell(3).setCellValue("EmpAdress");
	
	//row-0 already have column headers so,data need store row-1 onwards
	int rowIndex =1;
	
	//iterating list and inserted in cells in the excel sheet
	for (Employee emp:listOfEmployees) {
		HSSFRow dataRow = sheet.createRow(rowIndex);
		dataRow.createCell(0).setCellValue(emp.getEmpId());
		dataRow.createCell(1).setCellValue(emp.getEmpName());
		dataRow.createCell(2).setCellValue(emp.getEmpSalary());
		dataRow.createCell(3).setCellValue(emp.getEmpAdress());
		rowIndex++;
		
	}
	//
	ServletOutputStream ops = response.getOutputStream();
	workBook.write(ops);
	workBook.close();
	ops.close();
	}

	
	/*
	public void export(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontHeader = FontFactory.getFont(FontFactory.TIMES_BOLD);
        fontHeader.setSize(22);

        Paragraph headerParagraph = new Paragraph("## PDF Heading ##", fontHeader);
        headerParagraph.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.TIMES);
        fontParagraph.setSize(14);

        Paragraph pdfParagraph = new Paragraph("", fontParagraph);
        pdfParagraph.setAlignment(Paragraph.ALIGN_LEFT);

       

        List<Employee> listOfEmployees = employeeRepo.findAll();

        // Create a table to add employee data
        PdfPTable table = new PdfPTable(4); // 4 columns
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        // Define column widths
        float[] columnWidths = {1f, 3f, 2f, 4f};
        table.setWidths(columnWidths);

        // Table header
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

        // Table data
        Font fontTableData = FontFactory.getFont(FontFactory.TIMES);
        fontTableData.setSize(12);
        for (Employee employee : listOfEmployees) {
            table.addCell(new PdfPCell(new Phrase(String.valueOf(employee.getEmpId()), fontTableData)));
            table.addCell(new PdfPCell(new Phrase(employee.getEmpName(), fontTableData)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(employee.getEmpSalary()), fontTableData)));
            table.addCell(new PdfPCell(new Phrase(employee.getEmpAdress(), fontTableData)));
        }

        // Add components to the document
        document.add(headerParagraph);
      
        document.add(table);

        document.close();	  
        
	}
	
	*/
}
