####################
#
# Mike Roylance UW NetID roylance
# LING473 Project 4
#
####################
 
Universe   = vanilla
 
Environment = PATH=/usr/local/bin:/usr/bin:/bin:/condor/bin:/opt/ANT/bin;LC_ALL=en_US.UTF-8
 
Executable  = run.sh
Arguments   = /dropbox/12-13/473/project6/bp-20100419.txt /dropbox/12-13/473/project6/bp-20100828.txt
Log         = project6.log
Output      = output.txt
Error       = project6.err
Notification=Error
Queue