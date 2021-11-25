package com.deificindia.myapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;

import androidx.annotation.ColorInt;

import java.lang.ref.WeakReference;

public class ChatMessageBuilder {

    public static final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);
    public static final StyleSpan iss = new StyleSpan(android.graphics.Typeface.ITALIC);

    WeakReference<Context> context;
    SpannableStringBuilder ssb = new SpannableStringBuilder();

    public ChatMessageBuilder(Context context) {
        this.context = new WeakReference<>(context);
    }

    public static ChatMessageBuilder get(Context context){
        return new ChatMessageBuilder(context);
    }

    public ChatMessageBuilder drawDrawable(Drawable... drawables){
        if(drawables.length>0){
            for(Drawable drawable : drawables){

                int width = dip2px(context.get(), 12);
                int height = dip2px(context.get(), 12);
                drawable.setBounds(0, 0, width, height);

                ssb.append("  ");
                ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_CENTER);
                ssb.setSpan(imageSpan, ssb.length()-1, ssb.length() , Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            }
        }

        return this;
    }

    public static class TagObject{
        public int bgcolor;
        public int textColor;
        public String tag;
        public Drawable icon;
        public int iconTint;

        public TagObject(int bgcolor, int textColor, String tag, Drawable icon, int iconTint) {
            this.bgcolor = bgcolor;
            this.textColor = textColor;
            this.tag = tag;
            this.icon = icon;
            this.iconTint = iconTint;
        }
    }

    public ChatMessageBuilder drawTags(TagObject... tags){

        for(TagObject tag:tags){
            Drawable drawable1 =  TagDrawable.get(context.get())
                    .drawable(tag.bgcolor, tag.textColor, tag.tag, tag.icon, tag.iconTint);

            ssb.append("  ");
            ImageSpan imageSpan2 = new ImageSpan(drawable1, ImageSpan.ALIGN_CENTER);
            ssb.setSpan(imageSpan2, ssb.length()-1, ssb.length() , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        return this;
    }

    public static class Tag{
        public String text;
        @ColorInt
        public int textColor;
        public StyleSpan textStyle;

        public Tag(String text, @ColorInt int textColor, StyleSpan textStyle) {
            this.text = text;
            this.textColor = textColor;
            this.textStyle = textStyle;
        }
    }

    public ChatMessageBuilder drawText(Tag... tags){
        for (Tag tag:tags){
            int start= ssb.length();
            ssb.append(tag.text);

            if(tag.textStyle!=null) {
                ssb.setSpan(tag.textStyle, start, start + tag.text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            //if(tag.textColor>0) {
                ssb.setSpan(new ForegroundColorSpan(tag.textColor),
                        start, // start
                        start + tag.text.length(), // end
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                );
            //}
        }

        return this;
    }

    public SpannableStringBuilder build(){
        return ssb;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
