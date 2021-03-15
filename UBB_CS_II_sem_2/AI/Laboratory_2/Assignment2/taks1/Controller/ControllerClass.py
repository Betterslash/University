from queue import PriorityQueue
from random import randint

import pygame
from numpy import sqrt

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


def computeValueGreedy(current, destination):
    # Euclidean distance for effective distance
    dx = current[0] - destination[0]
    dy = current[1] - destination[1]
    return sqrt(dx * 2 + dy * 2)


class Controller:
    def __init__(self):
        x = randint(0, 19)
        y = randint(0, 19)
        self.drone = Drone(x, y)
        self.map = Map()
        self.map.loadMap("test1.map")
        self.moves = []
        self.exploration_map = self.createObjectsMap()

    def isUnblocked(self, posX, posY):
        return self.map.surface[posX][posY] == 0

    def computeHValue(self, destination):
        # Manhattan distance for A*
        return abs(self.drone.get_X() - destination[0]) + abs(self.drone.get_Y() - destination[1])

    def __init_first_position(self):
        self.exploration_map[self.drone.get_X()][self.drone.get_Y()].g = 0
        self.exploration_map[self.drone.get_X()][self.drone.get_Y()].h = 0
        self.exploration_map[self.drone.get_X()][self.drone.get_Y()].f = 0
        self.exploration_map[self.drone.get_X()][self.drone.get_Y()].x = self.drone.get_X()
        self.exploration_map[self.drone.get_X()][self.drone.get_Y()].y = self.drone.get_Y()

    def __trace_path(self, destination):
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
        self.__init_first_position()
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
                        self.__trace_path((finalX, finalY))
                        return self.moves
                    elif not self.drone.close_list[(current_x, current_y)]:
                        if self.isUnblocked(current_x, current_y):
                            # We iterate the steps as g
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
        return []

    def createObjectsMap(self):
        for i in range(self.map.m):
            for j in range(self.map.n):
                self.drone.close_list[(i, j)] = False

        return [[Node(-1, -1, self.map.surface[j][i], INF_VAL, INF_VAL, INF_VAL) for i in range(self.map.m)] for j in
                range(self.map.n)]

    def searchGreedy(self, finalX, finalY):
        # TO DO
        # implement the search function and put it in controller
        # returns a list of moves as a list of pairs [x,y]
        path = []
        pq = self.drone.priority_queue
        visited = self.drone.visited
        i = self.drone.get_X()
        j = self.drone.get_Y()
        pq.put((0, (i, j)))
        final = (finalX, finalY)
        while not pq.empty():
            u = pq.get()[1]
            path.append(u)
            visited.append((u[0], u[1]))
            if u[0] == finalX and u[1] == finalY:
                self.moves = path
                return self.moves
            minList = PriorityQueue()
            for d in directions:
                current_x = u[0] + d[0]
                current_y = u[1] + d[1]
                current = (current_x, current_y)
                if isValid(current_x, current_y) and self.isUnblocked(current_x, current_y):
                    if (current_x, current_y) not in visited:
                        cost = computeValueGreedy(current, final)
                        minList.put((cost, current))
            pq.put(minList.get())
            minList.queue.clear()
