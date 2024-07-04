package com.example.blog_app.services.impl;

import com.example.blog_app.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();


        String randomId = UUID.randomUUID().toString();
        String completeFileName = randomId.concat(name.substring(name.lastIndexOf(".")));

        String filePath = path + File.separator + completeFileName;

        //create folder if not created
        File folder = new File(path);
        if(!folder.exists())
            folder.mkdir();

        Files.copy(file.getInputStream(), Paths.get(filePath));

        return completeFileName;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path+File.separator+fileName;
        InputStream isr = new FileInputStream(fullPath);

        return isr;
    }
}
