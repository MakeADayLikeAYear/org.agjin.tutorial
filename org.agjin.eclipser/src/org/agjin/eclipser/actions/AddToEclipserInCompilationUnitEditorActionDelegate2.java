package org.agjin.eclipser.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;

public class AddToEclipserInCompilationUnitEditorActionDelegate2 implements IEditorActionDelegate {
	
	private IWorkbenchPart targetPart;
	
	@Override
	public void run(IAction action) {
		System.out.println("AddToEclipserInCompilationUnitEditorActionDelegate2 run ~~~~~");
		
//		MessageDialog.openInformation(targetPart.getSite().getShell(), "Add to Eclipser"
//				, "Triggered the " + getClass().getName() + " action");
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		this.targetPart = targetEditor;
	}

}
