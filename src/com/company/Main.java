package com.company;

import express.Express;
import io.javalin.http.UploadedFile;

import java.io.FileOutputStream;
import java.nio.file.Files;
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

        // Create a Note
        app.post("api/notes", (req, res) -> {
            Note note = (Note) req.body(Note.class);
            db.createNote(note);
            res.send(note.getHeader() + " created");
        });

        // Update a Note
        app.put("/api/notes/:id", (req, res) -> {
            Note note = (Note) req.body(Note.class);
            db.updateNote(note);
            res.send(note.getHeader() + " updated!");
        });

        // Delete Note
        app.delete("api/notes/:id", (req, res) -> {
            Note note = (Note) req.body(Note.class);


            boolean deleted = db.deleteNote(note);
            if (deleted == true){
                res.send("Note deleted!");
            }
            else res.send("Something went really wrong!");
        });

        // Search Note by header
        app.get("api/notes/search/header/:header", (req, res) ->{
            String header = (String) (req.params("header"));
            Note note[] = db.getNoteByHeader(header);
            res.json(note);

        });

        // Search Note by bodyText
        app.get("api/notes/search/notes/:notes", (req, res) ->{
            String notes = (String) (req.params("notes"));
            Note note[] = db.getNoteByNotes(notes);
            res.json(note);

        });




        // upload Images
        app.post("/api/images", (req, res) -> {
            //List<UploadedFile> files = req.formDataFiles("files");  // get files as list
            UploadedFile file = req.formDataFile("image"); // get a single file
            String header = req.formData("header"); // created header key-value


            // with FileOutputStream
            Path path = Paths.get("src/www/images/1/" + file.getFilename());
            System.out.println(path.toString());

            try (FileOutputStream os = new FileOutputStream(path.toString())) {
                os.write(file.getContent().readAllBytes()); // write to file


                Attachment attachment = new Attachment(path.toString(), 1, header); // created attachment object

                boolean test = db.setImageUrl(attachment);
                System.out.println(test); // debug statement true or false
                res.send("OK");
            }

            // with FileUtil (creates dirs if necessary) !!!! ANVÄNDER VI TILL CREATE USER!!!
            // FileUtil.streamToFile(file.getContent(), "src/images/" + file.getFilename());
        });

        //Get all Images
        app.get("/api/images", (req, res) -> {
            List<Attachment> images = db.getImageHeaders();
            res.json(images);
        });

        // Get Image by id
        app.get("api/images/:id", (req, res) ->{
            int id = Integer.parseInt(req.params("id"));
            Attachment attachment = db.getImageHeaderById(id);
            res.json(attachment);
        });

        app.delete("api/images/:id", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            Attachment attachment = db.getImageHeaderById(id);

            String imageUrl =  "./src/www" + attachment.getFileUrl();
            Path filePath = Paths.get(imageUrl);


            System.out.println(filePath);
            Boolean test = db.deleteImage(attachment);
            //System.out.print(test);
            Files.deleteIfExists(filePath);

            //System.out.println(attachment.toString());



            res.json(filePath);

        });



        //upload Files
        app.post("/api/files", (req, res) -> {
            //List<UploadedFile> files = req.formDataFiles("files");  // get files as list
            UploadedFile file = req.formDataFile("files");          // get a single file
            String header = req.formData("header"); // created header key-value

            // with FileOutputStream
            Path path = Paths.get("src/www/documents/1/" + file.getFilename());

            try (FileOutputStream os = new FileOutputStream(path.toString())) {
                os.write(file.getContent().readAllBytes()); // write to file
            }


            Attachment attachment = new Attachment(path.toString(), 1, header); // created attachment object

            boolean test = db.setFilesUrl(attachment);
            System.out.println(test); // debug statement true or false
            res.send("OK");
            // with FileUtil (creates dirs if necessary)
            //FileUtil.streamToFile(file.getContent(), "src/files/1/" + file.getFilename());
        });

        // Get all Files
        app.get("/api/files", (req, res) -> {
            List<Attachment> files = db.getFileHeaders();
            res.json(files);
        });

        // Get File by id
        app.get("api/files/:id", (req, res) ->{
            int id = Integer.parseInt(req.params("id"));
            Attachment attachment = db.getFileHeaderById(id);
            res.json(attachment);
        });

        // Delete file   /
        app.delete("api/files/:id", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            Attachment attachment = db.getFileHeaderById(id);

            String fileUrl = "./src/www" + attachment.getFileUrl();
            Path filePath = Paths.get(fileUrl);


            System.out.println(filePath);
            Boolean test = db.deleteFile(attachment);
            //System.out.print(test);
            Files.deleteIfExists(filePath);

            //System.out.println(attachment.toString());



            res.json(filePath);

        });

        // Server loop
        app.listen(3000);
        System.out.println("Server started on port 3000");

    }

}
