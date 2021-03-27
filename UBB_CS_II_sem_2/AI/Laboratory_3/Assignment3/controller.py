from random import sample

from repository import *


class Controller:
    def __init__(self):
        # args - list of parameters needed in order to create the controller
        self.repository = Repository()
        self.population = None

    def set_args(self, args):
        self.population = self.repository.create_population(args)
        self.population.set_population_exp_space(self.repository.cmap.surface)
        self.population.evaluate()

    def get_cmap(self):
        return self.repository.cmap

    def initialize_repository_map(self):
        self.repository.initialize_random_map()

    def iteration(self):
        # args - list of parameters needed to run one iteration
        # a iteration:
        # selection of the parrents
        # create offsprings by crossover of the parents
        # apply some mutations
        # selection of the survivors
        individuals = self.population.selection(len(self.population.get_v()) // 2)  # take the first better half
        offsprings = []

        index = 0
        for _ in range(len(individuals) // 2):
            off1, off2 = individuals[index].crossover(individuals[index + 1])
            offsprings.append(off1)
            offsprings.append(off2)
            index += 2

        for _ in range(len(individuals) // 2):
            parents = sample(individuals, 2)
            off1, off2 = parents[0].crossover(parents[1])
            offsprings.append(off1)
            offsprings.append(off2)

        for off in offsprings:
            off.mutate(0.04)

        return offsprings

    def run(self, args):
        # args - list of parameters needed in order to run the algorithm
        battery_life = int(args[0])
        # until stop condition
        #    perform an iteration
        #    save the information need it for the satistics
        # return the results and the info for statistics
        while battery_life > 0:
            self.population.set_v(self.iteration())
            self.population.evaluate()
            fts = sum(x.get_f() for x in self.population.get_v())
            print(fts)
            battery_life -= 1
            if battery_life == 0:
                self.population.get_v().sort(key=lambda x: x.get_f(), reverse=True)
                elem = self.population.get_v()[0]
                return elem

    def solver(self, args):
        # args - list of parameters needed in order to run the solver

        # create the population,
        # run the algorithm
        # return the results and the statistics
        pass

    def save_repository_map(self):
        self.repository.save_file()

    def set_map(self, index):
        self.repository.cmap.surface = self.repository.maps_representation[index]
        return len(self.repository.maps_representation)
