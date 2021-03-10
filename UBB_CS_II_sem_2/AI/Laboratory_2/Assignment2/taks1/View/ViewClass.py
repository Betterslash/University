import pygame

from taks1.Controller.ControllerClass import Controller, displayWithPath
from taks1.Model.DroneClass import Drone
from taks1.Model.EnviromentVariables import *
from taks1.Model.MapClass import Map


class View:
    def __init__(self, mapM: Map, droneD: Drone):
        self.controller = Controller(droneD)

    def get_controller_drone_X(self):
        return self.controller.get_drone_X()

    def get_controller_drone_Y(self):
        return self.controller.get_drone_Y()

    def get_exp_Map(self):
        return self.controller.get_exp_Map()

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
        self.get_exp_Map()[self.get_controller_drone_X()][self.get_controller_drone_Y()].g = 0
        self.get_exp_Map()[self.get_controller_drone_X()][self.get_controller_drone_Y()].f = 0
        self.get_exp_Map()[self.get_controller_drone_X()][self.get_controller_drone_Y()].h = 0
        self.get_exp_Map()[self.get_controller_drone_X()][self.get_controller_drone_Y()].x = self.get_controller_drone_X()
        self.get_exp_Map()[self.get_controller_drone_X()][self.get_controller_drone_Y()].y = self.get_controller_drone_Y()
        # main loop
        self.controller.searchAStar(2, 1)
        # while running:
        #     # event handling, gets all event from the event queue
        #     # for event in pygame.event.get():
        #     #     # only do something if the event is of type QUIT
        #     #     if event.type == pygame.QUIT:
        #     #         # change the value to False, to exit the main loop
        #     #         running = False
        #     #
        #     #     if event.type == KEYDOWN:
        #     #         d.move(m)  # this call will be erased
        #     # searchGreedy(m, d, d.x, d.y, 4, 5)
        #     move = moves.pop()
        #     self.controller.drone.x = move[0]
        #     self.controller.drone.y = move[1]
        #     if len(moves) == 0:
        #         break
        #     pygame.time.wait(50)
        #     screen.blit(self.controller.drone.mapWithDrone(m.image()), (0, 0))
        #     pygame.display.flip()

        path = self.controller.dummySearch()
        screen.blit(displayWithPath(self.controller.map.image(), path), (0, 0))

        pygame.display.flip()
        pygame.time.wait(5000)
        pygame.quit()
