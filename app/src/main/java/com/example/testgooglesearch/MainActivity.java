package com.example.testgooglesearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.testgooglesearch.DB.DataBase;
import com.example.testgooglesearch.DB.SimpleItemResponse;
import com.example.testgooglesearch.DB.DAO.TextSearchResultDao;
import com.example.testgooglesearch.DB.TextSearchResultForSave;
import com.example.testgooglesearch.Interfaces.OnGoogleFetchedListener;
import com.example.testgooglesearch.Models.ResponseModels.GoogleResponse;
import com.example.testgooglesearch.Models.GoogleSearchResultBody;
import com.example.testgooglesearch.Models.ResponseModels.ItemResponse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static android.content.SharedPreferences.*;



public class MainActivity extends AppCompatActivity {

    private GoogleSearchResultBody mGoogleSearchResultBody;
    private List<ItemResponse> mItemResponse;
    private RecyclerAdapter mRecyclerAdapter;
    private Button mSearchButton;
    private EditText mSearchEditText;
    private GoogleSearch mGoogleSearch;
    private TextSearchResultDao mTextSearchResultDao;
    private List<String> mHistorySearchEditText;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Init();

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGoogleSearchResultBody.setText(getTextFromEditText());
                mGoogleSearch.setGoogleSearchResultBody(mGoogleSearchResultBody);
                mGoogleSearch.getResponse();
                mGoogleSearch.setListener(new OnGoogleFetchedListener() {
                    @Override
                    public void getResult(GoogleResponse googleResponse) {
                        if(googleResponse!=null) {
                            mItemResponse = googleResponse.getItems();
                            mRecyclerAdapter.setItem(mItemResponse);

                            Runnable run = new Runnable() {
                                @Override
                                public void run() {
                                    List<TextSearchResultForSave> textSearchResultForSaveList = new ArrayList<>();
                                    for (ItemResponse item : mItemResponse){
                                        SimpleItemResponse simpleItemResponse = new SimpleItemResponse(item.getTitle(),
                                                item.getFormattedUrl(),
                                                item.getSnippet());
                                        textSearchResultForSaveList.add(new TextSearchResultForSave(getTextFromEditText(),
                                                                                                     simpleItemResponse));
                                    }
                                    mHistorySearchEditText = onGetHistory();
                                    if(!mHistorySearchEditText.contains(getTextFromEditText())){
                                        mTextSearchResultDao.insert(textSearchResultForSaveList);
                                        mHistorySearchEditText.add(getTextFromEditText());
                                        onSaveHistory(mHistorySearchEditText);
                                    }
                                }
                            };
                            Thread thread = new Thread(run);
                            thread.start();
                        }
                    }
                });
            }
        });
    }

    /**
     * Метод инициализации
     */
    private void Init(){

        if(mHistorySearchEditText == null){
            mHistorySearchEditText = new ArrayList<>();
        }else{
            mHistorySearchEditText = onGetHistory();
        }

        mItemResponse = new ArrayList<>();

        mGoogleSearch = new GoogleSearch();
        mGoogleSearchResultBody = new GoogleSearchResultBody(getString(R.string.key_google_api),
                                                             getString(R.string.cx_search_engine),
                                                        "",
                                                             getString(R.string.lang_google_search));

        mSearchEditText = findViewById(R.id.searchEditText);
        mSearchButton = findViewById(R.id.searchButton);
        RecyclerView resultRecyclerView = findViewById(R.id.resultSearchRecyclerView);
        resultRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerAdapter = new RecyclerAdapter();
        resultRecyclerView.setAdapter(mRecyclerAdapter);

        DataBase dataBase = App.getInstance().getDatabase();
        mTextSearchResultDao = dataBase.textSearchResultDao();
    }


    private String getTextFromEditText(){
        return mSearchEditText.getText().toString();
    }

    /**
     * Метод, который сохраняет историю поиска в SharedPreferences
     * @param historyList - история поиска
     */
    private void onSaveHistory(List<String> historyList){
        mSharedPreferences = getPreferences(MODE_PRIVATE);
        Set<String> historySet = new HashSet<>(historyList);
        Editor e = mSharedPreferences.edit();
        e.putStringSet("strSetKey", historySet);
        e.apply();
    }

    /**
     * Метод, который загружает историю поиска из SharedPreferences
     * @return
     */
    private List<String> onGetHistory(){
        mSharedPreferences = getPreferences(MODE_PRIVATE);
        Set<String> set = mSharedPreferences.getStringSet("strSetKey", new HashSet<String>());
        return new ArrayList<>(set);
    }
}
