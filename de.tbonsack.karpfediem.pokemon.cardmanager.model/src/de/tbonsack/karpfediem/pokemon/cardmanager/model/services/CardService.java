package de.tbonsack.karpfediem.pokemon.cardmanager.model.services;

import de.tbonsack.karpfediem.pokemon.cardmanager.model.objects.Card;

public interface CardService {

	Card createCard();

	Card getCard(int globalID);

	boolean saveCard(Card card);
}
