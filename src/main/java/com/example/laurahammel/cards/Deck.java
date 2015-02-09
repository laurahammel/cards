package com.example.laurahammel.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    public static final int SEVENTY_EIGHT = 78;

    List<Integer> deckArray;
    int deckSize;

    // default deck size is 78 (standard tarot deck size)
    public Deck() {
        deckArray = createNewDeckArray(SEVENTY_EIGHT);
        deckSize = SEVENTY_EIGHT;
    }

    public Deck(int size) {
        deckArray = createNewDeckArray(size);
        deckSize = size;
    }

    // returns int that represents the top card in the deck & removes from deckArray
    public int pickUpTopCard() {
        if (!deckArray.isEmpty()) {
            return deckArray.remove(deckArray.size() - 1);
        }
        return -1;
    }

    public void shuffle() {
        if (!deckArray.isEmpty()) {
            Collections.shuffle(deckArray);
        }
    }

    // returns an integer array of size deckSize containing shuffled integers from 0 to deckSize-1
    public List<Integer> createNewDeckArray(int size) {
        List<Integer> intArray = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            intArray.add(i);
        }
        Collections.shuffle(intArray);
        return intArray;
    }

    public List<Integer> getDeckArray() {
        return deckArray;
    }

    public int getDeckSize() {
        return deckSize;
    }

    public int cardsRemaining() {
        return deckArray.size();
    }
}
