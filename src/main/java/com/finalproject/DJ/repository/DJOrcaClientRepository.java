package com.finalproject.DJ.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.entity.Client;
import com.finalproject.entity.ClientProjection;
import com.finalproject.entity.ClientProjection2;


@Repository
public interface DJOrcaClientRepository extends JpaRepository<Client, String> {
    // DJ repository

    //select id from client where idchk=? and brn=?
    ClientProjection findByIdchkaAndBrn(String idchka, String brn);

    //select id, pw from client where id=? and brn=?
    ClientProjection2 findByIdAndBrn(String id, String brn);

    
}
