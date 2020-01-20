Mobile testing Homework 2
====
This project is the result of mobile testing homework №2 & №3 and represents native and web applications tests.

Getting started
----
You need:
1. Access to EPAM Network
2. Mobile Cloud token
   
Properties
---
Tests need appropriate capabilities and variables. Test properties files are located in the test resources directory (src/test/resources/properties/) 
+ Device:
  - platform: [ Android | iOS ] Default values are set in POM.xml file as profile properties.
  - driver: appium server URL
  - udid: device serial id. Default values are set in POM.xml file as profile properties. It's possible to leave this parameter without value (not recommended) or to set in POM.xml or directly in .properties file.
- Native tests:
  - aut: absolute path to testing app
  - (package | activity) | bundleID: parameters to run app on Android | iOS device
  - email | username | password: test data for new app account
- Web tests:
  - sut: testing site address in format "[www].[site name].[domain]"
  - homePageTitle: expected title of the first open page
  - query: searched word
  
**Also to run tests you need to create credentials.properties file in the root of the project and set property:
'token={your token}'**

Run
---
1. Create credentials.properties file with token
2. Connect to EPAM Network
3. Run Maven project with one desired profile 

**Do not forget to release the device after test, "keepDevice" option is enabled.**

About devices
---
+ Android
  - Web test: web test pass only on devices with latest version of Chrome (79.0.3945)
  - Native app test: although app can be installed on most android devices, test might failure because of the keyboard that hides some elements on the page.
    Passed on Google devices
- iOS
  - Web test: pass on random device
  - Native app test: pass only on following devices:
    - iPhone SE (udid=ec645b7fe329a5ee0b53bd3494ad2cd8963ec07a)
    - iPhone 7 (udid=fa7decbf149acbf5f395539a25635d9844c169d3)
    - iPhone 8 Plus (udid=3e50758dd2e1179a014ee7b51dc4285d73118107)

Notes
---
+ Native Test on iOS  ***fails***, because title of the BudgetActivity page doesn't correspond to the expected (indicated in homework 2).
- To assure cross-platform native test I had to reject checkbox state assertion, because this element has different attributes on different platforms ("selected=true" on Android, "value=1" on iOS)
