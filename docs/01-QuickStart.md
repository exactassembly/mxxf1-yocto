# Getting Started

## ;tldr;

To generate the default image in this repository once the DevContainer is properly initialized, open a shell in the devcontainer and run:

> /opt/cooker/scripts/broiler.sh broil

### Shortcut on VSCode 

Use the provided VSCode task (Terminal->Run Task...) choose the "Cooker-broil" task which will perform the necessary initialization, download, and build steps for the default target image type.

## Running Locally

Most developers using this project will want to execute the build on their local machine, allowing for interactive development and 

### Installation Requirements

- Docker
  - On Linux: Docker-CE
  - On Windows/OS X:
    - Docker Desktop
    - Rancher Desktop
- VSCode
  - VSCode extension: dev containers

### Starting the Devcontainer

Note: Ensure that Docker-Desktop is running before continuing

#### Option 1: Cloning into a container volume

1. Open a new VSCode window that is not a workspace for an existing project (File->New Window...)
2. Use the Remote Extension functions to checkout this source code in a container volume and automatically build the DevContainer 
   1. Click the green carats "><" button in the lower-left corner of the window
   2. A list will drop down from the window browser bar, select "Clone Repository in Container Volume..." 
   3. Enter the URL of this project source code (using the HTTPS option)
   4. You may be prompted for a username and password for the Git hosting service

#### Option 2: Running from a local clone (not recommended)

1. Clone git repository into a local folder
2. In VSCode: open cloned repository in VSCode (File->open folder...)
3. In VSCode: open the Command Palette (View->Command Palette...) and run: `Dev Containers: Reopen in Container`

## Running Remotely

This container is compatible with being built to execute within the GitHub Codespaces and similar services which support the DevContainer specification. As each platform has some differences the specific steps required to support execution in this manner is currently beyond the scope of this document. 

## Running within a CI/CD Pipeline

This container is compatible with being used within a Git-hook or other form of automated Continuous-Integration/Continuous-Delivery (CI/CD) pipeline. As each platform has some differences the specific steps required to support execution in this manner is currently beyond the scope of this document. 

## What is this stuff?

This repo contains boilerplate structure necessary to start a devcontainer environment in Visual Studio Code, download the necessary Yocto Project sources to temporary space, configure the Yocto/OpenEmbedded bitbake tools, and generate a runnable Yocto runtime.

### Devcontainer?

There are nearly infinite numbers of programming languages from C to Rust, and many more build orchestration tools from classic Makefile to CMake, which attempt to manage the complexities of system libraries, build configuration and versioning. While these often try to be 'universal', there can be OS and version dependencies for the very tools used, a typical solution is the Python 'pyenv' too which allows multiple versions of Python to exist in parallel.

For developers who may have multiple projects ongoing even managing pyenv is too much. A common modern solution for all of the dependencies of a project is to use Docker containers to hold both the "host" tools and the target development code. Microsoft introduced an open-source specification [devcontainers](https://containers.dev/) with an open-source reference implementation [devcontainers-cli](https://github.com/devcontainers/cli), which is part of the VSCode IDE by default, and also supported as a web based IDE through GitHub CodeSpaces.

### Yocto Project?

The [Yocto Project](https://www.yoctoproject.org) is a set of tools (bitbake) and metadata (layers) for building embedded Linux runtimes (kernel, filesystem, bootloader, etc.). It is supported by most major CPUs, and has a robust ecosystem of partners. The Yocto project itself is a 'meta' tool mean to be used to construct a user-specified configuration, and does not necessarily work for any specific hardware platform without some amount of manual configuration.

Once a complete set of Yocto source files has been deployed to your development platform, the 'bitbake' tool (the core tool for Yocto project builds) is invoked to actually execute all of the necessary build steps including cross-compliation of binary executables, and assembly of filesystem images and other artifacts that will be deployed to a target hardware device.

### Cooker?

Due to the fact that Yocto is a 'meta' framework with support for a large varietry of target hardware and runtime system configurations, it is deployed using a number of distict 'layers' which each provide a selectable set of features for end applications, and as such before a running Linux image can be constructed there are multiple source code repositories that must be cloned in the correct locations, and several configuration files that must be built. A further step is required to select the named 'image' output that bitbake will be directed to execute.

To provide a canonical reference for all of the build steps above, cooker uses a manifest file 'cooker-menu.json' which defines the entire set of sources used (both Yocto project official files, and any community trees and end-user specific files) along with Git MD5 commit hashes to ensure that the exact sources used to build your target image is explicitly defined. The cooker-menu file also provides the inputs necessary to generate the Yocto/bitbake 'local.conf' configuration file and names the default target image as well as any additional targets that can be generated by a cooker or bitbake run when specified.