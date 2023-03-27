import os, sys, signal

if len(sys.argv)!=2:
    if(len(sys.argv)>2):
        print("Nombre d'arguments invalide\n")
        os._exit(1)
    print("Nom du fichier ne peut pas être vide\n")
    os._exit(1)


def handler(numero, frame):
    os._exit(0)

try:
    signal.signal(signal.SIGINT, handler)
    fd = os.open(sys.argv[1], os.O_WRONLY | os.O_CREAT | os.O_TRUNC, 0o644)
    entree = os.read(0,8)
    while len(entree)>=0:
        os.write(fd, entree)
        entree = os.read(0, 8)
    
except OSError as e:
    print(e.strerror)
    os._exit(1)