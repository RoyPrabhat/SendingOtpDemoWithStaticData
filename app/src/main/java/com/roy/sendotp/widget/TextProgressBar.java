package com.roy.sendotp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.roy.sendotp.R;


/**
 * @author prabhat.roy
 *         <p>
 *         This class Implements a Custom progressbar which shows a spinner with the ability
 *         to show status text below the spinner.
 */
public class TextProgressBar extends LinearLayout {

    private final static String TAG = "TextProgressBar";
    private TextView textView;

    public TextProgressBar(Context context) {
        super(context);
        initializeViews(context);
    }

    public TextProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeViews(context);
    }

    public TextProgressBar(Context context,
                           AttributeSet attrs,
                           int defStyle) {
        super(context, attrs, defStyle);
        initializeViews(context);
    }

    public void setText(String text) {
        textView.setText(text);
    }

    public String getText() {
        return textView.getText().toString();
    }

    public void setTextColor(int color) {
        textView.setTextColor(color);
    }

    /**
     * Inflates the views in the layout.
     *
     * @param context the current context for the view.
     */
    private void initializeViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.widget_text_progress_bar, this);
        textView = (TextView) view.findViewById(R.id.progress_bar_text_view);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }
}
