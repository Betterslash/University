# -*- coding: utf-8 -*-

import numpy as np
from numpy.random import randint, seed, random

from utils import *

# the glass gene can be replaced with int or float, or other types
# depending on your problem's representation
global sd


class Seed:
    sed = None

    def __init__(self):
        sed = sd


class Gene:
    def __init__(self):
        # random initialise the gene according to the representation
        gene_num = randint(0, 4)

        possible_genes = [UP, DOWN, LEFT, RIGHT]
        self.gene = possible_genes[gene_num]

    def __str__(self):
        return str(self.gene)


class Individual:
    def __init__(self, size=0):
        self.__size = size
        self.__x = [Gene() for _ in range(self.__size)]
        self.__f = -1

    def get_f(self):
        return self.__f

    def get_x(self):
        return self.__x

    def fitness(self, exp_map):
        positions = set()
        x_pos = 2
        y_pos = 1
        final_sum = 0
        for elem in self.__x:
            if (0 <= x_pos < 20) and (0 <= y_pos < 20):
                if exp_map[x_pos][y_pos] == 0:
                    self.get_vision(exp_map, x_pos, y_pos, positions)
                else:
                    final_sum -= self.__size // 5
            else:
                final_sum -= self.__size * 5
            x_pos += v[elem.gene][0]
            y_pos += v[elem.gene][1]
        # print(len(positions))
        self.__f = final_sum + len(positions)

    @staticmethod
    def get_vision(exp_map, x, y, positions):
        dummy_x = x
        dummy_y = y
        while exp_map[dummy_x][y] != 1:
            if (0 <= dummy_x < 20) and (0 <= y < 20):
                positions.add((dummy_x, y))
                dummy_x += 1
            if (dummy_x == 19) and (exp_map[dummy_x][y] != 1):
                positions.add((dummy_x, y))
                break
            else:
                break
        dummy_x = x
        while exp_map[dummy_x][y] != 1:
            if (0 <= dummy_x < 20) and (0 <= dummy_y < 20):
                positions.add((dummy_x, y))
                dummy_x -= 1
            if (dummy_x == 0) and (exp_map[dummy_x][y] != 1):
                positions.add((dummy_x, y))
                break
            else:
                break
        while exp_map[x][dummy_y] != 1:
            if (0 <= x < 20) and (0 <= dummy_y < 20):
                positions.add((x, dummy_y))
                dummy_y += 1
            if (dummy_y == 19) and (exp_map[x][dummy_y] != 1):
                positions.add((x, dummy_y))
                break
            else:
                break
        dummy_y = y
        while exp_map[x][dummy_y] != 1:
            if (0 <= x < 20) and (0 <= dummy_y < 20):
                positions.add((x, dummy_y))
                dummy_y -= 1
            if (dummy_y == 0) and (exp_map[x][dummy_y] != 1):
                positions.add((x, dummy_y))
                break
            else:
                break

    def mutate(self, mutateProbability=0.02):
        if random() < mutateProbability:
            index = randint(1, self.__size - 1)
            self.__x[index] = Gene()
            # perform a mutation with respect to the representation

    def crossover(self, otherParent, crossoverProbability=0.95):
        offspring1, offspring2 = Individual(self.__size), Individual(self.__size)
        if random() < crossoverProbability:
            p = randint(1, self.__size)
            offspring1.__x = self.__x[:p] + otherParent.__x[p:]
            offspring2.__x = self.__x[p:] + otherParent.__x[:p]
            # perform the crossover between the self and the otherParent
        return offspring1, offspring2

    def __str__(self):
        representation = ""
        for i in self.__x:
            representation += str(i) + " "
        return representation


class Population:
    def __init__(self, populationSize=0, individualSize=0):
        self.__population_size = populationSize
        self.__v = [Individual(individualSize) for _ in range(populationSize)]
        self._exploration_space = []

    def evaluate(self):
        # evaluates the population
        for x in self.__v:
            x.fitness(self._exploration_space)

    def set_v(self, value):
        self.__population_size = len(value)
        self.__v = value

    def selection(self, k=0):
        self.__v.sort(key=lambda x: x.get_f(), reverse=True)
        return self.__v[:k]

    def set_generation(self, generation):
        self.__v = generation

    def set_population_exp_space(self, exp_spcae):
        self._exploration_space = exp_spcae

    def get_v(self):
        return self.__v


class Map:
    def __init__(self, n=20, m=20):
        self.n = n
        self.m = m
        self.surface = np.zeros((self.n, self.m))

    def randomMap(self, fill=0.2):
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
