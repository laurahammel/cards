package com.example.laurahammel.cards;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageButton;

import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class DeckActivity extends ActionBarActivity {

    Deck deck;
    int topCardValue;
    boolean backPressedYet;
    public static final int THREE = 3;
    public static final int FIVE = 5;
    public static final int TEN = 10;

    @InjectView(R.id.cardButton)
    ImageButton cardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck);
        ButterKnife.inject(this);

        deck = new Deck(FIVE);
        backPressedYet = false;

        loadImage(deck.getDeckSize());
    }

    public void loadImage(int cardValue) {
        int drawableID;

        switch (cardValue) {
            case 0:
                drawableID = R.drawable.swords;
                break;
            case 1:
                drawableID = R.drawable.world;
                break;
            case 2:
                drawableID = R.drawable.strength;
                break;
            case 3:
                drawableID = R.drawable.wheel;
                break;
            case 4:
                drawableID = R.drawable.pentacles;
                break;
            default:
                drawableID = R.drawable.back;
                break;
        }

        Picasso.with(this).load(drawableID).into(cardButton);
    }

    @Override
    public void onBackPressed() {
        topCardValue = deck.putTopDiscardBack();

        switch (topCardValue) {
            case -2:
                super.onBackPressed();
            case -1:
                loadImage(deck.getDeckSize());
                Log.d("blink182", String.valueOf(deck.howManyCardsRemaining()) + " left in deck");
                break;
            default:
                loadImage(topCardValue);
                Log.d("blink182", "last card picked up is #" + Integer.toString(topCardValue));
                Log.d("blink182", String.valueOf(deck.howManyCardsRemaining()) + " left in deck");
                break;
        }
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            super.onBackPressed();
            return true;
        }
        return super.onKeyLongPress(keyCode, event);
    }

    @OnClick(R.id.cardButton)
    public void pickUpTopCard() {
        topCardValue = deck.pickUpTopCard();
        if (topCardValue == -1) {
            noCardsLeftInDeck();
        } else {
            loadImage(topCardValue);
            Log.d("blink182", "last card picked up is #" + Integer.toString(topCardValue));
            Log.d("blink182", String.valueOf(deck.howManyCardsRemaining()) + " left in deck");
        }
    }

    public void noCardsLeftInDeck() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.no_more_cards));
        builder.setPositiveButton(getString(R.string.return_home), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(DeckActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton(getString(R.string.whatever), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        AlertDialog alert = builder.create();
        alert.show();
    }
}