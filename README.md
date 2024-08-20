# Notes Android App

## Overview

The Notes Android App is a simple note-taking application built using Java for Android. The app allows users to manage their notes with features including Google Sign-In, skipping sign-in, and basic CRUD operations using SQLite. This project is designed to help beginners understand Android app development, fragment management, and SQLite database interactions.

## Features

- **Google Sign-In Integration**: Users can sign in using their Google account.
- **Skip Sign-In Option**: Users can choose to skip sign-in and use the app without authentication.
- **Note Management**: Add, edit, and view notes.
- **SQLite Database**: Stores notes persistently using SQLite.

## Architecture

- **Activity**: `MainActivity` is the entry point of the app, managing fragment transactions based on user authentication status.
- **Fragments**: 
  - `LoginFragment`: Handles user authentication with Google and includes an option to skip sign-in.
  - `NotesFragment`: Displays the list of notes and provides an option to add a new note.
  - `AddEditNoteFragment`: Allows users to add or edit notes.
- **Database**: `SQLiteHelper` manages SQLite operations including CRUD operations for notes.
- **Adapter**: `NotesAdapter` binds note data to the `RecyclerView` in `NotesFragment`.
