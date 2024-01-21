package de.tbonsack.karpfediem.pokemon.cardmanager.services.impls;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.eclipse.e4.core.services.events.IEventBroker;

import de.tbonsack.karpfediem.pokemon.cardmanager.model.objects.Card;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.services.CardService;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.services.PreferenceService;

public class CardServiceImpl implements CardService {

	@Inject
	private static IEventBroker BROKER;

	@Inject
	private static PreferenceService PERF_SERVICE;

	private Map<Integer, Card> _allCards = new HashMap<>();

	@Override
	public Card createCard() {
		return new Card(PERF_SERVICE.getNextID(), "", -1);
	}

	@Override
	public Card getCard(int globalID) {
		return (Card) _allCards.get(globalID).clone();
	}

	@Override
	public boolean saveCard(Card card) {
		_allCards.put(card.getId(), card);
		return true;
	}

}
