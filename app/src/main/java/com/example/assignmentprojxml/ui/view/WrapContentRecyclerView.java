package com.example.assignmentprojxml.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Custom RecyclerView that properly handles wrap_content height
 * when used inside a ScrollView
 */
public class WrapContentRecyclerView extends RecyclerView {

    public WrapContentRecyclerView(Context context) {
        super(context);
    }

    public WrapContentRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapContentRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        // If height is wrap_content, measure all children to get proper height
        if (MeasureSpec.getMode(heightSpec) == MeasureSpec.UNSPECIFIED) {
            // Create a new height spec with AT_MOST mode and a large size
            int newHeightSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
            super.onMeasure(widthSpec, newHeightSpec);
        } else {
            super.onMeasure(widthSpec, heightSpec);
        }
    }
}