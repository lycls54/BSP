#!/bin/bash 
# Testet einen Host mit den mitgegeben Parametern
# Anil Ersin Kaya
# 06.04.2017

# ------------------------------------------------------------
# This function shows the help text for this bash script
usage() {
	echo "
	$0 [-h|-s <sec>] <hostname>|<IP-Address>
	"
}

interval=10
dest=""

# ---------------------- main --------------------------------
# check parameters 
case $1 in
	"-h")
	 	usage
	 	exit 0
	  ;;
	"-s")
	  interval=$2
	  dest=$3
	  ;;
	*)
	  dest=$1
esac

while true; do
    if ! ping -c 1 $dest > /dev/null; then
   	 echo $dest FAILED
    else
   	 echo $dest OK
    fi
    sleep $interval
done
# ---------------------- end ---------------------------------
