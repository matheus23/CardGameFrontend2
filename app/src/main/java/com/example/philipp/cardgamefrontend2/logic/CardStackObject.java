package com.example.philipp.cardgamefrontend2.logic;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import com.example.philipp.cardgamefrontend2.ResourceLoader;
import com.example.philipp.cardgamefrontend2.graphic.CardVisual;
import com.example.philipp.cardgamefrontend2.graphic.DeckVisuals;
import com.example.philipp.cardgamefrontend2.graphic.Graphic;
import com.example.philipp.cardgamefrontend2.graphic.SVGGraphic;

public class CardStackObject {

    private final ResourceLoader res;
    private final DeckVisuals visuals;
    private final CardStack stack;

    private final PointF position;
    private final Graphic stackLines;

    public CardStackObject(ResourceLoader res, DeckVisuals visuals, CardStack stack, float posX, float posY) {
        this.res = res;
        this.visuals = visuals;
        this.stack = stack;

        this.position = new PointF(posX, posY);
        this.stackLines = new SVGGraphic(res.stackSpace).setRelativeOrigin(0, 0);
    }

    public CardStack getStack() {
        return stack;
    }

    private String getSizeString(int n) {
        return n + (n == 1 ? " Karte" : " Karten");
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        canvas.translate(position.x, position.y);
        stackLines.draw(canvas, paint);
        canvas.save();
        canvas.scale(5, 5);
        canvas.drawText(getSizeString(stack.getCards().size()), 0, -1, paint);
        canvas.restore();
        if (!stack.getCards().isEmpty()) {
            Card topCard = stack.getCards().peek();
            CardVisual visual = visuals.getCardVisual(topCard);
            float tx = stackLines.getWidth() - visual.getWidth();
            float ty = stackLines.getHeight() - visual.getHeight();
            canvas.translate(tx/2, ty/2);
            visual.draw(canvas, paint);
        }
        canvas.restore();
    }

    public boolean pointInsideStack(float x, float y) {
        return x >= position.x && y >= position.y && x <= (position.x+stackLines.getWidth()) && y <= (position.y+stackLines.getHeight());
    }
}
