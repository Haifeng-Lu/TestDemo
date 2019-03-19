package com.datamaster.repository;


import com.datamaster.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("personRepository")
public interface PersonRepository extends JpaRepository<Person, Integer> {

}
