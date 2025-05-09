#!/bin/sh

export PATH=${PATH}:/usr/local/java/jdk1.8.0_152/bin/

if test -z "$XDG_RUNTIME_DIR"; then
    export XDG_RUNTIME_DIR=/run
fi

if test -z "$DISPLAY"; then
    export DISPLAY=":0"
fi 

# wait for weston
while [ ! -e  $XDG_RUNTIME_DIR/wayland-0 ] ; do sleep 0.1; done
sleep 1

#Start the GUI application
export DP_HOME=/config/gui

if [ -f $DP_HOME/rc.txt ]
then
    cd $DP_HOME
    . $DP_HOME/rc.txt
    if [ -f $JARFILE ]
    then
        while true
        do
            # stderr and stdout must not be piped to /dev/null on DR10ARM or else the log messages from java will be missing from journalctl.
            java $JAVA_CMD_LINE
        done
    else
        xterm &
    fi
fi
