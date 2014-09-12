package org.agjin.eclipser.model;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;

public class EclipserResource implements IEclipserItem {
	
	private EclipserItemType<?> type;
	private IResource resource;
	private String name;

	EclipserResource(EclipserItemType<?> type, IResource resource) {
		this.type = type;
		this.resource = resource;
	}
	
	public static EclipserResource loadEclipser(EclipserItemType<?> type, String info) {
		IResource res = ResourcesPlugin.getWorkspace().getRoot().findMember(new Path(info));
		if(res==null) {
			return null;
		}
		return new EclipserResource(type, res);
	}
	
	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
		if (adapter.isInstance(resource)) {
			return resource;
		}
		return Platform.getAdapterManager().getAdapter(this, adapter);
	}

	@Override
	public String getName() {
		if(name==null) {
			name = resource.getName();
		}
		return name;
	}

	@Override
	public void setName(String newName) {
		name = newName;
	}

	@Override
	public String getLocation() {
		IPath path = resource.getLocation().removeLastSegments(1);
		if(path.segmentCount()==0) {
			return "";
		}
		return path.toString();
	}

	@Override
	public boolean isEclipserFor(Object obj) {
		return resource.equals(obj);
	}

	@Override
	public EclipserItemType<?> getType() {
		return type;
	}

	@Override
	public String getInfo() {
		return resource.getFullPath().toString();
	}
	
	public boolean equals(Object obj) {
		return this == obj || (
				(obj instanceof EclipserResource)
				&& resource.equals(((EclipserResource)obj).resource));
	}
	
	public int hashCode() {
		return resource.hashCode();
	}
	
	
}
