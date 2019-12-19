package pl.michonskim.works.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.michonskim.works.dto.CompanyDto;
import pl.michonskim.works.entity.Company;
import pl.michonskim.works.exception.MyException;
import pl.michonskim.works.mapper.MyModelMapper;
import pl.michonskim.works.repository.CompanyRepository;
import pl.michonskim.works.repository.EmployeeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;

    public List<CompanyDto> findAll() {
        return companyRepository.findAll()
                .stream()
                .map(MyModelMapper::fromCompanyToCompanyDto)
                .collect(Collectors.toList());
    }

    public CompanyDto findOne(Long id) {
        if (id == null) {
            throw new MyException("id shouldn't be null");
        }
        return companyRepository.findById(id)
                .map(MyModelMapper::fromCompanyToCompanyDto)
                .orElseThrow(() -> new MyException("no company with this id"));
    }

    public CompanyDto add(CompanyDto companyDto) {
        if (companyDto == null) {
            throw new MyException("company shouldn't be null");
        }
        Company company = MyModelMapper.fromCompanyDtoToCompany(companyDto);
        Company companyFromDb = companyRepository.save(company);
        return MyModelMapper.fromCompanyToCompanyDto(companyFromDb);
    }

    public CompanyDto update(Long id, CompanyDto companyDto) {
        if (companyDto == null) {
            throw new MyException("company shouldn't be null");
        }
        Company company = companyRepository.getOne(id);
        company.setName(companyDto.getName() == null ? company.getName() : companyDto.getName());
        company.setNumberOfEmpployee(companyDto.getNumberOfEmployee() == null ? company.getNumberOfEmpployee() : companyDto.getNumberOfEmployee());
        company.setSpecialization(companyDto.getSpecialization() == null ? company.getSpecialization() : companyDto.getSpecialization());
        Company companyFromDb = companyRepository.save(company);
        return MyModelMapper.fromCompanyToCompanyDto(companyFromDb);

    }

    public CompanyDto delete(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new MyException("id shouldn't be null"));

        employeeRepository.saveAll(employeeRepository.findAllByCompany_Id(company.getId()))
                .stream()
                .peek(employee -> employee.setCompany(null))
                .collect(Collectors.toList());

        companyRepository.delete(company);
        return MyModelMapper.fromCompanyToCompanyDto(company);

    }
}