package com.keegan.android.todov2;

import java.util.Date;

public class ToDo {
    private int id;
    private String task;
    //what type should I make this
    private Date dueDate;

    public ToDo(int newID, String newTask, Date newDate)
    {
        setId(newID);
        setTask(newTask);
        setDueDate(newDate);
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getTask() {return task;}

    public void setTask(String task) {this.task = task;}

    public Date getDueDate() {return dueDate;}

    public void setDueDate(Date dueDate) {this.dueDate = dueDate;}
}
