package com.example.demo.infrastructure;

import com.example.demo.application.UploadService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;


public class UploadServiceImpl implements UploadService {
  private final S3Client s3Client;

  private final String bucketName;
  private final String path;

  public UploadServiceImpl(S3Client s3Client, @Value("${s3.bucket-name}") String bucketName, @Value("${s3.path}") String path) {
    this.s3Client = s3Client;
    this.bucketName = bucketName;
    this.path = path;
  }

  @Override
  public String uploadImage(MultipartFile uploadFile) {
    return null;
  }
}
