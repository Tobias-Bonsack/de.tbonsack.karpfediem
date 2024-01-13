package de.tbonsack.karpfediem.pokemon.cardmanager.services.impls;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.eclipse.e4.core.services.events.IEventBroker;

import de.tbonsack.karpfediem.pokemon.cardmanager.model.objects.Card;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.objects.CardSet;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.services.CardService;

public class CardServiceImpl implements CardService {

	private Map<Integer, Card> _allCards = new HashMap<>();

	@Inject
	private IEventBroker _broker;

	public CardServiceImpl() {
		_allCards.put(1, new Card(1, "glumanda", 1));
	}

	@Override
	public Card getCard(int globalID) {
		return _allCards.get(globalID);
	}

	@Override
	public Card getCard(int number, CardSet set) {
		// TODO Auto-generated method stub
		return null;
	}

}
