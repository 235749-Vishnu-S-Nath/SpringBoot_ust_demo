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

/**
 * The type Tutor controller.
 */
@RestController
public class TutorController {
    /**
     * The Tutor service.
     */
    @Autowired
    TutorService tutorService;

    /**
     * Add.
     *
     * @param tutor the tutor
     */
    @PostMapping("/tutor")
    public void add(@RequestBody Tutor tutor){
        tutorService.saveTutor(tutor);
    }

    /**
     * Get response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/tutor/{id}")
    public ResponseEntity<Tutor> get(@PathVariable Integer id){
        try{
            Tutor tutor = tutorService.getTutor(id);
            return new ResponseEntity<Tutor>(tutor, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return  new ResponseEntity<Tutor>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get all response entity.
     *
     * @return the response entity
     */
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

    /**
     * Remove.
     *
     * @param id the id
     */
    @DeleteMapping("/tutor/{id}")
    public void remove(@PathVariable Integer id){
        tutorService.removeTutor(id);
    }

    /**
     * Update response entity.
     *
     * @param tutor the tutor
     * @return the response entity
     */
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
