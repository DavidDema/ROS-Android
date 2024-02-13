
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
**Note:** This android device used in this approach does not need root rights.

#### Install the Alpine-Term app
Download and install the APK. Unfortunately this app is not available in the Google PlayStore, an alternative link is found here: https://apkcombo.com/alpine-term/alpine.term/

#### Install Docker on Alpine-Term
Follow these steps to install docker on your Android device in Alpine-Term:
```
# add docker package
sudo apk add docker
```
```
sudo rc-update add docker boot
```
```
# start docker deamon
sudo service docker start
```
For more details, watch this [video](https://www.youtube.com/watch?v=3LCUp24hPt4&ab_channel=WMCBTech) (starting at 2:11).

#### Pull and start ROS noetic docker image
Next up, we pull a ROS docker image, e.g. noetic-base.
```
docker pull ros:noetic-ros-base
```
Then we run the docker container
```
docker run -it --network=host ros:noetic-ros-base
```
And source the ROS workspace
```
cd /
source ros_.sh
```

#### Check ROS installation using a remote ROS Master
For testing purposes we start a ROS Master on another Device "DEVICE MASTER" and set the ROS_MASTER_URI environment variable accordingly.
```
# DEVICE MASTER
export ROS_MASTER_URI=http://192.168.170.1:11311/
roscore
```
On the Android device in the Docker container we set the ROS_MASTER_URI from the Host-Device "DEVICE MASTER" and check the current rostopics published to the network:
```
# DEVICE ANDROID
export ROS_MASTER_URI=http://192.168.170.1:11311/
rostopic list
```
The output should look like this:
```
/rosout
/rosout_agg
```
This means that we successfully receive the topics published by the master.

#### ROS Master on the Android device
In order to run the ROS core on an Android device we execute the command
```
roscore
```
Here an error occurs that could not be solved.
**Error msg:**
```
RLException: ERROR: could not contact master ...

process[master]: started with pid [71]
RLException: ERROR: could not contact master [http://alpine-term:11311/]
The traceback for the exception was written to the log file
[master] killing on exit
Traceback (most recent call last):
  File "/opt/ros/noetic/bin/rosmaster", line 34, in <module>
    import rosmaster
  File "/opt/ros/noetic/lib/python3/dist-packages/rosmaster/_init_.py", line 35, in <module>
    from .main import rosmaster_main
  File "/opt/ros/noetic/lib/python3/dist-packages/rosmaster/main.py", line 43, in <module>
    import rosmaster.master
  File "/opt/ros/noetic/lib/python3/dist-packages/rosmaster/master.py", line 45, in <module>
    import rosgraph.xmlrpc
  File "/opt/ros/noetic/lib/python3/dist-packages/rosgraph/_init_.py", line 36, in <module>
    from . masterapi import Master, MasterFailure, MasterError, MasterException
  File "/opt/ros/noetic/lib/python3/dist-packages/rosgraph/masterapi.py", line 45, in <module>
    from . names import make_caller_id
  File "<frozen importlib._bootstrap>", line 991, in _find_and_load
  File "<frozen importlib._bootstrap>", line 975, in _find_and_load_unlocked
  File "<frozen importlib._bootstrap>", line 671, in _load_unlocked
  File "<frozen importlib._bootstrap_external>", line 844, in exec_module
  File "<frozen importlib._bootstrap_external>", line 956, in get_code
  File "<frozen importlib._bootstrap_external>", line 1037, in get_data
KeyboardInterrupt
```
