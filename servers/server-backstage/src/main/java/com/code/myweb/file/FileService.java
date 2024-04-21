package com.code.myweb.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@Service
@Transactional
public class FileService {
    private static final String UTF_8 = "UTF-8";

    @Value("${define.file.path}")
    private String filePath;

    public void saveFile(byte[] file, String filePath, String fileName) {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        try (FileOutputStream out = new FileOutputStream(filePath + fileName)) {
            out.write(file);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void download(HttpServletResponse response, String filename) throws UnsupportedEncodingException {
        filename = new String((filename).getBytes(), UTF_8);
        response.setContentType("application/force-download");
        response.setCharacterEncoding(UTF_8);
        response.addHeader("Content-disposition", "attachment;fileName=" + URLEncoder.encode(filename, UTF_8));
        try (FileInputStream fis = new FileInputStream(new File(filePath + filename));
             OutputStream os = response.getOutputStream();) {
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = fis.read(buf)) != -1) {
                os.write(buf, 0, len);
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
