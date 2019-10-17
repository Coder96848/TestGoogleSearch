package com.example.testgooglesearch.DB;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Класс-сущность, которая сохраняется в БД
 * textSearch - текст, по которому осуществлялся запрос
 * simpleItemResponse - данные вида "заголовок, сслыка, описание"
 */
@Entity
public class TextSearchResultForSave {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String textSearch;

    @Embedded
    private SimpleItemResponse simpleItemResponse;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextSearch() {
        return textSearch;
    }

    public void setTextSearch(String textSearch) {
        this.textSearch = textSearch;
    }

    public SimpleItemResponse getSimpleItemResponse() {
        return simpleItemResponse;
    }

    public void setSimpleItemResponse(SimpleItemResponse simpleItemResponse) {
        this.simpleItemResponse = simpleItemResponse;
    }

    public TextSearchResultForSave() {
    }

    public TextSearchResultForSave(String textSearch, SimpleItemResponse simpleItemResponse) {
        this.textSearch = textSearch;
        this.simpleItemResponse = simpleItemResponse;
    }
}
