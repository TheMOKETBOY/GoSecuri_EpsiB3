#!/bin/bash


# -------------------------------------------------------------------- |
#                       SCRIPT OPTIONS                                 |
# ---------------------------------------------------------------------|
OPENCV_VERSION='4.3.0'       # Version to be installed
OPENCV_CONTRIB='NO'          # Install OpenCV's extra modules (YES/NO)
# -------------------------------------------------------------------- |

git clone https://github.com/opencv/opencv.git --single-branch OPENCV_VERSION
mv OPENCV_VERSION cv
cd cv
mkdir build
cd build
cmake ..
make
make install