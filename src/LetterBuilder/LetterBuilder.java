/**
 *
 * @author Cornell Hall III
 */
package LetterBuilder;

import static LetterBuilder.CustomerList.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import junit.framework.Test;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;




public class LetterBuilder {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
     */
    static String confirmation = "Customer Letters Written";
    static String path;
    static String decodedPath;
    static String customerFile;
    
    
public static void main(String[] args) throws IOException, InvalidFormatException { 
    
    path = Test.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    decodedPath = URLDecoder.decode(path.substring(1, path.lastIndexOf("/")+1), "UTF-8");
    customerFile = decodedPath + "Resources/customers.csv";
       
    String letterFile = decodedPath+ "Resources/letter.docx";
        System.out.println(customerFile + "= CustomerFile");
    System.out.println(decodedPath);
    JOptionPane.showMessageDialog(null, "Loading...Please wait");
    //Test functions
    try {
    //Load file data into list
        CustomerList list = new CustomerList();
        list.loadFile();
        
        } 
    catch (FileNotFoundException ex) {
        Logger.getLogger(LetterBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        cArray.forEach((item)->{
            String Customer = item.getfName() +" "+ item.getlName() ; 
            
            try {            
                LetterBuilder lb = new LetterBuilder();
                System.out.println(decodedPath);
                lb.readAndWriteFile(letterFile, Customer);
            } catch (IOException | InvalidFormatException ex) {
                Logger.getLogger(LetterBuilder.class.getName()).log(Level.SEVERE, null, ex);
            } 
        });//End forEach
        
        //Show confirmation window for Successful write
        JOptionPane.showMessageDialog(null, confirmation);
    }

@SuppressWarnings("ConvertToTryWithResources")
public void readAndWriteFile(String fileIn, String Customer)throws IOException, InvalidFormatException{         
        String fileOut= "Letters/"+ Customer +" Letter.docx"; 
        String path = Test.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String decodedPath = URLDecoder.decode(path.substring(1, path.lastIndexOf("/")+1), "UTF-8");
// This will reference one line at a time
               
        try {
            //Open fileReader to read text file
           // FileReader fileReader = new FileReader(fileIn);
            
         //Word file Read
            XWPFDocument docx = new XWPFDocument(new FileInputStream(fileIn));
            XWPFWordExtractor we =new XWPFWordExtractor(docx);
            XWPFDocument document = new XWPFDocument();
            
                System.out.println("");
                System.out.println("");
         //   XWPFParagraph paragraph = document.createParagraph();
         //   XWPFRun run =paragraph.createRun();
         //   run.setText(we.getText());
         //   document.write(out);      

        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(document, sectPr);
        XWPFHeader header = headerFooterPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT);
        //Insert Image for Title....
        XWPFParagraph title = header.createParagraph();
        XWPFRun run = title.createRun();
        title.setAlignment(ParagraphAlignment.CENTER);
        String image= decodedPath+"Resources/header.jpg";
        FileInputStream imageIn = new FileInputStream(image);
    //    InputStream is = this.getClass().getResourceAsStream(image);      
        run.addPicture(imageIn, XWPFDocument.PICTURE_TYPE_JPEG, image,Units.toEMU(470), Units.toEMU(72)); // 600x200 pixels
    //  run.addBreak();
    //Insert Customer Name
        XWPFParagraph intro = document.createParagraph();
        XWPFRun introRun = intro.createRun();
        introRun.setBold(true);
        introRun.setText("Dear "+Customer+",\n");
        
    //StringTokenizer for seperating Paragraphs    
        StringTokenizer para;
        int Num = 0;
        String input = we.getText();
        para = new StringTokenizer(input, "[]");
        int count=0;    
    
    //Insert Text from reference file
        while(para.hasMoreTokens()){
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run1 = paragraph.createRun();
            run1.setBold(true);
            run1.setText(para.nextToken());
            count++;
        }
        //Open Word file to Write
        String fileOutLocation = decodedPath+fileOut;
        FileOutputStream out = new FileOutputStream(new File(fileOutLocation));
        System.out.println("Writing...4");
        document.write(out);
     //   is.close();
        out.close();
            //Confirm Write Successful to User
        confirmation = confirmation.concat("\n"+Customer+" Letter Successful");
        System.out.println("*** "+Customer+" Write Successful***");
        }
        catch(FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Unable to open file '" + fileIn + "'");
            System.out.println("Unable to open file '" + 
                fileIn + "'");                
        }
        catch(IOException ex) {
            JOptionPane.showMessageDialog(null, "Error reading file '" + fileIn + "'");
            System.out.println("Error reading file '" + fileIn + "'");                  
        }
}//End of readAndWriteFile
         
}//End of Class 


