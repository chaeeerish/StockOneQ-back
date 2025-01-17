package umc.stockoneqback.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.stockoneqback.field.domain.model.PartTimer;
import umc.stockoneqback.field.domain.repository.PartTimerRepository;
import umc.stockoneqback.global.exception.BaseException;
import umc.stockoneqback.user.domain.Email;
import umc.stockoneqback.user.domain.User;
import umc.stockoneqback.user.dto.response.LoginIdResponse;
import umc.stockoneqback.user.dto.response.UserInformationResponse;
import umc.stockoneqback.user.exception.UserErrorCode;

import java.time.LocalDate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserInformationService {
    private final UserFindService userFindService;
    private final PartTimerRepository partTimerRepository;

    public LoginIdResponse findLoginId(String name, LocalDate birth, Email email) {
        User user = userFindService.findByEmail(email);
        if (!(user.getName().equals(name) && user.getBirth().equals(birth))) {
            throw BaseException.type(UserErrorCode.USER_NOT_FOUND);
        }

        return new LoginIdResponse(user.getLoginId());
    }

    public UserInformationResponse getInformation(Long userId) {
        User user = userFindService.findById(userId);

        switch (user.getRoles().get(0).getRoleType()) {
            case MANAGER -> { return getManagerInformation(user); }
            case PART_TIMER -> { return getPartTimerInformation(user); }
            case SUPERVISOR -> { return getSupervisorInformation(user); }
            default -> throw BaseException.type(UserErrorCode.USER_NOT_FOUND);
        }
    }

    private UserInformationResponse getManagerInformation(User user) {
        return new UserInformationResponse(user.getId(), user.getEmail().getValue(), user.getLoginId(), user.getName(), user.getBirth(), user.getPhoneNumber(), user.getRoles().get(0).getRoleType().getAuthority(), user.getManagerStore().getName(), user.getManagerStore().getCode(), user.getManagerStore().getAddress(), null);
    }

    private UserInformationResponse getPartTimerInformation(User user) {
        PartTimer partTimer = partTimerRepository.findByPartTimer(user).orElseThrow(() -> BaseException.type(UserErrorCode.USER_NOT_FOUND));
        return new UserInformationResponse(user.getId(), user.getEmail().getValue(), user.getLoginId(), user.getName(), user.getBirth(), user.getPhoneNumber(), user.getRoles().get(0).getRoleType().getAuthority(), partTimer.getStore().getName(), null, partTimer.getStore().getAddress(), null);
    }

    private UserInformationResponse getSupervisorInformation(User user) {
        return new UserInformationResponse(user.getId(), user.getEmail().getValue(), user.getLoginId(), user.getName(), user.getBirth(), user.getPhoneNumber(), user.getRoles().get(0).getRoleType().getAuthority(), null, null, null, user.getCompany().getName());
    }
}
