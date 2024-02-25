package de.tbonsack.karpfediem.pokemon.cardmanager.model.services;

import java.util.Collection;

import de.tbonsack.karpfediem.pokemon.cardmanager.model.objects.Card;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.objects.CardSet;
import de.tbonsack.karpfediem.utils.gson.service.ISerializable;

public interface CardService {

	Card createCard();

	boolean delete(Card card);

	Collection<ISerializable> getAllSaveableSets();

	Card getCard(int globalID);

	Collection<Card> getCards(CardSet element);

	void init();

	void removeAllFrom(CardSet curCardSetSelection);

	boolean save(Card card);
}
