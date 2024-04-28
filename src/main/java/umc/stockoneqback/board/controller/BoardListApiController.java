package umc.stockoneqback.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.stockoneqback.auth.domain.model.jwt.Authenticated;
import umc.stockoneqback.board.dto.response.CustomBoardListResponse;
import umc.stockoneqback.board.dto.response.BoardList;
import umc.stockoneqback.board.service.BoardListService;
import umc.stockoneqback.global.annotation.Auth;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardListApiController {
    private final BoardListService boardListService;

    @GetMapping("")
    public ResponseEntity<CustomBoardListResponse<BoardList>> boardList(@Auth Authenticated authenticated,
                                                                        @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                                        @RequestParam(value = "sort", required = false, defaultValue = "최신순") String sortBy,
                                                                        @RequestParam(value = "search", required = false, defaultValue = "제목") String searchBy,
                                                                        @RequestParam(value = "word", required = false, defaultValue = "") String searchWord) {
        return ResponseEntity.ok(boardListService.getBoardList(authenticated.id(), page, sortBy, searchBy, searchWord));
    }

    @GetMapping("/my")
    public ResponseEntity<CustomBoardListResponse<BoardList>> myBoardList(@Auth Authenticated authenticated,
                                                                          @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                                          @RequestParam(value = "sort", required = false, defaultValue = "최신순") String sortBy,
                                                                          @RequestParam(value = "search", required = false, defaultValue = "제목") String searchBy,
                                                                          @RequestParam(value = "word", required = false, defaultValue = "") String searchWord) {
        return ResponseEntity.ok(boardListService.getMyBoardList(authenticated.id(), page, sortBy, searchBy, searchWord));
    }

    @DeleteMapping("/my")
    public ResponseEntity<Void> deleteMyBoard(@Auth Authenticated authenticated, @RequestParam List<Long> boardId) {
        boardListService.deleteMyBoard(authenticated.id(), boardId);
        return ResponseEntity.ok().build();
    }
}
