# Android GitHub User Search Application

An Android application that displays a list of GitHub users filtered by a keyword. When tapping on any match, a detail page is displayed showcasing their login name, avatar image, star count, etc.

## Features

- Implement filtering for GitHub users.
- Only send the actual request after the second keystroke.
- Add a 1-second throttling before starting the request.
- Utilize GitHub REST API for the solution ([GitHub REST API documentation - GitHub Docs](https://docs.github.com/en/rest)).

## User Experience (UX)

- Prepare the app for general error handling (4xx and 5xx errors, offline).
- Implement loading states for asynchronous operations.

## Testing

- Cover the solution with unit tests; no need to test the UI.
