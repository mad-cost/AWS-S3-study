package com.example.demo.application;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface UploadService {
  // 업로드
  void uploadImage(MultipartFile uploadFile);

  // 다운로드
  InputStream getFile(String fileName);
}
