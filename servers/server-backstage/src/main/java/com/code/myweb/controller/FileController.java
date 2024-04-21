package com.code.myweb.controller;

import com.code.myweb.file.FileService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/myweb/file")
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Value("${define.file.path}")
    private String filePath;

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    @ResponseBody
    public String singleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            if (file == null || StringUtils.isEmpty(file.getOriginalFilename())) {
                return "error";
            }
            fileService.saveFile(file.getBytes(), filePath, file.getOriginalFilename());
        } catch (Exception e) {
            logger.error("",e);
            return "error";
        }
        return "success";
    }

    @RequestMapping("/download")
    public void singleFileUpload(HttpServletResponse response, String filename) throws UnsupportedEncodingException {
        fileService.download(response, filename);
    }

}