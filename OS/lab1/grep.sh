#!/bin/bash

grep -rli $1 $2 | xargs ls -l | awk '{ print $9 "  " $5 " bytes" }'
