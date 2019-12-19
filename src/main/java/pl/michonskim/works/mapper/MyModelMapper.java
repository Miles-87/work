package pl.michonskim.works.mapper;

import pl.michonskim.works.dto.CompanyDto;
import pl.michonskim.works.dto.EmployeeDto;
import pl.michonskim.works.entity.Company;
import pl.michonskim.works.entity.Employee;

import java.util.HashSet;

public interface MyModelMapper {

    static CompanyDto fromCompanyToCompanyDto(Company company){
        return CompanyDto.builder()
                .id(company.getId())
                .name(company.getName())
                .numberOfEmployee(company.getNumberOfEmpployee())
                .specialization(company.getSpecialization())
                .build();
    }

    static Company fromCompanyDtoToCompany(CompanyDto companyDto){
        return Company.builder()
                .id(companyDto.getId())
                .name(companyDto.getName())
                .numberOfEmpployee(companyDto.getNumberOfEmployee())
                .specialization(companyDto.getSpecialization())
                .eployees(new HashSet<>())
                .build();
    }

    static EmployeeDto fromEmployeeToEmployeeDto(Employee employee){
        return EmployeeDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .surname(employee.getSurname())
                .age(employee.getAge())
                .gender(employee.getGender())
                .companyDto(employee.getCompany()== null ?null:fromCompanyToCompanyDto(employee.getCompany()))
                .build();
    }

    static Employee fromEpmployeeDtoToEmployee(EmployeeDto employeeDto){
        return Employee.builder()
                .id(employeeDto.getId())
                .name(employeeDto.getName())
                .surname(employeeDto.getSurname())
                .age(employeeDto.getAge())
                .gender(employeeDto.getGender())
                .company(employeeDto.getCompanyDto()== null ? null : fromCompanyDtoToCompany(employeeDto.getCompanyDto()))
                .build();
    }
}
