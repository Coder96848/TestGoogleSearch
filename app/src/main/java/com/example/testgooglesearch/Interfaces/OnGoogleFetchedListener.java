package com.example.testgooglesearch.Interfaces;

import com.example.testgooglesearch.Models.ResponseModels.GoogleResponse;

/**
 * Интерфейс слушателя, передающего ответ от API Google Custom Search в MainActivity
 */
public interface OnGoogleFetchedListener {
    void getResult(GoogleResponse googleResponse);
}
