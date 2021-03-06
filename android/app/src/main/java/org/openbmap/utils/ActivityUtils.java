/*
	Radiobeacon - Openbmap wifi and cell logger
    Copyright (C) 2013  wish7

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.openbmap.utils;

import android.app.Activity;
import android.view.WindowManager;

/**
 * Helper class for keeping screen on
 */
public final class ActivityUtils {

	public static void setKeepScreenOn(final Activity activity, final boolean keepScreenOn) {
	    // WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
		if (keepScreenOn) {
	      activity.getWindow().
	        addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	    } else {
	      activity.getWindow().
	        clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	    }
	  }
	
	/**
	 * Private dummy constructor
	 */
	private ActivityUtils() {
	
	}
	
}
