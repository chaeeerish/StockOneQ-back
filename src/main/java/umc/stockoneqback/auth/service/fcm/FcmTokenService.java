package umc.stockoneqback.auth.service.fcm;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.stockoneqback.auth.domain.model.FcmToken;
import umc.stockoneqback.auth.domain.repository.FcmTokenRedisRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FcmTokenService {
    private final FcmTokenRedisRepository fcmTokenRedisRepository;

    @Transactional
    public void saveFcmToken(Long userId, String token) {
        fcmTokenRedisRepository.deleteById(userId);
        fcmTokenRedisRepository.save(FcmToken.createFcmToken(userId, token));
    }

    @Transactional
    public void deleteFcmToken(Long userId) {
        fcmTokenRedisRepository.deleteById(userId);
    }

    @Transactional
    public List<FcmToken> findAllOnlineUsers() {
        return fcmTokenRedisRepository.findAll();
    }
}
