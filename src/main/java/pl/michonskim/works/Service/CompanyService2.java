package pl.michonskim.works.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.michonskim.works.dto.CompanyDto;
import pl.michonskim.works.entity.Company;
import pl.michonskim.works.exception.MyException;
import pl.michonskim.works.mapper.MyModelMapper;
import pl.michonskim.works.repository.CompanyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyService2 {

    private final CompanyRepository companyRepository;

    public List<CompanyDto> findAll(){
        return companyRepository.findAll()
                .stream()
                .map(MyModelMapper::fromCompanyToCompanyDto)
                .collect(Collectors.toList());
    }

    public CompanyDto findOne(Long id){
        return  companyRepository.findById(id)
                .map(MyModelMapper::fromCompanyToCompanyDto)
                .orElseThrow(() -> new MyException("there is no taht id"));
    }

    public CompanyDto add(CompanyDto companyDto){
        if(companyDto == null){
            throw  new MyException("company shouln't be null");
        }
        Company company = MyModelMapper.fromCompanyDtoToCompany(companyDto);
        companyRepository.save(company);
        return MyModelMapper.fromCompanyToCompanyDto(company);
    }

    public CompanyDto update(Long id, CompanyDto companyDto){
        if(id == null){
            throw new MyException("id shouldn't be null");
        }
        Company company = companyRepository.getOne(id);
        company.setName(companyDto.getName() == null ? null : companyDto.getName());
        company.setSpecialization(companyDto.getSpecialization() == null ? null : companyDto.getSpecialization());
        company.setNumberOfEmpployee(companyDto.getNumberOfEmployee()==null ? null : companyDto.getNumberOfEmployee());
        Company companyFromDb = companyRepository.save(company);
        return MyModelMapper.fromCompanyToCompanyDto(companyFromDb);

    }
}
