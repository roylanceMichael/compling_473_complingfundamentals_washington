####################
#
# Mike Roylance UW NetID roylance
# LING473 Project 5
#
####################
 
Universe   = vanilla
 
Environment = PATH=/usr/local/bin:/usr/bin:/bin:/condor/bin:/opt/ANT/bin;LC_ALL=en_US.iso88591
 
Executable  = run.sh
Arguments   = ./content/ ./content/test.txt
Log         = project5.log
Output      = output.txt
Error       = project5.err
Notification=Error
Queue