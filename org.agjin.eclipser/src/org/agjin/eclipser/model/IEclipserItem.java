package org.agjin.eclipser.model;

import org.eclipse.core.runtime.IAdaptable;

public interface IEclipserItem extends IAdaptable {
	String getName();
	void setName(String newName);
	String getLocation();
	boolean isEclipserFor(Object obj);
	EclipserItemType<?> getType();
	String getInfo();
	
	
	static IEclipserItem[] NONE = new IEclipserItem[]{};
}
