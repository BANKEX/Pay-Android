package com.elegion.android.template.ui.widget

import android.content.Context
import android.support.annotation.DrawableRes
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import com.elegion.android.template.R
import com.elegion.android.template.util.ViewUtils
import kotlinx.android.synthetic.main.w_empty_view.view.*

open class EmptyView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    init {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        View.inflate(context, getLayout(), this)

        val a = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.EmptyView,
            0, 0
        )

        try {
            val resourceId = a.getResourceId(R.styleable.EmptyView_icon, 0)
            if (resourceId != 0) {
                emptyViewIcon.setImageResource(resourceId)
            }

            emptyViewText.text = a.getString(R.styleable.EmptyView_text)
            val buttonText = a.getString(R.styleable.EmptyView_buttonText)
            if (!TextUtils.isEmpty(buttonText)) {
                getButton().apply {
                    text = buttonText
                    visibility = View.VISIBLE
                }
            } else {
                getButton().visibility = View.GONE
            }
        } finally {
            a.recycle()
        }
    }

    @LayoutRes
    protected open fun getLayout(): Int = R.layout.w_empty_view

    protected open fun getButton(): Button = emptyViewButton

    fun setButtonClickListener(listener: View.OnClickListener?) = getButton().setOnClickListener(listener)

    fun bindEmptyView(
        @DrawableRes icon: Int,
        @StringRes text: Int,
        @StringRes buttonText: Int,
        listener: View.OnClickListener?
    ) {
        emptyViewIcon.setImageResource(icon)
        emptyViewText.setText(text)
        getButton().apply {
            visibility = View.VISIBLE
            setText(buttonText)
            setOnClickListener(listener)
        }
    }

    fun bindEmptyView(@DrawableRes icon: Int, @StringRes text: Int) {
        emptyViewIcon.setImageResource(icon)
        emptyViewText.setText(text)
        ViewUtils.setVisibility(View.GONE, getButton())
    }

    fun bindEmptyView(@StringRes text: Int) {
        emptyViewText.setText(text)
        ViewUtils.setVisibility(View.GONE, emptyViewIcon, getButton())
    }

    fun bindEmptyView(text: String) {
        emptyViewText.text = text
        ViewUtils.setVisibility(View.GONE, emptyViewIcon, getButton())
    }
}
