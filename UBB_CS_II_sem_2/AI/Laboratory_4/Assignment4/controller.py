from Assignment4.ACOImpl.ACONodeMatrix import ACOMatrix
from Assignment4.ACOImpl.ACOUtils import ACOConstants
from Assignment4.ACOImpl.Ant import exp_map, Ant


class Controller:
    def __init__(self):
        self.__aco_matrix = ACOMatrix(exp_map)
        self.__sens_lgt = self.__aco_matrix.get_sens_lgt()
        self.__phero_table = [[1.0 for _ in range(self.__sens_lgt)] for _ in range(self.__sens_lgt)]
        self.__dists = self.__aco_matrix.get_dists()
        self.__map_m = exp_map.surface
        self.__nodes = self.__aco_matrix.get_nodes()

    def __moveAnts(self, ants, q0):
        is_ant_alive = [True for _ in ants]
        for i in range(len(ants)):
            ant = ants[i]
            for step in range(
                    self.__sens_lgt - 1):
                successful_move_completion = ant.next_move(self.__dists, self.__phero_table, q0)
                if not successful_move_completion:
                    is_ant_alive[i] = False
                    break  # no use in trying to move the ant if it has no battery left / is dead

        alive_ants = []  # only return the ants which completed the path
        for i in range(len(ants)):
                ants[i].computeFitness()
                alive_ants.append(ants[i])
        return alive_ants

    @staticmethod
    def __selectBestAnt(ants):
        best_ant = None
        best_fitness = 0
        for ant in ants:
            if best_fitness < ant.get_fitness():
                best_fitness = ant.get_fitness()
                best_ant = ant
        return best_ant

    def __simulateEpoch(self, antCount, alpha, beta, q0, rho):
        ants = [Ant() for _ in range(antCount)]
        ants = self.__moveAnts(ants, q0)
        for i in range(self.__sens_lgt):
            for j in range(self.__sens_lgt):
                self.__phero_table[i][j] = (1 - rho) * self.__phero_table[i][j]
        for ant in ants:
            new_pheromone = 1.0 / ant.get_fitness()
            current_path = ant.get_path()
            for i in range(len(current_path) - 1):
                crt_node = current_path[i]
                next_node = current_path[i + 1]
                self.__phero_table[crt_node][next_node] += new_pheromone
        return self.__selectBestAnt(ants)

    def __updateBestSolution(self, bestSolution):
        current_solution = self.__simulateEpoch(ACOConstants.NOANTS, ACOConstants.ALPHA, ACOConstants.BETA,
                                                ACOConstants.Q0,
                                                ACOConstants.RHO)
        if current_solution is None:
            return bestSolution
        current_solution_path_length = len(current_solution.get_path())
        if bestSolution is None or current_solution_path_length > len(bestSolution.get_path()) or (
                current_solution_path_length == len(
            bestSolution.get_path()) and current_solution.get_fitness() < bestSolution.get_fitness()):
            return current_solution
        return bestSolution

    def getSolutionFromPath(self, path):
        sensor_energy_pairs = []
        for i in range(0, len(path), 3):
            sensor = self.__nodes.getNodeList()[path[i]]
            sensor_energy_pairs.append(((sensor.getX(), sensor.getY()), path[i + 1] - path[i] - 1))
        return sensor_energy_pairs

    def run(self):
        best_solution = None
        for epoch in range(100):
            best_solution = self.__updateBestSolution(best_solution)
        return best_solution
