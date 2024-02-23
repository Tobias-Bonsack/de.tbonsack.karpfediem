package de.tbonsack.karpfediem.pokemon.cardmanager.ui.handler.cardset;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.nls.Translation;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

import de.tbonsack.karpfediem.pokemon.cardmanager.model.services.CardSetService;
import de.tbonsack.karpfediem.pokemon.cardmanager.translation.i18n.Messages;
import de.tbonsack.karpfediem.pokemon.cardmanager.ui.dialog.SetCreateDialog;

public class CreateNewSetHandler {

	@Inject
	CardSetService _service;

	@CanExecute
	public boolean canExecute() {
		return true;
	}

	@Execute
	public void execute(Shell shell, @Translation Messages messages) {
		var createDialog = new SetCreateDialog(shell, messages);
		createDialog.setBlockOnOpen(true);
		if (createDialog.open() == Window.OK) {
			var cardSet = _service.createCardSet();
			cardSet.setName(createDialog.getName());
			cardSet.setImgPath(createDialog.getImgPath());
			_service.saveCardSet(cardSet);
		}
	}

}
