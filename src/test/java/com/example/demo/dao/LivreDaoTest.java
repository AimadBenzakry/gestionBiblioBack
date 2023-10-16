package com.example.demo.dao;

import com.example.demo.bean.Livre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class LivreDaoTest {

    @Autowired
    private LivreDao underTest;

    @Test
    void itShouldFindLivreByIsbn(){

        //Given
        Livre livre = new Livre("111","titre1","auteur1");
        this.underTest.save(livre);
        //When
        Livre expected = this.underTest.findByIsbn(livre.getIsbn());
        //Then
        assertThat(expected).isNotEqualTo(null);
        assertThat(expected).isEqualTo(livre);
//        assertThat(expected.getAuteur()).isEqualTo(livre.getAuteur());
    }

    @Test
    void itShouldNotFindLivreWhenItDoesNotExist(){
        //Given
        Livre livre = new Livre("111","titre1","auteur1");
        this.underTest.save(livre);
        Livre livre2 = new Livre("222","titre2","auteur2");
        this.underTest.save(livre2);

        String isbn = "333";
        //When
        Livre expected = this.underTest.findByIsbn(isbn);
        //Then
        assertThat(expected).isNull();
    }

}