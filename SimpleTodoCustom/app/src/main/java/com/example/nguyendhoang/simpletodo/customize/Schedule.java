package com.example.nguyendhoang.simpletodo.customize;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Nguyen.D.Hoang on 9/29/2016.
 */

@Table(name = "Schedule")
public class Schedule extends Model implements Serializable{
    @Column(name = "TaskName")
    public String taskName;

    @Column(name = "Description")
    public String description;

    @Column(name = "Priority")
    public String priorityLevel;

    @Column(name = "Status")
    public String Status;

    public Schedule(String taskName, String description, String priorityLevel, String status) {
        super();
        this.taskName = taskName;
        this.description = description;
        this.priorityLevel = priorityLevel;
        Status = status;
    }

    public Schedule() {
        super();
    }

    public static List<Schedule> GetAllSchedule(){
        return new Select().from(Schedule.class).execute();
    }

    public static boolean DeleteScheduleById(Schedule schedule){
        Schedule item = Schedule.load(Schedule.class, schedule.getId());
        try {
            item.delete();
        }catch (Exception e){
            return false;
        }
        return true;

    }
}
