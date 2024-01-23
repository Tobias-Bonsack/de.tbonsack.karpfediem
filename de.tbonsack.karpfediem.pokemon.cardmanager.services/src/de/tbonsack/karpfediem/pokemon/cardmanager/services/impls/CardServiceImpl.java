package de.tbonsack.karpfediem.pokemon.cardmanager.services.impls;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.eclipse.e4.core.services.events.IEventBroker;

import de.tbonsack.karpfediem.pokemon.cardmanager.model.objects.Card;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.objects.CardSet;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.services.CardService;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.services.PreferenceService;
import de.tbonsack.karpfediem.utils.gson.service.ISerializable;
import de.tbonsack.karpfediem.utils.gson.service.LoadService;

public class CardServiceImpl implements CardService {

	private Map<Integer, Card> _allCards = new HashMap<>();

	@Inject
	private IEventBroker BROKER;

	@Inject
	LoadService LOADER;

	@Inject
	PreferenceService PERF_SERVICE;

	@Override
	public Card createCard() {
		return new Card(PERF_SERVICE.getNextID(), "", -1);
	}

	@Override
	public Collection<ISerializable> getAllSaveableSets() {
		return _allCards.values().stream().map(i -> (ISerializable) i).collect(Collectors.toList());
	}

	@Override
	public Card getCard(int globalID) {
		return (Card) _allCards.get(globalID).clone();
	}

	@Override
	public Collection<Card> getCards(CardSet element) {
		return element.getCards().stream()//
				.map(_allCards::get).filter(i -> i != null)//
				.map(i -> (Card) i.clone())//
				.collect(Collectors.toList());
	}

	@Override
	public void init() {
		Collection<Card> cards = LOADER.loadFromGsonArray(Card.PATH + Card.FILE, Card.class);
		for (Card card : cards) {
			_allCards.put(card.getId(), card);
		}
	}

	@Override
	public boolean saveCard(Card card) {
		_allCards.put(card.getId(), card);
		return true;
	}

}
