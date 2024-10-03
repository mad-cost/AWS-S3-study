package com.example.demo.application;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
  String uploadImage(MultipartFile uploadFile);
}
