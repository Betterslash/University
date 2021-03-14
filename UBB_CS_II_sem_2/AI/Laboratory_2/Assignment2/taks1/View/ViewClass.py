from copy import deepcopy
from datetime import datetime

import pygame

from taks1.Controller.ControllerClass import Controller, displayWithPath
from taks1.Model.EnviromentVariables import *


class View:
    def __init__(self):
        self.controller = Controller()

    def main(self):
        # we create the map

        self.controller.exploration_map = self.controller.createObjectsMap()
        # initialize the pygame module
        pygame.init()
        # load and set the logo
        logo = pygame.image.load("logo32x32.png")
        pygame.display.set_icon(logo)
        pygame.display.set_caption("Path in simple environment")

        # we position the drone somewhere in the area

        # create drona

        # create a surface on screen that has the size of 400 x 480
        screen = pygame.display.set_mode((400, 400))
        screen.fill(WHITE)

        # define a variable to control the main loop
        running = True
        # main loop
        before_time = datetime.now()
        moves = deepcopy(self.controller.searchGreedy(2, 1).copy())
        # moves = deepcopy(self.controller.searchAStar(2, 1).copy())
        path = deepcopy(moves.copy())
        after_time = datetime.now()
        print("Finding the path took " + str((after_time - before_time).microseconds))
        moves.reverse()
        if len(moves) == 0:
            print("No moves could get the drone in the finished position !")
        else:
            while running:
                move = moves.pop()
                self.controller.drone.x = move[0]
                self.controller.drone.y = move[1]
                if len(moves) == 0:
                    break
                pygame.time.wait(250)
                screen.blit(self.controller.drone.mapWithDrone(self.controller.map.image()), (0, 0))
                pygame.display.flip()
            screen.blit(displayWithPath(self.controller.map.image(), path), (0, 0))
            pygame.display.flip()
            pygame.time.wait(5000)
            pygame.quit()
