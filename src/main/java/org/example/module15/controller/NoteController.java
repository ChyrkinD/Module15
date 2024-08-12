package org.example.module15.controller;

import org.example.module15.dao.model.Note;
import org.example.module15.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/list")
    public String listNotes(Model model) {
        model.addAttribute("notes", noteService.listAll());
        return "note/noteList";
    }

    @PostMapping("/delete")
    public String deleteNote(@RequestParam("id") int id){
        noteService.deleteById(id);
        return "redirect:/note/list";
    }

    @GetMapping("/edit")
    public String editNoteForm(@RequestParam("id") long id, Model model){
        Note note = noteService.getById(id);
        model.addAttribute("note", note);
        return "note/noteEdit";
    }

    @PostMapping("/edit")
    public String editNote(@ModelAttribute("note") Note note){
        noteService.update(note);
        return "redirect:/note/list";
    }
}
