package com.if5a.pab2.contact.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.if5a.pab2.contact.entities.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertUser(User user);

    @Query("sElECt * FrOM users")
    List<User> getAllUsers();

    @Update
    int updateUser(User user);

    @Query("DeLEte fRoM users wHErE id= :userId")
    int deleteUser(int userId);
}
