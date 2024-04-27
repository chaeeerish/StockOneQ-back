package umc.stockoneqback.board.controller.like;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import umc.stockoneqback.auth.domain.model.jwt.Authenticated;
import umc.stockoneqback.board.service.like.BoardLikeService;
import umc.stockoneqback.global.annotation.Auth;
import umc.stockoneqback.global.annotation.ExtractPayload;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards/{boardId}/likes")
public class BoardLikeApiController {
    private final BoardLikeService boardLikeService;

    @PostMapping
    public ResponseEntity<Void> register(@Auth Authenticated authenticated, @PathVariable Long boardId) {
        boardLikeService.register(authenticated.id(), boardId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> cancel(@Auth Authenticated authenticated, @PathVariable Long boardId) {
        boardLikeService.cancel(authenticated.id(), boardId);
        return ResponseEntity.ok().build();
    }
}

