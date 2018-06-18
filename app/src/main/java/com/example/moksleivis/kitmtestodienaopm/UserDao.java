package com.example.moksleivis.kitmtestodienaopm;

import android.database.Cursor;

public interface UserDao {

    boolean addUser(User user);
    //Get users for login informaition
    Cursor getUserData();
    //delete user
    void deleteUserEntry(String vardasPavarde, int telNr);
    //UPDATE ENTRY
    boolean updateUserEntry(String vardasPavarde, int gimimoMetai,int telNr, String klientoTipas);
}
