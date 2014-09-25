package org.agjin.eclipser.model;



import java.util.logging.Level;

import org.agjin.eclipser.logger.EclipserLogger;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;

public class EclipserResource implements IEclipserItem {
	
	EclipserLogger logger = new EclipserLogger(EclipserResource.class, Level.INFO);
	
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
			logger.debug("getAdapter : {}", resource);
			return resource;
		}
		logger.debug("getAdapter : {}", Platform.getAdapterManager().getAdapter(this, adapter));
		return Platform.getAdapterManager().getAdapter(this, adapter);
	}

	@Override
	public String getName() {
		if(name==null) {
			name = resource.getName();
		}
		
		logger.debug("getName : {}", name);
		return name;
	}

	@Override
	public void setName(String newName) {
		name = newName;
		logger.debug("setName : {}", name);
	}

	@Override
	public String getLocation() {
		IPath path = resource.getLocation().removeLastSegments(1);
		if(path.segmentCount()==0) {
			logger.debug("getLocation : {}", "");
			return "";
		}
		logger.debug("getLocation : {}", path.toString());
		return path.toString();
	}

	@Override
	public boolean isEclipserFor(Object obj) {
		logger.debug("isEclipserFor : {}", resource.equals(obj));
		return resource.equals(obj);
	}

	@Override
	public EclipserItemType<?> getType() {
		logger.debug("getType : {}", type);
		return type;
	}

	@Override
	public String getInfo() {
		logger.debug("getInfo : {}", resource.getFullPath().toString());
		return resource.getFullPath().toString();
	}
	
	public boolean equals(Object obj) {
		logger.debug("equals : {}", this == obj || (
															(obj instanceof EclipserResource)
															&& resource.equals(((EclipserResource)obj).resource)));
		return this == obj || (
				(obj instanceof EclipserResource)
				&& resource.equals(((EclipserResource)obj).resource));
	}
	
	public int hashCode() {
		logger.debug("getInfo : {}", resource.hashCode());
		return resource.hashCode();
	}
}
