# -*- coding: utf-8 -*-
from copy import deepcopy
from random import *

import numpy as np

from utils import *


# the glass gene can be replaced with int or float, or other types
# depending on your problem's representation

class Gene:
    def __init__(self):
        # random initialise the gene according to the representation
        gene_num = randint(0, 3)
        possible_genes = [UP, DOWN, LEFT, RIGHT]
        self.gene = possible_genes[gene_num]

    def __str__(self):
        return str(self.gene)


class Individual:
    def __init__(self, size=0):
        self.__size = size
        self.__x = [Gene() for i in range(self.__size)]
        self.__f = 0
        self.exploration_space = []

    def set_exp_space(self, exp_space):
        if len(self.exploration_space) == 0:
            self.exploration_space = deepcopy(exp_space.copy())

    def get_f(self):
        return self.__f

    def get_x(self):
        return self.__x

    def get_visibility(self, x, y):
        seen_sum = 0
        dummy_x = x
        dummy_y = y
        while self.exploration_space[dummy_x][y] != 1:
            if (0 <= dummy_x < 19) and (0 <= y < 20):
                if self.exploration_space[dummy_x][y] != 2:
                    self.exploration_space[dummy_x][y] = 2
                    seen_sum += NOT_VISITED
                else:
                    seen_sum += ALREADY_VISITED_POINTS
                dummy_x += 1
            else:
                break
        dummy_x = x
        while self.exploration_space[dummy_x][y] != 1:
            if (0 < dummy_x < 20) and (0 <= dummy_y < 20):
                if self.exploration_space[dummy_x][y] != 2:
                    self.exploration_space[dummy_x][y] = 2
                    seen_sum += NOT_VISITED
                else:
                    seen_sum += ALREADY_VISITED_POINTS
                dummy_x -= 1
            else:
                break
        while self.exploration_space[x][dummy_y] != 1:
            if (0 <= x < 20) and (0 <= dummy_y < 19):
                if self.exploration_space[x][dummy_y] != 2:
                    self.exploration_space[x][dummy_y] = 2
                    seen_sum += NOT_VISITED
                else:
                    seen_sum += ALREADY_VISITED_POINTS
                dummy_y += 1
            else:
                break
        dummy_y = y
        while self.exploration_space[x][dummy_y] != 1:
            if (0 <= x < 20) and (0 < dummy_y < 20):
                if self.exploration_space[x][dummy_y] != 2:
                    self.exploration_space[x][dummy_y] = 2
                    seen_sum += NOT_VISITED
                else:
                    seen_sum += ALREADY_VISITED_POINTS
                dummy_y -= 1
            else:
                break
        return seen_sum

    def fitness(self):
        # compute the fitness for the indivisual
        # and save it in self.__f
        final_sum = 0
        x_position = 2
        y_position = 1
        for elem in self.__x:
            x_position += v[elem.gene][0]
            y_position += v[elem.gene][1]
            if (0 <= x_position < 20) and (0 <= y_position < 20):
                if self.exploration_space[x_position][y_position] == 0:
                    final_sum += self.get_visibility(x_position, y_position)
                else:
                    final_sum -= WALL_COLLISION
            else:
                final_sum -= OUT_OF_MAP
        self.__f = -final_sum

    def mutate(self, mutateProbability=0.04):
        if random() < mutateProbability:
            self.__x[randint(0, len(self.__x) - 1)].gene = 3 - randint(0, 3)
            # perform a mutation with respect to the representation

    def crossover(self, otherParent, crossoverProbability=0.8):
        offspring1, offspring2 = Individual(self.__size), Individual(self.__size)
        if random() < crossoverProbability:
            p = randint(1, self.__size)
            offspring1.__x = self.__x[:p] + otherParent.__x[p:]
            offspring2.__x = self.__x[p:] + otherParent.__x[:p]
            # perform the crossover between the self and the otherParent
        return offspring1, offspring2

    def __le__(self, other):
        return self.__f <= other.get_f()

    def __lt__(self, other):
        return self.__f < other.get_f()

    def __str__(self):
        representation = ""
        for i in self.__x:
            representation += str(i) + " "
        return representation


class Population:
    def __init__(self, populationSize=0, individualSize=0):
        self.__population_size = populationSize
        self.__v = [Individual(individualSize) for x in range(populationSize)]
        self._exploration_space = []

    def evaluate(self):
        # evaluates the population
        for x in self.__v:
            x.fitness()

    def set_v(self, value):
        self.__v = value

    def selection(self, k=0):
        self.__v.sort()
        return self.__v[0]

    def set_generation(self, generation):
        self.__v = generation

    def add_to_population(self, off1: Individual, off2: Individual):
        self.__v.append(off1)
        self.__v.append(off2)
        off1.exploration_space = self._exploration_space
        off2.exploration_space = self._exploration_space

    def set_population_exp_space(self, exp_spcae):
        self._exploration_space = exp_spcae

    def set_exp_space(self, exp_space):
        for i in range(len(self.__v)):
            self.__v[i].set_exp_space(exp_space)

    def get_v(self):
        return self.__v


class Map:
    def __init__(self, n=20, m=20):
        self.n = n
        self.m = m
        self.surface = np.zeros((self.n, self.m))

    def randomMap(self, fill=0.3):
        for i in range(self.n):
            for j in range(self.m):
                if random() <= fill:
                    self.surface[i][j] = 1

    def get_surface(self):
        return self.surface

    def __str__(self):
        string = ""
        for i in range(self.n):
            for j in range(self.m):
                string = string + str(int(self.surface[i][j]))
            string = string + "\n"
        return string
