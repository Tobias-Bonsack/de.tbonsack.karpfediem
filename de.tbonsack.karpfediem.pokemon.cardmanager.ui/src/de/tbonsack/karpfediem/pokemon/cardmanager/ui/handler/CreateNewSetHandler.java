package de.tbonsack.karpfediem.pokemon.cardmanager.ui.handler;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;

public class CreateNewSetHandler {
	@CanExecute
	public boolean canExecute() {
		return true;
	}

	@Execute
	public void execute() {
		System.out.println("Test click");
	}

}
