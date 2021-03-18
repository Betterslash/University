# -*- coding: utf-8 -*-

from random import *
from utils import *
import numpy as np


# the glass gene can be replaced with int or float, or other types
# depending on your problem's representation

class Gene:
    def __init__(self):
        # random initialise the gene according to the representation
        gene_num = randint(0, 3)
        possible_genes = [UP, DOWN, LEFT, RIGHT]
        self.gene = possible_genes[gene_num]


class Individual:
    def __init__(self, size=0):
        self.__size = size
        self.__x = [Gene() for i in range(self.__size)]
        self.__f = None

    def get_f(self):
        return self.__f

    def get_x(self):
        return self.__x

    def fitness(self):
        # compute the fitness for the indivisual
        # and save it in self.__f
        final_sum = 0
        for elem in self.__x:
            x_position = v[elem.gene][0]
            y_position = v[elem.gene][1]
            final_sum += abs(x_position + y_position)
        self.__f = 1 / final_sum

    def mutate(self, mutateProbability=0.04):
        if random() < mutateProbability:
            self.__x[randint(0, 3)].gene = 3 - randint(0, 3)
            # perform a mutation with respect to the representation

    def crossover(self, otherParent, crossoverProbability=0.8):
        offspring1, offspring2 = Individual(self.__size), Individual(self.__size)
        if random() < crossoverProbability:
            p = randint(0, self.__size)
            offspring1.__x = self.__x[:p] + otherParent.__x[p:]
            offspring2.__x = self.__x[p:] + otherParent.__x[:p]
            # perform the crossover between the self and the otherParent

        return offspring1, offspring2

    def __repr__(self):
        return repr((self.__f, self.__size, self.__x))


class Population:
    def __init__(self, populationSize=0, individualSize=0):
        self.__population_size = populationSize
        self.__v = [Individual(individualSize) for x in range(populationSize)]

    def evaluate(self):
        # evaluates the population
        for x in self.__v:
            x.fitness()

    def selection(self, k=0) -> Individual:
        turnir = []
        turnir_size = self.__population_size
        for i in range(0, turnir_size):
            p = randint(0, self.__population_size - 1)
            turnir += [self.__v[p]]
        sorted(turnir, key=lambda elem: elem.get_f(), reverse=True)
        return turnir[0]


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

    def __str__(self):
        string = ""
        for i in range(self.n):
            for j in range(self.m):
                string = string + str(int(self.surface[i][j]))
            string = string + "\n"
        return string
