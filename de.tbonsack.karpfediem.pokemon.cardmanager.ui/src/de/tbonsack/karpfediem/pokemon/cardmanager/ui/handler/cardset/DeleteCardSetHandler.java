package de.tbonsack.karpfediem.pokemon.cardmanager.ui.handler.cardset;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.core.services.nls.Translation;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.workbench.Selector;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import de.tbonsack.karpfediem.pokemon.cardmanager.events.CardSetEvents;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.objects.CardSet;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.services.CardSetService;
import de.tbonsack.karpfediem.pokemon.cardmanager.translation.i18n.Messages;

public class DeleteCardSetHandler {

	@Inject
	IEventBroker _broker;

	CardSet _curCardSetSelection = null;

	@Inject
	CardSetService _service;

	@CanExecute
	public boolean canExecute() {
		return _curCardSetSelection != null;
	}

	@Execute
	public void execute(Shell shell, @Translation Messages messages) {
		boolean isToDelete = MessageDialog.openConfirm(shell, messages.set_delete_title, messages.set_delete_message);

		if (!isToDelete)
			return;

		_service.delete(_curCardSetSelection);
		_curCardSetSelection = null;
	}

	@Inject
	@Optional
	private void selectCardSetEvent(@UIEventTopic(CardSetEvents.cardset_select) CardSet cardSet) {
		_curCardSetSelection = cardSet;
		Selector s = element -> element.getElementId()
				.equals("de.tbonsack.karpfediem.pokemon.cardmanager.ui.handledtoolitem.toolitem_set_delete");
		_broker.post(UIEvents.REQUEST_ENABLEMENT_UPDATE_TOPIC, s);

	}

}
