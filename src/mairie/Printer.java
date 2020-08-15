package mairie;

import com.pdftron.pdf.*;
import com.pdftron.sdf.SDFDoc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Printer {

    public static void main(String[] args) {
        HashMap<String,String> map = new HashMap<>();
        map.put("NOM","Soulé");
        map.put("PRENOMS","Arémou Malick Aziz");
        Printer.generatePDF("test", map);
    }

    public static void generatePDF(String name, HashMap<String, String> arguments) {

        String input_path = "src/";

        // The first step in every application using PDFNet is to initialize the
        // library and set the path to common PDF resources. The library is usually
        // initialized only once, but calling Initialize() multiple times is also fine.
        PDFNet.initialize();

        //--------------------------------------------------------------------------------
        // Example 1) Update a business card template with personalized info

        try {
            PDFDoc doc = new PDFDoc(input_path + "extrait.pdf");
            doc.initSecurityHandler();

            ContentReplacer replacer = new ContentReplacer();
            Page page = doc.getPage(1);

            //***************************************************
            // first, replace the image on the first page
            //Image img = Image.create(doc, input_path + "peppers.jpg");
            //replacer.addImage(page.getMediaBox(), img.getSDFObj());
            //***************************************************

            for (Map.Entry<String,String> entry: arguments.entrySet()) {
                replacer.addString(entry.getKey(),entry.getValue());
            }

            // finally, apply
            replacer.process(page);

            doc.save("extraits/"+name+".pdf", SDFDoc.SaveMode.REMOVE_UNUSED, null);
            // output PDF doc
            doc.close();


            System.out.println("Save at extraits/"+name+".pdf");

            ProcessBuilder builder = new ProcessBuilder();
            builder.command("evince","extraits/"+name+".pdf");
            try {
                builder.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*PdfReader reader = null;

        try {
            reader = new PdfReader("extraits/"+name+".pdf");
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("extraits/"+name+"x.pdf"));
            stamper.getWriter().setPdfVersion(PdfWriter.PDF_VERSION_1_4);
            stamper.close();
            reader.close();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }*/

    }

}
