import os, sys, signal, time

if len(sys.argv) != 1:
    print("Le script ne prend pas d'arguments")
    os._exit(1)

processPidList = []
def switch_process(noSignal, frame):
    global processPidList
    if len(processPidList) >= 1:
        runningProcessPid = processPidList.pop(0)
        print("Changement du processus exécuté ", runningProcessPid,end=" ")
        os.kill(runningProcessPid, signal.SIGSTOP)
        processPidList.append(runningProcessPid)
        runningProcessPid = processPidList[0]
        print("à", str(runningProcessPid)+"\n")
        os.kill(runningProcessPid, signal.SIGCONT)
    signal.alarm(15)

def create_process(noSignal, frame):
    global processPidList
    pid = os.fork()
    if pid:
        processPidList.append(pid)
        print("Processus fils", pid,"a été crée et ajouté dans la liste:", processPidList)
        if len(processPidList) > 1:
            os.kill(pid, signal.SIGSTOP)
    else:
        while True:
            time.sleep(3)
            print("Processus", os.getpid(), "est exécuté")

signal.signal(signal.SIGINT, create_process)
signal.signal(signal.SIGALRM, switch_process)
print("PID père:", os.getpid(), "\n")
signal.alarm(15)
while True:
    pass