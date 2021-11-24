package com.deificindia.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

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

        int index = ssb.length();
        String tx = "name hello hello hello helloe qwerty poi hloo sddf dfsdf sdfsdf  fsd s  ";
        ssb.append(tx);

        //ssb.setSpan(txt, index, index + tx.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        ssb.append(" ");
        index = ssb.length();

        ImageSpan imageSpan1 = new ImageSpan(drawable, ImageSpan.ALIGN_CENTER);
        ssb.setSpan(imageSpan1, index-1, index , Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        txt.setText(ssb);


    }
}