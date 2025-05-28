#!/bin/sh

export PATH=${PATH}:/usr/local/java/jdk1.8.0_152/bin/

if test -z "$DISPLAY"; then
    export DISPLAY=":0"
fi 

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
