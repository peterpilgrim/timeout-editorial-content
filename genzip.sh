#!/bin/bash

targetFile=timeout-editorial-content-1.0-src.zip

targetDir=target/dist

mkdir -p ${targetDir}

echo "Creating ${targetDir}/${targetFile}"

zip -r  ${targetDir}/${targetFile}  *.sh *.sbt  README *.txt *.md src

#end
