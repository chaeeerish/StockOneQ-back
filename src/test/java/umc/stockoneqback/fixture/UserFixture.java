package umc.stockoneqback.fixture;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import umc.stockoneqback.user.domain.Email;
import umc.stockoneqback.user.domain.Password;
import umc.stockoneqback.user.domain.RoleType;
import umc.stockoneqback.user.domain.User;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;

import static umc.stockoneqback.global.utils.PasswordEncoderUtils.ENCODER;

@Getter
@RequiredArgsConstructor
public enum UserFixture {
    SAEWOO("saewoo@naver.com", "saewoo123", "NewPass-2023", "새우", LocalDate.of(2001, 4, 22), "01012345678", RoleType.PART_TIMER),
    ANNE("anne@gmail.com", "anne123", "Strong123!", "앤", LocalDate.of(1995, 5, 12), "01098765432", RoleType.MANAGER),
    WIZ("wiz@yahoo.com", "wiz123", "Secure456!", "위즈", LocalDate.of(1988, 9, 25), "01056781234", RoleType.SUPERVISOR),
    WONI("woni@hanmail.com", "woni123", "Pass1234$", "워니", LocalDate.of(2000, 12, 31), "01024681357", RoleType.PART_TIMER),
    BOB("bob@example.com", "bob123", "Secure789!", "밥", LocalDate.of(1990, 6, 18), "01011112222", RoleType.PART_TIMER),
    ELLA("ella@example.com", "ella123", "NewPass-2023", "엘라", LocalDate.of(1998, 8, 27), "01033334444", RoleType.MANAGER),
    JACK("jack@example.com", "jack123", "Strong987!", "잭", LocalDate.of(1987, 2, 13), "01055556666", RoleType.SUPERVISOR),
    LILY("lily@example.com", "lily123", "Pass4321$", "릴리", LocalDate.of(1993, 11, 8), "01077778888", RoleType.PART_TIMER),
    MIKE("mike@example.com", "mike123", "NewPass-2023", "마이크", LocalDate.of(1996, 9, 3), "01099990000", RoleType.MANAGER),
    OLIVIA("olivia@example.com", "olivia123", "Strong789!", "올리비아", LocalDate.of(1991, 1, 21), "01088889999", RoleType.SUPERVISOR),
    TONY("tony@example.com", "tony123", "Secure567!", "토니", LocalDate.of(1999, 5, 29), "01044445555", RoleType.PART_TIMER),
    SOPHIA("sophia@example.com", "sophia123", "NewPass-2023", "소피아", LocalDate.of(1986, 4, 17), "01033334444", RoleType.MANAGER),
    UNKNOWN("unknown@hanmail.com", "unknown123", "Pwd78910*", "언노운", LocalDate.of(2000, 10, 31), "01024681398", RoleType.MANAGER),
    ADMIN("admin@hanmail.com", "admin123", "Admin123!", "관리자", LocalDate.of(2000, 1, 1), "01001234567", RoleType.ADMINISTRATOR)
    ;

    private final String email;
    private final String loginId;
    private final String password;
    private final String name;
    private final LocalDate birth;
    private final String phoneNumber;
    private final RoleType roleType;

    public User toUser() {
        return User.createUser(Email.from(email), loginId, Password.encrypt(password, ENCODER), name, birth, phoneNumber, new HashSet<>(Collections.singleton(roleType)));
    }
}
