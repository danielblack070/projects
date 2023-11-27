import pygame as g
import pygame.gfxdraw



def palyarajz(palya,ablak):
    for o in range(0,palya.maxo):
        for s in range(0,palya.maxs):
            if not palya.foglalt[o][s]:
                g.gfxdraw.box(ablak,g.Rect(o *palya.nm,s * palya.nm, palya.nm, palya.nm),g.Color(55,55,55))
                g.gfxdraw.rectangle(ablak,g.Rect(o*palya.nm,s*palya.nm, palya.nm, palya.nm),g.Color(0,0,0))
            else:
                g.gfxdraw.box(ablak,g.Rect(o *palya.nm,s * palya.nm, palya.nm, palya.nm),palya.color[o][s])
                g.gfxdraw.rectangle(ablak,g.Rect(o*palya.nm,s*palya.nm, palya.nm, palya.nm),g.Color(0,0,0))

def alakrajz(al,ablak):
    for o in range(0,4):
        for s in range(0,4):
            if al.alak[o][s]:
                g.gfxdraw.box(ablak,g.Rect(al.x + o*al.nm, al.y + s*al.nm, al.nm, al.nm),al.color)
                g.gfxdraw.rectangle(ablak,g.Rect(al.x + o*al.nm, al.y + s*al.nm, al.nm, al.nm),g.Color(0,0,0))
