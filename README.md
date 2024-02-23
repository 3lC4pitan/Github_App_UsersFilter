Android application that displays a list of GitHub users filtered by some keyword. When you tap on any of the matches, display a
detail page with their login name, avatar image, star count etc.

Implement filtering for GitHub users
Only send the actual request after the second keystroke
Add 1 second throttling before you would start the request
Use GitHub REST API for your solution ( GitHub REST API documentation - GitHub Docs )
UX
Prepare the app for general error handling (4xx and 5xx errors, offline)
Implement loading states for async operations
Testing
Cover your solution with unit tests, no need to test the UI
