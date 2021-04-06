from Assignment4.ACOImpl.ACONode import ACONode
from Assignment4.ACOImpl.Ant import exp_map
from Assignment4.utils import directions


class Sensor(ACONode):
    def __init__(self, x=-1, y=-1):
        super().__init__(x, y)
        self._max_energy = 0
        self._vision_list = []
        self._check_energy()

    def __str__(self):
        stg = "S(" + str(self.__x) + ", " + str(self.__y) + ")"
        return stg

    def _check_energy(self, expm=exp_map.surface):
        vision_list = [0] * 6
        for i in range(1, 6):
            for d in directions:
                dum_x = self.get_x()
                dum_y = self.get_y()
                for pos in range(i):
                    dum_x = dum_x + d[0]
                    dum_y = dum_y + d[1]
                    if 0 <= dum_x < 20 and 0 <= dum_y < 20:
                        if expm[dum_x][dum_y] != 1:
                            vision_list[i] += 1
                        else:
                            break
        self._vision_list = vision_list
        self._max_energy = vision_list.index(max(vision_list))

    def get_max_energy(self):
        return self._max_energy
