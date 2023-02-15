#!/bin/sh

#sets dir to directory containing this script
dir=`dirname $0`

cat /dev/stdin | node $dir/parser.js

#use $dir/ as prefix to run any programs in this dir
#so that this script can be run from any directory.


