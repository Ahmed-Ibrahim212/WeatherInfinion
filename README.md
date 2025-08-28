# WeatherInfinion
WeatherInfinion
—it explains what it does, how to run it, and how to use it. Here’s a template tailored for your WeatherInfinion app:

WeatherInfinion

A simple Android app that fetches and displays current weather information (description and temperature) for a city using the OpenWeather API. Built with Jetpack Compose, Hilt, Retrofit, and Moshi.

Features

Splash screen

Enter a city to fetch weather data

View weather description and temperature

Detail screen showing the weather

Back navigation from detail to home screen

Error handling for invalid city names

Uses Kotlin coroutines for asynchronous network calls

Screenshots

(Add your screenshots here, e.g., splash screen, home screen, detail screen)

Getting Started
Prerequisites

Android Studio (latest stable version)

Minimum SDK: 24

OpenWeather API key (free at OpenWeather
)

Setup

Clone the repository:

git clone git@github.com:Ahmed-Ibrahim212/WeatherInfinion.git
cd WeatherInfinion


Open in Android Studio.

Add your OpenWeather API key in gradle.properties:

OPEN_WEATHER_API_KEY=your_api_key_here


Make sure the build.gradle references it:

buildConfigField "String", "OPEN_WEATHER_API_KEY", "\"${project.findProperty("OPEN_WEATHER_API_KEY")}\""


Sync Gradle and run the app on an emulator or device.

How to Use

Launch the app.

Enter a city name in the text field.

Press Get Weather.

View the weather on the home screen or navigate to the detail screen.

Use the back arrow on the detail screen to return home.

Tech Stack

Kotlin

Jetpack Compose

Hilt (Dependency Injection)

Retrofit + Moshi (API requests and JSON parsing)

Coroutines (async operations)

Notes

Enter a valid city name; otherwise, an error message will appear.

The app does not include maps; it focuses on weather information only.
