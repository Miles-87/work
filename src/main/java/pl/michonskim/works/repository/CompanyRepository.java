package pl.michonskim.works.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.michonskim.works.entity.Company;
import pl.michonskim.works.entity.Employee;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<Company> findAllByEmployees_Id(Long id);
   // Page<Company> findAll(Pageable pageable);
    Company findByName(String name);
}
