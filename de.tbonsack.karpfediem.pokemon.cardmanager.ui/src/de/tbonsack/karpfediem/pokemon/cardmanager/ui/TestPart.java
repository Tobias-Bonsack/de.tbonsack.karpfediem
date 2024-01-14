package de.tbonsack.karpfediem.pokemon.cardmanager.ui;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.widgets.WidgetFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import de.tbonsack.karpfediem.pokemon.cardmanager.model.objects.CardSet;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.services.CardSetService;
import de.tbonsack.karpfediem.utils.gson.service.SaveService;

public class TestPart {

	@Inject
	SaveService<CardSet> _saveService;

	@Inject
	CardSetService _setService;

	@PostConstruct
	public void test(Composite parent) {
		GridLayoutFactory.fillDefaults().applyTo(parent);

		WidgetFactory.button(SWT.None).text("Save Set")//
				.data(GridDataFactory.fillDefaults().create())//
				.onSelect((event) -> {
					_saveService.safeAsGson(_setService.getAllSets(), CardSet.class);
				})//
				.create(parent);
	}
}
