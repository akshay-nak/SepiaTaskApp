# SepiaTaskApp

The application has 2 screens
1. Pets list
2. Pet Details

The application is developed using Kotlin

Architecture pattern used in this application is MVVM

Third party libraries used:
1. GSON: For converting string to Data class
2. Glide: For loading the images in pet list


Pets List:
-This is the landing screen for the application
-Initially configuration is fetched which consists the working hours during the user will be allowed to use the application
-Based on the current time, if the current time falls between the working time from the configuration
  a. Pets list will be fetched
  b. Alter will be shown and user will be blocked from using the application
-On click of the pet item from the pets list the Pet Details screen will be opened
  
Pet Details:
-WebView is used to load the content URL for displaying the Pet details
