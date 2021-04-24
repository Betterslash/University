from random import uniform

from constants.Constants import Constants
from controller.Computer import Computer
from model.FileHandler import FileHandler
from ui.Displayer import Displayer


class Solver:
    def __init__(self):
        __file_handler = FileHandler(Constants.FILE_PATH)
        self.__length = Constants.ITEM_FIELD_NUMBER - 1
        self.__items = __file_handler.read_file()
        self.__computer = None
        self.__displayer = Displayer()

    def __get_col_min_max(self):
        # We build minimal points for val1 and val2 and the maximum ones as well
        minima = [Constants.MAX_VALUE for _ in range(self.__length)]
        maxima = [Constants.MIN_VALUE for _ in range(self.__length)]

        for element in self.__items:
            if element.get_x() < minima[0]:
                minima[0] = element.get_x()

            if element.get_y() < minima[1]:
                minima[1] = element.get_y()

            if element.get_x() > maxima[0]:
                maxima[0] = element.get_x()

            if element.get_y() > maxima[1]:
                maxima[1] = element.get_y()

        return minima, maxima

    def __initialize_means(self, min_value, max_value):
        means = [[0. for _ in range(self.__length)] for _ in range(Constants.CLUSTER_NUMBER)]

        for mean in means:
            for i in range(len(mean)):
                mean[i] = uniform(min_value[i] + 1, max_value[i] - 1)

        return means

    @staticmethod
    def __update_mean(length, mean, item):
        for i in range(1, len(mean)):
            m = mean[i]
            m = (m * (length - 1) + item[i]) / float(length)
            mean[i] = round(m, 3)

        return mean

    @staticmethod
    def __classify_items(means, items):
        minim = Constants.MAX_VALUE
        index = -1

        for i in range(len(means)):
            distance = Constants.euclidean_distance(items, means[i])
            if distance < minim:
                minim = distance
                index = i

        return index

    def __calculate_means(self, items, max_iters=Constants.MAX_ITERS):
        min_value, max_value = self.__get_col_min_max()
        means = self.__initialize_means(min_value, max_value)
        cluster_sizes = [0 for _ in range(len(means))]
        bleongs_to = [0 for _ in range(len(self.__items))]

        for e in range(max_iters):
            change_counter = True
            for i in range(self.__length):
                item = items[i]
                index = Solver.__classify_items(means, item)

                cluster_sizes[index] += 1
                cluster_size = cluster_sizes[index]
                means[index] = Solver.__update_mean(cluster_size, means[index], item)

                if index != bleongs_to[i]:
                    change_counter = False
                bleongs_to[i] = index

            if change_counter:
                break

        return means

    def find_clustes(self):
        means = self.__calculate_means(self.__items)
        clusters = [[] for _ in range(len(means))]

        for item in self.__items:
            index = Solver.__classify_items(means, item)
            clusters[index].append(item)

        return clusters

    def run(self):
        items = self.find_clustes()
        self.__computer = Computer(items)
        self.__computer.compute()
        print(self.__computer.get_values())

        for elem in items:
            self.__displayer.display(elem)



