from repository import *


class Controller:
    def __init__(self):
        # args - list of parameters needed in order to create the controller
        self.repository = Repository()
        self.population = None

    def set_args(self, args):
        self.population = self.repository.create_population(args)
        self.population.set_population_exp_space(self.repository.cmap.surface)
        self.population.set_exp_space(deepcopy(self.repository.cmap.surface.copy()))
        self.population.evaluate()

    def get_cmap(self):
        return self.repository.cmap

    def initialize_repository_map(self):
        self.repository.initialize_random_map()

    def iteration(self, args):
        # args - list of parameters needed to run one iteration
        # a iteration:
        # selection of the parrents
        # create offsprings by crossover of the parents
        # apply some mutations
        # selection of the survivors
        self.population.evaluate()
        parents = [self.population.selection()]
        for i in range(len(self.population.get_v())):
            parent_one, parent_two = parents[0].crossover(self.population.get_v()[i])
            parent_one.mutate()
            parent_two.mutate()
            parents.append(parent_one)
            parents.append(parent_two)
        self.population.set_v(parents)
        self.population.set_exp_space(self.population._exploration_space)

    def run(self, args):
        # args - list of parameters needed in order to run the algorithm
        battery_life = int(args[0])
        # until stop condition
        #    perform an iteration
        #    save the information need it for the satistics
        # return the results and the info for statistics
        while battery_life > 0:
            self.iteration(args[2:])
            battery_life -= 1
            if battery_life == 0:
                elem = self.population.get_v()[0]
                print(elem.get_f())
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
