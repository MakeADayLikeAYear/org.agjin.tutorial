package org.agjin.eclipser.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;

public class AddToEclipserInViewActionDelegate  implements IViewActionDelegate {

	/**
	 * @uml.property  name="targetPart"
	 * @uml.associationEnd  
	 */
	private IWorkbenchPart targetPart;
	
	public AddToEclipserInViewActionDelegate() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run(IAction action) {
		System.out.println("AddToEclipserInViewActionDelegate run ~~~~~");
		
		MessageDialog.openInformation(targetPart.getSite().getShell(), "Add to Eclipser"
				, "Triggered the " + getClass().getName() + " action");
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IViewPart view) {
		this.targetPart = view;
	}

}
