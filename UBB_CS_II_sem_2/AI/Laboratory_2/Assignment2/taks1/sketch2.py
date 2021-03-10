from random import randint

from taks1.Model.DroneClass import Drone
from taks1.Model.MapClass import Map
from taks1.View.ViewClass import View

if __name__ == "__main__":
    # call the main function
    x = randint(0, 19)
    y = randint(0, 19)
    drone = Drone(x, y)
    mapM = Map()
    view = View(mapM, drone)
    view.main()
