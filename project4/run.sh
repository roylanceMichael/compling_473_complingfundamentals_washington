#!/bin/sh
 
# Note that the array version of the skeleton needs more than the default maximum heap size.
# Not needed for the stream version.
 
java -cp ./source -Xmx1g App $*