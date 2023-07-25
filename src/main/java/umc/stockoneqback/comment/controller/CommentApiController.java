package umc.stockoneqback.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.stockoneqback.comment.controller.dto.CommentRequest;
import umc.stockoneqback.comment.service.CommentService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments/{writerId}")
public class CommentApiController {
    private final CommentService commentService;

    @PostMapping("/{boardId}")
    public ResponseEntity<Void> create(@PathVariable Long writerId, @PathVariable Long boardId,
                                       @RequestBody @Valid CommentRequest request) {
        commentService.create(writerId, boardId, request.image(), request.content());
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<Void> update(@PathVariable Long writerId, @PathVariable Long commentId,
                                       @RequestBody @Valid CommentRequest request) {
        commentService.update(writerId, commentId, request.image(), request.content());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> delete(@PathVariable Long writerId, @PathVariable Long commentId) {
        commentService.delete(writerId, commentId);
        return ResponseEntity.ok().build();
    }
}