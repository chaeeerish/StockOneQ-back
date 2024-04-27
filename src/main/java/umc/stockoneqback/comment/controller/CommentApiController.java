package umc.stockoneqback.comment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import umc.stockoneqback.auth.domain.model.jwt.Authenticated;
import umc.stockoneqback.comment.dto.request.CommentRequest;
import umc.stockoneqback.comment.service.CommentService;
import umc.stockoneqback.global.annotation.Auth;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentApiController {
    private final CommentService commentService;

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/{boardId}")
    public ResponseEntity<Void> create(@Auth Authenticated authenticated, @PathVariable Long boardId,
                                       @RequestPart(value = "request")  @Valid CommentRequest request,
                                       @RequestPart(value = "image", required = false) MultipartFile multipartFile) {
        commentService.create(authenticated.id(), boardId, multipartFile, request.content());
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PatchMapping("/{commentId}")
    public ResponseEntity<Void> update(@Auth Authenticated authenticated, @PathVariable Long commentId,
                                       @RequestPart(value = "request")  @Valid CommentRequest request,
                                       @RequestPart(value = "image", required = false) MultipartFile multipartFile) {
        commentService.update(authenticated.id(), commentId, multipartFile, request.content());
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> delete(@Auth Authenticated authenticated, @PathVariable Long commentId) {
        commentService.delete(authenticated.id(), commentId);
        return ResponseEntity.ok().build();
    }
}
