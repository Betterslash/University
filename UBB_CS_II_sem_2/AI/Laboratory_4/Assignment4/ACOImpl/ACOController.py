from Assignment4.ACOImpl.ACOUtils import ACOConstants
from Assignment4.ACOImpl.Ant import exp_map
from Assignment4.ACOImpl.Sensor import Sensor

from Assignment4.domain import Drone, Node
from Assignment4.utils import directions, INF_VAL


class ACOController:
    def __init__(self, drone=Drone(2, 1), mapm=exp_map, sensors=ACOConstants.SENSORS):
        self.drone = drone
        self.map = mapm
        self.moves = []
        self.sensors = sensors
        self.exploration_map = []
        self.__set_sensors()

    def __set_sensors(self):
        sensors = []
        for i in self.sensors:
            sensor = Sensor(i[0], i[1])
            sensors.append(sensor)
        self.sensors = sensors
        for i in self.sensors:
            self.map.surface[i.get_x()][i.get_y()] = 6

    @staticmethod
    def isValid(posX, posY):
        return 0 <= posX < 20 and 0 <= posY < 20

    def isUnblocked(self, posX, posY):
        return self.map.surface[posX][posY] == 0

    def computeHValue(self, destination):
        return abs(self.drone.x - destination[0]) + abs(self.drone.y - destination[1])

    def __init_first_position(self):
        self.exploration_map[self.drone.x][self.drone.y].g = 0
        self.exploration_map[self.drone.x][self.drone.y].h = 0
        self.exploration_map[self.drone.x][self.drone.y].f = 0
        self.exploration_map[self.drone.x][self.drone.y].x = self.drone.x
        self.exploration_map[self.drone.x][self.drone.y].y = self.drone.y

    def __trace_path(self, destination):
        self.moves.clear()
        row = destination[0]
        col = destination[1]
        while self.exploration_map[row][col].x != row or self.exploration_map[row][col].y != col:
            self.moves.append((row, col))
            temp_row = self.exploration_map[row][col].x
            temp_col = self.exploration_map[row][col].y
            row = temp_row
            col = temp_col

        self.moves.append((row, col))
        self.moves.reverse()
        return self.moves

    def searchAStar(self, finalX, finalY):
        self.exploration_map = self.createObjectsMap()
        self.__init_first_position()
        while len(self.drone.open_list) > 0:
            current_elem = self.drone.open_list[0]
            self.drone.open_list.remove(current_elem)
            i = current_elem[1][0]
            j = current_elem[1][1]
            self.drone.close_list[(i, j)] = True
            for d in directions:

                current_x = i + d[0]
                current_y = j + d[1]

                if self.isValid(current_x, current_y):
                    if current_x == finalX and current_y == finalY:
                        self.exploration_map[current_x][current_y].x = i
                        self.exploration_map[current_x][current_y].y = j
                        self.__trace_path((finalX, finalY))
                        return self.moves
                    elif not self.drone.close_list[(current_x, current_y)]:
                        if self.isUnblocked(current_x, current_y):
                            # We iterate the steps as g
                            g_function = self.exploration_map[i][j].g + 1
                            h_function = self.computeHValue((finalX, finalY))
                            f_function = g_function + h_function
                            if self.exploration_map[current_x][current_y].f == INF_VAL or \
                                    self.exploration_map[current_x][
                                        current_y].f > f_function:
                                self.drone.open_list.append((f_function, (current_x, current_y)))
                                self.exploration_map[current_x][current_y].f = f_function
                                self.exploration_map[current_x][current_y].g = g_function
                                self.exploration_map[current_x][current_y].h = h_function
                                self.exploration_map[current_x][current_y].x = i
                                self.exploration_map[current_x][current_y].y = j
        return []

    def createObjectsMap(self):
        for i in range(self.map.m):
            for j in range(self.map.n):
                self.drone.close_list[(i, j)] = False

        return [[Node(-1, -1, self.map.surface[j][i], INF_VAL, INF_VAL, INF_VAL) for i in range(self.map.m)] for j in
                range(self.map.n)]

    def traverse_map(self):
        path = []
        for i in range(1, len(self.sensors)):
            self.drone = Drone(self.sensors[i - 1].get_x(), self.sensors[i - 1].get_y())
            path.extend(self.searchAStar(self.sensors[i].get_x(), self.sensors[i].get_y()))
        self.moves = path
