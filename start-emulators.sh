#!/bin/bash

# Script para iniciar Firebase Emulators con Java 21

export PATH="/opt/homebrew/opt/openjdk@21/bin:$PATH"
export JAVA_HOME="/opt/homebrew/opt/openjdk@21"

cd /Users/solangeguerrero/StudioProjects/PARCIALTP3

echo "🔥 Iniciando Firebase Emulators..."
echo "Java version:"
java -version

firebase emulators:start --only auth,firestore

