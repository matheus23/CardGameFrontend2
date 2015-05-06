package com.example.philipp.cardgamefrontend2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView {

    private final Paint paint = new Paint();
    private ResourceLoader res;
    private SurfaceHolder holder;

    private CardStack handcards;

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
        handcards = (new CardStack()).shuffle().takeFromTop(4);

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
    }

    @Override
    public void draw(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setAlpha(255);
        paint.setStrokeWidth(1);

        Card[] cards = new Card[handcards.getCards().size()];
        handcards.getCards().toArray(cards);
        for (int i = 0; i < cards.length; i++) {
            drawCard(i * 100, cards[i], canvas, paint);
        }
    }

    public void drawCard(int offset, Card card, Canvas canvas, Paint paint) {
        Bitmap cardBitmap = res.getCardBitmap(card.color, card.type);
        Rect cardRect = getBitmapRect(cardBitmap);
        Rect viewRect = new Rect(cardRect);
        viewRect.offset(offset, 0);
        canvas.drawBitmap(cardBitmap, cardRect, viewRect, paint);
    }

    private Rect getBitmapRect(Bitmap bm) {
        Rect src = new Rect();
        src.left = 0;
        src.right = bm.getWidth();
        src.top = 0;
        src.bottom = bm.getHeight();
        return src;
    }

}
