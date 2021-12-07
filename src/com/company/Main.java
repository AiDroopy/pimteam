package com.company;

import express.Express;
import io.javalin.http.UploadedFile;

import java.io.FileOutputStream;
import java.nio.file.Path;
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

        // Get note by id
        app.get("api/notes/:id", (req, res) ->{
            int id = Integer.parseInt(req.params("id"));
            Note note = db.getNoteById(id);
            res.json(note);

        });

        // Create a note
        app.post("api/notes", (req, res) -> {
            Note note = (Note) req.body(Note.class);
            db.createNote(note);
            res.send(note.getHeader() + " created");
        });

        // Update a note
        app.put("/api/notes/:id", (req, res) -> {
            Note note = (Note) req.body(Note.class);
            db.updateNote(note);
            res.send(note.getHeader() + " updated!");
        });

        // Delete note
        app.delete("api/notes/:id", (req, res) -> {
            Note note = (Note) req.body(Note.class);


            boolean deleted = db.deleteNote(note);
            if (deleted == true){
                res.send("Note deleted!");
            }
            else res.send("Something went really wrong!");
        });

        // Search note by header

        app.get("api/notes/search/header/:header", (req, res) ->{
            String header = (String) (req.params("header"));
            Note note[] = db.getNoteByHeader(header);
            res.json(note);

        });

        // Search by bodyText
        app.get("api/notes/search/notes/:notes", (req, res) ->{
            String notes = (String) (req.params("notes"));
            Note note[] = db.getNoteByNotes(notes);
            res.json(note);

        });

        app.post("/api/images", (req, res) -> {
            //List<UploadedFile> files = req.formDataFiles("files");  // get files as list
            UploadedFile file = req.formDataFile("files");          // get a single file

            // with FileOutputStream
            Path path = Paths.get("src/www/images/1/" + file.getFilename());
            System.out.println(path.toString());
            try (FileOutputStream os = new FileOutputStream(path.toString())) {
                os.write(file.getContent().readAllBytes()); // write to file
            }


            // with FileUtil (creates dirs if necessary) !!!! ANVÄNDER VI TILL CREATE USER!!!
            //FileUtil.streamToFile(file.getContent(), "src/images/" + file.getFilename());
        });

        app.post("/api/documents", (req, res) -> {
            //List<UploadedFile> files = req.formDataFiles("files");  // get files as list
            UploadedFile file = req.formDataFile("files");          // get a single file

            // with FileOutputStream
            Path path = Paths.get("src/www/documents/1/" + file.getFilename());

            try (FileOutputStream os = new FileOutputStream(path.toString())) {
                os.write(file.getContent().readAllBytes()); // write to file
            }

            // with FileUtil (creates dirs if necessary)
            //FileUtil.streamToFile(file.getContent(), "src/files/1/" + file.getFilename());
        });


        // Server loop
        app.listen(3000);
        System.out.println("Server started on port 3000");

    }

}
