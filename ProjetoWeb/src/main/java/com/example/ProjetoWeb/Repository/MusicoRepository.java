package com.example.ProjetoWeb.Repository;

import com.example.ProjetoWeb.Model.MusicoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicoRepository extends JpaRepository<MusicoModel, Integer> {

}
