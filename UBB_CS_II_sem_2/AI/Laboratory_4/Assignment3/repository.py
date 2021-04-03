# -*- coding: utf-8 -*-
import os
import pickle

from Assignment3.gui import movingDrone
from domain import *


class Repository:
    def __init__(self):
        self.maps_representation = []
        self.__populations = []
        self.cmap = Map()
        self.load_file()
        self.cmap.surface = self.maps_representation[0]

    def initialize_random_map(self):
        cmap = Map()
        cmap.randomMap()
        self.maps_representation.append(cmap.surface)

    @staticmethod
    def create_population(args):
        # args = [populationSize, individualSize] -- you can add more args    
        return Population(args[1], args[2])

    # TO DO : add the other components for the repository: 
    #    load and save from file, etc

    def load_file(self):
        if os.path.getsize('maps.pickle') > 0:
            with open('maps.pickle', 'rb') as file:
                # Call load method to deserialze
                myvar = pickle.load(file)
                self.maps_representation = myvar

    def save_file(self):
        with open('maps.pickle', 'wb') as file:
            # A new file will be created
            pickle.dump(self.maps_representation, file)

    def visualise_map(self):
        movingDrone(self.cmap, [[2, 1]])
