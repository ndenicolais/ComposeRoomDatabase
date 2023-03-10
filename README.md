# ComposeRoomDatabase
> <b>Author: Nicola De Nicolais</b>

## 📍 Description
Android application built with Kotlin and Jetpack Compose that shows how to perform CRUD operations in the Room database using Android Architecture Components and the MVVM Architecture Pattern.

The app displays a list of items (users) from the inventory database. The user has options to add a new item, update an existing item, and delete an item from the inventory database. For this codelab, you save the item data to the Room database.<br/>
The Room persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite. In particular, Room provides the following benefits:<br/>
- Compile-time verification of SQL queries.
- Convenience annotations that minimize repetitive and error-prone boilerplate code.
- Streamlined database migration paths.

## ⚡ Structure
### Tech Stacks
#### Coroutines
Coroutines are blocks of code that are executed asynchronously without blocking the thread from which they are started. Coroutines can be implemented without having to worry about making complex AsyncTask implementations or managing multiple threads directly. Because of the way they are implemented, coroutines are much more efficient and require less resources than using traditional multi-threading options. Coroutines also make code that is much easier to write, understand and maintain as it allows you to write code sequentially without having to write callbacks to handle events and thread results.

#### Dagger Hilt
Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project. Hilt provides a standard way to use DI in your application by providing containers for every Android class in your project and managing their lifecycles automatically. Hilt is built on top of the popular DI library Dagger to benefit from the compile-time correctness, runtime performance, scalability, and Android Studio support that Dagger provides.

#### Room
The Room persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite. Room is now considered as a better approach for data persistence than SQLiteDatabase and it makes it easier to work with SQLiteDatabase objects in your app, decreasing the amount of boilerplate code and verifying SQL queries at compile time.

### Jetpack Compose
#### Navigation
Navigation refers to the interactions that allow users to navigate across, into, and back out from the different pieces of content within your app. Android Jetpack's Navigation component helps you implement navigation, from simple button clicks to more complex patterns, such as app bars and the navigation drawer.

#### ViewModel
The ViewModel class is a business logic or screen level state holder. It exposes state to the UI and encapsulates related business logic. Its principal advantage is that it caches state and persists it through configuration changes. This means that your UI doesn’t have to fetch data again when navigating between activities, or following configuration changes, such as when rotating the screen.

#### LiveData
LiveData is an observable data holder class. Unlike a regular observable, LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services. This awareness ensures LiveData only updates app component observers that are in an active lifecycle state.

## 🛠️ Package Structure

```
com.denicks21.roomdatabase      # Root Package
│
├── database                    # Database folder
│   ├── UserDao                 # Interface class.
│   ├── UserDatabase            # Room database.
|
├── di                          # DI folder
│   ├── AppModule               # Inject repository in the constructor.
│   ├── DatabaseModule          # Provide to Hilt an instance of Dao.
|
├── model                       # Models folder
│   ├── User                    # Data class.
|
├── navigation                  # Navigation folder
│   ├── NavGraph                # Contains all of app destinations and actions.
│   └── NavScreens              # Contains a sealed class with object corresponds to a screen and its routes.
|
├── repository                  # Repository folder
│   ├── UserRepository          # Repository to access Dao.
|
├── screen                      # App screens folder
|   │   ├── InfoPage            # Page containing information about the app and developer profile.
|   │   ├── IntroPage           # Splashscreen of the app.
|   │   ├── UserAddPage         # Page that allows to insert a new user.
|   │   ├── UserDetailsPage     # Page that displays user details.
|   │   ├── UserListPage        # Page that displays the list of users.
|   │   ├── UserUpdatePage      # Page that allows to update user details.
│
├── ui                          # UI resources folder
│   ├── composables             # Composable components folder
|   │   ├── CustomBackPress     # Component that control and prevent back button action.
|   │   ├── CustomDrawer        # Navigation drawer menu with app screens.
|   │   ├── CustomTextField     # TextField customized for entering input details.
|   │   ├── CustomTopBar        # Bar that represent the app name and drawer menu.
|
├── theme                       # Theme components folder
|   │   ├── Color               # Color palette used by the app.
|   │   ├── Shape               # Components shapes of Compose used by the app.
|   │   ├── Theme               # Theme used by the app.
|   │   ├── Type                # Typography styles for the fonts used by the app.
|
├── viewmodels                  # ViewModel folder
|   │   ├── HomeViewModel       # Model that interact with repository class.
│
├── MainActivity                # Main activity
├── RoomDatabase                # Trigger Hilt's code generation.
```

## 📎 Screenshots
<p float="left">
<img height="500em" src="screenshots/Screenshot01.png" title="RoomDatabase's screen preview">
<img height="500em" src="screenshots/Screenshot02.png" title="RoomDatabase's screen preview">
<img height="500em" src="screenshots/Screenshot03.png" title="RoomDatabase's screen preview">
