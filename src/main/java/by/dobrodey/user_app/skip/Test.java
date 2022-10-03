package by.dobrodey.user_app.skip;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.dobrodey.sender.model.ID;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws IOException {
        String url = "http://ip.jsontest.com/";
        URL url1 = new URL(url);
        final ID id = get(url1, ID.class);
        System.out.println(id);


        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");

        PDDocument document = new PDDocument();
        PDPage firstPage = new PDPage(PDRectangle.A4);
        document.addPage(firstPage);

        String name = "Olga Dobrodey";
        String callNo = "11111";

        Format d_format = new SimpleDateFormat("dd/MM/yyyy");
        Format t_format = new SimpleDateFormat("HH:mm");

        int pageWidth = (int) firstPage.getTrimBox().getWidth();
        int pageHeight = (int) firstPage.getTrimBox().getHeight();

        PDPageContentStream contentStream = new PDPageContentStream(document, firstPage);
        MyTextClass myTextClass = new MyTextClass(document, contentStream);

        PDFont font = PDType1Font.HELVETICA;
        PDFont italicFont = PDType1Font.HELVETICA_OBLIQUE;

        PDImageXObject headImage = PDImageXObject.createFromFile("src/main/resources/img/1.jpg", document);
        contentStream.drawImage(headImage, 0, pageHeight - 235, pageWidth, 239);

        String[] contactDetails = new String[]{"22222222", "333333333"};
        myTextClass.addMultiLineText(contactDetails, 18, (int) (pageWidth - font.getStringWidth("grgrg") / 1000 * 15 - 10),
                pageHeight - 25, font, 15, Color.BLACK);
        myTextClass.addSingleLineText("OLGA TRA L", 25, pageHeight - 150, font, 40, Color.BLACK);
        myTextClass.addSingleLineText("CUstemer name " + "ELENA", 25, pageHeight - 250, font, 16, Color.BLACK);
        myTextClass.addSingleLineText("Mo. No" + "xtuj", 25, pageHeight - 274, font, 16, Color.BLACK);

        String invoiceNo = "OLGA WERTYUI";
        float textWidth = myTextClass.getTextWidth(invoiceNo, font, 16);

        myTextClass.addSingleLineText(invoiceNo, (int) (pageWidth - 25 - textWidth),
                pageHeight - 250, font, 16, Color.BLACK);

        float dateTextWidth = myTextClass.getTextWidth("Date: " + d_format.format(new Date()), font, 16);
        myTextClass.addSingleLineText("Date: " + d_format.format(new Date()), (int) (pageWidth - 25 - dateTextWidth), pageHeight - 274, font, 16, Color.BLACK);

        String time = t_format.format(new Date());
        float timeTextWidth = myTextClass.getTextWidth("time: " + time, font, 16);
        myTextClass.addSingleLineText("Time: " + time, (int) (pageWidth - 25 - timeTextWidth),
                pageHeight - 298, font, 16, Color.BLACK);

        MyTableClass myTable = new MyTableClass(document, contentStream);
        int[] cellWidths = {70, 160, 120, 90, 100};
        myTable.setTable(cellWidths, 30, 25, pageHeight - 350);
        myTable.setTableFont(font, 16, Color.BLACK);

        Color tableHeadColor = new Color(240, 93, 11);
        Color tableBodyColor = new Color(219, 218, 198);

        myTable.addCall("C1", tableHeadColor);
        myTable.addCall("C2", tableHeadColor);
        myTable.addCall("C3", tableHeadColor);
        myTable.addCall("C4", tableHeadColor);
        myTable.addCall("C5", tableHeadColor);

        myTable.addCall("C1", tableBodyColor);
        myTable.addCall("C2", tableBodyColor);
        myTable.addCall("C3", tableBodyColor);
        myTable.addCall("C4", tableBodyColor);
        myTable.addCall("C5", tableBodyColor);

        myTable.addCall("C1", tableBodyColor);
        myTable.addCall("C2", tableBodyColor);
        myTable.addCall("C3", tableBodyColor);
        myTable.addCall("C4", tableBodyColor);
        myTable.addCall("C5", tableBodyColor);

        myTable.addCall("", null);
        myTable.addCall("", null);
        myTable.addCall("", null);
        myTable.addCall("", null);
        myTable.addCall("C5", tableBodyColor);

        String[] paymentMethod = {"Here you can find activities to practise your reading skills."," Reading will help you to improve your understanding"," of the language and build your vocabulary."};
        myTextClass.addMultiLineText(paymentMethod, 15,25,180,italicFont,10, new Color(122,122,122));


        contentStream.setStrokingColor(Color.BLUE);
        contentStream.setLineWidth(2);
        contentStream.moveTo(pageWidth-250, 150);
        contentStream.lineTo(pageWidth-25, 150);
        contentStream.stroke();

        String autoSing = "Authorised S";
        float authoSighWith = myTextClass.getTextWidth(autoSing,italicFont,16);



        contentStream.close();


        document.save("D:\\olga.pdf");
        document.close();
        System.out.println("PDF CREATE");
    }

    public static <T> T get(URL url, Class<T> type) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(url, type);
    }

    private static class MyTextClass {
        PDDocument document;
        PDPageContentStream contentStream;

        public MyTextClass(PDDocument document, PDPageContentStream contentStream) {
            this.document = document;
            this.contentStream = contentStream;
        }

        void addSingleLineText(String text, int xPosition, int yPosition, PDFont font, float fontSize, Color color) throws IOException {
            contentStream.beginText();
            contentStream.setFont(font, fontSize);
            contentStream.setNonStrokingColor(color);
            contentStream.newLineAtOffset(xPosition, yPosition);
            contentStream.showText(text);
            contentStream.endText();
            contentStream.moveTo(0, 0);
        }

        void addMultiLineText(String[] textArray, float leading, int xPosition, int yPosition, PDFont font, float fontSize, Color color) throws IOException {
            contentStream.beginText();
            contentStream.setFont(font, fontSize);
            contentStream.setNonStrokingColor(color);
            contentStream.setLeading(leading);
            contentStream.newLineAtOffset(xPosition, yPosition);

            for (String text : textArray) {
                contentStream.showText(text);
                contentStream.newLine();
            }
            contentStream.endText();
            contentStream.moveTo(0, 0);
        }

        float getTextWidth(String text, PDFont font, float fontSize) throws IOException {
            return font.getStringWidth(text) / 1000 * fontSize;
        }
    }

    private static class MyTableClass {
        PDDocument document;
        PDPageContentStream contentStream;
        private int[] colWidths;
        private int cellHeight;
        private int yPosition;
        private int xPosition;
        private int colPosition = 0;
        private int xInitialPosition;
        private float frontSize;
        private PDFont font;
        private Color fontColor;

        public MyTableClass(PDDocument document, PDPageContentStream contentStream) {
            this.document = document;
            this.contentStream = contentStream;
        }

        void setTable(int[] colWidths, int cellHeight, int xPosition, int yPosition) {
            this.colWidths = colWidths;
            this.cellHeight = cellHeight;
            this.xPosition = xPosition;
            this.yPosition = yPosition;
            xInitialPosition = xPosition;
        }

        void setTableFont(PDFont font, float frontSize, Color fontColor) {
            this.font = font;
            this.frontSize = frontSize;
            this.fontColor = fontColor;
        }

        void addCall(String text, Color fillColor) throws IOException {
            contentStream.setStrokingColor(1f);

            if (fillColor != null) {
                contentStream.setNonStrokingColor(fillColor);
            }
            contentStream.addRect(xPosition, yPosition, colWidths[colPosition], cellHeight);
            if (fillColor == null) {
                contentStream.stroke();
            } else contentStream.fillAndStroke();
            contentStream.beginText();
            contentStream.setNonStrokingColor(fontColor);

            if (colPosition == 4 || colPosition == 2) {
                float fontWidth = font.getStringWidth(text) / 1000 * frontSize;
                contentStream.newLineAtOffset(xPosition + colWidths[colPosition] - 20 - fontWidth, yPosition + 10);

            } else {
                 contentStream.newLineAtOffset(xPosition + 20, yPosition + 10);
            }
            contentStream.showText(text);
            contentStream.endText();

            xPosition = xPosition + colWidths[colPosition];
            colPosition++;

            if (colPosition == colWidths.length) {
                colPosition = 0;
                xPosition = xInitialPosition;
                yPosition -= cellHeight;
            }
        }
    }
}

