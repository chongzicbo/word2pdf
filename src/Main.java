import com.aspose.words.Document;
import com.aspose.words.SaveFormat;

import java.io.FileOutputStream;

public class Main {
    public static void main(String[] args) {
        String docPath="D:\\360MoveData\\Users\\MDPI\\Documents\\format-dentistry-2389996.docx";
        String pdfPath="D:\\360MoveData\\Users\\MDPI\\Documents\\demo1.pdf";
//        String docPath="";
//        String pdfPath="";
//        if (args != null && args.length > 1) {
//            docPath= args[0];
//            pdfPath = args[1];
//        }
//        else {
//            System.out.println("please input word path or pdf path");
//            System.exit(-1);
//        }
        System.out.println("Start convert word to pdf.");

        long startTime = System.currentTimeMillis();
        word2PDF(docPath, pdfPath);
        long endTime = System.currentTimeMillis();

        System.out.println("Competed successfully! Take time " + (endTime - startTime) + "ms");
    }

    private static void word2PDF(String sourceFile,String targetFile){
        try(FileOutputStream outputStream=new FileOutputStream(targetFile)){
            Document doc=new Document(sourceFile);
            doc.save(outputStream, SaveFormat.PDF);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        finally {
//            System.exit(0);
//        }
    }
}