import os

try:
    (r,w) = os.pipe()
    pid = os.fork()
    if pid:
      if os.fork():
        os.close(r)
        os.close(w)
        os.wait()
        os.wait()
        os._exit(0)
      else:
        os.close(w)
        os.dup2(r,0)
        os.read(os.execlp("grep", "grep", "\\.py"), 64)
    else:
        os.close(r)
        os.dup2(w, 1)
        os.execlp("ls", "ls", "-l")
except OSError as e:
    print(e.strerror)
    os._exit(1)