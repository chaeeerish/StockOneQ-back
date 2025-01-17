package umc.stockoneqback.field.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.stockoneqback.field.domain.model.PartTimer;
import umc.stockoneqback.field.domain.model.Store;
import umc.stockoneqback.field.domain.repository.StoreRepository;
import umc.stockoneqback.field.exception.StoreErrorCode;
import umc.stockoneqback.global.exception.BaseException;
import umc.stockoneqback.user.domain.User;
import umc.stockoneqback.user.service.UserFindService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final UserFindService userFindService;

    @Transactional
    public Long save(String name, String sector, String address, Long managerId) {
        validateAlreadyExistStore(name);

        User manager = userFindService.findById(managerId);
        Store store = Store.createStore(name, sector, address, manager);

        return storeRepository.save(store).getId();
    }

    private void validateAlreadyExistStore(String name) {
        if (storeRepository.existsByName(name)) {
            throw BaseException.type(StoreErrorCode.ALREADY_EXIST_STORE);
        }
    }

    public Store findById(Long storeId) {
        return storeRepository.findById(storeId)
                .orElseThrow(() -> BaseException.type(StoreErrorCode.STORE_NOT_FOUND));
    }

    public Store findByName(String storeName) {
        return storeRepository.findByName(storeName)
                .orElseThrow(() -> BaseException.type(StoreErrorCode.STORE_NOT_FOUND));
    }

    public Store findByUser(User user) {
        return storeRepository.findByManager(user)
                .orElseThrow(() -> BaseException.type(StoreErrorCode.STORE_NOT_FOUND));
    }

    @Transactional
    public void deleteManager(Store store) {
        store.deleteManager();
    }

    @Transactional
    public void deletePartTimer(Store store, PartTimer partTimer) {
        store.deletePartTimer(partTimer);
    }
}
