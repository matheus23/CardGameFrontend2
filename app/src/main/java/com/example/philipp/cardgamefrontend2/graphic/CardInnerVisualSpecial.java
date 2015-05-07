package com.example.philipp.cardgamefrontend2.graphic;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.philipp.cardgamefrontend2.logic.CardType;

public class CardInnerVisualSpecial implements CardInnerVisual {

    private final Graphic innerGraphic;

    public CardInnerVisualSpecial(Graphic graphic) {
        this.innerGraphic = graphic;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        innerGraphic.draw(canvas, paint);
    }
}
