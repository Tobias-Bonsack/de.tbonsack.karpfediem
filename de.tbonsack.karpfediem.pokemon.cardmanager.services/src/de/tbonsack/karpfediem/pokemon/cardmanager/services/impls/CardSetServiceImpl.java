package de.tbonsack.karpfediem.pokemon.cardmanager.services.impls;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.e4.core.services.events.IEventBroker;

import de.tbonsack.karpfediem.pokemon.cardmanager.model.objects.Card;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.objects.CardSet;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.services.CardSetService;

public class CardSetServiceImpl implements CardSetService {

	@Inject
	private IEventBroker _broker;

	private List<CardSet> _sets = new ArrayList<>();

	public CardSetServiceImpl() {
		CardSet cardSet = new CardSet("base", null);
		_sets.add(cardSet);

		cardSet.addCard(new Card("glumanda", 1));
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
