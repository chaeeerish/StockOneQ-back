package umc.stockoneqback.board.infra.query;

import umc.stockoneqback.board.domain.BoardSearchType;
import umc.stockoneqback.board.dto.response.BoardList;
import umc.stockoneqback.board.dto.response.CustomBoardListResponse;

public interface BoardListQueryRepository {
    CustomBoardListResponse<BoardList> getBoardListOrderByTime(BoardSearchType boardSearchType, String searchWord, int page);
    CustomBoardListResponse<BoardList> getBoardListOrderByHit(BoardSearchType boardSearchType, String searchWord, int page);
    CustomBoardListResponse<BoardList> getMyBoardListOrderByTime(Long userId, BoardSearchType boardSearchType, String searchWord, int page);
    CustomBoardListResponse<BoardList> getMyBoardListOrderByHit(Long userId, BoardSearchType boardSearchType, String searchWord, int page);
}
