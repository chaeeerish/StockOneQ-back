package umc.stockoneqback.share.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.stockoneqback.auth.domain.model.jwt.Authenticated;
import umc.stockoneqback.business.dto.FilteredBusinessUser;
import umc.stockoneqback.global.annotation.Auth;
import umc.stockoneqback.share.dto.response.CustomShareListPage;
import umc.stockoneqback.share.service.ShareListService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/share")
public class ShareListApiController {
    private final ShareListService shareListService;

    @PreAuthorize("hasAnyRole('MANAGER', 'PART_TIMER', 'SUPERVISOR')")
    @GetMapping("/users")
    public ResponseEntity<FilteredBusinessUser> userSelectBox(@Auth Authenticated authenticated) {
        return ResponseEntity.ok(shareListService.userSelectBox(authenticated.id()));
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'PART_TIMER', 'SUPERVISOR')")
    @GetMapping("")
    public ResponseEntity<CustomShareListPage> shareList(@Auth Authenticated authenticated,
                                                         @RequestParam(value = "user") Long userBusinessId,
                                                         @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                         @RequestParam(value = "category", required = false, defaultValue = "공지사항") String category,
                                                         @RequestParam(value = "search", required = false, defaultValue = "제목") String searchType,
                                                         @RequestParam(value = "word", required = false, defaultValue = "") String searchWord) throws IOException {
        return ResponseEntity.ok(shareListService.getShareList(authenticated.id(), userBusinessId, page, category, searchType, searchWord));
    }
}
