# GitHub-Test

This project demonstrates automated testing of GitHub functionalities using Selenium WebDriver and TestNG. The purpose of this project is to provide a framework for automating common tasks on GitHub, such as logging in, editing profiles, and forking repositories. This can be useful for testing the functionality of GitHub's web interface, or for automating repetitive tasks.

## Getting Started

### Prerequisites

1. [Java](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) installed on your system.
2. [Selenium WebDriver](https://www.selenium.dev/downloads/) library.
3. [TestNG](https://testng.org/doc/download.html) testing framework.
4. [Chrome WebDriver (chromedriver)](https://sites.google.com/a/chromium.org/chromedriver/downloads).

### Setup

1. Clone this repository to your local machine.
```bash
   git clone https://github.com/SandeepVashishtha/Github-Testing.git
```
2.Download the Chrome WebDriver (chromedriver) and set its path in `config.properties` file. You can do this by opening the config.properties file in a text editor and adding a line like `chromedriver_path=/path/to/chromedriver`.
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

## Contributing

If you'd like to contribute to this project, please feel free to fork the repository, make your changes, and then submit a pull request. All contributions are welcome!

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

## Author

Sandeep Vashishtha
