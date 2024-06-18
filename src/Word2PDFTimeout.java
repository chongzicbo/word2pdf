import com.aspose.words.Document;
import com.aspose.words.SaveFormat;

import java.io.FileOutputStream;
import java.util.concurrent.*;

/**
 * 添加超时退出
 */
public class Word2PDFTimeout {
    public static void main(String[] args) {
        String docPath = "";
        String pdfPath = "";
        if (args != null && args.length > 1) {
            docPath = args[0];
            pdfPath = args[1];
        } else {
            System.out.println("please input word path or pdf path");
            System.exit(-1);
        }
        final ExecutorService executorService = Executors.newFixedThreadPool(1);
        String finalDocPath = docPath;
        String finalPdfPath = pdfPath;
        Callable<String> call = new Callable<String>() {
            @Override
            public String call() throws Exception {
                word2PDF(finalDocPath, finalPdfPath);
                return "word2pdf successfully!";
            }
        };
        try {
            Future<String> future = executorService.submit(call);
            //任务处理超时时间设为 1 秒
            String obj = future.get(1000 * 60, TimeUnit.MILLISECONDS);
            System.out.println("任务成功返回:" + obj);
        } catch (TimeoutException ex) {
            System.out.println("处理超时啦....");
            ex.printStackTrace();
            System.exit(-1);
        } catch (Exception e) {
            System.out.println("处理失败.");
            e.printStackTrace();
            System.exit(-1);
        }
        // 关闭线程池
        executorService.shutdown();
    }

    private static void word2PDF(String sourceFile, String targetFile) {
        try (FileOutputStream outputStream = new FileOutputStream(targetFile)) {
            Document doc = new Document(sourceFile);
            doc.save(outputStream, SaveFormat.PDF);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
