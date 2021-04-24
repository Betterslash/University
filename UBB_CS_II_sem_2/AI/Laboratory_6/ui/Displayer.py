from turtle import Turtle, Screen

from constants.Constants import Constants
from model.ItemEntry import ItemEntry


class Displayer:
    def __init__(self):
        self.__screen = Screen()

    def display(self, items: list[ItemEntry]):
        width, height = self.__screen.window_width(), self.__screen.window_height()

        turtle = Turtle(visible=False)
        turtle.speed('fastest')
        turtle.penup()

        for elem in items:
            radius = Constants.RADIUS_SIZE
            turtle.setposition(elem.get_x() * width // 20, elem.get_y() * height // 20)
            turtle.dot(radius * 5, elem.get_color())
