package umc.stockoneqback.field.domain.model;

import jakarta.persistence.*;
import lombok.*;
import umc.stockoneqback.global.base.BaseTimeEntity;
import umc.stockoneqback.global.base.Status;
import umc.stockoneqback.user.domain.User;

import java.util.ArrayList;
import java.util.List;

import static umc.stockoneqback.global.utils.RandomCodeGenerator.generateRandomCode;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "company")
public class Company extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String sector;

    private String code;

    @OneToMany(mappedBy = "company", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<User> employees = new ArrayList<>();

    @Convert(converter = Status.StatusConverter.class)
    private Status status;

    @Builder
    public Company(String name, String sector, String code) {
        this.name = name;
        this.sector = sector;
        this.code = code;
        this.status = Status.NORMAL;
    }

    public static Company createCompany(String name, String sector) {
        return new Company(name, sector, generateRandomCode());
    }

    public void addEmployees(User user) {
        user.registerCompany(this);
        this.employees.add(user);
    }

    public void deleteSupervisor(User user) {
        this.employees.remove(user);
    }
}
