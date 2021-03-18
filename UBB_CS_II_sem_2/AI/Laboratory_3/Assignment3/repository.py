# -*- coding: utf-8 -*-

import pickle
from domain import *


class Repository:
    def __init__(self):
        self.maps_representation = []
        self.__populations = []
        self.cmap = Map()
        self.cmap.randomMap()

    def createPopulation(self, args):
        # args = [populationSize, individualSize] -- you can add more args    
        return Population(args[0], args[1])

    # TO DO : add the other components for the repository: 
    #    load and save from file, etc

    def load_file(self):
        with open('maps.pickle', 'rb') as file:
            # Call load method to deserialze
            myvar = pickle.load(file)
            self.maps_representation.append(myvar)

    def save_file(self):
        with open('maps.pickle', 'wb') as file:
            # A new file will be created
            for elem in self.maps_representation:
                pickle.dump(elem, file)
