package com.elegion.android.template.extension.android.widget

import android.text.TextWatcher
import android.widget.TextView

inline fun TextView.addTextChangedListener(init: KTextWatcher.() -> Unit): TextWatcher =
    KTextWatcher().apply {
        init()
        addTextChangedListener(this)
    }