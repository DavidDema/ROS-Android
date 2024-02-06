
# Running ROS on an Android device

This project should enable a full version of ROS running on Android.

## Introduction

### Rosjava (ROS1)
This project is not up-to-date, therefore we encountered many problems due to version incompability. A big problem was that only Android NDKs up to 12 were supported. This is not feasible for an implementation with current smartphones, therefore we decided to find a different approach.

### ROS2 Mobile
The ROS2-Mobile App for Android enables the development of Widgets in Java using the ROS2 communication on the same Network using DSS. The widgets can be defined as a publisher or subscriber. The problem we encountered in this implementation is, that we can not use already implemented ROS packages, e.g. ROS control package.

The **ROS2_Mobile** app is a [forked repo](https://github.com/YasuChiba/ROS2-Mobile-Android) trying to port the official ROS-Mobile-Android app to ROS2. The functionalities/widgets supported by this implementation are:
- [x] battery
- [x] button
- [ ] camera (ros2_java related issues)
- [ ] debug
- [x] gps
- [ ] gridmap
- [x] joystick
- [x] label
- [x] laserscan
- [ ] logger
- [ ] path
- [ ] pose
- [ ] rqtplot
- [x] switchbutton
- [ ] touchgoal
- [x] viz2d
- [x] gps sender
- [x] imu sender

#### Installation
This part shows how to build ROS2 packages for the use with Java. Please refer to this  [repo](https://github.com/YasuChiba/ros2-android-build) for more details.  
Steps:
- Change directory
```
cd ./ROS2_rosjava_build
```
- Build docker image
```
docker build -t ros2java-android-build ./
```
- Build
```
python3 run.py ./out/soOut ./out/jarOut
```

The files in the folders `./out/jarOut` and `./out/soOut` need to be copied to the Android project according this file-tree:
```
app (e.g. ROS2_Mobile)
 -libs
    - .jar files
 - src
    - main
        - java
        - jniLibs
            - arm64-v8a
                - .so files
        - res
        - AndroidManifest.xml

```
Now you are ready to use the Android project with the imported ROS2 packages. Refer to the example project for testing: https://github.com/YasuChiba/ros2-android-test-app

**Note:** There is also the option to use the [Maven artifact repository](https://github.com/rosjava/rosjava_mvn_repo), currently only available for ROS1.

#### Creating new widgets
In order to create a new widget for the ROS2_Mobile app you need to follow these steps:
- Open the project folder `./ROS2_Mobile` in Android Studio.
- Duplicate an existing widget package in the directory `java/com.schneewittchen.rosandroid/widgets/` and name it, e.g. `testWidget`
- Rename the files in the package, e.g. `testWidgetData, testWidgetView, ...`
- Add your new widget name to the file `res/values/widgets.xml`, first to the string-array "widget_names" and then add the Description at the end of the file according to the other widget implementations.
- Duplicate the widget detail xml-file of another widget and name it accordingly, e.g. `res/layout/widget_detail_gps.xml`

The widget package consists of four main classes:
- **Data:** Representation of the data structure of this widget
- **DetailVH:** This class is used for the settings of the widget
- **Entity:** Here the entity class is defined, such as publisher or subscriber.
- **View:** This class describes how the widget looks like

**Concluding** the ROS2_Mobile app could be very useful for future implementations with a ROS "Master" device and not as a standalone option.

### Dockerized ROS1 on Android
The third approach was to use a dockerized version of ROS1 on Android using "Linux on Android" such as Termux or Alpine Term.
