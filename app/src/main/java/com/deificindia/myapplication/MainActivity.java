package com.deificindia.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);
    final StyleSpan iss = new StyleSpan(android.graphics.Typeface.ITALIC);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txt = findViewById(R.id.txt);

        SpannableStringBuilder ssb = new SpannableStringBuilder(" ");

        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_verified_live);

        float scale = txt.getContext().getResources().getDisplayMetrics().density;
        int width = (int) (12 * scale + 0.5f);
        int height = (int) (12 * scale + 0.5f);
        drawable.setBounds(0, 0, width, height);

        ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_CENTER);
        ssb.setSpan(imageSpan, ssb.length()-1, ssb.length() , 0);

        ssb.append(" ");
        ImageSpan imageSpan1 = new ImageSpan(drawable, ImageSpan.ALIGN_CENTER);
        ssb.setSpan(imageSpan1, ssb.length()-1, ssb.length() , Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        int start= ssb.length();
        String str = "name : ";
        ssb.append(str);
        ssb.setSpan(bss, start, start + str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.setSpan(new ForegroundColorSpan(Color.RED),
                start, // start
                start + str.length(), // end
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        String tx = "name hello hello hello helloe qwerty poi hloo sddf dfsdf sdfsdf  fsd s  ";
        ssb.append(tx);

        //ssb.setSpan(txt, index, index + tx.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        //ssb.setSpan(bss, 0, 4, Spannable.SPAN_INCLUSIVE_INCLUSIVE);


        txt.setText(ssb);


    }
}