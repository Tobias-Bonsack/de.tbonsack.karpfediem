package de.tbonsack.karpfediem.pokemon.cardmanager.model.services;

import java.util.List;
import java.util.Optional;

import de.tbonsack.karpfediem.pokemon.cardmanager.model.objects.CardSet;
import de.tbonsack.karpfediem.utils.gson.service.ISerializable;

public interface CardSetService {

	CardSet createCardSet();

	List<ISerializable> getAllSaveableSets();

	List<CardSet> getAllSets();

	Optional<CardSet> getCardSet(int id);

	List<CardSet> getCardSets(String name);

	boolean saveCardSet(CardSet cardSet);

}
