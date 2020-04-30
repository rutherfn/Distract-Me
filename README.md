# Distract Me
Distract Me is an android application that allows you to be distracted for some time. It distracts you by showing you news with a very detailed filter; as well as saving articles for later viewing. Developed this app for a quick distraction, when working on other projects. It fetches data from the `News API`

## Language Used 
Kotlin ([View](https://kotlinlang.org))

# Trello Board 
Trello Board: ([View](https://trello.com/b/7Iy0ew56/distract-me-android-app))

### Prerequisites
Be sure to run `git clone https://github.com/rutherfn/Distract-Me.git`, on the given repo. From there, you will be able to check out the master branch to get the up to date version of features!

## Getting Started / Running The Project 

This project uses the latest Gradle version. Gradle will update if a new version gets released.  

To run this app, you will need to download [Android Studio](https://developer.android.com/studio). 

To run the project open the folder `code`

You will need to create a new emulator otherwise can use your own android phone [Use Your Android Phone](https://javatutorial.net/connect-android-device-android-studio). 

From there the gradle will build the project and after completed, hit the `run` green button on top of the screen. 

The app will build and run starting from the splash screen. 

## Core Project 

`code` contains folder stucture and classes to get you started. Classes are defined into folders, to keep the code organized and concise.

# Classes

Down below are all of the classes defined in `code` and what they are used for. 

## Activitys

An activity provides the window in which the app draws its UI. The window typically fills the screen, but may be smaller then the screen and float on top of other windows. 

Used to control different `windows/screens` of a mobile applications, but can also be used to load in other screens inside of a activity. 

- `SplashActivity`: Responsible for loading in a splash screen towards the app from stock image.

- `MainActivity`: Loads in a `ViewPager Adapter`, to handle all the sub views or Fragments state. Also responsible for handling the Top Navigation View functionality. It also does a extra job, by using `corountines` to add or remove data from the saved database. It also holds the timer that lives on all screens of the app, and when the app reaches `0`, ask the user if they want to continue using the app. 

## Adapters 

Adapters are used to load in layout for fragments, as well as loads in data from UI.  

Here in this project there are `Adapters` that handles loading recycler view holders into the desired Fragments. 

- `All Adapters`:Are responsible for loading there associated `ViewHolder` classes to control functionality of view, and using override methods `onCreateViewHolder, onBindViewHolder, getItemCount`.

- `TimerAdapt`: Loads in `TimerSetViewHolder`, as well as calls the main method from the view holder. Also loads in the main layout for this alert adapter.

- `TimerCustomAdapt`: Loads in `TimerCustomSetViewHolder`, as well as calls the main method from the view holder. Also loads in the main layout for this alert adapter. 

- `TimerExtendAdapt`: Loads in `TimerExtendViewHolder`, as well as calls the main method from the view holder. Also loads in the main layout for this alert adapter. 

- `FilterBy`: Loads in `FilterByViewHolder`, as well as calls the main method of the view holder. Also loads in the main layout for this recycler adapter. 

- `News`: Loads in `NewsHomeViewHolder`, as well as calls the main method of the view holder. Also loads in the main layout for this recycler adapter. Also responsible for init the layout with the size of the `arraylist itself`. As well as has a update method, that updates the screen of data when needed. 

-  `NewsDb`: Loads in `NewsDbViewHolder`, as well as calls the main method of the view holder. Also loads in the main layout for the recycler adapter. 

- `ViewPagerAdapter`: Handles loading in all the Fragments inside a adapter use to handle state. 

## Recycler View Holders 

Every adapter class has a primary sub class of `ViewHolder`. Which handles the functionality of current layout that gets `init`

`ViewHolders` handles all of the UI functionality for recycler view adapter classes.

-  `TimerCustomSetViewHolder`: Responsible for setting up screen ui, as well as setting up a custom time standpoint in mins for how long the user wants to be distracted for. 

- `TimerExtendViewHolder`: Responsible for setting up screen ui, and asking the user if they want to extend there time. If they hit `Yes`, then the app will ask the user for how long. Selecting no, will kick them out of the app. 

- `TimerSetViewholder`: Responsible for setting up screen ui, and setting up a default timer for the user. 

- `FilerByViewHolder`: Responsible for setting up screen ui, as well as filtering news for the user. When the user hits filter, they will be subjected back to the home page to see the updated news. 

- `NewsDbViewHolder`: Responsible for setting up screen ui, as well as showing all the saved news in the database. If the user hits the red x, it will remove the article from the database. If they also hit view more, they will be prompted to the `Web` fragment; in which they can read more news on the article. 

- `NewsHomeViewHolder`: Responsible for setting up screen ui, as well as showing all the news from the api filter. If the user hits the star icon, it will save the article in the database. If they also hit view more, they will be prompted to the `Web` fragment; in which they can read more news on the article. 

## Fragments

A `Fragment` represents a behavior of a portion of user interface in a `FragmentActivity`. You can combine multiple fragments in a single activity to build a multi-pane UI and reuse a fragment in multiple activitys.

A fragment can really be summed up as a modular section of an activity, which has its own life cycles. 

Fragments declared in this project are the `Filter, Home, Saved, Web` Fragments. 

There are also dialog fragments, which has the same use case of a fragment(only its a dialog). 

Dialog Fragments declared in this project are `CustomTimerPopup, TimerExtendPopup, TimerPopup`. 

## Helpers 

A `Helper` class is any override class used in a parent class, to ease functionality of a current class. 

It creates less code for overall functionality of your parent class, and used mainly for passing paramters from `parent` class, and using it in `sub` class. 

- `NonSwipePagerableViewPager`: Is a helper class that extends View Pager, and gets used in replaced of ViewPager to not allow swiping. 

- `Typeface`: Used to load in different typefaces across the app(`instead of reinit the same typeface override class`).

- `PrefUtil`: Override method for timer functionality. 

- `NetworkTask`: Used to show a quick dialog, for a haft a second(to simulate new data loading). 

- `DatabaseTask`: Used to grab new data from database in the background. 

## Data

All `responses classes` are used for Model types to get a return type from the Network API.

As the `room classes` are responsible for building out my room database that holds saved articles. It uses a `Entity, Database, and Dao` class. 

## Network

`Network` represents all of the ovveride methods used for return of Network API calls 

- `RetrofitClient`: Use to set up my two base urls needed for making retro fit calls. 

- `NewsApiService`: Responsible for `init` all fo my get endpoints along with any overrides that are needed. 

- `NewsRepository`: Used to set up my suspend methods, for fetching data from api. 

- `NewsRepositoryImp`: Any logic for fetching data. All of them really just are responsible for returning a type that the api request returns. 

## Libraries Being Used 

- Materials ([View](https://github.com/material-components/material-components-android))
- RecyclerView ([View](https://developer.android.com/reference/android/support/v7/widget/RecyclerView))
- Picasso ([View](https://github.com/square/picasso))
- RetroFit ([View](https://square.github.io/retrofit/))
- Gson ([View](https://github.com/google/gson/))
- Room ([View](https://developer.android.com/topic/libraries/architecture/room))
- Corountines ([View](https://kotlinlang.org/docs/reference/coroutines-overview.html)
- CircleImageView ([View](https://github.com/hdodenhof/CircleImageView/))

## API Used

- News API ([View](https://newsapi.org))

## Project Status

:white_check_mark: Completed