package pl.michonskim.works.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.michonskim.works.dto.EmployeeDto;
import pl.michonskim.works.entity.Employee;
import pl.michonskim.works.exception.MyException;
import pl.michonskim.works.mapper.MyModelMapper;
import pl.michonskim.works.repository.CompanyRepository;
import pl.michonskim.works.repository.EmployeeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;

    public List<EmployeeDto> all() {
        return employeeRepository.findAll()
                .stream()
                .map(MyModelMapper::fromEmployeeToEmployeeDto)
                .collect(Collectors.toList());
    }

    public EmployeeDto one(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new MyException("fgf"));
        return MyModelMapper.fromEmployeeToEmployeeDto(employee);

    }

    public EmployeeDto add(EmployeeDto employeeDto){
        Employee employee = MyModelMapper.fromEpmployeeDtoToEmployee(employeeDto);
        Employee employeeFromDb = employeeRepository.save(employee);
        return MyModelMapper.fromEmployeeToEmployeeDto(employeeFromDb);
    }

    public EmployeeDto update(Long id, EmployeeDto employeeDto){
        Employee employee = employeeRepository.getOne(id);
        employee.setName(employeeDto.getName() == null? null:employeeDto.getName());
        employee.setSurname(employeeDto.getSurname() == null ?null : employeeDto.getSurname());
        employee.setGender(employeeDto.getGender() == null ? null:employeeDto.getGender());
        employee.setAge(employeeDto.getAge() == null ? null :employeeDto.getAge());
        Employee employeeFromDb = employeeRepository.save(employee);
        return MyModelMapper.fromEmployeeToEmployeeDto(employeeFromDb);
    }

    public EmployeeDto delete(Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new MyException(""));
        employeeRepository.delete(employee);
        return MyModelMapper.fromEmployeeToEmployeeDto(employee);

    }


}
