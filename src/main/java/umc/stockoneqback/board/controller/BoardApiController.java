package umc.stockoneqback.board.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import umc.stockoneqback.auth.domain.model.jwt.Authenticated;
import umc.stockoneqback.board.dto.request.BoardRequest;
import umc.stockoneqback.board.dto.response.BoardResponse;
import umc.stockoneqback.board.service.BoardService;
import umc.stockoneqback.global.annotation.Auth;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardApiController {
    private final BoardService boardService;

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("")
    public ResponseEntity<Void> create(@Auth Authenticated authenticated,
                                       @RequestBody @Valid BoardRequest request) {
        Long boardId = boardService.create(authenticated.id(), request.title(), request.content());
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PatchMapping("/{boardId}")
    public ResponseEntity<Void> update(@Auth Authenticated authenticated, @PathVariable Long boardId,
                                       @RequestBody @Valid BoardRequest request) {
        boardService.update(authenticated.id(), boardId, request.title(), request.content());
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/{boardId}")
    public ResponseEntity<BoardResponse> loadBoard(@Auth Authenticated authenticated, @PathVariable Long boardId) {
        return ResponseEntity.ok(boardService.loadBoard(authenticated.id(), boardId));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping("/{boardId}/hit")
    public ResponseEntity<Void> updateHit(@Auth Authenticated authenticated, @PathVariable Long boardId) {
        boardService.updateHit(authenticated.id(), boardId);
        return ResponseEntity.ok().build();
    }
}
