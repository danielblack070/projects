import pygame as g
import os
import pygame.gfxdraw
import random as r
import rajz as ra

class elem:
    def __init__(self,color,nm):
        self.alak = [[False] * 4 for i in range(4) ]
        self.x = 0
        self.y = 0
        self.nm = nm
        self.color = color
        for o in range(0,4):
            for s in range(0,4):
                self.alak[o][s] = False
        hs = 0
        ho = 0
        self.alak[ho][hs] = True
        fogl = 1
        while fogl < 4:
            rnd = r.randint(0,1)
            if rnd == 0:
                valt = r.randint(-1,1)
                if hs + valt >= 0 and hs + valt < 4:
                    hs += valt
            else:
                valt = r.randint(-1,1)
                if ho + valt >= 0 and ho + valt < 4:
                    ho += valt
            if not self.alak[ho][hs]:
                self.alak[ho][hs] = True
                fogl += 1

    def forgat(self,bal):#tömegközéppont szerinti forgatás
        forg = [[False] * 4 for i in range(4)]
        rtkps = 0.0 # régi tömegközéppont sor
        rtkpo = 0.0 # régi tömegközéppont oszlop
        for o in range(0,4):
            for s in range(0,4):
                if self.alak[o][s]:
                    rtkps += s / 4
                    rtkpo += o / 4
        for o in range(0,4):
            for s in range(0,4):
                if bal:
                    forg[3-s][o] = self.alak[o][s]
                else:
                    forg[s][3-o] = self.alak[o][s]
        #új tömegközéppont szerinti eltolás
        utkps = 0.0
        utkpo = 0.0
        for o in range(0,4):
            for s in range(0,4):
                if forg[o][s]:
                    utkps += s / 4
                    utkpo += o / 4
        se = round(rtkps - utkps)#hány sorral toljuk el
        oe = round(rtkpo - utkpo)#hány oszloppal toljuk el

        self.x += self.nm*oe
        self.y += self.nm*se
        for o in range(0,4):
            for s in range(0,4):
                self.alak[o][s] = forg[o][s]

    def rogzit(self,palya):
        for o in range(0,4):
            for s in range(0,4):
                if self.alak[o][s]:
                    palya.foglalt[self.x // self.nm + o][self.y // self.nm + s] = True
                    palya.color[self.x // self.nm + o][self.y // self.nm + s] = self.color

    def atfedes(self,palya):
        for o in range(0,4):
            for s in range(0,4):
                if self.alak[o][s]:
                    if self.x // self.nm + o < 0 or self.x // self.nm + o + 1 > palya.maxo:
                        return True
                    elif self.y // self.nm + s < 0 or self.y // self.nm + s + 1 > palya.maxs:
                        return True
                    elif palya.foglalt[o + self.x // self.nm][self.y // self.nm + s]:
                        return True

class lerakott:
    def __init__(self,maxs,maxo,nm):
        self.maxs = maxs
        self.maxo = maxo
        self.nm = nm
        self.foglalt = [[False] * maxs for i in range(maxo)]
        self.color = [[None] * maxs for i in range(maxo)]

    def sortorles(self):
        torolt = 0
        for s in range(0,self.maxs):
            torles = True
            for o in range(0,self.maxo):
                if not self.foglalt[o][s]:
                    torles = False
            if torles:
                torolt += 1
                for s1 in range(s,0,-1):
                    for o1 in range(0,self.maxo):
                        self.foglalt[o1][s1] = self.foglalt[o1][s1-1]
                        self.color[o1][s1] = self.color[o1][s1-1]
                    self.foglalt[o1][0] = False
                    self.color[o1][0] = None
        return torolt

class eredmeny:
    def __init__(self,nev,pont):
        self.nev = nev
        self.pont = pont

def beo(f):
    szov = " "
    while szov[0] not in "0123456789":
        szov = f.readline()
    for i in range(0,len(szov)):
        if szov[i] not in "0123456789":
            szov = szov[:i]
            break
    return int(szov)
    
def szin():
    ossz = 0
    while ossz == 0:
        piros = r.randint(0,1)
        zold = r.randint(0,1)
        kek = r.randint(0,1)
        ossz = piros + zold + kek
    return g.Color(255 * piros, 255 * zold, 255 * kek)

def toplista(pontok):
    eredmenyek = []
    f = open("leaderboard.txt","at")
    sajat = input("Add meg, milyen névvel szeretnél a toplistán szerepelni, vagy nyomj entert, ha nem szeretnél felkerülni\n")
    if sajat != "":
        f.write("\n{}\t{}".format(sajat,pontok))
    f.close()
    f = open("leaderboard.txt","rt")
    szov = " "
    while szov != "":
        szov = f.readline() 
        if szov != "":
            for i in range(0,len(szov)):
                if szov[i] == "\t":
                    eredmenyek.append(eredmeny(szov[:i],int(szov[i:].strip())))
    f.close()
    for i in range(0,len(eredmenyek)-1):
        for j in range(i+1,len(eredmenyek)):
            if eredmenyek[j].pont > eredmenyek[i].pont:
                temp1 = eredmenyek[i].pont
                temp2 = eredmenyek[i].nev
                eredmenyek[i].pont = eredmenyek[j].pont
                eredmenyek[i].nev = eredmenyek[j].nev
                eredmenyek[j].pont = temp1
                eredmenyek[j].nev = temp2
    print("toplista:")
    for i in range(0,len(eredmenyek)):
        print("{}.".format(i+1),eredmenyek[i].nev,eredmenyek[i].pont,sep="\t")
            

def main():    

    input("Nyomj entert a kezdéshez!")
    g.init()
    try:
        f = open("config.txt","rt")
        vsz = beo(f)
        fg = beo(f)
        maxs = beo(f)
        maxo = beo(f)
        ido = beo(f)
        idok = beo(f)
        f.close()
    except Exception:
        print("Hibás konfigurációs fájl")
        input()
        exit()
        
    nm = int(vsz // maxo // (4/3))
    if fg // maxs < nm:
        nm = int(fg // maxs)
    pontok = 0
    feher = g.Color(255,255,255)
    a = elem(szin(),nm)
    ak = elem(szin(),nm)
    palya = lerakott(maxs,maxo,nm)
    os.environ['SDL_VIDEO_WINDOW_POS'] = "30,30"
    ablak = g.display.set_mode((vsz,fg),0,0)
    g.display.set_caption("Tetris by Fekete Dániel")
    font = g.font.SysFont("Arial",nm)
    fontvege = g.font.SysFont("Arial", nm * 2)
    szoveg_kovelem = font.render("Következő elem:",True,feher)
    szoveg_pont = font.render("Pont: {}".format(pontok),True,feher)
    szoveg_vege = fontvege.render("Game Over",True,g.Color(255,0,0))
    szoveg_vege2 = font.render("Nyomj meg egy gombot a továbblépéshez",True,feher)
    ablak.blit(szoveg_pont,(nm * (maxo + 0.5),nm * 0.5))
    ablak.blit(szoveg_kovelem,(nm * (maxo + 0.5),nm*2))
    ra.palyarajz(palya,ablak)
    a.x = nm * (maxo // 2 - 1)
    ak.x = nm * (maxo + 1.5)
    ak.y = nm * 4
    ra.alakrajz(a,ablak)
    ra.alakrajz(ak,ablak)
    g.display.update()
    ora = g.time.Clock()
    eltelt = 0

    vege = False
    kilepes = False
    while not kilepes:
        ora.tick()
        eltelt += ora.get_rawtime()
        if eltelt >= ido:
            eltelt = 0
            a.y += nm
            if not a.atfedes(palya):
                ra.palyarajz(palya,ablak)
                ra.alakrajz(a,ablak)
                g.display.update()
            else:
                a.y -= nm
                a.rogzit(palya)
                t = palya.sortorles()
                pontok += t
                ido -= idok * t
                ablak.fill(g.Color(0,0,0))
                ra.palyarajz(palya,ablak)
                a = ak
                a.x = nm * (maxo // 2 - 1)
                a.y = 0
                ak = elem(szin(),nm)
                ak.x = nm * (maxo + 1.5)
                ak.y = nm * 4
                ra.alakrajz(a,ablak)
                ra.alakrajz(ak,ablak)
                szoveg_pont = font.render("Pont: {}".format(pontok),True,feher)
                ablak.blit(szoveg_pont,(nm * (maxo + 0.5),nm * 0.5))
                ablak.blit(szoveg_kovelem,(nm * (maxo + 0.5),nm*2))
                if not a.atfedes(palya):
                    g.display.update()
                else:
                    ablak.fill(g.Color(0,0,0))
                    ablak.blit(szoveg_vege,(vsz // 3 ,fg // 3))
                    ablak.blit(szoveg_pont,(vsz // 2.2 ,fg // 2.2))
                    ablak.blit(szoveg_vege2,(vsz // 4 ,fg // 1.8))
                    g.display.update()
                    kilepes = True
                    vege = True
                    g.time.delay(500)
                    event = g.event.wait()
                    while event.type != g.KEYDOWN:
                        event = g.event.wait()
                    
        for event in g.event.get():
            if event.type == g.QUIT:
                kilepes = True
            elif event.type == g.KEYDOWN:
                if event.key == g.K_DOWN:
                    eltelt = ido
                elif event.key == g.K_UP:
                        a.forgat(1)
                        if not a.atfedes(palya):
                            ra.palyarajz(palya,ablak)
                            ra.alakrajz(a,ablak)
                            g.display.update()
                        else:
                            a.forgat(0)
                elif event.key == g.K_LEFT:
                    a.x -= nm
                    if not a.atfedes(palya):
                        ra.palyarajz(palya,ablak)
                        ra.alakrajz(a,ablak)
                        g.display.update()
                    else:
                        a.x += nm
                elif event.key == g.K_RIGHT:
                    a.x += nm
                    if not a.atfedes(palya):
                        ra.palyarajz(palya,ablak)
                        ra.alakrajz(a,ablak)
                        g.display.update()
                    else:
                        a.x -= nm
        
    g.quit()
    if vege:
        toplista(pontok)
        input("Nyomj entert a kilépéshez")

main()
