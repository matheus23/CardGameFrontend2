package com.example.philipp.cardgamefrontend2;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;

public class ResourceLoader {

    // references to our images
    private final Integer[] cards = {
            R.drawable.n2_of_clubs, R.drawable.n2_of_diamonds, R.drawable.n2_of_hearts, R.drawable.n2_of_spades,
            R.drawable.n3_of_clubs, R.drawable.n3_of_diamonds, R.drawable.n3_of_hearts, R.drawable.n3_of_spades,
            R.drawable.n4_of_clubs, R.drawable.n4_of_diamonds, R.drawable.n4_of_hearts, R.drawable.n4_of_spades,
            R.drawable.n5_of_clubs, R.drawable.n5_of_diamonds, R.drawable.n5_of_hearts, R.drawable.n5_of_spades,
            R.drawable.n6_of_clubs, R.drawable.n6_of_diamonds, R.drawable.n6_of_hearts, R.drawable.n6_of_spades,
            R.drawable.n7_of_clubs, R.drawable.n7_of_diamonds, R.drawable.n7_of_hearts, R.drawable.n7_of_spades,
            R.drawable.n8_of_clubs, R.drawable.n8_of_diamonds, R.drawable.n8_of_hearts, R.drawable.n8_of_spades,
            R.drawable.n9_of_clubs, R.drawable.n9_of_diamonds, R.drawable.n9_of_hearts, R.drawable.n9_of_spades,
            R.drawable.n10_of_clubs, R.drawable.n10_of_diamonds, R.drawable.n10_of_hearts, R.drawable.n10_of_spades,
            R.drawable.ace_of_clubs, R.drawable.ace_of_diamonds, R.drawable.ace_of_hearts, R.drawable.ace_of_spades,
            R.drawable.jack_of_clubs, R.drawable.jack_of_diamonds, R.drawable.jack_of_hearts, R.drawable.jack_of_spades,
            R.drawable.queen_of_clubs, R.drawable.queen_of_diamonds, R.drawable.queen_of_hearts, R.drawable.queen_of_spades,
            R.drawable.king_of_clubs, R.drawable.king_of_diamonds, R.drawable.king_of_hearts, R.drawable.king_of_spades,
    };

    //private final Bitmap[] cardBitmaps;

    public ResourceLoader(Context context, Resources resources) {
        DisplayMetrics display = context.getResources().getDisplayMetrics();
        //cardBitmaps = new Bitmap[cards.length];
        for (int i = 0; i < cards.length; i++) {
            //cardBitmaps[i] = loadBitmap(resources, cards[i], display.widthPixels/6, display.heightPixels/6);
        }
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    private Bitmap loadBitmap(Resources resources, int id, int reqWidth, int reqHeight) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, id, opt);
        opt.inSampleSize = calculateInSampleSize(opt, reqWidth, reqHeight);
        opt.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources, id, opt);
    }
/*
    public Bitmap getCardBitmap(CardColor col, CardType type) {
        return cardBitmaps[type.ordinal() * 4 + col.ordinal()]; // MAAAAAAGIIIIIC
    }*/
}
