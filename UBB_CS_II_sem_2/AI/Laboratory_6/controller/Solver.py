from random import uniform, random

from constants.Constants import Constants
from controller.Computer import Computer
from model.FileHandler import FileHandler
from ui.Displayer import Displayer


def get_m():
    centroids = []
    for _ in range(4):
        x = (random() - 0.5) * 10
        y = (random() - 0.5) * 10
        c = [x, y]
        centroids.append(c)
    return centroids


class Solver:
    def __init__(self):
        self.__file_handler = FileHandler(Constants.FILE_PATH)
        self.__length = Constants.ITEM_FIELD_NUMBER - 1
        self.__items = self.__file_handler.read_file()
        self.__computer = None
        self.__displayer = Displayer()
        min_value, max_value = self.__get_col_min_max()
        self.__means = self.__initialize_means(min_value, max_value)
        self.__calculate_means()

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
            # mean.sort()

        return means

    @staticmethod
    def __update_mean(length, mean, item):
        for i in range(len(mean)):
            m = mean[i]
            m = (m * (length - 1) + item[i + 1]) / float(length)
            mean[i] = round(m, 3)

        return mean

    def __classify_items(self, items):
        # Get index of class with minimmum distance from item to mean
        minim = Constants.MAX_VALUE
        index = -1

        for i in range(len(self.__means)):
            distance = Constants.euclidean_distance(items, self.__means[i])
            if distance < minim:
                minim = distance
                index = i

        return index

    def __calculate_means(self, max_iters=Constants.MAX_ITERS):
        cluster_sizes = [0 for _ in range(len(self.__means))]
        bleongs_to = [0 for _ in range(len(self.__items))]

        for e in range(max_iters):

            change_counter = True

            for i in range(len(self.__items)):
                item = self.__items[i]
                index = self.__classify_items(item)

                cluster_sizes[index] += 1
                cluster_size = cluster_sizes[index]
                self.__means[index] = self.__update_mean(cluster_size, self.__means[index], item)

                if index != bleongs_to[i]:
                    change_counter = False
                bleongs_to[i] = index

            if change_counter:
                break

        return self.__means

    def __find_clusters(self):
        # We iterate through all items and we assign them to the computed clusters

        clusters = [[] for _ in range(len(self.__means))]

        for item in self.__items:
            index = self.__classify_items(item)
            clusters[index].append(item)

        return clusters

    def run(self):
        items = self.__find_clusters()
        self.__computer = Computer(items)
        self.__computer.compute(self.__items)

        stats = self.__computer.get_stats().values()
        for i in stats:
            for el in i.keys():
                print(str(el) + " " + str(i[el]))
            print("-------------\n")

        self.__displayer.display(items)
