package com.company;

import com.fasterxml.jackson.core.JsonProcessingException;
import nosqlite.utilities.Utils;

import java.sql.*;
import java.util.List;

public class Database {
    private Connection conn;

    public Database(){
        try {
            conn = DriverManager.getConnection(("jdbc:sqlite:database/pim.db"));
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }

    public List<Note> getAllNotes(){
        List<Note> notes = null;
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM notes");
            ResultSet rs = stmt.executeQuery();

            Note[] notesFromRS = (Note[]) Utils.resultSetToObject(rs, Note[].class);
            notes = List.of(notesFromRS);


        } catch (SQLException | JsonProcessingException e) {
            e.printStackTrace();

        }

        return notes;
    }

    public Note getNoteById(int id){
        Note note = null;

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Notes WHERE id=?");
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            Note[] noteFromRS = (Note[]) Utils.resultSetToObject(rs, Note[].class);

            note = noteFromRS[0];


        } catch (SQLException | JsonProcessingException e) {
            e.printStackTrace();
        }

        return note;
    }

    public void createNote(Note note){
        try {
            PreparedStatement stmt = conn.prepareStatement(("INSERT INTO Notes (notes, UserId, header) VALUES(?, ?, ?)"));
            stmt.setString(1, note.getNotes());
            stmt.setInt(2, note.getUserId());
            stmt.setString(3, note.getHeader());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateNote(Note note){
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE Notes SET notes=?, UserId=?, header=? WHERE id=?");
            stmt.setString(1, note.getNotes());
            stmt.setInt(2, note.getUserId());
            stmt.setString(3, note.getHeader());
            stmt.setInt(4, note.getId());

            stmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // Returns true if operation was a success, false if Error
    public boolean deleteNote(Note note){
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Notes WHERE id=?");
            stmt.setInt(1, note.getId());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }

    /**
     *
     * @param header
     * @return
     */
    // SELECT * FROM Notes WHERE header LIKE '%irs%'
    public Note[] getNoteByHeader(String header){
        Note[] note = null;

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Notes WHERE header LIKE ?");
            stmt.setString(1, "%"+ header +"%");

            ResultSet rs = stmt.executeQuery();

            Note[] headerFromRS = (Note[]) Utils.resultSetToObject(rs, Note[].class);

            note = headerFromRS;


        } catch (SQLException | JsonProcessingException e) {
            e.printStackTrace();
        }

        return note;
    }

    public Note[] getNoteByNotes(String searchNotes){
        Note[] note = null;

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Notes WHERE notes LIKE ?");
            stmt.setString(1, "%"+ searchNotes +"%");

            ResultSet rs = stmt.executeQuery();

            Note[] notesFromRS = (Note[]) Utils.resultSetToObject(rs, Note[].class);

            note = notesFromRS;


        } catch (SQLException | JsonProcessingException e) {
            e.printStackTrace();
        }

        return note;
    }

    public boolean setImageUrl(Attachment attachment){
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Image (userId, fileUrl, timestamp, header) VALUES(?, ?, ?, ?)");

            stmt.setInt(1, 1);
            stmt.setString(2, attachment.getFileUrl());
            stmt.setTimestamp(3, attachment.getTimestamp());
            stmt.setString(4, attachment.getHeader());



            stmt.executeUpdate();
            return true;


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Attachment> getImageHeaders(){
        List<Attachment> imageHeaders = null;
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Image");
            ResultSet rs = stmt.executeQuery();

            Attachment[] imageHeadersFromRs = Utils.resultSetToObject(rs, Attachment[].class);
            imageHeaders = List.of(imageHeadersFromRs);


        } catch (SQLException | JsonProcessingException e) {
            e.printStackTrace();

        }

        return imageHeaders;
    }

    public Attachment getImageHeaderById(int id){
        Attachment attachment = null;

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Image WHERE id=?");
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            Attachment[] attachmentFromRS = Utils.resultSetToObject(rs, Attachment[].class);

            attachment = attachmentFromRS[0];


        } catch (SQLException | JsonProcessingException e) {
            e.printStackTrace();
        }

        return attachment;
    }

    public boolean setFilesUrl(Attachment attachment){
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Files (userId, fileUrl, timestamp, header) VALUES(?, ?, ?, ?)");

            stmt.setInt(1, 1);
            stmt.setString(2, attachment.getFileUrl());
            stmt.setTimestamp(3, attachment.getTimestamp());
            stmt.setString(4, attachment.getHeader());



            stmt.executeUpdate();
            return true;


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Attachment> getFileHeaders(){
        List<Attachment> attachment = null;
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Files");
            ResultSet rs = stmt.executeQuery();

            Attachment[] filesHeadersFromRs = Utils.resultSetToObject(rs, Attachment[].class);
            attachment = List.of(filesHeadersFromRs);


        } catch (SQLException | JsonProcessingException e) {
            e.printStackTrace();

        }

        return attachment;
    }

    public Attachment getFileHeaderById(int id){
        Attachment attachment = null;

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Files WHERE id=?");
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            Attachment[] attachmentFromRS = Utils.resultSetToObject(rs, Attachment[].class);

            attachment = attachmentFromRS[0];


        } catch (SQLException | JsonProcessingException e) {
            e.printStackTrace();
        }

        return attachment;
    }
}
