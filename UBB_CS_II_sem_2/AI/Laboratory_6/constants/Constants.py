import math


class Constants:
    FILE_PATH = "dataset.csv"
    MAX_VALUE = 99999
    MIN_VALUE = -99999
    ITEM_FIELD_NUMBER = 3
    MAX_ITERS = 10000000
    RADIUS_SIZE = 2
    CLUSTER_NUMBER = 4
    COLORS = ['red', 'green', 'blue', 'magenta']
    GREEN = (0, 255, 0)
    BLUE = (0, 0, 255)
    PURPLE = (255, 0, 255)
    BLACK = (0, 0, 0)
    WHITE = (255, 255, 255)

    @staticmethod
    def euclidean_distance(x_value, y_value):
        result = 0
        # print(x_value, y_value)
        for i in range(1, len(x_value)):
            result += math.pow(x_value[i] - y_value[i - 1], 2)

        return math.sqrt(result)
