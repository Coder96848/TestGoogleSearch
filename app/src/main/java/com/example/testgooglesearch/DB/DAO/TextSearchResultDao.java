package com.example.testgooglesearch.DB.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.testgooglesearch.DB.TextSearchResultForSave;

import java.util.List;

/**
 * Объект доступа к БД
 */
@Dao
public interface TextSearchResultDao {

    /**
     *
     * @return все записи из БД
     */
    @Query("SELECT * FROM TextSearchResultForSave")
    List<TextSearchResultForSave> getAll();

    /**
     *
     * @param text - текст запроса
     * @return записи по поиску определенного запроса
     */
    @Query("SELECT * FROM TextSearchResultForSave WHERE textSearch = :text")
    List<TextSearchResultForSave> getOneRecord(String text);

    @Query("SELECT * FROM TextSearchResultForSave WHERE id = :id")
    TextSearchResultForSave getById(int id);

    @Insert
    void insert(List<TextSearchResultForSave> textSearchResultForSaveList);

    @Update
    void update(TextSearchResultForSave textSearchResultForSave);

    @Delete
    void delete(TextSearchResultForSave textSearchResultForSave);
}
