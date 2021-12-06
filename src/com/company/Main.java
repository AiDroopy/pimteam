package com.company;

import express.Express;
import org.eclipse.jetty.server.Authentication;

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
                List<Notes> notes = db.getNotes();
                res.json(notes);
        });

        app.post("api/notes", (req, res) -> {
            Notes note = (Notes) req.body(Notes.class);
            System.out.println("INSERT: ");
            System.out.println(note.toString());
            db.createNote(note);
            res.send(note.getHeader() + " created");
        });

        // Server loop
        app.listen(3000);
        System.out.println("Server started on port 3000");

    }

}
