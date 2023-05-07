# meta-tinker-board-edger

# Table of contents

1. [Introduction](#introduction)
2. [Dependencies](#dependencies)
3. [List of Tinker Boards supported](#list_of_boards_supported)
4. [Using the meta-asus-edge-r layer](#meta_asus_edge_r_usage)
    1. [Step 1:Fetching the Source](#source_fetch)
    2. [Step 2:Setting up the Environment](#setup)
    3. [Step 3:Bblayers.conf Setup](#bblayers.conf_setup)
    4. [Step 4:Local.conf Setup](#local.conf_setup)
    5. [Step 5:Building the Image](#build_image)
5. [Serial Console](#serial_console)
6. [Login Details](#login)
7. [Networking](#networking)
    1. [Wifi Connectivity](#wifi)
    2. [Bluetooth Connectivity](#bluetooth)    
8. [Change Log](#change_log)
9. [Contributing](#contributing)
10. [Reporting Bugs](#bugs)
11. [Maintainers](#maintainers)


## Introduction <a name="introduction"></a>

An OpenEmbedded/Yocto Project BSP layer for the ASUS Tinker Board Edge R machines

## Dependencies <a name="dependencies"></a>

The meta-asus-edge-r layer depends on:

	URI: git://git.yoctoproject.org/poky
	branch: kirkstone

	URI: git://git.openembedded.org/meta-openembedded
	branch: kirkstone

	URI: https://github.com/JeffyCN/meta-rockchip.git
	branch: kirkstone

## List of Tinker Boards supported <a name="list_of_boards_supported"></a>

1) ASUS Tinker Board Edge R

## Using the meta-radxa layer <a name="meta_asus_edge_r_usage"></a>

### Step 1: Fetching the source <a name="source_fetch"></a>

Fetch the source using the commands given below:

<pre><code>
~ $ mkdir yocto
~ $ cd yocto
~/yocto $ git clone git://git.yoctoproject.org/poky -b kirkstone
~/yocto $ git clone git://git.openembedded.org/meta-openembedded -b kirkstone
~/yocto $ git clone https://github.com/JeffyCN/meta-rockchip.git -b kirkstone
~/yocto $ git clone https://github.com/mike-at-dusty/meta-asus-edge-r.git -b kirkstone
</code></pre>

### Step 2: Setting up the Environment <a name="setup"></a>

<pre><code>
~/yocto $ source poky/oe-init-build-env
</code></pre>

#### Step 3: Bblayers.conf Setup <a name="bblayers.conf_setup"></a>

* You can simply copy the bblayers.conf.sample present in meta-asus-edge-r/conf folder to the build/conf folder and rename it to bblayers.conf

<div align="center"><b>OR</b></div>

* Add the layers manually as given below to the bblayers.conf in the build/conf folder

<pre><code>
  ${TOPDIR}/../poky/meta \
  ${TOPDIR}/../poky/meta-poky \
  ${TOPDIR}/../poky/meta-yocto-bsp \
  ${TOPDIR}/../meta-openembedded/meta-oe \
  ${TOPDIR}/../meta-openembedded/meta-filesystems \
  ${TOPDIR}/../meta-openembedded/meta-networking \
  ${TOPDIR}/../meta-openembedded/meta-python \
  ${TOPDIR}/../meta-rockchip \
  ${TOPDIR}/../meta-asus-edge-r \
</code></pre>

### Step 4: Local.conf Setup <a name="local.conf_setup"></a>

* You can simply copy the local.conf.sample present in meta-asus-edge-r/conf folder to the build/conf folder and rename it to local.conf

<div align="center"><b>OR</b></div>

* Add the following lines in the build/conf/local.conf

```
MACHINE = "asus-edge-r"
DISTRO_FEATURES_append = " pam systemd"
VIRTUAL-RUNTIME_init_manager = "systemd"
PACKAGECONFIG:append_pn-systemd = " resolved networkd"
```

### Step 5: Building the Image <a name="build_image"></a>

* If you wish to build a minimal image use the command given below:
```
~/yocto/poky/build $ bitbake -k edge-r-minimal-image
```
<div align="center"><b>OR</b></div>

* If you wish to build a console image use the command given below:

```
~/yocto/poky/build $ bitbake -k edge-r-console-image
```
<div align="center"><b>OR</b></div>

**At the end of a successful build, you should have an update.img image in tmp/deploy/images/asus-edge-r folder. The update.img can be directly flashed to emmc memory on the Edge R**

## Serial Console <a name="serial_console"></a>

The Serial Console for the ASUS Tinker Board Edge R is enabled on UART-0 (GPIO Pins 8 and 10).

**Helpful Links:**

+ ASUS Tinker Board Edge R (https://tinker-board.asus.com/doc_er.html)
+ ASUS Tinker Board Edge R Forum (https://tinker-board.asus.com/forum/index.php?/forum/14-tinker-edge-r/)
+ Yocto BSP layer for the Rockchip SOC boards (https://github.com/JeffyCN/meta-rockchip)

## Login Details <a name="login"></a>

```
Username: root
```

## Networking <a name="networking"></a>

**Network Devices available:**

+ Wifi
+ Ethernet
+ Bluetooth

### Wifi Connectivity <a name="wifi"></a>

+ Using Commandline Based GUI(nmtui) [Available on console image]

nmtui is a curses based GUI. You can start it by running the following command:

```
nmtui
```

+ Using Commandline Utility(nmcli) [Available on console image]

nmcli is a command-line tool for controlling NetworkManager and reporting network status.

**List available devices**

```
nmcli dev
```

**Turn on Wifi**

```
nmcli r wifi on
```

**Scanning different devices**

```
nmcli dev wifi
```

**Connect to WiFi Hotspot**

```
nmcli dev wifi connect "SSID" password "PASSWORD"
```

***Note:You need to replace “SSID” and “Password” with your actual WiFi’s SSID and password.***

### Bluetooth Connectivity <a name="bluetooth"></a>

+ Bluetooth on ASUS Tinker Board Edge R

**Activating bluetooth:**

```
hciconfig hci0 up
```

**Check Bluetooth device:**

```
 $ hciconfig
 hci0:    Type: Primary  Bus: USB
          BD Address: 3C:91:80:B7:A1:BC  ACL MTU: 1021:6  SCO MTU: 255:12
          UP RUNNING 
          RX bytes:1272 acl:0 sco:0 events:71 errors:0
          TX bytes:1086 acl:0 sco:0 commands:71 errors:0
```

## Change Log <a name="change_log"></a>

+ Initial creation of layer for ASUS Tink Board Edge R

## Contributing <a name="contributing"></a>

Please use github for pull requests: https://github.com/mike-at-dusty/meta-asus-edge-r/pulls

## Reporting bugs <a name="bugs"></a>

The github issue tracker (https://github.com/mike-at-dusty/meta-tinker-board-edger/issues) is being used to keep track of bugs.

## Maintainers <a name="maintainers"></a>

* Mike Thompson <mthompson@dustyrobotics.com>
