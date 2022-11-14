package com.student.ust.service;

import com.student.ust.entity.Tutor;
import com.student.ust.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The type Tutor service.
 */
@Service
public class TutorService {

    /**
     * The Tutor repository.
     */
    @Autowired
    TutorRepository tutorRepository;

    /**
     * Save tutor.
     *
     * @param tutor the tutor
     */
    public void saveTutor(Tutor tutor) {
        tutor.setCreateDate(LocalDateTime.now());
        tutor.setModifiedDate(tutor.getCreateDate());
        tutorRepository.save(tutor);
    }

    /**
     * Gets tutor.
     *
     * @param id the id
     * @return the tutor
     */
    public Tutor getTutor(Integer id) {
        return tutorRepository.findById(id).orElse(null);
    }

    /**
     * Gets all tutor.
     *
     * @return the all tutor
     */
    public List<Tutor> getAllTutor() {
        return tutorRepository.findAll();
    }

    /**
     * Remove tutor.
     *
     * @param id the id
     */
    public void removeTutor(Integer id) {
        tutorRepository.deleteById(id);
    }

    /**
     * Update tutor tutor.
     *
     * @param tutor the tutor
     * @return the tutor
     */
    public Tutor updateTutor(Tutor tutor) {
        Tutor updatedTutor = tutorRepository.findById(tutor.getTutorId()).orElseThrow(()->new NoSuchElementException());
        updatedTutor.setSubject(tutor.getSubject());
        updatedTutor.setName(tutor.getName());
        updatedTutor.setModifiedDate(LocalDateTime.now());
        tutorRepository.save(updatedTutor);
        return updatedTutor;
    }
}
