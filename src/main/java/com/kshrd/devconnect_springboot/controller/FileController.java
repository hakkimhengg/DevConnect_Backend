package com.kshrd.devconnect_springboot.controller;

import com.kshrd.devconnect_springboot.base.ApiResponse;
import com.kshrd.devconnect_springboot.base.BaseController;
import com.kshrd.devconnect_springboot.model.entity.FileMetadata;
import com.kshrd.devconnect_springboot.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("api/v1/files")
@RequiredArgsConstructor
public class FileController extends BaseController {

    private final FileService fileService;

    @PostMapping(value = "/upload-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> uploadFile(@RequestParam MultipartFile file){
        return response(ApiResponse.builder()
                .success(true)
                .message("Upload file successfully!")
                .status(HttpStatus.CREATED)
                .payload(fileService.fileUpload(file))
                .build());
    }

    @GetMapping("/preview-file/{file-name}")
    public ResponseEntity<?> getFileByFileName(@PathVariable("file-name") String fileName) throws IOException {
        InputStream inputStream = fileService.getFileByFileName(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .body(inputStream.readAllBytes());
    }

}
