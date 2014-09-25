package org.agjin.eclipser.model;

import java.util.logging.Level;

import org.agjin.eclipser.logger.EclipserLogger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

public abstract class EclipserItemType<T> implements Comparable<T> {
	
	EclipserLogger logger = new EclipserLogger(EclipserItemType.class, Level.INFO);
	
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
	
	public abstract IEclipserItem newEclipser(Object obj);
	
	public IEclipserItem loadEclipser(String info) {
		return EclipserResource.loadEclipser(this, info);
	}
	
	public int compareTo(Object arg) {
		return this.ordinal - ((EclipserItemType<?>)arg).ordinal;
	}
	
	public static final EclipserItemType<?> UNKNOWN = new EclipserItemType<Object>("Unknown", "Unknown", 0) {
		
		@Override
		public Image getImage() {
			return null;
		}
		
		@Override
		public IEclipserItem newEclipser(Object obj) {
			logger.debug("newEclipser UNKNOWN : [{}]", obj);
			return null;
		}
		
		@Override
		public IEclipserItem loadEclipser(String info) {
			logger.debug("loadEclipser UNKNOWN : [{}]", info);
			return null;
		}
	};
	
	public static final EclipserItemType<?> WORKBENCH_FILE = new EclipserItemType<Object>("WBFile", "Workbench File", 1) {
		@Override
		public Image getImage() {
			return PLAFORM_IMAGES.getImage(ISharedImages.IMG_OBJ_FILE);
		}
		@Override
		public IEclipserItem newEclipser(Object obj) {
			logger.debug("newEclipser : [{}]", obj);
			if(!(obj instanceof IFile)) {
				return null;
			}
			return new EclipserResource(this, (IFile)obj);
		}
	};
	
	public static final EclipserItemType<?> WORKBENCH_FOLDER = new EclipserItemType<Object>("WBFolder", "Workbench Folder", 2) {
		@Override
		public Image getImage() {
			return PLAFORM_IMAGES.getImage(ISharedImages.IMG_OBJ_FOLDER);
		}
		@Override
		public IEclipserItem newEclipser(Object obj) {
			logger.debug("newEclipser : [{}]", obj);
			if(!(obj instanceof IFolder)) {
				return null;
			}
			return new EclipserResource(this, (IFolder)obj);
		}
	};
	
	public static final EclipserItemType<?> WORKBENCH_PROJECT = new EclipserItemType<Object>("WBProject", "Workbench Project", 3) {
		@SuppressWarnings("deprecation")
		public Image getImage() {
			return PLAFORM_IMAGES.getImage(ISharedImages.IMG_OBJ_PROJECT);
		}
		@Override
		public IEclipserItem newEclipser(Object obj) {
			logger.debug("newEclipser : [{}]", obj);
			if(!(obj instanceof IProject)) {
				return null;
			}
			return new EclipserResource(this, (IProject)obj);
		}
	};
	
	public static final EclipserItemType<?> JAVA_PROJECT = new EclipserItemType<Object>("WBJavaProject", "Workbench Java Project", 4) {
		@SuppressWarnings("deprecation")
		public Image getImage() {
			return PLAFORM_IMAGES.getImage(ISharedImages.IMG_OBJ_PROJECT_CLOSED);
		}
		@Override
		public IEclipserItem newEclipser(Object obj) {
			return null;
		}
	};
	
	public static final EclipserItemType<?> JAVA_PACKAGE_ROOT = new EclipserItemType<Object>("WBJavaPackageRoot", "Workbench Java Package Root", 5) {
		@SuppressWarnings("deprecation")
		public Image getImage() {
			return PLAFORM_IMAGES.getImage(ISharedImages.IMG_OPEN_MARKER);
		}
		@Override
		public IEclipserItem newEclipser(Object obj) {
			return null;
		}
	};
	
	public static final EclipserItemType<?> JAVA_PACKAGE = new EclipserItemType<Object>("WBJavaPackage", "Workbench Java Package", 6) {
		public Image getImage() {
			return PLAFORM_IMAGES.getImage(ISharedImages.IMG_TOOL_BACK);
		}
		@Override
		public IEclipserItem newEclipser(Object obj) {
			return null;
		}
	};
	
	public static final EclipserItemType<?> JAVA_CLASS_FILE = new EclipserItemType<Object>("WBJavaClassFile", "Workbench Java Class File", 7) {
		public Image getImage() {
			return PLAFORM_IMAGES.getImage(ISharedImages.IMG_TOOL_BACK_DISABLED);
		}
		@Override
		public IEclipserItem newEclipser(Object obj) {
			return null;
		}
	};
	
	public static final EclipserItemType<?> JAVA_COMP_UNIT = new EclipserItemType<Object>("WBJavaCompUnit", "Workbench Java Comp Unit", 8) {
		public Image getImage() {
			return PLAFORM_IMAGES.getImage(ISharedImages.IMG_TOOL_BACK_DISABLED);
		}
		@Override
		public IEclipserItem newEclipser(Object obj) {
			return null;
		}
	};
	
	public static final EclipserItemType<?> JAVA_INTERFACE = new EclipserItemType<Object>("WBJavaInterface", "Workbench Java Interface", 9) {
		@SuppressWarnings("deprecation")
		public Image getImage() {
			return PLAFORM_IMAGES.getImage(ISharedImages.IMG_TOOL_BACK_HOVER);
		}
		@Override
		public IEclipserItem newEclipser(Object obj) {
			return null;
		}
	};
	
	public static final EclipserItemType<?> JAVA_CLASS = new EclipserItemType<Object>("WBJavaClass", "Workbench Java Class", 10) {
		public Image getImage() {
			return PLAFORM_IMAGES.getImage(ISharedImages.IMG_TOOL_COPY);
		}
		@Override
		public IEclipserItem newEclipser(Object obj) {
			return null;
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
