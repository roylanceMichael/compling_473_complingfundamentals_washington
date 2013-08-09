#!/bin/sh
if [ -n $1 ]
then
  ruby ./source/main.rb /corpora/LDC/LDC99T42/RAW/parsed/prd/wsj/14
else  
	ruby ./source/main.rb $1
fi
