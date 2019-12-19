package pl.michonskim.works.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.michonskim.works.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
