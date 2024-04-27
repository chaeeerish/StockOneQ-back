package umc.stockoneqback.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.stockoneqback.comment.domain.model.Comment;
import umc.stockoneqback.comment.domain.repository.CommentRepository;
import umc.stockoneqback.comment.exception.CommentErrorCode;
import umc.stockoneqback.global.exception.BaseException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentFindService {
    private final CommentRepository commentRepository;

    public Comment findById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> BaseException.type(CommentErrorCode.COMMENT_NOT_FOUND));
    }
}
