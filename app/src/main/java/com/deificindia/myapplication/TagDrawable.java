package com.deificindia.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class TagDrawable {

    Context context;
    String text;
    String textColor = "#FFFFFF", iconColor = "#FFFFFF", bgColor = "#000000";

    public TagDrawable(Context context) {
        this.context = context;
    }

    public static TagDrawable get(Context context){
        return new TagDrawable(context);
    }


    public Drawable drawable(int bgcolor, int textColor, String tag, Drawable icon, int iconTint){
        View tagv = buildTag(context, bgcolor, textColor, tag, icon, iconTint);

        return view2drawable(tagv);

    }

    public Drawable view2drawable(View view){

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

        Drawable drawable1 = new BitmapDrawable(context.getResources(), bitmap);
        drawable1.setBounds(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        return drawable1;
    }

    View buildTag(Context context, int bgcolor, int textColor, String tag, Drawable icon, int iconTint){

        ViewGroup.LayoutParams group = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(group);
        linearLayout.setPadding(dip2px(8), dip2px(2), dip2px(8), dip2px(2));


        TextView textView = new TextView(context);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, dip2px(8));


        SpannableStringBuilder ssb = new SpannableStringBuilder();

        //Drawable drawable = ContextCompat.getDrawable(context, icon);
        icon.setBounds(0, 0, dip2px(8), dip2px(8));

        if(iconTint!=0){
            icon.setColorFilter(iconTint, PorterDuff.Mode.SRC_IN);
        }

        ssb.append(" ");
        ImageSpan imageSpan = new ImageSpan(icon, ImageSpan.ALIGN_CENTER);
        ssb.setSpan(imageSpan, ssb.length()-1, ssb.length() , 0);

        int start= ssb.length();
        String str = " " + tag;
        ssb.append(str);
        ssb.setSpan(new ForegroundColorSpan(textColor),
                start, // start
                start + str.length(), // end
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        textView.setText(ssb);

        Drawable draw = DrawableRectBuilder.get(context)
                .colors(bgcolor, bgcolor)
                .corner(dip2px(18))
                //.stroke(5, col)
                .build();

        linearLayout.setBackground(draw);

        linearLayout.addView(textView);

        return linearLayout;

    }

    public int dip2px(float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
