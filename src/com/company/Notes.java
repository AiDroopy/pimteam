package com.company;

public class Notes {
    private int id;
    private String notes;
    private int userId;
    private String header;

    //default constructor
    public Notes() {
    }

    public Notes(int id, String notes, int userId, String header) {
        this.id = id;
        this.notes = notes;
        this.userId = userId;
        this.header = header;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "id=" + id +
                ", notes='" + notes + '\'' +
                ", userId=" + userId +
                ", header='" + header + '\'' +
                '}';
    }




    // getters and setters
    public int getId() {
        return id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
