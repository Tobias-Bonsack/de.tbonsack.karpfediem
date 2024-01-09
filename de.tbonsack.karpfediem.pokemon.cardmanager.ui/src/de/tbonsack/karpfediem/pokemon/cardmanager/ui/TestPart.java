package de.tbonsack.karpfediem.pokemon.cardmanager.ui;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.swt.widgets.Composite;

import de.tbonsack.karpfediem.pokemon.cardmanager.model.objects.CardSet;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.services.CardSetService;

public class TestPart {

	@Inject
	CardSetService _setService;

	@PostConstruct
	public void test(Composite parent) {
		System.out.println("Test");
		List<CardSet> allSets = _setService.getAllSets();
		System.out.println(allSets);
	}
}
