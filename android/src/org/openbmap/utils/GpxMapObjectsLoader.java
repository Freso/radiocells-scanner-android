/**
 * Loads session wifis asynchronously.
 */
package org.openbmap.utils;

import java.util.ArrayList;

import org.mapsforge.core.model.GeoPoint;
import org.openbmap.activity.MapViewActivity;
import org.openbmap.db.DataHelper;
import org.openbmap.db.model.PositionRecord;

import android.content.Context;
import android.os.AsyncTask;


public class GpxMapObjectsLoader extends AsyncTask<Object, Void, ArrayList<GeoPoint>> {

	private static final String	TAG	= GpxMapObjectsLoader.class.getSimpleName();

	/**
	 * Indices for doInBackground arguments
	 */
	public enum Argument { MIN_LAT_COL, MAX_LAT_COL, MIN_LON_COL, MIN_MAX_COL }

	private static final int	MIN_LAT_COL	= 0;
	private static final int	MAX_LAT_COL	= 1;
	private static final int	MIN_LON_COL	= 2;
	private static final int	MAX_LON_COL	= 3;

	/**
	 * Interface for activity.
	 */
	public interface OnGpxLoadedListener {
		void onGpxLoaded(ArrayList<GeoPoint> points);
	}

	private Context	mContext;

	private OnGpxLoadedListener mListener;

	public GpxMapObjectsLoader(final Context context) {

		mContext = context;

		if (context instanceof MapViewActivity) {
			setOnGpxLoadedListener((OnGpxLoadedListener) context);
		}
	}

	public final void setOnGpxLoadedListener(final OnGpxLoadedListener listener) {
		this.mListener = listener;
	}

	/**
	 * Queries reference database for all wifis in specified range around map centre.
	 * @param args
	 * 			Args is an object array containing
	 * 			args[0]: min latitude as double
	 * 			args[1]: max latitude as double
	 * 			args[2]: min longitude as double
	 *			args[3]: max longitude as double
	 */
	@Override
	protected final ArrayList<GeoPoint> doInBackground(final Object... args) {         
		//Log.d(TAG, "Loading gpx points");
	
		DataHelper dbHelper = new DataHelper(mContext);
		
		ArrayList<PositionRecord> positions = dbHelper.loadPositionsWithin(dbHelper.loadActiveSession().getId(),
				(Double) args[MIN_LAT_COL], (Double) args[MAX_LAT_COL], (Double) args[MIN_LON_COL], (Double) args[MAX_LON_COL]);
		
		ArrayList<GeoPoint> points = new ArrayList<GeoPoint>();
		for (int i = 0; i < positions.size(); i++) {
			points.add(new GeoPoint(positions.get(i).getLatitude() , positions.get(i).getLongitude()));
		}
		
		return points;
	}

	/**
	 * Informs activity on available results by calling mListener.
	 */
	@Override
	protected final void onPostExecute(final ArrayList<GeoPoint> points) {
		if (mListener != null) {
			mListener.onGpxLoaded(points);
		}
	}

}