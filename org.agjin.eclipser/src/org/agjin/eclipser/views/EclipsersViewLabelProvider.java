package org.agjin.eclipser.views;

import org.agjin.eclipser.model.IEclipserItem;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class EclipsersViewLabelProvider extends LabelProvider implements ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		if((columnIndex==0) && (element instanceof IEclipserItem)) {
			return ((IEclipserItem)element).getType().getImage();
		}
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		switch(columnIndex) {
		
			case 0 : // Type 열
				return "";
				
			case 1 : // Name 
				if (element!=null) {
					if(element instanceof IEclipserItem) {
						return ((IEclipserItem)element).getName();
					} else {
						return element.toString();
					}
				} else {
					return "";
				}
				
			case 2 : // Location 열
				if (element!=null) {
					if(element instanceof IEclipserItem) {
						return ((IEclipserItem)element).getLocation();
					} 
				}
				return "";
			default :
				return null;		
		}
	}

}
