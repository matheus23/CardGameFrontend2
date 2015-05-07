package com.example.philipp.cardgamefrontend2.logic;

import android.graphics.Color;

public enum CardColor {
    CLUBS(Color.BLACK),
    DIAMONDS((int)0xffdf00000L),
    HEARTS((int)0xffdf00000L),
    SPADES(Color.BLACK);

    public int color;

    CardColor(int color) {
        this.color = color;
    }
}
