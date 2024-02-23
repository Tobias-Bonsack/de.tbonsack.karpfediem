package de.tbonsack.karpfediem.pokemon.cardmanager.ui.handler.cardset;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;

import de.tbonsack.karpfediem.pokemon.cardmanager.model.services.CardSetService;

public class SafeCardSetHandler {

	@Inject
	CardSetService _service;

	@CanExecute
	public boolean canExecute(MPart part) {
		return part.isDirty();
	}

	@Execute
	public void execute(MPart part) {
		_service.saveAllSets();
		part.setDirty(false);
	}

}
