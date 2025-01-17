package umc.stockoneqback.reply.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import umc.stockoneqback.auth.domain.model.jwt.Authenticated;
import umc.stockoneqback.global.annotation.Auth;
import umc.stockoneqback.reply.dto.request.ReplyRequest;
import umc.stockoneqback.reply.service.ReplyService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/replies")
public class ReplyApiController {
    private final ReplyService replyService;

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/{commentId}")
    public ResponseEntity<Void> create(@Auth Authenticated authenticated, @PathVariable Long commentId,
                                       @RequestPart(value = "request")  @Valid ReplyRequest request,
                                       @RequestPart(value = "image", required = false) MultipartFile multipartFile) {
        replyService.create(authenticated.id(), commentId, multipartFile, request.content());
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PatchMapping("/{replyId}")
    public ResponseEntity<Void> update(@Auth Authenticated authenticated, @PathVariable Long replyId,
                                       @RequestPart(value = "request")  @Valid ReplyRequest request,
                                       @RequestPart(value = "image", required = false) MultipartFile multipartFile) {
        replyService.update(authenticated.id(), replyId, multipartFile, request.content());
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("/{replyId}")
    public ResponseEntity<Void> delete(@Auth Authenticated authenticated, @PathVariable Long replyId) {
        replyService.delete(authenticated.id(), replyId);
        return ResponseEntity.ok().build();
    }
}
