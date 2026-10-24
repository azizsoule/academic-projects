import pygame #Importaton de la librairie pygame
from pygame.locals import * #Importation des constantes de pygame

#Fonction de chargement des images,des polices et de la musique dans le programme
def create(height,width) : 
    #Globalisation des differentes variables pour pouvoir les utiliser dans d'autres fonctions
    global foret
    global foret_rect
    
    global murp
    global murp_rect
    global murp1
    global murp1_rect
    
    global murg
    global murg_rect
    global murg1
    global murg1_rect
    
    global ptfont
    global jfont
    
    global jumper
    global jum_rect
    
    global game_over
    global over_rect
    
    #Chargement,convertion et positionnement des images 
    foret = pygame.image.load("./images/back.jpg")
    foret = foret.convert()
    
    foret_rect = foret.get_rect()
    foret_rect.bottom = height
    
    jumper = pygame.image.load("./images/jumper.png")
    jumper = jumper.convert_alpha()
    jum_rect = jumper.get_rect()
    jum_rect.bottom = height/2
    
    murp = pygame.image.load("./images/murp.PNG")
    murp = murp.convert_alpha()
    murp_rect = murp.get_rect()
    murp_rect.right = width
    murp_rect.bottom = height
    
    murp1 = pygame.image.load("./images/murp.PNG")
    murp1 = murp.convert_alpha()
    murp1_rect = murp.get_rect()
    murp1_rect.right = width
    
    murg = pygame.image.load("./images/murg.PNG")
    murg = murg.convert_alpha()
    murg_rect = murg.get_rect()
    murg_rect.right = width/2
    murg_rect.bottom = height
    
    murg1 = pygame.image.load("./images/murg.PNG")
    murg1 = murg.convert_alpha()
    murg1_rect = murg.get_rect()
    murg1_rect.right = width/2
    
    game_over = pygame.image.load("./images/over.jpg")
    game_over = game_over.convert()
    over_rect = game_over.get_rect()
    over_rect.center = (width/2,height/2)
    
    #Chargement de la police utilisee
    ptfont = pygame.font.Font("./fonts/kathen.otf",20)
    
    #Chargement , volume et lecture infinie (jusqu'a la fin du jeu) de la musique
    pygame.mixer.music.load("./music/off_limit.wav")
    pygame.mixer.music.set_volume (1)
    #pygame.mixer.music.play(-1)

#affichage des images et des textes
def blitter(ecran,pts,width) : 
    #global pts #globalisation de la variable pts car non passee en parametre a la foction
    #Affichage de l'arriere plan
    ecran.blit(foret,foret_rect) 
    #Affichage des murs
    ecran.blit(murp,murp_rect) 
    ecran.blit(murg,murg_rect)
    ecran.blit(murp1,murp1_rect)
    ecran.blit(murg1,murg1_rect)
    ecran.blit(jumper,jum_rect)
    #Affichage du score en noir((0,0,0)) en haut a gauche (10,10)
    point = ptfont.render("POINTS : " + str(pts),1,(0,0,0))
    ecran.blit(point,(10,10))
    #Affichage de "JUMPER" en haut a droite (width - 85,10)
    jum = ptfont.render("JUMPER",1,(0,0,0))
    ecran.blit(jum,(width - 85,10))
    #Raffraichissement de l'ecran apres ajout de nouveaux elements (images,textes)
    pygame.display.flip()

#Fonction de mise en mouvement des differentes images affichees
def action_murp(tps,width) : 
    murp_rect.x -= 5 #Deplacement de 5 pixels vers la gauche
    if murp_rect.right < 0 : #Condition de recadrage de l'image
        murp_rect.right = width

def action_murg(tps,width) : 
    murg_rect.x -= 5 #Deplacement de 5 pixels vers la gauche
    if murg_rect.right < 0 : #Condition de recadrage de l'image
        murg_rect.right = width
        
def action_murp1(tps,width) : 
    murp1_rect.x -= 2.5 #Deplacement de 2,5 pixels vers la gauche
    if murp1_rect.right < 0:
        murp1_rect.right = width
        
def action_murg1(tps,width) : 
    murg1_rect.x -= 2.5 #Deplacement de 2,5 pixels vers la gauche
    if murg1_rect.right < 0:
        murg1_rect.right = width
        
def action_foret(tps,width) : 
    foret_rect.x -= 5 #Deplacement de 5 pixels vers la gauche
    if foret_rect.x == -1600 + width : #Condition de recadrage de l'image
        foret_rect.x = 0

#Fonction contenant les fonctions de deplacement des differentes images(jumper,murp,....)
def all_action(tps,width) :
    action_foret(tps,width) 
    action_murp(tps,width)
    action_murg(tps,width)
    action_murp1(tps,width)
    action_murg1(tps,width)

#Fonction de detection des collisions entre jumper et les murs
def collision(rect1,rect2,screen,pts) : 
    if rect1.colliderect(rect2) :  #Condition de detection de collision
        start = False
        screen.blit(game_over,over_rect)
        pygame.display.flip()
        pygame.time.delay(5000)
        print("\n")
        print("SCORE : " + str(pts))
        print("\n")
        print("Si vous voulez rejouez, executez le fichier JUMPER.py")
        print("########### FIN DU JEU ###########")
    return 1

def collisions(jum_rect,murp1_rect,screen,pts):
    if collision(jum_rect,murp1_rect,screen,pts) == 1  :
        return 1
    else :
        return 0



#Fonction principale faisant appel a toutes le autres fonctions

def main(height,width,fps,pts) :
    
    #Initialisation de la bibliotheque pygame
    pygame.init()
    start = True #Initialisation de la variable d'entree dans la boucle principale (while) du jeu #variale booleenne initialisee a True
    screen = pygame.display.set_mode((width,height)) #Creation de la fenetre de jeu (HAUTEUR = 500 pixels et LARGEUR = 800 pixels)
    #Definition du nom de la fenetre(JUMPER)
    pygame.display.set_caption("JUMPER")
    #Detection de maintien d'une touche
    pygame.key.set_repeat(400,30)
    #Creation de l'objet "clock" permettant de regler le nombres d'images vues par seconde (30 images par seconde dans notre cas)
    clock = pygame.time.Clock()
    
    create(height,width) #Appel de la fonction de chargement des images,des polices et de la musique dans le programme

    s = 1
    
    while start == True : #Boucle de jeu
        
        #Limitation du nombre d'images a 30 par seconde
        Fps = clock.tick(fps)
        FPS = Fps/1000.0
        while s == 1 :
                for evt in pygame.event.get() :
                        if evt.type == KEYDOWN :
                                if evt.key == K_SPACE :
                                        s = 0
                                        pygame.mixer.music.play(-1)

        jum_rect.y += 5 #Chute de jumper
        
        for evt in pygame.event.get() :  #Boucle de detection des evenements (appui sur une touche du clavier, deplacement de la souris,...)
            if evt.type == KEYDOWN : #Detection d'appui sur une touche du clavier 
                if evt.key == K_ESCAPE : #Detection de la touche "ECHAP" pour quitter le jeu
                    start = False #Initialisation de start a False pour quitter la boucle while
                if evt.key == K_SPACE :  #Deection de la touche "SPACE" 
                    jum_rect.y -= 10 #Envol de jumper 
            if evt.type == QUIT  : #Detection de clic sur la croix rouge en haut a droite pour fermer la fenetre
                start = False
        
        blitter(screen,pts,width) #Affichage des differntes images
        all_action(FPS,width) #Appel de la fonction de mise en mouvement des images avec comme parametre le temps en seconde
        pts = int(pygame.time.get_ticks()/1000) + 1 #Icrementation du score en fonction du temps
        
        #Detection des collisions possibles
        if collisions (jum_rect,murp1_rect,screen,pts) == 1 :
            pass
        else :
            pass
        
        #condition de detection de sortie de la fenetre (screen)
        if jum_rect.y <= 0 or jum_rect.bottom >= height : 
            start = False
            screen.blit(game_over,over_rect)
            pygame.mixer.music.stop() #Arret de la musique a la fin du jeu
            pygame.display.flip()
            pygame.time.delay(5000)
            print("\n")
            print("SCORE : " + str(pts))
            print("\n")
            print("########### FIN DU JEU ###########")
            break
    
    pygame.quit() #Fermeture de la fenetre a la fin du jeu
    exit() #Sortie du systeme pour liberer les ressources
    return 0
