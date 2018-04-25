package com.turastory.jamquery.presentation.ui.jamquery_list.add;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.turastory.jamquery.R;
import com.turastory.jamquery.presentation.ui.jamquery_list.ValidationTextWatcher;

/**
 * Created by tura on 2018-04-25.
 * <p>
 * Jamquery 추가 Dialog
 */
public class AddJamqueryDialog extends Dialog {
    
    public AddJamqueryDialog(@NonNull Context context, OnCompleteListener onCompleteListener) {
        super(context);
        setContentView(R.layout.dialog_add_jamquery);
        
        TextView urlMessage = findViewById(R.id.text_message_url);
        
        ((EditText) findViewById(R.id.edit_text_url)).addTextChangedListener(
            new ValidationTextWatcher(string -> Patterns.WEB_URL.matcher(string).matches())
                .setInvalidListener(s -> {
                    urlMessage.setVisibility(View.VISIBLE);
                    urlMessage.setText(R.string.message_invalid_url);
                })
                .setValidListener(s -> {
                    urlMessage.setVisibility(View.GONE);
                }));
        
        findViewById(R.id.button_add).setOnClickListener(v -> {
            if (onCompleteListener != null) {
                if (validUrl()) {
                    onCompleteListener.onComplete(
                        ((EditText) findViewById(R.id.edit_text_title)).getText().toString(),
                        ((EditText) findViewById(R.id.edit_text_url)).getText().toString());
                }
            }
            
            dismiss();
        });
    }
    
    private boolean validUrl() {
        return !urlMessage().equals(getContext().getString(R.string.message_invalid_url));
    }
    
    @NonNull
    private String urlMessage() {
        return ((TextView) findViewById(R.id.text_message_url)).getText().toString();
    }
    
    public interface OnCompleteListener {
        void onComplete(String title, String url);
    }
}
