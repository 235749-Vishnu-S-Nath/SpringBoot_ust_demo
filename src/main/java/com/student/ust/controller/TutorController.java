package com.student.ust.controller;

import com.student.ust.entity.Book;
import com.student.ust.entity.Tutor;
import com.student.ust.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class TutorController {
    @Autowired
    TutorService tutorService;
    @PostMapping("/tutor")
    public void add(@RequestBody Tutor tutor){
        tutorService.saveTutor(tutor);
    }

    @GetMapping("/tutor/{id}")
    public ResponseEntity<Tutor> get(@PathVariable Integer id){
        try{
            Tutor tutor = tutorService.getTutor(id);
            return new ResponseEntity<Tutor>(tutor, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return  new ResponseEntity<Tutor>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/tutor")
    public ResponseEntity<List<Tutor>> getAll(){
        try{
            List<Tutor> tutorList = tutorService.getAllTutor();
            return new ResponseEntity<List<Tutor>>(tutorList, HttpStatus.OK);
        }
        catch(NoSuchElementException e) {
            return new ResponseEntity<List<Tutor>>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/tutor/{id}")
    public void remove(@PathVariable Integer id){
        tutorService.removeTutor(id);
    }

    @PutMapping("/tutor")
    public ResponseEntity<Tutor> update(@RequestBody Tutor tutor){
        try{
            Tutor updatedTutor = tutorService.updateTutor(tutor);
            return new ResponseEntity<Tutor>(updatedTutor,HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<Tutor>(HttpStatus.NOT_FOUND);
        }
    }
}
