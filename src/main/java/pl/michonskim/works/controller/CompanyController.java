package pl.michonskim.works.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.michonskim.works.Service.CompanyService;
import pl.michonskim.works.dto.CompanyDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companies")
public class CompanyController {

    CompanyService companyService;

    @GetMapping
    ResponseEntity<List<CompanyDto>> findAll() {
        return new ResponseEntity<>(companyService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<CompanyDto> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(companyService.findOne(id), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<CompanyDto> add(@RequestBody CompanyDto companyDto) {
        return new ResponseEntity<>(companyService.add(companyDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<CompanyDto> update(@PathVariable Long id, @RequestBody CompanyDto companyDto) {
        return new ResponseEntity<>(companyService.update(id, companyDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<CompanyDto> delete(@PathVariable Long id) {
        return new ResponseEntity<>(companyService.delete(id), HttpStatus.OK);
    }


}
