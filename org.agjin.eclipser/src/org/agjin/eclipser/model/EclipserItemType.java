package org.agjin.eclipser.model;

import org.eclipse.core.resources.IFile;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

public abstract class EclipserItemType<T> implements Comparable<T> {
	
	private static final ISharedImages PLAFORM_IMAGES = PlatformUI.getWorkbench().getSharedImages();
	
	private final String id;
	private final String printName;
	private final int ordinal;
	
	private EclipserItemType(String id, String printName, int position) {
		this.id = id;
		this.printName = printName;
		this.ordinal = position;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return printName;
	}
	
	public abstract Image getImage();
	
	public IEclipserItem newEclipser(Object obj) {
		if(!(obj instanceof IFile)) {
			return null;
		}
		return new EclipserResource(this, (IFile)obj);
	}
	
	public IEclipserItem loadEclipser(String info) {
		return EclipserResource.loadEclipser(this, info);
	}
	
	public int compareTo(Object arg) {
		return this.ordinal - ((EclipserItemType<?>)arg).ordinal;
	}
	
	public static final EclipserItemType<?> UNKNOWN = new EclipserItemType<Object>("Unknown", "Unknown", 0) {
		
		public Image getImage() {
			return null;
		}
		
		@Override
		public IEclipserItem newEclipser(Object obj) {
			return null;
		}
		
		@Override
		public IEclipserItem loadEclipser(String info) {
			return null;
		}
	};
	
	public static final EclipserItemType<?> WORKBENCH_FILE = new EclipserItemType<Object>("WBFile", "Workbench File", 1) {
		public Image getImage() {
			return PLAFORM_IMAGES.getImage(ISharedImages.IMG_OBJ_FILE);
		}
	};
	
	public static final EclipserItemType<?> WORKBENCH_FOLDER = new EclipserItemType<Object>("WBFolder", "Workbench Folder", 2) {
		public Image getImage() {
			return PLAFORM_IMAGES.getImage(ISharedImages.IMG_OBJ_FOLDER);
		}
	};
	
	public static final EclipserItemType<?> WORKBENCH_PROJECT = new EclipserItemType<Object>("WBProject", "Workbench Project", 3) {
		@SuppressWarnings("deprecation")
		public Image getImage() {
			return PLAFORM_IMAGES.getImage(ISharedImages.IMG_OBJ_PROJECT);
		}
	};
	
	public static final EclipserItemType<?> JAVA_PROJECT = new EclipserItemType<Object>("WBJavaProject", "Workbench Java Project", 4) {
		@SuppressWarnings("deprecation")
		public Image getImage() {
			return PLAFORM_IMAGES.getImage(ISharedImages.IMG_OBJ_PROJECT_CLOSED);
		}
	};
	
	public static final EclipserItemType<?> JAVA_PACKAGE_ROOT = new EclipserItemType<Object>("WBJavaPackageRoot", "Workbench Java Package Root", 5) {
		@SuppressWarnings("deprecation")
		public Image getImage() {
			return PLAFORM_IMAGES.getImage(ISharedImages.IMG_OPEN_MARKER);
		}
	};
	
	public static final EclipserItemType<?> JAVA_PACKAGE = new EclipserItemType<Object>("WBJavaPackage", "Workbench Java Package", 6) {
		public Image getImage() {
			return PLAFORM_IMAGES.getImage(ISharedImages.IMG_TOOL_BACK);
		}
	};
	
	public static final EclipserItemType<?> JAVA_CLASS_FILE = new EclipserItemType<Object>("WBJavaClassFile", "Workbench Java Class File", 7) {
		public Image getImage() {
			return PLAFORM_IMAGES.getImage(ISharedImages.IMG_TOOL_BACK_DISABLED);
		}
	};
	
	public static final EclipserItemType<?> JAVA_COMP_UNIT = new EclipserItemType<Object>("WBJavaCompUnit", "Workbench Java Comp Unit", 8) {
		public Image getImage() {
			return PLAFORM_IMAGES.getImage(ISharedImages.IMG_TOOL_BACK_DISABLED);
		}
	};
	
	public static final EclipserItemType<?> JAVA_INTERFACE = new EclipserItemType<Object>("WBJavaInterface", "Workbench Java Interface", 9) {
		@SuppressWarnings("deprecation")
		public Image getImage() {
			return PLAFORM_IMAGES.getImage(ISharedImages.IMG_TOOL_BACK_HOVER);
		}
	};
	
	public static final EclipserItemType<?> JAVA_CLASS = new EclipserItemType<Object>("WBJavaClass", "Workbench Java Class", 10) {
		public Image getImage() {
			return PLAFORM_IMAGES.getImage(ISharedImages.IMG_TOOL_COPY);
		}
	};
	
	/**
	 * http://shinych.blogspot.kr/2007/05/eclipse-shared-images.html
	 */
	private static final EclipserItemType<?>[] TYPE = {
		UNKNOWN, WORKBENCH_FILE, WORKBENCH_FOLDER, WORKBENCH_PROJECT, JAVA_PROJECT, JAVA_PACKAGE_ROOT, JAVA_PACKAGE,
		JAVA_CLASS_FILE, JAVA_COMP_UNIT, JAVA_INTERFACE, JAVA_CLASS
	};
	
	public static EclipserItemType<?>[] getType() {
		return TYPE;
	}
}
