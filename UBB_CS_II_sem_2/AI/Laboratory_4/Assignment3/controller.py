from random import sample

import matplotlib.pyplot as plt
import numpy
from numpy.lib import average

from repository import *


class Controller:
    def __init__(self):
        # args - list of parameters needed in order to create the controller
        self.stats = []
        self.repository = Repository()
        self.population = None

    def set_args(self, args):
        Seed.sed = randint(1, 31)
        seed(Seed.sed)
        self.population = self.repository.create_population(args)
        self.population.set_population_exp_space(self.repository.cmap.surface)
        self.population.evaluate()

    def get_cmap(self):
        return self.repository.cmap

    def initialize_repository_map(self):
        self.repository.initialize_random_map()

    def iteration(self):
        indivs = self.population.get_v()
        individuals = self.population.selection(len(indivs) // 2)  # take the first better half
        lgt = len(individuals) // 2
        offsprings = []
        index = 0
        for _ in range(lgt):
            off1, off2 = individuals[index].crossover(individuals[index + 1], 1)
            offsprings.append(off1)
            offsprings.append(off2)
            index += 2
        for _ in range(lgt):
            parents = sample(individuals, 2)
            off1, off2 = parents[0].crossover(parents[1], 1)
            offsprings.append(off1)
            offsprings.append(off2)
        for x in offsprings:
            x.mutate(0.02)
        return offsprings

    def run(self,  args=None):
        battery_life = int(args[0])
        while battery_life > 0:
            offs = self.iteration()
            self.population.set_v(offs)
            self.population.evaluate()
            battery_life -= 1
            if battery_life == 0:
                self.population.get_v().sort(key=lambda x: x.get_f(), reverse=True)
                elem = self.population.get_v()[0]
                avgs = [x.get_f() for x in self.population.get_v()]
                stdd = numpy.std(avgs)
                avg = average(avgs)
                self.stats.append([len(self.stats) + 1, Seed.sed, avg, round(stdd, 2)])
                return elem

    def solver(self, args):
        path = []
        x = 2
        y = 1
        for i in self.run(args).get_x():
            x = x + v[i.gene][0]
            y = y + v[i.gene][1]
            path.append([x, y])
        return path

    def show_table(self):
        f = plt.figure(figsize=(10, 20))
        ax = f.add_subplot(122)
        plt.autoscale()
        table = ax.table(cellText=self.stats, colLabels=["No.Run", "Seed", "Fitness", "Std. Deviation"], loc='center')
        table.scale(1, len(self.stats[0]))
        ax.axis('off')
        plt.show()

    def save_repository_map(self):
        self.repository.save_file()

    def set_map(self, index):
        space = self.repository.maps_representation[index]
        self.population.set_population_exp_space(space)
        self.repository.cmap.surface = space
        return len(self.repository.maps_representation)
