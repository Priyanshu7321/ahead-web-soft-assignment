package com.example.assignmentprojxml;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.graphics.Rect;
import com.bumptech.glide.Glide;
import com.example.assignmentprojxml.data.model.NavigationItem;
import com.example.assignmentprojxml.ui.adapter.MenuItemAdapter;
import com.example.assignmentprojxml.ui.dialog.ApiResponseDialog;
import com.example.assignmentprojxml.ui.viewmodel.NavigationViewModel;
import com.example.assignmentprojxml.di.ViewModelFactory;
import com.example.assignmentprojxml.config.AppConfig;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    
    @Inject
    ViewModelFactory viewModelFactory;
    
    private NavigationViewModel viewModel;
    private DrawerLayout drawerLayout;
    
    // UI Components
    private ImageView profileImage;
    private TextView profileName;
    private RecyclerView appsRecyclerView;
    private RecyclerView helpRecyclerView;
    private TextView seeMoreApps;
    private TextView signOut;
    private View loadingLayout;
    
    // Adapters
    private MenuItemAdapter appsAdapter;
    private MenuItemAdapter helpAdapter;
    
    // Dialog
    private ApiResponseDialog apiResponseDialog;
    
    // Data
    private List<com.example.assignmentprojxml.data.model.MenuItem> allAppsItems = new ArrayList<>();
    private List<com.example.assignmentprojxml.data.model.MenuItem> displayedAppsItems = new ArrayList<>();
    private boolean showingAllApps = false; // Track if we're showing all apps or just initial count
    
    // Store the latest API response for dialog
    private com.example.assignmentprojxml.data.model.NavigationResponse latestApiResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Enable edge-to-edge
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        // Handle system bars insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            
            // Apply padding to the main content
            View mainContent = findViewById(R.id.main_content);
            if (mainContent != null) {
                mainContent.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            }
            
            // Apply top padding to the navigation header to avoid status bar overlap
            View navHeader = findViewById(R.id.nav_header);
            if (navHeader != null) {
                navHeader.setPadding(16, 16 + systemBars.top, 16, 16);
            }
            
            // Apply bottom padding to the navigation content to avoid navigation bar overlap
            View navContent = findViewById(R.id.nav_content);
            if (navContent != null) {
                navContent.setPadding(0, 0, 0, systemBars.bottom);
            }
            
            return insets;
        });
        
        // Inject dependencies
        ((NavigationApp) getApplication()).getAppComponent().inject(this);
        
        // Initialize ViewModel
        viewModel = new ViewModelProvider(this, viewModelFactory)
                .get(NavigationViewModel.class);
        
        initViews();
        setupCustomHeader();
        setupDrawer();
        setupRecyclerViews();
        setupClickListeners();
        observeViewModel();
        
        // Load data
        viewModel.loadNavigationData();
    }
    
    private void initViews() {
        drawerLayout = findViewById(R.id.drawer_layout);
        profileImage = findViewById(R.id.profile_image);
        profileName = findViewById(R.id.profile_name);
        appsRecyclerView = findViewById(R.id.apps_recycler_view);
        helpRecyclerView = findViewById(R.id.help_recycler_view);
        seeMoreApps = findViewById(R.id.see_more_apps);
        signOut = findViewById(R.id.sign_out);
        loadingLayout = findViewById(R.id.loading_layout);
    }
    
    private void setupCustomHeader() {
        ImageView menuIcon = findViewById(R.id.menu_icon);
        ImageView actionRefresh = findViewById(R.id.action_refresh);
        ImageView actionApiResponse = findViewById(R.id.action_api_response);
        drawerLayout.openDrawer(GravityCompat.START);
        menuIcon.setOnClickListener(v -> {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        
        actionRefresh.setOnClickListener(v -> {
            viewModel.loadNavigationData();
            Toast.makeText(this, "Refreshing API data...", Toast.LENGTH_SHORT).show();
        });
        
        actionApiResponse.setOnClickListener(v -> showApiResponseDialog());
    }
    
    private void setupDrawer() {
        // No ActionBarDrawerToggle needed since we're using custom header
        // The menu icon click is handled in setupCustomHeader()
    }
    
    private void setupRecyclerViews() {
        // Initialize with empty lists but ensure they're not null
        displayedAppsItems = new ArrayList<>();
        allAppsItems = new ArrayList<>();
        
        // Add some test data initially to verify RecyclerView works
        addTestData();
        
        // Initialize the display with first 4 items
        updateAppsDisplay();
        
        // Apps RecyclerView
        appsAdapter = new MenuItemAdapter(displayedAppsItems);
        GridLayoutManager appsLayoutManager = new GridLayoutManager(this, 2);
        appsRecyclerView.setLayoutManager(appsLayoutManager);
        appsRecyclerView.setAdapter(appsAdapter);
        
        // Add spacing between grid items with edge spacing
        int spacing = getResources().getDimensionPixelSize(R.dimen.grid_spacing);
        appsRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, spacing, true));
        
        // Help RecyclerView
        helpAdapter = new MenuItemAdapter(new ArrayList<>());
        GridLayoutManager helpLayoutManager = new GridLayoutManager(this, 2);
        helpRecyclerView.setLayoutManager(helpLayoutManager);
        helpRecyclerView.setAdapter(helpAdapter);
        
        // Add spacing between grid items
        helpRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, spacing, false));
        
        // Set click listeners
        appsAdapter.setOnItemClickListener(item -> {
            Toast.makeText(this, "Clicked: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        });
        
        helpAdapter.setOnItemClickListener(item -> {
            Toast.makeText(this, "Clicked: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        });
    }
    
    private void addTestData() {
        // Clear any existing data
        allAppsItems.clear();
        
        // Add test data to verify RecyclerView is working
        com.example.assignmentprojxml.data.model.MenuItem testItem1 = new com.example.assignmentprojxml.data.model.MenuItem();
        testItem1.setTitle("Albums");
        testItem1.setIcon("");
        testItem1.setColor("#E8F5E8");
        
        com.example.assignmentprojxml.data.model.MenuItem testItem2 = new com.example.assignmentprojxml.data.model.MenuItem();
        testItem2.setTitle("Jobs");
        testItem2.setIcon("");
        testItem2.setColor("#FFE8F5");
        
        com.example.assignmentprojxml.data.model.MenuItem testItem3 = new com.example.assignmentprojxml.data.model.MenuItem();
        testItem3.setTitle("Crowdfunding");
        testItem3.setIcon("");
        testItem3.setColor("#FFF0E8");
        
        com.example.assignmentprojxml.data.model.MenuItem testItem4 = new com.example.assignmentprojxml.data.model.MenuItem();
        testItem4.setTitle("Group");
        testItem4.setIcon("");
        testItem4.setColor("#E8F0FF");
        
        // Add 8 more items to test "See More" functionality (total 12 items)
        com.example.assignmentprojxml.data.model.MenuItem testItem5 = new com.example.assignmentprojxml.data.model.MenuItem();
        testItem5.setTitle("Events");
        testItem5.setIcon("");
        testItem5.setColor("#F0E8FF");
        
        com.example.assignmentprojxml.data.model.MenuItem testItem6 = new com.example.assignmentprojxml.data.model.MenuItem();
        testItem6.setTitle("Marketplace");
        testItem6.setIcon("");
        testItem6.setColor("#E8FFF0");
        
        com.example.assignmentprojxml.data.model.MenuItem testItem7 = new com.example.assignmentprojxml.data.model.MenuItem();
        testItem7.setTitle("Pages");
        testItem7.setIcon("");
        testItem7.setColor("#FFE8E8");
        
        com.example.assignmentprojxml.data.model.MenuItem testItem8 = new com.example.assignmentprojxml.data.model.MenuItem();
        testItem8.setTitle("Videos");
        testItem8.setIcon("");
        testItem8.setColor("#E8E8FF");
        
        com.example.assignmentprojxml.data.model.MenuItem testItem9 = new com.example.assignmentprojxml.data.model.MenuItem();
        testItem9.setTitle("Photos");
        testItem9.setIcon("");
        testItem9.setColor("#F5FFE8");
        
        com.example.assignmentprojxml.data.model.MenuItem testItem10 = new com.example.assignmentprojxml.data.model.MenuItem();
        testItem10.setTitle("Messages");
        testItem10.setIcon("");
        testItem10.setColor("#E8F5FF");
        
        com.example.assignmentprojxml.data.model.MenuItem testItem11 = new com.example.assignmentprojxml.data.model.MenuItem();
        testItem11.setTitle("Settings");
        testItem11.setIcon("");
        testItem11.setColor("#FFE8F0");
        
        com.example.assignmentprojxml.data.model.MenuItem testItem12 = new com.example.assignmentprojxml.data.model.MenuItem();
        testItem12.setTitle("Profile");
        testItem12.setIcon("");
        testItem12.setColor("#F0FFE8");
        
        // Add all items to allAppsItems
        allAppsItems.add(testItem1);
        allAppsItems.add(testItem2);
        allAppsItems.add(testItem3);
        allAppsItems.add(testItem4);
        allAppsItems.add(testItem5);
        allAppsItems.add(testItem6);
        allAppsItems.add(testItem7);
        allAppsItems.add(testItem8);
        allAppsItems.add(testItem9);
        allAppsItems.add(testItem10);
        allAppsItems.add(testItem11);
        allAppsItems.add(testItem12);
    }
    
    private void setupClickListeners() {
        seeMoreApps.setOnClickListener(v -> toggleAppsDisplay());
        
        // Long click on "See More" to show API response dialog
        seeMoreApps.setOnLongClickListener(v -> {
            showApiResponseDialog();
            return true;
        });
        
        signOut.setOnClickListener(v -> {
            Toast.makeText(this, "Sign Out clicked", Toast.LENGTH_SHORT).show();
            // Implement sign out logic here
        });
    }
    
    private void observeViewModel() {
        viewModel.getNavigationData().observe(this, response -> {
            if (response != null && response.getData() != null) {
                // Store the latest response for dialog
                latestApiResponse = response;
                updateUI(response);
            }
        });
        
        viewModel.getErrorMessage().observe(this, error -> {
            if (error != null) {
                Toast.makeText(this, error, Toast.LENGTH_LONG).show();
            }
        });
        
        viewModel.getIsLoading().observe(this, isLoading -> {
            if (loadingLayout != null) {
                loadingLayout.setVisibility(isLoading ? android.view.View.VISIBLE : android.view.View.GONE);
            }
        });
    }
    
    private void updateUI(com.example.assignmentprojxml.data.model.NavigationResponse response) {
        // Update profile
        if (response.getData().getUserProfile() != null) {
            profileName.setText(response.getData().getUserProfile().getName());
            
            if (response.getData().getUserProfile().getProfilePicture() != null) {
                Glide.with(this)
                        .load(response.getData().getUserProfile().getProfilePicture())
                        .placeholder(R.drawable.default_profile)
                        .error(R.drawable.default_profile)
                        .into(profileImage);
            }
        }
        
        // Update navigation items
        if (response.getData().getNavigationItems() != null) {
            for (NavigationItem navItem : response.getData().getNavigationItems()) {
                if ("apps".equalsIgnoreCase(navItem.getType()) && navItem.getItems() != null) {
                    // Clear test data and add real data
                    allAppsItems.clear();
                    displayedAppsItems.clear();
                    allAppsItems.addAll(navItem.getItems());
                    // Reset to show initial items from config
                    showingAllApps = false;
                    updateAppsDisplay();
                    Toast.makeText(this, "Loaded " + allAppsItems.size() + " apps from API", Toast.LENGTH_SHORT).show();
                } else if ("help".equalsIgnoreCase(navItem.getType()) && navItem.getItems() != null) {
                    helpAdapter.updateItems(navItem.getItems());
                }
            }
        } else {
            Toast.makeText(this, "No navigation items found in response", Toast.LENGTH_SHORT).show();
        }
    }
    int totalItemCount = 4;
    private void toggleAppsDisplay() {
        // Toggle between showing initial count and all items

        totalItemCount += 2;
        if(totalItemCount==38){
            showingAllApps = true;
        }
        updateAppsDisplay();
    }
    
    private void updateAppsDisplay() {
        displayedAppsItems.clear();
        
        if (showingAllApps) {
            totalItemCount = 4;
            // Show all items
            displayedAppsItems.addAll(allAppsItems);
        }else if(seeMoreApps.getText().equals("See Less")){
            displayedAppsItems.addAll(allAppsItems.subList(0, 4));
            seeMoreApps.setText("see more");
        } else {
            // Show only initial count from config
            int itemsToShow = Math.min(totalItemCount, allAppsItems.size());
            if (itemsToShow > 0) {
                displayedAppsItems.addAll(allAppsItems.subList(0, itemsToShow));
            }
        }
        
        // Debug: Log the number of items being displayed
        android.util.Log.d("MainActivity", "updateAppsDisplay: showingAllApps=" + showingAllApps + 
                ", APPS_INITIAL_DISPLAY_COUNT=" + AppConfig.APPS_INITIAL_DISPLAY_COUNT +
                ", allAppsItems.size()=" + allAppsItems.size() + 
                ", displayedAppsItems.size()=" + displayedAppsItems.size());
        
        Toast.makeText(this, "Displaying " + displayedAppsItems.size() + " of " + allAppsItems.size() + " apps", Toast.LENGTH_SHORT).show();
        
        if (appsAdapter != null) {
            appsAdapter.updateItems(displayedAppsItems);
        }

        if (allAppsItems.size() <= AppConfig.APPS_INITIAL_DISPLAY_COUNT) {
            seeMoreApps.setVisibility(android.view.View.GONE);
        } else {
            seeMoreApps.setVisibility(android.view.View.VISIBLE);
            if (showingAllApps) {
                seeMoreApps.setText("See Less");
                showingAllApps = false;
            } else {
                int remainingItems = allAppsItems.size() - totalItemCount;
                seeMoreApps.setText("See More");
            }
        }
    }
    
    private void showApiResponseDialog() {
        if (latestApiResponse == null) {
            Toast.makeText(this, "No API response available yet", Toast.LENGTH_SHORT).show();
            return;
        }
        
        if (apiResponseDialog == null) {
            apiResponseDialog = new ApiResponseDialog(this);
            apiResponseDialog.setOnRefreshListener(() -> {
                // Refresh API data
                viewModel.loadNavigationData();
                Toast.makeText(this, "Refreshing API data...", Toast.LENGTH_SHORT).show();
            });
        }
        
        apiResponseDialog.setResponse(latestApiResponse);
        apiResponseDialog.show();
        
        Toast.makeText(this, "Long press 'See More' to view API response", Toast.LENGTH_LONG).show();
    }
    
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    
    // ItemDecoration class for grid spacing
    public static class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int column = position % spanCount;

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount;
                outRect.right = (column + 1) * spacing / spanCount;

                if (position < spanCount) {
                    outRect.top = spacing;
                }
                outRect.bottom = spacing;
            } else {
                outRect.left = column * spacing / spanCount;
                outRect.right = spacing - (column + 1) * spacing / spanCount;
                if (position >= spanCount) {
                    outRect.top = spacing;
                }
            }
        }
    }
}