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
                List<String> notes = db.getNotes();
                res.json(notes);
        });

        // Server loop
        app.listen(3000);
        System.out.println("Server started on port 3000");

    }

}
