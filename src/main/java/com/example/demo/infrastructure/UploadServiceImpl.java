package com.example.demo.infrastructure;

import com.example.demo.application.UploadService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.InputStream;

@Service
public class UploadServiceImpl implements UploadService {
  private final S3Client s3Client;

  private final String bucketName;
  private final String path;

  public UploadServiceImpl(S3Client s3Client, @Value("${s3.bucket-name}") String bucketName, @Value("${s3.path}") String path) {
    this.s3Client = s3Client;
    this.bucketName = bucketName;
    this.path = path;
  }

  // S3에 파일 업로드
  @Override
  @SneakyThrows /// 1. Exception 제거해주는 애너테이션
  public String uploadImage(MultipartFile uploadFile) {
    // images/{파일명} 형태로 저장
    //String uploadUrl = path + "/" + uploadFile.getOriginalFilename(); -> 중복 코드 제거
    String uploadUrl = getS3UploadUrl(uploadFile.getOriginalFilename());
    PutObjectRequest request = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(uploadUrl)
            .build();

    RequestBody content = RequestBody.fromInputStream(
            uploadFile.getInputStream(), // 1. Exception 발생
            uploadFile.getSize()
    );

    s3Client.putObject(request, content);
    return uploadUrl;
  }

  // S3에서 파일 다운로드
  @Override
  public InputStream getFile(String fileName) {
    GetObjectRequest request = GetObjectRequest.builder()
            .bucket(bucketName)
            .key(getS3UploadUrl(fileName))
            .build();
    return s3Client.getObject(request);
  }

  private String getS3UploadUrl(String fileName) {
    // images/docker.png
    return path + "/" + fileName;
  }

}
