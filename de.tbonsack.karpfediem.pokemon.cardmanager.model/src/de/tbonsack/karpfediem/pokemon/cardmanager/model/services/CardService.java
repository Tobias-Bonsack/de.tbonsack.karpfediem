package de.tbonsack.karpfediem.pokemon.cardmanager.model.services;

import de.tbonsack.karpfediem.pokemon.cardmanager.model.objects.Card;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.objects.CardSet;

public interface CardService {

	public Card getCard(int number, CardSet set);
}
