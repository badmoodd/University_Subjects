#!/bin/bash

# Если в программу передаётся только имя 
# входного cpp файла, то после выполнения 
# бинарник будет удалён. Если передано ещё и
# имя выходного файла, то бинариник не будет
# удалён, а сохранён с этим именем.

remove_after=1 # false
output=run_cpp

print_usage() {
	echo "Usage: `basename "$0"` [input cpp file] [output bin file]"
}
	
if [[ $1 ]];then 
	target=$1
	# output=run_"${target%%.*}"
else
	print_usage
	exit 1
fi

if [[ $2 ]]; then
	output=$2
else 
	remove_after=0 # true
fi

# gcc $target -lstdc++ -o $output
g++ $target -o $output

if [[ $? -eq 0 ]];then
	./$output
else 
	remove_after=1 # false
fi

if [[ $remove_after -eq 0 ]]; then
	rm $output
fi
