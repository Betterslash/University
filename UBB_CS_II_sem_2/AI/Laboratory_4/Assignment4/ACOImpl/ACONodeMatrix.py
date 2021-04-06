from copy import deepcopy

from Assignment4.ACOImpl.ACOController import ACOController
from Assignment4.domain import Drone
from Assignment4.utils import INF_VAL


class ACOMatrix:
    def __init__(self, mapm):
        self._nodes_representation = []
        self._exp_map = mapm
        self._controller = ACOController()
        self._sens_lgt = len(self._controller.sensors)
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
            self._nodes_representation.append(self._controller.sensors[i])

    @staticmethod
    def _check_diff_nodes(node_i, node_j):
        if (node_i[0] != node_j[0]) or (node_j[1] != node_i[1]):
            return True
        else:
            return False

    def _set_distances(self):
        sensors = self._controller.sensors
        for i in range(self._sens_lgt):
            node_i = (sensors[i].get_x(), sensors[i].get_y())
            for j in range(self._sens_lgt):
                node_j = (sensors[j].get_x(), sensors[j].get_y())
                if self._check_diff_nodes(node_i, node_j):
                    self._controller.drone = Drone(node_i[0], node_i[1])
                    self._distances[i][j] = \
                        len(deepcopy(self._controller.searchAStar(node_j[0], node_j[1]).copy()))
                else:
                    self._distances[i][j] = 0


