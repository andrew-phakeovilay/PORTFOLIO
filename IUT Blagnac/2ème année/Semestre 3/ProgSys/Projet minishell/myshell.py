#!/usr/bin/python

import os

while(True):
    try:
        print("Enter your command")
        command = input()
        args = command.split(" ")
        if(len(args) >= 1 and args[0] != ""):
            if(args[-1] == "&" and len(args) >= 2):
                args.pop()
                bg = 1
            else:
                bg = 0
        else:
            continue
        try:
            pid = os.fork()
            if pid:
                if bg == 0:
                    status = os.waitpid(pid, 0)
                    if((status[1]//256) == 0):
                        print("PID fils : ", status[0], "\nCode : ", status[1]//256)
            else:
                os.execvp(args[0], args)
        except OSError as e:
            print(e.strerror)
            os._exit(1)
    except KeyboardInterrupt:
        print("")
        os._exit(1)
