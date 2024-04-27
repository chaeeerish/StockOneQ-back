package umc.stockoneqback.field.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.stockoneqback.field.domain.company.Company;
import umc.stockoneqback.field.domain.company.CompanyRepository;
import umc.stockoneqback.field.exception.CompanyErrorCode;
import umc.stockoneqback.global.exception.BaseException;
import umc.stockoneqback.user.domain.model.User;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public Company findByName(String companyName) {
        return companyRepository.findByName(companyName)
                .orElseThrow(() -> BaseException.type(CompanyErrorCode.COMPANY_NOT_FOUND));
    }

    @Transactional
    public void deleteSupervisor(Company company, User user) {
        company.deleteSupervisor(user);
    }
}
