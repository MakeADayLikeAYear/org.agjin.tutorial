package org.agjin.eclipser.actions;

import java.util.Iterator;
import java.util.logging.Level;

import org.agjin.eclipser.logger.EclipserLogger;
import org.agjin.eclipser.model.EclipsersManager;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class AddToEclipserActionDelegate3 implements IObjectActionDelegate {
	
	/**
	 * @uml.property  name="logger"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	EclipserLogger logger = new EclipserLogger(AddToEclipserActionDelegate3.class, Level.CONFIG);
	
	/**
	 * @uml.property  name="selection"
	 * @uml.associationEnd  
	 */
	private ISelection selection;

	@SuppressWarnings("unchecked")
	@Override
	public void run(IAction action) {
		logger.debug("run ---- [{}]",  selection);
		if (selection instanceof IStructuredSelection) {
			logger.debug("run ---- IStructuredSelection");
			
			EclipsersManager mgr = EclipsersManager.getManager();
			Iterator<Object> iter = ((IStructuredSelection)selection).iterator();
			mgr.addEclipsers(mgr.newEclipsersFor(iter));
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		logger.debug("selectionChanged ---- ");
		this.selection = selection;
		action.setEnabled(!selection.isEmpty());
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		
	}
}
