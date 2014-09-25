package org.agjin.eclipser.views;

import java.util.logging.Level;

import org.agjin.eclipser.logger.EclipserLogger;
import org.agjin.eclipser.model.EclipsersManager;
import org.agjin.eclipser.model.EclipsersManagerEvent;
import org.agjin.eclipser.model.EclipsersManagerListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;

public class EclipsersViewContentProvider implements IStructuredContentProvider, EclipsersManagerListener {
	
	public EclipserLogger logger = new EclipserLogger(EclipsersViewContentProvider.class, Level.CONFIG);
	
	private TableViewer viewer;
	private EclipsersManager manager;

	@Override
	public void dispose() {
	}
	
	/**
	 * EclipsersManager change :  newInput (EclipsersManager)
	 * 
	 * @param viewer
	 * @param oldInput : EclipsersManager
	 * @param newInput : EclipsersManager
	 */
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		logger.debug("inputChanged ---- oldInput[{}}, newInput[{}]", oldInput, newInput);
		this.viewer = (TableViewer)viewer;
		if(manager!=null) {
			logger.debug("inputChanged ---- removeEclipsersManagerListener");
			manager.removeEclipsersManagerListener(this);
		}
		manager = (EclipsersManager)newInput;
		if(manager!=null) {
			logger.debug("inputChanged ---- addEclipsersManagerListener");
			manager.addEclipsersManagerListener(this);
		}
	}

	@Override
	public void eclipsersChnaged(EclipsersManagerEvent event) {
		logger.debug("eclipsersChnaged ---- ");
		viewer.getTable().setRedraw(false);
		try {
			viewer.remove(event.getItemsRemoved());
			viewer.add(event.getItemsAdded());
		}
		finally {
			viewer.getTable().setRedraw(true);
		}
	}
	
	/**
	 * IEclipserItem  return ~
	 * 
	 * @param inputElement :  EclipsersManager
	 * @return IEclipserItem array
	 */
	@Override
	public Object[] getElements(Object inputElement) {
		logger.debug("getElements ---- [{}]", "manager.getEclipsers()");
		return manager.getEclipsers();
	}

}
