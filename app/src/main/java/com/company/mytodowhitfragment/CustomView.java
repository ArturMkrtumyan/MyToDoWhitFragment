package com.company.mytodowhitfragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View {
    private Rect rect;
    private Paint paint;

    public CustomView(Context context) {
        super(context);
        init(null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void init(AttributeSet attrs) {
        rect = new Rect();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rect.top = 200;
        rect.left = 200;
        rect.right = rect.left + 200;
        rect.bottom = rect.top + 200;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(rect, paint);
    }
}

