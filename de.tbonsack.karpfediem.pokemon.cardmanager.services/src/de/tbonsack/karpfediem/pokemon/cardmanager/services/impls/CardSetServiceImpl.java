package de.tbonsack.karpfediem.pokemon.cardmanager.services.impls;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.e4.core.services.events.IEventBroker;

import de.tbonsack.karpfediem.pokemon.cardmanager.model.objects.CardSet;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.services.CardSetService;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.services.PreferenceService;

public class CardSetServiceImpl implements CardSetService {

	@Inject
	private IEventBroker _broker;

	@Inject
	private PreferenceService _prefs;

	private List<CardSet> _sets = new ArrayList<>();

	public CardSetServiceImpl() {
		System.out.println("Start cardsetservice");
		CardSet cardSet = new CardSet(1, "base", null);
		_sets.add(cardSet);
		cardSet.addCard(1);

		CardSet cardSet2 = new CardSet(1, "base", null);
		_sets.add(cardSet2);

	}

	@Override
	public List<CardSet> getAllSets() {
		return _sets;
	}

	@Override
	public CardSet getCardSet(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
