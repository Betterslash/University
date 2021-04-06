# -*- coding: utf-8 -*-
from Assignment4.controller import Controller


class Ui:
    def __init__(self):
        self.controller = Controller()


if __name__ == "__main__":
    # choice = input()
    ui = Ui()
    best_sol = ui.controller.run()
    print("ok")
