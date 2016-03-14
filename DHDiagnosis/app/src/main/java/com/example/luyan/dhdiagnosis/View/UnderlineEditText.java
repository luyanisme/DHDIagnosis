package com.example.luyan.dhdiagnosis.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by luyan on 3/14/16.
 */
public class UnderlineEditText extends EditText {

    private Paint mPaint;

    public UnderlineEditText(Context context) {
        super(context);
    }

    public UnderlineEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        // 你可以根据自己的具体需要在此处对画笔做更多个性化设置
        mPaint.setColor(Color.BLACK);
    }

    public UnderlineEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public UnderlineEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 画底线
        canvas.drawLine(0, this.getHeight() - 2, this.getWidth() - 2,
                this.getHeight() - 2, mPaint);
    }
}
