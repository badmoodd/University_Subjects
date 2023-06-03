#!/bin/bash

move() {
	if [ -e $1 ]; then 	
		mv $1 $2
		echo [$1] saved to [$2]
	else
		echo not founded: $1
	fi
}

copy() {
	if [ -e $1 ]; then
		cp -r $1 $2
		echo [$1] copied to [$2]
	else
		echo not founded: $2
	fi
}


prepare() {
	ostis_dir=$1
	move "$ostis_dir/problem-solver" "$ostis_dir/problem-solver.save"
	copy ./problem-solver "$ostis_dir"
	
	copy ./tests "$ostis_dir/kb"

	$ostis_dir/scripts/./build_kb.sh
	$ostis_dir/scripts/./build_problem_solver.sh -f
}



if [ $1 ]; then
	if [ -e $1 ]; then 
		if [[ "$(basename $1)" == "ostis-example-app" ]]; then 
			echo ostis founden, it\'s okay 	
			prepare $1
			exit 0
		fi
	fi

	echo ostis not founded [ $1 ]
	exit 1

else 
	echo usage: autoscript.sh [path_to_ostis_example_app]
fi


