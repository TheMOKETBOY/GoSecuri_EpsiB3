###################################################################################
#AUTHOR : Kevin Sapp
#Version : 1.0
#Description: Create OPENCV4 Docker image
#Estimated Buid time: 60 minutes
#Estimated Image Size: 12.0 GB
#Notes: You can remove the OPTIONAL installs to save on disk space.
#History: 02/07/2019 - Created OPENCV4 build file v1.0 
###################################################################################
#Download image from Ubuntu registry
#Use the latest ubuntu image 
###################################################################################

FROM ubuntu:latest
MAINTAINER Kevin Sapp <ksapp@codeduet.com>

###################################################################################
# Install the package requirements for OpenCV4
# *** This warning can be ignored during the build *** 
# debconf: delaying package configuration, since apt-utils is not installed
###################################################################################

RUN echo "Install all package dependencies for OPENCV4 ..."
RUN apt-get update -y && apt-get install -y \
	wget \
	build-essential \
	cmake \
	unzip \
	pkg-config \
	libjpeg-dev \
	libpng-dev \
	libtiff-dev \
	libavcodec-dev \
	libavformat-dev \
	libswscale-dev \
	libv4l-dev \
	libxvidcore-dev \
	libx264-dev \
	libgtk-3-dev \
	libatlas-base-dev \
	gfortran \


###################################################################################
# Install OPTIONAL packages for network troubleshooting (Ping and testing connection)
###################################################################################

#RUN echo "Install all OPTIONAL packages ..."
#RUN apt-get install -y \
#	net-tools \
#	iputils-ping \
#	curl \
#	netcat \
#	dnsutils 
 
#Clean up apt-get cache
RUN rm -rf /var/lib/apt/lists/*
RUN echo "Create a new directory ..."
RUN mkdir /install_opencv

###################################################################################
# Change current directory 
###################################################################################
WORKDIR /install_opencv


###################################################################################
# Install OpenCV4 - With all features\algorithms enabled
# ***Note: Some features are installed that require licensing for commercial use. Free for personal use.
###################################################################################

RUN echo "Download source files for OpenCV4 ..."
RUN wget -O opencv.zip https://github.com/opencv/opencv/archive/4.3.0.zip
RUN wget -O opencv_contrib.zip https://github.com/opencv/opencv_contrib/archive/4.3.0.zip
RUN unzip opencv.zip
RUN unzip opencv_contrib.zip
RUN mv opencv-4.0.0 opencv
RUN mv opencv_contrib-4.0.0 opencv_contrib
RUN mkdir /install_opencv/opencv/build

WORKDIR /install_opencv/opencv/build

RUN echo "Compile OPENCV4 from source build ..."
RUN cmake -D CMAKE_BUILD_TYPE=RELEASE \
	-D CMAKE_INSTALL_PREFIX=/usr/local \
	-D INSTALL_PYTHON_EXAMPLES=ON \
	-D INSTALL_C_EXAMPLES=OFF \
	-D OPENCV_ENABLE_NONFREE=ON \
	-D OPENCV_EXTRA_MODULES_PATH=/install_opencv/opencv_contrib/modules \
	-D BUILD_EXAMPLES=ON ..
	
###################################################################################
#Change this number if you are using more and less than 2 CPU Cores
#Show CPU cores: grep -c ^processor /proc/cpuinfo or nproc --all
###################################################################################

RUN echo "Change CPU Cores to 2 cores to speed up installation ..."
RUN make -j2

RUN echo "Install OpenCV4 ..."
RUN make install
RUN ldconfig



###################################################################################
#Cleanup Install Directory and files
###################################################################################
WORKDIR /
RUN echo "Cleanup files and folders ..."
RUN rm -rf /install_opencv

RUN echo "Installation Completed Successfully!"