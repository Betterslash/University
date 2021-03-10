import pygame

from taks1.Model.DroneClass import Drone
from taks1.Model.EnviromentVariables import *
from taks1.Model.MapClass import Map
from taks1.Model.NodeClass import Node


def isValid(posX, posY):
    return 0 <= posX < 20 and 0 <= posY < 20


def displayWithPath(image, path):
    mark = pygame.Surface((20, 20))
    mark.fill(GREEN)
    for move in path:
        image.blit(mark, (move[1] * 20, move[0] * 20))

    return image


class Controller:
    def __init__(self, droneD: Drone):
        self.drone = droneD
        self.map = Map()
        self.map.loadMap("test1.map")
        self.moves = []
        self.exploration_map = self.createObjectsMap()

    def get_Drone(self):
        return self.drone

    def get_exp_Map(self):
        return self.exploration_map

    def get_drone_X(self):
        return self.drone.get_X()

    def get_drone_Y(self):
        return self.drone.get_Y()

    def isUnblocked(self, posX, posY):
        return self.map.surface[posX][posY] == 0

    def computeHValue(self, destination):
        return abs(self.get_drone_X() - destination[0]) + abs(self.get_drone_Y() - destination[1])

    def trace_path(self, destination):
        row = destination[0]
        col = destination[1]
        while self.exploration_map[row][col].x != row or self.exploration_map[row][col].y != col:
            self.moves.append((row, col))
            temp_row = self.exploration_map[row][col].x
            temp_col = self.exploration_map[row][col].y
            row = temp_row
            col = temp_col

        self.moves.append((row, col))
        self.moves.reverse()
        return self.moves

    def searchAStar(self, finalX, finalY):
        # TO DO
        # implement the search function and put it in controller
        # returns a list of moves as a list of pairs [x,y]
        while len(self.drone.open_list) > 0:
            current_elem = self.drone.open_list[0]
            self.drone.open_list.remove(current_elem)
            i = current_elem[1][0]
            j = current_elem[1][1]
            self.drone.close_list[(i, j)] = True
            for d in directions:

                current_x = i + d[0]
                current_y = j + d[1]

                if isValid(current_x, current_y):
                    if current_x == finalX and current_y == finalY:
                        self.exploration_map[current_x][current_y].x = i
                        self.exploration_map[current_x][current_y].y = j
                        self.trace_path((finalX, finalY))
                        return self.moves
                    elif not self.drone.close_list[(current_x, current_y)]:
                        if self.isUnblocked(current_x, current_y):
                            g_function = self.exploration_map[i][j].g + 1
                            h_function = self.computeHValue((finalX, finalY))
                            f_function = g_function + h_function
                            if self.exploration_map[current_x][current_y].f == INF_VAL or \
                                    self.exploration_map[current_x][
                                        current_y].f > f_function:
                                self.drone.open_list.append((f_function, (current_x, current_y)))
                                self.exploration_map[current_x][current_y].f = f_function
                                self.exploration_map[current_x][current_y].g = g_function
                                self.exploration_map[current_x][current_y].h = h_function
                                self.exploration_map[current_x][current_y].x = i
                                self.exploration_map[current_x][current_y].y = j

    def createObjectsMap(self):
        for i in range(self.map.m):
            for j in range(self.map.n):
                self.drone.close_list[(i, j)] = False

        return [[Node(-1, -1, self.map.surface[j][i], INF_VAL, INF_VAL, INF_VAL) for i in range(self.map.m)] for j in
                range(self.map.n)]

    def searchGreedy(self, initialX, initialY, finalX, finalY):
        # TO DO
        # implement the search function and put it in controller
        # returns a list of moves as a list of pairs [x,y]
        pass

    def dummySearch(self):
        # example of some path in test1.map from [5,7] to [7,11]
        return self.moves
