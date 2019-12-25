# Github Search User Project

This project is application to find github user using android application

These are possible corner cases that i tried to solve

- Prevent multiple request when user try to type query, using debounce operator
- Empty result
- Could not connect to server
- Show loading progress
- Pagination/Endless Scrolling
- Cache http response to file so we can find user in cache without internet connection
- Keep existing data when configuration changes such as : Device Orientation
- prevent the keyboard from appearing automatically because there is a focus textbox for the first time and when configuration changes
- Prevent request with empty query to reduce wasted requests

These are library that i used

- MVVM Architecture
- LiveData (Android Jetpack)
- DataBinding (Android Jetpack)
- Paging Library (Android Jetpack)
- Dagger2
- Retrofit
- RxJava/RxKotlin

Testing

- JUnit
- Espresso
- Mockito

Programming used is Kotlin with Unit Testing & Instrumented Testing
