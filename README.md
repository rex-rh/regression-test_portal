# Advanced Level - End to End Selenium Test Automation Framework (Testng - Java).

this Test is designed using 
1. page object model
2. Page Object pattern helper class

Report Listeners
1. Extent Report listener
2. Retry Lister

# Run Test
To run the  test execute the testng.xml file which contains all the regression test

# Test configuration
Open the config file
BrowserName: Provide the desired browser to run the test,  four options is available (Chrome, Firefox, RemoteChrome, RemoteFirefox)
UrlPROD: the Url of the wb application
RemoteURL: this url should be provided when executing the test on any remote server

incognito: this is a boolean value(true or false). it determines if the browser selected  will open in incognito mode.
headless: this is a boolean value(true or false). it determines if the browser will run in headless mode.

# Run Test
To run the  test execute the testng.xml file which contains all the regression test

# Docker file
the docker file has been Setup to include:
1. selenium/standalone-chrome
2. Maven version:3.8.6
3. Maven directory.

To build a docker image for the Test: "docker build -t ###### . "
To run the docker image in a container : "docker run ######"

To pull a sample docker  image for this test run : "docker image pull ennymax/clipboard:1.0.1"
