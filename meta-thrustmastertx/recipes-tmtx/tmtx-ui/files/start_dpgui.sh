#!/bin/bash
/usr/local/network_related/set_network_name.sh
/usr/local/network_related/rc.txt.sh restart
sleep 5
export DISPLAY=:0
cd /config
bash start_gui.sh