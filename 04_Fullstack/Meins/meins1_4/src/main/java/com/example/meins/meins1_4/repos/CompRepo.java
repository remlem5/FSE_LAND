package com.example.meins.meins1_4.repos;

import com.example.meins.meins1_4.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompRepo extends JpaRepository<Company, Long> {

    Company findCompanyByCompId(long compId);

    Company findCompanyByCompName(String name);

}
