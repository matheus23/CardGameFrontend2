package com.example.philipp.cardgamefrontend2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.example.philipp.cardgamefrontend2.graphic.DeckVisuals;
import com.example.philipp.cardgamefrontend2.logic.Card;
import com.example.philipp.cardgamefrontend2.logic.CardStack;
import com.example.philipp.cardgamefrontend2.logic.MauMauTable;

public class GameView extends SurfaceView implements View.OnTouchListener {

    private final Paint paint = new Paint();
    private ResourceLoader res;
    private SurfaceHolder holder;

    private MauMauTable table;

    // Wenn nicht alle Konstruktoren vorhanden sind, kann es sein das die App crasht...
    public GameView(Context ctx) {
        super(ctx);
        init(ctx);
    }

    public GameView(Context ctx, AttributeSet set) {
        super(ctx, set);
        init(ctx);
    }

    public GameView(Context ctx, AttributeSet set, int defStyle) {
        super(ctx, set, defStyle);
        init(ctx);
    }

    public void init(Context ctx) {
        res = new ResourceLoader(ctx, ctx.getResources());
        table = new MauMauTable(res, paint);

        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Canvas canvas = holder.lockCanvas();
                draw(canvas);
                holder.unlockCanvasAndPost(canvas);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
            }
        });
        this.setOnTouchListener(this);
    }

    @Override
    public void draw(Canvas canvas) {
        paint.setColor(Color.DKGRAY);
        paint.setAlpha(255);
        paint.setStrokeWidth(1);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
        paint.setColor(Color.WHITE);
        table.draw(canvas, paint);
    }

    private void repaint() {
        Canvas c = null;
        try {
            c = holder.lockCanvas();
            draw(c);
        } finally {
            if (c != null) holder.unlockCanvasAndPost(c);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (table.onTouch(v, event)) repaint();
        return true;
    }

}
