package de.tbonsack.karpfediem.pokemon.cardmanager.model.services;

import java.util.List;

import de.tbonsack.karpfediem.pokemon.cardmanager.model.objects.CardSet;

public interface CardSetService {

	public List<CardSet> getAllSets();

	public CardSet getCardSet(String name);

}
