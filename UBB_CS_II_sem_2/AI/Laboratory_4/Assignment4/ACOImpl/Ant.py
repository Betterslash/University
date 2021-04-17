from copy import deepcopy
from random import random, randint

from Assignment4.ACOImpl.ACOUtils import ACOConstants
from Assignment4.repository import Repository
from Assignment4.utils import INF_VAL

exp_map = Repository().cmap


class Ant:
    def __init__(self, energy=1270):
        self.__path = [randint(0, (len(ACOConstants.SENSORS) - 1))]
        self.__energy = energy
        self.__fitness = 0

    def __sensor_to_energy(self):
        sensor_energy = []
        for i in range(0, len(self.__path)):
            sensor_energy.append((self.__path[i], self.__energy))
        return sensor_energy

    def compute_fitness(self, nodes):
        sensor_energy = self.__sensor_to_energy()
        mapm = deepcopy(exp_map.surface.copy())
        res = 0
        for pair in sensor_energy:
            sensor_index, energy = pair
            sensor = nodes[sensor_index]
            res += ACOConstants.get_accesible((sensor.get_x(), sensor.get_y()), mapm, energy)
        # approximativelly how many empty tiles we have is 320
        self.__fitness = 320-res

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
            selected_node = ACOConstants.selection(next_n_prob)
        self.__energy -= dist_map[self.__path[-1]][selected_node]
        self.__path.append(selected_node)
        return True

    def get_path(self):
        return self.__path

    def get_fitness(self):
        return self.__fitness

    def get_energy(self):
        return self.__energy
