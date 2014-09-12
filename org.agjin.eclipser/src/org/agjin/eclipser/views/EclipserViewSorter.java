package org.agjin.eclipser.views;

import java.util.Comparator;

import org.agjin.eclipser.model.IEclipserItem;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableColumn;

public class EclipserViewSorter extends ViewerSorter {
	
	// 열 기준으로 정렬 정보를 그룹짓는 간단한 데이터 구조체
	private class SortInfo {
//		int columnIndex;
		Comparator<IEclipserItem> comparator;
		boolean descending;
	}
	
	private TableViewer viewer;
	private SortInfo[] infos;
		
	public EclipserViewSorter(TableViewer viewer, TableColumn[] columns, Comparator<IEclipserItem>[] comparators) {
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
	
	public int compare(Viewer viewer, IEclipserItem elclipser1, IEclipserItem eclipser2) {
		for(int i=0, size=infos.length; i<size; i++) {
			int result = infos[i].comparator.compare(elclipser1, eclipser2);
			if(result!=0) {
				if(infos[i].descending) {
					return -result;
				} else {
					return result;
				}
			}
		}
		
		return 0;
	}
	
	private void createSelectionListener(final TableColumn column, final SortInfo info) {
		column.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
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
}
