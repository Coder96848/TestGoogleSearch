package com.example.testgooglesearch.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.testgooglesearch.DB.DAO.TextSearchResultDao;

/**
 * Класс БД
 */
@Database(entities = {TextSearchResultForSave.class}, version = 1)
public abstract class DataBase extends RoomDatabase {

    public abstract TextSearchResultDao textSearchResultDao();

}
