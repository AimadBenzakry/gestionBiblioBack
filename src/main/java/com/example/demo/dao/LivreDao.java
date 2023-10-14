package com.example.demo.dao;

import com.example.demo.bean.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LivreDao extends JpaRepository<Livre,Long> {
    Livre findByIsbn(String isbn);
    int deleteByIsbn(String isbn);
}
