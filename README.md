# 📱 Navigation Drawer Assignment

## Assignment Overview

This Android application was developed as part of the technical assessment for **Ahead Websoft Technologies**. The project demonstrates proficiency in modern Android development practices, clean architecture, and responsive UI design.

## 📋 Assignment Requirements

The task was to create a navigation drawer application with the following specifications:

### ✅ **Core Requirements Implemented**
- **Navigation Drawer**: Side menu with user profile and dynamic content
- **MVVM Architecture**: Clean separation using ViewModel and Repository patterns
- **Dependency Injection**: Implemented using Dagger for maintainable code
- **API Integration**: Real-time data fetching with proper error handling
- **Responsive Design**: Adaptive layouts for different screen sizes
- **Apps Section**: Grid layout showing 10 items initially with "See More" functionality

### 🎯 **Technical Specifications Met**
- **API Endpoint**: `https://demo.socialnetworking.solutions/sesapi/navigation`
- **Parameters**: `restApi=Sesapi`, `sesapi_platform=1`, `auth_token=B179086bb56c32731633335762`
- **Profile Display**: User name and profile picture from API data
- **Dynamic Content**: Apps section with expandable grid (10 → all items)
- **Error Handling**: Graceful fallbacks and loading states

## 🏗️ Architecture & Design Patterns

### **MVVM (Model-View-ViewModel)**
```
📱 View (Activity/Fragments)
    ↕️
🧠 ViewModel (Business Logic)
    ↕️
📊 Repository (Data Management)
    ↕️
🌐 API Service (Network Layer)
```

### **Key Components**
- **MainActivity**: Main UI controller with drawer management
- **NavigationViewModel**: Handles business logic and data flow
- **NavigationRepository**: Manages API calls and data caching
- **Dagger Modules**: Dependency injection setup
- **Custom Views**: Responsive RecyclerView and grid layouts

## 🚀 Features Implemented

### **Navigation Drawer**
- ✅ Slide-out menu with smooth animations
- ✅ User profile section with API-driven content
- ✅ Apps grid with 2-column (phone) / 3-column (tablet) layout
- ✅ "See More" functionality to expand from 10 to all items
- ✅ Help section and sign-out option

### **Responsive Design**
- ✅ **Phone Layout**: Compact design with 320dp drawer width
- ✅ **Tablet Layout**: Spacious design with 400dp drawer width  
- ✅ **Large Tablet**: Maximum comfort with 480dp drawer width
- ✅ Dynamic grid columns based on screen size

### **API Integration**
- ✅ Retrofit + OkHttp for network calls
- ✅ Real-time data fetching with loading indicators
- ✅ Smart fallback to mock data when API unavailable
- ✅ Proper error handling and user feedback

### **Additional Features**
- ✅ API Response Viewer (developer tool)
- ✅ Pull-to-refresh functionality
- ✅ Smooth loading states and transitions
- ✅ Edge-to-edge design with proper system bar handling

## 📱 Device Compatibility

| Device Type | Screen Size | Layout | Grid Columns |
|-------------|-------------|---------|--------------|
| **Phones** | < 600dp | Compact | 2 columns |
| **Tablets** | 600dp - 720dp | Spacious | 3 columns |
| **Large Tablets** | > 720dp | Maximum | 3 columns |

## 🔧 Technical Implementation

### **Dependencies Used**
- **UI**: Material Design Components, ConstraintLayout
- **Architecture**: Lifecycle Components (ViewModel, LiveData)
- **Networking**: Retrofit, OkHttp, Gson
- **DI**: Dagger 2
- **Image Loading**: Glide
- **Testing**: JUnit, Espresso

### **Project Structure**
```
📦 com.example.assignmentprojxml/
├── 🎨 ui/                    # User Interface Layer
│   ├── adapter/              # RecyclerView adapters
│   ├── dialog/               # Custom dialogs
│   ├── view/                 # Custom views & components
│   └── viewmodel/            # ViewModels for MVVM
├── 📊 data/                  # Data Layer
│   ├── api/                  # Network services & endpoints
│   ├── model/                # Data models & DTOs
│   └── repository/           # Repository pattern implementation
├── 🔧 di/                    # Dependency Injection
│   ├── AppComponent          # Main DI component
│   ├── NetworkModule         # Network dependencies
│   └── ViewModelModule       # ViewModel factory setup
└── ⚙️ config/               # Configuration & constants
```

## 🚀 Getting Started

### **Prerequisites**
- Android Studio Arctic Fox or later
- Java 11 (JDK 11)
- Android SDK API 24+

### **Setup Instructions**
1. Clone the repository
2. Open in Android Studio
3. Sync project with Gradle files
4. Run on device or emulator (API 24+)

```bash
git clone [repository-url]
cd AssignmentProjXml
# Open in Android Studio and run
```

### **Build Commands**
```bash
# Debug build
./gradlew assembleDebug

# Release build (signed)
./gradlew assembleRelease
```

## 📊 Assignment Deliverables

### ✅ **Completed Requirements**
- [x] Navigation drawer implementation
- [x] MVVM architecture with Repository pattern
- [x] Dagger dependency injection
- [x] API integration with provided endpoint
- [x] Profile section with user data
- [x] Apps grid with "See More" functionality
- [x] Responsive design for multiple screen sizes
- [x] Proper error handling and loading states

### 🎁 **Bonus Features Added**
- [x] API response viewer for debugging
- [x] Tablet-optimized layouts
- [x] Custom responsive RecyclerView
- [x] Smooth animations and transitions
- [x] Edge-to-edge design
- [x] Release-ready app signing

## 🔐 Security & Best Practices

- **API Credentials**: Stored in BuildConfig (not hardcoded)
- **Keystore**: Properly configured for release builds
- **Code Quality**: Clean architecture with separation of concerns
- **Error Handling**: Comprehensive error management
- **Performance**: Efficient image loading and memory management

## 📝 Development Notes

This assignment demonstrates:
- **Modern Android Development**: Latest practices and libraries
- **Clean Code**: Well-structured, maintainable codebase
- **Professional UI/UX**: Polished interface following Material Design
- **Scalable Architecture**: Easy to extend and maintain
- **Production Ready**: Proper signing, error handling, and optimization

## 🎮 How to Test

### **Basic Navigation**
1. Launch the app
2. Tap the menu icon (☰) to open the drawer
3. Browse the apps in the grid layout
4. Tap "See More" to expand from 10 to all items
5. Test on different screen sizes for responsiveness

### **Developer Features**
- **API Viewer**: Long-press "See More" to view raw API response
- **Refresh**: Tap refresh icon to reload data
- **Responsive Testing**: Rotate device or test on tablets

## 📱 Screenshots & Demo

The app includes:
- Clean navigation drawer with profile section
- Responsive grid layouts (2-col phones, 3-col tablets)
- Smooth "See More" expansion functionality
- Professional loading states and error handling
- Edge-to-edge design with proper system bar handling

---

**Developed for Ahead Websoft Technologies**  
*Technical Assessment - Android Developer Position*

**Key Highlights:**
- ✅ All assignment requirements met
- ✅ Clean MVVM architecture implementation
- ✅ Production-ready code quality
- ✅ Responsive design for all devices
- ✅ Bonus features and optimizations