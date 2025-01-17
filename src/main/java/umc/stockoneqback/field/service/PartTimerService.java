package umc.stockoneqback.field.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.stockoneqback.field.domain.model.PartTimer;
import umc.stockoneqback.field.domain.repository.PartTimerRepository;
import umc.stockoneqback.field.exception.PartTimerErrorCode;
import umc.stockoneqback.global.exception.BaseException;
import umc.stockoneqback.user.domain.User;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PartTimerService {
    private final StoreService storeService;
    private final PartTimerRepository partTimerRepository;

    @Transactional
    public void savePartTimer(PartTimer partTimer) {
        partTimerRepository.save(partTimer);
    }

    public PartTimer findByUser(User user) {
        return partTimerRepository.findByPartTimer(user)
                .orElseThrow(() -> BaseException.type(PartTimerErrorCode.PART_TIMER_NOT_FOUND));
    }

    @Transactional
    public void deletePartTimer(User user) {
        Optional<PartTimer> partTimer = partTimerRepository.findByPartTimer(user);
        if (partTimer.isEmpty())
            return;

        storeService.deletePartTimer(partTimer.get().getStore(), partTimer.get());
        partTimerRepository.deleteByPartTimer(user);
    }
}
