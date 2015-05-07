package com.example.philipp.cardgamefrontend2.logic;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.example.philipp.cardgamefrontend2.ResourceLoader;
import com.example.philipp.cardgamefrontend2.graphic.CardVisual;
import com.example.philipp.cardgamefrontend2.graphic.DeckVisuals;

import java.util.ArrayList;
import java.util.Stack;

public class MauMauTable implements View.OnTouchListener {

    private final DeckVisuals deckVisuals;

    private final ArrayList<CardStackObject> stacks = new ArrayList<>();

    private CardStackObject currentlyDraggingFrom = null;
    private Card currentlyDragging = null;
    private float dragx, dragy;

    public MauMauTable(ResourceLoader res, Paint paint) {
        deckVisuals = new DeckVisuals(res, paint);

        stacks.add(new CardStackObject(res, deckVisuals, new CardStack(), 100, 100));
        stacks.add(new CardStackObject(res, deckVisuals, new CardStack(new Stack<Card>()), 400, 100));
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        float x = event.getX();
        float y = event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                CardStackObject stackObject = stackObjectAt(x, y);
                if (stackObject != null && !stackObject.getStack().getCards().isEmpty()) {
                    currentlyDragging = stackObject.getStack().getCards().pop();
                    currentlyDraggingFrom = stackObject;
                    dragx = x;
                    dragy = y;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (currentlyDragging != null) {
                    dragx = x;
                    dragy = y;
                }
                break;
            case MotionEvent.ACTION_UP:
                CardStackObject stackObject1 = stackObjectAt(x, y);
                if (stackObject1 != null) {
                    stackObject1.getStack().getCards().push(currentlyDragging);
                } else {
                    currentlyDraggingFrom.getStack().getCards().push(currentlyDragging);
                }
                currentlyDragging = null;
                currentlyDraggingFrom = null;
                break;
        }
        return true;
    }

    public CardStackObject stackObjectAt(float x, float y) {
        for (int i = 0; i < stacks.size(); i++) {
            if (stacks.get(i).pointInsideStack(x, y)) return stacks.get(i);
        }
        return null;
    }

    public void draw(Canvas canvas, Paint paint) {
        for (int i = 0; i < stacks.size(); i++) {
            stacks.get(i).draw(canvas, paint);
        }
        if (currentlyDragging != null) {
            CardVisual visual = deckVisuals.getCardVisual(currentlyDragging);
            canvas.save();
            canvas.translate(dragx - visual.getWidth() / 2, dragy - visual.getHeight() / 2);
            visual.draw(canvas, paint);
            canvas.restore();
        }
    }
}
