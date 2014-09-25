package org.agjin.eclipser.views;

import java.util.Comparator;
import java.util.Iterator;
import java.util.logging.Level;

import org.agjin.eclipser.actions.CopyEclipsersAction;
import org.agjin.eclipser.actions.CutEclipsersAction;
import org.agjin.eclipser.actions.EclipserViewFilterAction;
import org.agjin.eclipser.actions.PasteEclipsersAction;
import org.agjin.eclipser.actions.RemoveEclipsersAction;
import org.agjin.eclipser.logger.EclipserLogger;
import org.agjin.eclipser.model.EclipsersManager;
import org.agjin.eclipser.model.IEclipserItem;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.ViewPart;

public class EclipserView3 extends ViewPart {
	
	EclipserLogger logger = new EclipserLogger(EclipserView3.class, Level.CONFIG);
	
	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.agjin.eclipser.views.EclipserView2";
	
	private TableViewer viewer;
	private TableColumn typeColumn;
	private TableColumn nameColumn;
	private TableColumn locationColumn;
	private EclipsersViewSorter sorter;
	private RemoveEclipsersAction removeAction;
	private EclipserViewFilterAction filterAction;
	private Clipboard clipboard;
	private CopyEclipsersAction copyAction;
	private CutEclipsersAction cutAction;
	private PasteEclipsersAction pasteAction;
	
	public EclipserView3() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
		final Table table = viewer.getTable();
		
		table.setHeaderVisible(true);
		table.setLinesVisible(false);
		
		typeColumn = new TableColumn(table, SWT.LEFT);
		typeColumn.setText(" ");
		typeColumn.setWidth(18);
		
		nameColumn = new TableColumn(table, SWT.LEFT);
		nameColumn.setText("Name");
		nameColumn.setWidth(200);
		
		locationColumn = new TableColumn(table, SWT.LEFT);
		locationColumn.setText("Location");
		locationColumn.setWidth(450);
		
		logger.info("createPartControl ==============================================[[[  ");
		logger.info("createPartControl ==============================================[[[ setContentProvider ");
		viewer.setContentProvider(new EclipsersViewContentProvider());
		logger.info("createPartControl ==============================================[[[ setLabelProvider ");
		viewer.setLabelProvider(new EclipsersViewLabelProvider());
		logger.info("createPartControl ==============================================[[[ createPartControl ");
		viewer.setInput(EclipsersManager.getManager());
		
		logger.info("createPartControl ==============================================]]] ");
		
		createTableSorter();
		createActions();
		createContextMenu();
		createToolbarButtions();
		createViewPulldownMenu();
		hookKeyboardActions();
		hookGolbalActions();
		hookDragAndDrop();
	}
	
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	@SuppressWarnings("unchecked")
	private void createTableSorter() {
		
		Comparator<IEclipserItem> nameComparator = new Comparator<IEclipserItem>() {
			@Override
			public int compare(IEclipserItem o1, IEclipserItem o2) {
				logger.debug("nameComparator ---- [{}], [{}]", o1.getName(), o2.getName());
				return o1.getName().compareTo(o2.getName());
			}
		};
		
		Comparator<IEclipserItem> locationComparator = new Comparator<IEclipserItem>() {
			@Override
			public int compare(IEclipserItem o1, IEclipserItem o2) {
				logger.debug("locationComparator ---- [{}], [{}]", o1.getLocation(), o2.getLocation());
				return o1.getLocation().compareTo(o2.getLocation());
			}
		};
		
		Comparator<IEclipserItem> typeComparator = new Comparator<IEclipserItem>() {
			@Override
			public int compare(IEclipserItem o1, IEclipserItem o2) {
				logger.debug("typeComparator ---- [{}], [{}]", o1.getType(), o2.getType());
				return o1.getType().compareTo(o2.getType());
			}
		};
		
		sorter = new EclipsersViewSorter(viewer
				, new TableColumn[]{nameColumn, locationColumn, typeColumn}
				, new Comparator[]{nameComparator, locationComparator, typeComparator});
		
		viewer.setSorter(sorter);
	}
	
	@SuppressWarnings("unchecked")
	public IEclipserItem[] getSelectedEclipsers() {
		logger.info("getSelectedEclipsers ---- ");
		
		IStructuredSelection selection = (IStructuredSelection)viewer.getSelection();
		IEclipserItem[] items = new IEclipserItem[selection.size()];
		Iterator<IEclipserItem> iter = selection.iterator();
		int index = 0;
		while (iter.hasNext())
			items[index++] = (IEclipserItem)iter.next();
		return items;
	}
	
	/**
	 * Action Create~
	 */
	private void createActions() {
		logger.info("createActions ---- ");
		
		logger.info("createActions ---- removeAction");
		IWorkbench workbench = PlatformUI.getWorkbench();
		ISharedImages platformImages = workbench.getSharedImages();
		removeAction = new RemoveEclipsersAction(this, "Remove");
		removeAction.setImageDescriptor(platformImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
		removeAction.setDisabledImageDescriptor(platformImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE_DISABLED));
		removeAction.setToolTipText("Remove the selected eclipse items");
		
		logger.info("createActions ---- copyAction");
		copyAction = new CopyEclipsersAction(this, "Copy");
		copyAction.setImageDescriptor(platformImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
		copyAction.setDisabledImageDescriptor(platformImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY_DISABLED));
		copyAction.setToolTipText("Copy the selected Eclipser items");
		
		logger.info("createActions ---- cutAction");
		cutAction = new CutEclipsersAction(copyAction, removeAction, "Cut");
		cutAction.setImageDescriptor(platformImages.getImageDescriptor(ISharedImages.IMG_TOOL_CUT));
		cutAction.setDisabledImageDescriptor(platformImages.getImageDescriptor(ISharedImages.IMG_TOOL_CUT_DISABLED));
		cutAction.setToolTipText("Cut  the selected Eclipser items");
		
		logger.info("createActions ---- pasteAction");
		pasteAction = new PasteEclipsersAction(this, "Paste");
		pasteAction.setImageDescriptor(platformImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));
		pasteAction.setDisabledImageDescriptor(platformImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE_DISABLED));
		pasteAction.setToolTipText("Paste the selected Eclipser items");
	}
	
	/**
	 * View PopupMenu Create 
	 */
	public void createContextMenu() {
		logger.info("createContextMenu ---- ");
		
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		// menu reset ~~~
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			
			@Override
			public void menuAboutToShow(IMenuManager manager) {
				logger.info("menuAboutToShow ---- ");
				
				EclipserView3.this.fillContextMenu(manager);
			}
			
		});
		
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}
	
	/**
	 * View PopupMenu List
	 * @param menuMgr
	 */
	private void fillContextMenu(IMenuManager menuMgr) {
		logger.info("fillContextMenu ---- ");
		boolean isEmpty = viewer.getSelection().isEmpty();
		removeAction.setEnabled(!isEmpty);
		menuMgr.add(removeAction);
		menuMgr.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	/**
	 * ToolbarButton  add ~
	 */
	private void createToolbarButtions() {
		logger.info("createToolbarButtions ---- ");
		
		getViewSite().getActionBars().getToolBarManager().add(removeAction);
		removeAction.setEnabled(false);
		viewer.addSelectionChangedListener(
				new ISelectionChangedListener() {
					
					@Override
					public void selectionChanged(SelectionChangedEvent event) {
						logger.info("selectionChanged ---- ");
						removeAction.setEnabled(!event.getSelection().isEmpty());
					}
					
		});
	}
	
	/**
	 * PullDown Menu Create
	 */
	private void createViewPulldownMenu() {
		IMenuManager menu = getViewSite().getActionBars().getMenuManager();
		filterAction = new EclipserViewFilterAction(viewer, "Filter...");
		menu.add(filterAction);
	}
	
	/**
	 * Keyboard key Action Create
	 */
	private void hookKeyboardActions() {
		viewer.getControl().addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				handlekeyreleased(e);
			}
			
		});
	}
	
	protected void handlekeyreleased(KeyEvent event) {
		if (event.character == SWT.DEL && event.stateMask == 0) {
			removeAction.run();
		}
	}
	
	/**
	 * Golbal Action Add ~
	 */
	private void hookGolbalActions() {
		getViewSite().getActionBars().setGlobalActionHandler(ActionFactory.DELETE.getId(), removeAction);
		getViewSite().getActionBars().setGlobalActionHandler(ActionFactory.COPY.getId(), copyAction);
		getViewSite().getActionBars().setGlobalActionHandler(ActionFactory.CUT.getId(), cutAction);
		getViewSite().getActionBars().setGlobalActionHandler(ActionFactory.PASTE.getId(), pasteAction);
	}
	
	/**
	 * dispose --> clipboard.dispose();
	 *  
	 * @return
	 */
	public Clipboard getClipboard() {
		if (clipboard == null)
			clipboard = new Clipboard(getSite().getShell().getDisplay());
		
		return clipboard;
	}
	
	public void dispose() {
		if (clipboard != null)
			clipboard.dispose();
		super.dispose();
	}
	
	public void hookDragAndDrop() {
		new EclipsersDragSource(this, viewer);
		new EclipsersDropTarget(this, viewer);
	}
	
}
