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

    public List<Notes> getNotes(){
        List<Notes> notes = null;
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM notes");
            ResultSet rs = stmt.executeQuery();

            Notes[] notesFromRS = (Notes[]) Utils.resultSetToObject(rs, Notes[].class);
            notes = List.of(notesFromRS);


        } catch (SQLException e) {
            e.printStackTrace();

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return notes;
    }

    public void createNote(Notes note){
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


}
