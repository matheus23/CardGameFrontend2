package com.example.philipp.cardgamefrontend2.graphic;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.philipp.cardgamefrontend2.ResourceLoader;
import com.example.philipp.cardgamefrontend2.logic.Card;
import com.example.philipp.cardgamefrontend2.logic.CardColor;
import com.example.philipp.cardgamefrontend2.logic.CardType;

public class CardVisual {

    private static final float PADDING = 6;

    private final ResourceLoader res;
    private final Card card;
    private final CardInnerVisual innerVisual;

    private Graphic emptyCard;
    private Graphic colorTop;
    private Graphic colorBottom;
    private Graphic charTop;
    private Graphic charBottom;

    public CardVisual(ResourceLoader res, Paint paint, Card card) {
        this.res = res;
        this.card = card;

        emptyCard = new SVGGraphic(res.emptyCard).setRelativeOrigin(0, 0);

        float width = emptyCard.getWidth();
        float height = emptyCard.getHeight();

        innerVisual = getFittingInnerVisual(res, card, width, height);

        Rect ssize = new Rect();
        paint.getTextBounds(card.type.identifier, 0, card.type.identifier.length(), ssize);
        float s = getIdentifierScaling(card.type);
        float cs = 3.5f; // character scale
        float swidth = ssize.width() * cs;
        float sheight = ssize.height() * cs;

        //spadesOrnated = new SVGGraphic(res.spadesAce).scale(3, 3).move(width / 2, height / 2);
        colorTop = new SVGGraphic(res.getColor(card.color))
                .setRelativeOrigin(0, 0)
                .scale(s, s)
                .move(PADDING, PADDING+sheight + 2);
        colorBottom = new SVGGraphic(res.getColor(card.color))
                .setRelativeOrigin(0, 0)
                .scale(-s, -s)
                .move(width - PADDING, height - (PADDING+sheight + 2));

        charTop = new TextGraphic(card.color.color, card.type.identifier)
                .scale(cs, cs)
                .move(PADDING+(colorTop.getWidth()-swidth)/2, PADDING+sheight);
        charBottom = new TextGraphic(card.color.color, card.type.identifier)
                .scale(-cs, -cs)
                .move(width-(PADDING+(colorTop.getWidth()-swidth)/2), height-(PADDING+sheight));
    }

    private static CardInnerVisual getFittingInnerVisual(ResourceLoader res, Card card, float w, float h) {
        float aceScale = card.color == CardColor.SPADES ? 3 : 1;
        switch (card.type) {
            case ACE: return new CardInnerVisualSpecial(new SVGGraphic(res.getAceInner(card.color)).scale(aceScale, aceScale).move(w/2, h/2));
            case JACK: return new CardInnerVisualSpecial(new SVGGraphic(res.getJackInner(card.color)).move(w/2, h/2));
            case QUEEN: return new CardInnerVisualSpecial(new SVGGraphic(res.getQueenInner(card.color)).move(w/2, h/2));
            case KING: return new CardInnerVisualSpecial(new SVGGraphic(res.getKingInner(card.color)).move(w/2, h/2));
            default: return new CardInnerVisualNumber(res, card, w, h);
        }
    }

    private static float getIdentifierScaling(CardType type) {
        switch (type) {
            case JACK: case QUEEN: case KING: return 0.7f;
            default: return 1;
        }
    }

    public void draw(Canvas canvas, Paint paint) {
        emptyCard.draw(canvas, paint);
        innerVisual.draw(canvas, paint);
        colorTop.draw(canvas, paint);
        colorBottom.draw(canvas, paint);
        charTop.draw(canvas, paint);
        charBottom.draw(canvas, paint);
    }

    public float getWidth() {
        return emptyCard.getWidth();
    }

    public float getHeight() {
        return emptyCard.getHeight();
    }
}
