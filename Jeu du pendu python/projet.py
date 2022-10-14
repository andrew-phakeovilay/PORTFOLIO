from tkinter import*
import random

mots = open("liste_francais.txt")
liste_mots = mots.readlines()
mots.close()
liste_mots = [x.replace('\n', '') for x in liste_mots]
mot=random.choice(liste_mots)
mot= list(mot.strip())
essais = 0
mot_trouver = []
for i in range (len(mot)):
    mot_trouver = mot_trouver + ["_"]

def A():
    global essais, mot_trouver, mot
    a = False
    x="A"
    if essais != 11:
        for i in range (len(mot)):
            if x == mot[i]:
                mot_trouver[i] = mot[i]
                
                a = True
                label1.config(text=mot_trouver)
                boutonA.config(foreground="green")
            if a == False and x not in mot:
                essais = essais + 1
                pendu.config(file="pendu_"+str(essais)+".png")
                a = True
                boutonA.config(foreground="red")
                
            if essais == 11:
                image_perdu.place(x = 400, y = 200)
                perdre.place(x=375, y = 380)
                mot_trouver = mot
                label1.config(text=mot_trouver,foreground="red")
            if mot_trouver == mot and essais != 11:
                image_gagner.place(x = 400, y = 200)
                gagner.place(x=375, y =380)
                label1.config(text=mot_trouver,foreground="green")
def B():
    global essais, mot_trouver, mot
    a = False
    x="B"
    if essais != 11:
        for i in range (len(mot)):
            if x == mot[i]:
                mot_trouver[i] = mot[i]
                
                a = True
                boutonB.config(foreground="green")
                label1.config(text=mot_trouver)
            if a == False and x not in mot:
                essais = essais + 1
                pendu.config(file="pendu_"+str(essais)+".png")
                a = True
                boutonB.config(foreground="red")
                
            if essais == 11:
                image_perdu.place(x = 400, y = 200)
                perdre.place(x=375, y = 380)
                mot_trouver = mot
                label1.config(text=mot_trouver,foreground="red")
            if mot_trouver == mot and essais != 11:
                image_gagner.place(x = 400, y = 200)
                gagner.place(x=375, y =380)
                label1.config(text=mot_trouver,foreground="green")
def C():
    global essais, mot_trouver, mot
    a = False
    x="C"
    if essais != 11:
        for i in range (len(mot)):
            if x == mot[i]:
                mot_trouver[i] = mot[i]
                
                a = True
                label1.config(text=mot_trouver)
                boutonC.config(foreground="green")
            if a == False and x not in mot:
                essais = essais + 1
                pendu.config(file="pendu_"+str(essais)+".png")
                a = True
                boutonC.config(foreground="red")
                
            if essais == 11:
                image_perdu.place(x = 400, y = 200)
                perdre.place(x=375, y = 380)
                mot_trouver = mot
                label1.config(text=mot_trouver,foreground="red")
            if mot_trouver == mot and essais != 11:
                image_gagner.place(x = 400, y = 200)
                gagner.place(x=375, y =380)
                label1.config(text=mot_trouver,foreground="green")
def D():
    global essais, mot_trouver, mot
    a = False
    x="D"
    if essais != 11:
        for i in range (len(mot)):
            if x == mot[i]:
                mot_trouver[i] = mot[i]
                
                a = True
                label1.config(text=mot_trouver)
                boutonD.config(foreground="green")
            if a == False and x not in mot:
                essais = essais + 1
                pendu.config(file="pendu_"+str(essais)+".png")
                a = True
                boutonD.config(foreground="red")
                
            if essais == 11:
                image_perdu.place(x = 400, y = 200)
                perdre.place(x=375, y = 380)
                mot_trouver = mot
                label1.config(text=mot_trouver,foreground="red")
            if mot_trouver == mot and essais != 11:
                image_gagner.place(x = 400, y = 200)
                gagner.place(x=375, y =380)
                label1.config(text=mot_trouver,foreground="green")
def E():
    global essais, mot_trouver, mot
    a = False
    x="E"
    if essais != 11:
        for i in range (len(mot)):
            if x == mot[i]:
                mot_trouver[i] = mot[i]
                
                a = True
                label1.config(text=mot_trouver)
                boutonE.config(foreground="green")
            if a == False and x not in mot:
                essais = essais + 1
                pendu.config(file="pendu_"+str(essais)+".png")
                a = True
                boutonE.config(foreground="red")
                
            if essais == 11:
                image_perdu.place(x = 400, y = 200)
                perdre.place(x=375, y = 380)
                mot_trouver = mot
                label1.config(text=mot_trouver,foreground="red")
            if mot_trouver == mot and essais != 11:
                image_gagner.place(x = 400, y = 200)
                gagner.place(x=375, y =380)
                label1.config(text=mot_trouver,foreground="green")
def F():
    global essais, mot_trouver, mot
    a = False
    x="F"
    if essais != 11:
        for i in range (len(mot)):
            if x == mot[i]:
                mot_trouver[i] = mot[i]
                
                a = True
                label1.config(text=mot_trouver)
                boutonF.config(foreground="green")
            if a == False and x not in mot:
                essais = essais + 1
                pendu.config(file="pendu_"+str(essais)+".png")
                a = True
                boutonF.config(foreground="red")
                
            if essais == 11:
                image_perdu.place(x = 400, y = 200)
                perdre.place(x=375, y = 380)
                mot_trouver = mot
                label1.config(text=mot_trouver,foreground="red")
            if mot_trouver == mot and essais != 11:
                image_gagner.place(x = 400, y = 200)
                gagner.place(x=375, y =380)
                label1.config(text=mot_trouver,foreground="green")
def G():
    global essais, mot_trouver, mot
    a = False
    x="G"
    if essais != 11:
        for i in range (len(mot)):
            if x == mot[i]:
                mot_trouver[i] = mot[i]
                
                a = True
                label1.config(text=mot_trouver)
                boutonG.config(foreground="green")
            if a == False and x not in mot:
                essais = essais + 1
                pendu.config(file="pendu_"+str(essais)+".png")
                a = True
                boutonG.config(foreground="red")
                
            if essais == 11:
                image_perdu.place(x = 400, y = 200)
                perdre.place(x=375, y = 380)
                mot_trouver = mot
                label1.config(text=mot_trouver,foreground="red")
            if mot_trouver == mot and essais != 11:
                image_gagner.place(x = 400, y = 200)
                gagner.place(x=375, y =380)
                label1.config(text=mot_trouver,foreground="green")
def H():
    global essais, mot_trouver, mot
    a = False
    x="H"
    if essais != 11:
        for i in range (len(mot)):
            if x == mot[i]:
                mot_trouver[i] = mot[i]
                
                a = True
                label1.config(text=mot_trouver)
                boutonH.config(foreground="green")
            if a == False and x not in mot:
                essais = essais + 1
                pendu.config(file="pendu_"+str(essais)+".png")
                a = True
                boutonH.config(foreground="red")
                
            if essais == 11:
                image_perdu.place(x = 400, y = 200)
                perdre.place(x=375, y = 380)
                mot_trouver = mot
                label1.config(text=mot_trouver,foreground="red")
            if mot_trouver == mot and essais != 11:
                image_gagner.place(x = 400, y = 200)
                gagner.place(x=375, y =380)
                label1.config(text=mot_trouver,foreground="green")
def I():
    global essais, mot_trouver, mot
    a = False
    x="I"
    if essais != 11:
        for i in range (len(mot)):
            if x == mot[i]:
                mot_trouver[i] = mot[i]
                
                a = True
                label1.config(text=mot_trouver)
                boutonI.config(foreground="green")
            if a == False and x not in mot:
                essais = essais + 1
                pendu.config(file="pendu_"+str(essais)+".png")
                a = True
                boutonI.config(foreground="red")
                
            if essais == 11:
                image_perdu.place(x = 400, y = 200)
                perdre.place(x=375, y = 380)
                mot_trouver = mot
                label1.config(text=mot_trouver,foreground="red")
            if mot_trouver == mot and essais != 11:
                image_gagner.place(x = 400, y = 200)
                gagner.place(x=375, y =380)
                label1.config(text=mot_trouver,foreground="green")
def J():
    global essais, mot_trouver, mot
    a = False
    x="J"
    if essais != 11:
        for i in range (len(mot)):
            if x == mot[i]:
                mot_trouver[i] = mot[i]
                
                a = True
                label1.config(text=mot_trouver)
                boutonJ.config(foreground="green")
            if a == False and x not in mot:
                essais = essais + 1
                pendu.config(file="pendu_"+str(essais)+".png")
                a = True
                boutonJ.config(foreground="red")
                
            if essais == 11:
                image_perdu.place(x = 400, y = 200)
                perdre.place(x=375, y = 380)
                mot_trouver = mot
                label1.config(text=mot_trouver,foreground="red")
            if mot_trouver == mot and essais != 11:
                image_gagner.place(x = 400, y = 200)
                gagner.place(x=375, y =380)
                label1.config(text=mot_trouver,foreground="green")
def K():
    global essais, mot_trouver, mot
    a = False
    x="K"
    if essais != 11:
        for i in range (len(mot)):
            if x == mot[i]:
                mot_trouver[i] = mot[i]
                
                a = True
                boutonK.config(foreground="green")
                label1.config(text=mot_trouver)
            if a == False and x not in mot:
                essais = essais + 1
                pendu.config(file="pendu_"+str(essais)+".png")
                a = True
                boutonK.config(foreground="red")
                
            if essais == 11:
                image_perdu.place(x = 400, y = 200)
                perdre.place(x=375, y = 380)
                mot_trouver = mot
                label1.config(text=mot_trouver,foreground="red")
            if mot_trouver == mot and essais != 11:
                image_gagner.place(x = 400, y = 200)
                gagner.place(x=375, y =380)
                label1.config(text=mot_trouver,foreground="green")
def L():
    global essais, mot_trouver, mot
    a = False
    x="L"
    if essais != 11:
        for i in range (len(mot)):
            if x == mot[i]:
                mot_trouver[i] = mot[i]
                
                a = True
                label1.config(text=mot_trouver)
                boutonL.config(foreground="green")
            if a == False and x not in mot:
                essais = essais + 1
                pendu.config(file="pendu_"+str(essais)+".png")
                a = True
                boutonL.config(foreground="red")
                
            if essais == 11:
                image_perdu.place(x = 400, y = 200)
                perdre.place(x=375, y = 380)
                mot_trouver = mot
                label1.config(text=mot_trouver,foreground="red")
            if mot_trouver == mot and essais != 11:
                image_gagner.place(x = 400, y = 200)
                gagner.place(x=375, y =380)
                label1.config(text=mot_trouver,foreground="green")
def M():
    global essais, mot_trouver, mot
    a = False
    x="M"
    if essais != 11:
        for i in range (len(mot)):
            if x == mot[i]:
                mot_trouver[i] = mot[i]
                
                a = True
                label1.config(text=mot_trouver)
                boutonM.config(foreground="green")
            if a == False and x not in mot:
                essais = essais + 1
                pendu.config(file="pendu_"+str(essais)+".png")
                a = True
                boutonM.config(foreground="red")
                
            if essais == 11:
                image_perdu.place(x = 400, y = 200)
                perdre.place(x=375, y = 380)
                mot_trouver = mot
                label1.config(text=mot_trouver,foreground="red")
            if mot_trouver == mot and essais != 11:
                image_gagner.place(x = 400, y = 200)
                gagner.place(x=375, y =380)
                label1.config(text=mot_trouver,foreground="green")
def N():
    global essais, mot_trouver, mot
    a = False
    x="N"
    if essais != 11:
        for i in range (len(mot)):
            if x == mot[i]:
                mot_trouver[i] = mot[i]
                
                a = True
                label1.config(text=mot_trouver)
                boutonN.config(foreground="green")
            if a == False and x not in mot:
                essais = essais + 1
                pendu.config(file="pendu_"+str(essais)+".png")
                a = True
                boutonN.config(foreground="red")
                
            if essais == 11:
                image_perdu.place(x = 400, y = 200)
                perdre.place(x=375, y = 380)
                mot_trouver = mot
                label1.config(text=mot_trouver,foreground="red")
            if mot_trouver == mot and essais != 11:
                image_gagner.place(x = 400, y = 200)
                gagner.place(x=375, y =380)
                label1.config(text=mot_trouver,foreground="green")
def O():
    global essais, mot_trouver, mot
    a = False
    x="O"
    if essais != 11:
        for i in range (len(mot)):
            if x == mot[i]:
                mot_trouver[i] = mot[i]
                
                a = True
                label1.config(text=mot_trouver)
                boutonO.config(foreground="green")
            if a == False and x not in mot:
                essais = essais + 1
                pendu.config(file="pendu_"+str(essais)+".png")
                a = True
                boutonO.config(foreground="red")
                
            if essais == 11:
                image_perdu.place(x = 400, y = 200)
                perdre.place(x=375, y = 380)
                mot_trouver = mot
                label1.config(text=mot_trouver,foreground="red")
            if mot_trouver == mot and essais != 11:
                image_gagner.place(x = 400, y = 200)
                gagner.place(x=375, y =380)
                label1.config(text=mot_trouver,foreground="green")
def P():
    global essais, mot_trouver, mot
    a = False
    x="P"
    if essais != 11:
        for i in range (len(mot)):
            if x == mot[i]:
                mot_trouver[i] = mot[i]
                
                a = True
                label1.config(text=mot_trouver)
                boutonP.config(foreground="green")
            if a == False and x not in mot:
                essais = essais + 1
                pendu.config(file="pendu_"+str(essais)+".png")
                a = True
                boutonP.config(foreground="red")
                
            if essais == 11:
                image_perdu.place(x = 400, y = 200)
                perdre.place(x=375, y = 380)
                mot_trouver = mot
                label1.config(text=mot_trouver,foreground="red")
            if mot_trouver == mot and essais != 11:
                image_gagner.place(x = 400, y = 200)
                gagner.place(x=375, y =380)
                label1.config(text=mot_trouver,foreground="green")
def Q():
    global essais, mot_trouver, mot
    a = False
    x="Q"
    if essais != 11:
        for i in range (len(mot)):
            if x == mot[i]:
                mot_trouver[i] = mot[i]
                
                a = True
                label1.config(text=mot_trouver)
                boutonQ.config(foreground="green")
            if a == False and x not in mot:
                essais = essais + 1
                pendu.config(file="pendu_"+str(essais)+".png")
                a = True
                boutonQ.config(foreground="red")
                
            if essais == 11:
                image_perdu.place(x = 400, y = 200)
                perdre.place(x=375, y = 380)
                mot_trouver = mot
                label1.config(text=mot_trouver,foreground="red")
            if mot_trouver == mot and essais != 11:
                image_gagner.place(x = 400, y = 200)
                gagner.place(x=375, y =380)
                label1.config(text=mot_trouver,foreground="green")
def R():
    global essais, mot_trouver, mot
    a = False
    x="R"
    if essais != 11:
        for i in range (len(mot)):
            if x == mot[i]:
                mot_trouver[i] = mot[i]
                
                a = True
                label1.config(text=mot_trouver)
                boutonR.config(foreground="green")
            if a == False and x not in mot:
                essais = essais + 1
                pendu.config(file="pendu_"+str(essais)+".png")
                a = True
                boutonR.config(foreground="red")
                
            if essais == 11:
                image_perdu.place(x = 400, y = 200)
                perdre.place(x=375, y = 380)
                mot_trouver = mot
                label1.config(text=mot_trouver,foreground="red")
            if mot_trouver == mot and essais != 11:
                image_gagner.place(x = 400, y = 200)
                gagner.place(x=375, y =380)
                label1.config(text=mot_trouver,foreground="green")
def S():
    global essais, mot_trouver, mot
    a = False
    x="S"
    if essais != 11:
        for i in range (len(mot)):
            if x == mot[i]:
                mot_trouver[i] = mot[i]
                
                a = True
                label1.config(text=mot_trouver)
                boutonS.config(foreground="green")
            if a == False and x not in mot:
                essais = essais + 1
                pendu.config(file="pendu_"+str(essais)+".png")
                a = True
                boutonS.config(foreground="red")
                
            if essais == 11:
                image_perdu.place(x = 400, y = 200)
                perdre.place(x=375, y = 380)
                mot_trouver = mot
                label1.config(text=mot_trouver,foreground="red")
            if mot_trouver == mot and essais != 11:
                image_gagner.place(x = 400, y = 200)
                gagner.place(x=375, y =380)
                label1.config(text=mot_trouver,foreground="green")
def T():
    global essais, mot_trouver, mot
    a = False
    x="T"
    if essais != 11:
        for i in range (len(mot)):
            if x == mot[i]:
                mot_trouver[i] = mot[i]
                
                a = True
                label1.config(text=mot_trouver)
                boutonT.config(foreground="green")
            if a == False and x not in mot:
                essais = essais + 1
                pendu.config(file="pendu_"+str(essais)+".png")
                a = True
                boutonT.config(foreground="red")
                
            if essais == 11:
                image_perdu.place(x = 400, y = 200)
                perdre.place(x=375, y = 380)
                mot_trouver = mot
                label1.config(text=mot_trouver,foreground="red")
            if mot_trouver == mot and essais != 11:
                image_gagner.place(x = 400, y = 200)
                gagner.place(x=375, y =380)
                label1.config(text=mot_trouver,foreground="green")
def U():
    global essais, mot_trouver, mot
    a = False
    x="U"
    if essais != 11:
        for i in range (len(mot)):
            if x == mot[i]:
                mot_trouver[i] = mot[i]
                
                a = True
                label1.config(text=mot_trouver)
                boutonU.config(foreground="green")
            if a == False and x not in mot:
                essais = essais + 1
                pendu.config(file="pendu_"+str(essais)+".png")
                a = True
                boutonU.config(foreground="red")
                
            if essais == 11:
                image_perdu.place(x = 400, y = 200)
                perdre.place(x=375, y = 380)
                mot_trouver = mot
                label1.config(text=mot_trouver,foreground="red")
            if mot_trouver == mot and essais != 11:
                image_gagner.place(x = 400, y = 200)
                gagner.place(x=375, y =380)
                label1.config(text=mot_trouver,foreground="green")
def V():
    global essais, mot_trouver, mot
    a = False
    x="V"
    if essais != 11:
        for i in range (len(mot)):
            if x == mot[i]:
                mot_trouver[i] = mot[i]
                
                a = True
                label1.config(text=mot_trouver)
                boutonV.config(foreground="green")
            if a == False and x not in mot:
                essais = essais + 1
                pendu.config(file="pendu_"+str(essais)+".png")
                a = True
                boutonV.config(foreground="red")
                
            if essais == 11:
                image_perdu.place(x = 400, y = 200)
                perdre.place(x=375, y = 380)
                mot_trouver = mot
                label1.config(text=mot_trouver,foreground="red")
            if mot_trouver == mot and essais != 11:
                image_gagner.place(x = 400, y = 200)
                gagner.place(x=375, y =380)
                label1.config(text=mot_trouver,foreground="green")
def W():
    global essais, mot_trouver, mot
    a = False
    x="W"
    if essais != 11:
        for i in range (len(mot)):
            if x == mot[i]:
                mot_trouver[i] = mot[i]
                
                a = True
                label1.config(text=mot_trouver)
                boutonW.config(foreground="green")
            if a == False and x not in mot:
                essais = essais + 1
                pendu.config(file="pendu_"+str(essais)+".png")
                a = True
                boutonW.config(foreground="red")
                
            if essais == 11:
                image_perdu.place(x = 400, y = 200)
                perdre.place(x=375, y = 380)
                mot_trouver = mot
                label1.config(text=mot_trouver,foreground="red")
            if mot_trouver == mot and essais != 11:
                image_gagner.place(x = 400, y = 200)
                gagner.place(x=375, y =380)
                label1.config(text=mot_trouver,foreground="green")
def X():
    global essais, mot_trouver, mot
    a = False
    x="X"
    if essais != 11:
        for i in range (len(mot)):
            if x == mot[i]:
                mot_trouver[i] = mot[i]
                
                a = True
                label1.config(text=mot_trouver)
                boutonX.config(foreground="green")
            if a == False and x not in mot:
                essais = essais + 1
                pendu.config(file="pendu_"+str(essais)+".png")
                a = True
                boutonX.config(foreground="red")
                
            if essais == 11:
                image_perdu.place(x = 400, y = 200)
                perdre.place(x=375, y = 380)
                mot_trouver = mot
                label1.config(text=mot_trouver,foreground="red")
            if mot_trouver == mot and essais != 11:
                image_gagner.place(x = 400, y = 200)
                gagner.place(x=375, y =380)
                label1.config(text=mot_trouver,foreground="green")
def Y():
    global essais, mot_trouver, mot
    a = False
    x="Y"
    if essais != 11:
        for i in range (len(mot)):
            if x == mot[i]:
                mot_trouver[i] = mot[i]
                
                a = True
                label1.config(text=mot_trouver)
                boutonY.config(foreground="green")
            if a == False and x not in mot:
                essais = essais + 1
                pendu.config(file="pendu_"+str(essais)+".png")
                a = True
                boutonY.config(foreground="red")
                
            if essais == 11:
                image_perdu.place(x = 400, y = 200)
                perdre.place(x=375, y = 380)
                mot_trouver = mot
                label1.config(text=mot_trouver,foreground="red")
            if mot_trouver == mot and essais != 11:
                image_gagner.place(x = 400, y = 200)
                gagner.place(x=375, y =380)
                label1.config(text=mot_trouver,foreground="green")
def Z():
    global essais, mot_trouver, mot
    a = False
    x="Z"
    if essais != 11:
        for i in range (len(mot)):
            if x == mot[i]:
                mot_trouver[i] = mot[i]
                
                a = True
                label1.config(text=mot_trouver)
                boutonZ.config(foreground="green")
            if a == False and x not in mot:
                essais = essais + 1
                pendu.config(file="pendu_"+str(essais)+".png")
                boutonZ.config(foreground="red")
                a = True
                
            if essais == 11:
                image_perdu.place(x = 400, y = 200)
                perdre.place(x=375, y = 380)
                mot_trouver = mot
                label1.config(text=mot_trouver,foreground="red")
            if mot_trouver == mot and essais != 11:
                image_gagner.place(x = 400, y = 200)
                gagner.place(x=375, y =380)
                label1.config(text=mot_trouver,foreground="green")
def restart():
    global essais, mot_trouver, mot
    mot=random.choice(liste_mots)
    mot= list(mot.strip())
    essais = 0
    mot_trouver = []
    for i in range (len(mot)):
        mot_trouver = mot_trouver + ["_"]
    pendu.config(file="pendu_0.png")
    label1.config(text=mot_trouver, foreground="black")
    image_gagner.place_forget()
    gagner.place_forget()
    image_perdu.place_forget()
    perdre.place_forget()
    boutonA.config(foreground="black")
    boutonB.config(foreground="black")
    boutonC.config(foreground="black")
    boutonD.config(foreground="black")
    boutonE.config(foreground="black")
    boutonF.config(foreground="black")
    boutonG.config(foreground="black")
    boutonH.config(foreground="black")
    boutonI.config(foreground="black")
    boutonJ.config(foreground="black")
    boutonK.config(foreground="black")
    boutonL.config(foreground="black")
    boutonM.config(foreground="black")
    boutonN.config(foreground="black")
    boutonO.config(foreground="black")
    boutonP.config(foreground="black")
    boutonQ.config(foreground="black")
    boutonR.config(foreground="black")
    boutonS.config(foreground="black")
    boutonT.config(foreground="black")
    boutonU.config(foreground="black")
    boutonV.config(foreground="black")
    boutonW.config(foreground="black")
    boutonX.config(foreground="black")
    boutonY.config(foreground="black")
    boutonZ.config(foreground="black")

def quitter():
    fenetre.destroy()

fenetre = Tk()
fenetre.title("Pendu")

fenetre.geometry("1000x500")
fenetre.resizable(False, False)

label = Label(fenetre, text="Cherche le mot :", font=("Courier", 50))
label1 = Label(fenetre, text=mot_trouver, font=("Courier", 50))

pendu = PhotoImage(file="pendu_0.png")
image_pendu = Label(fenetre, image=pendu, border=0)
image_pendu.place(x = 700, y = 190)

gagner = Label(fenetre, text="Vous avez gagné !", border=0, font=("Courier", 20))

perdu = PhotoImage(file="perdu.png")
image_perdu = Label(fenetre, image=perdu, border=0)
perdre = Label(fenetre, text="Vous avez perdu..", border=0, font=("Courier", 20))

gagnerimg = PhotoImage(file="gagner.png")
image_gagner = Label(fenetre, image=gagnerimg, border=0)


Recommencer=Button(fenetre, text="Recommencer", command = restart, font=("Courier", 20))
Recommencer.place(x=200, y=420)

Quitter=Button(fenetre, text="Quitter", command = quitter, font=("Courier", 20))
Quitter.place(x=40, y=420)

boutonA=Button(fenetre, text="A", command = A, font=("Courier", 20))
boutonB=Button(fenetre, text="B", command = B, font=("Courier", 20))
boutonC=Button(fenetre, text="C", command = C, font=("Courier", 20))
boutonD=Button(fenetre, text="D", command = D, font=("Courier", 20))
boutonE=Button(fenetre, text="E", command = E, font=("Courier", 20))
boutonF=Button(fenetre, text="F", command = F, font=("Courier", 20))
boutonG=Button(fenetre, text="G", command = G, font=("Courier", 20))
boutonH=Button(fenetre, text="H", command = H, font=("Courier", 20))
boutonI=Button(fenetre, text="I", command = I, font=("Courier", 20))
boutonJ=Button(fenetre, text="J", command = J, font=("Courier", 20))
boutonK=Button(fenetre, text="K", command = K, font=("Courier", 20))
boutonL=Button(fenetre, text="L", command = L, font=("Courier", 20))
boutonM=Button(fenetre, text="M", command = M, font=("Courier", 20))
boutonN=Button(fenetre, text="N", command = N, font=("Courier", 20))
boutonO=Button(fenetre, text="O", command = O, font=("Courier", 20))
boutonP=Button(fenetre, text="P", command = P, font=("Courier", 20))
boutonQ=Button(fenetre, text="Q", command = Q, font=("Courier", 20))
boutonR=Button(fenetre, text="R", command = R, font=("Courier", 20))
boutonS=Button(fenetre, text="S", command = S, font=("Courier", 20))
boutonT=Button(fenetre, text="T", command = T, font=("Courier", 20))
boutonU=Button(fenetre, text="U", command = U, font=("Courier", 20))
boutonV=Button(fenetre, text="V", command = V, font=("Courier", 20))
boutonW=Button(fenetre, text="W", command = W, font=("Courier", 20))
boutonX=Button(fenetre, text="X", command = X, font=("Courier", 20))
boutonY=Button(fenetre, text="Y", command = Y, font=("Courier", 20))
boutonZ=Button(fenetre, text="Z", command = Z, font=("Courier", 20))
boutonA.place(x=40, y=200)
boutonB.place(x=80, y=200)
boutonC.place(x=120, y=200)
boutonD.place(x=160, y=200)
boutonE.place(x=200, y=200)
boutonF.place(x=240, y=200)
boutonG.place(x=280, y=200)
boutonH.place(x=40, y=252)
boutonI.place(x=80, y=252)
boutonJ.place(x=120, y=252)
boutonK.place(x=160, y=252)
boutonL.place(x=200, y=252)
boutonM.place(x=240, y=252)
boutonN.place(x=40, y=304)
boutonO.place(x=80, y=304)
boutonP.place(x=120, y=304)
boutonQ.place(x=160, y=304)
boutonR.place(x=200, y=304)
boutonS.place(x=240, y=304)
boutonT.place(x=280, y=304)
boutonU.place(x=40, y=356)
boutonV.place(x=80, y=356)
boutonW.place(x=120, y=356)
boutonX.place(x=160, y=356)
boutonY.place(x=200, y=356)
boutonZ.place(x=240, y=356)

label.pack()
label1.pack()

fenetre.mainloop()