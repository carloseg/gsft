# GSFT: Global Snapshot File Tracker

## What is this?

GSFT is a tool to collect information from virtual machine snapshots and their corresponding files.

Hypervisors such as Virtualbox allow users to create snapshots, i.e., to store the state of a virtual machine. Although, they do offer tools to list, create and delete the snapshots, some of them do not offer options to obtain information of the files related to these snapshots neither to copy them to other servers.

This tool, supporting by now VirtualBox, was created to support global snapshots of distributed systems running on virtual machines. We are using this library to implement global snapshots in desktop clouds such as CernVM and UnaCloud. However, it can be used in many other scenarios.

## Usage

To process a .vbox file, you must run the program indicating the file to process. For instance, the following command will process the `example.vbox` file.

    gsft example.vbox
    
To create a .csv file with the results, you can specify the target filename using `-csv filename`. For instance, the following command will produce an `example.csv` file.

    gsft -csv example.csv example.vbox
    
To get help about the options, you can run the program without parameters    

    gsft 