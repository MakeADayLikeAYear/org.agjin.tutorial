package org.agjin.eclipser.model;

import java.util.EventObject;
import java.util.logging.Level;

import org.agjin.eclipser.logger.EclipserLogger;

public class EclipsersManagerEvent extends EventObject {
	
	EclipserLogger logger = new EclipserLogger(EclipsersManagerEvent.class, Level.CONFIG);
	
	private static final long serialVersionUID = 1L;
	
	private final IEclipserItem[] added;
	private final IEclipserItem[] removed;
	
	public EclipsersManagerEvent(EclipsersManager source, IEclipserItem[] itemsAdded, IEclipserItem[] itemsRemoved) {
		super(source);
		
		logger.info("EclipsersManagerEvent ---- itemsAdded : [{}]", (Object)itemsAdded);
		logger.info("EclipsersManagerEvent ---- itemsRemoved : [{}]", (Object)itemsRemoved);
		
		this.added = itemsAdded;
		this.removed = itemsRemoved;
	}

	public IEclipserItem[] getItemsAdded() {
		logger.info("getItemsAdded : [{}]", (Object)added);
		return added;
	}

	public IEclipserItem[] getItemsRemoved() {
		logger.info("getItemsRemoved : [{}]", (Object)removed);
		return removed;
	}
}
