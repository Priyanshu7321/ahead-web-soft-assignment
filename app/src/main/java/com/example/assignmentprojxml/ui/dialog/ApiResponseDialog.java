package com.example.assignmentprojxml.ui.dialog;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.assignmentprojxml.R;
import com.example.assignmentprojxml.data.model.NavigationResponse;
import com.example.assignmentprojxml.data.model.NavigationItem;
import com.example.assignmentprojxml.data.model.MenuItem;
import com.example.assignmentprojxml.data.model.MenuItemReal;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ApiResponseDialog {
    
    public interface OnRefreshListener {
        void onRefreshRequested();
    }
    
    private Dialog dialog;
    private Context context;
    private NavigationResponse response;
    private OnRefreshListener refreshListener;
    
    // Views
    private TextView tvResponseStatus;
    private TextView tvResponseSize;
    private TextView tabFormatted;
    private TextView tabRaw;
    private ScrollView scrollFormatted;
    private ScrollView scrollRaw;
    private TextView tvProfileInfo;
    private TextView tvNavigationInfo;
    private TextView tvRawJson;
    private Button btnCopyResponse;
    private Button btnRefreshApi;
    private ImageView btnCloseDialog;
    
    private boolean isFormattedTabSelected = true;
    
    public ApiResponseDialog(Context context) {
        this.context = context;
        createDialog();
    }
    
    private void createDialog() {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_api_response, null);
        dialog.setContentView(dialogView);
        
        // Make dialog full width with some margin
        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        
        initViews(dialogView);
        setupClickListeners();
    }
    
    private void initViews(View dialogView) {
        tvResponseStatus = dialogView.findViewById(R.id.tv_response_status);
        tvResponseSize = dialogView.findViewById(R.id.tv_response_size);
        tabFormatted = dialogView.findViewById(R.id.tab_formatted);
        tabRaw = dialogView.findViewById(R.id.tab_raw);
        scrollFormatted = dialogView.findViewById(R.id.scroll_formatted);
        scrollRaw = dialogView.findViewById(R.id.scroll_raw);
        tvProfileInfo = dialogView.findViewById(R.id.tv_profile_info);
        tvNavigationInfo = dialogView.findViewById(R.id.tv_navigation_info);
        tvRawJson = dialogView.findViewById(R.id.tv_raw_json);
        btnCopyResponse = dialogView.findViewById(R.id.btn_copy_response);
        btnRefreshApi = dialogView.findViewById(R.id.btn_refresh_api);
        btnCloseDialog = dialogView.findViewById(R.id.btn_close_dialog);
    }
    
    private void setupClickListeners() {
        btnCloseDialog.setOnClickListener(v -> dismiss());
        
        tabFormatted.setOnClickListener(v -> switchToFormattedTab());
        tabRaw.setOnClickListener(v -> switchToRawTab());
        
        btnCopyResponse.setOnClickListener(v -> copyJsonToClipboard());
        
        btnRefreshApi.setOnClickListener(v -> {
            if (refreshListener != null) {
                refreshListener.onRefreshRequested();
            }
            dismiss();
        });
    }
    
    private void switchToFormattedTab() {
        if (!isFormattedTabSelected) {
            isFormattedTabSelected = true;
            updateTabAppearance();
            scrollFormatted.setVisibility(View.VISIBLE);
            scrollRaw.setVisibility(View.GONE);
        }
    }
    
    private void switchToRawTab() {
        if (isFormattedTabSelected) {
            isFormattedTabSelected = false;
            updateTabAppearance();
            scrollFormatted.setVisibility(View.GONE);
            scrollRaw.setVisibility(View.VISIBLE);
        }
    }
    
    private void updateTabAppearance() {
        if (isFormattedTabSelected) {
            tabFormatted.setBackgroundResource(R.drawable.tab_selected_background);
            tabFormatted.setTextColor(Color.WHITE);
            tabRaw.setBackgroundResource(R.drawable.tab_unselected_background);
            tabRaw.setTextColor(context.getResources().getColor(android.R.color.darker_gray));
        } else {
            tabRaw.setBackgroundResource(R.drawable.tab_selected_background);
            tabRaw.setTextColor(Color.WHITE);
            tabFormatted.setBackgroundResource(R.drawable.tab_unselected_background);
            tabFormatted.setTextColor(context.getResources().getColor(android.R.color.darker_gray));
        }
    }
    
    public void setResponse(NavigationResponse response) {
        this.response = response;
        updateContent();
    }
    
    private void updateContent() {
        if (response == null) {
            tvResponseStatus.setText("No Data");
            tvResponseSize.setText("0 KB");
            tvProfileInfo.setText("No response data available");
            tvNavigationInfo.setText("No navigation items available");
            tvRawJson.setText("No JSON data available");
            return;
        }
        
        // Update status
        tvResponseStatus.setText(response.getStatus() != null ? response.getStatus() : "Unknown");
        
        // Calculate and display size
        String jsonString = new Gson().toJson(response);
        double sizeKB = jsonString.length() / 1024.0;
        tvResponseSize.setText(String.format("%.1f KB", sizeKB));
        
        // Update formatted content
        updateFormattedContent();
        
        // Update raw JSON
        updateRawJson();
    }
    
    private void updateFormattedContent() {
        // Profile Info
        if (response.getData() != null && response.getData().getUserProfile() != null) {
            StringBuilder profileInfo = new StringBuilder();
            profileInfo.append("Name: ").append(response.getData().getUserProfile().getName()).append("\\n");
            profileInfo.append("Email: ").append(response.getData().getUserProfile().getEmail()).append("\\n");
            profileInfo.append("Profile Picture: ").append(response.getData().getUserProfile().getProfilePicture());
            tvProfileInfo.setText(profileInfo.toString());
        } else if (response.getResult() != null) {
            // Handle real API structure
            StringBuilder profileInfo = new StringBuilder();
            profileInfo.append("Name: ").append(response.getResult().getTitle()).append("\\n");
            profileInfo.append("User Photo: ").append(response.getResult().getUserPhoto()).append("\\n");
            profileInfo.append("Wallet: ").append(response.getResult().getWalletAmount()).append("\\n");
            profileInfo.append("Notifications: ").append(response.getResult().getNotificationCount()).append("\\n");
            profileInfo.append("Messages: ").append(response.getResult().getMessageCount()).append("\\n");
            profileInfo.append("Friend Requests: ").append(response.getResult().getFriendReqCount());
            tvProfileInfo.setText(profileInfo.toString());
        } else {
            tvProfileInfo.setText("No profile data available");
        }
        
        // Navigation Items Info
        if (response.getData() != null && response.getData().getNavigationItems() != null) {
            StringBuilder navInfo = new StringBuilder();
            
            for (NavigationItem navItem : response.getData().getNavigationItems()) {
                navInfo.append("📂 ").append(navItem.getTitle()).append(" (").append(navItem.getType()).append(")\\n");
                
                if (navItem.getItems() != null) {
                    navInfo.append("   Items: ").append(navItem.getItems().size()).append("\\n");
                    
                    for (int i = 0; i < Math.min(navItem.getItems().size(), 5); i++) {
                        MenuItem item = navItem.getItems().get(i);
                        navInfo.append("   • ").append(item.getTitle());
                        if (item.getColor() != null) {
                            navInfo.append(" (").append(item.getColor()).append(")");
                        }
                        navInfo.append("\\n");
                    }
                    
                    if (navItem.getItems().size() > 5) {
                        navInfo.append("   ... and ").append(navItem.getItems().size() - 5).append(" more\\n");
                    }
                }
                navInfo.append("\\n");
            }
            
            tvNavigationInfo.setText(navInfo.toString());
        } else if (response.getResult() != null && response.getResult().getMenus() != null) {
            // Handle real API structure
            StringBuilder navInfo = new StringBuilder();
            navInfo.append("📱 Total Menu Items: ").append(response.getResult().getMenus().size()).append("\\n\\n");
            
            // Count by type
            int regularItems = 0;
            int sectionHeaders = 0;
            
            for (MenuItemReal menu : response.getResult().getMenus()) {
                if (menu.getType() == 1) regularItems++;
                else if (menu.getType() == 0) sectionHeaders++;
            }
            
            navInfo.append("📊 Regular Items: ").append(regularItems).append("\\n");
            navInfo.append("📋 Section Headers: ").append(sectionHeaders).append("\\n\\n");
            
            navInfo.append("🔝 First 10 Items:\\n");
            for (int i = 0; i < Math.min(response.getResult().getMenus().size(), 10); i++) {
                MenuItemReal menu = response.getResult().getMenus().get(i);
                if (menu.getType() == 1) {
                    navInfo.append("   • ").append(menu.getLabel());
                    if (menu.getModule() != null && !menu.getModule().isEmpty()) {
                        navInfo.append(" (").append(menu.getModule()).append(")");
                    }
                    navInfo.append("\\n");
                } else {
                    navInfo.append("   📂 ").append(menu.getLabel()).append(" [SECTION]\\n");
                }
            }
            
            if (response.getResult().getMenus().size() > 10) {
                navInfo.append("   ... and ").append(response.getResult().getMenus().size() - 10).append(" more items");
            }
            
            tvNavigationInfo.setText(navInfo.toString());
        } else {
            tvNavigationInfo.setText("No navigation items available");
        }
    }
    
    private void updateRawJson() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String prettyJson = gson.toJson(response);
            tvRawJson.setText(prettyJson);
        } catch (Exception e) {
            tvRawJson.setText("Error formatting JSON: " + e.getMessage());
        }
    }
    
    private void copyJsonToClipboard() {
        try {
            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            String jsonString = new GsonBuilder().setPrettyPrinting().create().toJson(response);
            ClipData clip = ClipData.newPlainText("API Response", jsonString);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(context, "JSON copied to clipboard", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Failed to copy JSON", Toast.LENGTH_SHORT).show();
        }
    }
    
    public void setOnRefreshListener(OnRefreshListener listener) {
        this.refreshListener = listener;
    }
    
    public void show() {
        if (dialog != null) {
            dialog.show();
        }
    }
    
    public void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}