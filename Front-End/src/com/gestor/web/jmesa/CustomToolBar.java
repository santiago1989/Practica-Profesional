package com.gestor.web.jmesa;

import org.jmesa.view.html.toolbar.AbstractToolbar;
import org.jmesa.view.html.toolbar.ToolbarItemType;

public class CustomToolBar extends AbstractToolbar {

	@Override
	public String render() {
		if (hasToolbarItems()) { // already has items
	        return super.render();
	    }
		addToolbarItem(ToolbarItemType.FIRST_PAGE_ITEM);
		addToolbarItem(ToolbarItemType.LAST_PAGE_ITEM);
		addToolbarItem(ToolbarItemType.NEXT_PAGE_ITEM);
		addToolbarItem(ToolbarItemType.PREV_PAGE_ITEM);
		addToolbarItem(ToolbarItemType.PAGE_NUMBER_ITEMS);
		addToolbarItem(ToolbarItemType.MAX_ROWS_ITEM);
		return super.render();
	}	
}
