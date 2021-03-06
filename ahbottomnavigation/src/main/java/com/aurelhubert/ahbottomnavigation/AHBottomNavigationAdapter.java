package com.aurelhubert.ahbottomnavigation;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.ColorInt;
import androidx.appcompat.widget.PopupMenu;

/**
 *
 */
public class AHBottomNavigationAdapter {

	private Menu mMenu;
	private List<AHBottomNavigationItem> navigationItems;

	/**
	 * Constructor
	 *
	 */
	public AHBottomNavigationAdapter(Context context, MenuItemsProvider provider) {
		PopupMenu popupMenu = new PopupMenu(context, null);
		mMenu = popupMenu.getMenu();
		provider.onMenuCreated(mMenu);
	}

	/**
	 * Setup bottom navigation
	 *
	 * @param ahBottomNavigation AHBottomNavigation: Bottom navigation
	 */
	public void setupWithBottomNavigation(AHBottomNavigation ahBottomNavigation) {
		setupWithBottomNavigation(ahBottomNavigation, null);
	}

	/**
	 * Setup bottom navigation (with colors)
	 *
	 * @param ahBottomNavigation AHBottomNavigation: Bottom navigation
	 * @param colors             int[]: Colors of the item
	 */
	public void setupWithBottomNavigation(AHBottomNavigation ahBottomNavigation, @ColorInt int[] colors) {
		if (navigationItems == null) {
			navigationItems = new ArrayList<>();
		} else {
			navigationItems.clear();
		}

		if (mMenu != null) {
			for (int i = 0; i < mMenu.size(); i++) {
				MenuItem item = mMenu.getItem(i);
				AHBottomNavigationItem navigationItem;
				if (colors != null && colors.length >= mMenu.size() && colors[i] != 0) {
					navigationItem = new AHBottomNavigationItem(String.valueOf(item.getTitle()), item.getIcon(), colors[i], item.getItemId());
				} else {
					navigationItem = new AHBottomNavigationItem(String.valueOf(item.getTitle()), item.getIcon(), item.getItemId());
				}
				navigationItems.add(navigationItem);
			}
			ahBottomNavigation.removeAllItems();
			ahBottomNavigation.addItems(navigationItems);
		}
	}

	/**
	 * Get Menu Item
	 *
	 * @param index
	 * @return
	 */
	public MenuItem getMenuItem(int index) {
		return mMenu.getItem(index);
	}

	/**
	 * Get Navigation Item
	 *
	 * @param index
	 * @return
	 */
	public AHBottomNavigationItem getNavigationItem(int index) {
		return navigationItems.get(index);
	}

	/**
	 * Get position by menu id
	 *
	 * @param menuId
	 * @return
	 */
	public Integer getPositionByMenuId(int menuId) {
		for (int i = 0; i < mMenu.size(); i++) {
			if (mMenu.getItem(i).getItemId() == menuId)
				return i;
		}
		return null;
	}
}