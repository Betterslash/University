# -*- coding: utf-8 -*-


# imports
from Assignment3.commandreader import CommandReader
from controller import *
from gui import *


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
    def __init__(self):
        self.controller = Controller()

    def run(self):
        running = True
        while running:
            self.__print_main_ui()
            menu_choice = input("Choose a menu, master ")
            if menu_choice == "1":
                self.__print_map_options()
                menu_choice = input("Choose an option master ")
                if menu_choice == "a":
                    self.controller.initialize_repository_map()
                    print("Map succesfully initialized ...")
                elif menu_choice == "b":
                    menu_choice = int(input("Give here the map number >> "))
                    maps_number = self.controller.set_map(menu_choice)
                    print("You have " + str(maps_number) + " maps !")
                    print("Map succesfully laoded ...")
                elif menu_choice == "c":
                    self.controller.save_repository_map()
                    print("Map succesfully saved ...")
                elif menu_choice == "d":
                    self.controller.repository.visualise_map()
            elif menu_choice == "2":
                self.__print_ea_options()
                menu_choice = input("Choose an option master ")
                if menu_choice == "a":
                    print("Initialize args here >> ")
                    args = CommandReader.read_command()
                    for i in range(len(args)):
                        args[i] = int(args[i])
                    self.controller.set_args(args)
                elif menu_choice == "b":
                    pass
                elif menu_choice == "c":
                    pass
                elif menu_choice == "d":
                    movingDrone(self.controller.repository.cmap, TEST_VECTOR)

    @staticmethod
    def __print_main_ui():
        print("1 -> Map options !")
        print("2 -> EA options !")

    @staticmethod
    def __print_map_options():
        print("a -> create random map")
        print("b -> load a map")
        print("c -> save current map")
        print("d -> visualize map")

    @staticmethod
    def __print_ea_options():
        print("a -> parameters setup")
        print("b -> run the solver")
        print("c -> visualise the statistics")
        print("d -> view the drone moving on a path")


if __name__ == "__main__":
    # choice = input()
    ui = Ui()
    ui.run()
