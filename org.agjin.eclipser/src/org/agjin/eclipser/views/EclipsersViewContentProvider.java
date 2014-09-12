package org.agjin.eclipser.views;

import org.agjin.eclipser.model.EclipsersManager;
import org.agjin.eclipser.model.EclipsersManagerEvent;
import org.agjin.eclipser.model.EclipsersManagerListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;

public class EclipsersViewContentProvider implements IStructuredContentProvider, EclipsersManagerListener {
	
	private TableViewer viewer;
	private EclipsersManager manager;

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.viewer = (TableViewer)viewer;
		if(manager!=null) {
			manager.removeEclipsersManagerListener(this);
		}
		manager = (EclipsersManager)newInput;
		if(manager!=null) {
			manager.addEclipsersManagerListener(this);
		}
	}

	@Override
	public void eclipsersChnaged(EclipsersManagerEvent event) {
		viewer.getTable().setRedraw(false);
		try {
			viewer.remove(event.getItemsRemoved());
			viewer.add(event.getItemsAdded());
		}
		finally {
			viewer.getTable().setRedraw(true);
		}
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return manager.getEclipsers();
	}

}
