package com.example.laurahammel.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    public static final int SEVENTY_EIGHT = 78;

    List<Integer> deckArray; // integer array that represents a deck of cards
    List<Integer> discardArray; // integer array that represents the ordered discard pile
    int deckSize; // deckSize = deckArray.size() + discardArray.size()

    // default deck size is 78 (standard tarot deck size)
    public Deck() {
        deckArray = createNewDeckArray(SEVENTY_EIGHT);
        discardArray = new ArrayList<>();
        deckSize = SEVENTY_EIGHT;
    }

    // initialize deck of custom size
    public Deck(int size) {
        deckArray = createNewDeckArray(size);
        discardArray = new ArrayList<>();
        deckSize = size;
    }

    // returns int that represents the top card in the deck & removes it from deckArray,
    // adds it to the top of discardArray
    public int pickUpTopCard() {
        if (!deckArray.isEmpty()) {
            int topCardIndex = deckArray.size() - 1;
            int topCardValue = deckArray.remove(topCardIndex);
            discardArray.add(topCardValue);
            return topCardValue;
        }
        return -1;
    }

    public void putTopDiscardBackHelper() {
        int topDiscardIndex = discardArray.size() - 1;
        int topDiscardValue = discardArray.remove(topDiscardIndex);
        deckArray.add(topDiscardValue);
    }

    public int putTopDiscardBack() {
        int topCardValue = -2;
        switch (discardArray.size()) {
            case 0:
                break;
            case 1:
                putTopDiscardBackHelper();
                topCardValue = -1;
                break;
            default:
                putTopDiscardBackHelper();
                topCardValue = discardArray.get(discardArray.size() - 1);
                break;
        }
        return topCardValue;
    }


    // shuffles the deckArray

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

    public List<Integer> getDiscardArray() {
        return discardArray;
    }

    public int getDeckSize() {
        return deckSize;
    }

    public int howManyCardsRemaining() {
        return deckArray.size();
    }

    public int howManyCardsDrawn() {
        return discardArray.size();
    }
}
