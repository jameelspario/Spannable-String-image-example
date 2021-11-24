package com.deificindia.myapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import androidx.annotation.ColorInt;
import androidx.core.content.ContextCompat;

import java.lang.ref.WeakReference;

public class DrawableRectBuilder {

    WeakReference<Context> weakReference;
    public static final String trans_gray = "#34000000";

    int[] color_list = null;
    float drawable_corner = 0f;

    int stroke_width = 0;

    @ColorInt
    int stroke_color = 0;

    public DrawableRectBuilder(Context cnx) {
        this.weakReference = new WeakReference<>(cnx);
    }

    public static DrawableRectBuilder get(Context cnx){
        return new DrawableRectBuilder(cnx);
    }

    public DrawableRectBuilder colors(int... colors){
        color_list = new int[colors.length];

        for(int i=0; i<colors.length; i++){

            color_list[i] = /*ContextCompat.getColor(weakReference.get(),*/ colors[i];
        }

        return this;
    }

    public DrawableRectBuilder corner(float corner){
        this.drawable_corner = corner;
        return this;
    }

    public DrawableRectBuilder stroke(int width, @ColorInt int color){
        this.stroke_width = width;
        this.stroke_color = color;
        return this;
    }

    public Drawable build(){
        GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, color_list);
        if(color_list!=null) {
            gd = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, color_list);
        }else {
            gd = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,
                    new int[]{ContextCompat.getColor(weakReference.get(), R.color.white), ContextCompat.getColor(weakReference.get(), R.color.white)});
        }

        gd.setShape(GradientDrawable.RECTANGLE);

        gd.setStroke(stroke_width,  stroke_color);

        gd.setCornerRadius(drawable_corner);
        gd.setBounds(2, 2, 2, 2);

        return gd;

    }

}
