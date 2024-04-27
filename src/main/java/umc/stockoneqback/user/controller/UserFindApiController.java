package umc.stockoneqback.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.stockoneqback.auth.domain.model.jwt.Authenticated;
import umc.stockoneqback.global.annotation.Auth;
import umc.stockoneqback.user.service.UserFindService;
import umc.stockoneqback.user.service.dto.response.FindManagerResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserFindApiController {
    private final UserFindService userFindService;

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/friend")
    public ResponseEntity<FindManagerResponse> findFriendManagers(@Auth Authenticated authenticated,
                                                                  @RequestParam(value = "last", required = false, defaultValue = "-1") Long lastUserId,
                                                                  @RequestParam(value = "search", required = false, defaultValue = "이름") String searchType,
                                                                  @RequestParam(value = "word") String searchWord) {
        return ResponseEntity.ok(userFindService.findFriendManagers(authenticated.id(), lastUserId, searchType, searchWord));
    }

    @PreAuthorize("hasRole('SUPERVISOR')")
    @GetMapping("/business")
    public ResponseEntity<FindManagerResponse> findBusinessManagers(@Auth Authenticated authenticated,
                                                                    @RequestParam(value = "last", required = false, defaultValue = "-1") Long lastUserId,
                                                                    @RequestParam(value = "search", required = false, defaultValue = "이름") String searchType,
                                                                    @RequestParam(value = "word") String searchWord) {
        return ResponseEntity.ok(userFindService.findBusinessManagers(authenticated.id(), lastUserId, searchType, searchWord));
    }
}
