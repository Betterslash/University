# -*- coding: utf-8 -*-


# imports
from Assignment3.commandreader import CommandReader
from gui import *
from controller import *
from repository import *
from domain import *


# create a menu
#   1. map options:
#         a. create random map
#         b. load a map
#         c. save a map
#         d visualise map
#   2. EA options:
#         a. parameters setup
#         b. run the solver
#         c. visualise the statistics
#         d. view the drone moving on a path
#              function gui.movingDrone(currentMap, path, speed, markseen)
#              ATENTION! the function doesn't check if the path passes trough walls

class Ui:
    def __init__(self, args):
        self.controller = Controller(args)

    def run(self):
        self.__print_main_ui()
        menu_choice = input("Choose a menu, master ")
        if menu_choice == "1":
            self.__print_map_options()
            menu_choice = input("Choose an option master ")
            if menu_choice == "a":
                path = self.controller.run(args)
                true_path = []
                for ele in path.get_x():
                    true_path.append(v[ele.gene])
                print(true_path)
                movingDrone(self.controller.get_cmap(), true_path)
        elif menu_choice == "2":
            self.__print_ea_options()

    @staticmethod
    def __print_main_ui():
        print("1 -> Map options !")
        print("2 -> EA options !")

    @staticmethod
    def __print_map_options():
        print("a -> create random map")
        print("b -> load a map")
        print("c -> save a map")
        print("d -> visualize map")

    @staticmethod
    def __print_ea_options():
        print("a -> parameters setup")
        print("b -> run the solver")
        print("c -> visualise the statistics")
        print("d -> view the drone moving on a path")


if __name__ == "__main__":
    # choice = input()
    args = CommandReader.read_command()
    for i in range(len(args)):
        args[i] = int(args[i])
    ui = Ui(args)
    ui.run()
