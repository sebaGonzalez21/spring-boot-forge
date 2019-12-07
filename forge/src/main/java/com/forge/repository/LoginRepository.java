package com.forge.repository;

import com.forge.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//Crud Repository o JpaRepository o PaginAndSortignRepository  permite realizar operaciones sobre la base de datos
@Repository
public interface LoginRepository extends JpaRepository<Login,Long> {

    Login findByEmail(String email);

}
