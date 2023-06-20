package com.example.demo.Practice;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.example.demo.repository.ProductRepo;
import com.example.demo.bean.FileBean;
import com.example.demo.bean.Products;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


@org.springframework.stereotype.Controller
public class Controller {

	@Autowired
	ProductRepo ProductRepo ;
	
	@GetMapping("/test")
	public String gettest1(Model model)
	{
		model.addAttribute("message","hii Shailesh");
		return "test";
	}
	
	@GetMapping("/test2")
	public String gettest2(Model model)
	{
		model.addAttribute("message","hii Shailesh");
		return "awaitasync";
	}
	
	@GetMapping("/homePage")
	public String homePage(Model model)
	{
	List<Products> getAllProducts = ProductRepo.findAll();
	model.addAttribute("allProducts", getAllProducts);
		return "homePage";
	}
	
@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
public String submit(@RequestParam("file") CommonsMultipartFile file, ModelMap modelMap) throws IOException {
    modelMap.addAttribute("file", file);

    try {

        File tempFile = File.createTempFile("temp", ".xlsx");
        file.transferTo(tempFile);       
        processExcelFile(tempFile);
        
        return "fileUploadView";
    } catch (IOException e) {
        e.printStackTrace();
      
        return "errorView";
    }
}


private void processExcelFile(File file) {
	
	 try (FileInputStream inputStream = new FileInputStream(file)) {
		 Workbook workbook;
	        System.out.println("file.getName() "+file.getName());
	        // Determine the appropriate Workbook class based on the file extension
	        if (file.getName().endsWith(".xls")) {
	            workbook = new HSSFWorkbook(inputStream); // For older Excel formats (.xls)
	        } else if (file.getName().endsWith(".xlsx")) {
	            workbook = new XSSFWorkbook(inputStream); // For newer Excel formats (.xlsx)
	        } else {
	            // Handle unsupported file formats
	            throw new IllegalArgumentException("Unsupported file format");
	        }
	        
	        // Rest of the code for processing the workbook
	        	
	        Sheet sheet = workbook.getSheetAt(0); // Assuming the data is in the first sheet
	        for (Row row : sheet) {
	            // Assuming the data is in columns A and B, modify as per your Excel structure
	        	String modelNumber ="";
	        	String name = "";
	        	String price = "";
	        	String quantity ="";
	        	
	        	Cell cell1 = row.getCell(1);
	        	if (cell1.getCellType() == CellType.STRING) {
	        		modelNumber = cell1.getStringCellValue();
	        	} else if (cell1.getCellType() == CellType.NUMERIC) {
	        		modelNumber = String.valueOf((int) cell1.getNumericCellValue());
	        	}
	        	
	        	Cell cell2 = row.getCell(2);
	        	if (cell2.getCellType() == CellType.STRING) {
	        	    name = cell2.getStringCellValue();
	        	} else if (cell2.getCellType() == CellType.NUMERIC) {
	        	    name = String.valueOf((int) cell2.getNumericCellValue());
	        	}
	        	
	        	Cell cell3 = row.getCell(3);
	        	if (cell3.getCellType() == CellType.STRING) {
	        		price = cell3.getStringCellValue();
	        	} else if (cell3.getCellType() == CellType.NUMERIC) {
	        		price = String.valueOf((int) cell3.getNumericCellValue());
	        	}
	        	
	        	Cell cell4 = row.getCell(4);
	        	if (cell4.getCellType() == CellType.STRING) {
	        		quantity = cell4.getStringCellValue();
	        	} else if (cell4.getCellType() == CellType.NUMERIC) {
	        		quantity = String.valueOf((int) cell4.getNumericCellValue());
	        	}
         
	            Products entity = new Products();
	            entity.setModelNumber(String.valueOf(modelNumber));
	            entity.setName(name);
	            entity.setPrice(String.valueOf(price));
	            entity.setQuantity(String.valueOf(quantity));
	           
	            // Use Spring Data JPA or JDBC to save the entity to the database
	            ProductRepo.save(entity);
	        }

	        workbook.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
}

}
