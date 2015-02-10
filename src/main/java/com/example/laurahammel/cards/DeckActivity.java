package com.example.laurahammel.cards;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class DeckActivity extends ActionBarActivity {

    Deck deck;
    int cardNumber;
    public static final int THREE = 3;

    @InjectView(R.id.cardButton)
    ImageButton cardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck);
        ButterKnife.inject(this);

        deck = new Deck(THREE);

        Picasso.with(this)
                .load(Uri.parse(getString(R.string.card_back_uri_0)))
                .fit()
                .centerInside()
                .into(cardButton);
    }

    @OnClick(R.id.cardButton)
    public void pickUpCard() {
        cardNumber = deck.pickUpTopCard();
        if (cardNumber == -1) {
            noCardsLeftInDeck();
        } else {
            CardFragment cardFragment = new CardFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new CardFragment())
                    .commit();
        }
    }

    public void noCardsLeftInDeck() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.no_more_cards));
        builder.setPositiveButton(getString(R.string.return_to_home), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(DeckActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        AlertDialog alert = builder.create();
        alert.show();
    }


    public static class CardFragment extends Fragment {

        public CardFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_deck, container, false);
            return rootView;
        }
    }
}