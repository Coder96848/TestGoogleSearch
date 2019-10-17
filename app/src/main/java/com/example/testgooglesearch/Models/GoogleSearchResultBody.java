package com.example.testgooglesearch.Models;

/**
 * Модель для тела запроса к API Google Custom Search
 */
public class GoogleSearchResultBody {

    /**
     *  key - API-key
     *  cx - идентификатор поискового движка
     *  text - текст поиска
     *  lang - приоритетный регион поиска, в нашем случае - ru-регион
     */
    private String key;
    private String cx;
    private String text;
    private String lang;

    public GoogleSearchResultBody(String key, String cx, String text, String lang) {
        this.key = key;
        this.cx = cx;
        this.text = text;
        this.lang = lang;
    }

    public GoogleSearchResultBody() {
    }

    public String getKey() {
        return key;
    }

    public String getCx() {
        return cx;
    }

    public String getLang() {
        return lang;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
