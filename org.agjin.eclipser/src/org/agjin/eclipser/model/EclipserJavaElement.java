package org.agjin.eclipser.model;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;

public class EclipserJavaElement implements IEclipserItem {
	
	private EclipserItemType<?> type;
	private IJavaElement element;

	private String name;
	
	public EclipserJavaElement(EclipserItemType<?> type, IJavaElement element) {
		this.type = type;
		this.element = element;
	}
	
	public static EclipserJavaElement loadEclipser(EclipserItemType<?> type, String info) {
		IResource res = ResourcesPlugin.getWorkspace().getRoot().findMember(new Path(info));
		if(res==null) {
			return null;
		}
		
		IJavaElement elem = JavaCore.create(res);
		if(elem==null){
			return null;
		}
		return new EclipserJavaElement(type, elem);
	}

	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
		if(adapter.isInstance(element)) {
			return element;
		}
		IResource resource = element.getResource();
		if(adapter.isInstance(resource)) {
			return resource;
		}
		return Platform.getAdapterManager().getAdapter(this, adapter);
	}

	@Override
	public String getName() {
		if(name==null) {
			name = element.getElementName();
		}
		return name;
	}

	@Override
	public void setName(String newName) {
		name = newName;
	}

	@Override
	public String getLocation() {
		try {
			IResource res = element.getUnderlyingResource();
			if(res!=null) {
				IPath path = res.getLocation().removeLastSegments(1);
				if(path.segmentCount()==0) {
					return "";
				}
				return path.toString();
			}
		}
		catch(JavaModelException e) {
			System.out.println(e.getMessage());
		}
		return "";
	}

	@Override
	public boolean isEclipserFor(Object obj) {
		return element.equals(obj);
	}

	@Override
	public EclipserItemType<?> getType() {
		return type;
	}

	@Override
	public String getInfo() {
		try {
			return element.getUnderlyingResource().getFullPath().toString();
		}
		catch (JavaModelException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public boolean equals(Object obj) {
		return this==obj || (
			(obj instanceof EclipserJavaElement) && element.equals(((EclipserJavaElement)obj).element));
	}
	
	public int hashCode() {
		return element.hashCode();
	}
	
}
