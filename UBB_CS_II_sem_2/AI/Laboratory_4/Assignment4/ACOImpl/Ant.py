from random import random, randint

from Assignment4.ACOImpl.ACOUtils import ACOConstants
from Assignment4.repository import Repository
from Assignment4.utils import INF_VAL

exp_map = Repository().cmap


class Ant:
    def __init__(self, energy=70):
        self.__path = [randint(0, (len(ACOConstants.SENSORS) - 1))]
        self.__energy = energy
        self.__fitness = 0

    def computeFitness(self):
        self.__fitness = 1 / (len(self.__path) + self.__energy)

    def __get_possible_moves(self, distMap):
        poss_moves = []
        current_node = self.__path[-1]
        distances = distMap[current_node]
        nodes_num = len(ACOConstants.SENSORS)
        for next_node in range(nodes_num):
            if next_node != current_node and distances[next_node] != INF_VAL and \
                    next_node not in self.__path and self.__energy >= distances[next_node]:
                poss_moves.append(next_node)
        return poss_moves

    def __prob_next_move(self, poss_moves, distMap, phero_table, be=ACOConstants.BETA, al=ACOConstants.ALPHA):
        current_index = self.__path[-1]
        nodes_num = len(ACOConstants.SENSORS)
        next_prob = [0 for _ in range(nodes_num)]
        for move_i in poss_moves:
            distance_next = distMap[current_index][move_i]
            pheromone_next = phero_table[current_index][move_i]
            probability = ((distance_next + 0.001) ** al) * (pheromone_next ** be)
            next_prob[move_i] = probability
        return next_prob

    def next_move(self, dist_map, phero_table, q0):
        poss_moves = self.__get_possible_moves(dist_map)
        if not poss_moves:
            return False
        next_n_prob = self.__prob_next_move(poss_moves, dist_map,
                                            phero_table)
        if random() < q0:
            best_move = max(next_n_prob)
            selected_node = next_n_prob.index(best_move)
        else:
            selected_node = self.__selection(next_n_prob)
        self.__energy -= dist_map[self.__path[-1]][selected_node]
        self.__path.append(selected_node)
        return True

    @staticmethod
    def __selection(next_n_prob):
        probability_sum = sum(next_n_prob)
        if probability_sum == 0:
            return randint(0, len(next_n_prob) - 1)
        partipartial_sums = [next_n_prob[0] / probability_sum]
        for i in range(1, len(next_n_prob)):
            partipartial_sums.append(partipartial_sums[i - 1] + next_n_prob[i] / probability_sum)
        r = random()
        position = 0
        while r > partipartial_sums[position]:
            position += 1
        return position

    def get_path(self):
        return self.__path

    def get_fitness(self):
        return self.__fitness

    def get_energy(self):
        return self.__energy
