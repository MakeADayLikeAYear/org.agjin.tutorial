package org.agjin.eclipser.views;

import java.util.Collection;

import org.agjin.eclipser.jface.Person;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

public class EclipserView2 extends ViewPart {
	
	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.agjin.eclipser.views.EclipserView2";
	
	private TableViewer viewer;
	
//	private TableColumn typeColumn;
//	private TableColumn nameColumn;
//	private TableColumn locationColumn;
	
	/*
	 * The content provider class is responsible for
	 * providing objects to the view. It can wrap
	 * existing objects in adapters or simply return
	 * objects as-is. These objects may be sensitive
	 * to the current input of the view, or ignore
	 * it and always show the same content 
	 * (like Task List, for example).
	 */
	 
	class ViewContentProvider implements IStructuredContentProvider {
		
	    /**
	     * This implementation does nothing.
	     */
	    @Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	        // do nothing.
	    }

	    /**
	     * This implementation does nothing.
	     */
	    @Override
		public void dispose() {
	        // do nothing.
	    }
		
	    /**
	     * Returns the elements in the input, which must be either an array or a
	     * <code>Collection</code>. 
	     */
	    @Override
		public Object[] getElements(Object inputElement) {
	    	
	    	System.out.println("ViewContentProvider.getElements ---------------------->");
	    	System.out.println("Class : " + inputElement.getClass());
			
	        if (inputElement instanceof Object[]) {
	        	System.out.println("instanceof Object[]");
				return (Object[]) inputElement;
			}
	        if (inputElement instanceof Collection) {
	        	System.out.println("instanceof Collection");
				return ((Collection<?>) inputElement).toArray();
			}
	        return new Object[0];
		}
	}
	
	class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {
		public String getColumnText(Object obj, int index) {
//			return getText(obj);
			Person person = (Person)obj;
			
			switch(index) {
				case 0 :
					return person.firstName;
				case 1 :
					return person.lastName;
				case 2 :
					return Integer.toString(person.age);
				case 3 :
					return Integer.toString(person.children.length);
				default :
					return "unknown : " + index;
			}
		}
		
		public Image getColumnImage(Object obj, int index) {
//			return getImage(obj);
			return null;
		}
		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().
					getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
	}

	public EclipserView2() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
		final Table table = viewer.getTable();
		
		table.setHeaderVisible(true);
		table.setLinesVisible(false);
		
		String[] columnNames = new String[]{"First Name", "Last Name", "Age", "Num Childern"};
		int[] columnWidths = new int[]{100, 100, 35, 75};
		int[] columnAligments = new int[]{SWT.LEFT, SWT.LEFT, SWT.CENTER, SWT.CENTER};
		
		for(int i=0, size=columnAligments.length; i<size; i++) {
			TableColumn tableColumn = new TableColumn(table, columnAligments[i]);
			tableColumn.setText(columnNames[i]);
			tableColumn.setWidth(columnWidths[i]);
		}
		
		
		viewer.setContentProvider(new ViewContentProvider());
//		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
//		viewer.setInput(getViewSite());
		viewer.setInput(Person.example());
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
