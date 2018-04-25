package com.turastory.jamquery.presentation.ui.jamquery_list;

import android.text.Editable;
import android.text.TextWatcher;

import com.annimon.stream.Optional;
import com.annimon.stream.function.Predicate;

/**
 * Created by tura on 2018-04-25.
 * <p>
 * 입력된 값을 검사하는 Text Watcher
 */
public class ValidationTextWatcher implements TextWatcher {
    
    private final Predicate<String> validationLogic;
    private Optional<OnValidListener> validListenerOpt = Optional.empty();
    private Optional<OnInvalidListener> invalidListenerOpt = Optional.empty();
    
    public ValidationTextWatcher(Predicate<String> validationLogic) {
        this.validationLogic = validationLogic;
    }
    
    public ValidationTextWatcher setValidListener(OnValidListener validListener) {
        this.validListenerOpt = Optional.of(validListener);
        return this;
    }
    
    public ValidationTextWatcher setInvalidListener(OnInvalidListener invalidListener) {
        this.invalidListenerOpt = Optional.of(invalidListener);
        return this;
    }
    
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    
    }
    
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    
    }
    
    @Override
    public void afterTextChanged(Editable s) {
        String value = s.toString();
        
        if (validationLogic.test(value)) {
            validListenerOpt.ifPresent(listener -> listener.onValid(value));
        } else {
            invalidListenerOpt.ifPresent(listener -> listener.onInvalid(value));
        }
    }
    
    public interface OnValidListener {
        void onValid(String s);
    }
    
    public interface OnInvalidListener {
        void onInvalid(String s);
    }
}
