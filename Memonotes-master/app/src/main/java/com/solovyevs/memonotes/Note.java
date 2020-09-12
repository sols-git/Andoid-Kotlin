package com.solovyevs.memonotes;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.GregorianCalendar;

@Entity //(tableName = "notes")
public class Note implements Serializable, Comparable<Note>, Cloneable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String bodyNote;
    private Long deadline;
    private Long dateUpdate;
    private Long dateCreate;



    public Note(String title, String bodyNote, Long deadline, Long dateCreate, Long dateUpdate) {
        this.id = id;
        this.title = title;
        this.bodyNote = bodyNote;
        this.deadline = deadline;
        this.dateUpdate = dateUpdate;
        this.dateCreate = dateCreate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Long dateCreate) {
        this.dateCreate = dateCreate;
    }

    public void setDateUpdate(Long dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Long getDateUpdate() {
        return dateUpdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBodyNote() {
        return bodyNote;
    }

    public void setBodyNote(String bodyNote) {
        this.bodyNote = bodyNote;
    }

    public Long getDeadline() {
        return deadline;
    }

    public void setDeadline(Long deadline) {
        this.deadline = deadline;
    }

    static public Long convertDateToLong(GregorianCalendar calendar)
    {
        return calendar.getTimeInMillis();
    }

    static public GregorianCalendar converLongToDate(Long date )
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(date);
        return calendar;
    }

    public Note clone() throws CloneNotSupportedException{

        return (Note) super.clone();
    }

    @Override
    public String toString() {
        return "Note{" +
                "title='" + title + '\'' +
                ", bodyNote='" + bodyNote + '\'' +
                ", deadline=" + deadline +
                '}';
    }

   @Override
    public int compareTo(Note o) {

        return deadline.compareTo(o.getDeadline());

    }


}
