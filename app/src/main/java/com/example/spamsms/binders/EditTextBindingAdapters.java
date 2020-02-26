package com.example.spamsms.binders;

import android.text.TextWatcher;
import android.widget.EditText;

import androidx.databinding.BindingAdapter;

public class EditTextBindingAdapters {

    @BindingAdapter("textChangedListener")
    public static void bindTextWatcher(EditText editText, TextWatcher textWatcher) {
        editText.addTextChangedListener(textWatcher);
    }

    @BindingAdapter("userTextChangeListener")
    public static void bindUserTextWatcher(EditText editText, TextWatcher textWatcher) {
        editText.addTextChangedListener(textWatcher);
    }
}
