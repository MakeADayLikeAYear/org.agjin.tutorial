package org.agjin.eclipser.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;

public class EclipsersManager {
	private static EclipsersManager manager;
	private Collection<IEclipserItem> eclipsers;
	private List<EclipsersManagerListener> listeners = new ArrayList<EclipsersManagerListener>();
	
	private EclipsersManager() {
	}
	
	public static EclipsersManager getManager() {
		if(manager==null) {
			manager = new EclipsersManager();
		}
		return manager;
	}
	
	public IEclipserItem[] getEclipsers() {
		if(eclipsers==null) {
			loadEclipsers();
		}
		return (IEclipserItem[])eclipsers.toArray(new IEclipserItem[eclipsers.size()]);
	}
	
	public void loadEclipsers() {
		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		
		eclipsers = new HashSet<IEclipserItem>(projects.length);
		for(int i=0, size=projects.length; i<size; i++) {
			eclipsers.add(new EclipserResource(EclipserItemType.WORKBENCH_PROJECT, projects[i]));
		}
	}
	
	public IEclipserItem newEclipserFor(Object obj) {
		EclipserItemType<?>[] types = EclipserItemType.getType();
		for(int i=0, size=types.length; i<size; i++) {
			IEclipserItem item = types[i].newEclipser(obj);
			if(item!=null) {
				return item;
			}
		}
		return null;
	}
	
	public IEclipserItem[] newEclipsersFor(Iterator<Object> iter) {
		if(iter==null) {
			return IEclipserItem.NONE;
		}
		Collection<IEclipserItem> items = new HashSet<IEclipserItem>(20);
		while(iter.hasNext()) {
			IEclipserItem item = newEclipserFor((Object)iter.next());
			if(item!=null) {
				items.add(item);
			}
		}
		
		return items.toArray(new IEclipserItem[items.size()]);
	}
	
	public IEclipserItem[] newEclipsersFor(Object[] objects) {
		if(objects==null) {
			return IEclipserItem.NONE;
		}
		return newEclipsersFor(Arrays.asList(objects).iterator());
	}
	
	public IEclipserItem existingEclipseFor(Object obj) {
		if(obj==null) {
			return null;
		}
		Iterator<IEclipserItem> iter = eclipsers.iterator();
		while(iter.hasNext()) {
			IEclipserItem item = iter.next();
			if(item.isEclipserFor(obj)) {
				return item;
			}
		}
		
		return null;
	}
	
	public IEclipserItem[] existingEclipseFor(Iterator<Object> iter) {
		List<IEclipserItem> result = new ArrayList<IEclipserItem>();
		while(iter.hasNext()) {
			IEclipserItem item = existingEclipseFor(iter.next());
			if(item!=null) {
				result.add(item);
			}
		}
		return result.toArray(new IEclipserItem[result.size()]);
	}
	
	public void addEclipsers(IEclipserItem[] items) {
		if(eclipsers==null) {
			loadEclipsers();
		}
		if(eclipsers.addAll(Arrays.asList(items))) {
			fireEclipsersChanged(IEclipserItem.NONE, items);
		}
	}
	
	public void addEclipsersManagerListener(EclipsersManagerListener listener) {
		if(!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}
	
	public void removeEclipsersManagerListener(EclipsersManagerListener listener) {
		listeners.remove(listener);
	}
	
	public void fireEclipsersChanged(IEclipserItem[] itemsAdded, IEclipserItem[] itemsReomved) {
		EclipsersManagerEvent event = new EclipsersManagerEvent(this, itemsAdded, itemsReomved);
		for(Iterator<EclipsersManagerListener> iter=listeners.iterator(); iter.hasNext();) {
			iter.next().eclipsersChnaged(event);
		}
	}
}
