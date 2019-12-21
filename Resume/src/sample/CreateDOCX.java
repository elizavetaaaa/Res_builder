package sample;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.*;
import javax.imageio.ImageIO;

public class CreateDOCX {

    public static void main(String[] args){
    }
   public  void creatDOCX(String name, String surname, String email,
                          String companyName1,String workDone1,String companyName2, String workDone2,
                          String skill1,String skill2,String skill3,String skill4,String imagePath,String companyName3,String workDone3) {
        try {

            XWPFDocument document = new XWPFDocument();

            FileOutputStream out = new FileOutputStream(new File("/home/elizaveta/Desktop/" +name + surname +".docx"));
            System.out.println("the path was found");

            XWPFParagraph paragraph = document.createParagraph();
            paragraph.setAlignment(ParagraphAlignment.CENTER);
            paragraph.setBorderBottom(Borders.BASIC_THIN_LINES);
            paragraph.setSpacingAfter(300);
            XWPFRun run = paragraph.createRun();
            run.setFontSize(20);
            run.setBold(true);
            run.setText("Curriculum Vitae");
            run.addBreak();
            run.addBreak();


            XWPFRun r = paragraph.createRun();
            int width1 = 100;
            int height1 = 120;
            String imgFile1 = imagePath;
            int imgFormat1 = getImageFormat(imgFile1);
            r.addPicture(new FileInputStream(imgFile1), imgFormat1, imgFile1, Units.toEMU(width1), Units.toEMU(height1));
            r.addBreak();
            r.addBreak();


            //XWPFParagraph paragraph2 = document.createParagraph();

           // paragraph2.setSpacingAfter(300);
            //add data for name
            XWPFRun run2 = paragraph.createRun();
            run2.setFontSize(15);
            run2.setBold(true);
            //run2.setVerticalAlignment("");
            run2.setText("Name :  ");

            XWPFRun run3 = paragraph.createRun();
            run3.setFontSize(15);
            run3.setText(name);
            run3.addBreak();
            run3.addBreak();

            //add data for surname
            XWPFRun run4 = paragraph.createRun();
            run4.setFontSize(15);
            run4.setBold(true);
            run4.setText("Surname: ");
            XWPFRun run5 = paragraph.createRun();
            run5.setFontSize(15);
            run5.setText(surname);
            run5.addBreak();
            run5.addBreak();

            //add data for email
            XWPFRun run6 = paragraph.createRun();
            run6.setFontSize(15);
            run6.setBold(true);
            run6.setText("Email: ");
            XWPFRun run7 = paragraph.createRun();
            run7.setFontSize(15);
            run7.setText(email);
            run7.addBreak();
            run7.addBreak();


            //add data to working experience part
            XWPFParagraph paragraph3 = document.createParagraph();
            paragraph3.setSpacingAfter(500);
            XWPFRun run8 = paragraph3.createRun();
            run8.setFontSize(15);
            run8.setBold(true);
            run8.setText("Work experience: ");
            run8.addBreak();
            run8.addBreak();

            //company1 and work1
            XWPFRun run9 = paragraph3.createRun();
            run9.setFontSize(15);
            run9.setItalic(true);
            run9.setBold(true);

            if(!companyName1.equals("")){
                run9.setText("1. " +companyName1);}
            run9.addBreak();
            run9.addBreak();

            XWPFRun runW1 = paragraph3.createRun();
            runW1.setFontSize(15);
            runW1.setText(workDone1);
            runW1.addBreak();
            runW1.addBreak();
            //company2 and work2
            XWPFRun run10 = paragraph3.createRun();
            run10.setFontSize(15);
            run10.setItalic(true);
            run10.setBold(true);

            if(!companyName2.equals("")){
                run10.setText("2. " +companyName2);}
            run10.addBreak();
            run10.addBreak();

            XWPFRun runW2 = paragraph3.createRun();
            runW2.setFontSize(15);
            runW2.setText(workDone2);
            runW2.addBreak();
            runW2.addBreak();

            //company3 and work3
            XWPFRun runC3 = paragraph3.createRun();
            runC3.setFontSize(15);
            runC3.setItalic(true);
            runC3.setBold(true);

            if(!companyName3.equals("")){
                runC3.setText("3. " +companyName3);}
            runC3.addBreak();
            runC3.addBreak();

            XWPFRun runW3 = paragraph3.createRun();
            runW3.setFontSize(15);
            runW3.setText(workDone3);
            runW3.addBreak();


            //add data to skills part
            XWPFParagraph paragraph4 = document.createParagraph();
            paragraph4.setSpacingAfter(300);


            //personal skills
            XWPFRun run12 = paragraph4.createRun();
            run12.setFontSize(15);
            run12.setBold(true);
            run12.setText("Personal skills:  ");
            run12.addBreak();
            run12.addBreak();
            XWPFRun run13 = paragraph4.createRun();
            run13.setFontSize(15);
            run13.setText(skill1);
            run13.addBreak();
            run13.addBreak();

            //physical skills
            XWPFRun run14 = paragraph4.createRun();
            run14.setFontSize(15);
            run14.setBold(true);
            run14.setText("Technical skills:  ");
            run14.addBreak();
            run14.addBreak();
            XWPFRun run15 = paragraph4.createRun();
            run15.setFontSize(15);
            run15.setText(skill2);
            run15.addBreak();
            run15.addBreak();

            //languages
            XWPFRun run16 = paragraph4.createRun();
            run16.setFontSize(15);
            run16.setBold(true);
            run16.setText("Languages:  ");
            run16.addBreak();
            run16.addBreak();
            XWPFRun run17 = paragraph4.createRun();
            run17.setFontSize(15);
            run17.setText(skill3);
            run17.addBreak();
            run17.addBreak();


            //special skills
            XWPFRun run18 = paragraph4.createRun();
            run18.setFontSize(15);
            run18.setBold(true);
            run18.setText("Interests (additional): ");
            run18.addBreak();
            run18.addBreak();
            XWPFRun run19 = paragraph4.createRun();
            run19.setFontSize(15);
            run19.setText(skill4);
            run19.addBreak();
            run19.addBreak();






            System.out.println("data to document was added");

            document.write(out);
            out.close();
            System.out.println( name + surname + ".docx written successfully");
        }

        catch (Exception e) {
            System.out.println("something wrong");

        }}



    private static int getImageFormat(String imgFileName) {
        int format;
        if (imgFileName.endsWith(".emf"))
            format = XWPFDocument.PICTURE_TYPE_EMF;
        else if (imgFileName.endsWith(".wmf"))
            format = XWPFDocument.PICTURE_TYPE_WMF;
        else if (imgFileName.endsWith(".pict"))
            format = XWPFDocument.PICTURE_TYPE_PICT;
        else if (imgFileName.endsWith(".jpeg") || imgFileName.endsWith(".jpg"))
            format = XWPFDocument.PICTURE_TYPE_JPEG;
        else if (imgFileName.endsWith(".png"))
            format = XWPFDocument.PICTURE_TYPE_PNG;
        else if (imgFileName.endsWith(".dib"))
            format = XWPFDocument.PICTURE_TYPE_DIB;
        else if (imgFileName.endsWith(".gif"))
            format = XWPFDocument.PICTURE_TYPE_GIF;
        else if (imgFileName.endsWith(".tiff"))
            format = XWPFDocument.PICTURE_TYPE_TIFF;
        else if (imgFileName.endsWith(".eps"))
            format = XWPFDocument.PICTURE_TYPE_EPS;
        else if (imgFileName.endsWith(".bmp"))
            format = XWPFDocument.PICTURE_TYPE_BMP;
        else if (imgFileName.endsWith(".wpg"))
            format = XWPFDocument.PICTURE_TYPE_WPG;
        else {
            return 0;
        }
        return format;
    }







}



