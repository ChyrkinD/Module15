package org.example.module15.service;

import lombok.RequiredArgsConstructor;
import org.example.module15.dao.model.Note;
import org.example.module15.exception.NoteNotFoundByThisIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
@RequiredArgsConstructor
@Service
public class NoteService {
    private final List<Note> notes;

    public List<Note> listAll(){
        return notes;
    }

    public Note add(Note note){
        note.setId(generateId());
        notes.add(note);
        return note;
    }

    public void deleteById(long id){
        Note noteForDeleteById = getById(id);
        notes.remove(noteForDeleteById);
    }

    public void update(Note note){
        Note noteForUpdate = getById(note.getId());
        notes.get(notes.indexOf(noteForUpdate)).setContent(note.getContent());
        notes.get(notes.indexOf(noteForUpdate)).setTitle(note.getTitle());
    }

    public Note getById(long id){
        Note result = null;
        for (Note note : notes) {
            if(note.getId() == id){
                result = note;
            }
        }
        if(result == null){
            throw new NoteNotFoundByThisIdException();
        }
        return result;
    }

    private Long generateId(){
        Long id = 0L;
        boolean chekIdExists = true;
        while (chekIdExists){
            chekIdExists = false;
            id = new Random().nextLong(1000);
            if (notes.isEmpty()){
                break;
            }
            for (Note note : notes) {
                if (note.getId() == id) {
                    chekIdExists = true;
                }
            }
        }
        return id;
    }
}
