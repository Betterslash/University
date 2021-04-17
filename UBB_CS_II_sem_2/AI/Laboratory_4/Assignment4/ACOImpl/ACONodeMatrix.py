from copy import deepcopy

from Assignment4.ACOImpl.ACOController import ACOController
from Assignment4.domain import Drone
from Assignment4.utils import INF_VAL


class ACOMatrix:
    def __init__(self, mapm):
        self._nodes_representation = []
        self._exp_map = mapm
        self._controller = ACOController()
        self._sens_lgt = len(
            self._controller.sensors * 8)  # because we will place extra nodes for computing energy levels
        self._distances = [[INF_VAL for _ in range(self._sens_lgt)] for _ in range(self._sens_lgt)]
        self.__initialize_nodes()
        self._set_distances()

    def get_nodes(self):
        return self._controller.sensors

    def get_sens_lgt(self):
        return self._sens_lgt

    def get_dists(self):
        return self._distances

    def __initialize_nodes(self):
        for i in range(len(self._controller.sensors)):
            for j in range(9):
                # for every energy level + 1 first + exit tile(because we need to get out of the node) we place an extra node
                self._nodes_representation.append(self._controller.sensors[i])
        return

    @staticmethod
    def _check_diff_nodes(node_i, node_j):
        if (node_i[0] != node_j[0]) or (node_j[1] != node_i[1]):
            return True
        else:
            return False

    def _set_distances(self):
        sensors = self._controller.sensors
        already_checked_sensors = set()
        # for every unchecked sensor we put the energy consumed for every non wasting case
        for i in range(64):
            if i % 8 == 0:
                for en in range(6):
                    if en <= sensors[i // 8].get_max_energy():
                        self._distances[i][i + en + 1] = en
                    else:
                        self._distances[i][i + en + 1] = INF_VAL
                    already_checked_sensors.add(((i * 8), ((i * 8) + en + 1)))
            elif (i + 1) % 8 == 0:
                self._controller.drone = Drone(self._controller.sensors[i // 8].get_x(), self._controller.sensors[i // 8].get_y())
                for j in range(i + 1, 64):
                    dist = self._controller.searchAStar(self._controller.sensors[j // 8].get_x(),
                                                        self._controller.sensors[j // 8].get_y())
                    self._distances[i][j] = self._distances[i - i % 8 + 7][i - i % 8] = len(dist)
            else:
                self._distances[i][i - i % 8 + 7] = 0
        return
