package io.agora.openlive;

import java.sql.Timestamp;

public class Task {

    String assigned_floor,
            assigned_room,
            assigned_method,
            assigned_time_in,
            assigned_time_out;


    public Task(){}

    public Task(String assigned_floor, String assigned_room, String assigned_method, String assigned_time_in, String assigned_time_out ) {
        this.assigned_floor = assigned_floor;
        this.assigned_room = assigned_room;
        this.assigned_method = assigned_method;
        this.assigned_time_in= assigned_time_in;
        this.assigned_time_out= assigned_time_out;
    }

    public String getassigned_floor() {
        return assigned_floor;
    }

    public void setassigned_floor(String assigned_floor) {
        this.assigned_floor = assigned_floor;
    }

    public String getassigned_room() {
        return assigned_room;
    }
//
    public void setassigned_room(String assigned_room) {
        this.assigned_room = assigned_room;
    }



     public String getassigned_method() {
        return assigned_method;
    }

   public void setassigned_method(String assigned_method) {
       this.assigned_method = assigned_method;
   }



    public String getassigned_time_in() {
        return assigned_time_in;
    }

    public void setassigned_time_in(String assigned_time_in) {
        this.assigned_time_in = assigned_time_in;
    }







    public String getassigned_time_out() {
        return assigned_time_out;
    }

    public void setassigned_time_out(String assigned_time_out) {
        this.assigned_time_out = assigned_time_out;
    }




}
