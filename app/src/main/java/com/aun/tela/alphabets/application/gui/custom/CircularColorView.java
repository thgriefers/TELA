package com.aun.tela.alphabets.application.gui.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.aun.tela.alphabets.R;

import io.meengle.util.Value;

/**
 * Created by Joseph Dalughut on 29/12/15 at 8:16 PM.
 * Project name : Alphabets.
 * Copyright (c) 2015 Meengle. All rights reserved.
 */
public class CircularColorView extends FrameLayout {

    Paint paint; //paint object to draw the bar
    Paint borderPaint; //paint object to draw the border'
    int borderWidth = 0;
    int borderColor = Color.WHITE;
    int circularColor = Color.WHITE;

    int canvasSize;

    public CircularColorView(Context context) {
        super(context);
        init();
    }

    public CircularColorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CircularColorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CircularColorView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(){
        init(null);
    }

    private void init(AttributeSet attributeSet){
        setWillNotDraw(false);
        paint = new Paint();
        borderPaint = new Paint();
        paint.setAntiAlias(true);
        borderPaint.setAntiAlias(true);
        if(!Value.NULL(attributeSet)) {
            TypedArray array = getResources().obtainAttributes(attributeSet, R.styleable.CircularColorView);
            borderWidth = array.getDimensionPixelSize(R.styleable.CircularColorView_border_width, 0);
            if (array.getBoolean(R.styleable.CircularColorView_shadow, false))
                addShadow();
            if (Value.Same.INTEGER(array.getInteger(R.styleable.CircularColorView_border_color, -1), 0))
                setBorderColor(com.aun.tela.alphabets.application.util.Color.random());
            else
                borderColor = array.getColor(R.styleable.CircularColorView_border_color, 0xFFEEEEEE);
            if (Value.Same.INTEGER(array.getInteger(R.styleable.CircularColorView_circular_color, -1), 0))
                setCircularColor(com.aun.tela.alphabets.application.util.Color.random());
            else
                circularColor = array.getColor(R.styleable.CircularColorView_circular_color, 0xFFFFFFFF);
            array.recycle();
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvasSize = canvas.getWidth() > canvas.getHeight() ? canvas.getWidth() : canvas.getHeight();

        int rad1 = canvasSize / 2;
        int rad2 = rad1 - borderWidth;

        borderPaint.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        borderPaint.setColor(borderColor);
        borderPaint.setStrokeWidth((float) borderWidth);
        paint.setColor(circularColor);

        canvas.drawCircle(rad1, rad1, rad2, borderPaint);
        canvas.drawCircle(rad1, rad1, rad2, paint);

    }

    /**
     * Add a shadow for this view
     */
    public void addShadow() {
        setLayerType(LAYER_TYPE_SOFTWARE, borderPaint);
        borderPaint.setShadowLayer(8.0f, 0.0f, 4.0f, 0x20000000);
    }

    /**
     * Remove the shadow for this view
     */
    public void removeShadow() {
        setLayerType(LAYER_TYPE_SOFTWARE, borderPaint);
        borderPaint.setShadowLayer(0, 0, 4, Color.TRANSPARENT);
    }

    /**
     * Set the circularColor for this view
     * @param circularColor the circularColor for this view
     */
    public void setCircularColor(int circularColor){
        this.circularColor = circularColor;
        this.invalidate();
    }

    /**
     * @return the circularView for this view
     */
    public int getCircularColor(){
        return this.circularColor;
    }

    /**
     * Set the borderColor for this view
     * @param borderColor the borderColor for this view
     */
    public void setBorderColor(int borderColor){
        this.borderColor = borderColor;
        this.invalidate();
    }

    /**
     * Set the borderWitch for this view
     * @param borderWidth
     */
    public void setBorderWidth(int borderWidth){
        this.borderWidth = borderWidth;
        this.requestLayout();
        this.invalidate();
    }

    /**
     * @return the borderWidth for this view
     */
    public int getBorderWidth(){return this.borderWidth;}

    /**
     * @return the borderColor for this view
     */
    public int getBorderColor(){return this.borderColor;}

    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            // The parent has determined an exact size for the child.
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            // The child can be as large as it wants up to the specified size.
            result = specSize;
        } else {
            // The parent has not imposed any constraint on the child.
            result = canvasSize;
        }

        return result;
    }

    private int measureHeight(int measureSpecHeight) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpecHeight);
        int specSize = MeasureSpec.getSize(measureSpecHeight);

        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            // The child can be as large as it wants up to the specified size.
            result = specSize;
        } else {
            // Measure the text (beware: ascent is a negative number)
            result = canvasSize;
        }

        return (result + 2);
    }

}
