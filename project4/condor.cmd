####################
#
# Mike Roylance UW NetID roylance
# LING473 Project 4
#
####################
 
Universe   = vanilla
 
Environment = PATH=/usr/local/bin:/usr/bin:/bin:/condor/bin:/opt/ANT/bin;LC_ALL=en_US.UTF-8
 
Executable  = run.sh
Arguments   = ./content/ ./content/seven-targets.txt
Log         = project4.log
Output      = output.txt
Error       = project4.err
Notification=Error
Queue