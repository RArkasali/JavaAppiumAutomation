# Amazon Automation

The project defines page object model for automating the Android apps

## Getting Started
To execute automation project on real device, the execution requires Appium tool to be run in backend to interact with devices. Initiate the DeviceTestRunner.java as Junit Tests from eclipse/ preferred IDE. 

### Prerequisites

Update the properties file with values before executing the automation scripts. Below are he mandatory properties to be defined

1.test.execution.environment -> Device type configuration - realdevice/emulator

2.Android Device capabilities
 * android.realdevice.platform_name = android
 * android.realdevice.device_name = samsung
 * android.realdevice.version = 6.0.1
	
3.Amazon app needs to be installed from PlayStore


## Running the tests

The Base class to execute the entire automation suite is: DeviceTestRunner.java. Once, the maven build is success then we need to go DeviceTestRunner class and select run as Junit Tests.

## About Project Structure

We have set of classes in this page object model framework. We have defined the package structure as com.amazon.*
* base package, hold the BasePOM class, which plays a key role in distributing the objects.
* pageObjects package, where we will initialize our APP access elements.
* pages package, will handle actions on particular page
* utils package will handle device actions like click, scroll, swipe etc.,.
* test runner package class in the Junit class, where the execution starts.
* feature folder will be placed in resources folder and it have the all required features

