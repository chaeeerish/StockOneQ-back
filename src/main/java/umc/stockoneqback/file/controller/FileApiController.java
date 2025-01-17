package umc.stockoneqback.file.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.stockoneqback.auth.domain.model.jwt.Authenticated;
import umc.stockoneqback.file.dto.UploadRequest;
import umc.stockoneqback.file.exception.FileErrorCode;
import umc.stockoneqback.file.service.FileService;
import umc.stockoneqback.global.annotation.Auth;
import umc.stockoneqback.global.exception.BaseException;

import java.io.IOException;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/file")
public class FileApiController {
    private final FileService fileService;

    @PostMapping(value = "/upload", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> upload(@Auth Authenticated authenticated, @ModelAttribute @Valid UploadRequest request) {
        String dir = request.dir();
        switch (dir) {
            case "board" -> {
                String uploadFileLink = fileService.uploadBoardFiles(request.file());
                return ResponseEntity.ok(uploadFileLink);
            }
            case "share" -> {
                String uploadFileLink = fileService.uploadShareFiles(request.file());
                return ResponseEntity.ok(uploadFileLink);
            }
            case "product" -> {
                String uploadFileLink = fileService.uploadProductFiles(request.file());
                return ResponseEntity.ok(uploadFileLink);
            }
            case "comment" -> {
                String uploadFileLink = fileService.uploadCommentFiles(request.file());
                return ResponseEntity.ok(uploadFileLink);
            }
            case "reply" -> {
                String uploadFileLink = fileService.uploadReplyFiles(request.file());
                return ResponseEntity.ok(uploadFileLink);
            }
            default -> throw new BaseException(FileErrorCode.INVALID_DIR);
        }
    }

    @GetMapping(value = "/download")
    public ResponseEntity<byte[]> download(@Auth Authenticated authenticated, String fileKey) throws IOException {
        return fileService.download(fileKey);
    }
}
