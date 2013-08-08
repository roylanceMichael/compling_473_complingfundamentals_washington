#!/bin/sh
if [ -n "$1" ] # note the quoted test strings
then
  ruby ./source/main.rb /corpora/LDC/LDC99T24/RAW/parsed/prd/wsj/14
else  
	ruby ./source/main.rb $1
fi
