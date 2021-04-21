# -*- coding: utf-8 -*-
from Assignment4.ACOImpl.Ant import exp_map
from Assignment4.controller import Controller
from Assignment4.gui import movingDrone


class Ui:
    def __init__(self):
        self.controller = Controller()


if __name__ == "__main__":
    # choice = input()
    ui = Ui()
    best_sol = ui.controller.run()
    movingDrone(exp_map, [[2, 1]], best_sol)
    print(best_sol.get_path())
    print("ok")
