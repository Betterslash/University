import pygame

from constants.Constants import Constants
from model.ItemEntry import ItemEntry

screen = pygame.display.set_mode((800, 700))
pygame.display.set_caption("Resolve")
screen.fill(Constants.WHITE)
pygame.display.update()
dsp = False


class Displayer:

    @staticmethod
    def draw(point: ItemEntry, radius=3, color=Constants.BLACK):
        pos = (point.get_x(), point.get_y())

        if radius != 3:
            pygame.draw.circle(screen, Constants.WHITE, pos, radius * 2)

        pygame.draw.circle(screen, color, pos, radius)
        if dsp:
            pygame.display.update()

    @staticmethod
    def display(items: list[list[ItemEntry]]):
        pygame.init()
        pygame.display.update()
        running = True
        while running:
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    running = False
            color = [Constants.GREEN, Constants.BLACK, Constants.PURPLE, Constants.BLUE]
            i = -1
            for elem in items:
                i += 1
                for dot in elem:
                    pygame.draw.circle(screen, color[i], (dot.get_x() * 25 + 300, dot.get_y() * 25 + 300), 2)
                    pygame.display.update()
