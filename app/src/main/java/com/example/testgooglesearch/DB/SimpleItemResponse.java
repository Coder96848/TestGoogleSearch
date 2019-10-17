package com.example.testgooglesearch.DB;

/**
 * Упрощенная модель данных, используемых для вывода на экран, которые нужно сохранить
 */
public class SimpleItemResponse {

    /**
     * title - заголовок результата поиска
     * url - ссылка результата поиска
     * description - описание результата поиска
     */
    public String title;
    public String url;
    public String description;

    public SimpleItemResponse(String title, String url, String description) {
        this.title = title;
        this.url = url;
        this.description = description;
    }
}
