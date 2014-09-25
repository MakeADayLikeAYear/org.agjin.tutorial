package org.agjin.eclipser.views;

import java.util.Comparator;
import java.util.logging.Level;

import org.agjin.eclipser.logger.EclipserLogger;
import org.agjin.eclipser.model.IEclipserItem;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableColumn;

public class EclipsersViewSorter extends ViewerSorter {
	
	/**
	 * @uml.property  name="logger"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	public EclipserLogger logger = new EclipserLogger(EclipsersViewSorter.class, Level.CONFIG);
	
	// ??Í∏∞Ï??ºÎ°ú ?ïÎ†¨ ?ïÎ≥¥Î•?Í∑∏Î£πÏßìÎäî Í∞ÑÎã®???∞Ïù¥??Íµ¨Ï°∞Ï≤?
	private class SortInfo {
//		int columnIndex;
		Comparator<IEclipserItem> comparator;
		boolean descending;
	}
	
	/**
	 * @uml.property  name="viewer"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private TableViewer viewer;
	/**
	 * @uml.property  name="infos"
	 * @uml.associationEnd  multiplicity="(0 -1)" inverse="this$0:org.agjin.eclipser.views.EclipsersViewSorter$SortInfo"
	 */
	private SortInfo[] infos;
	
	/**
	 * sort Ï∞∏Í≥† --> http://www.java2s.com/Code/JavaAPI/org.eclipse.jface.viewers/extendsViewerSorterSortable.htm 
	 * @param viewer
	 * @param columns
	 * @param comparators
	 */
	public EclipsersViewSorter(TableViewer viewer, TableColumn[] columns, Comparator<IEclipserItem>[] comparators) {
		logger.debug("EclipsersViewSorter ---- [{}]", (Object)columns);
		
		this.viewer = viewer;
		infos = new SortInfo[columns.length];
		
		for(int i=0, size=columns.length; i<size; i++) {
			infos[i] = new SortInfo();
//			infos[i].columnIndex = i;
			infos[i].comparator = comparators[i];
			infos[i].descending = false;

			createSelectionListener(columns[i], infos[i]);
		}
	}
	
	/**
	 * Param type??Î≥?≤Ω?òÎ©¥ ?àÎê® (Viewer, Object, Object) 
	 * Î≥?≤Ω??Í≤ΩÏö∞ Sort ?òÏ? Î™ªÌïú??
	 * public int compare(Viewer viewer, Object elclipser1, Object eclipser2) {
	 */
	public int compare(Viewer viewer, Object eclipser1, Object eclipser2) {
		int result = 0;
		for(int i=0, size=infos.length; i<size; i++) {
			result = infos[i].comparator.compare((IEclipserItem)eclipser1, (IEclipserItem)eclipser2);
			if(result!=0) {
				if(infos[i].descending) {
					result = -result;
				} 
				break;
			}
		}
		
		logger.debug("compare ---- [{}], {{}} --> Í≤∞Í≥º[{}]", eclipser1, eclipser2, result);
		return result;
	}
	
	private void createSelectionListener(final TableColumn column, final SortInfo info) {
		logger.debug("createSelectionListener ---- [{}], {{}}", column, info);
		column.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				logger.debug("widgetSelected ---- [{}]",  info);
				sortUsing(info);
			}

		});
	}
	
	protected void sortUsing(SortInfo info) {
		if(info==infos[0]) {
			info.descending = !info.descending;
		}
		else {
			for(int i=0, size=infos.length; i<size; i++) {
				if(info == infos[i]) {
					System.arraycopy(infos, 0, infos, 1, i);
					infos[0] = info;
					info.descending = false;
					break;
				}
			}
		}
		
		viewer.refresh();
	}
	
	public static void main(String[] args) {
		String a[] = {"A", "B", "C", "D", "E", "F"};
		
		System.arraycopy(a, 0, a, 1, 4);
		
		for (int i=0, size=a.length ; i<size; i++)
			System.out.println(a[i]);
	}
}
