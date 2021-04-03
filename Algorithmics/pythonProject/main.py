DIRECTIONS = [[-1, 0], [1, 0], [0, 1], [0, -1]]
INF_VAL = 1111111


class Node:
    def __init__(self, X, Y, Value, g_fun, f_fun, h_fun):
        self.value = Value
        self.x = X
        self.y = Y
        self.g = g_fun
        self.f = f_fun
        self.h = h_fun


class Knight:
    def __init__(self, X, Y):
        self._x = X
        self._y = Y
        self.open_list = []
        self.open_list.append((0, (self._x, self._y)))
        self.close_list = dict()

    def get_X(self):
        return self._x

    def get_Y(self):
        return self._y


class Map:
    def __init__(self):
        self.rep = [[Node(0, 0, 0, 0, 0, 0)] * 8] * 8


class Solver:
    def __init__(self, X, Y):
        self.kn = Knight(X, Y)
        self.mapm = Map()
        self.moves = []
        self.mapm.rep = self.createObjectsMap()

    @staticmethod
    def isValid(posX, posY):
        return 0 <= posX < 8 and 0 <= posY < 8

    def computeHValue(self, destination):
        # Manhattan distance for A*
        return abs(self.kn.get_X() - destination[0]) + abs(self.kn.get_Y() - destination[1])

    def __tace_path(self, dest):
        row = dest[0]
        col = dest[1]
        while self.mapm.rep[row][col].x != row and self.mapm.rep[row][col].y != col:
            self.moves.append((row, col))
            temp_row = self.mapm.rep[row][col].x
            temp_col = self.mapm.rep[row][col].y
            row = temp_row
            col = temp_col
        self.moves.append((row, col))
        return len(self.moves)

    def createObjectsMap(self):
        for i in range(8):
            for j in range(8):
                self.kn.close_list[(i, j)] = False

        return [[Node(-1, -1, 0, INF_VAL, INF_VAL, INF_VAL) for _ in range(8)] for _ in
                range(8)]

    def __init_first_position(self):
        self.mapm.rep[self.kn.get_X()][self.kn.get_Y()].g = 0
        self.mapm.rep[self.kn.get_X()][self.kn.get_Y()].h = 0
        self.mapm.rep[self.kn.get_X()][self.kn.get_Y()].f = 0
        self.mapm.rep[self.kn.get_X()][self.kn.get_Y()].x = self.kn.get_X()
        self.mapm.rep[self.kn.get_X()][self.kn.get_Y()].y = self.kn.get_Y()

    def searchAStar(self, finalX, finalY):
        self.__init_first_position()
        while len(self.kn.open_list) > 0:
            current_elem = self.kn.open_list[0]
            self.kn.open_list.remove(current_elem)
            i = current_elem[1][0]
            j = current_elem[1][1]
            self.kn.close_list[(i, j)] = True
            for d in DIRECTIONS:
                curr_x = i + d[0]
                curr_y = i + d[1]
                if self.isValid(curr_x, curr_y):
                    if curr_x == finalX and curr_y == finalY:
                        self.mapm.rep[curr_x][curr_y].x = i
                        self.mapm.rep[curr_x][curr_y].y = j
                        self.__tace_path((finalX, finalY))
                        return
                    elif not self.kn.close_list[(curr_x, curr_y)]:
                        g_function = self.mapm.rep[i][j].g + 1
                        h_function = self.computeHValue((finalX, finalY))
                        f_function = g_function + h_function
                        if self.mapm.rep[curr_x][curr_y].f == INF_VAL or \
                                self.mapm.rep[curr_x][curr_y].f > f_function:
                            self.kn.open_list.append((f_function, (curr_x, curr_y)))
                            self.mapm.rep[curr_x][curr_y].f = f_function
                            self.mapm.rep[curr_x][curr_y].g = g_function
                            self.mapm.rep[curr_x][curr_y].h = h_function
                            self.mapm.rep[curr_x][curr_y].x = i
                            self.mapm.rep[curr_x][curr_y].y = j
        return []


def QuickKnight(strParam):
    solver = Solver(1, 1)
    print(str(solver.searchAStar(6, 6)))
    return strParam


# keep this function call here
if __name__ == "__main__":
    QuickKnight("ss")
