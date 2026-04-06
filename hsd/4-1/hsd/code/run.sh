#!/bin/bash
if [ -z "$1" ]; then
    echo "Usage: $0 ClassName"
    exit 1
fi

echo "Compiling $1.java..."
javac "$1.java"
if [ $? -eq 0 ]; then
    echo "================================"
    echo "Running $1..."
    java "$1"
else
    echo "Compilation failed."
fi
