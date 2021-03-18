from repository import *


class Controller:
    def __init__(self, args):
        # args - list of parameters needed in order to create the controller
        self.repository = Repository()
        self.population = self.repository.createPopulation(args)

    def get_cmap(self):
        return self.repository.cmap

    def iteration(self, args):
        # args - list of parameters needed to run one iteration
        # a iteration:
        # selection of the parrents
        # create offsprings by crossover of the parents
        # apply some mutations
        # selection of the survivors
        self.population.evaluate()
        off1 = self.population.selection()
        off2 = self.population.selection()
        off1, off2 = off1.crossover(off2)
        off1.mutate()
        off2.mutate()
        return self.population.selection()

    def run(self, args):
        # args - list of parameters needed in order to run the algorithm
        battery_life = int(args[0])
        # until stop condition
        #    perform an iteration
        #    save the information need it for the satistics

        # return the results and the info for statistics
        while battery_life > 0:
            params = self.iteration(args)
            battery_life -= 1
            if battery_life == 0:
                return params

    def solver(self, args):
        # args - list of parameters needed in order to run the solver

        # create the population,
        # run the algorithm
        # return the results and the statistics
        pass
