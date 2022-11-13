package com.keegan.android.todov2;

import java.util.Date;

public class ToDo {
    private int id;
    private String task;
    private String dueDate;

    public ToDo(int newID, String newTask, String newDate)
    {
        setId(newID);
        setTask(newTask);
        setDueDate(newDate);
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getTask() {return task;}

    public void setTask(String task) {this.task = task;}

    public String getDueDate() {return dueDate;}

    public void setDueDate(String dueDate) {this.dueDate = dueDate;}
}
