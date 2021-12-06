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


        } catch (SQLException e) {
            e.printStackTrace();

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return notes;
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


}
