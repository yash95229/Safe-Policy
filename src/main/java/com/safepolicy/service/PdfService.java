package com.safepolicy.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.Background;
import com.itextpdf.layout.property.BorderRadius;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.safepolicy.model.PersonalDetail;
import com.safepolicy.model.Policy;

@Service
public class PdfService {

    public byte[] generatePolicyPdf(List<Policy> policies, List<PersonalDetail> personalDetails) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf); 
       
       
        try {
            addTitle(document, "Policy Details");
            for (Policy policy : policies) {
                // Find the corresponding personal detail for this policy
                PersonalDetail matchingPersonalDetail = personalDetails.stream()
                    .filter(detail -> detail.getQuoteId().equals(policy.getQuoteId()))
                    .findFirst()
                    .orElse(null);
                
                // Add policy details and personal details to the document
                addPolicyDetails(document, policy, matchingPersonalDetail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
       
        return baos.toByteArray();
    }

    private void addTitle(Document document, String title) throws IOException {
        // Creating a Paragraph for the title
        Paragraph titleParagraph = new Paragraph(title)
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                .setFontSize(18)
                .setFontColor(new DeviceRgb(255, 255, 255))
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(20)
                .setBackgroundColor(new DeviceRgb(2,168,187))
                .setPaddingTop(20)
                .setPaddingBottom(20)
                .setPaddingLeft(20)
                .setPaddingRight(20);

        document.add(titleParagraph);
    }

    private void addPolicyDetails(Document document, Policy policy, PersonalDetail personalDetails) throws IOException {
        // Left-aligned details
    	
		 Table tableFirst = new Table(4);
		
		 tableFirst.setWidth(UnitValue.createPercentValue(100));
		 tableFirst.setBorder(Border.NO_BORDER);  
		 
		 Date date = new Date();
		 SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		 String BookingFormattedDate = formatter.format(policy.getBookingDate());
	     
		 tableFirst.addCell(createBorderlessCell("Name ").setBold().setFontColor(new DeviceRgb(18, 8, 8)));
		 tableFirst.addCell(createBorderlessCell(policy.getFullName()).setMarginTop(25));
		 tableFirst.addCell(createBorderlessCell("Email ").setBold().setFontColor(new DeviceRgb(18, 8, 8)));
		 tableFirst.addCell(createBorderlessCell(personalDetails.getEmail()).setMarginTop(5));
		 tableFirst.addCell(createBorderlessCell("Phone Number ").setBold().setFontColor(new DeviceRgb(18, 8, 8)));
		 tableFirst.addCell(createBorderlessCell(personalDetails.getNumber()).setMarginTop(5));	 
		 tableFirst.addCell(createBorderlessCell("Pan Number ").setBold().setFontColor(new DeviceRgb(18, 8, 8)));
		 tableFirst.addCell(createBorderlessCell(personalDetails.getPanNumber()).setMarginTop(5));
		 tableFirst.addCell(createBorderlessCell("Aadhaar Number ").setBold().setFontColor(new DeviceRgb(18, 8, 8)));
		 tableFirst.addCell(createBorderlessCell(personalDetails.getAadhaarNumber()).setMarginTop(5));
		 tableFirst.addCell(createBorderlessCell("Booking Date ").setBold().setFontColor(new DeviceRgb(18, 8, 8)));
		 tableFirst.addCell(createBorderlessCell(String.valueOf(BookingFormattedDate)).setMarginTop(5));
		 tableFirst.addCell(createBorderlessCell("Gender ").setBold().setFontColor(new DeviceRgb(18, 8, 8)));
		 tableFirst.addCell(createBorderlessCell(personalDetails.getGender()).setMarginTop(5));
		 
		 
		 document.add(tableFirst.setMarginBottom(20));
		 
        
        SolidLine line = new SolidLine(2f); // 2f is the thickness of the line
        line.setColor(new DeviceRgb(2,168,187)); // Color: Cyan (0, 204, 204)
        line.setLineWidth(3);
        document.add(new LineSeparator(line));   
        
       
        Table tableSecond = new Table(6);
        tableSecond.setMarginTop(20);
        tableSecond.setWidth(UnitValue.createPercentValue(100));
        tableSecond.setBorder(Border.NO_BORDER);  

        tableSecond.addCell(createBorderlessCell("Booking ID ").setBold().setFontColor(new DeviceRgb(255, 255, 255)).setBackgroundColor(new DeviceRgb(2,168,187)));
        tableSecond.addCell(createBorderlessCell("Application Number ").setBold().setFontColor(new DeviceRgb(255, 255, 255)).setBackgroundColor(new DeviceRgb(2,168,187)));
        tableSecond.addCell(createBorderlessCell("Sum Assured ").setBold().setFontColor(new DeviceRgb(255, 255, 255)).setBackgroundColor(new DeviceRgb(2,168,187)));
        tableSecond.addCell(createBorderlessCell("Plan Term ").setBold().setFontColor(new DeviceRgb(255, 255, 255)).setBackgroundColor(new DeviceRgb(2,168,187)));
        tableSecond.addCell(createBorderlessCell("Total Addon ").setBold().setFontColor(new DeviceRgb(255, 255, 255)).setBackgroundColor(new DeviceRgb(2,168,187)));
        tableSecond.addCell(createBorderlessCell("Final Premium ").setBold().setFontColor(new DeviceRgb(255, 255, 255)).setBackgroundColor(new DeviceRgb(2,168,187)));

        tableSecond.addCell(createBorderlessCell(String.valueOf(policy.getBookingId())).setMarginTop(25));
        tableSecond.addCell(createBorderlessCell(policy.getApplicationNumber()).setMarginTop(5));
        tableSecond.addCell(createBorderlessCell(String.valueOf(policy.getSumAssured())).setMarginTop(5));
        tableSecond.addCell(createBorderlessCell(policy.getPlanTerm()).setMarginTop(5));
        tableSecond.addCell(createBorderlessCell(String.valueOf(policy.getTotalAddon())).setMarginTop(5));
        tableSecond.addCell(createBorderlessCell(String.valueOf(policy.getFinalPremium())).setMarginTop(5));

        document.add(tableSecond.setMarginBottom(20));


        SolidLine line1 = new SolidLine(2f); 
        line1.setColor(new DeviceRgb(128, 128, 128)); 
        line1.setLineWidth(3);
        document.add(new LineSeparator(line1));
        
        
        Table tableThird = new Table(2); // Change to 2 columns
        tableThird.setWidth(UnitValue.createPercentValue(100));
        tableThird.setBorder(Border.NO_BORDER);   

        
        //Image img = new Image(ImageDataFactory.create("D:/SpringPro(12-07-24)/SpringPro/safe-policy/src/main/resources/static/images/safepolicylogo.webp"));
        Image img = new Image(ImageDataFactory.create("D:\\SpringPro(12-07-24)\\SpringPro\\safe-policy\\src\\main\\resources\\static\\images\\logopolicy.jpg"));

            img.setAutoScale(true); 
            img.setWidth(50); // Adjust the width as needed
            img.setHeight(50); // Adjust the height as needed
            img.setBorderRadius(new BorderRadius(10));
            
            Cell imageCell = createBorderlessCell("").setTextAlignment(TextAlignment.CENTER);
            imageCell.add(img);
            tableThird.addCell(imageCell);
       

        // Add text to the second column
        Cell textCell = createBorderlessCell("")
            .add(new Paragraph("Name of the Insurer: Safe Policy")
                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER))
                .setFontSize(10)
                .setMarginTop(20)
                .setMarginBottom(5)
                .setTextAlignment(TextAlignment.RIGHT))
            .add(new Paragraph("Email: safepolicyteam@gmail.com")
                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER))
                .setFontSize(10)
                .setMarginBottom(5)
                .setTextAlignment(TextAlignment.RIGHT))
            .add(new Paragraph("Address: Ahmedabad, Gujarat, India")
                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER))
                .setFontSize(10)
                .setMarginBottom(5)
                .setTextAlignment(TextAlignment.RIGHT))
            .add(new Paragraph("Zip Code: 380058")
                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER))
                .setFontSize(10)
                .setMarginBottom(5)
                .setTextAlignment(TextAlignment.RIGHT));
        tableThird.addCell(textCell);

       
        document.add(tableThird.setMarginBottom(20));
        
        Paragraph thanks = new Paragraph("THANK YOU FOR USING THE SAFE-POLICY")
              .setFont(PdfFontFactory.createFont(StandardFonts.TIMES_BOLDITALIC))
              .setFontSize(10)
              .setBold()
              .setMarginBottom(5)
              .setFontColor(new DeviceRgb(2,168,187))
              .setTextAlignment(TextAlignment.CENTER);
        document.add(thanks);
        
   
     
    }
    
 
  

	private Cell createBorderlessCell(String content) throws IOException {
        return new Cell().add(new Paragraph(content)
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                .setFontSize(12)
                .setMarginBottom(5))
                .setBorder(Border.NO_BORDER);
    }

}
