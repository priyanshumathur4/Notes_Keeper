# Note_Keeper Android App

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

## File Structure

- **MainActivity.java**: Handles authentication and fragment transactions.
- **LoginFragment.java**: Manages Google Sign-In and skip sign-in.
- **NotesFragment.java**: Displays and manages the list of notes.
- **AddEditNoteFragment.java**: Handles note addition and editing.
- **SQLiteHelper.java**: Manages SQLite database operations.
- **NotesAdapter.java**: Adapter for displaying notes in a `RecyclerView`.
- **Note.java**: Model class for notes.

## XML Layout Files

- **activity_main.xml**: Layout for the main activity, containing a `FrameLayout` for fragments.
- **fragment_login.xml**: Layout for the login fragment with sign-in and skip sign-in buttons.
- **fragment_notes.xml**: Layout for the notes fragment with a `RecyclerView` and an add note button.
- **fragment_add_edit_note.xml**: Layout for adding or editing notes.
- **notetv.xml**: Layout for displaying individual notes in a `RecyclerView`.

## Database Schema

- **Notes Table**:
  - `id` (TEXT, PRIMARY KEY): Unique identifier for each note.
  - `title` (TEXT): Content of the note.
