package com.example.demo.controller;

import com.example.demo.application.UploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images")
public class ImageController {
  private final UploadService uploadService;

  public ImageController(UploadService uploadService) {
    this.uploadService = uploadService;
  }

  @PostMapping
  public ResponseEntity<String> uploadImage(
          @RequestParam("file") MultipartFile file
  ) {
    uploadService.uploadImage(file);
    return ResponseEntity.ok("ok");
  }
}