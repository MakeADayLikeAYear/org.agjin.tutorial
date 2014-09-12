package org.agjin.eclipser.model;

import java.util.EventObject;

public class EclipsersManagerEvent extends EventObject {

	private static final long serialVersionUID = 1L;
	
	private final IEclipserItem[] added;
	private final IEclipserItem[] removed;
	
	public EclipsersManagerEvent(EclipsersManager source, IEclipserItem[] itemsAdded, IEclipserItem[] itemsRemoved) {
		super(source);
		
		this.added = itemsAdded;
		this.removed = itemsRemoved;
	}

	public IEclipserItem[] getItemsAdded() {
		return added;
	}

	public IEclipserItem[] getItemsRemoved() {
		return removed;
	}
}
