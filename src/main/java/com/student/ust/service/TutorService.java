package com.student.ust.service;

import com.student.ust.entity.Tutor;
import com.student.ust.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TutorService {

    @Autowired
    TutorRepository tutorRepository;

    public void saveTutor(Tutor tutor) {
        tutor.setCreateDate(LocalDateTime.now());
        tutor.setModifiedDate(tutor.getCreateDate());
        tutorRepository.save(tutor);
    }

    public Tutor getTutor(Integer id) {
        return tutorRepository.findById(id).orElse(null);
    }

    public List<Tutor> getAllTutor() {
        return tutorRepository.findAll();
    }

    public void removeTutor(Integer id) {
        tutorRepository.deleteById(id);
    }

    public Tutor updateTutor(Tutor tutor) {
        Tutor updatedTutor = tutorRepository.findById(tutor.getTutorId()).orElseThrow(()->new NoSuchElementException());
        updatedTutor.setSubject(tutor.getSubject());
        updatedTutor.setName(tutor.getName());
        updatedTutor.setModifiedDate(LocalDateTime.now());
        tutorRepository.save(updatedTutor);
        return updatedTutor;
    }
}
