package com.example.demo.Controller;

import com.example.demo.bean.Livre;
import com.example.demo.dao.LivreDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/livres")
//@CrossOrigin(origins = "http://localhost:3000")
public class LivreController {

    @Autowired
    LivreDao livreDao;


    @GetMapping("/getAll")
    public ResponseEntity<List<Livre>> getAllLivres(){
        try{
            List<Livre> livreList = new ArrayList<>();
            livreDao.findAll().forEach(e->livreList.add(e));

            if (livreList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(livreList,HttpStatus.OK);

        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getByIsbn/{isbn}")
    public ResponseEntity<Livre> getByIsbn(@PathVariable String isbn){
        try{
            Livre livre = livreDao.findByIsbn(isbn);
            if (livre == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(livre,HttpStatus.OK);
            }

        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addLivre")
    public ResponseEntity<Livre> addLivre(@RequestBody Livre livre){
        Livre livre_ =  livreDao.save(livre);
        return new ResponseEntity<>(livre,HttpStatus.OK);
    }

    @PutMapping("/updateByIsbn/{isbn}")
    public ResponseEntity<Livre> updateLivreByIsbn(@PathVariable String isbn, @RequestBody Livre livre){
        Livre ancientLivre = livreDao.findByIsbn(isbn);
        if (ancientLivre == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            livreDao.save(livre);
            return new ResponseEntity<>(livre,HttpStatus.OK);
        }
    }

    @DeleteMapping("/deleteByIsbn/{isbn}")
    public ResponseEntity<Livre> deleteLivreByIsbn(@PathVariable String isbn){
        livreDao.deleteByIsbn(isbn);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
