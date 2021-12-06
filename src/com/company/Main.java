package com.company;

import express.Express;

import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Express app = new Express();
        Database db = new Database();

        // Serv html root
        try {
            app.useStatic(Paths.get("src/www"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Get all notes
        app.get("api/notes", (req, res) -> {
                List<Note> notes = db.getAllNotes();
                res.json(notes);
        });

        // Create a note
        app.post("api/notes", (req, res) -> {
            Note note = (Note) req.body(Note.class);
            System.out.println("INSERT: ");
            System.out.println(note.toString());
            db.createNote(note);
            res.send(note.getHeader() + " created");
        });

        // Update a note
        app.put("/api/notes", (req, res) -> {
            Note note = (Note) req.body(Note.class);

            db.updateNote(note);
            res.send(note.getHeader() + " updated!");
        });



        // Server loop
        app.listen(3000);
        System.out.println("Server started on port 3000");

    }

}
