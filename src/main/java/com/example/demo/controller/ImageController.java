package com.example.demo.controller;

import com.example.demo.application.UploadService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@RestController
@RequestMapping("/images")
public class ImageController {
  private final UploadService uploadService;

  public ImageController(UploadService uploadService) {
    this.uploadService = uploadService;
  }

  // S3에 파일 업로드
  @PostMapping
  public ResponseEntity<String> uploadImage(
          @RequestParam("file") MultipartFile file
  ) {
    uploadService.uploadImage(file);
    return ResponseEntity.ok("ok");
  }

  // S3에서 파일 다운로드
  @GetMapping
  public ResponseEntity<InputStreamResource> getImage(@RequestParam(value = "file") String fileName) {
    InputStream imageStream = uploadService.getFile(fileName);

    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
            .contentType(MediaType.IMAGE_PNG) // 혹은 IMAGE_JPEG
            .body(new InputStreamResource(imageStream)); // imageStream을 InputStreamResource으로 감싸서 반환
  }
}