# GSFT: Global Snapshot File Tracker

## What is this?

GSFT is a tool to collect information from virtual machine snapshots and their corresponding files. This version supports VirtualBox .vbox specifictation files.

## Motivation

Hypervisors such as Virtualbox allow users to create snapshots, i.e., to store the state of a virtual machine. Although, they do offer tools to list, create and delete the snapshots, some of them (namely, VirtualBox, VmWare and HyperV) do not offer command-line programs to obtain information of the files related to these snapshots neither to copy them to other servers.

This tool was created to support global snapshots of distributed systems running on virtual machines. We are using it to implement global snapshots in desktop clouds such as CernVM and UnaCloud. However, it can be used in many other scenarios such as planned VM migration and consolidation.

## Usage

To process a .vbox file, you must run the program indicating the file to process. For instance, the following command will process the `example.vbox` file.

    gsft example.vbox
    
To create a .csv file with the results, you can specify the target filename using `-file <filename>`. For instance, the following command will produce an `example.csv` file.

    gsft -file example.csv example.vbox
    
To obtain a list of the files required to migrate a virtual machine, you can use the option `-migrate`. For instance, the following command produces a list of files.

    gsft -migrate example.vbox   
    
The option `-file filename` can be used to store the list of files to migrate in a file.

    gsft -migrate -file list-of-files.txt example.vbox
    
The option `-snapshot <snapshot>`  can be used to show the files required to migrate an specific snapshot of a virtual machine.

    gsft -migrate -snapshot snapshot1 -file list-of-files.txt example.vbox
    
To get help about the options, you can run the program without parameters    

    gsft 