package com.example.assignmentprojxml.ui.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.assignmentprojxml.R;
import com.example.assignmentprojxml.data.model.MenuItem;
import java.util.List;

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.MenuItemViewHolder> {
    private List<MenuItem> menuItems;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(MenuItem item);
    }

    public MenuItemAdapter(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MenuItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_menu, parent, false);
        return new MenuItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemViewHolder holder, int position) {
        MenuItem item = menuItems.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return menuItems != null ? menuItems.size() : 0;
    }

    public void updateItems(List<MenuItem> newItems) {
        this.menuItems = newItems;
        notifyDataSetChanged();
    }

    class MenuItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView iconImageView;
        private TextView titleTextView;
        private View itemContainer;

        public MenuItemViewHolder(@NonNull View itemView) {
            super(itemView);
            iconImageView = itemView.findViewById(R.id.menu_icon);
            titleTextView = itemView.findViewById(R.id.menu_title);
            itemContainer = itemView.findViewById(R.id.menu_item_container);

            itemView.setOnClickListener(v -> {
                if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                    listener.onItemClick(menuItems.get(getAdapterPosition()));
                }
            });
        }

        public void bind(MenuItem item) {
            titleTextView.setText(item.getTitle());
            
            // Load icon using Glide
            if (item.getIcon() != null && !item.getIcon().isEmpty()) {
                Glide.with(itemView.getContext())
                        .load(item.getIcon())
                        .placeholder(R.drawable.ic_default_menu)
                        .error(R.drawable.ic_default_menu)
                        .into(iconImageView);
            } else {
                iconImageView.setImageResource(R.drawable.ic_default_menu);
            }

            // Set background color if available
            if (item.getColor() != null && !item.getColor().isEmpty()) {
                try {
                    int color = Color.parseColor(item.getColor());
                    itemContainer.setBackgroundColor(color);
                } catch (IllegalArgumentException e) {
                    itemContainer.setBackgroundResource(R.drawable.menu_item_background);
                }
            } else {
                itemContainer.setBackgroundResource(R.drawable.menu_item_background);
            }
        }
    }
}