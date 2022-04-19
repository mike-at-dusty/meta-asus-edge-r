#!/bin/bash

# configs
SLEEP_INTERVAL=1  # in s
DEBUG_LOG=false

# Tinker Board 2 is 154, Tinker Edge R is 158
FAN_GPIO=158

# set temps (in degrees C * 1000) and corresponding pwm values in ascending order and with the same amount of values
TEMP_TRIGGER=45000
TEMP_BUFFER=2500

# hardcoded termal_zone0 for CPU
FILE_TEMP=/sys/class/thermal/thermal_zone0/temp
#FILE_CONFIG=""

# checking for privileges
if [ $UID -ne 0 ]
then
  echo "Writing to sysfs requires privileges, relaunch as root"
  exit 1
fi

# export fan gpio
function exportPin {
  if [ ! -d /sys/class/gpio/gpio$FAN_GPIO ]; then
    echo $FAN_GPIO > /sys/class/gpio/export
  fi
}

# set fan gpio as output
function setOutput {
  echo "out" > /sys/class/gpio/gpio$FAN_GPIO/direction
}

function interpolate_gpio {
  i=0
  TEMP=$(cat $FILE_TEMP)
  OLD_VALUE=$(cat /sys/class/gpio/gpio$FAN_GPIO/value)

  if [ $DEBUG_LOG = true ]; then
    echo "current temp: $TEMP, value: $(cat /sys/class/gpio/gpio$FAN_GPIO/value)"
  fi

  if [[ $TEMP -le $((TEMP_TRIGGER - TEMP_BUFFER)) ]]; then
    if [ $OLD_VALUE = 1 ]; then
      echo 0 > /sys/class/gpio/gpio$FAN_GPIO/value
      echo "temp: $TEMP, change value to 0"
    fi
  elif [[ $TEMP -ge $TEMP_TRIGGER ]]; then 
    if [ $OLD_VALUE = 0 ]; then
      echo 1 > /sys/class/gpio/gpio$FAN_GPIO/value
      echo "temp: $TEMP, change value to 1"
    fi
  fi
  
  return
}

function reset_on_fail {
  echo "exiting, resetting fan to auto control..."
  echo 1 > /sys/class/gpio/gpio$FAN_GPIO/value
  echo $FAN_GPIO > /sys/class/gpio/unexport
  exit 1
}

# always try to reset fans on exit
trap "reset_on_fail" SIGINT SIGTERM

function run_daemon {
  while :; do
    interpolate_gpio
    sleep $SLEEP_INTERVAL
  done
}


# print debug info
echo "--------------------------------"
echo "SLEEP_INTERVAL = $SLEEP_INTERVAL"
echo "FAN_GPIO       = $FAN_GPIO"
echo "DEBUG_LOG      = $DEBUG_LOG"
echo "TEMP_TRIGGER   = $TEMP_TRIGGER"
echo "TEMP_BUFFER    = $TEMP_BUFFER"
echo "--------------------------------"
echo

# set fan control
exportPin
setOutput

# finally start the loop
run_daemon

