package com.example.philipp.cardgamefrontend2.graphic;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.philipp.cardgamefrontend2.ResourceLoader;
import com.example.philipp.cardgamefrontend2.logic.Card;
import com.example.philipp.cardgamefrontend2.logic.CardColor;
import com.example.philipp.cardgamefrontend2.logic.CardType;

public class DeckVisuals {

    private final CardVisual[] visuals;

    public DeckVisuals(ResourceLoader res, Paint paint) {
        visuals = new CardVisual[CardColor.values().length * CardType.values().length];
        for (int col = 0; col < CardColor.values().length; col++) {
            for (int typ = 0; typ < CardType.values().length; typ++) {
                Card card = new Card(CardType.values()[typ], CardColor.values()[col]);
                visuals[typ * CardColor.values().length + col] = new CardVisual(res, paint, card);
            }
        }
    }

    private int calcIndex(CardType type, CardColor color) {
        return type.ordinal() * CardColor.values().length + color.ordinal();
    }

    public void drawCard(Canvas canvas, Card card, Paint paint) {
        getCardVisual(card).draw(canvas, paint);
    }

    public CardVisual getCardVisual(Card card) {
        return visuals[calcIndex(card.type, card.color)];
    }
}
