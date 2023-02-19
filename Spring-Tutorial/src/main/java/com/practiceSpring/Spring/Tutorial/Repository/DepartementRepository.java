package com.practiceSpring.Spring.Tutorial.Repository;

import com.practiceSpring.Spring.Tutorial.entity.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, Long> {


    //@modifying to idicate that this query will modify the data base structure
    //@transactional to indicate that it is a transaction
    // @QUERY(value= "SELECT * FROM tbl_student s where s.email_adress = :emailId", nativeQuery = true)
    //@Param("emailId")


    public Departement findByDepartementNameIgnoreCase(String departementName);
}
