package com.turastory.jamquery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;

public class MainEntry extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_entry);
        
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        SearchView searchView = (SearchView) menu.getItem(0).getActionView();
        searchView.setQueryHint("여기에 입력하세요.");
        searchView.setOnSearchClickListener(v -> {
            Log.e("asdf", "OnSearchClick");
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e("asdf", "submit [" + query + "]");
                return false;
            }
        
            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e("asdf", "text changed to [" + newText + "]");
                return false;
            }
        });
        
        return super.onCreateOptionsMenu(menu);
    }
}
