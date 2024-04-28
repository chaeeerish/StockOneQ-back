package umc.stockoneqback.friend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.stockoneqback.auth.domain.model.jwt.Authenticated;
import umc.stockoneqback.friend.dto.response.FriendAssembler;
import umc.stockoneqback.friend.service.FriendInformationService;
import umc.stockoneqback.global.annotation.Auth;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/friends")
public class FriendInformationController {
    private final FriendInformationService friendInformationService;

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("")
    public ResponseEntity<FriendAssembler> getFriends(@Auth Authenticated authenticated,
                                                      @RequestParam(value = "last", required = false, defaultValue = "-1") Long lastUserId) {
        FriendAssembler response = friendInformationService.getFriends(authenticated.id(), lastUserId);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/waiting")
    public ResponseEntity<FriendAssembler> getWaitingFriends(@Auth Authenticated authenticated,
                                                           @RequestParam(value = "last", required = false, defaultValue = "-1") Long lastUserId) {
        FriendAssembler response = friendInformationService.getWaitingFriends(authenticated.id(), lastUserId);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/requested")
    public ResponseEntity<FriendAssembler> getRequestedFriends(@Auth Authenticated authenticated,
                                                             @RequestParam(value = "last", required = false, defaultValue = "-1") Long lastUserId) {
        FriendAssembler response = friendInformationService.getRequestedFriends(authenticated.id(), lastUserId);
        return ResponseEntity.ok(response);
    }

}
