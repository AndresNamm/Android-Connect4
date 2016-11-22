// Based on
//https://developer.android.com/guide/topics/ui/layout/gridview.html
//https://android--code.blogspot.hk/2015/08/android-gridview-selected-item-color.html
package com.example.udu.a1assignement;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by UDU on 02.11.2016.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private int nrTiles;
    public ImageAdapter(Context c, int nrOfTiles) {
        mContext = c;
        this.nrTiles = nrOfTiles;
    }

    public int getCount() {
        return nrTiles;
    }
    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(95, 95));

            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            int pad = 10;
            imageView.setPadding(pad, pad, pad, pad);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(R.drawable.empty_t);
        return imageView;
    }





}
