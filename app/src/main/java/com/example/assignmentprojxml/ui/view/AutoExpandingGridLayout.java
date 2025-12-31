package com.example.assignmentprojxml.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.example.assignmentprojxml.R;
import com.example.assignmentprojxml.data.model.MenuItem;
import com.example.assignmentprojxml.ui.adapter.MenuItemAdapter;
import java.util.List;

/**
 * Custom LinearLayout that creates a grid of menu items
 * Automatically expands height based on number of items
 */
public class AutoExpandingGridLayout extends LinearLayout {
    
    private static final int COLUMNS = 2;
    private MenuItemAdapter.OnItemClickListener onItemClickListener;
    
    public AutoExpandingGridLayout(Context context) {
        super(context);
        init();
    }
    
    public AutoExpandingGridLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    
    private void init() {
        setOrientation(VERTICAL);
    }
    
    public void setOnItemClickListener(MenuItemAdapter.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
    
    public void setItems(List<MenuItem> items) {
        removeAllViews();
        
        if (items == null || items.isEmpty()) {
            return;
        }
        
        LayoutInflater inflater = LayoutInflater.from(getContext());
        
        // Create rows
        for (int i = 0; i < items.size(); i += COLUMNS) {
            LinearLayout row = new LinearLayout(getContext());
            row.setOrientation(HORIZONTAL);
            row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            
            // Add items to row
            for (int j = 0; j < COLUMNS && (i + j) < items.size(); j++) {
                MenuItem item = items.get(i + j);
                View itemView = inflater.inflate(R.layout.item_menu, row, false);
                
                // Set equal weight for grid layout
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f);
                if (j > 0) {
                    params.leftMargin = getResources().getDimensionPixelSize(R.dimen.grid_spacing);
                }
                itemView.setLayoutParams(params);
                
                // Bind data (you'd need to extract this logic from MenuItemAdapter)
                // bindItemView(itemView, item);
                
                // Set click listener
                itemView.setOnClickListener(v -> {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(item);
                    }
                });
                
                row.addView(itemView);
            }
            
            // Add spacing between rows
            if (i > 0) {
                LayoutParams rowParams = (LayoutParams) row.getLayoutParams();
                rowParams.topMargin = getResources().getDimensionPixelSize(R.dimen.grid_spacing);
            }
            
            addView(row);
        }
    }
}