package umc.stockoneqback.friend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import umc.stockoneqback.auth.domain.model.jwt.Authenticated;
import umc.stockoneqback.friend.service.FriendService;
import umc.stockoneqback.global.annotation.Auth;
import umc.stockoneqback.global.annotation.ExtractPayload;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/friend")
public class FriendApiController {
    private final FriendService friendService;

    @PreAuthorize("hasRole('PART_TIMER')")
    @PostMapping("/request/{receiverId}")
    public ResponseEntity<Void> requestFriend(@Auth Authenticated authenticated, @PathVariable Long receiverId) {
        Long friendId = friendService.requestFriend(authenticated.id(), receiverId);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("/request/{receiverId}")
    public ResponseEntity<Void> cancelFriend(@Auth Authenticated authenticated, @PathVariable Long receiverId) {
        friendService.cancelFriend(authenticated.id(), receiverId);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PatchMapping("/accept/{senderId}")
    public ResponseEntity<Void> acceptFriend(@Auth Authenticated authenticated, @PathVariable Long senderId) {
        Long friendId = friendService.acceptFriend(senderId, authenticated.id());
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("/reject/{senderId}")
    public ResponseEntity<Void> rejectFriend(@Auth Authenticated authenticated, @PathVariable Long senderId) {
        friendService.rejectFriend(senderId, authenticated.id());
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("/{friendUserId}")
    public ResponseEntity<Void> deleteFriend(@Auth Authenticated authenticated, @PathVariable Long friendUserId) {
        friendService.deleteFriend(authenticated.id(), friendUserId);
        return ResponseEntity.ok().build();
    }
}
