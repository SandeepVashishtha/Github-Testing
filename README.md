# GitHub-Test

This project demonstrates automated testing of GitHub functionalities using Selenium WebDriver and TestNG.

## Getting Started

### Prerequisites

1. Java installed on your system.
2. Selenium WebDriver library.
3. TestNG testing framework.
4. Chrome WebDriver (chromedriver).

### Setup

1. Clone this repository to your local machine.
```
   git clone https://github.com/SandeepVashishtha/Github-Testing.git
```
2. Download the Chrome WebDriver (chromedriver) and set its path in `config.properties` file.
3. Install required dependencies by running:

```
 mvn clean install
```

## Running Tests

To run the tests, execute the following command:

```
mvn test
```

## Test Cases

1. **loginWithInvalidPassword():**
   - Enters invalid password and verifies error message.

2. **loginWithValidCredentials():**
   - Logs in with valid credentials.

3. **forgotPassword():**
   - Tests the Forgot Password functionality.

4. **SignInAndSignOut():**
   - Signs in, scrolls down, and signs out.

5. **editProfile():**
   - Edits the user profile.

6. **SearchandForkRepo():**
   - Searches for a repository, stars it, and forks it.

## Author

Sandeep Vashishtha
