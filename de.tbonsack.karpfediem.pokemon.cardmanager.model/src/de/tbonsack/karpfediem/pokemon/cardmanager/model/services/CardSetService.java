package de.tbonsack.karpfediem.pokemon.cardmanager.model.services;

import java.util.List;
import java.util.Optional;

import de.tbonsack.karpfediem.pokemon.cardmanager.model.objects.CardSet;

public interface CardSetService {

	CardSet createCardSet();

	boolean delete(CardSet cardSet);

	List<CardSet> getAllSets();

	Optional<CardSet> getCardSet(int id);

	List<CardSet> getCardSets(String name);

	void init();

	boolean saveAllSets();

	boolean saveCardSet(CardSet cardSet);

}
