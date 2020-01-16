Mobile testing Homework 2
====
This project is the result of mobile testing homework №2 and represents native and web applications tests.

Getting started
----
You need to install:
1. Appium desktop server
2. Android app bundle
   - Android device emulator
   
Properties
---
Tests need appropriate capabilities and variables. Properties files are located in the root of the project (nativetest.properties and webtest.properties) 
+ Common:
  - platform: [ Android | iOS ]
  - driver: appium server URL
- Native tests:
  - aut: absolute path to testing app
  - email | username | password: parameters for new app account
- Web tests:
  - sut: testing site address in format "[www].[site name].[domain]"
  - homePageTitle: expected title of the first open page
  - query: searched word

Run
---
1. Run device emulator
2. Start Appium desktop server with "relaxed security" option
3. Run Maven with desired profile 

Notes
---
Native Test ***fails***, because app behaviour does not correspond to common sense.
Checkbox on the Registration page can not be selected, but this do not make obstacles to registration. So what is this checkbox for?
