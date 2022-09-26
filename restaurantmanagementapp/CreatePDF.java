/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurantmanagementapp;

import java.io.File;   
import java.io.IOException;   
import java.util.ArrayList;
import org.apache.pdfbox.pdmodel.PDDocument;   
import org.apache.pdfbox.pdmodel.PDPage;   
import org.apache.pdfbox.pdmodel.PDPageContentStream;   
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;  

/**
 *
 * @author trong
 */
public class CreatePDF {
    private GUI frame;
    
    public CreatePDF(GUI frame) {
        this.frame = frame;
    }
    
    public String createPDFFile (ArrayList<Item> arrayList, String tableName) throws IOException{
        String fileName = "Order.pdf";
        
        //Construc doc and page
        PDDocument doc = new PDDocument();
        PDPage page = new PDPage();
        doc.addPage(page);
        
        
        PDPageContentStream contentStream = new PDPageContentStream(doc, page); 
        
        contentStream.beginText();   
        //Setting the font to the Content stream    
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 26);
        contentStream.newLineAtOffset(200, 600);
        contentStream.setLeading(25f);
        //Create title with the table name
        String nameOfTable = tableName;
        contentStream.showText("** " + tableName + " **");
        contentStream.endText();  
        
        contentStream.beginText(); 
        //print list of items
        contentStream.newLineAtOffset(200, 550);
        contentStream.setFont(PDType1Font.HELVETICA, 14);
        
        for (int i = 0; i < arrayList.size(); i++){
            String itemName = arrayList.get(i).getName();
            contentStream.showText("- " + itemName);
            contentStream.newLine();
        }
        
        //Ending the content stream  
        contentStream.endText();  
       //Closing the content stream  
        contentStream.close();  
        
        //Saving the document  
        //doc.save("C:\\Users\\trong\\Documents\\"+fileName);  
        doc.save(fileName);
        //Closing the document  
        doc.close();  
        return "Order sent successfully";
    }
    
    
}  
       
