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

    def __move_ant(self, ants, q0):
        is_alive = [True for _ in ants]
        for i in range(len(ants)):
            ant = ants[i]
            for step in range(
                    self.__sens_lgt - 1):
                successful_move_completion = ant.next_move(self.__dists, self.__phero_table, q0)
                if not successful_move_completion:
                    is_alive[i] = False
                    break

        alive_ants = []
        for i in range(len(ants)):
            ants[i].compute_fitness(self.__aco_matrix.get_nodes())
            alive_ants.append(ants[i])
        return alive_ants

    @staticmethod
    def __best_ant(ants):
        best_ant = None
        best_fitness = 0
        for ant in ants:
            if best_fitness < ant.get_fitness():
                best_fitness = ant.get_fitness()
                best_ant = ant
        return best_ant

    def __simulate_epoch(self, antCount, q0, rho):
        ants = [Ant() for _ in range(antCount)]
        ants = self.__move_ant(ants, q0)
        for i in range(self.__sens_lgt):
            for j in range(self.__sens_lgt):
                # we simulate the evaporation of pheromone
                self.__phero_table[i][j] *= (1 - rho)
        for ant in ants:
            new_pheromone = 1.0 / ant.get_fitness()
            current_path = ant.get_path()
            for i in range(len(current_path) - 1):
                crt_node = current_path[i]
                next_node = current_path[i + 1]
                # updating the pheromone table
                self.__phero_table[crt_node][next_node] += new_pheromone
        return self.__best_ant(ants)

    def _update_bsolution(self, best_solution):
        current_solution = self.__simulate_epoch(ACOConstants.NOANTS, ACOConstants.Q0, ACOConstants.RHO)
        if current_solution is None:
            return best_solution
        current_solution_path_length = len(current_solution.get_path())
        if best_solution is None or current_solution_path_length > len(best_solution.get_path()) or (
                current_solution_path_length == len(
            best_solution.get_path()) and current_solution.get_fitness() < best_solution.get_fitness()):
            return current_solution
        return best_solution

    def solution_from_path(self, path):
        sensor_energy_pairs = []
        for i in range(0, len(path), 3):
            sensor = self.__nodes.getNodeList()[path[i]]
            sensor_energy_pairs.append(((sensor.getX(), sensor.getY()), path[i + 1] - path[i] - 1))
        return sensor_energy_pairs

    def run(self):
        best_solution = None
        for epoch in range(100):
            best_solution = self._update_bsolution(best_solution)
        return best_solution
