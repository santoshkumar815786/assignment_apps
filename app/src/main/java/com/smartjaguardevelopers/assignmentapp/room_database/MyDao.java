package com.smartjaguardevelopers.assignmentapp.room_database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.smartjaguardevelopers.assignmentapp.room_database.User;

@Dao
public interface MyDao
{
    @Insert
    public void addUser(User user);

    @Query("Select password FROM users WHERE emailId LIKE :emailId")
    public String getUserPassword(String emailId);
}
