##########################################
# _*_ coding : utf-8 _*_             	  #
#                                        #
# AUTEURS : GROUPE_2_MIAGE-GI_LICENCE_1  #
# DATE :                                 #
#                                        #
# NOM DU PROGRAMME : JUMPER              #
#                                        #
#            VERSION 1.0                 #
##########################################

########### Programme en cours d'amelioration ###########

'''
Realise par : 

    DIOMANDE VAMONKIE JEAN HUBERT
    DIARRASSOUBA SIAKA
    BOKA CHIADON ANITA MARLENE
    OUTTARA CHERE MARIE-EVA
    SOULE AREMOU MALICK AZIZ
'''

'''
Thanks to :

    AMAN EDOUKOU KEVIN ALIAS KEKE (Modification de l'image representant jumper)
    Matthew Roe (Pour le module GIFImage (qui ne fonctionne pas lol...))
'''

##############################################################################################
# PROGRAMME PRINCIPAL
##############################################################################################

import pygame #Importaton de la librairie pygame
from pygame.locals import * #Importation des constantes de pygame
from sys import exit #Importation de la fonction exit du module sys
from jump_pack.mainFunc import *
import os

width = 800 #Variable representant la largeur de la fenetre (screen) de jeu
height = 500 #Variable representant la largeur de la fenetre (screen) de jeu
fps = 30 #Variable de limitation du nombre d'images par secondes  (30 images par seconde)
pts = 0 #Variable contenant les points

if __name__ == '__main__' :
    #Affichages des regles du jeu
    print("\n") 
    print("########### BIENVENUE DANS JUMPER ###########")
    print("\n") 
    print("VOICI LES REGLES DU JEU : ")
    print("\n")
    print("JUMPER est un jeu dans lequel le heros nomme jumper doit eviter un certains nombres d'obstacles (murs) sur son chemin.")
    print("jumper ayant la capacite de voler, il doit donc utiliser cette aptitude pour eviter les murs sur son chemin.")
    print("Pour pouvoir voler, l'utilisateur doit maintenir la touche espace (SPACE).")
    print("Si jumper tombe il perd automatiquement.")
    print("Si jumper vole trop haut, il perd  egalement.")
    print("Si jumper heurte un mur , il perd immediatement.")
    print("MERCI")
    print("\n")
    print("Appuyez sur la touche \"space\" pour commencer !!!")
    pygame.time.delay(6000) #Temps d'attente de 6 secondes avant le debut du jeu
    main(height,width,fps,pts) #Appel de la fonction principale et debut du jeu
    os.system("pause")
