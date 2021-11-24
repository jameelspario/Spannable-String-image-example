package com.deificindia.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);
    final StyleSpan iss = new StyleSpan(android.graphics.Typeface.ITALIC);

    String age = "20  ", gender = "FEMALE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout rel = findViewById(R.id.rel);
        TextView txt = findViewById(R.id.txt);

        SpannableStringBuilder ssb = new SpannableStringBuilder(" ");

        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_verified_live);

        float scale = txt.getContext().getResources().getDisplayMetrics().density;
        int width = (int) (12 * scale + 0.5f);
        int height = (int) (12 * scale + 0.5f);
        drawable.setBounds(0, 0, width, height);

        ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_CENTER);
        ssb.setSpan(imageSpan, ssb.length()-1, ssb.length() , 0);

        ssb.append("  ");
        ImageSpan imageSpan1 = new ImageSpan(drawable, ImageSpan.ALIGN_CENTER);
        ssb.setSpan(imageSpan1, ssb.length()-1, ssb.length() , Spannable.SPAN_INCLUSIVE_EXCLUSIVE);


        if(age!=null && gender!=null){

            Drawable drawable1 =  tag(this);

            ssb.append("  ");
            ImageSpan imageSpan2 = new ImageSpan(drawable1, ImageSpan.ALIGN_CENTER);
            ssb.setSpan(imageSpan2, ssb.length()-1, ssb.length() , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        }

        int start= ssb.length();
        String str = " name : ";
        ssb.append(str);
        ssb.setSpan(bss, start, start + str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#E91E63")),
                start, // start
                start + str.length(), // end
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        start= ssb.length();
        str = "name hello hello hello helloe qwerty poi hloo sddf dfsdf sdfsdf  fsd s  ";
        ssb.append(str);

        ssb.setSpan(new ForegroundColorSpan(Color.WHITE),
                start, // start
                start + str.length(), // end
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );


        //ssb.setSpan(txt, index, index + tx.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        //ssb.setSpan(bss, 0, 4, Spannable.SPAN_INCLUSIVE_INCLUSIVE);


        txt.setText(ssb);

        int col = Color.parseColor("#11000000");
        Drawable draw = DrawableRectBuilder.get(this)
                .colors(R.color.white, R.color.purple_200)
                //.colors(Color.parseColor(DrawableRectBuilder.trans_gray), Color.parseColor(DrawableRectBuilder.trans_gray))
                //.colors(Color.parseColor("#AB03A9F4"), Color.parseColor("#AB03A9F4"))
                //.colors(Color.BLACK, Color.WHITE)
                .corner(12)
                .stroke(5, col)
                .build();

        rel.setBackground(draw);


    }

    Shader textGradient(int w, int h){
        //TextPaint paint = textView.getPaint();
        //float width = paint.measureText(txt);

        Shader textShader = new LinearGradient(0, 0, w, h,
                new int[]{
                        Color.parseColor("#F97C3C"),
                        Color.parseColor("#FDB54E"),
                        Color.parseColor("#64B678"),
                        Color.parseColor("#478AEA"),
                        Color.parseColor("#8446CC"),
                }, null, Shader.TileMode.CLAMP);
        //textView.getPaint().setShader(textShader);

        return textShader;
    }

    void textGradient1(TextView textView, String txt){
        TextPaint paint = textView.getPaint();
        float width = paint.measureText(txt);

        Shader textShader = new LinearGradient(0, 0, width, textView.getTextSize(),
                new int[]{
                        Color.parseColor("#F97C3C"),
                        Color.parseColor("#FDB54E"),
                        Color.parseColor("#64B678"),
                        Color.parseColor("#478AEA"),
                        Color.parseColor("#8446CC"),
                }, null, Shader.TileMode.CLAMP);
        textView.getPaint().setShader(textShader);
    }

    void fromUrl(String imgUrl){
        try {
            URL appImgUrlLink = new URL(imgUrl);
            Bitmap bitmap = BitmapFactory.decodeStream(appImgUrlLink.openConnection().getInputStream());
            Drawable drawable = new BitmapDrawable(getResources(), bitmap);

            float scale = getResources().getDisplayMetrics().density;
            int width = (int) (12 * scale + 0.5f);
            int height = (int) (12 * scale + 0.5f);
            drawable.setBounds(0, 0, width, height);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Bitmap getViewBitmap(View view)
    {

        if (view.getMeasuredHeight() <= 0) {
            view.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
          /*  Bitmap b = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(b);
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
            view.draw(c);
            return b;*/
        }


        //Get the dimensions of the view so we can re-layout the view at its current size
        //and create a bitmap of the same size
        int width = view.getWidth();
        int height = view.getHeight();

        int measuredWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        int measuredHeight = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);

        //Cause the view to re-layout
        view.measure(measuredWidth, measuredHeight);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        //Create a bitmap backed Canvas to draw the view into
        Bitmap b = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);

        //Now that the view is laid out and we have a canvas, ask the view to draw itself into the canvas
        view.draw(c);

        return b;
    }

    public Bitmap createBitmapFromLayoutWithText(Context context) {
        LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Inflate the layout into a view and configure it the way you like
        RelativeLayout view = new RelativeLayout(context);
        mInflater.inflate(R.layout.layout_tag_view, view, true);
        //TextView tv = (TextView) findViewById(R.id.my_text);
        //tv.setText("Beat It!!");

        //Provide it with a layout params. It should necessarily be wrapping the
        //content as we not really going to have a parent for it.
        view.setLayoutParams(new ViewGroup.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));

        //Pre-measure the view so that height and width don't remain null.
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

        //Assign a size and position to the view and all of its descendants
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        //Create the bitmap
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(),
                view.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        //Create a canvas with the specified bitmap to draw into
        Canvas c = new Canvas(bitmap);

        //Render this view (and all of its children) to the given Canvas
        view.draw(c);
        return bitmap;
    }

    Drawable tag(Context context){
        LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Inflate the layout into a view and configure it the way you like
        RelativeLayout view = new RelativeLayout(context);
        mInflater.inflate(R.layout.layout_tag_view, null, false);
        TAGView tagView = new TAGView(context);

        RelativeLayout.LayoutParams para = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                //getResources().getDimensionPixelSize(R.dimen._24dp),
                getResources().getDimensionPixelSize(R.dimen._16dp)
        );
        //tagView.setLayoutParams(para);

        view.addView(tagView, para);

        int bgcolor;
        int gendertag;
        if(gender.equalsIgnoreCase("Male")){
            bgcolor = R.color.male_color;
            gendertag = R.drawable.ic_male_gender;

        }else {
            bgcolor = R.color.female_color;
            gendertag = R.drawable.ic_female_sign;

        }

        tagView.setIcon(gendertag);
        tagView.iconTint(R.color.white);

        tagView.setImageWidth(10);
        tagView.setRadius(16);
        tagView.setText(age,
                getResources().getColor(R.color.white),
                getResources().getDimensionPixelSize(R.dimen._10dp));

        tagView.setBackgroundColor(getResources().getColor(bgcolor));
        tagView.setDividerWidth(4);
        tagView.setPadding(6, 4, 6, 4);


        view.setLayoutParams(new ViewGroup.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));

        //Pre-measure the view so that height and width don't remain null.
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

        //Assign a size and position to the view and all of its descendants
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        //Create the bitmap
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(),
                view.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        //Create a canvas with the specified bitmap to draw into
        Canvas c = new Canvas(bitmap);

        //Render this view (and all of its children) to the given Canvas
        view.draw(c);

        Drawable drawable1 = new BitmapDrawable(getResources(), bitmap);
        drawable1.setBounds(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        return drawable1;
    }


}