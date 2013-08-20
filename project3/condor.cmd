####################
#
# James White UW NetID jimwhite
# LING473 Project 3
#
####################

Universe   = vanilla

Environment = PATH=/opt/mono/bin:/usr/local/bin:/usr/bin:/bin:/condor/bin:/opt/ANT/bin;LC_ALL=en_US.UTF-8

Executable  = run.sh
Log         = project3.log
Input       = /opt/dropbox/12-13/473/project3/fsm-input.utf8.txt
Output      = output.html
Error	    = project3.err
Notification=Error
Queue
