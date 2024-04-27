package umc.stockoneqback.user.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import umc.stockoneqback.board.domain.Board;
import umc.stockoneqback.field.domain.company.Company;
import umc.stockoneqback.field.domain.store.model.Store;
import umc.stockoneqback.global.base.BaseTimeEntity;
import umc.stockoneqback.global.base.Status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.CascadeType.PERSIST;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Email email;

    private String loginId;

    @Embedded
    private Password password;

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;

    private String phoneNumber;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private final List<Role> roles = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_store_id", referencedColumnName = "id")
    private Store managerStore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    @Convert(converter = Status.StatusConverter.class)
    private Status status;

    // 회원 탈퇴시 작성한 게시글 모두 삭제
    @OneToMany(mappedBy = "writer", cascade = PERSIST, orphanRemoval = true)
    private List<Board> boardList = new ArrayList<>();

    @Builder
    private User(Email email, String loginId, Password password, String username, LocalDate birth, String phoneNumber, Set<RoleType> roleTypes) {
        this.email = email;
        this.loginId = loginId;
        this.password = password;
        this.name = username;
        this.phoneNumber = phoneNumber;
        this.roles.addAll(
                roleTypes.stream()
                        .map(roleType -> Role.createRole(this, roleType))
                        .toList()
        );
        this.birth = birth;
        this.status = Status.NORMAL;
    }

    public static User createUser(Email email, String loginId, Password password, String username, LocalDate birth, String phoneNumber, Set<RoleType> roleTypes) {
        return new User(email, loginId, password, username, birth, phoneNumber, roleTypes);
    }

    public void registerManagerStore(Store store) {
        this.managerStore = store;
    }

    public void registerCompany(Company company) {
        this.company = company;
    }

    public void updateInformation(Email email, String loginId, Password password, String username, LocalDate birth, String phoneNumber) {
        this.email = email;
        this.loginId = loginId;
        this.password = password;
        this.name = username;
        this.phoneNumber = phoneNumber;
        this.birth = birth;
    }

    public void updatePassword(Password password) {
        this.password = password;
    }
}
